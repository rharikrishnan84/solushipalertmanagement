package com.meritconinc.shiplinx.carrier.purolator.stub;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.carrier.purolator.PurolatorAPI;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.Address;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ArrayOfShipmentSummaryDetail;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.Error;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.NotificationEmails;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ObjectFactory;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.PhoneNumber;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.PickUpServiceContract;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.PickUpServiceContractSchedulePickUpValidationFaultFaultFaultMessage;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.PickUpServiceContractVoidPickUpValidationFaultFaultFaultMessage;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.PickupInstruction;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.RequestContext;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ResponseInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.SchedulePickUpRequestContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.SchedulePickUpResponseContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ShipmentSummary;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ShipmentSummaryDetail;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.VoidPickUpRequestContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.VoidPickUpResponseContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.Weight;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.WeightUnit;
import com.meritconinc.shiplinx.carrier.utils.PurolatorException;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class PickUpServiceClient{

	private static Logger logger = Logger.getLogger(PickUpServiceClient.class);

	public void voidPickup(Pickup pickup)
	{
		ObjectFactory objectFactory = new ObjectFactory();
	
		try
		{
			VoidPickUpRequestContainer reqContainer = new VoidPickUpRequestContainer();
			reqContainer.setPickUpConfirmationNumber(objectFactory.createVoidPickUpRequestContainerPickUpConfirmationNumber(pickup.getConfirmationNum()));			
		    
			VoidPickUpResponseContainer response = sendVoidPickupRequest(reqContainer, pickup);

			if(response.isPickUpVoided()){
				logger.error("Pickup " + pickup.getConfirmationNum() + " is voided!");
				return;
			}
				
			// Display the response
			String respInf = getResponse(response.getResponseInformation());
			if(respInf.length() > 0)
				throw new PurolatorException(respInf);
			    
		} catch(Exception e){
			//e.printStackTrace();
			logger.error("Exception ",e);
			throw new PurolatorException(e.getMessage());
		}
	}

	
	private String setWithMaxLength(String s, int max){
		if(s==null || s.length()==0)
			return "";
		
		if(s.length()<=max)
			return s;
		
		return s.substring(0, max);
		
	}

   public void schedulePickup(Pickup pickup)
	{
		ObjectFactory objectFactory = new ObjectFactory();
		SchedulePickUpRequestContainer reqContainer = new SchedulePickUpRequestContainer();
    		
		try
		{
			reqContainer.setBillingAccountNumber(pickup.getCarrierAccount().getAccountNumber1());
			
			reqContainer.setPartnerID(objectFactory.createSchedulePickUpRequestContainerPartnerID(""));
			PickupInstruction pkup = new PickupInstruction();
			pkup.setDate(FormattingUtil.getFormattedDate(pickup.getPickupDate(), FormattingUtil.DATE_FORMAT_WEB));
			pkup.setAnyTimeAfter(pickup.getReadyHour() + pickup.getReadyMin());
			pkup.setUntilTime(pickup.getCloseHour() + pickup.getCloseMin());
			
	    
			Weight totalWeight = new Weight();
			java.math.BigDecimal w = new java.math.BigDecimal(pickup.getTotalWeight());
			totalWeight.setValue(w);
			totalWeight.setWeightUnit(WeightUnit.LB);
			
			pkup.setTotalWeight(objectFactory.createWeight(totalWeight));
			pkup.setTotalPieces((int) pickup.getQuantity());
			
			if(pickup.getPickupLocation()==null || pickup.getPickupLocation().length()==0){
				pkup.setPickUpLocation(objectFactory.createPickupInstructionPickUpLocation("FrontDoor"));
			}
			else{
				pkup.setPickUpLocation(objectFactory.createPickupInstructionPickUpLocation(mapPickupLocation(pickup.getPickupLocation())));
			}
			
			String inst = pickup.getInstructions();
			if(inst!=null && inst.length()>0)
				pkup.setAdditionalInstructions(objectFactory.createPickupInstructionPickUpLocation(setWithMaxLength(pickup.getInstructions(), 70)));					
			
			reqContainer.setPickupInstruction(pkup);

			Address pkupaddr = new Address();
			
			com.meritconinc.shiplinx.model.Address shipFromAddress =  pickup.getAddress();
			pkupaddr.setName(setWithMaxLength(shipFromAddress.getContactName(),30));
			pkupaddr.setCompany(objectFactory.createAddressCompany(setWithMaxLength(shipFromAddress.getAbbreviationName(),30)));

			if(shipFromAddress.getAddress1()!=null){
				if(shipFromAddress.getAddress1().length()<=30) //max allowed length for this field is 25
					pkupaddr.setStreetName(shipFromAddress.getAddress1());
				else
				{
					pkupaddr.setStreetName(shipFromAddress.getAddress1().substring(0, 30));
					pkupaddr.setStreetAddress2(objectFactory.createAddressStreetAddress2(shipFromAddress.getAddress1().substring(30, shipFromAddress.getAddress1().length())));
				}
			}
			if(shipFromAddress.getAddress2()!=null){
				if(shipFromAddress.getAddress2().length()<=25) //max allowed length for this field is 25
					pkupaddr.setStreetAddress3(objectFactory.createAddressStreetAddress3(shipFromAddress.getAddress2()));
				else
					pkupaddr.setStreetAddress3(objectFactory.createAddressStreetAddress3(shipFromAddress.getAddress2().substring(0, 25)));
			}

			
			pkupaddr.setCity(shipFromAddress.getCity());
			pkupaddr.setProvince(shipFromAddress.getProvinceCode());
			pkupaddr.setCountry(shipFromAddress.getCountryCode());
			pkupaddr.setPostalCode(shipFromAddress.getPostalCode());

			PhoneNumber phone = setPhoneNumber(shipFromAddress.getPhoneNo());
			pkupaddr.setPhoneNumber(phone);
			
			if(pkupaddr.getProvince().equalsIgnoreCase("NF"))
				pkupaddr.setProvince("NL");
			if(pkupaddr.getProvince().equalsIgnoreCase("PQ"))
				pkupaddr.setProvince("QC");

			if(pkupaddr.getPostalCode().equalsIgnoreCase("N/A"))
				pkupaddr.setPostalCode("");
			if(pkupaddr.getProvince().equalsIgnoreCase("N/A"))
				pkupaddr.setProvince("");
				
			
			reqContainer.setAddress(pkupaddr);
			
			NotificationEmails ne = new NotificationEmails();
			ne.getNotificationEmail().add(pickup.getAddress().getEmailAddress());
			reqContainer.setNotificationEmails(objectFactory.createNotificationEmails(ne));		
			
			
			ShipmentSummaryDetail[] ssd = new ShipmentSummaryDetail[1];
			ssd[0] = new ShipmentSummaryDetail();
			if(pickup.getDestinationCountryCode().equalsIgnoreCase(ShiplinxConstants.US)){
				ssd[0].setDestinationCode("USA");
			}
			else if(pickup.getDestinationCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA)){
				ssd[0].setDestinationCode("DOM");	
			}
			else{
				ssd[0].setDestinationCode("INT");
			}
			
			ssd[0].setTotalPieces((int) pickup.getQuantity());
			
			Weight ssdWeight = new Weight();
			java.math.BigDecimal ssdw = new java.math.BigDecimal(pickup.getTotalWeight());			
			ssdWeight.setValue(ssdw);
			ssdWeight.setWeightUnit(WeightUnit.LB);			
			ssd[0].setTotalWeight(ssdWeight);
					
			ShipmentSummary ss = new ShipmentSummary();
			ArrayOfShipmentSummaryDetail arr = new ArrayOfShipmentSummaryDetail();
			arr.getShipmentSummaryDetail().add(ssd[0]);
			ss.setShipmentSummaryDetails(arr);
			reqContainer.setShipmentSummary(objectFactory.createShipmentSummary(ss));

			SchedulePickUpResponseContainer response = sendSchedulePickupRequest(reqContainer, pickup);

			
			String pickUpConf = response.getPickUpConfirmationNumber().getValue();

			if(pickUpConf!=null && pickUpConf.length()>0) {
				pickup.setConfirmationNum(pickUpConf);
				return;
			}

			// Display the response
			String respInf = getResponse(response.getResponseInformation());
			if(respInf.length() > 0)
				throw new PurolatorException(respInf);

		} catch(Exception e){
			logger.error("CallSchedulePickUp Failed!", e);
			throw new PurolatorException(e.getMessage());
		}
		
	}
    	
    private PhoneNumber setPhoneNumber(String phNo)	{
    		PhoneNumber ph = new PhoneNumber();
    		phNo=phNo.replace("-","");
    		phNo=phNo.replace(" ","");
    		if(phNo.length()==0){
    			ph.setCountryCode("1");
    			ph.setAreaCode("888");
    			ph.setPhone("8888888");
    		}
    		else if(phNo.length()==10){ //US/Canada
    			ph.setCountryCode("1");
    			ph.setAreaCode(phNo.substring(0,3));			
    			ph.setPhone(phNo.substring(3, phNo.length()));
    		}
    		else if(phNo.length()>10){ 
    			ph.setCountryCode(phNo.substring(0,1));
    			ph.setAreaCode(phNo.substring(1,4));			
    			ph.setPhone(phNo.substring(4, phNo.length()));
    		}
    		
    		logger.debug("Phone number is : " + ph.getCountryCode() + ":" + ph.getAreaCode() + ":" + ph.getPhone());
    		
    		return ph;
    	}
    	
	

	private VoidPickUpResponseContainer sendVoidPickupRequest(VoidPickUpRequestContainer request, Pickup pickup) throws PurolatorException{

		VoidPickUpResponseContainer response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(PickUpServiceContract.class);
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(PurolatorAPI.TEST_URL_PICKUP);
			else
				factory.setAddress(PurolatorAPI.LIVE_URL_PICKUP);
			
			PickUpServiceContract client = (PickUpServiceContract) factory.create();
			
			Client clientProxy = ClientProxy.getClient(client);
			HTTPConduit http = (HTTPConduit) clientProxy.getConduit();

			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

			httpClientPolicy.setConnectionTimeout(36000);
			httpClientPolicy.setAllowChunking(false);
			httpClientPolicy.setReceiveTimeout(32000);
			
			AuthorizationPolicy authorization = new AuthorizationPolicy();

			
			// Production URL
			authorization.setUserName(pickup.getCarrierAccount().getProperty1());
			authorization.setPassword(pickup.getCarrierAccount().getProperty2());
			
			http.setAuthorization(authorization);
		
			// ----------- RequestContext
			RequestContext reqContext = getRequestContext();
			
			List<Header> headers = new ArrayList<Header>();
			Header dummyHeader = new Header(new QName("http://purolator.com/pws/datatypes/v1", "RequestContext"), reqContext,
			                                new JAXBDataBinding(RequestContext.class));
			headers.add(dummyHeader);

			
			((BindingProvider)client).getRequestContext().put(Header.HEADER_LIST, headers);

			response = client.voidPickUp(request);
			
			logger.debug("Response:" + response);
		} 
		catch(PickUpServiceContractVoidPickUpValidationFaultFaultFaultMessage sem){
			logger.error("Error sending pickup void request to Purolator", sem);
		}
		catch (Exception e) {
			logger.error("Error sending pickup void request to Purolator", e);
		}

		return response;
	}	

	private RequestContext getRequestContext() {
		RequestContext reqContext = new RequestContext();
		reqContext.setVersion("1.0");
		reqContext.setLanguage(com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.Language.EN);
		reqContext.setGroupID("1");
		reqContext.setRequestReference("RequestReference");
		return reqContext;
	}

	private String getResponse(ResponseInformation respInf){
		if (respInf == null)
			return "";

		StringBuilder stringBuilder = new StringBuilder();
		
		int i = 0;
		if (respInf.getErrors() != null && respInf.getErrors().getError() != null&& respInf.getErrors().getError().size() > 0)
		{
			for (Error error : respInf.getErrors().getError())
			{
				i++;
				stringBuilder.append(error.getCode());
				stringBuilder.append(":");
				stringBuilder.append(error.getDescription());
				stringBuilder.append("\n");
			}
		}
		return stringBuilder.toString();
	}

	private String mapPickupLocation(String location){

		if(location.equalsIgnoreCase("Front Door"))
			return "FrontDoor";
		else if(location.equalsIgnoreCase("Back Door"))
			return "BackDoor";
		else if(location.equalsIgnoreCase("Side Door"))
			return "SideDoor";
		else if(location.equalsIgnoreCase("Receiving"))
			return "Receiving";
		else if(location.equalsIgnoreCase("Reception"))
			return "Reception";
		else if(location.equalsIgnoreCase("Office"))
			return "Office";
		else if(location.equalsIgnoreCase("Mail Room"))
			return "MailRoom";
		else if(location.equalsIgnoreCase("Garage"))
			return "Garage";
		else if(location.equalsIgnoreCase("Upstairs"))
			return "BackDoor";
		else if(location.equalsIgnoreCase("Downstairs"))
			return "Basement";
		else if(location.equalsIgnoreCase("Guard House"))
			return "GateHouse";
		else if(location.equalsIgnoreCase("Third Party"))
			return "BackDoor";
		else if(location.equalsIgnoreCase("Warehouse"))
			return "Shipping";
		else return "FrontDesk";
			
		//these Purol locations are not mapped to our 'generic' pick up locations. 
		//if customer complains, we would have to add them to our jsp list (newShipment.jsp) and then map them here
//		public final static String[] pickUpLocations = {"","","","","BetweenDoors","OutsideDoor","Counter",
//			"PartsDepartment","Desk","Pharmacy","FrontDesk","ProShop","",
//			"","FrontPorch","","","Security","","ServiceCounter",
//			"Kiosk Lab","","LoadingDock","","Lobby","Mailbox"};


	}
	
	private SchedulePickUpResponseContainer sendSchedulePickupRequest(SchedulePickUpRequestContainer request, Pickup pickup) throws PurolatorException{

		logger.debug("Request:" + request);
		SchedulePickUpResponseContainer response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(PickUpServiceContract.class);
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(PurolatorAPI.TEST_URL_PICKUP);
			else
				factory.setAddress(PurolatorAPI.LIVE_URL_PICKUP);
			
			PickUpServiceContract client = (PickUpServiceContract) factory.create();
			
			Client clientProxy = ClientProxy.getClient(client);
			HTTPConduit http = (HTTPConduit) clientProxy.getConduit();

			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

			httpClientPolicy.setConnectionTimeout(36000);
			httpClientPolicy.setAllowChunking(false);
			httpClientPolicy.setReceiveTimeout(32000);
			
			AuthorizationPolicy authorization = new AuthorizationPolicy();

			
			// Production URL
			authorization.setUserName(pickup.getCarrierAccount().getProperty1());
			authorization.setPassword(pickup.getCarrierAccount().getProperty2());
			
			http.setAuthorization(authorization);
		
			// ----------- RequestContext
			RequestContext reqContext = getRequestContext();
			
			List<Header> headers = new ArrayList<Header>();
			Header dummyHeader = new Header(new QName("http://purolator.com/pws/datatypes/v1", "RequestContext"), reqContext,
			                                new JAXBDataBinding(RequestContext.class));
			headers.add(dummyHeader);

			
			((BindingProvider)client).getRequestContext().put(Header.HEADER_LIST, headers);

			response = client.schedulePickUp(request);
			
			logger.debug("Response:" + response);
		} 
		catch(PickUpServiceContractSchedulePickUpValidationFaultFaultFaultMessage sem){
			logger.error("Error sending pickup void request to Purolator", sem);
		}
		catch (Exception e) {
			logger.error("Error sending pickup void request to Purolator", e);
		}

		return response;
	}	

}