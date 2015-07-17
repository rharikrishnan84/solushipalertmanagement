package com.meritconinc.shiplinx.carrier.ups.service;

import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.cwsi.eshipper.carrier.ups.rate.RatingServiceSelectionResponseDocument;
import com.cwsi.eshipper.carrier.ups.track.ActivityDocument.Activity;
import com.cwsi.eshipper.carrier.ups.track.ShipmentDocument.Shipment;
import com.cwsi.eshipper.carrier.ups.track.TrackResponseDocument.TrackResponse;
import com.lowagie.text.pdf.PdfWriter;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.ups.dao.UPSCanadaTariffDAO;
import com.meritconinc.shiplinx.carrier.ups.model.UPSAccCharges;
import com.meritconinc.shiplinx.carrier.ups.model.UPSCANEAS;
import com.meritconinc.shiplinx.carrier.ups.model.UPSDASZipsUS;
import com.meritconinc.shiplinx.carrier.ups.stub.PickupRequestWebServiceClient;
import com.meritconinc.shiplinx.carrier.ups.stub.ShipmentRequestWebServiceClient;
import com.meritconinc.shiplinx.carrier.ups.stub.TimeInTransitRequestWebServiceClient;
import com.meritconinc.shiplinx.carrier.ups.stub.VoidRequestWebServiceClient;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCancelResponse;
import com.meritconinc.shiplinx.carrier.ups.ws.pickup.proxy.PickupCreationResponse;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ImageType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.PackageResultsType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipmentRequest;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipmentResponse;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.ServiceSummaryType;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.TimeInTransitRequest;
import com.meritconinc.shiplinx.carrier.ups.ws.tnt.proxy.TimeInTransitResponse;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.VoidShipmentRequest;
import com.meritconinc.shiplinx.carrier.ups.ws.voidws.proxy.VoidShipmentResponse;
import com.meritconinc.shiplinx.carrier.utils.UPSException;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.FuelSurcharge;
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
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class UPSAPI implements CarrierService {

	private static final Logger log = Logger.getLogger(UPSAPI.class);

	public static final String LIVE_URL_RATE = "https://www.ups.com/ups.app/xml/Rate";
	public static final String TEST_URL_RATE = "https://wwwcie.ups.com/ups.app/xml/Rate";
	public static final String LIVE_URL_TRACK = "https://www.ups.com/ups.app/xml/Track";
	public static final String TEST_URL_TRACK = "https://wwwcie.ups.com/ups.app/xml/Track";
	public static final String LIVE_URL_VOID = "https://www.ups.com/ups.app/xml/Void";
	public static final String TEST_URL_VOID = "https://wwwcie.ups.com/ups.app/xml/Void";
	public static final String LIVE_URL_TIMEINTRANSIT = "https://onlinetools.ups.com/ups.app/xml/TimeInTransit";
	public static final String TEST_URL_TIMEINTRANSIT = "https://wwwcie.ups.com/ups.app/xml/TimeInTransit";
	
	public static final String TEST_URL_WS_SHIP = "https://wwwcie.ups.com/webservices/Ship";
	public static final String LIVE_URL_WS_SHIP = "https://onlinetools.ups.com/webservices/Ship";
	public static final String TEST_URL_WS_TNT = "https://wwwcie.ups.com/webservices/TimeInTransit";
	public static final String LIVE_URL_WS_TNT = "https://onlinetools.ups.com/webservices/TimeInTransit";
	public static final String TEST_URL_WS_VOID = "https://wwwcie.ups.com/webservices/Void";
	public static final String LIVE_URL_WS_VOID = "https://onlinetools.ups.com/webservices/Void";
	public static final String TEST_URL_WS_PICKUP = "https://wwwcie.ups.com/webservices/Pickup";
	public static final String LIVE_URL_WS_PICKUP = "https://onlinetools.ups.com/webservices/Pickup"; 
	
	
	public static final int REQUEST_TYPE_RATE = 1;
	public static final int REQUEST_TYPE_SHIP = 2; 
	public static final int REQUEST_TYPE_TRACK = 3;
	public static final int REQUEST_TYPE_VOID = 4;
	public static final int REQUEST_TYPE_TIMEINTRANSIT = 5;

	public static final String CHARGE_CODE_LEVEL1_ACC = "ACC";
	public static final String CHARGE_CODE_LEVEL1_FRT = "FRT";
	public static final String CHARGE_CODE_LEVEL1_FSC = "FSC";
	
	public static final String CHARGE_CODE_LEVEL2_DVS = "DVS";
	public static final String CHARGE_CODE_LEVEL2_EVS = "EVS";
	public static final String CHARGE_CODE_LEVEL2_RES = "RES";
	public static final String CHARGE_CODE_LEVEL2_DCR = "DCR";
	public static final String CHARGE_CODE_LEVEL2_DCS = "DCS";
	public static final String CHARGE_CODE_LEVEL2_ADS = "ADS";
	public static final String CHARGE_CODE_LEVEL2_SAT = "SAT";
	public static final String CHARGE_CODE_LEVEL2_ESD = "ESD";
	public static final String CHARGE_CODE_LEVEL2_EDS = "EDS";
	public static final String CHARGE_CODE_LEVEL2_RDR = "RDR";
	public static final String CHARGE_CODE_LEVEL2_RDC = "RDC";
	public static final String CHARGE_CODE_LEVEL2_LDR = "LDR";
	public static final String CHARGE_CODE_LEVEL2_LDC = "LDC";
	public static final String CHARGE_CODE_LEVEL2_AHC = "AHC";
	public static final String CHARGE_CODE_LEVEL2_LPS = "LPS";
	public static final String CHARGE_CODE_LEVEL2_COD = "COD";
	public static final String CHARGE_CODE_LEVEL2_FSC = "FSC";
	public static final String CHARGE_CODE_LEVEL2_OTH = "OTH";
	
	private static final double EAS_CAN_INTL_PER_POUND = 0.35;
	private static final double EAS_CAN_INTL_MIN = 35;

	public static final String UNIT_INCHES_STRING = "IN";
	public static final String UNIT_LBS_STRING = "LBS";
	public static final String UNIT_KGS_STRING = "KGS";

//	private static Map transitCodeServiceMap = null;
//	static{
//		transitCodeServiceMap = new HashMap();
//		transitCodeServiceMap.put("1DMS", new Long(200));
//		transitCodeServiceMap.put("1DAS", new Long(200));
//		transitCodeServiceMap.put("1DM", new Long(205));
//		transitCodeServiceMap.put("1DA", new Long(200));
//		transitCodeServiceMap.put("1DP", new Long(209));
//		transitCodeServiceMap.put("2DM", new Long(211));
//		transitCodeServiceMap.put("2DA", new Long(201));
//		transitCodeServiceMap.put("3DS", new Long(206));
//		transitCodeServiceMap.put("GND", new Long(208));
//		transitCodeServiceMap.put("01", new Long(200));
//		transitCodeServiceMap.put("02", new Long(201));
//		transitCodeServiceMap.put("03", new Long(204));
//		transitCodeServiceMap.put("05", new Long(203));
//		transitCodeServiceMap.put("06", new Long(202));
//		transitCodeServiceMap.put("08", new Long(204));		
//		transitCodeServiceMap.put("09", new Long(202));
//		transitCodeServiceMap.put("10", new Long(200));
//		transitCodeServiceMap.put("18", new Long(207));
//		transitCodeServiceMap.put("19", new Long(201));
//		transitCodeServiceMap.put("20", new Long(207));
//		transitCodeServiceMap.put("21", new Long(210));
//		transitCodeServiceMap.put("22", new Long(205));
//		transitCodeServiceMap.put("23", new Long(210));
//		transitCodeServiceMap.put("24", new Long(200));
//		transitCodeServiceMap.put("25", new Long(204));
//		transitCodeServiceMap.put("26", new Long(207));
//		transitCodeServiceMap.put("33", new Long(206));
//		transitCodeServiceMap.put("28", new Long(206));
//		
//	}


	protected static final int DEFAULT_DAYS_FOR_DELIVERY = 0;

	private List<CarrierErrorMessage> errorMessages = new ArrayList();

	private static final String SERVICE_TYPE_AIR = "Air";
	private static final String SERVICE_TYPE_GROUND = "Ground";

	private CustomerManager customerManager;
	private ShippingDAO shippingDAO;
	private CarrierServiceDAO carrierServiceDAO;
	private ShippingService shippingService;
	private FuelSurchargeService fuelSurchargeService = null;
	private UPSCanadaTariffDAO upsCanadaTariffDAO = null;

	private static HashMap ups_services = null;
	

	public void setUpsCanadaTariffDAO(UPSCanadaTariffDAO upsCanadaTariffDAO) {
		this.upsCanadaTariffDAO = upsCanadaTariffDAO;
	}

	public void setShippingDAO(ShippingDAO dao) {
		this.shippingDAO = dao;
	}

	public void setShippingService(ShippingService mgr) {
		this.shippingService = mgr;
	}

	public void setCustomerManager(CustomerManager mgr) {
		this.customerManager = mgr;
	}

	
	public long getCarrierId() {
		return ShiplinxConstants.CARRIER_UPS;
	}

	public List<Rating> rateShipment(ShippingOrder order, List<Service> services, long carrierId, CustomerCarrier customerCarrier) {

//		if(order.getPackageTypeId().getPackageTypeId()==ShiplinxConstants.PACKAGE_TYPE_PALLET){
//			return null;
//		}
		ShiplinxConstants.setServices(services);
		order.setFromRatingList(new ArrayList<Rating>());
		order.setToRatingList(new ArrayList<Rating>());
		log.debug("----------rateShipment-- UPS---"+services.size());
		List<Rating> rates = new ArrayList<Rating>();
		List<Rating> rateList = new ArrayList<Rating>();

		try{
			rates = rateShipmentByService(order, null, carrierId, customerCarrier);
			if (rates != null)
				rateList.addAll(rates);
		}catch (UPSException e) {
			log.debug("--UPSException-- UPS---",e);
			throw e;
		}
		catch (Exception e) {
			log.debug("--UPSException-- UPS---",e);
			throw new UPSException(e.getMessage());
		}

		return rateList;
	}


	private List<Rating> rateShipmentByService(ShippingOrder order, Long serviceId, long carrierId, CustomerCarrier customerCarrier) throws UPSException{
		log.debug("----------rateShipmentByService-- UPS---"+order);
		log.debug("-----------order.getFromAddress().getCountryCode()-------------"+order.getFromAddress().getCountryCode());
		
		long start = System.currentTimeMillis();
		List<Rating> rateList = new ArrayList<Rating>();		

//		if(order.getPackageTypeId().getName().equalsIgnoreCase(ShiplinxConstants.PACKAGE_TYPE_PALLET_STRING)) {
//			log.debug("Pallet package type is not supported");
//			return null;
//		}

		if(order.getDangerousGoods()!=null && order.getDangerousGoods()!=0){
			log.debug("Dangerous Goods is not supported");
			return null;
		}

		if(!(order.getFromAddress().getCountryCode().equals(ShiplinxConstants.CANADA) ||
				order.getFromAddress().getCountryCode().equals(ShiplinxConstants.US))) {
			log.info("ship from country needs to be Canada or US");
			return null;
		}		


//		if(order.getFromAddress().getCountryCode().equals(ShiplinxConstants.CANADA) && order.getCODValue() > 0)
//			return null;

		log.debug("order.getSignatureRequired():::"+order.getSignatureRequired());
		if(order.getCODValue() > 0 && order.getSignatureRequired() != 1 ) {
			throw new UPSException("COD service not allowed with signature required service.");
		}

		UPSRequestBuilder request_builder = new UPSRequestBuilder();
		RatingServiceSelectionResponseDocument response = null;

		try{
			if(serviceId==null)
				response = request_builder.createAndSendRateRequest(order, null, customerCarrier);
			else{
				Service s = new Service();
				s.setId(order.getServiceId());
				s.setCarrierId(carrierId);
				s = upsCanadaTariffDAO.getServiceCodeByService(s);
				response = request_builder.createAndSendRateRequest(order, s.getCode(), customerCarrier);
			}

			if(response == null) 
				return null;

			//log.debug("-------UPS--response------------"+response);

			UPSResponseHelper response_helper = new UPSResponseHelper();				
			Map<Long,Rating> rates_map = response_helper.extractRates(response, order, upsCanadaTariffDAO, carrierId);

			if(rates_map==null || rates_map.size()==0)
				return null;

			TimeInTransitRequestWebServiceClient transit_request = new TimeInTransitRequestWebServiceClient(order, customerCarrier);
			TimeInTransitRequest request = transit_request.buildRequest();
			TimeInTransitResponse tnt_response = transit_request.sendRequest();
			
			if(!tnt_response.getResponse().getResponseStatus().getCode().equalsIgnoreCase("1")){
				//not successful, error was returned.
				log.error("UPS TimeInTransit Request failed!");
			}

			//get the return rates
			List<Service> carrierServicesList = carrierServiceDAO.getServicesForCarrier(carrierId);

			if(tnt_response!=null && tnt_response.getTransitResponse()!=null && tnt_response.getTransitResponse().getServiceSummary()!=null){
				List<ServiceSummaryType> services =  tnt_response.getTransitResponse().getServiceSummary();
				for(ServiceSummaryType service: services){
					String transitCodeReturned = service.getService().getCode();
					
					//hard coding, from Canada Standard transit code is 25 for domestic and 03 to US. Need to code this correctly
					if(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) && order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) && transitCodeReturned.equalsIgnoreCase("25"))
						transitCodeReturned = new String("03");
					
					Service s = carrierServiceDAO.getServiceByCarrierIdAndTransitCode(carrierId, transitCodeReturned);
					if(s==null)
						continue;
					
						log.info(service.getService().getCode() + " / " + s.getId());
					Rating rate = rates_map.get(s.getId());
					if(rate!=null && rate.getTransitDays()==0){//if it was returned in the rating request we use that value
						String transitDays = service.getEstimatedArrival().getBusinessDaysInTransit();
						if(transitDays!=null ) 
							rate.setTransitDays(Integer.valueOf(transitDays));
					}
				}
			}
			

			
			for(Service s : carrierServicesList){
				if(rates_map.get(s.getId())== null)
					continue;
				else{
					Rating rate = rates_map.get(s.getId());
					rate.setCarrierName(ShiplinxConstants.CARRIER_UPS_STRING);
					rateList.add(rate);
					
				}
			}

			breakdownRates(rateList, order, tnt_response, customerCarrier);
//			applyCharges(rateList,order);
			setCurrency(rateList, order, customerCarrier);

		}
		catch(UPSException upse){
			throw upse;
		}
		catch (Exception e) {
			log.debug("--UPSException-- UPS---",e);
			throw new UPSException("Unable to get shipping rates");
		}

		return rateList;
	}


	public Object isDelivered(ShippingOrder o) {
		// TODO Auto-generated method stub
		return null;
	}

	public void checkForShipmentStatusUpdates() {

		log.debug("checkForShipmentStatusUpdates...");
		
		/*String instanceName = propertyDAO.readProperty(ShiplinxConstants.SYSTEM_SCOPE, ShiplinxConstants.EXECUTE_QUARTZ_JOBS_TO_WEB_SERVER);
		if(!ShiplinxConstants.isValidServerToExecuteJob(instanceName)){
			return;
		}*/
		
		log.debug("Starting UPS status update...");
		
		/*OrderSearchBean search = new OrderSearchBean();
		Integer[] statusList = new Integer[2];
		statusList[0] = new Integer(ShiplinxConstants.STATUS_DISPATCHED);
		statusList[1] = new Integer(ShiplinxConstants.STATUS_INTRANSIT);
		
		search.setCarrierId(this.getCarrierId());
		search.setStatusList(statusList);
		search.setDateRange("month");
		search.calculateDate();
		*/
		List<ShippingOrder> orders = shippingService.search(this.getCarrierId() );
		if(orders==null || orders.size()==0){
			log.info(("No UPS orders to update status!"));
			return;
		}
		log.info(orders.size()  + " orders to update!");
		
		UPSRequestBuilder request_builder = new UPSRequestBuilder();
		
		for(ShippingOrder or: orders){
			log.debug("ShippingOrder-----id ------"+or.getId());
			log.debug("ShippingOrder-----isLive ------"+or.isLive());
			log.debug("---getCustomerId ------"+or.getCustomerId());
			log.debug("---getFromCountry ------"+or.getFromCountry());
			log.debug("getServiceName::"+or.getServiceName()+"--getServiceId::"+or.getServiceId());
			log.debug("getCarrierName::"+or.getCarrierName()+"--getCarrierId::"+or.getCarrierId());
			
			//get the customer for this combination and set to the order
			
			ShippingOrder order = shippingService.getShippingOrder(or.getId()); 
			CustomerCarrier customerCarrier = carrierServiceDAO.getDefAccountByCountry(getCarrierId(),
					or.getCustomerId(), or.getFromCountry());
			log.debug("customerCarrier ------"+customerCarrier.getCarrierAccountId());
			
//			order.setCustomerCarrier(customerCarrier);
			
			log.debug("---getAccountNumber ------"+customerCarrier.getAccountNumber1());
			log.debug("---getMeterNumber ------"+customerCarrier.getProperty1());
			log.debug("---getAccountKey ------"+customerCarrier.getProperty2());
			log.debug("---getAccountPassword ------"+customerCarrier.getProperty3());
//			log.debug("---getUpsUserName ------"+customerCarrier.getUpsUserName());
//			log.debug("---getTestaccountKey ------"+customerCarrier.getTestaccountKey());
//			log.debug("---getTestaccountNumber ------"+customerCarrier.getTestaccountNumber());
//			log.debug("---getTestupsUserName ------"+customerCarrier.getTestupsUserName());
			
			TrackResponse response = null;
			try{
				response = request_builder.createAndSendTrackRequest(order, customerCarrier);
				log.debug("----00----");
				Shipment shipment = response.getShipmentList().get(0);
				
				List<com.cwsi.eshipper.carrier.ups.track.PackageDocument.Package> packages = shipment.getPackageList();
				log.debug("----111----");
				String comment;
				String pod;
				String date_time;
				Date dateTimePOD;
				
				boolean breakToNextOrder = false;
				
				//only looking at master package
				com.cwsi.eshipper.carrier.ups.track.PackageDocument.Package p = packages.get(0);
				log.debug("----22----");
				List<Activity> act_list = p.getActivityList();
				log.debug("act_list ------"+act_list);
				for(Activity a: act_list){
					
					if(breakToNextOrder)
						break;
					
					String code = a.getStatus().getStatusType().getCode();
					
					log.debug("------code-------- ::"+code);
					if(code.equalsIgnoreCase("D")){
						pod = a.getActivityLocation().getSignedForByName();
						if(pod==null)
							pod = a.getActivityLocation().getDescription();
						date_time = a.getDate() + a.getTime();
						dateTimePOD = FormattingUtil.getDate(date_time, FormattingUtil.DATE_FORMAT_YYYYMMDDHHMMSS);							
						comment = "POD : " + pod + " on " + FormattingUtil.getFormattedDate(dateTimePOD, FormattingUtil.DATE_FORMAT_DDMMMYY_HHMM);
						order.setProofOfDelivery(pod);
						order.setDateOfDelivery(dateTimePOD);
						//orderManager.removeFromSession(order);
						shippingService.save(order);
						shippingService.updateStatus(new Long(0), new Long(order.getId()), ShiplinxConstants.STATUS_DELIVERED, comment);
						breakToNextOrder = true;
						continue;
					}	
					else if(order.getStatusId()==ShiplinxConstants.STATUS_INTRANSIT){ //already in transit...no need to update
						breakToNextOrder = true;
						continue;
					}
					else if(code.equalsIgnoreCase("P")){
						shippingService.updateStatus(new Long(0), new Long(order.getId()), ShiplinxConstants.STATUS_INTRANSIT, "Order picked up by UPS");
						breakToNextOrder = true;
						continue;
					}
					else if(code.equalsIgnoreCase("I")){ 
						shippingService.updateStatus(new Long(0), new Long(order.getId()), ShiplinxConstants.STATUS_INTRANSIT, "Shipment is on the way");
						breakToNextOrder = true;
						continue;
					}

					log.debug("----99----");
				}					
			}
			
			catch(Exception e){
				e.printStackTrace();
				log.debug("------eee-----"+e);
				log.debug("Could not perform track operation for order with id " + order.getId());
				continue;
			}	
		}	
	
	}



	/**
	 * Ship Order
	 */
	public void shipOrder(ShippingOrder order, Rating rateInfo, CustomerCarrier customerCarrier) throws UPSException{

		log.debug("---shipOrder----UPS-----");

		Service service = new Service();
		service.setId(order.getServiceId());
		service.setCarrierId(order.getCarrierId());
		service = upsCanadaTariffDAO.getServiceCodeByService(service);

		String serviceCode = service.getCode();
		
		ShipmentRequestWebServiceClient shipment_request = new ShipmentRequestWebServiceClient(order, customerCarrier);
		ShipmentRequest request = shipment_request.buildRequest();
		ShipmentResponse response = shipment_request.sendRequest();
		
		if(!response.getResponse().getResponseStatus().getCode().equalsIgnoreCase("1")){
			//not successful, error was returned.
			throw new UPSException();
		}

		List<ShippingLabel> labels = new ArrayList<ShippingLabel>();
		
		List<PackageResultsType> prt = response.getShipmentResults().getPackageResults();
		order.setMasterTrackingNum(response.getShipmentResults().getShipmentIdentificationNumber());
		int i =0;
		for(PackageResultsType p: prt){
			String image = p.getShippingLabel().getGraphicImage();
			String html = p.getShippingLabel().getHTMLImage();

			String tracking_number = p.getTrackingNumber();
			order.getPackages().get(i).setTrackingNumber(tracking_number);
			
			byte[] imageBytes = Base64.decodeBase64(image.getBytes());
			byte[] htmlBytes = Base64.decodeBase64(html.getBytes());
			
			ShippingLabel label = new ShippingLabel();
			label.setLabel(imageBytes);
			label.setHtml(htmlBytes);
			label.setTrackingNumber(tracking_number);
			label.setLabelType(p.getShippingLabel().getImageFormat().getCode());
			labels.add(label);
			
			i++;

		}
		
		if(response.getShipmentResults().getHighValueReport() != null){
			byte[] imageBytes = Base64.decodeBase64(response.getShipmentResults().getHighValueReport().getImage().getGraphicImage().getBytes());
			ShippingLabel label = new ShippingLabel();
			label.setLabel(imageBytes);
			label.setLabelType(response.getShipmentResults().getHighValueReport().getImage().getImageFormat().getCode());
			labels.add(label);
		}
		
		if(response.getShipmentResults().getControlLogReceipt()!=null){
			for(ImageType it: response.getShipmentResults().getControlLogReceipt()){
				byte[] imageBytes = Base64.decodeBase64(it.getGraphicImage().getBytes());
				ShippingLabel label = new ShippingLabel();
				label.setLabel(imageBytes);
				label.setLabelType(it.getImageFormat().getCode());
				labels.add(label);

			}
		}
		
		order.setLabels(labels);
//		order.getCharges().addAll(rateInfo.getCharges());
//		order.setBaseCharge(rateInfo.getBaseCharge());
//		order.setTotalCharge(rateInfo.getTotalCost());
//		order.setFuelCharges(rateInfo.getFuelSurcharge());

	}

	public boolean cancelOrder(ShippingOrder order, CustomerCarrier customerCarrier) {

		if(StringUtil.isEmpty(order.getMasterTrackingNum()))
			return true; //if no tracking # was generated, we can safely treat this shipment as cancelled

		VoidRequestWebServiceClient void_request = new VoidRequestWebServiceClient(order, customerCarrier);
		VoidShipmentRequest request = void_request.buildRequest();
		VoidShipmentResponse response = void_request.sendRequest();
		
		if(!response.getResponse().getResponseStatus().getCode().equalsIgnoreCase("1")){
			//not successful, error was returned.
			return false;
		}
		return true;

	}

	public void requestPickup(Pickup pickup) throws UPSException{

		PickupRequestWebServiceClient pickup_request = new PickupRequestWebServiceClient(pickup);
		pickup_request.buildRequest();
		PickupCreationResponse response = pickup_request.sendRequest();
		
		if(!response.getResponse().getResponseStatus().getCode().equalsIgnoreCase("1")){
			//not successful, error was returned.
			return;
		}
		
		pickup.setConfirmationNum(response.getPRN());
		
	}
	
	public boolean cancelPickup(Pickup pickup) throws UPSException{

		PickupRequestWebServiceClient pickup_request = new PickupRequestWebServiceClient(pickup);
		pickup_request.buildCancelRequest();
		PickupCancelResponse response = pickup_request.sendCancelRequest();
		
		if(!response.getResponse().getResponseStatus().getCode().equalsIgnoreCase("1")){
			//not successful, error was returned.
			return false;
		}
		
		return true;
	}

	public String getTrackingURL(ShippingOrder o) {
		StringBuilder stb = new StringBuilder();
		stb.append("http://wwwapps.ups.com/etracking/tracking.cgi?tracknums_displayed=25&TypeOfInquiryNumber=T&HTMLVersion=4.0&");
		
		stb.append("InquiryNumber=" + o.getMasterTrackingNum());
			
		return stb.toString();
	}

	public String getLoginURL(ShippingOrder o) {
		
		if(true)
			return null;
		StringBuilder stb = new StringBuilder();
		stb.append("https://www.campusship.ups.com/login");
		
		return stb.toString();
	}

	public void generateShippingLabel(OutputStream outputStream, long orderId, CustomerCarrier customerCarrier) {
		log.debug("Attempting to retrieve shipping label for order with id " + orderId);
		//order = orderDAO.getOrder(orderId);
		long start = System.currentTimeMillis();	
		try{
			UPSLabelGenerator label_gen = new UPSLabelGenerator();
			label_gen.setShippingLabelDAO(this.shippingDAO);
			label_gen.generateShippingLabel(shippingDAO.getShippingOrder(orderId), outputStream);
		}catch (Exception e) {
			//e.printStackTrace();
			log.debug("--------"+e);

		}
		long end = System.currentTimeMillis();
		log.debug("Time (in ms) to generate shipping label: " + (end-start));

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
		//order = orderDAO.getOrder(orderId);
		long start = System.currentTimeMillis();	

		UPSLabelGenerator label_gen = new UPSLabelGenerator();

		long end = System.currentTimeMillis();
	}



	public List<CarrierErrorMessage> getErrorMessages(){
		return errorMessages;
	}

	public static String getUPSPackageCode(int packageId){

		switch(packageId){
			case 1: return "01";
			case 2: return "04";
			case 3: return "02";
//			case 4: return "30";
			case 4: return "02";
		}
		return "02";

	}

//	private void applyCharges(List<Rating> rates, ShippingOrder order){
//		for(Rating r: rates){
//
//			double baseCharge = r.getBaseCharge();
//
//			//first calculate cost to CWW
//			double totalCost = 0.0;
//			totalCost += baseCharge;
//			double totalSurcharges = 0.0;
//
//			for(Charge s:r.getCharges()){
//				totalCost += s.getCost();
//				totalSurcharges += s.getCost();
//			}
//			
//			r.setBaseCharge(r.getBaseCharge()); //these are the charges to be shown to the customer
//			r.setTotalCost(roundFigureRates(totalCost,2));
//			r.setTotalSurcharge(totalSurcharges);
//
//		}
//	}

	private void breakdownRates(List<Rating> rates, ShippingOrder order, TimeInTransitResponse tnt_response, CustomerCarrier customerCarrier){
		log.debug("--------------breakdownRates------------------"+order);
		//delivery area surcharge for the US 
		String deliverySurchargeCode = getDeliveryAreaSurcharge(order, rates.get(0).isUps_switch_res_comm(), rates.get(0).isUps_extended_area(), customerCarrier);
//		double areaSurcharge = 0, areaSurchargeCost=0;
//		if(deliverySurchargeCode != null) {
//			UPSAccCharges upsAccCharges = getAccCharge(order,CHARGE_CODE_LEVEL1_ACC, deliverySurchargeCode);
//			areaSurcharge = upsAccCharges.getCharge();
//			areaSurchargeCost = upsAccCharges.getCost();
//			if(upsAccCharges.isPerPackage()) {
//				areaSurcharge = areaSurcharge * order.getQuantity();
//				areaSurchargeCost = areaSurchargeCost * order.getQuantity();
//			}
//		}		
		
		FuelSurcharge air = null;
		FuelSurcharge ground = null;
		
		FuelSurcharge f = new FuelSurcharge();
		f.setCarrierId(rates.get(0).getCarrierId());
		f.setType(SERVICE_TYPE_AIR);
		f.setFromCountry(order.getFromAddress().getCountryCode());
		List<FuelSurcharge> fuelsurcharges = fuelSurchargeService.getFuelSurcharge(f);		
		air = fuelsurcharges.get(0);
		
		f.setType(SERVICE_TYPE_GROUND);
		f.setToCountry(order.getToAddress().getCountryCode());
		fuelsurcharges = fuelSurchargeService.getFuelSurcharge(f);		
		if(fuelsurcharges!=null && fuelsurcharges.size()>0)
			ground = fuelsurcharges.get(0);

		try{
			for(Rating rate: rates){
				
				rate.setLoginURL(this.getLoginURL(order));

				Charge charge = new Charge();
				rate.setSchedPickup(true);

				rate.setInstanceAPIName(this.toString());
				rate.setCustomerCarrier(customerCarrier); //remember the account that was used to rate, we will use the same one at shipping time
				rate.setCarrierName(ShiplinxConstants.CARRIER_UPS_STRING);
				Service service = carrierServiceDAO.getService(rate.getServiceId());

				rate.setServiceId(service.getId());
				rate.setServiceName(service.getName()); 
				
				//
				

				double fuel_perc = 0;
				double baseTariff = rate.getTariffRate()/(1+(fuel_perc/100));
				double fuelTariff = rate.getTariffRate() - baseTariff;

				double baseCost = rate.getBaseCharge()/(1+(fuel_perc/100));
				double fuelCost = rate.getBaseCharge() - baseCost;
				
				rate.setFuelSurcharge(0.0);
				rate.setTariffFuelSurcharge(0.0);
				
				try{
					log.debug("service.getMode()---"+service.getMode());
					if(service.getMode()!= null && service.getMode() == ShiplinxConstants.MODE_TRANSPORT_AIR_VALUE){
						fuel_perc = air.getValue();
					}else{
						fuel_perc = ground.getValue();
					}
				}catch (Exception e) {
					log.error("----------Error in ----breakdownRates------------------"+e);

				}

				double balance_to_charge = rate.getServiceOptionsCharges();// + rate.getHandlingCharges();

				rate.setFuel_perc(fuel_perc);

				//delivery/extended surcharges
				if(deliverySurchargeCode != null) {
					charge = new com.meritconinc.shiplinx.model.Charge();
					charge.setChargeCode(CHARGE_CODE_LEVEL1_ACC);
					charge.setChargeCodeLevel2(deliverySurchargeCode);
					calcAndAddCharge(order, charge, rate, 0, true, true, customerCarrier);
					//Large package surcharge is embedded in the transportation charges returned in API, need to deduct from tariff accordingly
					
					balance_to_charge -= charge.getCost();
				}

				//insurance
				if(order.getInsuranceValue() > 100){
					charge = new com.meritconinc.shiplinx.model.Charge();
					charge.setChargeCode(CHARGE_CODE_LEVEL1_ACC);
					charge.setChargeCodeLevel2(CHARGE_CODE_LEVEL2_EVS);
					calcAndAddCharge(order, charge, rate, 0, false, false, customerCarrier);
					
//					rate.setBaseCharge(rate.getBaseCharge() - charge.getCost());		
					balance_to_charge -= charge.getCost();
				}
				
				//Residential
				if( order.getToAddress().isResidential()){ 
					log.debug("Attempting to add residential charge");
					charge = new com.meritconinc.shiplinx.model.Charge();
					charge.setChargeCode(CHARGE_CODE_LEVEL1_ACC);
					charge.setChargeCodeLevel2(CHARGE_CODE_LEVEL2_RES);
					calcAndAddCharge(order, charge, rate, 0, true, true, customerCarrier);
					balance_to_charge -= charge.getCost();
				}
				
				//Signature Required
				if(order.getSignatureRequired() > ShiplinxConstants.SIGNATURE_REQUIRED_NO){
					charge = new com.meritconinc.shiplinx.model.Charge();
					charge.setChargeCode(CHARGE_CODE_LEVEL1_ACC);
			
					if(order.getSignatureRequired() == ShiplinxConstants.SIGNATURE_REQUIRED_CONFIRMATION){
						charge.setChargeCodeLevel2(CHARGE_CODE_LEVEL2_DCR);
					}
					if(order.getSignatureRequired() == ShiplinxConstants.SIGNATURE_REQUIRED_SIGNATURE){
						charge.setChargeCodeLevel2(CHARGE_CODE_LEVEL2_DCS);
					}
					if(order.getSignatureRequired() == ShiplinxConstants.SIGNATURE_REQUIRED_ADULT_SIGNATURE){
						charge.setChargeCodeLevel2(CHARGE_CODE_LEVEL2_ADS);
					}
					
					calcAndAddCharge(order, charge, rate, order.getQuantity(), false, false, customerCarrier);
					balance_to_charge -= charge.getCost();
				}

				//Saturday delivery
				if(order.getSatDelivery()){
					charge = new com.meritconinc.shiplinx.model.Charge();
					charge.setChargeCode(CHARGE_CODE_LEVEL1_ACC);
					charge.setChargeCodeLevel2(CHARGE_CODE_LEVEL2_SAT);
					calcAndAddCharge(order, charge, rate, 0, false, false, customerCarrier);
					balance_to_charge -= charge.getCost();
				}

				//Large Packages
				if(rate.getLargePackageSurcharges() > 0) {
					charge = new com.meritconinc.shiplinx.model.Charge();
					charge.setChargeCode(CHARGE_CODE_LEVEL1_ACC);
					charge.setChargeCodeLevel2(CHARGE_CODE_LEVEL2_LPS);
					calcAndAddCharge(order, charge, rate, (int)rate.getLargePackageSurcharges(), true, true, customerCarrier);
					//Large package surcharge is embedded in the transportation charges returned in API, need to deduct from tariff accordingly
					
					balance_to_charge -= charge.getCost();
				}
				
				//Additional Handling
				if(rate.getHandlingCharges() > 0) {
					charge = new com.meritconinc.shiplinx.model.Charge();
					charge.setChargeCode(CHARGE_CODE_LEVEL1_ACC);
					charge.setChargeCodeLevel2(CHARGE_CODE_LEVEL2_AHC);
					calcAndAddCharge(order, charge, rate, (int)rate.getHandlingCharges(), false, false, customerCarrier); //additional handling is not returned as part of 'transportation charges' (in Canada at least) and is not fuel applicable
					balance_to_charge -= charge.getCost();
				}

				//COD
				//Determine how many packages require COD
				int cod_units=0;
				for(com.meritconinc.shiplinx.model.Package p: order.getPackages()){
					if(p.getCodAmount().floatValue() > 0)
						cod_units++;
				}
				if( cod_units > 0 ){					
					charge = new com.meritconinc.shiplinx.model.Charge();
					charge.setChargeCode(CHARGE_CODE_LEVEL1_ACC);
					charge.setChargeCodeLevel2(CHARGE_CODE_LEVEL2_COD);
					calcAndAddCharge(order, charge, rate, cod_units, false, false, customerCarrier);
					balance_to_charge -= charge.getCost();
				}

				//transportation charges contain base and fuel, need to break this up
				double base_tariff = rate.getTariffRate()/(1+(fuel_perc/100));
				double fuel_tariff = rate.getTariffRate() - base_tariff;
				double base_cost = rate.getBaseCharge()/(1+(fuel_perc/100));
				double fuel_cost = rate.getBaseCharge() - base_cost;

				charge = new com.meritconinc.shiplinx.model.Charge();
				charge.setName(ShiplinxConstants.FUEL_SURCHARGE);
				charge.setChargeCode(CHARGE_CODE_LEVEL1_FSC);
				charge.setChargeCodeLevel2(CHARGE_CODE_LEVEL2_FSC);
				charge.setCost((FormattingUtil.add(fuel_cost, rate.getFuelSurcharge()).doubleValue()));
				charge.setCharge((FormattingUtil.add(fuel_tariff, rate.getTariffFuelSurcharge()).doubleValue()));
				charge.setTariffRate((FormattingUtil.add(fuel_tariff, rate.getTariffFuelSurcharge()).doubleValue()));
				charge.setStaticAmount(rate.getStaticFuel());
				rate.getCharges().add(charge);
				rate.setTotalSurcharge(getFormattedValue(rate.getTotalSurcharge()+charge.getCharge()));
				
				charge = new com.meritconinc.shiplinx.model.Charge();
				charge.setName(ShiplinxConstants.FREIGHT_STRING);
				charge.setChargeCode(CHARGE_CODE_LEVEL1_FRT);
				charge.setCost(base_cost);
				charge.setCharge(base_tariff);
				charge.setTariffRate(base_tariff);
				rate.getCharges().add(charge);

				
				if(balance_to_charge > 0){
					log.debug("Still have $" + balance_to_charge + " charges left over, adding it to \"other\" charges for now!");				
					charge = new com.meritconinc.shiplinx.model.Charge();
					charge.setChargeCode(CHARGE_CODE_LEVEL1_ACC);
					charge.setChargeCodeLevel2(CHARGE_CODE_LEVEL2_OTH);
					charge.setCost(balance_to_charge);
					charge.setCharge(balance_to_charge);
					charge.setTariffRate(balance_to_charge);

					rate.getCharges().add(charge);
					rate.setTotalSurcharge(getFormattedValue(rate.getTotalSurcharge()+charge.getCharge()));
				}

				//Change order of charges to show freight first and fuel last
				
				/*double totalSurcharges = 0.0;

				for(Charge s:rate.getCharges()){
					totalSurcharges += s.getAmount();
				}

				rate.setTotalCost(fuelCharge.doubleValue() + totalSurcharges);
				rate.setTotal(fuelCharge.doubleValue() + totalSurcharges);*/
			}

		}catch (Exception e) {
			//e.printStackTrace();
			log.error("----------Error in ----breakdownRates------------------",e);

		}
	}

	//for now, all rates for UPS shipments will be CAD/USA currency as per From or To country
	private void setCurrency(List<Rating> rates, ShippingOrder order, CustomerCarrier customerCarrier){
		if(rates==null)
			return;
		for(Rating rate:rates){
			setCurrency(rate, order, customerCarrier);
		}
	}
	//for now, all rates for UPS shipments will be CAD/USA currency as per From or To country
	private void setCurrency(Rating rate, ShippingOrder order, CustomerCarrier customerCarrier){
		if(rate!=null){
			if(customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.CANADA))
				rate.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING);
			else if(customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.US))				
				rate.setCurrency(ShiplinxConstants.CURRENCY_US_STRING);
			else rate.setCurrency(ShiplinxConstants.CURRENCY_CA_STRING); //hard-coded, should be based on response coming back
		}
	}

	private String getDeliveryAreaSurcharge(ShippingOrder order, boolean isUpsSwitchToCom, boolean isExtended, CustomerCarrier customerCarrier) {
		
		boolean ship_to_res = order.getToAddress().isResidential();
		if(!isUpsSwitchToCom)
			ship_to_res = false;

		//we have stored the extended area surcharges for shipments out of Canada in ups_can_eas table
		//Based on analysis of previous EDI files, EDS is for domestic shipments and ESD is for international
		if(customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.CANADA) && isExtended){
			if(order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA))
				return CHARGE_CODE_LEVEL2_EDS;
			else
				return CHARGE_CODE_LEVEL2_ESD;
		}
		
		if(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) && order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US)){
			UPSDASZipsUS uPSDASZipsUS = this.upsCanadaTariffDAO.getUPSDASZipsUSByZipCode(order.getToAddress().getPostalCode());
			if(uPSDASZipsUS != null) {
				if(uPSDASZipsUS.isDas() && ship_to_res) 
					return CHARGE_CODE_LEVEL2_RDR;
				else if(uPSDASZipsUS.isDas() && ship_to_res)
					return CHARGE_CODE_LEVEL2_RDC;				
				else if (uPSDASZipsUS.isDasExtended() && ship_to_res) {
					return CHARGE_CODE_LEVEL2_LDR;
				} else if (uPSDASZipsUS.isDasExtended() && !ship_to_res) {
					return CHARGE_CODE_LEVEL2_LDC;
				} 
//				else if (uPSDASZipsUS.isRemoteHI()) {
//					return upsAccChargesDAO.getAccCharge(franchiseId,DELIVERY_AREA_SURCHARGE_REMOTE_HI, country).getCharge();
//				} else if (uPSDASZipsUS.isRemoteAK()) {
//					return upsAccChargesDAO.getAccCharge(franchiseId,DELIVERY_AREA_SURCHARGE_REMOTE_AK, country).getCharge();
//				}
			}
		}
		return null;
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
	
	private UPSAccCharges getAccCharge(ShippingOrder order,String chargeCode, String chargeCodeLevel2, CustomerCarrier customerCarrier) {
		String country = order.getFromAddress().getCountryCode();
		UPSAccCharges accCharges = new UPSAccCharges(order.getBusinessId(), chargeCode, chargeCodeLevel2, customerCarrier.getCountry());
		return upsCanadaTariffDAO.getAccCharge(accCharges);
	}
	
	private void calcAndAddCharge(ShippingOrder order, Charge chargeObject, Rating rate, int units, boolean deduct_from_tariff, boolean isFuelApplicable, CustomerCarrier customerCarrier){
		double cost = 0;
		double charge = 0;
		double tariff = 0;
		
		if(chargeObject.getChargeCodeLevel2().equalsIgnoreCase(CHARGE_CODE_LEVEL2_EDS) || chargeObject.getChargeCodeLevel2().equalsIgnoreCase(CHARGE_CODE_LEVEL2_ESD)){
			//In this case, get the values of the Extended Area Surcharge (Canada) from ups_can_eas table
			//Assuming no discounts are offered on EAS in Canada, will need to change this code if otherwise
			UPSCANEAS upsCANEAS = upsCanadaTariffDAO.getUPSCANEAS(order.getToAddress().getPostalCode(), order.getToAddress().getCity(), order.getToAddress().getCountryCode());
			if(upsCANEAS==null && !order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US))
				return;
			//If Canada, then take value from record, otherwise it is based on a calculation of $35 per shipment or $0.35 per pound, whichever is greater
			if(upsCANEAS!=null){ //values are stored in the ups_eas_can table
				tariff = upsCANEAS.getCharge();
				cost = upsCANEAS.getCharge();
				charge = upsCANEAS.getCharge();
			}
			else{
				tariff = cost = charge = 30; //easIntl; Hardcoding to $30 as per Carol Qian Aug 4, 2012
			}
		}
		else{ //not EAS Canada, accessorials must be stored in ups_acc_charges table
		
			UPSAccCharges upsAccCharges = getAccCharge(order,chargeObject.getChargeCode(), chargeObject.getChargeCodeLevel2(), customerCarrier);
	
			if(upsAccCharges==null)
				return;
			
			tariff = upsAccCharges.getTariff();
			cost = upsAccCharges.getCost();
			charge = upsAccCharges.getCharge();
			
			//hard-coding COD Canada for now. The rate in the ups_acc_charges table is for domestic Canada, the other possibility is to US
			if(chargeObject.getChargeCodeLevel2().equalsIgnoreCase(CHARGE_CODE_LEVEL2_COD) && customerCarrier.getCountry().equalsIgnoreCase(ShiplinxConstants.CANADA)){
				if(order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US)){
					tariff = 12;
					cost = 12;
					charge = 12;
				}
			}
			
			if(units==0)
				units = order.getQuantity();
			if(upsAccCharges.isPerPackage()) {
				charge = charge * units;
				cost = cost * units;
				tariff = tariff * units;
			}
			
			
			//Currently insurance is the only one that falls in this caregory of charge per units (charge per 100)
			if(upsAccCharges.getPerUnits() > 0){
				units = (int)Math.ceil((order.getInsuranceValue()-100)/100); //insurance is calculated for value over 100, ie first 100 of insurance is free
				charge = charge * (units);
				cost = cost * (units);
				tariff = tariff * (units);

			}
			
		}
		
		chargeObject.setCost(cost);
		chargeObject.setCharge(charge);
		chargeObject.setTariffRate(tariff);

//		rate.setTotalSurcharge(getFormattedValue(rate.getTotalSurcharge()+chargeObject.getCharge()));
//		
//		rate.setBaseCharge(rate.getBaseCharge() - chargeObject.getCost());		
		
		if(tariff > 0 && deduct_from_tariff )
			rate.setTariffRate((FormattingUtil.add(rate.getTariffRate(), tariff*-1)).doubleValue());
		if(cost > 0)
			rate.setBaseCharge((FormattingUtil.add(rate.getBaseCharge(), cost*-1)).doubleValue());
		
		if(isFuelApplicable){//Fuel is applicable on this large package surcharge, Extended Delviery, Residential, etc
			double acc_fuel = tariff*(rate.getFuel_perc()/100);
			rate.setTariffRate((FormattingUtil.add(rate.getTariffRate(), acc_fuel*-1)).doubleValue());
			rate.setTariffFuelSurcharge((FormattingUtil.add(rate.getTariffFuelSurcharge(), acc_fuel)).doubleValue());
			
			//the fuel for this will not be marked up or down
			rate.setStaticFuel((FormattingUtil.add(rate.getStaticFuel(), acc_fuel)).doubleValue());
			
			double acc_fuel_cost = cost*(rate.getFuel_perc()/100);
			rate.setBaseCharge((FormattingUtil.add(rate.getBaseCharge(), acc_fuel_cost*-1)).doubleValue());
			rate.setFuelSurcharge((FormattingUtil.add(rate.getFuelSurcharge(), acc_fuel_cost)).doubleValue());

		}
		
		rate.getCharges().add(chargeObject);

		
	}
	
	
	private double getFormattedValue(double amount){
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(amount));
	}

	@Override
	public void uploadRates(Service service, long customerId, long busId, boolean isOverwrite) {
		// TODO Auto-generated method stub
		
	}



}
