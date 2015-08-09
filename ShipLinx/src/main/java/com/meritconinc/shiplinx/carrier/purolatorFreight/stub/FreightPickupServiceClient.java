package com.meritconinc.shiplinx.carrier.purolatorFreight.stub;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.Error;
import com.meritconinc.shiplinx.carrier.purolator.ws.pickup.proxy.ResponseInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.Address;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.ArrayOfLineItem;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.GetPickUpRequestContainer;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.GetPickUpResponseContainer;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.Language;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.LineItem;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.ObjectFactory;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.PaymentInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.PaymentType;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.PhoneNumber;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.PickUp;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.PickUpInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.ReceiverInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.RequestContext;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.SenderInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.ShipmentInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.Weight;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.WeightUnit;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.service.v1.FreightPickUpServiceContract;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.service.v1.FreightPickUpServiceContractGetFreightPickupValidationFaultFaultFaultMessage;
import com.meritconinc.shiplinx.carrier.utils.PurolatorException;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Pickup;
import com.meritconinc.shiplinx.model.ShippingOrder;


public class FreightPickupServiceClient {

 public void schedulePickup( Pickup pickups){
 GetPickUpRequestContainer getPickupRequestContainer=new GetPickUpRequestContainer();
 PickUp pickup=new PickUp();
 ShipmentInformation shipmentInformation=new ShipmentInformation();
 PickUpInformation pickUpInformation=new PickUpInformation();
 ArrayOfLineItem lineItemDetails = new ArrayOfLineItem();
 LineItem lineItem = new LineItem();
 Weight weight = new Weight();
 ObjectFactory objectFactory = new ObjectFactory();
 ShippingOrder shippingOrder = new ShippingOrder();
 if(pickups!=null && pickups.getCarrierId()>0){
 ShippingDAO shippingDAO = (ShippingDAO)MmrBeanLocator.getInstance().findBean("shippingDAO");
 shippingOrder = shippingDAO.getShippingOrder(pickups.getOrderId());
 }
 //======================== Sender Information ==================================
 pickup.setSenderInformation(setSenderInformation(shippingOrder));

 // ====================== Receiver Information ================================
 pickup.setReceiverInformation(setReceiverInformation(shippingOrder));

 // ====================== Shipment Information =================================

 lineItem.setLineNumber(shippingOrder.getQuantity());
 lineItem.setPieces(shippingOrder.getQuantity());
 lineItem.setHandlingUnit(1);
 lineItem.setHandlingUnitType("Pallet");
 weight.setValue(new BigDecimal(shippingOrder.getTotalWeight()));
 weight.setWeightUnit(WeightUnit.LB);
 lineItem.setWeight(weight);
 lineItemDetails.getLineItem().add(lineItem);
 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 JAXBElement<String> shipDate =objectFactory.createEstimateInformationShipmentDate(dateFormat.format(shippingOrder.getScheduledShipDate()).toString());
 JAXBElement<String> serviceTypeCode = objectFactory.createEstimateInformationServiceTypeCode("PurolatorFreightGround");
 shipmentInformation.setServiceTypeCode(serviceTypeCode);
 shipmentInformation.setShipmentDate(shipDate);
 shipmentInformation.setLineItemDetails(lineItemDetails);
 pickup.setShipmentDetails(shipmentInformation);

 // ======================== paymentinformation =============================================
 /*paymentInformation.setPaymentType(PaymentType.SENDER);
 paymentInformation.setRegisteredAccountNumber("4217962");
 JAXBElement<String> str = objectFactory.createPaymentInformationBillingAccountNumber("4217962");
 paymentInformation.setBillingAccountNumber(str);*/
 pickup.setPaymentInformation(setPaymentInformation(pickups));

 // ======================= pickupinformation ===============================================
 pickUpInformation.setPickupDate(dateFormat.format(shippingOrder.getScheduledShipDate()).toString());
 String ReadyTime = pickups.getReadyHour()+":"+pickups.getReadyMin();
 //ReadyTime = ReadyTime.concat(pickups.getReadyMin());
 pickUpInformation.setReadyTime(ReadyTime);
 String CloseTime = pickups.getCloseHour()+":"+pickups.getCloseMin();
 pickUpInformation.setCloseTime(CloseTime);

pickup.setPickupInformation(pickUpInformation);
 getPickupRequestContainer.setPickUp(pickup);

 JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
 //factory.getInInterceptors().add(new LoggingInInterceptor());
 //factory.getOutInterceptors().add(new LoggingOutInterceptor());
 factory.setServiceClass(FreightPickUpServiceContract.class);
 factory.setAddress("https://webservices.purolator.com/EWS/V1/FreightPickUp/FreightPickUpService.asmx");
 FreightPickUpServiceContract client = (FreightPickUpServiceContract) factory.create();
 org.apache.cxf.endpoint.Client clientProxy = ClientProxy.getClient(client);
 //clientProxy.getInInterceptors().add(new LoggingInInterceptor());
 //clientProxy.getOutInterceptors().add(new LoggingOutInterceptor());
 HTTPConduit http = (HTTPConduit) clientProxy.getConduit();

 HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

 httpClientPolicy.setConnectionTimeout(36000);
 httpClientPolicy.setAllowChunking(false);
 httpClientPolicy.setReceiveTimeout(32000);
 AuthorizationPolicy authorization = new AuthorizationPolicy();
 // Production URL
 authorization.setUserName(pickups.getCarrierAccount().getProperty1());
 authorization.setPassword(pickups.getCarrierAccount().getProperty2());

 ////Test URL
 /*authorization.setUserName("c099d4b2805d4501ac9343fb6e70f960");
 authorization.setPassword("A!fPUcP");*/

 http.setAuthorization(authorization);

 // ----------- RequestContext
 RequestContext reqContext = getRequestContext();
 try {
 List<Header> headers = new ArrayList<Header>();
 Header dummyHeader = new Header(new QName("http://purolator.com/pws/datatypes/v1","RequestContext"), reqContext,
 new JAXBDataBinding(RequestContext.class));
 headers.add(dummyHeader);

 ((BindingProvider)client).getRequestContext().put(Header.HEADER_LIST, headers);


 /*RequestContextE requestContext = new RequestContextE();
+ requestContext.setRequestContext(reqContext);*/

 /*FreightGetEstimateRequest request=new FreightGetEstimateRequest();
+ request.setFreightGetEstimateRequest(freightGetEstimateRequest);*/
 GetPickUpResponseContainer response = client.getFreightPickup(getPickupRequestContainer);
 String pickUpConf = response.getPickupNumber().getValue();
 if(pickUpConf!=null && pickUpConf.length()>0) {
 pickups.setConfirmationNum(pickUpConf);

 return;
 }
 // Display the response
 String respInf = getResponse(response.getResponseInformation());
 if(respInf.length() > 0)
 throw new PurolatorException(respInf);

 System.out.println(response);
 } catch (FreightPickUpServiceContractGetFreightPickupValidationFaultFaultFaultMessage e) {
 // TODO Auto-generated catch block
 e.printStackTrace();
 } catch (JAXBException e) {
// TODO Auto-generated catch block
 e.printStackTrace();
 }

 }
 public static RequestContext getRequestContext(){
 RequestContext reqContext = new RequestContext();
 reqContext.setVersion("1.0");
 reqContext.setLanguage(Language.EN);
 reqContext.setGroupID("1");
 reqContext.setRequestReference("RequestReference");
 return reqContext;

 }

 private PaymentInformation setPaymentInformation(Pickup pickups) {
 // TODO Auto-generated method stub
 PaymentInformation paymentInformation = new PaymentInformation();
 ObjectFactory objectFactory = new ObjectFactory();
 paymentInformation.setPaymentType(PaymentType.SENDER);
 paymentInformation.setRegisteredAccountNumber(pickups.getCarrierAccount().getAccountNumber1());
 JAXBElement<String> str = objectFactory.createPaymentInformationBillingAccountNumber(pickups.getCarrierAccount().getAccountNumber1());
 paymentInformation.setBillingAccountNumber(str);
 return paymentInformation;
 }

 private ReceiverInformation setReceiverInformation(ShippingOrder shippingOrder) {
 // TODO Auto-generated method stub
 ReceiverInformation receiverInformation = new ReceiverInformation();
 Address receiverAddress = new Address();
 ObjectFactory objectFactory = new ObjectFactory();
 JAXBElement<String> toCompanyName = objectFactory.createAddressCompany(shippingOrder.getToAddress().getAbbreviationName());
 receiverAddress.setCompany(toCompanyName);
 if(shippingOrder.getToAddress().getContactName().length()<=10){
 receiverAddress.setName(shippingOrder.getToAddress().getContactName());
 }else{
 receiverAddress.setName(shippingOrder.getToAddress().getContactName().substring(0, 10));
 }
 receiverAddress.setStreetNumber("");
 if(shippingOrder.getToAddress().getAddress1()!=null){
 if(shippingOrder.getToAddress().getAddress1().length()<=20) //max allowed length for this field is 25
 receiverAddress.setStreetName(shippingOrder.getToAddress().getAddress1());
 else
 {
 receiverAddress.setStreetName(shippingOrder.getToAddress().getAddress1().substring(0, 20));
 receiverAddress.setStreetAddress2(objectFactory.createAddressStreetAddress2(shippingOrder.getToAddress().getAddress1().substring(20, shippingOrder.getToAddress().getAddress1().length())));
 }
 }
 if(shippingOrder.getToAddress().getAddress2()!=null){
 if(shippingOrder.getToAddress().getAddress2().length()<=15) //max allowed length for this field is 25
 receiverAddress.setStreetAddress3(objectFactory.createAddressStreetAddress2(shippingOrder.getToAddress().getAddress2()));
 else
 receiverAddress.setStreetAddress3(objectFactory.createAddressStreetAddress2(shippingOrder.getToAddress().getAddress2().substring(0, 15)));
 }
 receiverAddress.setCity(shippingOrder.getToAddress().getCity());
 receiverAddress.setProvince(shippingOrder.getToAddress().getProvinceCode());
 receiverAddress.setCountry(shippingOrder.getToAddress().getCountryCode());
 receiverAddress.setPostalCode(shippingOrder.getToAddress().getPostalCode());
 PhoneNumber phone = new PhoneNumber();
 phone.setPhone(shippingOrder.getToAddress().getPhoneNo());
 phone.setAreaCode("");
 phone.setCountryCode(shippingOrder.getToAddress().getCountryCode());
 receiverAddress.setPhoneNumber(phone);
 receiverInformation.setAddress(receiverAddress);
 return receiverInformation;
 }
 private SenderInformation setSenderInformation(ShippingOrder shippingOrder) {
 // TODO Auto-generated method stub
 SenderInformation senderInformation = new SenderInformation();
 Address sendAddress = new Address();
 ObjectFactory objectFactory = new ObjectFactory();
 JAXBElement<String> fromCompanyName = objectFactory.createAddressCompany(shippingOrder.getFromAddress().getAbbreviationName());
 sendAddress.setCompany(fromCompanyName);
 if(shippingOrder.getToAddress().getContactName().length()<=10){
 sendAddress.setName(shippingOrder.getFromAddress().getContactName());
 }else{
 sendAddress.setName(shippingOrder.getFromAddress().getContactName().substring(0, 10));
 }
 sendAddress.setStreetNumber("");
 if(shippingOrder.getFromAddress().getAddress1()!=null){
 if(shippingOrder.getFromAddress().getAddress1().length()<=20) //max allowed length for this field is 25
 sendAddress.setStreetName(shippingOrder.getFromAddress().getAddress1());
 else
 {
 sendAddress.setStreetName(shippingOrder.getFromAddress().getAddress1().substring(0, 20));
 sendAddress.setStreetAddress2(objectFactory.createAddressStreetAddress2(shippingOrder.getFromAddress().getAddress1().substring(20, shippingOrder.getFromAddress().getAddress1().length())));
 }
 }
 if(shippingOrder.getFromAddress().getAddress2()!=null){
 if(shippingOrder.getFromAddress().getAddress2().length()<=15) //max allowed length for this field is 25
 sendAddress.setStreetAddress3(objectFactory.createAddressStreetAddress2(shippingOrder.getFromAddress().getAddress2()));
 else
 sendAddress.setStreetAddress3(objectFactory.createAddressStreetAddress2(shippingOrder.getFromAddress().getAddress2().substring(0, 15)));
 }
 sendAddress.setCity(shippingOrder.getFromAddress().getCity());
 sendAddress.setProvince(shippingOrder.getFromAddress().getProvinceCode());
 sendAddress.setCountry(shippingOrder.getFromAddress().getCountryCode());
 sendAddress.setPostalCode(shippingOrder.getFromAddress().getPostalCode());
 PhoneNumber phone = new PhoneNumber();
 phone.setPhone(shippingOrder.getFromAddress().getPhoneNo().replaceAll("-", ""));
 phone.setAreaCode("");
 phone.setCountryCode(shippingOrder.getFromAddress().getCountryCode());
 sendAddress.setPhoneNumber(phone);
 senderInformation.setAddress(sendAddress);
 return senderInformation;
 }

 private String getResponse(com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.ResponseInformation responseInformation){
 if (responseInformation == null)
 return "";

 StringBuilder stringBuilder = new StringBuilder();

int i = 0;
 if (responseInformation.getErrors() != null && responseInformation.getErrors().getError() != null&& responseInformation.getErrors().getError().size() > 0)
{
 for (com.meritconinc.shiplinx.carrier.purolatorFreight.ws.pickup.com.purolator.pws.datatypes.v1.Error error : responseInformation.getErrors().getError())
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

}
