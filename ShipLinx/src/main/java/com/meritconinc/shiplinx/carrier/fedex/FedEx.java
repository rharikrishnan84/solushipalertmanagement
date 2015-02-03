package com.meritconinc.shiplinx.carrier.fedex;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream; 
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.axis.types.NonNegativeInteger;
import org.apache.axis.types.PositiveInteger;
import org.apache.axis.types.Time;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fedex.ship.stub.Dimensions;
import com.fedex.ship.stub.LinearUnits;
import com.fedex.ship.stub.Money;
import com.fedex.ship.stub.NotificationSeverityType;
import com.fedex.ship.stub.ProcessShipmentReply;
import com.fedex.ship.stub.ProcessShipmentRequest;
import com.fedex.ship.stub.TrackingId;
import com.fedex.ship.stub.Weight;
import com.fedex.ship.stub.WeightUnits;
import com.fedex.ws.pickup.v3.Address;
import com.fedex.ws.pickup.v3.BuildingPartCode;
import com.fedex.ws.pickup.v3.CarrierCodeType;
import com.fedex.ws.pickup.v3.ClientDetail;
import com.fedex.ws.pickup.v3.Contact;
import com.fedex.ws.pickup.v3.ContactAndAddress;
import com.fedex.ws.pickup.v3.CountryRelationshipType;
import com.fedex.ws.pickup.v3.CreatePickupReply;
import com.fedex.ws.pickup.v3.CreatePickupRequest;
import com.fedex.ws.pickup.v3.Localization;
import com.fedex.ws.pickup.v3.PickupBuildingLocationType;
import com.fedex.ws.pickup.v3.PickupOriginDetail;
import com.fedex.ws.pickup.v3.TransactionDetail;
import com.fedex.ws.pickup.v3.VersionId;
import com.fedex.ws.pickup.v3.WebAuthenticationDetail;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.utilities.Common;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.WebUtil;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.utils.FedExException;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingLabel;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class FedEx implements CarrierService {
	private static final Logger log = LogManager.getLogger(FedEx.class);
	public  static final String FREIGHT_CHARGE_CODE = "050";
	public  static final String FUEL_CHARGE_CODE = "FUEL";
	private TrackWebServiceClient trackWebServiceClient;
	private ShippingDAO shippingDAO;
	private ShippingService shippingService;
	private CarrierServiceDAO carrierServiceDAO;
	private CustomerManager customerService;
	
	private static final String[] statusTerms = new String[]{"Left", "Arrived", "Scanned", "Vehicle", "Picked"};
	

	public boolean cancelOrder(ShippingOrder order, CustomerCarrier customerCarrier) {
		if(StringUtil.isEmpty(order.getMasterTrackingNum()))
			return true; //if no tracking # was generated, we can safely treat this shipment as cancelled
		CancelPackageWebServiceClient cancelPackageWebServiceClient = new CancelPackageWebServiceClient(order, customerCarrier);
		cancelPackageWebServiceClient.init();
		boolean isOrderCanceled=cancelPackageWebServiceClient.sendRequest();
		return isOrderCanceled;
	}

	public long getCarrierId() {
		return ShiplinxConstants.CARRIER_FEDEX;
	}

	public List<CarrierErrorMessage> getErrorMessages() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getShippingOrderStatus(ShippingOrder order) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTrackingURL(ShippingOrder o) {
		StringBuffer tracking_numbers = new StringBuffer();
		List<Package> packages = o.getPackages();
		
		for(Package p:packages){
			tracking_numbers.append(p.getTrackingNumber());
			tracking_numbers.append(",");
		}
		
		//String url = "http://www.fedex.com/Tracking?ascend_header=1&clienttype=dotcom&cntry_code=us&language=english&tracknumbers=" + tracking_numbers.toString();
		String url = "http://www.fedex.com/Tracking?tracknumbers=" + tracking_numbers.toString();
		return url;
	}

	public List<Rating> rateShipment(ShippingOrder shippingOrder, List<Service> services, long carrierId, CustomerCarrier customerCarrier) {

//		if(shippingOrder.getPackageTypeId().getPackageTypeId()==ShiplinxConstants.PACKAGE_TYPE_PALLET){
//			return null;
//		}
//		log.debug("------------------rateShipment----------------");
		List<Rating> ratingList = new ArrayList<Rating>();
		try{
			RateAvailableWebServiceClient rateAvailableWebServiceClient = new RateAvailableWebServiceClient(shippingOrder,services, customerCarrier);
			rateAvailableWebServiceClient.buildRateRequest();
			if(rateAvailableWebServiceClient!=null){
			ratingList = rateAvailableWebServiceClient.getRatingList();
			}

		}catch (FedExException e) {
			e.printStackTrace();
			throw e;

		}catch (Exception e) {
			e.printStackTrace();
			log.debug(e);
			throw new FedExException(e.getMessage());

		}

		return ratingList;
	}

	public void shipOrder(ShippingOrder order, Rating rate, CustomerCarrier customerCarrier) throws FedExException {
		
		ShipWebServiceClient shipWebServiceClient = new ShipWebServiceClient(order, customerCarrier);
		ProcessShipmentRequest request=shipWebServiceClient.buildRequest();
		List<ShippingLabel> labels = new ArrayList<ShippingLabel>();

		if(order.getPackages().size()>1)
		{
			ProcessShipmentReply reply=shipWebServiceClient.sendRequest(request,"1");
			
			if (!isResponseOk(reply.getHighestSeverity())) // check if the call was successful
			{
				log.debug("ERROR FedEx ShipOrder::"+reply.getNotifications()[0].getMessage());
				throw new FedExException(reply.getNotifications()[0].getMessage());
			}
	
			order.setMasterTrackingNum(reply.getCompletedShipmentDetail().getMasterTrackingId().getTrackingNumber());
			order.getPackages().get(0).setTrackingNumber(reply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getTrackingIds(0).getTrackingNumber());
			order.getPackages().get(0).setLabel(reply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getLabel().getParts(0).getImage());
			order.getPackages().get(0).setLabelInfo(new ShippingLabel());
			order.getPackages().get(0).getLabelInfo().setFormId(reply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getTrackingIds(0).getFormId());
			
			ShippingLabel label = new ShippingLabel();
			label.setLabel(reply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getLabel().getParts(0).getImage());			
			label.setTrackingNumber(reply.getCompletedShipmentDetail().getMasterTrackingId().getTrackingNumber());
			label.setLabelType(ShiplinxConstants.LABEL_TYPE_PNG_STRING);
			labels.add(label);

			
			order.getPackages().get(0).getLabelInfo().setLabelType(ShiplinxConstants.LABEL_TYPE_PNG_STRING);
			
			request.getRequestedShipment().setMasterTrackingId(new TrackingId());
			request.getRequestedShipment().getMasterTrackingId().setFormId(reply.getCompletedShipmentDetail().getMasterTrackingId().getFormId());
			request.getRequestedShipment().getMasterTrackingId().setTrackingNumber(reply.getCompletedShipmentDetail().getMasterTrackingId().getTrackingNumber());
			for(int x=1;x<order.getPackages().size();x++)
			{
				int packageSequence = x+1;
				String sequence= (new Integer(packageSequence)).toString();
				request.getRequestedShipment().getRequestedPackageLineItems(0).setSequenceNumber(new org.apache.axis.types.PositiveInteger(sequence));
				request.getRequestedShipment().getRequestedPackageLineItems(0).setWeight(new Weight(WeightUnits.LB, order.getPackages().get(x).getWeight()));
		    	request.getRequestedShipment().getRequestedPackageLineItems(0).setInsuredValue(new Money(shipWebServiceClient.getCurrency(), order.getPackages().get(x).getInsuranceAmount()));		    
				request.getRequestedShipment().getRequestedPackageLineItems(0).setDimensions(new Dimensions(new NonNegativeInteger(Integer.toString(order.getPackages().get(x).getLength().intValue())), new NonNegativeInteger(Integer.toString(order.getPackages().get(x).getWidth().intValue())), new NonNegativeInteger(Integer.toString(order.getPackages().get(x).getHeight().intValue())), LinearUnits.IN));
				ProcessShipmentReply childReply=shipWebServiceClient.sendRequest(request,sequence);
				if (!isResponseOk(childReply.getHighestSeverity())) // check if the call was successful
				{
					log.info("ERROR FedEx ChildShipOrder::"+childReply.getNotifications()[0].getMessage());
					throw new FedExException(childReply.getNotifications()[0].getMessage());
				}
				Package currentPackage = order.getPackages().get(x);
				currentPackage.setTrackingNumber(childReply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getTrackingIds(0).getTrackingNumber());
				currentPackage.setLabel(childReply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getLabel().getParts(0).getImage());
				currentPackage.setLabelInfo(new ShippingLabel());
				currentPackage.getLabelInfo().setFormId(childReply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getTrackingIds(0).getFormId());
				label = new ShippingLabel();
				label.setLabel(childReply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getLabel().getParts(0).getImage());			
				label.setTrackingNumber(reply.getCompletedShipmentDetail().getMasterTrackingId().getTrackingNumber());
				label.setLabelType(ShiplinxConstants.LABEL_TYPE_PNG_STRING);
				labels.add(label);
				
				order.getPackages().get(x).getLabelInfo().setLabelType(ShiplinxConstants.LABEL_TYPE_PNG_STRING);
				
			}	
		}
		else
		{
			ProcessShipmentReply reply=shipWebServiceClient.sendRequest(request,"1");

			if (!isResponseOk(reply.getHighestSeverity())) // check if the call was successful
				{
					log.info("ERROR FedEx ShipOrder::"+reply.getNotifications()[0].getMessage());
					throw new FedExException(reply.getNotifications()[0].getMessage());
				}
		
			String masterTrackingNumber = reply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getTrackingIds(0).getTrackingNumber();
			String masterFormId = reply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getTrackingIds(0).getFormId();
			order.setMasterTrackingNum(masterTrackingNumber);
			
			order.getPackages().get(0).setTrackingNumber(reply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getTrackingIds(0).getTrackingNumber());
			order.getPackages().get(0).setLabel(reply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getLabel().getParts(0).getImage());
			order.getPackages().get(0).setLabelInfo(new ShippingLabel());
			order.getPackages().get(0).getLabelInfo().setFormId(masterFormId);
			ShippingLabel label = new ShippingLabel();
			label.setLabel(reply.getCompletedShipmentDetail().getCompletedPackageDetails(0).getLabel().getParts(0).getImage());			
			label.setTrackingNumber(masterTrackingNumber);
			label.setLabelType(ShiplinxConstants.LABEL_TYPE_PNG_STRING);
			labels.add(label);
			order.getPackages().get(0).getLabelInfo().setLabelType(ShiplinxConstants.LABEL_TYPE_PNG_STRING);
		}	
		
		order.setLabels(labels);
		
	}

	public void requestPickup(Pickup pickup) throws FedExException{
    	try 
    	{
    		//get the customer
        	Customer customer = customerService.getCustomerInfoByCustomerId(pickup.getCustomerId());
    	    if(pickup !=null)
    	    {	
    		    PickupWebServiceClient pickupWebServiceClient=new PickupWebServiceClient(pickup.getCarrierAccount());
    		    //This method will set all the credentials as per from - to address logic
    		    pickupWebServiceClient.authenticationInfo();
    		    WebAuthenticationDetail detail = pickupWebServiceClient.createPickupkWebAuthenticationDetail();
    		    ClientDetail clientDetail = pickupWebServiceClient.createPickupClientDetail();
    			Localization localization = new Localization("EN","ES");
    			clientDetail.setLocalization(localization);
    			TransactionDetail transactionDetail = new TransactionDetail();
    			transactionDetail.setCustomerTransactionId("CreatePickup_v3");
    			
    			// <v3:Version>
    			VersionId version=new VersionId();
    			version.setServiceId("disp");
    			version.setMajor(3);
    			version.setIntermediate(0);
    			version.setMinor(0);
    			
    			// <v3:OriginDetail>
    			PickupOriginDetail pickupOriginDetail = new PickupOriginDetail();
    			pickupOriginDetail.setUseAccountAddress(false);
    			
    			Contact contact=new Contact();
    			contact.setPersonName(pickup.getAddress().getContactName());
    			
    			//API Request doesnot contains company name.
    			/*if(pickup.getCompanyName() != null)
    		    contact.setCompanyName(pickup.getAddress().getc);
    			else*/
    			contact.setCompanyName("");
    	
    		    contact.setEMailAddress(pickup.getAddress().getEmailAddress());
    			ContactAndAddress contactAndAddress=new ContactAndAddress(); //<v3:PickupLocation>
    			contactAndAddress.setContact(contact); //<v3:Contact>
    			
    			String[] streetLines = {pickup.getAddress().getAddress1(),pickup.getAddress().getAddress2()};// <v3:Address>
    			Address pickupAddress = new Address();
    			pickupAddress.setStreetLines(streetLines);
    			pickupAddress.setCity(pickup.getAddress().getCity());
    			pickupAddress.setStateOrProvinceCode(pickup.getAddress().getProvinceCode());
    			pickupAddress.setPostalCode(pickup.getAddress().getPostalCode());
    			pickupAddress.setCountryCode(pickup.getAddress().getCountryCode());
    			contactAndAddress.setAddress(pickupAddress);
    			
    			pickupOriginDetail.setPickupLocation(contactAndAddress);  
    			if(pickup.getPickupLocation().equalsIgnoreCase("Front Door"))
    				pickupOriginDetail.setPackageLocation(PickupBuildingLocationType.FRONT);// <v3:PackageLocation>
    			else if(pickup.getPickupLocation().equalsIgnoreCase("Back Door"))
    				pickupOriginDetail.setPackageLocation(PickupBuildingLocationType.REAR);// <v3:PackageLocation>
    			else if(pickup.getPickupLocation().equalsIgnoreCase("Side Door"))
    				pickupOriginDetail.setPackageLocation(PickupBuildingLocationType.SIDE);// <v3:PackageLocation>
    			else //set it to front by default
    				pickupOriginDetail.setPackageLocation(PickupBuildingLocationType.FRONT);// <v3:PackageLocation>
    			pickupOriginDetail.setBuildingPart(BuildingPartCode.DEPARTMENT);//  <v3:BuildingPart>
    			pickupOriginDetail.setBuildingPartDescription("OPERATIONS");// <v3:BuildingPartDescription>
    			
    			Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));			
     			cal.setTime(pickup.getPickupDate());
    			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(pickup.getReadyHour()));
    			cal.set(Calendar.MINUTE, Integer.parseInt(pickup.getReadyMin()));
    			
    			pickupOriginDetail.setReadyTimestamp(cal); //  <v3:ReadyTimestamp>
    			
    			String pickCloseTime= pickup.getCloseHour()+":"+pickup.getCloseMin()+":"+"00";
    			pickupOriginDetail.setCompanyCloseTime(new Time(pickCloseTime));
    	       
    	        CreatePickupRequest request = new CreatePickupRequest();
    			request.setWebAuthenticationDetail(detail);
    			request.setClientDetail(clientDetail);
    			request.setTransactionDetail(transactionDetail);
    			//request.setAssociatedAccountNumber(accountNumber);
    			request.setVersion(version);
    			request.setOriginDetail(pickupOriginDetail);
    			PositiveInteger packageCount = new PositiveInteger(pickup.getQuantity()+"");
    			request.setPackageCount(packageCount);

    			// <v3:TotalWeight>
			    com.fedex.ws.pickup.v3.Weight  weight=new com.fedex.ws.pickup.v3.Weight ();
				if(pickup.getWeightUnit().equalsIgnoreCase(ShiplinxConstants.WEIGHT_UNITS_LBS))
					weight.setUnits(com.fedex.ws.pickup.v3.WeightUnits.LB);
				else
					weight.setUnits(com.fedex.ws.pickup.v3.WeightUnits.KG);
				
				weight.setValue(new BigDecimal(pickup.getTotalWeight()));
				request.setTotalWeight(weight);
				
				if(!StringUtil.isEmpty(pickup.getServiceCode()) && (pickup.getServiceCode().contains("GROUND")))					
					request.setCarrierCode(CarrierCodeType.FDXG);				
				else //default to express
					request.setCarrierCode(CarrierCodeType.FDXE); 
    			//         <v3:OversizePackageCount>
    			if(pickup.getOversizeQuantity()>0)
    			{
    				PositiveInteger oversizePackageCount= new PositiveInteger(pickup.getOversizeQuantity() + "");
    				request.setOversizePackageCount(oversizePackageCount);
    			}
    			//<v3:CommodityDescription>
    			request.setCommodityDescription("Freight");
    			//    <v3:CountryRelationship>
    			if(pickup.getAddress().getCountryCode().equalsIgnoreCase(pickup.getDestinationCountryCode()))
    				request.setCountryRelationship(CountryRelationshipType.DOMESTIC);
    			else
    				request.setCountryRelationship(CountryRelationshipType.INTERNATIONAL);
    			//PickupWebServiceClient  pickupWebServiceClient= new PickupWebServiceClient(order);
    			
    			log.debug("Pickup Request::::" + Common.getXMLOfObject(request));
    			CreatePickupReply reply = pickupWebServiceClient.sendRequest(request);
    			log.debug("Pickup Reply::::" + Common.getXMLOfObject(reply));
    			
    			boolean success = true;
     			if (!isPickupResponseOk(reply.getHighestSeverity())) // check if the call was successful
    			{
    				log.debug("ERROR FedEx Pickup::"+reply.getNotifications()[0].getMessage());
    				throw new FedExException(reply.getNotifications()[0].getMessage());
    			}

    			if(!success || StringUtil.isEmpty(reply.getPickupConfirmationNumber()))
    				throw new ShiplinxException();
    			
    	        log.info("--- Fedex Pickup done Confirmation Number :---" + reply.getPickupConfirmationNumber());
    	        pickup.setConfirmationNum(reply.getPickupConfirmationNumber());
     	    }//end of if pickup!=null
		} 
    	catch (ShiplinxException e) 
    	{
    		log.error("Error occured in request Pickup",e);
    		throw e;
    	}
    	catch (Exception e) 
    	{
    		log.error("Error occured in request Pickup",e);
    		throw new ShiplinxException();
    	}
    	
	    
	}
	
	public boolean cancelPickup(Pickup pickup) {
		return false;
	}
	
	public void setCustomerCarrierData(CustomerCarrier carrierData) {
		// TODO Auto-generated method stub

	}

	public void generateShippingLabel(OutputStream outputStream, long orderId, CustomerCarrier customerCarrier) {
		log.debug("Attempting to retrieve shipping label for order with id " + orderId);
		//order = orderDAO.getOrder(orderId);
		long start = System.currentTimeMillis();	
		try{
			FedExLabelGenerator label_gen = new FedExLabelGenerator();
			label_gen.setShippingDAO(this.shippingDAO);
			label_gen.generateShippingLabel(shippingDAO.getShippingOrder(orderId), outputStream);
		}catch (Exception e) {
			e.printStackTrace();
			log.debug("--------"+e);

		}
		long end = System.currentTimeMillis();
		log.debug("Time (in ms) to generate shipping label: " + (end-start));

	}

	public ShippingDAO getShippingDAO() {
		return shippingDAO;
	}

	public void setShippingDAO(ShippingDAO shippingDAO) {
		this.shippingDAO = shippingDAO;
	}

	
	public static String getXMLOfObject(Object object) {
		StringBuilder sb = new StringBuilder();

		try {

			Long longTime = Calendar.getInstance().getTimeInMillis();
			String fileName = WebUtil.getProperty(Constants.SYSTEM_SCOPE, "BASE_PATH")+object.getClass().getName()+"_"+longTime+".xml";

			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
			encoder.writeObject(object);
			encoder.close();

			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String str;
			while ((str = in.readLine()) != null) {
				sb.append(str);
				sb.append("\r\n");
			}
			in.close();
			File f = new File(fileName);
			f.delete();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	
	
	public void checkForShipmentStatusUpdates() {
		
		log.info("Starting Fedex status update...");
		long start = System.currentTimeMillis();
		
		List<ShippingOrder> orders = shippingDAO.search(this.getCarrierId());
		log.info("this.getCarrierId()..."+this.getCarrierId());
		if(orders==null || orders.size()==0){
			log.info(("No Fedex orders to update status!"));
			return;
		}
		log.info(orders.size()  + " orders to update!");
		
		for(ShippingOrder shippingorder: orders){
			
			try{
				
				ShippingOrder order = shippingService.getShippingOrder(shippingorder.getId()); 
				//TrackPackageResponse isDelivered = (TrackPackageResponse)webAPI.isDelivered(or);
				
				log.debug("ShippingOrder-----id ------"+shippingorder.getId());
				log.debug("ShippingOrder-----isLive ------"+shippingorder.isLive());
				log.debug("---getCustomerId ------"+shippingorder.getCustomerId());
				log.debug("---getFromCountry ------"+shippingorder.getFromCountry());
				log.debug("getServiceName::"+shippingorder.getServiceName()+"--getServiceId::"+shippingorder.getServiceId());
				log.debug("getCarrierName::"+shippingorder.getCarrierName()+"--getCarrierId::"+shippingorder.getCarrierId());
				
				
				
				//set the customer account to shipping order according to country
				CustomerCarrier customerCarrier = carrierServiceDAO.getDefAccountByCountry(getCarrierId(),
						shippingorder.getCustomerId(), shippingorder.getFromCountry());
				
				log.debug("customerCarrier ------"+customerCarrier.getCarrierAccountId());
				
//				order.setCustomerCarrier(customerCarrier);
				
				//web service call to get the tracking information
				trackWebServiceClient.setShippingOrder(order);
				trackWebServiceClient.init();
				trackWebServiceClient.sendRequest();
				
				String status = "";
				String del_date = "";
				String del_time = "";
				
				if(status==null){
					log.debug("Could not determine status for Fedex order with Id " + order.getId());
					continue;
				}
				
				if(status.equalsIgnoreCase("Delivered")){
					order.setProofOfDelivery("");
					String dateTime = del_date + " " + del_time;
					order.setDateOfDelivery(FormattingUtil.getDate(dateTime, FormattingUtil.DATE_FORMAT_YYYYMMDD_HHMMSS));
					//orderManager.removeFromSession(order);
					shippingService.save(order);
					String comment = "POD : " + order.getProofOfDelivery() + " on " + FormattingUtil.getFormattedDate(order.getDateOfDelivery(), FormattingUtil.DATE_FORMAT_DDMMMYY_HHMM);
					shippingService.updateStatus(new Long(0), new Long(order.getId()), ShiplinxConstants.STATUS_DELIVERED, comment);
					continue;
				}
				else if(order.getStatusId()==ShiplinxConstants.STATUS_INTRANSIT) //already in transit...no need to update
					continue;

				else{
					for(String s:statusTerms){
						log.debug("STATUS " + s + " " + status);
						if(status.indexOf(s) >= 0){
							shippingService.updateStatus(new Long(0), new Long(order.getId()), ShiplinxConstants.STATUS_INTRANSIT, "Order picked up by Fedex");
							break;
						}
					}
				}
			}
			catch(Exception e){
				log.error("Unable to determine update status for Fedex order with id " + shippingorder.getId() + "!!", e);
				
				continue;
			}
		}
		
		long end = System.currentTimeMillis();
		log.info("Completed Fedex status update in " + (end-start) + " ms");

	}

	public ShippingService getShippingService() {
		return shippingService;
	}

	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

	public TrackWebServiceClient getTrackWebServiceClient() {
		return trackWebServiceClient;
	}

	public void setTrackWebServiceClient(TrackWebServiceClient trackWebServiceClient) {
		this.trackWebServiceClient = trackWebServiceClient;
	}

	public CarrierServiceDAO getCarrierServiceDAO() {
		return carrierServiceDAO;
	}

	public void setCarrierServiceDAO(CarrierServiceDAO carrierServiceDAO) {
		this.carrierServiceDAO = carrierServiceDAO;
	}
	
	private static boolean isResponseOk(NotificationSeverityType notificationSeverityType) {
		if (notificationSeverityType == null) {
			return false;
		}
		if (notificationSeverityType.equals(NotificationSeverityType.WARNING) ||
			notificationSeverityType.equals(NotificationSeverityType.NOTE)    ||
			notificationSeverityType.equals(NotificationSeverityType.SUCCESS)) {
			return true;
		}
			return false; 
	   }

	private static boolean isPickupResponseOk(com.fedex.ws.pickup.v3.NotificationSeverityType notificationSeverityType) {
		if (notificationSeverityType == null) {
			return false;
		}
		if (notificationSeverityType.getValue().equalsIgnoreCase(NotificationSeverityType.WARNING.getValue()) ||
			notificationSeverityType.getValue().equalsIgnoreCase(NotificationSeverityType.NOTE.getValue())    ||
			notificationSeverityType.getValue().equalsIgnoreCase(NotificationSeverityType.SUCCESS.getValue())) {
			return true;
		}
			return false; 
	 }

	@Override
	public void uploadRates(Service service, long customerId, long busId, boolean isOverwrite) {
		// TODO Auto-generated method stub
		
	}

	public CustomerManager getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerManager customerService) {
		this.customerService = customerService;
	}

}
