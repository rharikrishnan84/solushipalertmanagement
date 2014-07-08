package com.meritconinc.shiplinx.carrier.fedex;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.DeleteShipmentRequest;
import com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.DeletionControlType;
import com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.Notification;
import com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.NotificationSeverityType;
import com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.ShipPortType;
import com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.ShipServiceLocator;
import com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.TransactionDetail;
import com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.VersionId;
import com.meritconinc.shiplinx.carrier.utils.FedExException;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;



/** 
 * Sample code to call the FedEx Cancel Package Web Service 
 * <p>
 * com.fedex.cancelpackage.stub is generated via WSDL2Java, like this:<br>
 * <pre>
 * java org.apache.axis.wsdl.WSDL2Java -w -p com.fedex.ship.stub http://www.fedex.com/...../ShipService?wsdl
 * </pre>
 * 
 * This sample code has been tested with JDK 5 and Apache Axis 1.4
 */ 
public class CancelPackageWebServiceClient  extends FedExRequestHelper{

	private static final Logger log = LogManager.getLogger(CancelPackageWebServiceClient.class);

	private String trackingNumber;    

	public CancelPackageWebServiceClient(ShippingOrder order, CustomerCarrier customerCarrier)
	{
		super(order, customerCarrier);
	}

	
	public void init(){
		trackingNumber=order.getMasterTrackingNum();
		log.debug("Attempting to cancel FedEx order: "+ trackingNumber); 
		authenticationInfo();
	}
	
	public boolean sendRequest() throws FedExException{ 
		boolean isOrderCanceled=false;
		try{
			// Build a CancelPackageRequest object
			DeleteShipmentRequest request = new DeleteShipmentRequest(); 
			request.setClientDetail(createCancelClientDetail());
			request.setWebAuthenticationDetail(createCancelWebAuthenticationDetail());
			request.setDeletionControl(DeletionControlType.DELETE_ONE_PACKAGE);
			//
			TransactionDetail transactionDetail = new TransactionDetail();
			transactionDetail.setCustomerTransactionId("java sample - Cancel Package / Delete Shipment Request Transaction");  //This is a reference field for the customer.  Any value can be used and will be provided in the response.
			request.setTransactionDetail(transactionDetail);
			//
			VersionId versionId = new VersionId("ship", 6, 0, 0);
			request.setVersion(versionId);
			//
			//request.setCarrierCode(CarrierCodeType.FDXE); // CarrierCodeTypes are FDXC(Cargo), FDXE(Express), FDXG(Ground), FDCC(Custom Critical), FXFR(Freight)
			request.setTrackingNumber(getTrackingNumber()); // Replace with the tracking number of the package to cancel / delete
			//

			// Initialize the service
			ShipServiceLocator service;
			ShipPortType port;
			//
			service = new ShipServiceLocator();
			updateEndPoint(service);
			port = service.getShipServicePort();
			// This is the call to the web service passing in a CancelPackageRequest and returning a CancelPackageReply
			com.meritconinc.shiplinx.carrier.fedex.stub.cancelshipment.ShipmentReply reply = port.deleteShipment(request);
			//
			NotificationSeverityType nst = reply.getHighestSeverity();
			log.debug("CancelPackageReply HightestSeverity: " + nst.toString());
			if (isResponseOk(reply.getHighestSeverity()))
			{
				log.debug("Successful.");
				isOrderCanceled=true;
			}
			getNotifications(reply.getNotifications());

		}catch (Exception e){
			isOrderCanceled=false;
			log.debug(e.getMessage());
			throw new FedExException(e.getMessage());
		}
		return isOrderCanceled;
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

	private void updateEndPoint(ShipServiceLocator serviceLocator) {
		String endPoint;
		if(ShiplinxConstants.isTestMode())
			endPoint= LIVE_URL_RATE;	
		else
			endPoint= LIVE_URL_SERVICE;	
		if (endPoint != null) {
			serviceLocator.setShipServicePortEndpointAddress(endPoint);
		}
	}

	private String getTrackingNumber() {
		return trackingNumber;
	}

}
