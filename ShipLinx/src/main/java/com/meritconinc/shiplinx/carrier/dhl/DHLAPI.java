//NOTES REGARDING INFORMATATION IN CUSTOMER_CARRIER TABLE
//Account1=Export Shipping Account, Account2=Import Shipping Acct, Property1=Web service Login, Property2=Web service password, Property3=Export Rating Account, Property4=Import Rating Acct
//We need to store the rating and shipping accounts separately because the shipping accounts have a rate block on them


package com.meritconinc.shiplinx.carrier.dhl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.cwsi.eshipper.carrier.ups.rate.RatingServiceSelectionResponseDocument;
import com.lowagie.text.pdf.PdfWriter;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.dhl.dao.DHLCanadaTariffDAO;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLAccCharges;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLTariff;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLZone;
import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidateResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.PickupResponse;
import com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.LabelImage;
import com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.response.ShipmentResponse;
import com.meritconinc.shiplinx.carrier.utils.DHLException;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.FuelSurcharge;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingLabel;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.FuelSurchargeService;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.PDFRenderer;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class DHLAPI implements CarrierService {

	private static final Logger logger = Logger.getLogger(DHLAPI.class);
	public static final String FREIGHT_CHARGE_CODE	="400"; 
	public static final String FUEL_CHARGE_CODE = "405";	
	public static final String REMOTE_AREA_SERVICE_CODE	="RES"; 
	public static final long SERVICE_EXPRESS = 301;
	public static final long SERVICE_EXPRESS_9AM = 300;
	public static final long SERVICE_EXPRESS_1030 = 303;
	public static final long SERVICE_EXPRESS_12PM = 304;
	public static final long SERVICE_ESI = 305;
	public static final long SERVICE_IMPORT = 306;
	public static final long SERVICE_IMPORT_9AM = 307;
	public static final long SERVICE_IMPORT_12PM = 308;
	public static final long SERVICE_INTL_GROUND = 309;
	private static final int OVERSIZE_INCHES = 48;
	private static final int OVERWEIGHT_LBS = 149;
	private static final String OVERSIZE_CHARGE_CODE = "520";
	private static final String SATURDAY_DELIVERY_CHARGE_CODE = "665";
	private static final String INSURANCE_CHARGE_CODE = "INS";
	private static final double ESI_EXPORT_MIN_WEIGHT = 24;
	
	public static final String DHL_CANADA_EXPORT_LINK =  "https://webshipping2.dhl.com/wsi/WSIServlet?moduleKey=Login&countryCode=CA";
	public static final String DHL_CANADA_IMPORT_LINK =  "https://importexpressonline.dhl.com/iea/jsps/login/Login.jsp";
	public static final String HTTP_URL_TEST = "https://xmlpitest-ea.dhl.com/XMLShippingServlet";
	public static final String HTTP_URL = "https://xmlpi-ea.dhl.com/XMLShippingServlet";

	private List<CarrierErrorMessage> errorMessages = new ArrayList();


	private CustomerManager customerManager;
	private ShippingDAO shippingDAO;
	private CarrierServiceDAO carrierServiceDAO;
	private ShippingService shippingService;
	private FuelSurchargeService fuelSurchargeService = null;
	private DHLCanadaTariffDAO dhlCanadaTariffDAO = null;

	public void setShippingDAO(ShippingDAO dao) {
		this.shippingDAO = dao;
	}

	public void setShippingService(ShippingService mgr) {
		this.shippingService = mgr;
	}

	public void setCustomerManager(CustomerManager mgr) {
		this.customerManager = mgr;
	}

	public void setDhlCanadaTariffDAO(DHLCanadaTariffDAO dhlCanadaTariffDAO) {
		this.dhlCanadaTariffDAO = dhlCanadaTariffDAO;
	}

	public long getCarrierId() {
		return ShiplinxConstants.CARRIER_DHL;
	}

	public List<Rating> rateShipment(ShippingOrder order, List<Service> services, long carrierId, CustomerCarrier customerCarrier) {

		ShiplinxConstants.setServices(services);
		logger.debug("----------rateShipment-- DHL---"+services.size());
		List<Rating> rates = new ArrayList<Rating>();
		List<Rating> rateList = new ArrayList<Rating>();

		try{
			rates = rateShipmentByService(order, null, customerCarrier);
			if (rates != null)
				rateList.addAll(rates);
		}catch (DHLException e) {
			logger.debug("--DHLException-- DHL---"+e);
			throw e;
		}
		catch (Exception e) {
			logger.debug("--DHLException-- DHL---"+e, e);
			throw new DHLException(e.getMessage());
		}

		//this is a special piece for IC, they want to copy DHL Express or DHL ESI rate (cheaper of 2) and show it as "Ground" with a different markup
		//We have added a service in the service table with id 309 to accommodate this.
		if(order.getDocsOnly()!=null && !order.getDocsOnly() && order.getPackageTypeId().getPackageTypeId()!=ShiplinxConstants.PACKAGE_TYPE_ENVELOPE){
			Rating rateToAdd = null;
			for(Rating rate: rateList){
				if(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) && order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US)){ 
					if(rate.getServiceId() == SERVICE_EXPRESS)
						rateToAdd = rate;
					if(rate.getServiceId() == SERVICE_ESI && rate.getBillWeight()>=ESI_EXPORT_MIN_WEIGHT){
						rateToAdd = rate;
						break;
					}			
				}
				if(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) && order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA)){ 
					if(rate.getServiceId() == SERVICE_IMPORT)
						rateToAdd = rate;
				}
			}
			if(rateToAdd != null){
				Rating targetRating = new Rating();
				Rating.copyRating(rateToAdd, targetRating);
				targetRating.setOriginalServiceId(rateToAdd.getServiceId());
				targetRating.setServiceId(SERVICE_INTL_GROUND);
				targetRating.setTransitDays(5); //hard-coded, but if UPS Ground is available, it will reset to that value in CarrierServiceManagerImpl
				Service s = carrierServiceDAO.getService(SERVICE_INTL_GROUND);
				targetRating.setServiceName(s.getName());
				rateList.add(targetRating);
				
			}
		}
		
		return rateList;
	}


	private List<Rating> rateShipmentByService(ShippingOrder order, Long serviceId, CustomerCarrier customerCarrier) throws DHLException{
		logger.debug("----------rateShipmentByService-- DHL---"+order);
		
		long start = System.currentTimeMillis();
		List<Rating> rateList = new ArrayList<Rating>();	
		
		if(order.getDangerousGoods()!=null && order.getDangerousGoods()>0){
			logger.debug("DHL rates not returned as DG has been requested.");
			return null;
		}
		
		//For Canada, the rates are currently stored locally on DB
		if(customerCarrier.getCountry().equals(ShiplinxConstants.CANADA)){
			if(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) || order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) ){
				if(order.getFromAddress().getCountryCode().equalsIgnoreCase(order.getToAddress().getCountryCode()))
					return null; //From and To country cannot be Canada, no domestic services available
				else
					//return rateLocal(order, serviceId);
					rateList = rateLocal(order, serviceId, customerCarrier); 
			}
			else 
				rateList = rateLocal(order, serviceId, customerCarrier); //third country rates
		}

		if(order.getPackageTypeId().getName().equalsIgnoreCase(ShiplinxConstants.PACKAGE_TYPE_PALLET_STRING)) {
			logger.debug("Pallet package type is not supported");
			return null;
		}

		FuelSurcharge f = new FuelSurcharge();
		f.setCarrierId(ShiplinxConstants.CARRIER_DHL);
		f.setFromCountry(customerCarrier.getCountry());
		List<FuelSurcharge> fuelsurcharges = fuelSurchargeService.getFuelSurcharge(f);	
		if(fuelsurcharges !=null && fuelsurcharges.size()>0)
		f = fuelsurcharges.get(0);

		DHLRequestBuilder request_builder = new DHLRequestBuilder();
		RatingServiceSelectionResponseDocument response = null;

		try{
			if(serviceId==null)
				return request_builder.createAndSendRateRequest(order, null, rateList, customerCarrier, f);
			else
				return request_builder.createAndSendRateRequest(order, (String)ShiplinxConstants.service_code.get(serviceId), rateList, customerCarrier, f);


		}catch (DHLException e) {
			//e.printStackTrace();
			throw e;
		}catch (Exception e) {
			e.printStackTrace();
			throw new DHLException("Unable to get shipping rates");
		}

//		return rateList;
	}

	private List<Rating> rateLocal(ShippingOrder order, Long serviceId, CustomerCarrier customerCarrier){
		List<Rating> rates = new ArrayList<Rating>();
		List<Service> carrierServicesList = carrierServiceDAO.getServicesForCarrier(getCarrierId());
		
		int shipmentType = getShipmentType(customerCarrier.getCountry(), order.getFromAddress().getCountryCode(), order.getToAddress().getCountryCode());
		
		DHLZone fromZone = new DHLZone(order.getFromAddress().getCountryCode());	
		fromZone = dhlCanadaTariffDAO.getZone(fromZone);
		DHLZone toZone = new DHLZone(order.getToAddress().getCountryCode());	
		toZone = dhlCanadaTariffDAO.getZone(toZone);
		if(fromZone==null){
			logger.error("Could not determine DHL zone for country " + order.getFromAddress().getCountryCode() + ". Cannot determine rates!");
			return null;
		}		
		if(toZone==null){
			logger.error("Could not determine DHL zone for country " + order.getToAddress().getCountryCode() + ". Cannot determine rates!");
			return null;
		}

		FuelSurcharge f = new FuelSurcharge();
		f.setCarrierId(ShiplinxConstants.CARRIER_DHL);
		f.setFromCountry(customerCarrier.getCountry());
		List<FuelSurcharge> fuelsurcharges = fuelSurchargeService.getFuelSurcharge(f);		
		f = fuelsurcharges.get(0);
		
		//determine the total dim weight
		double totalWeight = 0;
		for(com.meritconinc.shiplinx.model.Package p: order.getPackages()){
			double dimWeight = p.getLength().doubleValue()*p.getWidth().doubleValue()*p.getHeight().doubleValue()/139;
			if(dimWeight < p.getWeight().floatValue())
				totalWeight += p.getWeight().floatValue();
			else
				totalWeight += dimWeight;
		}
		
		totalWeight = Math.ceil(totalWeight);

		for(Service s: carrierServicesList){
			
			//if shipment is an export, then show only export services, else show only import services
			if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_EXPORT){				
				if(!(s.getId()>=SERVICE_EXPRESS_9AM && s.getId()<=SERVICE_ESI))
					continue;					
			}
			else if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_IMPORT)
				if(!(s.getId()>=SERVICE_IMPORT && s.getId()<=SERVICE_IMPORT_12PM))
					continue;					
			
			if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_THIRD_COUNTRY && s.getId()!=SERVICE_EXPRESS) //only service for third country is Express
				continue;					

			DHLTariff tariff = new DHLTariff(s.getId(), order.getPackageTypeId().getPackageTypeId(), totalWeight, 0.0, fromZone.getZoneCode(), toZone.getZoneCode(), shipmentType);
			try{
				tariff = dhlCanadaTariffDAO.getTariffRecord(tariff);
			}
			catch(Exception e){
				logger.error("Unable to retrieve tariff record", e);
				tariff = null;
			}
			if(tariff == null){
				logger.error("Could not find DHL tariff record for service: " + s.getName());
				continue;
			}
			Rating rate = new Rating();
			rate.setBillWeight(totalWeight);
			rate.setCustomerCarrier(customerCarrier);
			rate.setServiceId(s.getId());	
			rate.setInstanceAPIName(this.toString());
			rate.setCarrierId(getCarrierId());		
			rate.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
			rate.setCarrierName(ShiplinxConstants.CARRIER_DHL_STRING);
			rate.setServiceName(s.getName());
			rate.setLoginURL(getLoginURL(order, customerCarrier));
			
			Double tariff_rate = null;
			if(order.getDocsOnly() != null && order.getDocsOnly())
				tariff_rate = tariff.getRateDoc();
			else
				tariff_rate = tariff.getRateNonDoc();
			
			Charge c = new Charge();
			c.setChargeCode(FREIGHT_CHARGE_CODE);
			c.setTariffRate(tariff_rate);
//			c.setCost(FormattingUtil.add(c.getTariffRate().doubleValue(),(-1 * c.getTariffRate() *  customerCarrier.getBusinessCarrierDiscount()/100)).doubleValue());
			//hard coding the discount for now
			c.setCost(FormattingUtil.add(c.getTariffRate().doubleValue(),(-1 * c.getTariffRate() *  78/100)).doubleValue());
			c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
			rate.getCharges().add(c);
			
			c = new Charge();
			c.setChargeCode(FUEL_CHARGE_CODE);
			c.setTariffRate(tariff_rate * f.getValue()/100);
//			c.setCost(FormattingUtil.add(c.getTariffRate().doubleValue(),(-1 * c.getTariffRate() *  customerCarrier.getBusinessCarrierDiscount()/100)).doubleValue());
			c.setCost(FormattingUtil.add(c.getTariffRate().doubleValue(),(-1 * c.getTariffRate() *  78/100)).doubleValue());
			c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
			rate.getCharges().add(c);
			rate.setFuel_perc(f.getValue());

			//add the accessorial charges
			
			//oversize and overweight
			int numPiecesOversize = 0;
			int numPiecesOverweight = 0;
			for(com.meritconinc.shiplinx.model.Package p:order.getPackages()){
				if(p.getLength().doubleValue() > OVERSIZE_INCHES || p.getWidth().doubleValue() > OVERSIZE_INCHES || p.getHeight().doubleValue() > OVERSIZE_INCHES)
					numPiecesOversize++;
				if(p.getWeight().floatValue() > OVERWEIGHT_LBS)
					numPiecesOverweight++;				
			}
			
			if(numPiecesOversize > 0){
				DHLAccCharges accCharge = new DHLAccCharges(order.getBusinessId(), OVERSIZE_CHARGE_CODE, null, customerCarrier.getCountry());
				accCharge = findAccCharge(accCharge);
	
				if(accCharge!=null){
					addChargeToRate(accCharge, rate, numPiecesOversize);				
				}
			}
			
			if(numPiecesOverweight > 0){
				DHLAccCharges accCharge = new DHLAccCharges(order.getBusinessId(), OVERSIZE_CHARGE_CODE, null, customerCarrier.getCountry());
				accCharge = findAccCharge(accCharge);
	
				if(accCharge!=null){
					addChargeToRate(accCharge, rate, numPiecesOverweight);				
				}
			}
			
			//Saturday service
			if(order.getSatDelivery()){
				DHLAccCharges accCharge = new DHLAccCharges(order.getBusinessId(), SATURDAY_DELIVERY_CHARGE_CODE, null, customerCarrier.getCountry());
				accCharge = findAccCharge(accCharge);
	
				if(accCharge!=null){
					addChargeToRate(accCharge, rate, 1);				
				}				
			}

			//Dangerous Goods
//			if(order.getSatDelivery()){
//				DHLAccCharges accCharge = new DHLAccCharges(order.getBusinessId(), SATURDAY_DELIVERY_CHARGE_CODE, null, order.getCustomerCarrier().getCountry());
//				accCharge = findAccCharge(accCharge);
//	
//				if(accCharge!=null){
//					addChargeToRate(accCharge, rate, numPiecesOversize);				
//				}				
//			}
			
			//Insurance charge
			if(order.getInsuranceValue() > 0){
				DHLAccCharges accCharge = new DHLAccCharges(order.getBusinessId(), INSURANCE_CHARGE_CODE, null, customerCarrier.getCountry());
				accCharge = findAccCharge(accCharge);
	
				if(accCharge!=null){
					c = new Charge();
					c.setChargeCode(accCharge.getChargeCode());
					
					double chargeAmount = accCharge.getCharge() * order.getInsuranceValue() / 100; //the insurance value stored in db is a percentage
					double costAmount = accCharge.getCost() * order.getInsuranceValue() / 100; //the insurance value stored in db is a percentage;
					
					if(chargeAmount < accCharge.getMinCharge())
						chargeAmount = accCharge.getMinCharge();
					
					c.setTariffRate(chargeAmount);
					c.setCost(costAmount);
					c.setCharge(chargeAmount);
					c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
					rate.getCharges().add(c);
			
				}				
			}

			rates.add(rate);

			
		}
		
		return rates;
	}
	
	private void addChargeToRate(DHLAccCharges accCharge, Rating rate, int quantity){
		Charge c = new Charge();
		c.setChargeCode(accCharge.getChargeCode());
		
		double chargeAmount = accCharge.getCharge();
		double costAmount = accCharge.getCost();

		if(accCharge.isPerPackage()){
			chargeAmount = chargeAmount * quantity;
			costAmount = costAmount * quantity;
		}
		
		if(chargeAmount < accCharge.getMinCharge())
			chargeAmount = accCharge.getMinCharge();
		
		c.setTariffRate(chargeAmount);
		c.setCost(costAmount);
		c.setCharge(chargeAmount);
		c.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
		rate.getCharges().add(c);

	}
	
	private DHLAccCharges findAccCharge(DHLAccCharges accCharge){
		DHLAccCharges accChargeFound = dhlCanadaTariffDAO.getAcccharge(accCharge);
		if(accChargeFound == null){
			accCharge.setBusinessId(0);
			accChargeFound = dhlCanadaTariffDAO.getAcccharge(accCharge);
		}
		return accChargeFound;
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
	
	public void shipOrder(ShippingOrder order, Rating rateInfo, CustomerCarrier customerCarrier) throws DHLException{

		logger.debug("---shipOrder----DHL-----");
		
		com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.ShipmentRequest req = DHLXmlDataConverter.populateGlobalShipmentValidateRequest(order, rateInfo, this.dhlCanadaTariffDAO, customerCarrier);
		
		DHLXmlWSClient xmlWsClient = new DHLXmlWSClient();
		String sReq = xmlWsClient.globalTransform(req); 
		String servletURL = null;
		if(ShiplinxConstants.isTestMode())
			servletURL = HTTP_URL_TEST;
		else
			servletURL = HTTP_URL;

		if (sReq != null) {
			String sRes = xmlWsClient.submitRequest(sReq, servletURL);
			if (sRes != null) {
//				sRes = DHLXmlDataConverter.customizeResponse(sRes);
				logger.debug(sRes);
				if (sRes.contains("ErrorResponse")) {
					com.meritconinc.shiplinx.carrier.dhl.xml.ShipmentValidateErrorResponse errRes = xmlWsClient.processGlobalShipValidateErrorResponse(sRes);
					if (errRes != null && errRes.getResponse()!=null && errRes.getResponse().getStatus() != null && errRes.getResponse().getStatus().getCondition() != null) {
						String errMsg = DHLXmlWSClient.statusConditionToString(
								errRes.getResponse().getStatus().getCondition());
						logger.debug("ErrorResponse:" + errMsg);
						throw new DHLException(errMsg);
					}
				} else {
					// Valid Response
					ShipmentResponse res = xmlWsClient.processGlobalResponse(sRes);
					logger.debug("ShipmentResponse:" + res);
					
					DhlResponseHandler dhlResponseHandler = new DhlResponseHandler();
					DhlShipValidateResponse dhlShipValRes = dhlResponseHandler.getShipValidateResponse(res);
					dhlShipValRes.setOrderId(order.getId());
					order.setMasterTrackingNum(dhlShipValRes.getAirwayBillNumber());
					if (dhlShipValRes.getChargeableWeight() != null && dhlShipValRes.getChargeableWeight().floatValue()>order.getQuotedWeight())
						order.setQuotedWeight(new Float(dhlShipValRes.getChargeableWeight().doubleValue()));
					for(Package p:order.getPackages()){
						p.setTrackingNumber(dhlShipValRes.getAirwayBillNumber());		
					}
					
					try {
						// persist DhlShipValidateResponse
						this.dhlCanadaTariffDAO.addShipValidateResponse(dhlShipValRes);
					} catch (Exception e) {
						logger.error(e);
						throw new DHLException(e.getMessage());
					}
					
					List<ShippingLabel> labels = new ArrayList<ShippingLabel>();
					for(LabelImage li: res.getLabelImage()){
						try {
							ShippingLabel label = new ShippingLabel();
							label.setLabel(li.getOutputImage());
							label.setLabelType(ShiplinxConstants.LABEL_TYPE_PDF_STRING);							
							label.setTrackingNumber(dhlShipValRes.getAirwayBillNumber());
							labels.add(label);
						}catch(Exception e) {
							logger.error(e);
						}
					}
					order.setLabels(labels);
					// Generate Label
//					dhlResponseHandler.generateLabel(order, dhlShipValRes);
				}
			}
		}
	}

	public boolean cancelOrder(ShippingOrder order, CustomerCarrier customerCarrier) {
		if(true || StringUtil.isEmpty(order.getMasterTrackingNum()))
			return true; //if no tracking # was generated, we can safely treat this shipment as cancelled
		DHLRequestBuilder request_builder = new DHLRequestBuilder();
//		boolean isCanceled=request_builder.createAndSendVoidRequest(order);	
		return false;

	}

	public void requestPickup(Pickup pickup) {
		DHLRequestBuilder request = new DHLRequestBuilder();
		PickupResponse pu_response = request.createAndSendPickupRequest(pickup);
		pickup.setConfirmationNum(String.valueOf(pu_response.getConfirmationNumber()));
		pickup.setCarrierReference(pu_response.getOriginSvcArea());
		
	}

	public boolean cancelPickup(Pickup pickup) {
		DHLRequestBuilder request = new DHLRequestBuilder();
	    return request.createAndSendPickupCancelRequest(pickup);	
	}

	public String getTrackingURL(ShippingOrder o) {
		String url = "http://www.dhl.co.uk/content/gb/en/express/tracking.shtml?brand=DHL&AWB=" + o.getMasterTrackingNum();
		return url;
	}

	public String getLoginURL(ShippingOrder o, CustomerCarrier customerCarrier) {
		if(true)
			return null;
		
		StringBuilder stb = new StringBuilder();
		int shipmentType = getShipmentType(customerCarrier.getCountry(), o.getFromAddress().getCountryCode(), o.getToAddress().getCountryCode());
		if(shipmentType==ShiplinxConstants.SHIPMENT_TYPE_EXPORT)
			stb.append(DHL_CANADA_EXPORT_LINK);
		else
			stb.append(DHL_CANADA_IMPORT_LINK);
		
		return stb.toString();
	}

	public void generateShippingLabel(OutputStream outputStream, long orderId, CustomerCarrier customerCarrier) {
		try {
			List<ShippingLabel> shippingLabels = shippingDAO.getLabelsByOrderId(orderId);
			
			if(shippingLabels == null || shippingLabels.size()==0) //backward compatibility
				generateShippingLabelOld(outputStream, orderId, customerCarrier);
      
			int page = 1;
			PDFRenderer pdfRenderer = new PDFRenderer(); 
			java.util.ArrayList srcList = new ArrayList();
			for(ShippingLabel label:shippingLabels) {
				
				String pdfFileName = pdfRenderer.getUniqueTempPDFFileName("label" + orderId + page);
				File f = new File(pdfFileName);
				BufferedOutputStream sbos = new BufferedOutputStream(new FileOutputStream(f));
				sbos.write(label.getLabel());
				sbos.close();
				srcList.add(pdfFileName);		
				page++;
			}
			// delete temp files
			pdfRenderer.concatPDF(srcList, outputStream);				
			pdfRenderer.deleteFiles(srcList);

		}catch(Exception e) {
			logger.error("DHL label error", e);
//			e.printStackTrace();
			logger.error(e);			
		}

	}

	public void generateShippingLabelOld(OutputStream outputStream, long orderId, CustomerCarrier customerCarrier) {
		logger.debug("Attempting to retrieve shipping label for order with id " + orderId);
		
		try {
			ShippingOrder order = shippingDAO.getShippingOrder(orderId);
			if (order != null) {
				DhlShipValidateResponse shipValRes = this.dhlCanadaTariffDAO.getShipValidateResponse(
												null, order.getMasterTrackingNum());
				if (shipValRes != null) {
					DhlResponseHandler dhlResponseHandler = new DhlResponseHandler();
					dhlResponseHandler.generateLabel(order, shipValRes, outputStream);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		

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

	//for now, all rates for DHL shipments will be CAD/USA currency as per From or To country
	private void setCurrency(List<Rating> rates, ShippingOrder order){
		if(rates==null)
			return;
		for(Rating rate:rates){
			setCurrency(rate, order);
		}
	}

	//for now, all rates for DHL shipments will be CAD/USA currency as per From or To country
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
	
//	public static String getZoneCountry(ShippingOrder shipment) {
//		// TODO Auto-generated method stub
//		if (shipment.getFromAddress() != null && shipment.getFromAddress().getCountryCode() != null) {
//			if (shipment.getCustomerCarrier().getCountry().equalsIgnoreCase(shipment.getFromAddress().getCountryCode()))
//				return shipment.getToAddress().getCountryCode();
//			else
//				return shipment.getFromAddress().getCountryCode();
//		}		
//		return null;
//	}	

//	public static int getShipmentType(ShippingOrder shipment) {
//		
//		String accountCountry = shipment.getCustomerCarrier().getCountry();
//		String fromCountry = shipment.getFromAddress().getCountryCode();
//		String toCountry = shipment.getToAddress().getCountryCode();
//		
//		
//		//this code should simply call the next function  getShipmentType(String accountCountry, String fromCountry, String toCountry)
//		if(!accountCountry.equalsIgnoreCase(fromCountry) && !accountCountry.equalsIgnoreCase(toCountry))
//			return ShiplinxConstants.SHIPMENT_TYPE_THIRD_COUNTRY;
//		if(accountCountry.equalsIgnoreCase(fromCountry) && accountCountry.equalsIgnoreCase(toCountry))
//			return ShiplinxConstants.SHIPMENT_TYPE_DOMESTIC;
//		if(accountCountry.equalsIgnoreCase(fromCountry) && !accountCountry.equalsIgnoreCase(toCountry))
//			return ShiplinxConstants.SHIPMENT_TYPE_EXPORT;
//		 
//		return ShiplinxConstants.SHIPMENT_TYPE_IMPORT;	
//		
//	}	

	public static int getShipmentType(String accountCountry, String fromCountry, String toCountry) {
		
		if(!accountCountry.equalsIgnoreCase(fromCountry) && !accountCountry.equalsIgnoreCase(toCountry))
			return ShiplinxConstants.SHIPMENT_TYPE_THIRD_COUNTRY;
		if(accountCountry.equalsIgnoreCase(fromCountry) && accountCountry.equalsIgnoreCase(toCountry))
			return ShiplinxConstants.SHIPMENT_TYPE_DOMESTIC;
		if(accountCountry.equalsIgnoreCase(fromCountry) && !accountCountry.equalsIgnoreCase(toCountry))
			return ShiplinxConstants.SHIPMENT_TYPE_EXPORT;
		 
		return ShiplinxConstants.SHIPMENT_TYPE_IMPORT;	
		
	}

	@Override
	public void uploadRates(Service service, long customerId, long busId, boolean isOverwrite) {
		// TODO Auto-generated method stub
		
	}	


}
