package com.meritconinc.shiplinx.carrier.midland;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.mail.MailHelper;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.loomis.dao.LoomisTariffDAO;
import com.meritconinc.shiplinx.carrier.midland.model.MidlandRate;
import com.meritconinc.shiplinx.carrier.utils.CarrierException;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.MarkupManagerDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Attachment;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.FuelSurcharge;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.model.Zone;
import com.meritconinc.shiplinx.service.FuelSurchargeService;
import com.meritconinc.shiplinx.service.impl.InvoiceManagerImpl;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class MidlandAPI implements CarrierService{
	
	private static final Logger logger = Logger.getLogger(MidlandAPI.class);
	public static final String FREIGHT_CHARGE_CODE	="FRT"; 
	public static final String AIR_FUEL_CHARGE_CODE = "AF";	
	public static final String GROUND_FUEL_CHARGE_CODE = "GF";	
	
	private static final int OVERSIZE_INCHES = 60;
	private static final double OVERSIZE_CHARGE = 12.0;
	private static final int OVERWEIGHT_LBS = 150;
	private static final double OVERWEIGHT_CHARGE = 12.0;
	private static final double RESIDENTIAL_CHARGE = 5.0;
	private static final double SATURDAY_CHARGE = 16.0; 
	private static final float MIN_INSURANCE_COVERAGE = 100;
	private static final float INSURANCE_COST_PER_100 = 3;
	private static final String INSURANCE_CHARGE_CODE = "INS";
	private static final double DANGEROUS_GOODS_CHARGE = 30.0;
	private static final String SERVICE_TYPE_AIR = "Air";
	private static final String SERVICE_TYPE_GROUND = "Ground";

	private List<CarrierErrorMessage> errorMessages = new ArrayList();

	
	private CustomerDAO customerDAO;
	private BusinessDAO businessDAO;
	private ShippingDAO shippingDAO;
	private CarrierServiceDAO carrierServiceDAO;
	private FuelSurchargeService fuelSurchargeService = null;
	private LoomisTariffDAO loomisTariffDAO = null;
	private MarkupManagerDAO markupDAO;
	//Declare DAO
	private  AddressDAO addressDAO;

	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public void setShippingDAO(ShippingDAO shippingDAO) {
		this.shippingDAO = shippingDAO;
	}

	public void setLoomisTariffDAO(LoomisTariffDAO loomisTariffDAO) {
		this.loomisTariffDAO = loomisTariffDAO;
	}

	public void setMarkupDAO(MarkupManagerDAO markupDAO) {
		this.markupDAO = markupDAO;
	}

	public long getCarrierId() {
		return ShiplinxConstants.CARRIER_MIDLAND;
	}

	public List<Rating> rateShipment(ShippingOrder order, List<Service> services, long carrierId, CustomerCarrier customerCarrier) {

		
		ShiplinxConstants.setServices(services);
		logger.debug("----------rateShipment-- Midland---"+services.size());
		List<Rating> rates = new ArrayList<Rating>();
		List<Rating> rateList = new ArrayList<Rating>();

		try{
			rates = rateShipmentByService(order, null, customerCarrier);
			if (rates != null)
				rateList.addAll(rates);
		}catch (ShiplinxException e) {
			logger.debug("--MidlandException-- Midland---",e);
			throw e;
		}
		catch (Exception e) {
			logger.debug("--MidlandException-- Midland---",e);
			throw new ShiplinxException(e.getMessage());
		}

		return rateList;
	}


	private List<Rating> rateShipmentByService(ShippingOrder order, Long serviceId, CustomerCarrier customerCarrier) throws CarrierException{
		logger.debug("----------rateShipmentByService-- Midland---"+order);
		
		List<Rating> rates = new ArrayList<Rating>();
		long start = System.currentTimeMillis();

		try {

			List<Service> carrierServicesList = carrierServiceDAO.getServicesForCarrier(getCarrierId());			
			FuelSurcharge air = null;
			FuelSurcharge ground = null;
			FuelSurcharge f = new FuelSurcharge();
			f.setCarrierId(ShiplinxConstants.CARRIER_MIDLAND);
			f.setType(SERVICE_TYPE_AIR);
			//There's only one record in the fuel_surcharge table currently
			List<FuelSurcharge> fuelsurcharges = fuelSurchargeService.getFuelSurcharge(f);		
			air = fuelsurcharges.get(0);
			f.setType(SERVICE_TYPE_GROUND);
			//There's only one record in the fuel_surcharge table currently
			fuelsurcharges = fuelSurchargeService.getFuelSurcharge(f);	
			ground = fuelsurcharges.get(0);

			//determine the total dim weight
			double totalWeightAir = 0;
			double totalWeightGround = 0;
			for(com.meritconinc.shiplinx.model.Package p: order.getPackages()){
				
				//weight of individual package cannot exceed 150 LB
				if(p.getWeight().floatValue()>OVERWEIGHT_LBS){
					throw new CarrierException("The weight of an individual piece cannot exceed 150 lb");
				}
				//Girth cannot exceed 130 LB
				double pGirth = p.getLength().doubleValue() + 2*p.getWidth().doubleValue() + 2*p.getHeight().doubleValue();
				if(pGirth > 130){
					throw new CarrierException("The size of an individual piece cannot exceed 130 lb");
				}
				
				double dimWeight = p.getLength().doubleValue()*p.getWidth().doubleValue()*p.getHeight().doubleValue()*15/1728;
				if(dimWeight < p.getWeight().floatValue())
					totalWeightAir += p.getWeight().floatValue();
				else
					totalWeightAir += dimWeight;

				double dimWeightGrd = p.getLength().doubleValue()*p.getWidth().doubleValue()*p.getHeight().doubleValue()*15/1728;
				if(dimWeightGrd < p.getWeight().floatValue())
					totalWeightGround += p.getWeight().floatValue();
				else
					totalWeightGround += dimWeightGrd;
			}
			
			totalWeightAir = Math.ceil(totalWeightAir);			
			totalWeightGround = Math.ceil(totalWeightGround);

			for(Service s: carrierServicesList){
				
				Zone fromZone = this.markupDAO.findZoneByPostalCode(s.getZoneStructureId(), order.getFromAddress());
				if(fromZone == null){
					logger.debug("Could not find from zone record for service and address: " + s.getName() + " / " + order.getFromAddress().getLongAddress());
					return null;
				}
				Zone toZone = this.markupDAO.findZoneByPostalCode(s.getZoneStructureId(), order.getToAddress());
				if(toZone == null){
					logger.debug("Could not find to zone record for service and address: " + s.getName() + " / " + order.getToAddress().getLongAddress());
					return null;
				}
				
				
				double totalWeight;
				if(s.getMode()==ShiplinxConstants.MODE_TRANSPORT_AIR_VALUE)
					totalWeight = totalWeightAir;
				else
					totalWeight = totalWeightGround;
				
				MidlandRate midlandRate = new MidlandRate(s.getId(), fromZone.getZoneName(), toZone.getZoneName());
				
				try{
					midlandRate = loomisTariffDAO.getMidlandRate(midlandRate);
				}
				catch(Exception e){
					midlandRate = null;
				}
				if(midlandRate == null){
					logger.error("Could not find Midland rate record for service: " + s.getName());
					continue;
				}
				
				Rating rate = new Rating();
				rate.setBillWeight(totalWeight);
				rate.setServiceId(s.getId());	
				rate.setInstanceAPIName(this.toString());
				rate.setCarrierId(getCarrierId());		
				rate.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
				rate.setServiceName(s.getName());
				
				double freightCharge = midlandRate.getBaseRate();
				double perPound = 0;

				if(s.getMode()==ShiplinxConstants.MODE_TRANSPORT_AIR_VALUE){
					if(totalWeight>50){ //4-50 lb @ tier1 and 51+ at tier2
						perPound = (FormattingUtil.add(perPound , (totalWeight-50)*midlandRate.getPerPoundTier2())).doubleValue();
						perPound = (FormattingUtil.add(perPound , 47*midlandRate.getPerPoundTier1())).doubleValue();
					}
					else if(totalWeight>=4) //between 4 and 50 LB
						perPound = (FormattingUtil.add(perPound , (totalWeight-3)*midlandRate.getPerPoundTier1())).doubleValue();					
				}
				else { //Ground
					if(totalWeight>50){ //11-50 lb @ tier1 and 51+ at tier2
						perPound = (FormattingUtil.add(perPound , (totalWeight-50)*midlandRate.getPerPoundTier2())).doubleValue();
						perPound = (FormattingUtil.add(perPound , 40*midlandRate.getPerPoundTier1())).doubleValue();
					}
					else if(totalWeight>=11) //between 4 and 50 LB
						perPound = (FormattingUtil.add(perPound , (totalWeight-10)*midlandRate.getPerPoundTier1())).doubleValue();					
				}
				
				if(perPound > 0){
					freightCharge = FormattingUtil.add(freightCharge , (perPound)).doubleValue();
				}
				
				Charge c = new Charge();
				c.setChargeCode(FREIGHT_CHARGE_CODE);
				c.setTariffRate(freightCharge);
				c.setCost(freightCharge);
				c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
				rate.getCharges().add(c);
				
				c = new Charge();
				if(s.getMode()==ShiplinxConstants.MODE_TRANSPORT_AIR_VALUE){
					c.setChargeCode(AIR_FUEL_CHARGE_CODE);
					f = air;
				}
				else{
					c.setChargeCode(GROUND_FUEL_CHARGE_CODE);
					f = ground;
				}
				c.setTariffRate(freightCharge * f.getValue()/100);
				c.setCost(c.getTariffRate());
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

				//NAV Surcharge for AIR
				if(s.getMode()==ShiplinxConstants.MODE_TRANSPORT_AIR_VALUE){
					c = new Charge();
					c.setChargeCode("NV");
					c.setTariffRate(7.5 * freightCharge / 100);
					c.setCost(c.getTariffRate());
					c.setCharge(c.getTariffRate());
					c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
					rate.getCharges().add(c);					
				}

				rates.add(rate);			

				
			}
			
		}catch (CarrierException e) {
			//e.printStackTrace();
			throw e;
		}catch (Exception e) {
			//e.printStackTrace();
			throw new CarrierException("Unable to get shipping rates");
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
	public void shipOrder(ShippingOrder order, Rating rateInfo, CustomerCarrier customerCarrier) throws CarrierException{
		
		/*StringBuffer stb = new StringBuffer();
		String subject = MessageUtil.getMessage(
				"label.shipment.notification");
		String message = MessageUtil.getMessage("content.ltl.shipment");
		
		Customer customer = customerDAO.getCustomerInfoByCustomerId(order.getCustomerId(), order.getBusinessId());
		Business business = businessDAO.getBusiessById(order.getBusinessId());
		
		message = new String(message.replaceAll("%CARRIER", order.getService().getCarrier().getName()));
		message = new String(message.replaceAll("%SERVICE", order.getService().getName()));
		message = new String(message.replaceAll("%CUSTOMERNAME", customer.getName()));
		message = new String(message.replaceAll("%ORDERNUM", order.getOrderNum()));
		message = new String(message.replaceAll("%ORDERSIZE", String.valueOf(order.getPackages().size())));
		message = new String(message.replaceAll("%TOTALWEIGHT", String.valueOf(order.getTotalWeight())));
		
	    List<Attachment> attachs = new ArrayList<Attachment>();

		try {
			File shippingLabels = new File(File.createTempFile("shippingLabel","").getAbsoluteFile() + ".pdf");	
			shippingLabels.deleteOnExit();
			BufferedOutputStream sbos = new BufferedOutputStream(new FileOutputStream(shippingLabels));
			Attachment attach = new Attachment();
			attach.setFile(shippingLabels);
			attach.setContentType("pdf");
			attachs.add(attach);
			
			try {							
				this.generateShippingLabel(sbos, order, customerCarrier);
				sbos.close();
											
			}catch(Exception e) {
				e.printStackTrace();
				logger.error("Could not create shipping label for notification attachment!", e);
			}
			
			
		}catch(Exception e) {
			logger.error(e);
		}

	       
		try {
			MailHelper.sendEmailNow2(business.getSmtpHost(), business.getSmtpUsername(), business.getSmtpPassword(), business.getName(), business.getSmtpPort(), business.getAddress().getEmailAddress(), business.getAddress().getEmailAddress(), null, subject, message, attachs, true);
		} catch (Exception e) {
			logger.error("Error sending email: " + e.getMessage());
		}*/


	}

	public boolean cancelOrder(ShippingOrder order, CustomerCarrier customerCarrier) {
		if(StringUtil.isEmpty(order.getMasterTrackingNum()))
			return true; //if no tracking # was generated, we can safely treat this shipment as cancelled

//		boolean isCanceled=request_builder.createAndSendVoidRequest(order);	
		return false;

	}

	public String getTrackingURL(ShippingOrder o) {
		return null;
	}

	public String getLoginURL(ShippingOrder o) {
		return null;
	}

	public void generateShippingLabel(OutputStream outputStream, long orderId, CustomerCarrier customerCarrier) {
		ShippingOrder order = shippingDAO.getShippingOrder(orderId);		
		generateShippingLabel(outputStream, order, customerCarrier);

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
	public void generateShippingLabel(OutputStream outputStream, ShippingOrder order, CustomerCarrier customerCarrier) {
		Customer customer = customerDAO.getCustomerInfoByCustomerId(order.getCustomerId(), order.getBusinessId());
		List<String> listOfPackageID=new ArrayList<String>();		
		try{						
			InputStream stream=this.getClass().getClassLoader().getResourceAsStream("./jasperreports/src/main/java/com/meritconinc/shiplinx/carrier/generic/jasperreports/Midland.jasper");
			if(stream==null) {
				logger.error("Stream is NULL!");
			}
			JasperReport jasperReport = (JasperReport)JRLoader.loadObject(stream);
			Map<String, Object> parameters = new HashMap<String, Object>();
			Business business = businessDAO.getBusiessById(order.getBusinessId());			
            int quantity=order.getQuantity();          
            String masterTrackingNumber=order.getMasterTrackingNum();
            String barcodePrefix=""; 
            String wayBillNumber="";
            for(int i=0;i<masterTrackingNumber.length();i++) 
            { 
            char splitCharAndNumber=masterTrackingNumber.charAt(i); 
            if(Character.isLetter(splitCharAndNumber)) 
            { 
            	barcodePrefix=barcodePrefix+splitCharAndNumber; 
            }
            else 
            { 
            	wayBillNumber=wayBillNumber+splitCharAndNumber; 
            } 
            }  
            
            long packageIDSuffix = (long)Integer.parseInt(wayBillNumber)*1000;            
            for(int i=1;i<=quantity;i++){
            	long packageIdWithoutPrefix = packageIDSuffix+i;            	
            	listOfPackageID.add(barcodePrefix+packageIdWithoutPrefix);            	 
            }             
                
            //long carrierAccountId=customerCarrier.getCarrierAccountId();                                 
            String logoPath = "/images/" + business.getLogoHiResFileName();
			URL logo = (InvoiceManagerImpl.class.getResource(logoPath));
			parameters.put("logo",logo);
			parameters.put("Order", order);
			parameters.put("barcodenumber", listOfPackageID);					
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(order.getPackages());			
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,ds);
			jasperPrint.setPageHeight(6*72);
			jasperPrint.setPageWidth(4*72);		
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);	
			
	}
		catch(Exception e) {
			logger.error("Could not generate label for order with id " + order.getId() + " and customer " + customer.getName(), e);
			throw new ShiplinxException();
		}			
		
	}
	

	public List<CarrierErrorMessage> getErrorMessages(){
		return errorMessages;
	}


	private void breakdownRates(List<Rating> rates, ShippingOrder order){
		logger.debug("--------------breakdownRates------------------"+order);
		//delivery area surcharge for the US 
	}

	//for now, all rates for Midland shipments will be CAD/USA currency as per From or To country
	private void setCurrency(List<Rating> rates, ShippingOrder order){
		if(rates==null)
			return;
		for(Rating rate:rates){
			setCurrency(rate, order);
		}
	}

	//for now, all rates for Midland shipments will be CAD/USA currency as per From or To country
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
	
	

