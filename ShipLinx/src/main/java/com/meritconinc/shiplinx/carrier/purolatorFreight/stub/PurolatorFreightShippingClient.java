package com.meritconinc.shiplinx.carrier.purolatorFreight.stub;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.Address;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.AlertDetail;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.AlertInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.ArrayOfAlertDetail;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.ArrayOfBoolValuePair;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.ArrayOfCustomerReference;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.ArrayOfLineItem;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.ArrayOfPIN;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.BoolValuePair;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.CreateShipmentRequestContainer;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.CreateShipmentResponseContainer;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.CustomerReference;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.Language;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.LineItem;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.ObjectFactory;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.PaymentInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.PaymentType;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.PhoneNumber;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.PickUpInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.ReceiverInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.RequestContext;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.SenderInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.Shipment;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.ShipmentInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.Weight;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.WeightUnit;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.service.v1.FreightShippingServiceContract;
import com.meritconinc.shiplinx.carrier.utils.PurolatorException;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;



public class PurolatorFreightShippingClient {

 private ShippingOrder shipOrder=null;
 private CustomerCarrier customerCarrier= null;

 public PurolatorFreightShippingClient(ShippingOrder order,CustomerCarrier customerCarrier) {
 // TODO Auto-generated constructor stub
 this.shipOrder=order;
 this.customerCarrier=customerCarrier;
 // TODO Auto-generated constructor stub
 }
 public void createShipping() {
 // TODO Auto-generated method stub

 //Object Creation
 CreateShipmentRequestContainer createShipmentRequestContainer = new CreateShipmentRequestContainer();
 Shipment shipment = new Shipment();
 ObjectFactory objectFactory = new ObjectFactory();
 ArrayOfLineItem lineItemDetails = new ArrayOfLineItem();
 LineItem lineItem = new LineItem();
 com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.Dimension dimension = new com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.Dimension();
 Weight packageWeight = new Weight();
 ShipmentInformation shipmentDetails = new ShipmentInformation();

 ///==================== Sender Information ==================================== ///
 shipment.setSenderInformation(setSenderInformation());

 // ======================== Receiver Information =============================== ///

 shipment.setReceiverInformation(setReceiverInformation());

 /// ======================= Payment Information =================================//
 shipment.setPaymentInformation(setPaymentInformation());

 /// ============================== Alert Information =============================///
 AlertInformation alertInfo = new AlertInformation();
 ArrayOfAlertDetail value = new ArrayOfAlertDetail();
 AlertDetail alertDet = new AlertDetail();
 if(shipOrder.getFromAddress().getEmailAddress()!=null && !shipOrder.getFromAddress().getEmailAddress().isEmpty()){
 alertDet.setEmailAddress(shipOrder.getFromAddress().getEmailAddress());
 }else if(shipOrder.getBusiness()!=null && shipOrder.getBusiness().getAddress()!=null && shipOrder.getBusiness().getAddress().getEmailAddress()!=null && !shipOrder.getBusiness().getAddress().getEmailAddress().isEmpty() ){
 alertDet.setEmailAddress(shipOrder.getBusiness().getAddress().getEmailAddress());
 }else{
 alertDet.setEmailAddress("");
 }
 alertDet.setType("POD");
 value.getAlertDetail().add(alertDet);
 JAXBElement<ArrayOfAlertDetail> arrayofAlertDetails = objectFactory.createAlertInformationAlertDetails(value );
 alertInfo.setAlertDetails(arrayofAlertDetails );
 JAXBElement<AlertInformation> alertInformation=objectFactory.createShipmentAlertInformation(alertInfo );
 shipment.setAlertInformation(alertInformation);

 /// ========================== ShipmentDetails and Line Item details====================================//
 if(shipOrder.getQuotedWeightUOM() != null && shipOrder.getQuotedWeightUOM().equalsIgnoreCase("KGS")){
 packageWeight.setWeightUnit(WeightUnit.KG);
 dimension.setDimensionUnit(com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.DimensionUnit.CM);
 }else{
 packageWeight.setWeightUnit(WeightUnit.LB);
 dimension.setDimensionUnit(com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.DimensionUnit.IN);
 }


 lineItem.setPieces(shipOrder.getPackages().size());
 lineItem.setLineNumber(shipOrder.getPackages().size());
 lineItem.setHandlingUnit(1);
 lineItem.setHandlingUnitType("Pallet");
 JAXBElement<String> description = objectFactory.createLineItemDescription("pallet");
 lineItem.setDescription(description );

 List<com.meritconinc.shiplinx.model.Package> packageList = shipOrder.getPackages();
 for(com.meritconinc.shiplinx.model.Package pack:packageList){
 dimension.setValue(pack.getLength());
 JAXBElement<com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.Dimension> length = objectFactory.createLineItemHeight(dimension);
 dimension.setValue(pack.getHeight());
 JAXBElement<com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.Dimension> height = objectFactory.createLineItemHeight(dimension);
 dimension.setValue(pack.getWidth());
 JAXBElement<com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.Dimension> width = objectFactory.createLineItemWidth(dimension);
 packageWeight.setValue(pack.getWeight());
 lineItem.setLength(length);
 lineItem.setHeight(height);
 lineItem.setWidth(width);
 lineItem.setWeight(packageWeight);
 lineItemDetails.getLineItem().add(lineItem);
 }
 shipmentDetails.setLineItemDetails(lineItemDetails);
 JAXBElement<String> shipmentDate = objectFactory.createEstimateInformationShipmentDate(shipOrder.getScheduledShipDate_web());
 JAXBElement<String> serviceCode = objectFactory.createEstimateInformationServiceTypeCode("S");
 shipmentDetails.setShipmentDate(shipmentDate );
 shipmentDetails.setServiceTypeCode(serviceCode);

 /////////============================ Pickup Information ============================
 if(shipOrder.getPickup()!=null && shipOrder.getPickup().isPickupRequired()){
 PickUpInformation shipmentPickupInformation = new PickUpInformation();
 SimpleDateFormat pickupDate = new SimpleDateFormat("yyyy-MM-dd");
 shipmentPickupInformation.setPickupDate(pickupDate.format(shipOrder.getPickup().getPickupDate()).toString());
 shipmentPickupInformation.setReadyTime(shipOrder.getPickup().getReadyHour()+":"+shipOrder.getPickup().getReadyMin());
 shipmentPickupInformation.setCloseTime(shipOrder.getPickup().getCloseHour()+":"+shipOrder.getPickup().getCloseMin());
 shipmentPickupInformation.setPickUpConfirmationEmailFlag(true);
 ArrayOfBoolValuePair arrayOfBoolValuePair = new ArrayOfBoolValuePair();
 BoolValuePair boolValuePair = new BoolValuePair();
 boolValuePair.setKeyword(shipOrder.getPickup().getPickupLocation());
 boolValuePair.setValue(true);
 JAXBElement<ArrayOfBoolValuePair> pickupOption = objectFactory.createPickUpInformationPickUpOptions(arrayOfBoolValuePair);
 shipmentPickupInformation.setPickUpOptions(pickupOption );
 JAXBElement<PickUpInformation> pickupInformation = objectFactory.createShipmentPickupInformation(shipmentPickupInformation);
 shipment.setPickupInformation(pickupInformation);
 shipment.setPickupFlag(true);
 }
 //////////==================== End ================================================

 ////////// Tracking Information
 ArrayOfCustomerReference arrayOfCustomerReference = new ArrayOfCustomerReference();
 CustomerReference customerReference = new CustomerReference();
 customerReference.setCode("123");
 customerReference.setSequence(1);
 arrayOfCustomerReference.getCustomerReference().add(customerReference);
 /*arrayOfCustomerReference.getCustomerReference().get(0).setCode(shipOrder.getReferenceCode());
 arrayOfCustomerReference.getCustomerReference().get(0).setSequence(1);*/
 JAXBElement<ArrayOfCustomerReference> customerRef = objectFactory.createArrayOfCustomerReference(arrayOfCustomerReference);
 shipmentDetails.setCustomerReferences(customerRef);
 shipment.setShipmentDetails(shipmentDetails);
 /////////
 createShipmentRequestContainer.setShipment(shipment);

 JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
 factory.getInInterceptors().add(new LoggingInInterceptor());
 factory.getOutInterceptors().add(new LoggingOutInterceptor());
 factory.setServiceClass(FreightShippingServiceContract.class);
 factory.setAddress("https://webservices.purolator.com/EWS/V1/FreightShipping/FreightShippingService.asmx");
 FreightShippingServiceContract client = (FreightShippingServiceContract) factory.create();
 org.apache.cxf.endpoint.Client clientProxy = ClientProxy.getClient(client);
 clientProxy.getInInterceptors().add(new LoggingInInterceptor());
 clientProxy.getOutInterceptors().add(new LoggingOutInterceptor());
 HTTPConduit http = (HTTPConduit) clientProxy.getConduit();

 HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

 httpClientPolicy.setConnectionTimeout(36000);
 httpClientPolicy.setAllowChunking(false);
 httpClientPolicy.setReceiveTimeout(32000);
 AuthorizationPolicy authorization = new AuthorizationPolicy();

 // Production URL
 authorization.setUserName(customerCarrier.getProperty1());
 authorization.setPassword(customerCarrier.getProperty2());
 


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
 CreateShipmentResponseContainer response = new CreateShipmentResponseContainer();
 response = client.createShipment(createShipmentRequestContainer);
 String respInf = getResponse(response.getResponseInformation());
 if(respInf.length() > 0)
 throw new PurolatorException(respInf);
 ArrayOfPIN pins = response.getShipmentPINs().getValue();
 shipOrder.setMasterTrackingNum(pins.getPIN().get(pins.getPIN().size()-1).getValue());
 int i=0;
 // Each package pin value
 if (pins != null && pins.getPIN()!= null && pins.getPIN().size() > 0){
 for (com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.PIN pin : pins.getPIN()){
 String trackingNumber = pin.getValue();
 //first pin will be as master tracking key of shipping order.
 shipOrder.getPackages().get(i++).setTrackingNumber(trackingNumber);

 }
 }
 if(shipOrder.getPickup()!=null && shipOrder.getPickup().isPickupRequired()){
 shipOrder.getPickup().setConfirmationNum(response.getPickupNumber().getValue());
 }

 System.out.println(response);
 } catch(Exception e){
 throw new PurolatorException(e.getMessage());
 }
 }

 public static RequestContext getRequestContext(){
 RequestContext reqContext = new RequestContext();
 reqContext.setVersion("1.0");
 reqContext.setLanguage(Language.EN);
 reqContext.setGroupID("1");
 reqContext.setRequestReference("Shipping");
 return reqContext;

 }
 private PaymentInformation setPaymentInformation() {
 // TODO Auto-generated method stub
 PaymentInformation paymentInformation = new PaymentInformation();
 ObjectFactory objectFactory = new ObjectFactory();
 paymentInformation.setPaymentType(PaymentType.SENDER);
 paymentInformation.setRegisteredAccountNumber(customerCarrier.getAccountNumber1());
 JAXBElement<String> billingAccountNumber = objectFactory.createPaymentInformationBillingAccountNumber(customerCarrier.getAccountNumber1());
 
 paymentInformation.setBillingAccountNumber(billingAccountNumber);
 return paymentInformation;
 }

 private ReceiverInformation setReceiverInformation() {
 // TODO Auto-generated method stub
 ReceiverInformation receiverInformation = new ReceiverInformation();
 Address receiverAddress = new Address();
 ObjectFactory objectFactory = new ObjectFactory();
 JAXBElement<String> toCompanyName = objectFactory.createAddressCompany(shipOrder.getToAddress().getAbbreviationName());
 receiverAddress.setCompany(toCompanyName);
 receiverAddress.setName(shipOrder.getToAddress().getAbbreviationName());
 receiverAddress.setStreetNumber("");
 if(shipOrder.getToAddress().getAddress1()!=null){
 if(shipOrder.getToAddress().getAddress1().length()<=25) //max allowed length for this field is 25
 receiverAddress.setStreetName(shipOrder.getToAddress().getAddress1());
 else
 {
 receiverAddress.setStreetName(shipOrder.getToAddress().getAddress1().substring(0, 25));
 receiverAddress.setStreetAddress2(objectFactory.createAddressStreetAddress2(shipOrder.getToAddress().getAddress1().substring(25, shipOrder.getToAddress().getAddress1().length())));
 }
 }
 if(shipOrder.getToAddress().getAddress2()!=null){
 if(shipOrder.getToAddress().getAddress2().length()<=25) //max allowed length for this field is 25
 receiverAddress.setStreetAddress3(objectFactory.createAddressStreetAddress2(shipOrder.getToAddress().getAddress2()));
 else
 receiverAddress.setStreetAddress3(objectFactory.createAddressStreetAddress2(shipOrder.getToAddress().getAddress2().substring(0, 25)));
 }
 receiverAddress.setCity(shipOrder.getToAddress().getCity());
 receiverAddress.setProvince(shipOrder.getToAddress().getProvinceCode());
 receiverAddress.setCountry(shipOrder.getToAddress().getCountryCode());
 receiverAddress.setPostalCode(shipOrder.getToAddress().getPostalCode());
 PhoneNumber phone = new PhoneNumber();
 phone.setPhone(shipOrder.getToAddress().getPhoneNo());
 phone.setAreaCode("");
 phone.setCountryCode(shipOrder.getToAddress().getCountryCode());
 receiverAddress.setPhoneNumber(phone);
 receiverInformation.setAddress(receiverAddress);
 return receiverInformation;
 }

 private SenderInformation setSenderInformation() {
 // TODO Auto-generated method stub
 SenderInformation senderInformation = new SenderInformation();
 Address sendAddress = new Address();
 ObjectFactory objectFactory = new ObjectFactory();
 JAXBElement<String> fromCompanyName = objectFactory.createAddressCompany(shipOrder.getFromAddress().getAbbreviationName());
 sendAddress.setCompany(fromCompanyName);

 sendAddress.setName(shipOrder.getFromAddress().getAbbreviationName());
 sendAddress.setStreetNumber("");
 if(shipOrder.getFromAddress().getAddress1()!=null){
 if(shipOrder.getFromAddress().getAddress1().length()<=30) //max allowed length for this field is 25
 sendAddress.setStreetName(shipOrder.getFromAddress().getAddress1());
 else
 {
 sendAddress.setStreetName(shipOrder.getFromAddress().getAddress1().substring(0, 30));
 sendAddress.setStreetAddress2(objectFactory.createAddressStreetAddress2(shipOrder.getFromAddress().getAddress1().substring(30, shipOrder.getFromAddress().getAddress1().length())));
 }
 }
 if(shipOrder.getFromAddress().getAddress2()!=null){
 if(shipOrder.getFromAddress().getAddress2().length()<=25) //max allowed length for this field is 25
 sendAddress.setStreetAddress3(objectFactory.createAddressStreetAddress2(shipOrder.getFromAddress().getAddress2()));
 else
 sendAddress.setStreetAddress3(objectFactory.createAddressStreetAddress2(shipOrder.getFromAddress().getAddress2().substring(0, 25)));
 }

 sendAddress.setCity(shipOrder.getFromAddress().getCity());
 sendAddress.setProvince(shipOrder.getFromAddress().getProvinceCode());
 sendAddress.setCountry(shipOrder.getFromAddress().getCountryCode());
 sendAddress.setPostalCode(shipOrder.getFromAddress().getPostalCode());
 PhoneNumber phone = new PhoneNumber();
 phone.setPhone(shipOrder.getFromAddress().getPhoneNo());
 phone.setAreaCode("");
 phone.setCountryCode(shipOrder.getFromAddress().getCountryCode());
 sendAddress.setPhoneNumber(phone);
 senderInformation.setAddress(sendAddress);
 return senderInformation;
 }

 private String getResponse(com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.ResponseInformation respInf){
 if (respInf == null)
 return "";

 StringBuilder stringBuilder = new StringBuilder();

 int i = 0;
 if (respInf.getErrors() != null && respInf.getErrors().getError() != null&& respInf.getErrors().getError().size() > 0)
 {
 for (com.meritconinc.shiplinx.carrier.purolatorFreight.ws.shipping.com.purolator.pws.datatypes.v1.Error error : respInf.getErrors().getError())
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