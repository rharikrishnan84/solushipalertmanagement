package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;




import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.axis.types.PositiveInteger;
import org.apache.axis.types.Time;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.a.a.a.g.c.e;
import com.fedex.ship.stub.NotificationSeverityType;
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
import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.Common;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidateResponse;
import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSAccessorialService;
import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSDocument;
import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSItem2;
import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSMessage;
import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSReference;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.GetAccessorialServices;
import com.meritconinc.shiplinx.carrier.eshipplus.ServiceMode;
import com.meritconinc.shiplinx.carrier.eshipplus.SubmitBookingRequest;
import com.meritconinc.shiplinx.carrier.eshipplus.WSAccessorialService;
import com.meritconinc.shiplinx.carrier.eshipplus.WSBookingRequest;
import com.meritconinc.shiplinx.carrier.eshipplus.WSCountry;
import com.meritconinc.shiplinx.carrier.eshipplus.WSDocument;
import com.meritconinc.shiplinx.carrier.eshipplus.WSFreightClass;
import com.meritconinc.shiplinx.carrier.eshipplus.WSItem2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSItemPackage;
import com.meritconinc.shiplinx.carrier.eshipplus.WSLocation2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSMessage;
import com.meritconinc.shiplinx.carrier.eshipplus.WSRate2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSReference;
import com.meritconinc.shiplinx.carrier.eshipplus.WSReturn;
import com.meritconinc.shiplinx.carrier.eshipplus.WSShipment2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSShipmentStatus;
import com.meritconinc.shiplinx.carrier.eshipplus.WSTime2;
import com.meritconinc.shiplinx.carrier.fedex.CancelPackageWebServiceClient;
import com.meritconinc.shiplinx.carrier.fedex.FedExLabelGenerator;
import com.meritconinc.shiplinx.carrier.fedex.PickupWebServiceClient;
import com.meritconinc.shiplinx.carrier.utils.FedExException;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.LoggedEventDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.AccessorialServices;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.EshipplusPackage;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.ManifestBean;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingLabel;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CustomerManager;
import com.meritconinc.shiplinx.service.ShippingService;
import com.meritconinc.shiplinx.service.MarkupManager;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.EODManifestCreator;
import com.meritconinc.shiplinx.utils.PDFRenderer;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
//import com.meritconinc.shiplinx.carrier.utils.EShipPlusException;

public class EShipPlusAPI implements CarrierService,Job{
	private static final Logger log = LogManager.getLogger(EShipPlusAPI.class);
	private HttpServletRequest request;
	private CarrierServiceDAO carrierDAO = null;
	public static final String LIVE_URL_RATE = "";
	//public static 
	
	public static final String ESHIPPLUS_PACKAGE_PALLET_STRING = "pallet";
	
	public  static final String FREIGHT_CHARGE_CODE = "050";
	public  static final String FUEL_CHARGE_CODE = "FUEL";
	private ShippingDAO shippingDAO;
	private LoggedEventDAO loggedEventDAO;
	private ShippingService shippingService;
	private CarrierServiceDAO carrierServiceDAO;
	private CustomerManager customerService;
	private MarkupManager markupManagerService;
	
	public EShipPlusAPI(){
		this.carrierDAO=(CarrierServiceDAO) MmrBeanLocator.getInstance().findBean(
				"carrierServiceDAO");
	}
	public MarkupManager getMarkupManagerService() {
		return markupManagerService;
	}

	public void setMarkupManagerService(MarkupManager markupManagerService) {
		this.markupManagerService = markupManagerService;
	}

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		String domain = System.getProperty("DOMAIN_NAME");
		
						log.info("DOMAIN NAME :" + " " + domain);
						if (domain != null){
							boolean flag = carrierDAO.getSchdulerFlagByDomain(domain);
							synchronized (this){
								if (flag) {
		log.debug("-------------------------------------Eship Plus Quartz Trigger Started-------------------------------------------------------");
		shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
		loggedEventDAO=(LoggedEventDAO) MmrBeanLocator.getInstance().findBean("loggedEventDAO");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date=new Date();
		List<ShippingOrder> orderList=new ArrayList<ShippingOrder>();
		List<LoggedEvent> eventList=new ArrayList<LoggedEvent>();
		String Message;
		orderList=shippingDAO.getEshipPlusActiveShipments();
		for(ShippingOrder order:orderList){
			int newStatus=0;
			LoggedEvent tempEventObj=new LoggedEvent();
			WSShipmentStatus statusResponse=GetShipmentStatus(order);
			if(statusResponse.getStatus()!=null){//The status of READY FOR SHIPPING in local coming from web service status as SCHEDULED
				if(statusResponse.getStatus().name().equals("SCHEDULED")){
					newStatus=16;
			    }else if(statusResponse.getStatus().name().equals("INTRANSIT")){
					newStatus=20;
				}else if(statusResponse.getStatus().name().equals("DELIVERED")){
					newStatus=30;
				}else if(statusResponse.getStatus().name().equals("VOID")){
					newStatus=40;
				}else{
					continue;
				}
				if(newStatus>0 && order.getStatusId()!=newStatus){
					//Assigning values for logged event entry in tempEventObj
					eventList=loggedEventDAO.getLoggedEventInfo(tempEventObj, true);
					
					shippingDAO.updateOrderStatus(order.getId(), newStatus);
					tempEventObj.setEntityId(order.getId());
					tempEventObj.setEntityType(10);
					tempEventObj.setEventUsername("admin");
					tempEventObj.setEventDateTime(new Timestamp(date.getTime()));
					//tempEventObj.setMessage("Shipping order status updated ! Status Received: "+statusResponse.getStatus().name());
					tempEventObj.setMessage("Shipping order status updated ! Status Received: "+statusResponse.getStatus().name()+"\n"+"Actual Delivery Date :"+statusResponse.getActualDeliveryDate()+"\n"+"Actual Pickup Date :"+statusResponse.getActualPickupDate()+"\n"+"Estimate Delivery Date :"+statusResponse.getEstimateDeliveryDate()+"\n"+"Estimate pickup date :"+statusResponse.getEstimatePickupDate()+"\n"+"Mode :"+statusResponse.getMode()+"\n"+"Shipment Number :"+statusResponse.getShipmentNumber()+"\n"+"ventor key :"+statusResponse.getVendorKey()+"\n"+"ventor Name :"+statusResponse.getVendorName()+"\n"+"ventor scac :"+statusResponse.getVendorScac());
					tempEventObj.setPrivateMessage(false);
					tempEventObj.setDeletedMessage(false);
					tempEventObj.setSystemLog("Order status Updated by automatic timer trigger");
					loggedEventDAO.addLoggedEventInfo(tempEventObj);
					log.debug("Eship Order Status updated for Order :" + order.getId());
				}
			}
		}
		log.debug("-------------------------------------Eship Plus Quartz Trigger End-------------------------------------------------------");
								}else{
											log.info("Eship Plus Job  not executed");
										}
								}
								}
	}
	
	/**CarrierService method implementation **/
	public long getCarrierId() {
		return ShiplinxConstants.CARRIER_ESHIPPLUS;
	}
	private static XMLGregorianCalendar getShipmentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DAY_OF_YEAR, 5);
		Date futuredate=calendar.getTime();
		XMLGregorianCalendar xmlgregorianCalendar =null;
		try {
			
			GregorianCalendar gregorianCalendar= new GregorianCalendar();
			calendar.setTime(futuredate);
			xmlgregorianCalendar= DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			xmlgregorianCalendar.setDay(xmlgregorianCalendar.getDay()+2);
		}
		catch (Exception e) {
		}
		return xmlgregorianCalendar;
		
	}
	private static XMLGregorianCalendar getScheduldedShipmentDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DAY_OF_YEAR, 5);
		Date futuredate=calendar.getTime();
		XMLGregorianCalendar xmlgregorianCalendar =null;
		String dateString=date.toString();
		try {
			
			GregorianCalendar gregorianCalendar= new GregorianCalendar();
			calendar.setTime(futuredate);
			xmlgregorianCalendar= DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
			xmlgregorianCalendar.setDay(Integer.parseInt(dateString.substring(8, 10)));
			xmlgregorianCalendar.setMonth(Integer.parseInt(dateString.substring(5, 7)));
			xmlgregorianCalendar.setYear(Integer.parseInt(dateString.substring(0, 4)));
			xmlgregorianCalendar.setTime(00, 00, 00);
			
		}
		catch (Exception e) {
		}
		return xmlgregorianCalendar;
		
	}
	
/*	public XMLGregorianCalendar getShipmentDate(ShippingOrder shipDate){

        XMLGregorianCalendar result = null ;
        Timestamp date = ((ShippingOrder) shipDate).getScheduledShipDate();
        
        // below line telling that which format we need the date
        //SimpleDateFormat simpleDateFormat;
        GregorianCalendar gregorianCalendar = null;
        try {
            //simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            // below line converting our string to date
            //date = simpleDateFormat.parse(s);
            // here we are getting the object of GregorianCalendar class
            gregorianCalendar =(GregorianCalendar) GregorianCalendar.getInstance();
            // by below we are setting the time of date into gregorianCalendar
            gregorianCalendar.setTime(date);
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

        } catch (Exception ex) {
        	log.debug("---XMLGregorianCalendar Error----"+ex);
        }
        return result;

        
		Calendar calendar = Calendar.getInstance();
	
	     calendar.add(Calendar.DAY_OF_YEAR, 6);
	      
	     // now get "tomorrow"
	     Date tomorrow = calendar.getTime();
	     XMLGregorianCalendar date2 = null;
	  try {
			   GregorianCalendar gcalendar = new GregorianCalendar();
			   calendar.setTime(tomorrow);
			   date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcalendar);
	   
	  } 
	  catch (DatatypeConfigurationException e) {
		  e.printStackTrace();
	  }
	  return date2;
	}*/

	public List<Rating> rateShipment(ShippingOrder shippingOrder, List<Service> services, long carrierId, CustomerCarrier customerCarrier) throws RuntimeException {
		List<Rating> ratingList = new ArrayList<Rating>();
		List<Rating> ratingListNew = new ArrayList<Rating>();
		try {
			if(shippingOrder.getPackageTypeId().getName().equalsIgnoreCase(ESHIPPLUS_PACKAGE_PALLET_STRING)){
			WSShipment2 wsShipment2 = setOrderDetails(shippingOrder);
			EshipPlusRequestConnector eshipPlusRequestConnector = new EshipPlusRequestConnector(customerCarrier);
			WSShipment2 shipmentRateDetails = eshipPlusRequestConnector.getEshipPlusRate(wsShipment2);
			ratingListNew=eshipPlusRequestConnector.buildRateFromRequest(shippingOrder,shipmentRateDetails, ratingList, customerCarrier, services);
			ratingList.addAll(ratingListNew);
			}
			//shippingService.getEshipPlusCarrierFilterByScac();
		} catch (Exception e) {
			log.debug(e);
			//throw new EShipPlusException(e.getMessage());
		}
		
		return ratingList;
	}
	
	
	@Override
	public Object getShippingOrderStatus(ShippingOrder order) {
		return null;
	}
	@Override
	public void shipOrder(ShippingOrder order, Rating rateInfo,
			CustomerCarrier customerCarrier) {
		 User user = UserUtil.getMmrUser();
		WSShipment2 wsShipment2 = setOrderDetailsForShipRequest(order,rateInfo,customerCarrier);
		SubmitBookingRequest submitBookingRequest=new SubmitBookingRequest();
		WSBookingRequest wsBookingRequest=new WSBookingRequest();
		
		AuthenticationToken authenticationtoken = AuthenticationProvider.authendication(customerCarrier);
		WSShipment2 shipment2 = new WSShipment2();
		EShipPlusWSv4 eshipplusws4=new EShipPlusWSv4();
		/*************SENDING BOOK REQUEST AND GETTING RESPONCE BACK****************************************/
		WSShipment2 Shipment2 = eshipplusws4.getEShipPlusWSv4Soap().book(wsShipment2, authenticationtoken);
		/***************************************************************************************************/
		order.setMasterTrackingNum(Shipment2.getBookingReferenceNumber());
		//Assigning modified freight class into local object
		for(int i=0;i<Shipment2.getItems().getWSItem2().size();i++){
			Double freightClass=Shipment2.getItems().getWSItem2().get(i).getFreightClass().getFreightClass();
				order.getPackages().get(i).setFreightClass(freightClass.toString());
			order.getPackages().get(i).setPackageType(Shipment2.getItems().getWSItem2().get(i).getPackaging().getPackageName());
			order.getPackages().get(i).setDescription(Shipment2.getItems().getWSItem2().get(i).getDescription());
		}
		if((order.getDangerousGoods()==null || order.getDangerousGoods()==0) && wsShipment2.isHazardousMaterialShipment()){
			order.setDangerousGoods(1);
		}
		order.setDefaultPickupTimeBetween(wsShipment2.getEarliestPickup().getTime()+"&"+wsShipment2.getLatestPickup().getTime()+"&"+user.getFirstName()+" "+user.getLastName());
		List<WSDocument> returnDoc = Shipment2.getReturnConfirmations().getWSDocument();
		ShippingLabel label = new ShippingLabel();
		label.setTrackingNumber(Shipment2.getBookingReferenceNumber());
		List<ShippingLabel> labels = new ArrayList<ShippingLabel>();
		byte[] createdLabel=createEShipPlusBOL(order,wsShipment2,Shipment2,rateInfo);
		//byte[] createdLabel=updateEshipLable(209441,order.getRealContextPath());
		for(WSDocument doc:returnDoc){
			label.setLabel(createdLabel);
			/**If you want to see the original label remove the comment**/
			label.setOriginalLabel(doc.getDocImage());
			String labelHtml = doc.getHtml();
			label.setHtml(labelHtml.getBytes());
			label.setLabelType(doc.getDocType().name());
			labels.add(label);
		}
		order.setLabels(labels);
		
	}
	public byte[] createEShipPlusBOL(ShippingOrder so,WSShipment2 requestObj,WSShipment2 responceObj,Rating rateInfo){
	    
	    User user = UserUtil.getMmrUser();
	    try {
	    	//E:\HTML5 Workspace Git Rep2\Soluship-HTML5-Dev\ShipLinx\src\main\java\com\meritconinc\shiplinx\carrier\eshipplus\eShipBOL.jrxml
	    	List<WSDocument> returnDoc = responceObj.getReturnConfirmations().getWSDocument();
	      JasperDesign jasperDesign = JRXmlLoader
	          .load(so.getRealContextPath()
	              + "WEB-INF/classes/com/meritconinc/shiplinx/carrier/eshipplus/eShipBOL.jrxml");
	      JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
          String imageRealPath=so.getRealContextPath()
	              + "WEB-INF/classes/com/meritconinc/shiplinx/carrier/eshipplus";
	      Map<String, Object> parameters = new HashMap<String, Object>();
	      shippingService = (ShippingService) MmrBeanLocator.getInstance().findBean("shippingService");
	      Long lastOrderId=shippingService.getLatestOrderId();
	      if(lastOrderId!=null){
	    	  lastOrderId++;
	      }else{
	    	  lastOrderId=Long.parseLong(so.getOrderNum());
	      }
	      parameters.put("imageRealPath", imageRealPath);
	      parameters.put("bolNumber",responceObj.getBookingReferenceNumber());
	      parameters.put("orderNumber",lastOrderId.toString());
	      parameters.put("originAddress",requestObj.getOrigin().getDescription()+", "+requestObj.getOrigin().getStreet()+", "+requestObj.getOrigin().getCity()+", "+requestObj.getOrigin().getState()+", "+requestObj.getOrigin().getPostalCode()+", "+requestObj.getOrigin().getCountry().getCode());
	      parameters.put("destinationAddress",requestObj.getDestination().getDescription()+", "+requestObj.getDestination().getStreet()+", "+requestObj.getDestination().getCity()+", "+requestObj.getDestination().getState()+", "+requestObj.getDestination().getPostalCode()+", "+requestObj.getDestination().getCountry().getCode());
	      parameters.put("originContactName",requestObj.getOrigin().getContact());
	      parameters.put("originPhone",requestObj.getOrigin().getPhone());
	      parameters.put("originFax", requestObj.getOrigin().getFax());
	      parameters.put("destinationContactName",requestObj.getDestination().getContact());
	      parameters.put("destinationPhone",requestObj.getDestination().getPhone() );
	      parameters.put("destinationFax", requestObj.getDestination().getFax());
	      parameters.put("shipperRef", so.getReferenceOne());
	      parameters.put("PONumber",so.getReferenceTwo());
	      //--------------------Hot coded the fiels wt we dont have about
	      parameters.put("originTerminal",null);
	      parameters.put("originTerminalPhone",null);
	      parameters.put("originTerminalFax",null );
	      parameters.put("destinationTerminal",null );
	      parameters.put("destinationTerminalPhone",null );
	      parameters.put("destinationTerminalFax", null);
	      //---------------------------------------
	      if(requestObj.isHazardousMaterialShipment()){
	    	  parameters.put("hazarousName",requestObj.getHazardousMaterialContactName());
	    	  parameters.put("hazardousPhone",requestObj.getHazardousMaterialContactPhone());
	    	  parameters.put("hazardousMobile",requestObj.getHazardousMaterialContactMobile());
	    	  parameters.put("hazardousEmail",requestObj.getHazardousMaterialContactEmail());
	      }else{
	    	  parameters.put("hazarousName",null);
	    	  parameters.put("hazardousPhone",null);
	    	  parameters.put("hazardousMobile",null);
	    	  parameters.put("hazardousEmail",null);
	      }
	      parameters.put("pickupInstructions",so.getFromInstructions());
	      parameters.put("deliveryInstructions",so.getToInstructions() );
	      parameters.put("carrierRoute", rateInfo.getCarrierNameLP());
	      parameters.put("shipmentDate", responceObj.getShipmentDate().toString().subSequence(0, 10));
	      parameters.put("pickupBetweenFrom", responceObj.getEarliestPickup().getTime());
	      parameters.put("pickupBetweenTo",responceObj.getLatestPickup().getTime());
	      parameters.put("criticalNotes",requestObj.getDestination().getSpecialInstructions());
	      String accessorials="";
	      AccessorialServices accessorial=new AccessorialServices();
	      for(int i=0;responceObj.getAccessorials()!=null && i<responceObj.getAccessorials().getWSAccessorialService().size();i++){
	    	  if(responceObj.getAccessorials().getWSAccessorialService().get(i).getServiceCode()!=null){
	    		  accessorial=shippingService.getAccessorialServiceByServiceCode(responceObj.getAccessorials().getWSAccessorialService().get(i).getServiceCode());
	    	  }	
	    	  if(accessorial!=null && accessorial.getServiceDescription()!=null){
	    		  accessorials=accessorials+accessorial.getServiceDescription()+" ";
	    	  }else{
	    		  accessorials=accessorials+responceObj.getAccessorials().getWSAccessorialService().get(i).getServiceDescription()+" ";
	    	  }
	      }
	      for(int i=0;responceObj.getBookedRate()!= null && responceObj.getBookedRate().getBillingDetails() != null &&  i<responceObj.getBookedRate().getBillingDetails().getWSBillingDetail().size();i++){
	    	  if(responceObj.getBookedRate().getBillingDetails().getWSBillingDetail().get(i).getCategory().name().equalsIgnoreCase("SERVICE")){
	    		  if(responceObj.getBookedRate().getBillingDetails().getWSBillingDetail().get(i).getBillingCode().equalsIgnoreCase("HOM")){
	    			  accessorials=accessorials+" "+"HOMELAND SECURITY";
	    		  }else{
	    			  accessorials=accessorials+" "+responceObj.getBookedRate().getBillingDetails().getWSBillingDetail().get(i).getDescription();
	    		  }
	    		  
	    	  }
	      }
	      
	      parameters.put("accessorials", accessorials);
	     // parameters.put("totalWeight", so.getTotalWeight());
	      parameters.put("totalWeight", so.getTotalActualWeight());
	      parameters.put("createdBy", user.getFirstName()+" "+user.getLastName());
	      
	      //variables
	      List<Package> packages = new ArrayList<Package>();
	      for(WSItem2 pack:responceObj.getItems().getWSItem2()){
	    	  Package tempPack=new Package();
	          tempPack.setPackageType(pack.getPackaging().getPackageName());
	          tempPack.setDescription(pack.getDescription());
	          tempPack.setWeight(pack.getWeight());
	          Integer quantity=pack.getPackagingQuantity();
	          tempPack.setPackageQuantity(quantity.toString());
	          Double fClassDouble=pack.getFreightClass().getFreightClass();
	          String fClassStr=fClassDouble.toString();
	          tempPack.setFreightClass(fClassStr);
	          tempPack.setLength(pack.getLength());
	          tempPack.setWidth(pack.getWidth());
	          tempPack.setHeight(pack.getHeight());
	    	  packages.add(tempPack);
	      }
	         //-------------------------------------------------
	        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(packages);
		      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
		      byte[] outputLabel = JasperExportManager.exportReportToPdf(jasperPrint); 
		      return outputLabel;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
   return null;
	  
	}
	
	public String updateEshipLable(long orderId,String path,String oTerminal,String oTerPhone,String oTerFax,String dTerminal,String dTerPhone,String dTerFax){
		User user = UserUtil.getMmrUser();
	    try {//need to colve problems in path and pickup time
	    	//E:\HTML5 Workspace Git Rep2\Soluship-HTML5-Dev\ShipLinx\src\main\java\com\meritconinc\shiplinx\carrier\eshipplus\eShipBOL.jrxml
	    	shippingDAO = (ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
	    	shippingService = (ShippingService) MmrBeanLocator.getInstance().findBean("shippingService");
	    	ShippingOrder order=shippingService.getShippingOrder(orderId);
	      JasperDesign jasperDesign = JRXmlLoader
	          .load(path
	              + "WEB-INF/classes/com/meritconinc/shiplinx/carrier/eshipplus/eShipBOL.jrxml");
	      JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
          String imageRealPath=path
	              + "WEB-INF/classes/com/meritconinc/shiplinx/carrier/eshipplus";
	      Map<String, Object> parameters = new HashMap<String, Object>();
	      parameters.put("imageRealPath", imageRealPath);
	      parameters.put("bolNumber",order.getMasterTrackingNum());
	      parameters.put("orderNumber",String.valueOf(orderId));
	      parameters.put("originAddress",order.getFromAddress().getAbbreviationName()+", "+order.getFromAddress().getAddress1()+", "+order.getFromAddress().getAddress2()+", "+order.getFromAddress().getCity()+", "+order.getFromAddress().getPostalCode()+", "+order.getFromAddress().getCountryCode());
	      parameters.put("destinationAddress",order.getToAddress().getAbbreviationName()+", "+order.getToAddress().getAddress1()+", "+order.getToAddress().getAddress2()+", "+order.getToAddress().getCity()+", "+order.getToAddress().getPostalCode()+", "+order.getToAddress().getCountryCode());
	      parameters.put("originContactName",order.getFromAddress().getContactName());
	      parameters.put("originPhone",order.getFromAddress().getPhoneNo());
	      parameters.put("originFax", order.getFromAddress().getFaxNo());
	      parameters.put("destinationContactName",order.getToAddress().getContactName());
	      parameters.put("destinationPhone",order.getToAddress().getPhoneNo());
	      parameters.put("destinationFax", order.getToAddress().getFaxNo());
	      parameters.put("shipperRef", order.getReferenceOne());
	      parameters.put("PONumber",order.getReferenceTwo());
	      //--------------------Hot coded the fiels wt we dont have about
	      parameters.put("originTerminal",oTerminal);
	      parameters.put("originTerminalPhone", oTerPhone);
	      parameters.put("originTerminalFax",oTerFax);
	      parameters.put("destinationTerminal",dTerminal);
	      parameters.put("destinationTerminalPhone",dTerPhone);
	      parameters.put("destinationTerminalFax",dTerFax);
	      //---------------------------------------
	    //---------------------------------------
	      if(order.getDangerousGoods()>0){
	    	  parameters.put("hazarousName",order.getFromAddress().getContactName());
	    	  parameters.put("hazardousPhone",order.getFromAddress().getPhoneNo());
	    	  parameters.put("hazardousMobile",order.getFromAddress().getMobilePhoneNo());
	    	  parameters.put("hazardousEmail",order.getFromAddress().getEmailAddress());
	      }else{
	    	  parameters.put("hazarousName",null);
	    	  parameters.put("hazardousPhone",null);
	    	  parameters.put("hazardousMobile",null);
	    	  parameters.put("hazardousEmail",null);
	      }
	      parameters.put("pickupInstructions",order.getFromInstructions());
	      parameters.put("deliveryInstructions",order.getToInstructions() );
	      parameters.put("carrierRoute", order.getCharges().get(0).getCarrierName());
	      parameters.put("shipmentDate", order.getScheduledShipDate().toString().subSequence(0, 10));
	      parameters.put("pickupBetweenFrom", order.getDefaultPickupTimeBetween().substring(0, 5));
	      parameters.put("pickupBetweenTo",order.getDefaultPickupTimeBetween().substring(6,10));
	      parameters.put("criticalNotes","");//hard coded
	      String accessorials="";
	      AccessorialServices accessorial=new AccessorialServices();
	      for(Charge charge:order.getCharges()){
	    	  if(charge!=null && charge.getName().equalsIgnoreCase("Accessorial")){
	    		  if(charge.getChargeCode().equalsIgnoreCase("PROTECTIVE SERVICE")){
	    			  accessorials=accessorials+" "+"PROTECT FROM FREEZE";
	    		  }else{
	    			  accessorials=accessorials+" "+charge.getChargeCode();
	    		  }
	    	  }
	    	  if(charge!=null && charge.getName().equalsIgnoreCase("Service")){
	    		  if(charge.getChargeCode().equalsIgnoreCase("HOMELAND SECURITY-BORDER PROCESSING FEE")){
	    			  accessorials=accessorials+" "+"HOMELAND SECURITY";
	    		  }else{
	    			  accessorials=accessorials+" "+charge.getChargeCode();
	    		  }
	    	  }
	      }
	      
	      parameters.put("accessorials", accessorials);
	      parameters.put("totalWeight", order.getTotalWeight());
	      parameters.put("createdBy", order.getDefaultPickupTimeBetween().substring(12));
	      
	      //variables
	      List<Package> packages = new ArrayList<Package>();
	      for(Package pack:order.getPackages()){
	    	  Package tempPack=new Package();
	          tempPack.setPackageType(pack.getType());
	          tempPack.setDescription(pack.getDescription());
	          tempPack.setWeight(pack.getWeight());
	          Integer quantity=1;
	          tempPack.setPackageQuantity(quantity.toString());
	          tempPack.setFreightClass(pack.getFreightClass());
	          tempPack.setLength(pack.getLength());
	          tempPack.setWidth(pack.getWidth());
	          tempPack.setHeight(pack.getHeight());
	    	  packages.add(tempPack);
	      }
	         //-------------------------------------------------
	        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(packages);
		      JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);
		      byte[] outputLabel = JasperExportManager.exportReportToPdf(jasperPrint); 
		      shippingDAO.updateEshipLabel(orderId,outputLabel);
		      return "success";
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return "error";
	}
	
	public WSShipment2 setOrderDetailsForShipRequest(ShippingOrder shippingOrder,Rating rateRecord,CustomerCarrier customerCarrier){
		
		GregorianCalendar d=new GregorianCalendar();
		markupManagerService = (MarkupManager)MmrBeanLocator.getInstance().findBean("markupManagerService");
        AuthenticationToken authenticationToken= AuthenticationProvider.authendication();
        
		WSLocation2 locationOrigin = new WSLocation2();
		WSCountry countryOrigin = new WSCountry();
		WSLocation2 locationDestination = new WSLocation2();
		WSCountry countryDestination = new WSCountry();
        //coded  
		SubmitBookingRequest submitBookingRequest=new SubmitBookingRequest();
		WSBookingRequest wsBookingRequest=new WSBookingRequest();
		
		AuthenticationToken authenticationtoken = AuthenticationProvider.authendication();
		WSShipment2 shipment2 = new WSShipment2();
		EShipPlusWSv4 eshipplusws4=new EShipPlusWSv4();
		//-------------------------
		shipment2.setReferenceNumber(shippingOrder.getReferenceOne());
		shipment2.setBookingReferenceNumber(shippingOrder.getReferenceTwo());
	
//		shipment2.setReferenceNumber("Raja");
//		shipment2.setBookingReferenceNumber("Mhd");
		
		
		/**SET THE ORIGIN*/
		
		locationOrigin.setDescription(shippingOrder.getFromAddress().getAbbreviationName());//shippingOrder
		locationOrigin.setPostalCode(shippingOrder.getFromAddress().getPostalCode());//L4V1Y9
		locationOrigin.setCity(shippingOrder.getFromAddress().getCity());//Mississauga
		locationOrigin.setState(shippingOrder.getFromAddress().getProvinceCode());//ON
		locationOrigin.setStreet(shippingOrder.getFromAddress().getAddress1());//155 - 6855 Airport Road
		locationOrigin.setContact(shippingOrder.getFromAddress().getContactName());//Tim Smith
		locationOrigin.setPhone(shippingOrder.getFromAddress().getPhoneNo());//9056732999
		locationOrigin.setSpecialInstructions(shippingOrder.getFromInstructions());
		
		/**SET THE ORIGIN COUNTRY**/
		countryOrigin.setCode(shippingOrder.getFromAddress().getCountryCode());//CA
		countryOrigin.setUsesPostalCode(true);
		locationOrigin.setCountry(countryOrigin);
		shipment2.setOrigin(locationOrigin);

		/**SET THE DESTINATION**/
			
		locationDestination.setDescription(shippingOrder.getToAddress().getAbbreviationName());
		locationDestination.setPostalCode(shippingOrder.getToAddress().getPostalCode());//12919
		locationDestination.setCity(shippingOrder.getToAddress().getCity());//CHAMPLAIN
		locationDestination.setState(shippingOrder.getToAddress().getProvinceCode());//NY
		locationDestination.setStreet(shippingOrder.getToAddress().getAddress1());//107 Lawrence Paqueette Ind. Dr.
		locationDestination.setContact(shippingOrder.getToAddress().getContactName());//Gary Cole
		locationDestination.setPhone(shippingOrder.getToAddress().getPhoneNo());//5182984400
		locationDestination.setSpecialInstructions(shippingOrder.getToInstructions());
		
		/**SET THE DESTINATION COUNTRY**/
		countryDestination.setCode(shippingOrder.getToAddress().getCountryCode());//US
		countryDestination.setUsesPostalCode(true);
		locationDestination.setCountry(countryDestination);
		shipment2.setDestination(locationDestination);
		
		//shipment date
		Date date=shippingOrder.getScheduledShipDate();
		//date.setTime(date.getTime()+ 1 * 1000 * 60 * 60 * 24);
		shipment2.setShipmentDate(getScheduldedShipmentDate(date));//2014-07-16T17:23:21.220+05:30
		
		
		//earliest pickup
		WSTime2 wsTime2 = new WSTime2();
		if(shippingOrder.getPickup().isPickupRequired()){
			wsTime2.setTime(shippingOrder.getPickup().getReadyHour()+":"+shippingOrder.getPickup().getReadyMin());
		}else{
			wsTime2.setTime("09:00");
		}
		shipment2.setEarliestPickup(wsTime2);
		
		//latest pickup
		WSTime2 wsTimeLatestPickup = new WSTime2();
		if(shippingOrder.getPickup().isPickupRequired()){
			wsTimeLatestPickup.setTime(shippingOrder.getPickup().getCloseHour()+":"+shippingOrder.getPickup().getCloseMin());
		}else{
			wsTimeLatestPickup.setTime("17:00");
		}
		shipment2.setLatestPickup(wsTimeLatestPickup);
		
		//earliest deleivery
		WSTime2 wsTimeEarliest = new WSTime2();
		wsTimeEarliest.setTime("09:30");
		shipment2.setEarliestDelivery(wsTimeEarliest);
		
		//latest deleivery
		WSTime2 wsTimeLatest = new WSTime2();
		wsTimeLatest.setTime("17:30");
		shipment2.setLatestDelivery(wsTimeLatest);
		
		List<EshipplusPackage> packageList=new ArrayList<EshipplusPackage>();
		//Getting Eship package List
		packageList=shippingService.getEshipplusPackagesList();
		//items
		ArrayList<WSItem2> arrayList = new ArrayList<WSItem2>();
		ArrayOfWSItem2 arrayOfWSItem22 = new ArrayOfWSItem2();
		for(Package pack:shippingOrder.getPackages()){
			WSItem2 item2 = new WSItem2();
			item2.setWeight(pack.getWeight());
			item2.setPackagingQuantity(1);
			item2.setLength(pack.getLength());
			item2.setHeight(pack.getHeight());
			item2.setWidth(pack.getWidth());
			if(pack.getDescription()==null || "".equalsIgnoreCase(pack.getDescription())){
				item2.setDescription("Item");
			}else{
				item2.setDescription(pack.getDescription());
			}
		
//			WSItemPackage itemPackage2 = new WSItemPackage();
//			if(item2.getPackaging()==null || item2.getPackaging().getKey()==0){
//				itemPackage2.setKey(258);
//			}else{
//				itemPackage2.setKey(item2.getPackaging().getKey());
//			}
//			item2.setPackaging(itemPackage2);
			//----------------------
			WSItemPackage itemPackage2 = new WSItemPackage();
			for(EshipplusPackage packages:packageList){
				if(pack!=null && pack.getType()!=null && !pack.getType().equalsIgnoreCase("-1") && packages.getIcPackageName().equalsIgnoreCase(pack.getType().substring(4))){
					itemPackage2.setKey(packages.getPackageKey());
				    itemPackage2.setPackageName(packages.getEshipPackageName());
				}
			}
			if(itemPackage2.getKey()==0){
				itemPackage2.setKey(267);
				itemPackage2.setPackageName("YOUR PACKAGING/UNITS");
			}
			item2.setPackaging(itemPackage2);
			
			//-----------------------
		
			WSFreightClass freightClass2 = new WSFreightClass();
			if(pack.getFreightClass()==null || "".equalsIgnoreCase(pack.getFreightClass())){
				freightClass2.setFreightClass(findFreightClass(pack));
			}else{
				freightClass2.setFreightClass(Double.parseDouble(pack.getFreightClass()));
			}
			item2.setFreightClass(freightClass2);
			
			arrayList.add(item2);
			  
		}
		arrayOfWSItem22.wsItem2 = arrayList; 
		shipment2.setItems(arrayOfWSItem22);
		
		//additional insurance
		shipment2.setDeclineAdditionalInsuranceIfApplicable(false);
		
		//selected Rate
		WSRate2 rate=new WSRate2();
		rate.setCarrierKey(rateRecord.getCarrierKeyLP());//349
		shipment2.setSelectedRate(rate);
		// Adding CarrierScac
		shipment2.getSelectedRate().setCarrierScac(rateRecord.getCarrierScacLP());//'RDWY'
		//Billed Weight
		BigDecimal big = new BigDecimal(rateRecord.getBillWeight());//1.0
		shipment2.getSelectedRate().setBilledWeight(big);
		
		shipment2.getSelectedRate().setTransitTime(rateRecord.getTransitDays());//3
		String mode=rateRecord.getServiceMode();
        //Service mode is enumeration type and 	fromValue is a method which help to get matching value 	ServiceMode.LESS_THAN_TRUCKLOAD
		if(rateRecord.getServiceMode().equalsIgnoreCase("LESS_THAN_TRUCKLOAD")){
			shipment2.getSelectedRate().setServiceMode(ServiceMode.LESS_THAN_TRUCKLOAD);
		}else if(rateRecord.getServiceMode().equalsIgnoreCase("TRUCKLOAD")){
			shipment2.getSelectedRate().setServiceMode(ServiceMode.TRUCKLOAD);
		}else if(rateRecord.getServiceMode().equalsIgnoreCase("AIR")){
			shipment2.getSelectedRate().setServiceMode(ServiceMode.AIR);
		}else if(rateRecord.getServiceMode().equalsIgnoreCase("RAIL")){
			shipment2.getSelectedRate().setServiceMode(ServiceMode.RAIL);
		}else if(rateRecord.getServiceMode().equalsIgnoreCase("SMALL_PACKAGE")){
			shipment2.getSelectedRate().setServiceMode(ServiceMode.SMALL_PACKAGE);
		}else{
			shipment2.getSelectedRate().setServiceMode(ServiceMode.NOT_APPLICABLE);
		}
		//shipment2.getSelectedRate().setServiceMode(ServiceMode.fromValue(rateRecord.getServiceMode()));
	    //freight charge
		big = new BigDecimal(rateRecord.getBaseCharge());
		shipment2.getSelectedRate().setFreightCharges(big);//143.0
		//Fuel charge
		big = new BigDecimal(rateRecord.getFuelSurcharge());//37.18
		shipment2.getSelectedRate().setFuelCharges(big);
		//AccessorialCharges
		big = new BigDecimal(rateRecord.getAccessorialChargeLP());//0
		shipment2.getSelectedRate().setAccessorialCharges(big);
		//ServiceCharges
		big = new BigDecimal(rateRecord.getServiceCharge());//0
		shipment2.getSelectedRate().setServiceCharges(big);	
		//TotalCharges
		big = new BigDecimal(rateRecord.getTotalSurcharge());
		shipment2.getSelectedRate().setTotalCharges(big);
		//Mileage
		big = new BigDecimal(rateRecord.getMileageLP());
		shipment2.getSelectedRate().setMileage(big);
		//MileageSourceKey
		shipment2.getSelectedRate().setMileageSourceKey(rateRecord.getMileageSourceKey());
		
		//------------------------------
	
		/**ADDITIONAL INSURANCE**/
		shipment2.setDeclineAdditionalInsuranceIfApplicable(false);
		ArrayOfWSAccessorialService accessorialList=new ArrayOfWSAccessorialService();
		//List<WSAccessorialService> accessorialList=new ArrayList<WSAccessorialService>();
		String shipmentMode=shippingOrder.getMode();
		if(shipmentMode==null){
			shipmentMode="SHIP";
		}
		/**FROM ADDRESS CHECKLIST DESCRIPTION **/
		//AccessorialServices accessorial=new AccessorialServices();
		//CarrierChargeCode accessorial=new CarrierChargeCode();
		//List<CarrierChargeCode> accessorial1=new ArrayList<CarrierChargeCode>();
		CarrierChargeCode accessorial1=new CarrierChargeCode();
		CarrierChargeCode chargeCode=new CarrierChargeCode();
		if(shippingOrder.getFromAddressCheckList().getDescription()!=null && !shippingOrder.getFromAddressCheckList().getDescription().equalsIgnoreCase("") && !shipmentMode.equalsIgnoreCase("QUOTE")){
			//accessorial=shippingService.getAccessorialServiceByServiceGroupCode(shippingOrder.getFromAddressCheckList().getDescription(),2);
			chargeCode.setChargeCode(shippingOrder.getFromAddressCheckList().getDescription().substring(0, shippingOrder.getFromAddressCheckList().getDescription().length()-1)+"1");
			//accessorial=markupManagerService.getChargesByCode(chargeCode);
			accessorial1=markupManagerService.getChargesByChargeCodeAndCarrier(ShiplinxConstants.CARRIER_ESHIPPLUS, chargeCode.getChargeCode(),shippingOrder.getCustomerId());
			//if(accessorial1==null || accessorial1.size()<=0){
			if(accessorial1==null){
//				bowlNumber = bowlnNumber.substring(0,bowlNumber.length()-1) + "2";
				chargeCode.setChargeCode(shippingOrder.getFromAddressCheckList().getDescription().substring(0, shippingOrder.getFromAddressCheckList().getDescription().length()-1)+"0");
				accessorial1=markupManagerService.getChargesByChargeCodeAndCarrier(ShiplinxConstants.CARRIER_ESHIPPLUS, chargeCode.getChargeCode(),shippingOrder.getCustomerId());
			}
			/*if(accessorial1!=null && accessorial1.size()>0 && accessorial1.get(0).getChargeCodeLevel2()!= null){
			   if(accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.DANGEROUS_GOODS_CHARGE_CODE_LEVEL_2)){*/
				if(accessorial1!=null  && accessorial1.getChargeCodeLevel2()!= null){
									if(accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.DANGEROUS_GOODS_CHARGE_CODE_LEVEL_2)){
					shipment2.setHazardousMaterialShipment(true);
					shipment2.setHazardousMaterialContactName(shippingOrder.getFromAddress().getContactName());
					shipment2.setHazardousMaterialContactEmail(shippingOrder.getFromAddress().getEmailAddress());
					shipment2.setHazardousMaterialContactMobile(shippingOrder.getFromAddress().getMobilePhoneNo());
					shipment2.setHazardousMaterialContactPhone(shippingOrder.getFromAddress().getPhoneNo());
				}
				WSAccessorialService addditional=new WSAccessorialService();
				/*addditional.setBillingCode(accessorial1.get(0).getChargeCodeLevel2());
				addditional.setServiceCode(accessorial1.get(0).getChargeCodeLevel2());
				addditional.setServiceDescription(accessorial1.get(0).getChargeDesc());
				if((!accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_PICKUP_CHARGE_CODE_LEVEL_2)&&!accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_DELIVERY_CHARGE_CODE_LEVEL_2)) || !shippingOrder.getFromAddressCheckList().isCommercialBusiness()){*/
				addditional.setBillingCode(accessorial1.getChargeCodeLevel2());
								addditional.setServiceCode(accessorial1.getChargeCodeLevel2());
								addditional.setServiceDescription(accessorial1.getChargeDesc());
								if((!accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_PICKUP_CHARGE_CODE_LEVEL_2)&&!accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_DELIVERY_CHARGE_CODE_LEVEL_2)) || !shippingOrder.getFromAddressCheckList().isCommercialBusiness()){	
				accessorialList.getWSAccessorialService().add(addditional);
				}
			}
		shipment2.setAccessorials(accessorialList);
		}
		
		/**TO ADDRESS CHECKLIST DESCRIPTION **/
		if(shippingOrder.getToAddressCheckList().getDescription()!=null && !shippingOrder.getToAddressCheckList().getDescription().equalsIgnoreCase("") && !shipmentMode.equalsIgnoreCase("QUOTE")){
			//accessorial=shippingService.getAccessorialServiceByServiceGroupCode(shippingOrder.getFromAddressCheckList().getDescription(),2);
			chargeCode.setChargeCode(shippingOrder.getToAddressCheckList().getDescription().substring(0, shippingOrder.getToAddressCheckList().getDescription().length()-1)+"2");
			accessorial1=markupManagerService.getChargesByChargeCodeAndCarrier(ShiplinxConstants.CARRIER_ESHIPPLUS, chargeCode.getChargeCode(),shippingOrder.getCustomerId());
			/*if(accessorial1==null || accessorial1.size()<=0){*/
			if(accessorial1==null){
//				bowlNumber = bowlnNumber.substring(0,bowlNumber.length()-1) + "2";
				chargeCode.setChargeCode(shippingOrder.getToAddressCheckList().getDescription().substring(0, shippingOrder.getToAddressCheckList().getDescription().length()-1)+"0");
				accessorial1=markupManagerService.getChargesByChargeCodeAndCarrier(ShiplinxConstants.CARRIER_ESHIPPLUS, chargeCode.getChargeCode(),shippingOrder.getCustomerId());
			}
			/*if(accessorial1!=null && accessorial1.size()>0 && accessorial1.get(0).getChargeCodeLevel2()!= null){
				if(accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.DANGEROUS_GOODS_CHARGE_CODE_LEVEL_2)){*/
			if(accessorial1!=null && accessorial1.getChargeCodeLevel2()!= null){
								if(accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.DANGEROUS_GOODS_CHARGE_CODE_LEVEL_2)){
					shipment2.setHazardousMaterialShipment(true);
					shipment2.setHazardousMaterialContactName(shippingOrder.getToAddress().getContactName());
					shipment2.setHazardousMaterialContactEmail(shippingOrder.getToAddress().getEmailAddress());
					shipment2.setHazardousMaterialContactMobile(shippingOrder.getToAddress().getMobilePhoneNo());
					shipment2.setHazardousMaterialContactPhone(shippingOrder.getToAddress().getPhoneNo());
				}
				WSAccessorialService addditional=new WSAccessorialService();
				/*addditional.setBillingCode(accessorial1.get(0).getChargeCodeLevel2());
				addditional.setServiceCode(accessorial1.get(0).getChargeCodeLevel2());
				addditional.setServiceDescription(accessorial1.get(0).getChargeDesc());
				if((!accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_PICKUP_CHARGE_CODE_LEVEL_2)&&!accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_DELIVERY_CHARGE_CODE_LEVEL_2)) || !shippingOrder.getToAddressCheckList().isCommercialBusiness()){*/
				addditional.setBillingCode(accessorial1.getChargeCodeLevel2());
								addditional.setServiceCode(accessorial1.getChargeCodeLevel2());
								addditional.setServiceDescription(accessorial1.getChargeDesc());
								if((!accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_PICKUP_CHARGE_CODE_LEVEL_2)&&!accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_DELIVERY_CHARGE_CODE_LEVEL_2)) || !shippingOrder.getToAddressCheckList().isCommercialBusiness()){
					accessorialList.getWSAccessorialService().add(addditional);
				}
			}
		shipment2.setAccessorials(accessorialList);
		}
		
		/**TEMP CONTROL**/
		if(shippingOrder.getTemperature()!=null && shippingOrder.getTempControl()){	
			WSAccessorialService additional=new WSAccessorialService();
			additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.TEMP_CONTROL_CHARGE_CODE,shippingOrder.getCustomerId());
			if(shipment2.getAccessorials()!=null){
				shipment2.getAccessorials().getWSAccessorialService().add(additional);
			}else{
				ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
				accessorialSubList.getWSAccessorialService().add(additional);
				shipment2.setAccessorials(accessorialSubList);
			}
		}
		/**HAZARDOUS SHIPMENT **/
		if(shippingOrder.getTemperature()!=null && shippingOrder.getDangerousGoods()!=0){
			shipment2.setHazardousMaterialShipment(true);
			shipment2.setHazardousMaterialContactName(shippingOrder.getFromAddress().getContactName());
			shipment2.setHazardousMaterialContactEmail(shippingOrder.getFromAddress().getEmailAddress());
			shipment2.setHazardousMaterialContactMobile(shippingOrder.getFromAddress().getMobilePhoneNo());
			shipment2.setHazardousMaterialContactPhone(shippingOrder.getFromAddress().getPhoneNo());
			WSAccessorialService additional=new WSAccessorialService();
			additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.DANGEROUS_GOODS_CHARGE_CODE,shippingOrder.getCustomerId());
			if(shipment2.getAccessorials()!=null){
				shipment2.getAccessorials().getWSAccessorialService().add(additional);
			}else{
				ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
				accessorialSubList.getWSAccessorialService().add(additional);
				shipment2.setAccessorials(accessorialSubList);
			}
		}
		
		/**PALLET JACK PICKUP**/
		if(shippingOrder.getFromPalletJack()!=null && shippingOrder.getFromPalletJack()){
			WSAccessorialService additional=new WSAccessorialService();
			additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.PALLET_JACK_REQUIRED_PICKUP_CHARGE_CODE,shippingOrder.getCustomerId());
			if(shipment2.getAccessorials()!=null){
				shipment2.getAccessorials().getWSAccessorialService().add(additional);
			}else{
				ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
				accessorialSubList.getWSAccessorialService().add(additional);
				shipment2.setAccessorials(accessorialSubList);
			}
		}
		/**PALLET JACK DELIVERY**/
		if(shippingOrder.getFromPalletJack()!=null && shippingOrder.getFromPalletJack()){
			WSAccessorialService additional=new WSAccessorialService();
			additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.PALLET_JACK_REQUIRED_DELIVERY_CHARGE_CODE,shippingOrder.getCustomerId());
			if(shipment2.getAccessorials()!=null){
				shipment2.getAccessorials().getWSAccessorialService().add(additional);
			}else{
				ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
				accessorialSubList.getWSAccessorialService().add(additional);
				shipment2.setAccessorials(accessorialSubList);
			}
		}
		/**POWER TAILGATE PICKUP**/
		if(shippingOrder.isFromTailgate() !=null && shippingOrder.isFromTailgate()){
			
			WSAccessorialService additional=new WSAccessorialService();
			additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.POWER_TAILGATE_PICKUP_CHARGE_CODE,shippingOrder.getCustomerId());
			if(shipment2.getAccessorials()!=null){
				shipment2.getAccessorials().getWSAccessorialService().add(additional);
			}else{
				ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
				accessorialSubList.getWSAccessorialService().add(additional);
				shipment2.setAccessorials(accessorialSubList);
			}
		}
		/**POWER TAILGATE DELIVERY**/
		if(shippingOrder.isToTailgate()!=null && shippingOrder.isToTailgate()){
			WSAccessorialService additional=new WSAccessorialService();
			additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.POWER_TAILGATE_DELIVERY_CHARGE_CODE,shippingOrder.getCustomerId());
			if(shipment2.getAccessorials()!=null){
				shipment2.getAccessorials().getWSAccessorialService().add(additional);
			}else{
				ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
				accessorialSubList.getWSAccessorialService().add(additional);
				shipment2.setAccessorials(accessorialSubList);
			}
		}

		if(shippingOrder.getToAddress()!=null && shippingOrder.getToAddress().isResidential()){
			WSAccessorialService additional=new WSAccessorialService();
			additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.RESIDENTIAL_DELIVERY_CHARGE_CODE,shippingOrder.getCustomerId());
			if(shipment2.getAccessorials()!=null){
				shipment2.getAccessorials().getWSAccessorialService().add(additional);
			}else{
				ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
				accessorialSubList.getWSAccessorialService().add(additional);
				shipment2.setAccessorials(accessorialSubList);
			}
		}
		/**INSIDE PICKUP**/
		if(shippingOrder.isInsidePickup()){
			WSAccessorialService additional=new WSAccessorialService();
			additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.INSIDE_PICKUP_CHARGE_CODE,shippingOrder.getCustomerId());
			if(shipment2.getAccessorials()!=null){
				shipment2.getAccessorials().getWSAccessorialService().add(additional);
			}else{
				ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
				accessorialSubList.getWSAccessorialService().add(additional);
				shipment2.setAccessorials(accessorialSubList);
			}
		}
//		/**INSIDE DELIVERY**/
//		if(shippingOrder.isI){
//			WSAccessorialService additional=new WSAccessorialService();
//			additional=assignAccessorialServiceDetails(ShiplinxConstants.INSIDE_DELIVERY_CHARGE_CODE);
//			if(shipment2.getAccessorials()!=null){
//				shipment2.getAccessorials().getWSAccessorialService().add(additional);
//			}else{
//				ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
//				accessorialSubList.getWSAccessorialService().add(additional);
//				shipment2.setAccessorials(accessorialSubList);
//			}
//		}
		ArrayOfWSAccessorialService tempServiceList=new ArrayOfWSAccessorialService();
		if(shipment2.getAccessorials()!= null && shipment2.getAccessorials().getWSAccessorialService()!=null){
			for(WSAccessorialService service:shipment2.getAccessorials().getWSAccessorialService()){
					boolean flagServiceDublicate=false;
					for(WSAccessorialService TempService:tempServiceList.getWSAccessorialService()){
						if(TempService.getServiceCode().equalsIgnoreCase(service.getServiceCode())){
							flagServiceDublicate=true;
						}
					}
					if(!flagServiceDublicate){
						tempServiceList.getWSAccessorialService().add(service);
					}
			}
		}
		if(tempServiceList!=null && tempServiceList.getWSAccessorialService()!=null && tempServiceList.getWSAccessorialService().size()>0){
			shipment2.getAccessorials().getWSAccessorialService().clear();
			shipment2.setAccessorials(tempServiceList);
		}

		String specialInstructions="";
		/**HOLD FOR PICKUP**/
		if(shippingOrder.getHoldForPickupRequired() !=null && shippingOrder.getHoldForPickupRequired()){
			specialInstructions="Hold For Pickup ";
		}
		//Pickup required 
		//Appointment pickup or deliver
		if(shippingOrder.isAppointmentPickup() || shippingOrder.isAppointmentDelivery()){
			specialInstructions=specialInstructions+"Appointment  ";
			if(shippingOrder.isAppointmentPickup()){
				specialInstructions=specialInstructions+"Pickup ";
			}
			if((shippingOrder.isAppointmentPickup()&&shippingOrder.isAppointmentDelivery())){
				specialInstructions=specialInstructions+"and ";
			}
			if(shippingOrder.isAppointmentDelivery()){
				specialInstructions=specialInstructions+"Deliver ";
			}
			specialInstructions=specialInstructions+"Required  ";
			
		}
		
		/**PRIOR TO PICKUP OR DELIVERY**/
		if(shippingOrder.getToAddressCheckList()!=null && shippingOrder.getToAddressCheckList().isPriorToPickup()){
				specialInstructions=specialInstructions+"Notify Pickup of Deliver ";
		}
		/**Floor Number**/
		if(shippingOrder.isInsidePickup() && shippingOrder.getFromFloorNo()!=-1){
			specialInstructions=specialInstructions+"Pickup Floor "+shippingOrder.getFromAddressCheckList().getFloorNo();
		}
		if(shippingOrder.getInsideDelivery()!=null && shippingOrder.getInsideDelivery() && shippingOrder.getToFloorNo()!=-1){
			specialInstructions=specialInstructions+"Delivery Floor "+shippingOrder.getToAddressCheckList().getFloorNo();
		}
		/**Temperature **/
		if(shippingOrder.getTemperature()!=null && shippingOrder.getTempControl()){
			if(shippingOrder.getTemperature()!=null && !shippingOrder.getTemperature().equalsIgnoreCase("")){
				specialInstructions=specialInstructions+"Temperature "+shippingOrder.getTemperature();
			}
		}
		if(!specialInstructions.equalsIgnoreCase("")){
			shipment2.getDestination().setSpecialInstructions(specialInstructions);
		}
		return shipment2;

	}
	
	
	public float findFreightClass(Package pack){
		double length=pack.getLength().doubleValue();
		double height=pack.getHeight().doubleValue();
		double width=pack.getWidth().doubleValue();
		double weight=pack.getWeight().doubleValue();
		double cubicFeet=length*height*width;
		cubicFeet=cubicFeet/ShiplinxConstants.CUBIC_INCHES_TO_FEET;
		
	    BigDecimal cubicFeetBigDecimal = new BigDecimal(cubicFeet);
	    BigDecimal RoundedCF = cubicFeetBigDecimal.setScale(2, RoundingMode.CEILING);
		double classRate=weight/RoundedCF.doubleValue();
		
		Float freightClass=0f;
		if(classRate>=50.00){
			freightClass=50.0f;
		}
		else if(classRate>=35.00){
			freightClass=55.0f;
		}
		else if(classRate>=30.00){
			freightClass=60.0f;
		}
		else if(classRate>=22.50){
			freightClass=65.0f;
		}
		else if(classRate>=15.00){
			freightClass=70.0f;
		}else if(classRate>=12.00){
			freightClass=85.0f;
		}else if(classRate>=10.00){
			freightClass=92.5f;
		}else if(classRate>=8.00){
			freightClass=100.0f;
		}else if(classRate>=6.00){
			freightClass=125.0f;
		}else if(classRate>=4.00){
			freightClass=150.0f;
		}else if(classRate>=2.00){
			freightClass=250.0f;
		}else if(classRate>=1.00){
			freightClass=300.0f;
		}else if(classRate>=0){
			freightClass=400.0f;
		}
		
		return freightClass;
	}
	
	@Override
	public boolean cancelOrder(ShippingOrder order,
		CustomerCarrier customerCarrier) {
		WSBookingRequest wsCancelRequest=new WSBookingRequest();
		String shipmentNumber=order.getMasterTrackingNum().toString();
		AuthenticationToken authenticationtoken = AuthenticationProvider.authendication();
		WSShipment2 shipment2 = new WSShipment2();
		EShipPlusWSv4 eshipplusws4=new EShipPlusWSv4();
		WSReturn Shipment2 = eshipplusws4.getEShipPlusWSv4Soap().requestShipmentCancellation(shipmentNumber, authenticationtoken);
		for(WSMessage msg:Shipment2.getMessages().getWSMessage()){
			if(msg.getValue().equalsIgnoreCase("Shipment Cancel Request Complete!")){
				return true;
			}
		}
		//WSShipmentStatus bol=GetShipmentStatus(order);
		return false;
	}
	
	public WSShipmentStatus GetShipmentStatus(ShippingOrder order) {
		String shipmentNumber=order.getMasterTrackingNum().toString();
		//AuthenticationToken authenticationtoken = AuthenticationProvider.authendication();
		AuthenticationToken authenticationtoken = null;
				CustomerCarrier customerCarrier=null;
			if(order!=null)
				{
				 customerCarrier=carrierDAO.getCarrierAccount(0l,order.getBusinessId(),order.getCarrierId(),"US", null);
				}
				if(customerCarrier!=null)
				{
				 authenticationtoken = AuthenticationProvider.authendication(customerCarrier);
				}
				else
				{
					authenticationtoken = AuthenticationProvider.authendication();
				}
		EShipPlusWSv4 eshipplusws4=new EShipPlusWSv4();
		WSShipmentStatus wsshipmentStatus=new WSShipmentStatus();
		wsshipmentStatus=eshipplusws4.getEShipPlusWSv4Soap().getShipmentStatus(shipmentNumber, authenticationtoken);
		return wsshipmentStatus;
	}
	
	@Override
	public String getTrackingURL(ShippingOrder o) {
		StringBuffer tracking_numbers = new StringBuffer();
		List<Package> packages = o.getPackages();
		
		for(Package p:packages){
			tracking_numbers.append(p.getTrackingNumber());
			tracking_numbers.append(",");
		}
		
		//String url = "http://www.fedex.com/Tracking?ascend_header=1&clienttype=dotcom&cntry_code=us&language=english&tracknumbers=" + tracking_numbers.toString();
		String url = "http://www.eshipplus.com/=" + tracking_numbers.toString();
		return url;
	}
	@Override
	public void generateShippingLabel(OutputStream outputStream, long orderId,
			CustomerCarrier customerCarrier) {
		log.debug("Attempting to retrieve shipping label for order with id " + orderId);
		ShippingOrder order=new ShippingOrder();
		 User user = UserUtil.getMmrUser();
	
		
//		long start = System.currentTimeMillis();	
//		try{
//			FedExLabelGenerator label_gen = new FedExLabelGenerator();
//			label_gen.setShippingDAO(this.shippingDAO);
//			label_gen.generateShippingLabel(shippingDAO.getShippingOrder(orderId), outputStream);
//		}catch (Exception e) {
//			e.printStackTrace();
//			log.debug("--------"+e);
//
//		}
//		long end = System.currentTimeMillis();
//		log.debug("Time (in ms) to generate shipping label: " + (end-start));
		
		   try {
			   	
			   	  order = shippingService.getShippingOrder(orderId);
			      List<ShippingLabel> shippingLabels = shippingDAO
			          .getLabelsByOrderId(order.getId().longValue());

			      int page = 1;
			      PDFRenderer pdfRenderer = new PDFRenderer();

			      java.util.ArrayList srcList = new ArrayList();
			      for (ShippingLabel label : shippingLabels) {

			        String pdfFileName = pdfRenderer.getUniqueTempPDFFileName("label" + order.getOrderNum()
			            + page);
			        File f = new File(pdfFileName);
			        BufferedOutputStream sbos = new BufferedOutputStream(new FileOutputStream(f));
			        
			        if(user.getUserRole().equalsIgnoreCase("busadmin") && !label.isLabelUpdated()){
			        	sbos.write(label.getOriginalLabel());
			        }else{
			        	sbos.write(label.getLabel());
			        }
			        
			        sbos.close();
			        srcList.add(pdfFileName);
			        page++;
			      }
			      // delete temp files
			      pdfRenderer.concatPDF(srcList, outputStream);
			      pdfRenderer.deleteFiles(srcList);

			    } catch (Exception e) {
			     e.printStackTrace();
			    }
	}
	@Override
	public void requestPickup(Pickup pickup) {
//		try 
//    	{
//    		//get the customer
//        	Customer customer = customerService.getCustomerInfoByCustomerId(pickup.getCustomerId());
//    	    if(pickup !=null)
//    	    {	
//    		    PickupWebServiceClient pickupWebServiceClient=new PickupWebServiceClient(pickup.getCarrierAccount());
//    		    //This method will set all the credentials as per from - to address logic
//    		    pickupWebServiceClient.authenticationInfo();
//    		    WebAuthenticationDetail detail = pickupWebServiceClient.createPickupkWebAuthenticationDetail();
//    		    ClientDetail clientDetail = pickupWebServiceClient.createPickupClientDetail();
//    			Localization localization = new Localization("EN","ES");
//    			clientDetail.setLocalization(localization);
//    			TransactionDetail transactionDetail = new TransactionDetail();
//    			transactionDetail.setCustomerTransactionId("CreatePickup_v3");
//    			
//    			// <v3:Version>
//    			VersionId version=new VersionId();
//    			version.setServiceId("disp");
//    			version.setMajor(3);
//    			version.setIntermediate(0);
//    			version.setMinor(0);
//    			
//    			// <v3:OriginDetail>
//    			PickupOriginDetail pickupOriginDetail = new PickupOriginDetail();
//    			pickupOriginDetail.setUseAccountAddress(false);
//    			
//    			Contact contact=new Contact();
//    			contact.setPersonName(pickup.getAddress().getContactName());
//    			
//    			//API Request doesnot contains company name.
//    			//if(pickup.getCompanyName() != null)
//    		   // contact.setCompanyName(pickup.getAddress().get);
//    			//else
//    			contact.setCompanyName("");
//    	
//    		    contact.setEMailAddress(pickup.getAddress().getEmailAddress());
//    			ContactAndAddress contactAndAddress=new ContactAndAddress(); //<v3:PickupLocation>
//    			contactAndAddress.setContact(contact); //<v3:Contact>
//    			
//    			String[] streetLines = {pickup.getAddress().getAddress1(),pickup.getAddress().getAddress2()};// <v3:Address>
//    			Address pickupAddress = new Address();
//    			pickupAddress.setStreetLines(streetLines);
//    			pickupAddress.setCity(pickup.getAddress().getCity());
//    			pickupAddress.setStateOrProvinceCode(pickup.getAddress().getProvinceCode());
//    			pickupAddress.setPostalCode(pickup.getAddress().getPostalCode());
//    			pickupAddress.setCountryCode(pickup.getAddress().getCountryCode());
//    			contactAndAddress.setAddress(pickupAddress);
//    			
//    			pickupOriginDetail.setPickupLocation(contactAndAddress);  
//    			if(pickup.getPickupLocation().equalsIgnoreCase("Front Door"))
//    				pickupOriginDetail.setPackageLocation(PickupBuildingLocationType.FRONT);// <v3:PackageLocation>
//    			else if(pickup.getPickupLocation().equalsIgnoreCase("Back Door"))
//    				pickupOriginDetail.setPackageLocation(PickupBuildingLocationType.REAR);// <v3:PackageLocation>
//    			else if(pickup.getPickupLocation().equalsIgnoreCase("Side Door"))
//    				pickupOriginDetail.setPackageLocation(PickupBuildingLocationType.SIDE);// <v3:PackageLocation>
//    			else //set it to front by default
//    				pickupOriginDetail.setPackageLocation(PickupBuildingLocationType.FRONT);// <v3:PackageLocation>
//    			pickupOriginDetail.setBuildingPart(BuildingPartCode.DEPARTMENT);//  <v3:BuildingPart>
//    			pickupOriginDetail.setBuildingPartDescription("OPERATIONS");// <v3:BuildingPartDescription>
//    			
//    			Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));			
//     			cal.setTime(pickup.getPickupDate());
//    			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(pickup.getReadyHour()));
//    			cal.set(Calendar.MINUTE, Integer.parseInt(pickup.getReadyMin()));
//    			
//    			pickupOriginDetail.setReadyTimestamp(cal); //  <v3:ReadyTimestamp>
//    			
//    			String pickCloseTime= pickup.getCloseHour()+":"+pickup.getCloseMin()+":"+"00";
//    			pickupOriginDetail.setCompanyCloseTime(new Time(pickCloseTime));
//    	       
//    	        CreatePickupRequest request = new CreatePickupRequest();
//    			request.setWebAuthenticationDetail(detail);
//    			request.setClientDetail(clientDetail);
//    			request.setTransactionDetail(transactionDetail);
//    			//request.setAssociatedAccountNumber(accountNumber);
//    			request.setVersion(version);
//    			request.setOriginDetail(pickupOriginDetail);
//    			PositiveInteger packageCount = new PositiveInteger(pickup.getQuantity()+"");
//    			request.setPackageCount(packageCount);
//
//    			// <v3:TotalWeight>
//			    com.fedex.ws.pickup.v3.Weight  weight=new com.fedex.ws.pickup.v3.Weight ();
//				if(pickup.getWeightUnit().equalsIgnoreCase(ShiplinxConstants.WEIGHT_UNITS_LBS))
//					weight.setUnits(com.fedex.ws.pickup.v3.WeightUnits.LB);
//				else
//					weight.setUnits(com.fedex.ws.pickup.v3.WeightUnits.KG);
//				
//				weight.setValue(new BigDecimal(pickup.getTotalWeight()));
//				request.setTotalWeight(weight);
//				
//				if(!StringUtil.isEmpty(pickup.getServiceCode()) && (pickup.getServiceCode().contains("GROUND")))					
//					request.setCarrierCode(CarrierCodeType.FDXG);				
//				else //default to express
//					request.setCarrierCode(CarrierCodeType.FDXE); 
//    			//         <v3:OversizePackageCount>
//    			if(pickup.getOversizeQuantity()>0)
//    			{
//    				PositiveInteger oversizePackageCount= new PositiveInteger(pickup.getOversizeQuantity() + "");
//    				request.setOversizePackageCount(oversizePackageCount);
//    			}
//    			//<v3:CommodityDescription>
//    			request.setCommodityDescription("Freight");
//    			//    <v3:CountryRelationship>
//    			if(pickup.getAddress().getCountryCode().equalsIgnoreCase(pickup.getDestinationCountryCode()))
//    				request.setCountryRelationship(CountryRelationshipType.DOMESTIC);
//    			else
//    				request.setCountryRelationship(CountryRelationshipType.INTERNATIONAL);
//    			//PickupWebServiceClient  pickupWebServiceClient= new PickupWebServiceClient(order);
//    			
//    			log.debug("Pickup Request::::" + Common.getXMLOfObject(request));
//    			CreatePickupReply reply = pickupWebServiceClient.sendRequest(request);
//    			log.debug("Pickup Reply::::" + Common.getXMLOfObject(reply));
//    			
//    			boolean success = true;
//     			if (!isPickupResponseOk(reply.getHighestSeverity())) // check if the call was successful
//    			{
//    				log.debug("ERROR FedEx Pickup::"+reply.getNotifications()[0].getMessage());
//    				throw new FedExException(reply.getNotifications()[0].getMessage());
//    			}
//
//    			if(!success || StringUtil.isEmpty(reply.getPickupConfirmationNumber()))
//    				throw new ShiplinxException();
//    			
//    	        log.info("--- Fedex Pickup done Confirmation Number :---" + reply.getPickupConfirmationNumber());
//    	        pickup.setConfirmationNum(reply.getPickupConfirmationNumber());
//     	    }//end of if pickup!=null
//		} 
//    	catch (ShiplinxException e) 
//    	{
//    		log.error("Error occured in request Pickup",e);
//    		throw e;
//    	}
//    	catch (Exception e) 
//    	{
//    		log.error("Error occured in request Pickup",e);
//    		throw new ShiplinxException();
//    	}
//    	
//		
}
	
	public WSShipment2 setOrderDetails(ShippingOrder shippingOrder){
		markupManagerService = (MarkupManager)MmrBeanLocator.getInstance().findBean("markupManagerService");
		WSShipment2 wsshipment2 = new WSShipment2();
		WSLocation2 originLocation = new WSLocation2();
		WSLocation2 destinationLocation = new WSLocation2();
		WSCountry originCountry = new WSCountry();
		WSCountry destinationCountry = new WSCountry();
			try {
				com.meritconinc.shiplinx.model.Address fromadd = shippingOrder.getFromAddress();
				com.meritconinc.shiplinx.model.Address toadd = shippingOrder.getToAddress();
				
				/**SET THE ORIGIN COUNTRY DETAILS*/
				originLocation.setDescription(shippingOrder.getFromInstructions());//""
				originLocation.setPostalCode(fromadd.getPostalCode());//14150
				originLocation.setCity(fromadd.getCity());//TONAWANDA
				originLocation.setState(fromadd.getBrokerCode());//CN
				originLocation.setStreet(fromadd.getAddress1());//155 - 6855 Airport Road
				originLocation.setContact(fromadd.getContactName());//Tim Smith
				originLocation.setPhone(fromadd.getPhoneNo());//9056732999
				
				/**SET THE ORIGIN COUNTRY**/
				originCountry.setCode(fromadd.getCountryCode());//US
				originCountry.setUsesPostalCode(true);//true
				originLocation.setCountry(originCountry);
				wsshipment2.setOrigin(originLocation);
				
				/**SET THE DESTINATION DETAILS **/
				destinationLocation.setDescription(shippingOrder.getToInstructions());//""
				destinationLocation.setPostalCode(toadd.getPostalCode());//48174
				destinationLocation.setCity(toadd.getCity());//ROMULUS
				destinationLocation.setState(toadd.getBrokerCode());//CN
				destinationLocation.setStreet(toadd.getAddress1());//107 Lawrence Paqueette Ind. Dr.
				destinationLocation.setContact(toadd.getContactName());//Gary Cole
				destinationLocation.setPhone(toadd.getPhoneNo());//5182984400
				
				/**SET THE DESTINATION COUNTRY**/
				destinationCountry.setCode(toadd.getCountryCode());//US
				destinationCountry.setUsesPostalCode(true);
				destinationLocation.setCountry(destinationCountry);
				wsshipment2.setDestination(destinationLocation);
				
				/**SET DATE AND TIME **/
				
				Pickup pickup= new Pickup();
				pickup.getPickupDate();
				
				 XMLGregorianCalendar result = null;
			        Timestamp date = shippingOrder.getScheduledShipDate();
			        //date.setTime(date.getTime()+ 1 * 1000 * 60 * 60 * 24);
			        GregorianCalendar gregorianCalendar = null;
			        try {
			            gregorianCalendar =(GregorianCalendar) GregorianCalendar.getInstance();
			            gregorianCalendar.setTime(date);
			            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

			        } catch (Exception ex) {
			        	log.debug("---XMLGregorianCalendar Error----"+ex);
			        }
			        //result.setDay(31);
				wsshipment2.setShipmentDate(result);//2014-07-21T00:00:00.000+05:30
				
				
				
				/**SET EARLIEST PICKUP **/
				WSTime2 wsTime2 = new WSTime2();
				wsTime2.setTime(shippingOrder.getPickup().getReadyHour()+":"+shippingOrder.getPickup().getReadyMin());
				wsshipment2.setEarliestPickup(wsTime2);
				
				/**SET LATEST PICKUP**/
				WSTime2 wsTimeLatestPickup = new WSTime2();
				wsTimeLatestPickup.setTime(shippingOrder.getPickup().getCloseHour()+":"+shippingOrder.getPickup().getCloseMin());
				wsshipment2.setLatestPickup(wsTimeLatestPickup);
				
				/**SET TIME EARLIEST**/
				WSTime2 wsTimeEarliest = new WSTime2();
				wsTimeEarliest.setTime("09:30");
				wsshipment2.setEarliestDelivery(wsTimeEarliest);
				
				/**SET LATEST DELIVERY**/
				WSTime2 wsTimelatest = new WSTime2();
				wsTimelatest.setTime("17:30");
				wsshipment2.setLatestDelivery(wsTimelatest);
				
				/**SET ITEM PACKAGE, QUANTITY**/
				ArrayOfWSItem2 arrayofwsitem22 = new ArrayOfWSItem2();
				
				/**SETTING THE PACKAGE TYPE**/
				int packageType = 0;
				if(shippingOrder.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_ENVELOPE)
				{
					packageType = ShiplinxConstants.PACKAGE_TYPE_ENVELOPE;	
				}else if(shippingOrder.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PAK)
				{
					packageType = ShiplinxConstants.PACKAGE_TYPE_PAK;	
				}else if(shippingOrder.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PACKAGE)
				{
					packageType = ShiplinxConstants.PACKAGE_TYPE_PACKAGE;	
				}else if(shippingOrder.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PALLET)
				{
					packageType = ShiplinxConstants.PACKAGE_TYPE_PALLET;	
				}
				ArrayList<WSItem2> itemList = new ArrayList<WSItem2>();
				/**ITERATING THE PACKAGES**/
				List<EshipplusPackage> packageList=new ArrayList<EshipplusPackage>();
				//Getting Eship package List
				packageList=shippingService.getEshipplusPackagesList();
				for(Package pack : shippingOrder.getPackages()){
					WSItem2 item2 = new WSItem2();
					item2.setWeight(new BigDecimal(pack.getWeight().toString()));//500
					item2.setPackagingQuantity(1);//1
					item2.setLength(new BigDecimal(pack.getLength().toString()));//48.00
					item2.setHeight(new BigDecimal(pack.getHeight().toString()));//48.00
					item2.setWidth(new BigDecimal(pack.getWidth().toString()));//48.00
					item2.setStackable(false);
					if(pack.getDescription()==null || "".equalsIgnoreCase(pack.getDescription())){
						item2.setDescription("Item");//Item
					}else{
						item2.setDescription(pack.getDescription());
					}
					/**------------SETTING PACKAGE TYPE-------------**/
					WSItemPackage itemPackage2 = new WSItemPackage();
					for(EshipplusPackage packages:packageList){
						if((pack!=null && pack.getType()!=null) && (!pack.getType().equalsIgnoreCase("-1") && packages.getIcPackageName().equalsIgnoreCase(pack.getType().substring(4)))){
							itemPackage2.setKey(packages.getPackageKey());
						}
					}
					if(itemPackage2.getKey()==0){
						itemPackage2.setKey(267);
					}
					item2.setPackaging(itemPackage2);
					
					/**---------------------SETTING FREIGHT CLASS------------------**/
					WSFreightClass freightClass2 = new WSFreightClass();
					Float f_class=findFreightClass(pack);
					Double freightClass = f_class.doubleValue();
					try{
						log.debug("---Handled Exception cause:no freight class selected----");
						freightClass = Double.valueOf(pack.getFreightClass());
						
					}catch(Exception e){
						e.printStackTrace();
					}
					freightClass2.setFreightClass(freightClass);//125
					item2.setFreightClass(freightClass2);
					
					/**adding the item in to the list.**/
					itemList.add(item2);
				}
				arrayofwsitem22.wsItem2 = itemList;
				wsshipment2.setItems(arrayofwsitem22);
				/**ADDITIONAL INSURANCE**/
				wsshipment2.setDeclineAdditionalInsuranceIfApplicable(false);
				ArrayOfWSAccessorialService accessorialList=new ArrayOfWSAccessorialService();
				//List<WSAccessorialService> accessorialList=new ArrayList<WSAccessorialService>();
				String shipmentMode=shippingOrder.getMode();
				if(shipmentMode==null){
					shipmentMode="SHIP";
				}
				/**FROM ADDRESS CHECKLIST DESCRIPTION **/
				//AccessorialServices accessorial=new AccessorialServices();
				//CarrierChargeCode accessorial=new CarrierChargeCode();
				//List<CarrierChargeCode> accessorial1=new ArrayList<CarrierChargeCode>();
				CarrierChargeCode accessorial1=new CarrierChargeCode();
				CarrierChargeCode chargeCode=new CarrierChargeCode();
				if(shippingOrder.getFromAddressCheckList().getDescription()!=null && !shippingOrder.getFromAddressCheckList().getDescription().equalsIgnoreCase("") && !shipmentMode.equalsIgnoreCase("QUOTE")){
					//accessorial=shippingService.getAccessorialServiceByServiceGroupCode(shippingOrder.getFromAddressCheckList().getDescription(),2);
					chargeCode.setChargeCode(shippingOrder.getFromAddressCheckList().getDescription().substring(0, shippingOrder.getFromAddressCheckList().getDescription().length()-1)+"1");
					//accessorial=markupManagerService.getChargesByCode(chargeCode);
					accessorial1=markupManagerService.getChargesByChargeCodeAndCarrier(ShiplinxConstants.CARRIER_ESHIPPLUS, chargeCode.getChargeCode(),shippingOrder.getCustomerId());
					//if(accessorial1==null || accessorial1.size()<=0){
					if(accessorial1==null){
//						bowlNumber = bowlnNumber.substring(0,bowlNumber.length()-1) + "2";
						chargeCode.setChargeCode(shippingOrder.getFromAddressCheckList().getDescription().substring(0, shippingOrder.getFromAddressCheckList().getDescription().length()-1)+"0");
						accessorial1=markupManagerService.getChargesByChargeCodeAndCarrier(ShiplinxConstants.CARRIER_ESHIPPLUS, chargeCode.getChargeCode(),shippingOrder.getCustomerId());
					}
					/*if((accessorial1!=null && accessorial1.size()>0) && accessorial1.get(0).getChargeCodeLevel2()!= null){
						if(accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.DANGEROUS_GOODS_CHARGE_CODE_LEVEL_2)){*/
					if((accessorial1!=null ) && accessorial1.getChargeCodeLevel2()!= null){
												if(accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.DANGEROUS_GOODS_CHARGE_CODE_LEVEL_2)){
							wsshipment2.setHazardousMaterialShipment(true);
							wsshipment2.setHazardousMaterialContactName(shippingOrder.getFromAddress().getContactName());
							wsshipment2.setHazardousMaterialContactEmail(shippingOrder.getFromAddress().getEmailAddress());
							wsshipment2.setHazardousMaterialContactMobile(shippingOrder.getFromAddress().getMobilePhoneNo());
							wsshipment2.setHazardousMaterialContactPhone(shippingOrder.getFromAddress().getPhoneNo());
						}
						WSAccessorialService addditional=new WSAccessorialService();
						/*addditional.setBillingCode(accessorial1.get(0).getChargeCodeLevel2());
						addditional.setServiceCode(accessorial1.get(0).getChargeCodeLevel2());
						addditional.setServiceDescription(accessorial1.get(0).getChargeDesc());
						if((!accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_PICKUP_CHARGE_CODE_LEVEL_2)&&!accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_DELIVERY_CHARGE_CODE_LEVEL_2)) || !shippingOrder.getFromAddressCheckList().isCommercialBusiness()){*/
						addditional.setBillingCode(accessorial1.getChargeCodeLevel2());
												addditional.setServiceCode(accessorial1.getChargeCodeLevel2());
												addditional.setServiceDescription(accessorial1.getChargeDesc());
												if((!accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_PICKUP_CHARGE_CODE_LEVEL_2)&&!accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_DELIVERY_CHARGE_CODE_LEVEL_2)) || !shippingOrder.getFromAddressCheckList().isCommercialBusiness()){
							accessorialList.getWSAccessorialService().add(addditional);
						}
					}
				wsshipment2.setAccessorials(accessorialList);
				}
				
				/**TO ADDRESS CHECKLIST DESCRIPTION **/
				if(shippingOrder.getToAddressCheckList().getDescription()!=null && !shippingOrder.getToAddressCheckList().getDescription().equalsIgnoreCase("") && !shipmentMode.equalsIgnoreCase("QUOTE")){
					//accessorial=shippingService.getAccessorialServiceByServiceGroupCode(shippingOrder.getFromAddressCheckList().getDescription(),2);
					chargeCode.setChargeCode(shippingOrder.getToAddressCheckList().getDescription().substring(0, shippingOrder.getToAddressCheckList().getDescription().length()-1)+"2");
					accessorial1=markupManagerService.getChargesByChargeCodeAndCarrier(ShiplinxConstants.CARRIER_ESHIPPLUS, chargeCode.getChargeCode(),shippingOrder.getCustomerId());
					//if(accessorial1==null || accessorial1.size()<=0){
					if(accessorial1==null ){
//						bowlNumber = bowlnNumber.substring(0,bowlNumber.length()-1) + "2";
						chargeCode.setChargeCode(shippingOrder.getToAddressCheckList().getDescription().substring(0, shippingOrder.getToAddressCheckList().getDescription().length()-1)+"0");
						accessorial1=markupManagerService.getChargesByChargeCodeAndCarrier(ShiplinxConstants.CARRIER_ESHIPPLUS, chargeCode.getChargeCode(),shippingOrder.getCustomerId());
					}
					/*if(accessorial1!=null && accessorial1.size()>0 && accessorial1.get(0).getChargeCodeLevel2()!= null){
						if(accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.DANGEROUS_GOODS_CHARGE_CODE_LEVEL_2)){*/
					if(accessorial1!=null && accessorial1.getChargeCodeLevel2()!= null){
												if(accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.DANGEROUS_GOODS_CHARGE_CODE_LEVEL_2)){
							wsshipment2.setHazardousMaterialShipment(true);
							wsshipment2.setHazardousMaterialContactName(shippingOrder.getToAddress().getContactName());
							wsshipment2.setHazardousMaterialContactEmail(shippingOrder.getToAddress().getEmailAddress());
							wsshipment2.setHazardousMaterialContactMobile(shippingOrder.getToAddress().getMobilePhoneNo());
							wsshipment2.setHazardousMaterialContactPhone(shippingOrder.getToAddress().getPhoneNo());
						}
						WSAccessorialService addditional=new WSAccessorialService();
						/*addditional.setBillingCode(accessorial1.get(0).getChargeCodeLevel2());
						addditional.setServiceCode(accessorial1.get(0).getChargeCodeLevel2());
						addditional.setServiceDescription(accessorial1.get(0).getChargeDesc());
						if((!accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_PICKUP_CHARGE_CODE_LEVEL_2)&&!accessorial1.get(0).getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_DELIVERY_CHARGE_CODE_LEVEL_2)) || !shippingOrder.getToAddressCheckList().isCommercialBusiness()){*/
						addditional.setBillingCode(accessorial1.getChargeCodeLevel2());
												addditional.setServiceCode(accessorial1.getChargeCodeLevel2());
												addditional.setServiceDescription(accessorial1.getChargeDesc());
												if((!accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_PICKUP_CHARGE_CODE_LEVEL_2)&&!accessorial1.getChargeCodeLevel2().equalsIgnoreCase(ShiplinxConstants.NON_COMMERCIAL_DELIVERY_CHARGE_CODE_LEVEL_2)) || !shippingOrder.getToAddressCheckList().isCommercialBusiness()){
							accessorialList.getWSAccessorialService().add(addditional);
						}
					}
				wsshipment2.setAccessorials(accessorialList);
				}
				
				/**TEMP CONTROL**/
				if(shippingOrder.getTempControl()!=null && shippingOrder.getTempControl()){	
					WSAccessorialService additional=new WSAccessorialService();
					additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.TEMP_CONTROL_CHARGE_CODE,shippingOrder.getCustomerId());
					if(wsshipment2.getAccessorials()!=null){
						wsshipment2.getAccessorials().getWSAccessorialService().add(additional);
					}else{
						ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
						accessorialSubList.getWSAccessorialService().add(additional);
						wsshipment2.setAccessorials(accessorialSubList);
					}
				}
				/**HAZARDOUS SHIPMENT **/
				if(shippingOrder.getDangerousGoods()!=0){
					wsshipment2.setHazardousMaterialShipment(true);
					wsshipment2.setHazardousMaterialContactName(shippingOrder.getFromAddress().getContactName());
					wsshipment2.setHazardousMaterialContactEmail(shippingOrder.getFromAddress().getEmailAddress());
					wsshipment2.setHazardousMaterialContactMobile(shippingOrder.getFromAddress().getMobilePhoneNo());
					wsshipment2.setHazardousMaterialContactPhone(shippingOrder.getFromAddress().getPhoneNo());
					WSAccessorialService additional=new WSAccessorialService();
					additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.DANGEROUS_GOODS_CHARGE_CODE,shippingOrder.getCustomerId());
					if(wsshipment2.getAccessorials()!=null){
						wsshipment2.getAccessorials().getWSAccessorialService().add(additional);
					}else{
						ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
						accessorialSubList.getWSAccessorialService().add(additional);
						wsshipment2.setAccessorials(accessorialSubList);
					}
				}
				
				/**PALLET JACK PICKUP**/
				if(shippingOrder.getFromPalletJack()!=null && shippingOrder.getFromPalletJack()){
					WSAccessorialService additional=new WSAccessorialService();
					additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.PALLET_JACK_REQUIRED_PICKUP_CHARGE_CODE,shippingOrder.getCustomerId());
					if(wsshipment2.getAccessorials()!=null){
						wsshipment2.getAccessorials().getWSAccessorialService().add(additional);
					}else{
						ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
						accessorialSubList.getWSAccessorialService().add(additional);
						wsshipment2.setAccessorials(accessorialSubList);
					}
				}
				/**PALLET JACK DELIVERY**/
				if(shippingOrder.getToPalletJack()!=null && shippingOrder.getToPalletJack()){
					WSAccessorialService additional=new WSAccessorialService();
					additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.PALLET_JACK_REQUIRED_DELIVERY_CHARGE_CODE,shippingOrder.getCustomerId());
					if(wsshipment2.getAccessorials()!=null){
						wsshipment2.getAccessorials().getWSAccessorialService().add(additional);
					}else{
						ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
						accessorialSubList.getWSAccessorialService().add(additional);
						wsshipment2.setAccessorials(accessorialSubList);
					}
				}
				/**POWER TAILGATE PICKUP**/
				if(shippingOrder.isFromTailgate() !=null && shippingOrder.isFromTailgate()){
					
					WSAccessorialService additional=new WSAccessorialService();
					additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.POWER_TAILGATE_PICKUP_CHARGE_CODE,shippingOrder.getCustomerId());
					if(wsshipment2.getAccessorials()!=null){
						wsshipment2.getAccessorials().getWSAccessorialService().add(additional);
					}else{
						ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
						accessorialSubList.getWSAccessorialService().add(additional);
						wsshipment2.setAccessorials(accessorialSubList);
					}
				}
				/**POWER TAILGATE DELIVERY**/
				if(shippingOrder.isToTailgate()!=null && shippingOrder.isToTailgate()){
					WSAccessorialService additional=new WSAccessorialService();
					additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.POWER_TAILGATE_DELIVERY_CHARGE_CODE,shippingOrder.getCustomerId());
					if(wsshipment2.getAccessorials()!=null){
						wsshipment2.getAccessorials().getWSAccessorialService().add(additional);
					}else{
						ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
						accessorialSubList.getWSAccessorialService().add(additional);
						wsshipment2.setAccessorials(accessorialSubList);
					}
				}

				if(shippingOrder.getToAddress()!=null && shippingOrder.getToAddress().isResidential()){
					WSAccessorialService additional=new WSAccessorialService();
					additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.RESIDENTIAL_DELIVERY_CHARGE_CODE,shippingOrder.getCustomerId());
					if(wsshipment2.getAccessorials()!=null){
						wsshipment2.getAccessorials().getWSAccessorialService().add(additional);
					}else{
						ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
						accessorialSubList.getWSAccessorialService().add(additional);
						wsshipment2.setAccessorials(accessorialSubList);
					}
				}
				/**INSIDE PICKUP**/
				if(shippingOrder.isInsidePickup()){
					WSAccessorialService additional=new WSAccessorialService();
					additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.INSIDE_PICKUP_CHARGE_CODE,shippingOrder.getCustomerId());
					if(wsshipment2.getAccessorials()!=null){
						wsshipment2.getAccessorials().getWSAccessorialService().add(additional);
					}else{
						ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
						accessorialSubList.getWSAccessorialService().add(additional);
						wsshipment2.setAccessorials(accessorialSubList);
					}
				}
//				/**INSIDE DELIVERY**/
//				if(shippingOrder.isI){
//					WSAccessorialService additional=new WSAccessorialService();
//					additional=assignAccessorialServiceDetails(ShiplinxConstants.INSIDE_DELIVERY_CHARGE_CODE);
//					if(wsshipment2.getAccessorials()!=null){
//						wsshipment2.getAccessorials().getWSAccessorialService().add(additional);
//					}else{
//						ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
//						accessorialSubList.getWSAccessorialService().add(additional);
//						wsshipment2.setAccessorials(accessorialSubList);
//					}
//				}

				/**APPOINTMENT DELIVERY/PICKUP**/
				if(shippingOrder.isAppointmentPickup() || shippingOrder.isAppointmentDelivery()){
					WSAccessorialService additional=new WSAccessorialService();
					additional=assignAccessorialServiceDetails(ShiplinxConstants.CARRIER_ESHIPPLUS,ShiplinxConstants.DESTINATION_NOTIFY_OR_APPOINTMENT_CODE,shippingOrder.getCustomerId());
					if(wsshipment2.getAccessorials()!=null){
						wsshipment2.getAccessorials().getWSAccessorialService().add(additional);
					}else{
						ArrayOfWSAccessorialService accessorialSubList=new ArrayOfWSAccessorialService();
						accessorialSubList.getWSAccessorialService().add(additional);
						wsshipment2.setAccessorials(accessorialSubList);
					}
				}
				
				ArrayOfWSAccessorialService tempServiceList=new ArrayOfWSAccessorialService();
				if(wsshipment2.getAccessorials()!= null && wsshipment2.getAccessorials().getWSAccessorialService()!=null){
					for(WSAccessorialService service:wsshipment2.getAccessorials().getWSAccessorialService()){
							boolean flagServiceDublicate=false;
							for(WSAccessorialService TempService:tempServiceList.getWSAccessorialService()){
								if(TempService.getServiceCode().equalsIgnoreCase(service.getServiceCode())){
									flagServiceDublicate=true;
								}
							}
							if(!flagServiceDublicate){
								tempServiceList.getWSAccessorialService().add(service);
							}
					}
				}
				if(tempServiceList!=null && tempServiceList.getWSAccessorialService()!=null && tempServiceList.getWSAccessorialService().size()>0){
					wsshipment2.getAccessorials().getWSAccessorialService().clear();
					wsshipment2.setAccessorials(tempServiceList);
				}

				
				String specialInstructions="";
				/**HOLD FOR PICKUP**/
				if(shippingOrder.getHoldForPickupRequired() !=null && shippingOrder.getHoldForPickupRequired()){
					specialInstructions="Hold For Pickup ";
				}
				
				//Pickup required 
				//Appointment pickup or deliver
				if(shippingOrder.isAppointmentPickup() || shippingOrder.isAppointmentDelivery()){
					specialInstructions=specialInstructions+"Appointment  ";
					if(shippingOrder.isAppointmentPickup()){
						specialInstructions=specialInstructions+"Pickup ";
					}
					if((shippingOrder.isAppointmentPickup()&&shippingOrder.isAppointmentDelivery())){
						specialInstructions=specialInstructions+"and ";
					}
					if(shippingOrder.isAppointmentDelivery()){
						specialInstructions=specialInstructions+"Deliver ";
					}
					specialInstructions=specialInstructions+"Required  ";
					
				}
				
				/**PRIOR TO PICKUP OR DELIVERY**/
				if(shippingOrder.getToAddressCheckList()!=null && shippingOrder.getToAddressCheckList().isPriorToPickup()){
						specialInstructions=specialInstructions+"Notify Pickup of Deliver ";
				}
				/**Floor Number**/
				if(shippingOrder.isInsidePickup() && shippingOrder.getFromFloorNo()!=-1){
					specialInstructions=specialInstructions+"Pickup Floor "+shippingOrder.getFromAddressCheckList().getFloorNo();
				}
				if(shippingOrder.getInsideDelivery()!=null && shippingOrder.getInsideDelivery() && shippingOrder.getToFloorNo()!=-1){
					specialInstructions=specialInstructions+"Delivery Floor "+shippingOrder.getToAddressCheckList().getFloorNo();
				}
				/**Temperature **/
				if(shippingOrder.getTemperature()!=null && shippingOrder.getTempControl()){
					if(shippingOrder.getTemperature()!=null && !shippingOrder.getTemperature().equalsIgnoreCase("")){
						specialInstructions=specialInstructions+"Temperature "+shippingOrder.getTemperature();
					}
				}
				if(!specialInstructions.equalsIgnoreCase("")){
					wsshipment2.getDestination().setSpecialInstructions(specialInstructions);
				}
				return wsshipment2;
	
				} catch (Exception e) {
					e.printStackTrace();
			}
			return wsshipment2;
		}
	
	private WSAccessorialService assignAccessorialServiceDetails(long carrierId,String chargeCode,long customerId){
		//Method to reuse code 
		markupManagerService = (MarkupManager)MmrBeanLocator.getInstance().findBean("markupManagerService");
			markupManagerService = (MarkupManager) MmrBeanLocator.getInstance().findBean("markupManagerService");
			//List<CarrierChargeCode> accessorial=new ArrayList<CarrierChargeCode>();
			CarrierChargeCode accessorial=new CarrierChargeCode();
			CarrierChargeCode chargeCodeObj=new CarrierChargeCode();
			WSAccessorialService additional=new WSAccessorialService();
		try{
			chargeCodeObj.setChargeCode(chargeCode);
			accessorial=markupManagerService.getChargesByChargeCodeAndCarrier(ShiplinxConstants.CARRIER_ESHIPPLUS, chargeCode,customerId);
			/*if(accessorial!=null && accessorial.size()>0){
				additional.setBillingCode(accessorial.get(0).getChargeCodeLevel2());
				additional.setServiceCode(accessorial.get(0).getChargeCodeLevel2());
				additional.setServiceDescription(accessorial.get(0).getChargeDesc());*/
			if(accessorial!=null ){
								additional.setBillingCode(accessorial.getChargeCodeLevel2());
								additional.setServiceCode(accessorial.getChargeCodeLevel2());
								additional.setServiceDescription(accessorial.getChargeDesc());
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception in accessorial fetch");
		}
		return additional;
	}
	
	private static boolean isPickupResponseOk(com.fedex.ws.pickup.v3.NotificationSeverityType notificationSeverityType) {
//		if (notificationSeverityType == null) {
//			return false;
//		}
//		if (notificationSeverityType.getValue().equalsIgnoreCase(NotificationSeverityType.WARNING.getValue()) ||
//			notificationSeverityType.getValue().equalsIgnoreCase(NotificationSeverityType.NOTE.getValue())    ||
//			notificationSeverityType.getValue().equalsIgnoreCase(NotificationSeverityType.SUCCESS.getValue())) {
//			return true;
//		}
			return false; 
	 }
	@Override
	public boolean cancelPickup(Pickup pickup) {
		return false;
	}
	@Override
	public List<CarrierErrorMessage> getErrorMessages() {
		return null;
	}
	@Override
	public void uploadRates(Service service, long customerId, long busId,
			boolean isOverwrite) throws Exception {
		
	}
	public ShippingDAO getShippingDAO() {
		return shippingDAO;
	}
	public void setShippingDAO(ShippingDAO shippingDAO) {
		this.shippingDAO = shippingDAO;
	}
	public ShippingService getShippingService() {
		return shippingService;
	}
	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}
	public CarrierServiceDAO getCarrierServiceDAO() {
		return carrierServiceDAO;
	}
	public void setCarrierServiceDAO(CarrierServiceDAO carrierServiceDAO) {
		this.carrierServiceDAO = carrierServiceDAO;
	}
	public CustomerManager getCustomerService() {
		return customerService;
	}
	public void setCustomerService(CustomerManager customerService) {
		this.customerService = customerService;
	}
	
	

}
