package com.meritconinc.shiplinx.carrier.loomis;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.lowagie.text.pdf.PdfWriter;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.dhl.DHLAPI;
import com.meritconinc.shiplinx.carrier.loomis.dao.LoomisTariffDAO;
import com.meritconinc.shiplinx.carrier.loomis.model.LoomisBeyond;
import com.meritconinc.shiplinx.carrier.loomis.model.LoomisTariff;
import com.meritconinc.shiplinx.carrier.utils.LoomisException;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.FuelSurcharge;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.FuelSurchargeService;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class LoomisAPI implements CarrierService {

	private static final Logger logger = Logger.getLogger(LoomisAPI.class);
	public static final String FREIGHT_CHARGE_CODE	="FRT"; 
	public static final String AIR_FUEL_CHARGE_CODE = "AF";	
	public static final String GROUND_FUEL_CHARGE_CODE = "GF";	 
	
	private static final int OVERSIZE_INCHES = 60;
	private static final double OVERSIZE_CHARGE = 12.0;
	private static final int OVERWEIGHT_LBS = 75;
	private static final double OVERWEIGHT_CHARGE = 12.0;
	private static final double RESIDENTIAL_CHARGE = 5.0;
	private static final double SATURDAY_CHARGE = 16.0;
	private static final float MIN_INSURANCE_COVERAGE = 100;
	private static final float INSURANCE_COST_PER_100 = 3;
	private static final String INSURANCE_CHARGE_CODE = "INS";
	private static final double DANGEROUS_GOODS_CHARGE = 30.0;



	private List<CarrierErrorMessage> errorMessages = new ArrayList();


	private CustomerManager customerManager;
	private ShippingDAO shippingDAO;
	private CarrierServiceDAO carrierServiceDAO;
	private ShippingService shippingService;
	private FuelSurchargeService fuelSurchargeService = null;
	private LoomisTariffDAO loomisTariffDAO = null;

	public void setShippingDAO(ShippingDAO dao) {
		this.shippingDAO = dao;
	}

	public void setShippingService(ShippingService mgr) {
		this.shippingService = mgr;
	}

	public void setCustomerManager(CustomerManager mgr) {
		this.customerManager = mgr;
	}

	public void setLoomisTariffDAO(LoomisTariffDAO loomisTariffDAO) {
		this.loomisTariffDAO = loomisTariffDAO;
	}

	public long getCarrierId() {
		return ShiplinxConstants.CARRIER_LOOMIS;
	}

	public List<Rating> rateShipment(ShippingOrder order, List<Service> services, long carrierId, CustomerCarrier customerCarrier) {

		//Loomis is for Domestic Canada only
		if(DHLAPI.getShipmentType(customerCarrier.getCountry(), order.getFromAddress().getCountryCode(), order.getToAddress().getCountryCode()) != ShiplinxConstants.SHIPMENT_TYPE_DOMESTIC)
			return null;
		
		ShiplinxConstants.setServices(services);
		logger.debug("----------rateShipment-- Loomis---"+services.size());
		List<Rating> rates = new ArrayList<Rating>();
		List<Rating> rateList = new ArrayList<Rating>();

		try{
			rates = rateShipmentByService(order, null, customerCarrier);
			if (rates != null)
				rateList.addAll(rates);
		}catch (LoomisException e) {
			logger.debug("--LoomisException-- Loomis---",e);
			throw e;
		}
		catch (Exception e) {
			logger.debug("--LoomisException-- Loomis---",e);
			throw new LoomisException(e.getMessage());
		}

		return rateList;
	}


	private List<Rating> rateShipmentByService(ShippingOrder order, Long serviceId, CustomerCarrier customerCarrier) throws LoomisException{
		logger.debug("----------rateShipmentByService-- Loomis---"+order);
		
		List<Rating> rates = new ArrayList<Rating>();
		long start = System.currentTimeMillis();
		if(order.getPackageTypeId().getName().equalsIgnoreCase(ShiplinxConstants.PACKAGE_TYPE_PALLET_STRING)) {
			logger.debug("Pallet package type is not supported");
			return null;
		}

		try {
//			LoomisRequestBuilder requestBuilder = new LoomisRequestBuilder();
//			requestBuilder.createAndSendRateRequest(order, null);
			
			List<Service> carrierServicesList = carrierServiceDAO.getServicesForCarrier(getCarrierId());
			
			
			FuelSurcharge f = new FuelSurcharge();
			f.setCarrierId(ShiplinxConstants.CARRIER_LOOMIS);
			f.setFromCountry(customerCarrier.getCountry());
			List<FuelSurcharge> fuelsurcharges = fuelSurchargeService.getFuelSurcharge(f);		
			f = fuelsurcharges.get(0);

			//determine the total dim weight
			double totalWeightAir = 0;
			for(com.meritconinc.shiplinx.model.Package p: order.getPackages()){
				double dimWeight = p.getLength().doubleValue()*p.getWidth().doubleValue()*p.getHeight().doubleValue()*12/1728;
				if(dimWeight < p.getWeight().floatValue())
					totalWeightAir += p.getWeight().floatValue();
				else
					totalWeightAir += dimWeight;
			}
			totalWeightAir = Math.ceil(totalWeightAir);
			
			//determine the total dim weight
			double totalWeightGround = 0;
			int numPiecesOversize = 0;
			int numPiecesOverweight = 0;
			for(com.meritconinc.shiplinx.model.Package p: order.getPackages()){
				double dimWeight = p.getLength().doubleValue()*p.getWidth().doubleValue()*p.getHeight().doubleValue()*10/1728;
				if(dimWeight < p.getWeight().floatValue())
					totalWeightGround += p.getWeight().floatValue();
				else
					totalWeightGround += dimWeight;

				if(p.getLength().doubleValue() >= OVERSIZE_INCHES || p.getWidth().doubleValue() >= OVERSIZE_INCHES || p.getHeight().doubleValue() >= OVERSIZE_INCHES)
					numPiecesOversize++;
				if(dimWeight >= OVERWEIGHT_LBS || p.getWeight().floatValue() >= OVERWEIGHT_LBS)
					numPiecesOverweight++;				

			
			}
			
			totalWeightGround = Math.ceil(totalWeightGround);

			for(Service s: carrierServicesList){
				
				double totalWeight;
				if(s.getMode()==ShiplinxConstants.MODE_TRANSPORT_AIR_VALUE)
					totalWeight = totalWeightAir;
				else
					totalWeight = totalWeightGround;
				
				LoomisTariff tariff = new LoomisTariff(s.getId(), order.getPackageTypeId().getPackageTypeId(), totalWeight, null, order.getFromAddress().getPostalCode(), order.getToAddress().getPostalCode(), null, null);
				try{
					tariff = loomisTariffDAO.getTariffRecord(tariff);
				}
				catch(Exception e){
					tariff = null;
				}
				if(tariff == null){
					logger.error("Could not find Loomis tariff record for service: " + s.getName());
					continue;
				}
				Rating rate = new Rating();
				rate.setBillWeight(totalWeight);
				rate.setLoginURL(getLoginURL(order));
				rate.setServiceId(s.getId());	
				rate.setInstanceAPIName(this.toString());
				rate.setCarrierId(getCarrierId());		
				rate.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
//				rate.setCarrierName(ShiplinxConstants.CARRIER_LOOMIS_STRING);
				rate.setServiceName(s.getName());
				
				Charge c = new Charge();
				c.setChargeCode(FREIGHT_CHARGE_CODE);
				c.setTariffRate(tariff.getRate());
				c.setCost(FormattingUtil.add(c.getTariffRate().doubleValue(),(-1 * c.getTariffRate() *  customerCarrier.getBusinessCarrierDiscount()/100)).doubleValue());
				c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
				rate.getCharges().add(c);
				
				c = new Charge();
				if(s.getMode()==ShiplinxConstants.MODE_TRANSPORT_AIR_VALUE)
					c.setChargeCode(AIR_FUEL_CHARGE_CODE);
				else
					c.setChargeCode(GROUND_FUEL_CHARGE_CODE);
				c.setTariffRate(tariff.getRate() * f.getValue()/100);
				c.setCost(FormattingUtil.add(c.getTariffRate().doubleValue(),(-1 * c.getTariffRate() *  customerCarrier.getBusinessCarrierDiscount()/100)).doubleValue());
				c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
				rate.getCharges().add(c);
				
				if(order.getInsuranceValue() > MIN_INSURANCE_COVERAGE){
					c = new Charge();
					double chargeAmount = INSURANCE_COST_PER_100 * (order.getInsuranceValue()-MIN_INSURANCE_COVERAGE) / 100; //the insurance value stored in db is a percentage
					c.setTariffRate(chargeAmount);
					c.setCost(chargeAmount);
					c.setCharge(chargeAmount);
					c.setChargeCode(INSURANCE_CHARGE_CODE);
					c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
					rate.getCharges().add(c);				
				}

				
				if(numPiecesOversize > 0){
					c = new Charge();
					c.setChargeCode("OV");
					c.setTariffRate(OVERSIZE_CHARGE * numPiecesOversize);
					c.setCost(OVERSIZE_CHARGE * numPiecesOversize);
					c.setCharge(OVERSIZE_CHARGE * numPiecesOversize);
					c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
					rate.getCharges().add(c);
				}
				
				if(numPiecesOverweight > 0){
					c = new Charge();
					c.setChargeCode("OV");
					c.setTariffRate(OVERWEIGHT_CHARGE * numPiecesOverweight);
					c.setCost(OVERWEIGHT_CHARGE * numPiecesOverweight);
					c.setCharge(OVERWEIGHT_CHARGE * numPiecesOverweight);
					c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
					rate.getCharges().add(c);
				}
				
				//Saturday service
				if(order.getToAddress().isResidential()){
					c = new Charge();
					c.setChargeCode("R");
					c.setTariffRate(RESIDENTIAL_CHARGE);
					c.setCost(RESIDENTIAL_CHARGE);
					c.setCharge(RESIDENTIAL_CHARGE);
					c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
					rate.getCharges().add(c);
				}
				
				//Saturday service
				if(order.getSatDelivery()){
					c = new Charge();
					c.setChargeCode("SD");
					c.setTariffRate(SATURDAY_CHARGE);
					c.setCost(SATURDAY_CHARGE);
					c.setCharge(SATURDAY_CHARGE);
					c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
					rate.getCharges().add(c);
				}
				
				//check if from or to address is "beyond"
				//from
				LoomisBeyond beyond = new LoomisBeyond();
				beyond.setFromPostalCode(order.getFromAddress().getPostalCode());				
				beyond = loomisTariffDAO.getBeyondRecord(beyond);
				if(beyond!=null){//this is a beyond address
					c = new Charge();
					c.setChargeCode("RA");
					c.setTariffRate(new Double(beyond.getInterProvBase()));
					c.setCost(new Double(beyond.getInterProvBase()));
					c.setCharge(new Double(beyond.getInterProvBase()));
					c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
					rate.getCharges().add(c);					
				}
				//to
				beyond = new LoomisBeyond();
				beyond.setFromPostalCode(order.getToAddress().getPostalCode());				
				beyond = loomisTariffDAO.getBeyondRecord(beyond);
				if(beyond!=null){//this is a beyond address
					c = new Charge();
					c.setChargeCode("RA");
					c.setTariffRate(new Double(beyond.getInterProvBase()));
					c.setCost(new Double(beyond.getInterProvBase()));
					c.setCharge(new Double(beyond.getInterProvBase()));
					c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
					rate.getCharges().add(c);					
				}
				
				//Dangerous Goods
				if(order.getDangerousGoods()!=null && order.getDangerousGoods()>0){//this is a beyond address
					c = new Charge();
					c.setChargeCode("DG");
					c.setTariffRate(DANGEROUS_GOODS_CHARGE);
					c.setCost(DANGEROUS_GOODS_CHARGE);
					c.setCharge(DANGEROUS_GOODS_CHARGE);
					c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
					rate.getCharges().add(c);					
				}

				rates.add(rate);			

				
			}
			
		}catch (LoomisException e) {
			//e.printStackTrace();
			throw e;
		}catch (Exception e) {
			//e.printStackTrace();
			throw new LoomisException("Unable to get shipping rates");
		}

		return rates;
	}

	public void requestPickup(Pickup pickup) {
	}

	public boolean cancelPickup(Pickup pickup) {
		return false;
	}
	public Object isDelivered(ShippingOrder o) {
		// TODO Auto-generated method stub
		return null;
	}

	public void checkForShipmentStatusUpdates() {

	
	}

	/**
	 * Ship Order
	 */
	public void shipOrder(ShippingOrder order, Rating rateInfo, CustomerCarrier customerCarrier) throws LoomisException{

		logger.debug("---shipOrder----Loomis-----");

//		String serviceCode = (String)ShiplinxConstants.service_code.get(rateInfo.getServiceId());
//		LoomisRequestBuilder request_builder = new LoomisRequestBuilder();
//
//		try{
//			com.cwsi.eshipper.carrier.ups.ship.ShipmentAcceptResponseDocument accept_document = request_builder.createAndSendShipRequest(order, serviceCode);
//
//			LoomisResponseHelper response_helper = new LoomisResponseHelper();
//			response_helper.processShipmentAcceptResponse(accept_document, order);
//		}
//		catch(LoomisException e){
//			StringBuilder messages = new StringBuilder();
//			for(String s:e.getErrorMessages()){
//				messages.append(s + "\n");
//				logger.debug("---shipOrder----UPS-----"+s);
//			}
//			throw e;
//		}
//
//		order.getCharges().addAll(rateInfo.getCharges());
//		order.setBaseCharge(rateInfo.getBaseCharge());
//		order.setTotalCharge(rateInfo.getTotalCost());
//		order.setFuelCharges(rateInfo.getFuelSurcharge());

	}

	public boolean cancelOrder(ShippingOrder order, CustomerCarrier customerCarrier) {
		if(StringUtil.isEmpty(order.getMasterTrackingNum()))
			return true; //if no tracking # was generated, we can safely treat this shipment as cancelled
		LoomisRequestBuilder request_builder = new LoomisRequestBuilder();
//		boolean isCanceled=request_builder.createAndSendVoidRequest(order);	
		return false;

	}

	public String getTrackingURL(ShippingOrder o) {
		StringBuilder stb = new StringBuilder();
		stb.append("http://www.loomisexpress.com/ca/wfTrackingStatus.aspx?PieceNumber=");
		
		stb.append(o.getMasterTrackingNum());
			
		return stb.toString();
	}

	public String getLoginURL(ShippingOrder o) {
		StringBuilder stb = new StringBuilder();
		stb.append("https://www.loomisexpress.com/ca/wfSignUp.aspx");
		
		return stb.toString();
	}

	public void generateShippingLabel(OutputStream outputStream, long orderId, CustomerCarrier customerCarrier) {
		logger.debug("Attempting to retrieve shipping label for order with id " + orderId);
		//order = orderDAO.getOrder(orderId);

	}

	public void generatePackageLabel(OutputStream outputStream, long orderId) {
	}

	public void generatePdfManifestSheet(OutputStream outputStream,long customerId, Date scheduledShipDate) {

	}

	public void generateVoidSheet(OutputStream outputStream, long customerId,
			Date scheduledShipDate) {

	}

	public void generatePickupSheet(OutputStream outputStream, long customerId,
			Date scheduledShipDate, int pickupNum, boolean commit) {

	}

	public void generateShippingLabel(OutputStream outputStream, long orderId,
			Document document, PdfWriter writer) {
	}



	public List<CarrierErrorMessage> getErrorMessages(){
		return errorMessages;
	}


	private void breakdownRates(List<Rating> rates, ShippingOrder order){
		logger.debug("--------------breakdownRates------------------"+order);
		//delivery area surcharge for the US 
	}

	//for now, all rates for Loomis shipments will be CAD/USA currency as per From or To country
	private void setCurrency(List<Rating> rates, ShippingOrder order){
		if(rates==null)
			return;
		for(Rating rate:rates){
			setCurrency(rate, order);
		}
	}

	//for now, all rates for Loomis shipments will be CAD/USA currency as per From or To country
	private void setCurrency(Rating rate, ShippingOrder order){
	}

	public Object getShippingOrderStatus(ShippingOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	public CarrierServiceDAO getCarrierServiceDAO() {
		return carrierServiceDAO;
	}

	public void setCarrierServiceDAO(CarrierServiceDAO carrierServiceDAO) {
		this.carrierServiceDAO = carrierServiceDAO;
	}

	public FuelSurchargeService getFuelSurchargeService() {
		return fuelSurchargeService;
	}

	public void setFuelSurchargeService(FuelSurchargeService fuelSurchargeService) {
		this.fuelSurchargeService = fuelSurchargeService;
	}

	public List<Rating> generateShippingLabel(ShippingOrder shippingOrder,
			OutputStream outputStream) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setCustomerCarrierData(CustomerCarrier carrierData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void uploadRates(Service service, long customerId, long busId, boolean isOverwrite) {
		// TODO Auto-generated method stub
		
	}
	

}
