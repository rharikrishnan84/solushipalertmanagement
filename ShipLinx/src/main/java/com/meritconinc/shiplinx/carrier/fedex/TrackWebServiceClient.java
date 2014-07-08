package com.meritconinc.shiplinx.carrier.fedex;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.carrier.fedex.stub.track.Address;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.ClientDetail;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.Notification;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.NotificationSeverityType;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.TrackDetail;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.TrackEvent;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.TrackIdentifierType;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.TrackPackageIdentifier;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.TrackPortType;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.TrackReply;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.TrackRequest;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.TrackServiceLocator;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.TransactionDetail;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.VersionId;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.WebAuthenticationCredential;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.WebAuthenticationDetail;
import com.meritconinc.shiplinx.carrier.fedex.stub.track.Weight;
import com.meritconinc.shiplinx.carrier.utils.FedExException;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;

/** 
 * Demo of using the Track service with Axis 
 * to track a shipment.
 * <p>
 * com.fedex.track.stub is generated via WSDL2Java, like this:<br>
 * <pre>
 * java org.apache.axis.wsdl.WSDL2Java -w -p com.fedex.track.stub http://www.fedex.com/...../TrackService?wsdl
 * </pre>
 * 
 * This sample code has been tested with JDK 5 and Apache Axis 1.4
 */
public class TrackWebServiceClient {
	private static final Logger log = LogManager.getLogger(CancelPackageWebServiceClient.class);

	private ShippingOrder shippingOrder;
	private CustomerCarrier customerCarrier;
	private String accountNumber;
	private String meterNumber;
	private String key;
	private String password;
	private String trackingNumber;    
	 
	public void init(){
		log.debug("shippingOrder.getMasterTrackingNumber():::"+shippingOrder.getMasterTrackingNum()); 
		
		trackingNumber=shippingOrder.getMasterTrackingNum();
		
		log.debug("shippingOrder.isLive():::"+shippingOrder.isLive()); 
		
		if(shippingOrder.isLive()){
			accountNumber = customerCarrier.getAccountNumber1(); //"510087224";//
			meterNumber = customerCarrier.getProperty1();  //"100001805";//
			key = customerCarrier.getProperty2(); //"MBtE3ecZ7OJ3T4L9";//
			password = customerCarrier.getProperty3(); // "MzGOvVE9sg7Xluw0XWTwjFq5Z"; //
		}else{
			accountNumber = customerCarrier.getAccountNumber1(); 
			meterNumber = customerCarrier.getProperty1(); 
			key = customerCarrier.getProperty2(); 
			password = customerCarrier.getProperty3(); 
		}
		
		log.debug("accountNumber:::"+accountNumber+"---meterNumber:::"+meterNumber
				+"---key:::"+key+"---password:::"+password); 
	}
	
	public boolean sendRequest() throws FedExException{    

		//
	    TrackRequest request = new TrackRequest();

        request.setClientDetail(createClientDetail());
        request.setWebAuthenticationDetail(createWebAuthenticationDetail());
        //
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setCustomerTransactionId("java sample - Tracking Request"); //This is a reference field for the customer.  Any value can be used and will be provided in the response.
        request.setTransactionDetail(transactionDetail);
 
        //
        VersionId versionId = new VersionId("trck", 4, 0, 0);
        request.setVersion(versionId);
        //
        TrackPackageIdentifier packageIdentifier = new TrackPackageIdentifier();
        packageIdentifier.setValue(getTrackingNumber()); // tracking number
        packageIdentifier.setType(TrackIdentifierType.TRACKING_NUMBER_OR_DOORTAG); // Track identifier types are TRACKING_NUMBER_OR_DOORTAG, TRACKING_CONTROL_NUMBER, ....
        request.setPackageIdentifier(packageIdentifier);

	    //
		try {
			// Initializing the service
			TrackServiceLocator service;
			TrackPortType port;
			//
			service = new TrackServiceLocator();
			updateEndPoint(service);
			port = service.getTrackServicePort();
		    //
			
			log.debug("request is :"+FedEx.getXMLOfObject(request));
			
			
			TrackReply reply = port.track(request); // This is the call to the web service passing in a request object and returning a reply object
			//
			
			log.debug("reply is :"+FedEx.getXMLOfObject(reply));
			
			
			if (isResponseOk(reply.getHighestSeverity())) // check if the call was successful
			{
				log.debug("Tracking detail\n");
				TrackDetail td[] = reply.getTrackDetails();
				for (int i=0; i< td.length; i++) { // package detail information
					log.debug("Package # : " + td[i].getPackageSequenceNumber() + " and Package count: " + td[i].getPackageCount());
					log.debug("Tracking number: " + td[i].getTrackingNumber() + " and Tracking number unique identifier: " + td[i].getTrackingNumberUniqueIdentifier());
					log.debug("Status: " + td[i].getStatusCode() + " and description: " + td[i].getStatusDescription());
					if(td[i].getOtherIdentifiers() != null)
					{
						TrackPackageIdentifier[] tpi = td[i].getOtherIdentifiers();
						for (int j=0; j< tpi.length; j++) {
							log.debug(tpi[j].getType() + " " + tpi[j].getValue());
						}
					}
					print("Packaging", td[i].getPackaging());
					printWeight("Package weight", td[i].getPackageWeight());
					printWeight("Shipment weight", td[i].getShipmentWeight());
				
					print("Ship date & time", td[i].getShipTimestamp().toString());
					log.debug("Destination: " + td[i].getDestinationAddress().getCity() 
							+ " " + td[i].getDestinationAddress().getPostalCode()
							+ " " + td[i].getDestinationAddress().getCountryCode());
					

					TrackEvent[] trackEvents = td[i].getEvents();
					if (trackEvents != null) {
						log.debug("Events:");
						for (int k = 0; k < trackEvents.length; k++) {
							log.debug("  Event no.: " + k);
							TrackEvent trackEvent = trackEvents[k];
							if (trackEvent != null) {
								print("    Timestamp", trackEvent.getTimestamp());
								print("    Description", trackEvent.getEventDescription());
								Address address = trackEvent.getAddress();
								if (address != null) {
									print("    City", address.getCity());
									print("    State", address.getStateOrProvinceCode());
								}
							}
						}
					}
				}
			}

			getNotifications(reply.getNotifications());

			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true; 
	}
	
	private boolean isResponseOk(NotificationSeverityType notificationSeverityType) {
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
    

	private ClientDetail createClientDetail() {
		ClientDetail clientDetail = new ClientDetail();
		clientDetail.setAccountNumber(accountNumber);
		clientDetail.setMeterNumber(meterNumber);
		return clientDetail;
	}
	
	private WebAuthenticationDetail createWebAuthenticationDetail() {
		WebAuthenticationCredential wac = new WebAuthenticationCredential();
		wac.setKey(key);
		wac.setPassword(password);
		return new WebAuthenticationDetail(wac);
	}
	
	private void getNotifications(Notification[] notifications) {
		log.debug("Notifications:");
		if (notifications == null || notifications.length == 0) {
			log.debug("  No notifications returned");
		}
		for (int i=0; i < notifications.length; i++){
			Notification n = notifications[i];
			log.debug ("  Notification no. " + i + ": ");
			if (n == null) {
				log.debug("null");
				continue;
			} else {
				log.debug("");
			}
			NotificationSeverityType nst = n.getSeverity();

			log.debug("    Severity: " + (nst == null ? "null" : nst.getValue()));
			log.debug("    Code: " + n.getCode());
			log.debug("    Message: " + n.getMessage());
			log.debug("    Source: " + n.getSource());
		}
	}
	
	private void updateEndPoint(TrackServiceLocator serviceLocator) {
		String endPoint = System.getProperty("endPoint");
		if (endPoint != null) {
			serviceLocator.setTrackServicePortEndpointAddress(endPoint);
		}
	}


	private String getTrackingNumber() {
		return trackingNumber;
	}

	private void print(String msg, Object obj) {
		if (msg == null || obj == null) {
			return;
		}
		log.debug(msg + ": " + obj.toString());
	}
	
	
	private void printWeight(String msg, Weight weight) {
		if (msg == null || weight == null) {
			return;
		}
		log.debug(msg + ": " + weight.getValue() + " " + weight.getUnits());
	}
	public ShippingOrder getShippingOrder() {
		return shippingOrder;
	}
	public void setShippingOrder(ShippingOrder shippingOrder) {
		this.shippingOrder = shippingOrder;
	}


}
