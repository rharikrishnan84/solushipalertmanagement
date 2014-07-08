package com.meritconinc.shiplinx.carrier.fedex;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fedex.rate.stub.RateRequest;
import com.fedex.ws.pickup.v3.CreatePickupReply;
import com.fedex.ws.pickup.v3.CreatePickupRequest;
import com.fedex.ws.pickup.v3.PickupPortType;
import com.fedex.ws.pickup.v3.PickupServiceLocator;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class PickupWebServiceClient extends FedExRequestHelper{
	
	private static final Logger log = LogManager.getLogger(RateAvailableWebServiceClient.class);
	RateRequest request = new RateRequest();
	
	
	public PickupWebServiceClient(CustomerCarrier customerCarrier)
	{
		super(customerCarrier);
	} 
	
	// Send create pickup request 
	public CreatePickupReply sendRequest(CreatePickupRequest request) {  
		//ProcessShipmentReply reply=null;
		CreatePickupReply  reply=null;
		try {
			PickupServiceLocator service;
			PickupPortType port;
			service = new PickupServiceLocator();
			updateEndPoint(service); 
			port = service.getPickupServicePort();
			// This is the call to the web service passing in a RateRequest and returning a RateReply
			reply = port.createPickup(request); // Service call
			} 
		catch (Exception e) 
		{
		    log.error("error:"+e.getMessage(), e);
		} 
		return reply;
	}
	
	private static void updateEndPoint(PickupServiceLocator serviceLocator) {
		
		String endPoint;
		if(ShiplinxConstants.isTestMode()){
			endPoint=TEST_URL_PICKUP;	
			log.debug("PICKUP TEST MODE");
		}
		else{
			endPoint=LIVE_URL_PICKUP;	
			log.debug("PICKUP LIVE MODE");
		}	
		if (endPoint != null) {
			log.debug("VEBH FEDEX_PICKUP CALLIGN :"+endPoint);
			serviceLocator.setPickupServicePortEndpointAddress(endPoint);
		}
	}
	
	
}
