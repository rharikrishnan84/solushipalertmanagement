package com.meritconinc.shiplinx.carrier.purolator.stub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.carrier.purolator.PurolatorAPI;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.ArrayOfPIN;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.ArrayOfScan;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.ArrayOfTrackingInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.DeliveryScan;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.GetDeliveryDetailsRequestContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.GetDeliveryDetailsResponseContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.Language;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.OnDeliveryScan;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.PIN;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.ProofOfPickUpScan;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.RequestContext;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.Scan;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.TrackPackagesByPinRequestContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.TrackPackagesByPinResponseContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.TrackingInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.tracking.proxy.TrackingServiceContract;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class TrackingPackageClient {
	

	private static Logger log = Logger.getLogger(TrackingPackageClient.class);
	public RequestContext getRequestContext(){
		RequestContext requestContext=new RequestContext();
		requestContext.setLanguage(Language.EN);
		requestContext.setGroupID(null);
		requestContext.setVersion("1.1");
		requestContext.setRequestReference("RequestReference");
		return requestContext;
	}	
	
	public void  getdeliveryDetails(List<String> pins){
		try{
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			
			factory.setAddress(PurolatorAPI.LIVE_URL_TRACK);
			factory.setServiceClass(TrackingServiceContract.class);
			TrackingServiceContract client=(TrackingServiceContract)factory.create();
			
			Client clientProxy = ClientProxy.getClient(client);
			HTTPConduit http = (HTTPConduit) clientProxy.getConduit();

			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

			httpClientPolicy.setConnectionTimeout(36000);
			httpClientPolicy.setAllowChunking(false);
			httpClientPolicy.setReceiveTimeout(32000);
			
			AuthorizationPolicy authorization = new AuthorizationPolicy();

			authorization.setUserName("57e6f3e8d9c44bc788358c2728b09ae6");
			authorization.setPassword("Nqf%XaZ^");
			
			http.setAuthorization(authorization);
			
			RequestContext reqContext = getRequestContext();
			
			List<Header> headers = new ArrayList<Header>();
			Header dummyHeader = new Header(new QName("http://purolator.com/pws/datatypes/v1", "RequestContext"), reqContext,
			                                new JAXBDataBinding(RequestContext.class));
			headers.add(dummyHeader);

			
			((BindingProvider)client).getRequestContext().put(Header.HEADER_LIST, headers);

			ShippingDAO shippingDAO=(ShippingDAO) MmrBeanLocator.getInstance().findBean("shippingDAO");
			//Written By Mohan
						TrackPackagesByPinRequestContainer trackReqContainer = new TrackPackagesByPinRequestContainer();
						int pinSize=pins.size();
						int remaining=pinSize%50;
						int start=0,limit=50;
						int trackNoPartitionSize=pinSize/50;
						
						if(pinSize < 50){
							trackNoPartitionSize=1;
							limit=pinSize;
							remaining=0;
						}
							
						
						for(int j=1;j<=trackNoPartitionSize;j++){
							
							List<PIN> listOfPins = new ArrayList<PIN>();
						for(int i=start;i<limit;i++){
							PIN pin = new PIN();
							String trackingNumber = shippingDAO.getTrackingNumberFromPackage(pins.get(i));
							if(trackingNumber!=null && !trackingNumber.isEmpty()){
								log.debug("adding tracking number "+trackingNumber);
								pin.setValue(trackingNumber);
							}else{
								log.debug("adding tracking number "+pins.get(i));
							    pin.setValue(pins.get(i));
							}
							listOfPins.add(pin);
							
						}
						if(j==trackNoPartitionSize && remaining != 0){
							trackNoPartitionSize++;
							limit+=remaining;
						}
						else{
							limit+=50;
						}
						start+=50;
						
						ArrayOfPIN arrayOfPins = new ArrayOfPIN();
						arrayOfPins.getPIN().addAll(listOfPins);
						trackReqContainer.setPINs(arrayOfPins);
						TrackPackagesByPinResponseContainer trackRespContainer = client.trackPackagesByPin(trackReqContainer);
						JAXBElement<ArrayOfTrackingInformation> trackingInformationList = trackRespContainer.getTrackingInformationList();
						if (trackingInformationList == null || trackingInformationList.getValue() == null ||trackingInformationList.getValue().getTrackingInformation() == null)
			                return;
						for (TrackingInformation trackingInformation : trackingInformationList.getValue().getTrackingInformation())
			            {
							try{
							JAXBElement<ArrayOfScan> scans = trackingInformation.getScans();
							Scan scan = scans.getValue().getScan().get(0);
							String scanType = scan.getScanType();
							String scanDate = scan.getScanDate();
							String scanPin = scan.getPIN().getValue();
							int scanCount=scans.getValue().getScan().size();
							String contactName="";		
							log.debug("Tracking No ====="+scanPin);
							log.debug("Type ============"+scanType);
							log.debug("Scan Count ======"+scans.getValue().getScan().size());
							if (scan instanceof ProofOfPickUpScan) {			
								ProofOfPickUpScan proofOfPickUpScan = new ProofOfPickUpScan();
								proofOfPickUpScan = (ProofOfPickUpScan)scan;
								scanDate=proofOfPickUpScan.getScanDetails().getCommitedDeliveryDate();	
								contactName=proofOfPickUpScan.getScanDetails().getPickUpContactName();
								if(proofOfPickUpScan!=null){
									Map<String,Object> details=new HashMap<String,Object>();
									details.put("trackingNo",scanPin);
									details.put("podReceiver", contactName);
									details.put("podDate", scanDate);
									details.put("signatureImage", "");
									details.put("billStatus", null);
									details.put("statusId", ShiplinxConstants.STATUS_DELIVERED);
									shippingDAO.updateShippingOrderBillingStatus(details);
								}
							}
							else if (scan instanceof DeliveryScan)
							{
								DeliveryScan ds = new DeliveryScan();
								ds = (DeliveryScan)scan;
								GetDeliveryDetailsRequestContainer getDeliveryDetailsRequest=new GetDeliveryDetailsRequestContainer();
								getDeliveryDetailsRequest.setPIN(ds.getPIN());
								GetDeliveryDetailsResponseContainer getDeliveryDetailsResponseContainer = client.getDeliveryDetails(getDeliveryDetailsRequest);
								if(getDeliveryDetailsResponseContainer != null){
									DeliveryScan deliveryScan=getDeliveryDetailsResponseContainer.getDeliveryDetails().getValue();
									if( deliveryScan != null){
										Map<String,Object> details=new HashMap<String,Object>();
										details.put("trackingNo",deliveryScan.getPIN().getValue());
										details.put("podReceiver", deliveryScan.getScanDetails().getDeliverySignature());
										details.put("podDate", deliveryScan.getScanDate());
										details.put("signatureImage", deliveryScan.getScanDetails().getSignatureImage());
										details.put("billStatus", null);
										details.put("statusId",  ShiplinxConstants.STATUS_DELIVERED);
										shippingDAO.updateShippingOrderBillingStatus(details);
									}
								}
							}
							else if (scan instanceof OnDeliveryScan)
							{	
								OnDeliveryScan onDeliveryScan = new OnDeliveryScan();
								onDeliveryScan = (OnDeliveryScan)scan;	
								if( onDeliveryScan != null){
									Map<String,Object> details=new HashMap<String,Object>();
									details.put("trackingNo",scanPin);
									details.put("podReceiver","" );
									details.put("podDate", scanDate);
									details.put("signatureImage", "");
									details.put("billStatus", null);
									details.put("statusId", ShiplinxConstants.STATUS_DELIVERED);
									shippingDAO.updateShippingOrderBillingStatus(details);
								}
							}else if(scanType.equalsIgnoreCase("Undeliverable") && scanCount >1){
								GetDeliveryDetailsRequestContainer getDeliveryDetailsRequest=new GetDeliveryDetailsRequestContainer();
								getDeliveryDetailsRequest.setPIN(scan.getPIN());
								GetDeliveryDetailsResponseContainer getDeliveryDetailsResponseContainer = client.getDeliveryDetails(getDeliveryDetailsRequest);
								if(getDeliveryDetailsResponseContainer != null){
									DeliveryScan deliveryScan=getDeliveryDetailsResponseContainer.getDeliveryDetails().getValue();
									if( deliveryScan != null){
										Map<String,Object> details=new HashMap<String,Object>();
										details.put("trackingNo",deliveryScan.getPIN().getValue());
										details.put("podReceiver", deliveryScan.getScanDetails().getDeliverySignature());
										details.put("podDate", deliveryScan.getScanDate());
										details.put("signatureImage", deliveryScan.getScanDetails().getSignatureImage());
										details.put("billStatus", null);
										details.put("statusId",  ShiplinxConstants.STATUS_DELIVERED);
										shippingDAO.updateShippingOrderBillingStatus(details);
									}
								}else{
									Map<String,Object> details=new HashMap<String,Object>();
									details.put("trackingNo",scanPin);
									details.put("podReceiver","" );
									details.put("podDate", scanDate);
									details.put("signatureImage", "");
									details.put("billStatus", null);
									details.put("statusId", ShiplinxConstants.STATUS_DELIVERED);
									shippingDAO.updateShippingOrderBillingStatus(details);
								}
							}else if(scanType.equalsIgnoreCase("Undeliverable") && scanCount<=1){
								Map<String,Object> details=new HashMap<String,Object>();
								details.put("trackingNo",scanPin);
								details.put("billStatus", ShiplinxConstants.PAYMENT_EXCEPTION);
								details.put("message", "Order Never Shipped");
								details.put("podDate", scanDate);
								details.put("isPrivate", true);
								shippingDAO.updateLoggedEvent(details);
								Map<String,Object> status=new HashMap<String,Object>();
								status.put("trackingNo",scanPin);
								status.put("billStatus", ShiplinxConstants.PAYMENT_BILLING_EXCEPTION);
								status.put("podDate", null);
								status.put("podReceiver", null);
								status.put("signatureImage",null);
								status.put("statusId",  ShiplinxConstants.STATUS_CANCELLED);
								shippingDAO.updateShippingOrderBillingStatus(status);
							}
							}catch(Exception e){
								e.printStackTrace();
							}
			            }
						}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}
