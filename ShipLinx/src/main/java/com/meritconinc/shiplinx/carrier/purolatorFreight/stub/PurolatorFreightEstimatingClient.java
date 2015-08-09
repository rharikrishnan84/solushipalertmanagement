package com.meritconinc.shiplinx.carrier.purolatorFreight.stub;


import java.io.StringWriter;
import java.math.BigDecimal;
import java.rmi.RemoteException;import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
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
import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.carrier.purolator.PurolatorAPI;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.AccessorialItem;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.Address;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.ArrayOfAccessorialItem;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.ArrayOfBoolValuePair;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.ArrayOfLineItem;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.BoolValuePair;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.Dimension;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.DimensionUnit;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.EstimateInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.GetEstimateRequestContainer;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.GetEstimateResponseContainer;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.Language;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.LineItem;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.ObjectFactory;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.PaymentInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.PaymentType;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.PhoneNumber;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.ReceiverInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.RequestContext;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.SenderInformation;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.Shipment;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.Weight;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.datatypes.v1.WeightUnit;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.service.v1.FreightEstimatingServiceContract;
import com.meritconinc.shiplinx.carrier.purolatorFreight.ws.estimating.com.purolator.pws.service.v1.FreightEstimatingServiceContractGetEstimateValidationFaultFaultFaultMessage;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.MarkupManagerDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class PurolatorFreightEstimatingClient {
 private static Logger logger = Logger.getLogger(PurolatorFreightEstimatingClient.class);
 private ShippingOrder shipOrder=null;
 private CustomerCarrier customerCarrier= null;
 GetEstimateResponseContainer response=null;
 private CarrierServiceDAO carrierServiceDAO;
 private MarkupManagerDAO markupManagerDAO;
 private ShippingDAO shippingDAO;
 public MarkupManagerDAO getMarkupManagerDAO() {
 return markupManagerDAO;
 }

 public void setMarkupManagerDAO(MarkupManagerDAO markupManagerDAO) {
 this.markupManagerDAO = markupManagerDAO;
 }

 public ShippingDAO getShippingDAO() {
 return shippingDAO;
 }

 public void setShippingDAO(ShippingDAO shippingDAO) {
 this.shippingDAO = shippingDAO;
 }

 public ShippingOrder getShipOrder() {
 return shipOrder;
 }

 public void setShipOrder(ShippingOrder shipOrder) {
 this.shipOrder = shipOrder;
 }

 public CustomerCarrier getCustomerCarrier() {
 return customerCarrier;
 }

 public void setCustomerCarrier(CustomerCarrier customerCarrier) {
 this.customerCarrier = customerCarrier;
 }

 public GetEstimateResponseContainer getResponse() {
 return response;
 }

 public void setResponse(GetEstimateResponseContainer response) {
 this.response = response;
 }

 public CarrierServiceDAO getCarrierServiceDAO() {
 return carrierServiceDAO;
 }

 public void setCarrierServiceDAO(CarrierServiceDAO carrierServiceDAO) {
 this.carrierServiceDAO = carrierServiceDAO;
 }

 public PurolatorFreightEstimatingClient(ShippingOrder order,CustomerCarrier customerCarrier) {
 // TODO Auto-generated constructor stub
 this.shipOrder=order;
 this.customerCarrier=customerCarrier;
 }

 public GetEstimateResponseContainer getEstimate() throws RemoteException {
 // TODO Auto-generated method stub

 GetEstimateRequestContainer freightGetEstimateRequest = new GetEstimateRequestContainer();
 Shipment shipment = new Shipment();
 
 ObjectFactory objectFactory = new ObjectFactory();
 SenderInformation senderInformation = new SenderInformation();
 ReceiverInformation receiverInformation = new ReceiverInformation();
 ArrayOfLineItem lineItemDetails = new ArrayOfLineItem();
 LineItem lineItem = new LineItem();
 Dimension dimension = new Dimension();
 Weight packageWeight = new Weight();
 EstimateInformation shipmentDetails = new EstimateInformation();
 
 String fromInstructions = null;
 String toInstructions = null;
 ArrayOfBoolValuePair arrayOfBoolValuePair = new ArrayOfBoolValuePair();
 
 if(this.shipOrder.getFromTailgate() || this.shipOrder.getToTailgate()){
	 BoolValuePair bv =new BoolValuePair();
	 bv.setKeyword("TAILGATE");
	 bv.setValue(true);
	 arrayOfBoolValuePair.getBoolValuePair().add(bv);
 }
 if(this.shipOrder.isInsidePickup()){
	 BoolValuePair bv =new BoolValuePair();
	 bv.setKeyword("ID");
	 bv.setValue(true);
	 arrayOfBoolValuePair.getBoolValuePair().add(bv);
 }
 if(this.shipOrder.getTempControl()){
	 BoolValuePair bv =new BoolValuePair();
	 bv.setKeyword("HEAT");
	 bv.setValue(true);
	 arrayOfBoolValuePair.getBoolValuePair().add(bv);
 }
 if(this.shipOrder.getFromAddress().getResidential() || this.shipOrder.getToAddress().getResidential()){
	 BoolValuePair bv =new BoolValuePair();
	 bv.setKeyword("RESID");
	 bv.setValue(true);
	 arrayOfBoolValuePair.getBoolValuePair().add(bv);
	 
 }
 if(this.shipOrder.isAppointmentPickup() || this.shipOrder.isAppointmentDelivery()){
	 BoolValuePair bv =new BoolValuePair();
	 bv.setKeyword("APPT");
	 bv.setValue(true);
	 arrayOfBoolValuePair.getBoolValuePair().add(bv);
	 
 }
 if(this.shipOrder.isTradeShowDelivery() || this.shipOrder.isTradeShowPickup()){
	 BoolValuePair bv =new BoolValuePair();
	 bv.setKeyword("TSDEL");
	 bv.setValue(true);
	 arrayOfBoolValuePair.getBoolValuePair().add(bv);
 }
 if(this.shipOrder.getDangerousGoodsName().equalsIgnoreCase(ShiplinxConstants.DANGEROUS_GOODS_LIMITED) || this.shipOrder.getDangerousGoodsName().equalsIgnoreCase(ShiplinxConstants.DANGEROUS_GOODS_500KG_EXEMPTION)){
	 BoolValuePair bv =new BoolValuePair();
	 bv.setKeyword("DG");
	 bv.setValue(true);
	 arrayOfBoolValuePair.getBoolValuePair().add(bv);
	 
 }
 if(this.shipOrder.getDangerousGoodsName().equalsIgnoreCase(ShiplinxConstants.DANGEROUS_GOODS_FULLY_REGULATED)){
	 BoolValuePair bv =new BoolValuePair();
	 bv.setKeyword("DGPL");
	 bv.setValue(true);
	 arrayOfBoolValuePair.getBoolValuePair().add(bv);
 }
 if(this.shipOrder.getFromInstructions() != null && !this.shipOrder.getFromInstructions().isEmpty()){
	 fromInstructions=this.shipOrder.getFromInstructions();
 }
 if(this.shipOrder.getToInstructions() != null && !this.shipOrder.getToInstructions().isEmpty()){
	 toInstructions=this.shipOrder.getToInstructions();
 }
 
 
 //shipmentDetails.setAccesorialParameters(objectFactory.createArrayOfBoolValuePair(arrayOfBoolValuePair));

 ///Sender Information ///
 senderInformation.setAddress(setSenderInformation());
 shipment.setSenderInformation(senderInformation);

 // Receiver Information ///
 receiverInformation.setAddress(setReceiverInformation());
 shipment.setReceiverInformation(receiverInformation);
 /// Payment Information //
 shipment.setPaymentInformation(setPaymentInformation());

 /*order.getPaymentInformation().setPaymentType(PaymentType.SENDER);
 order.getPaymentInformation().setRegisteredAccountNumber("F25507");*/

 /// ShipmentDetails //
 if(shipOrder.getQuotedWeightUOM() != null && shipOrder.getQuotedWeightUOM().equalsIgnoreCase("KGS")){
 packageWeight.setWeightUnit(WeightUnit.KG);
 dimension.setDimensionUnit(DimensionUnit.CM);
 }else{
 packageWeight.setWeightUnit(WeightUnit.LB);
 dimension.setDimensionUnit(DimensionUnit.IN);
 }

 lineItem.setPieces(shipOrder.getPackages().size());
 lineItem.setLineNumber(shipOrder.getPackages().size());
 lineItem.setHandlingUnit(1);
 lineItem.setHandlingUnitType("Pallet");
 List<com.meritconinc.shiplinx.model.Package> packageList = shipOrder.getPackages();
 boolean isValue = false;
 BigDecimal value = null;
 BigDecimal codeValue = null;
 for(com.meritconinc.shiplinx.model.Package pack:packageList){
 dimension.setValue(pack.getLength());
 JAXBElement<Dimension> length = objectFactory.createLineItemLength(dimension );
 dimension.setValue(pack.getHeight());
 JAXBElement<Dimension> height = objectFactory.createLineItemHeight(dimension);
 dimension.setValue(pack.getWidth());
 JAXBElement<Dimension> width = objectFactory.createLineItemWidth(dimension);
 packageWeight.setValue(pack.getWeight());
 lineItem.setLength(length);
 lineItem.setHeight(height);
 lineItem.setWidth(width);
 lineItem.setWeight(packageWeight);
 lineItemDetails.getLineItem().add(lineItem);
 if(pack.getInsuranceAmount().floatValue() > 0.0){
	 isValue=true;
	 value=pack.getInsuranceAmount();
	 codeValue=pack.getCodAmount();
 }
 }
 if(isValue){
	
	 BoolValuePair bv =new BoolValuePair();
	 bv.setKeyword("VALUE");
	 bv.setValue(true);
	 arrayOfBoolValuePair.getBoolValuePair().add(bv);
 }
 //weight.setValue(new BigDecimal(shipOrder.getBilledWeight()));


 //weight.setValue(new BigDecimal(100));
 //weight.setWeightUnit(WeightUnit.LB);
 shipmentDetails.setLineItemDetails(lineItemDetails);
 shipmentDetails.setAccesorialParameters(objectFactory.createEstimateInformationAccesorialParameters(arrayOfBoolValuePair));
 JAXBElement<String> shipDate =objectFactory.createEstimateInformationShipmentDate(shipOrder.getScheduledShipDate_web());
 JAXBElement<String> serviceTypeCode = objectFactory.createEstimateInformationServiceTypeCode("S");
 JAXBElement<BigDecimal> valueBig =objectFactory.createEstimateInformationDeclaredValue(value);
 JAXBElement<BigDecimal> valueCOD=objectFactory.createEstimateInformationCODAmount(codeValue);
 JAXBElement<String> valueInstructions=objectFactory.createEstimateInformationSpecialInstructions(fromInstructions);
 JAXBElement<String> toSpeInstructions=objectFactory.createEstimateInformationSpecialInstructions(toInstructions);
 shipmentDetails.setServiceTypeCode(serviceTypeCode);
 shipmentDetails.setShipmentDate(shipDate);
 shipmentDetails.setDeclaredValue(valueBig);
 shipmentDetails.setCODAmount(valueCOD);
 shipmentDetails.setSpecialInstructions(valueInstructions);
 shipmentDetails.setSpecialInstructions(toSpeInstructions);
 shipment.setShipmentDetails(shipmentDetails);

 freightGetEstimateRequest.setEstimate(shipment);
 
 /*
 JAXBElement<GetEstimateRequestContainer> jaxbElement =  new JAXBElement( 
         new QName(GetEstimateRequestContainer.class.getSimpleName()), GetEstimateRequestContainer.class,freightGetEstimateRequest);

      //Create a String writer object which will be 
      //used to write jaxbElment XML to string
      StringWriter writer = new StringWriter();

      // create JAXBContext which will be used to update writer 		
      JAXBContext context;
	try {
		context = JAXBContext.newInstance(GetEstimateRequestContainer.class);
		// marshall or convert jaxbElement containing student to xml format
	      context.createMarshaller().marshal(jaxbElement, writer);
	   
	      //print XML string representation of Student object			
	      System.out.println("JJJJJJJJJJJJJJJJJJ Response GET FULL"+ writer.toString() ); 
	} catch (JAXBException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

      */
      


 try {

 JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
 //factory.getInInterceptors().add(new LoggingInInterceptor());
 //factory.getOutInterceptors().add(new LoggingOutInterceptor());
 factory.setServiceClass(FreightEstimatingServiceContract.class);
 factory.setAddress("https://webservices.purolator.com/PWS/V1/FreightEstimating/FreightEstimatingService.asmx");
 FreightEstimatingServiceContract client = (FreightEstimatingServiceContract)factory.create();
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
 



 http.setAuthorization(authorization);

 // ----------- RequestContext
 RequestContext reqContext = getRequestContext();

 List<Header> headers = new ArrayList<Header>();
 Header dummyHeader = new Header(new QName("http://purolator.com/pws/datatypes/v1","RequestContext"), reqContext,
 new JAXBDataBinding(RequestContext.class));
 headers.add(dummyHeader);

 ((BindingProvider)client).getRequestContext().put(Header.HEADER_LIST, headers);
 response = client.getEstimate(freightGetEstimateRequest);
 
 /*JAXBElement<GetEstimateResponseContainer> jaxbElement1 =  new JAXBElement( 
         new QName(GetEstimateResponseContainer.class.getSimpleName()), GetEstimateResponseContainer.class,response);

      //Create a String writer object which will be 
      //used to write jaxbElment XML to string
      StringWriter writer1 = new StringWriter();

      // create JAXBContext which will be used to update writer 		
      JAXBContext context1;
	try {
		context1 = JAXBContext.newInstance(GetEstimateResponseContainer.class);
		// marshall or convert jaxbElement containing student to xml format
	      context1.createMarshaller().marshal(jaxbElement1, writer1);
	   
	      //print XML string representation of Student object			
	      System.out.println("RRRRRRRRRRRRR"+ writer1.toString() ); 
	} catch (JAXBException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}*/

	
 } catch (FreightEstimatingServiceContractGetEstimateValidationFaultFaultFaultMessage e) {
 // TODO Auto-generated catch block
 e.printStackTrace();
 } catch (JAXBException e) {
 // TODO Auto-generated catch block
 e.printStackTrace();
 }
 return response;
 }
 
 public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        

	    Calendar endCal = Calendar.getInstance();
	    endCal.setTime(endDate);

	    int workDays = 0;

	    //Return 0 if start and end are the same
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return 0;
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }

	    do {
	       //excluding start date
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            ++workDays;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

	    return workDays;
	}


 
 private PaymentInformation setPaymentInformation() {
 // TODO Auto-generated method stub
 PaymentInformation paymentInformation = new PaymentInformation();
 ObjectFactory objectFactory = new ObjectFactory();
 paymentInformation.setPaymentType(PaymentType.SENDER);
 paymentInformation.setRegisteredAccountNumber(customerCarrier.getAccountNumber1());
 JAXBElement<String> str = objectFactory.createPaymentInformationBillingAccountNumber(customerCarrier.getAccountNumber1());
 
 
 
 paymentInformation.setBillingAccountNumber(str);
 return paymentInformation;
 }

 private Address setReceiverInformation() {
 // TODO Auto-generated method stub
 Address receiverAddress = new Address();
 ObjectFactory objectFactory = new ObjectFactory();
 JAXBElement<String> toCompanyName = objectFactory.createAddressCompany(shipOrder.getToAddress().getAbbreviationName());
 receiverAddress.setCompany(toCompanyName);
 receiverAddress.setName(shipOrder.getToAddress().getAbbreviationName());
 receiverAddress.setStreetNumber("");
 receiverAddress.setCity(shipOrder.getToAddress().getCity());
 receiverAddress.setProvince(shipOrder.getToAddress().getProvinceCode());
 receiverAddress.setCountry(shipOrder.getToAddress().getCountryCode());
 receiverAddress.setPostalCode(shipOrder.getToAddress().getPostalCode());
 PhoneNumber phone = new PhoneNumber();
 phone.setPhone(shipOrder.getToAddress().getPhoneNo());
 phone.setAreaCode("");
 phone.setCountryCode(shipOrder.getToAddress().getCountryCode());
 receiverAddress.setPhoneNumber(phone);
 return receiverAddress;
 }

 private Address setSenderInformation() {
 // TODO Auto-generated method stub
 Address sendAddress = new Address();
 ObjectFactory objectFactory = new ObjectFactory();
 JAXBElement<String> fromCompanyName = objectFactory.createAddressCompany(shipOrder.getFromAddress().getAbbreviationName());
 sendAddress.setCompany(fromCompanyName);

 sendAddress.setName(shipOrder.getFromAddress().getAbbreviationName());
 sendAddress.setStreetNumber("");
 sendAddress.setCity(shipOrder.getFromAddress().getCity());
 sendAddress.setProvince(shipOrder.getFromAddress().getProvinceCode());
 sendAddress.setCountry(shipOrder.getFromAddress().getCountryCode());
 sendAddress.setPostalCode(shipOrder.getFromAddress().getPostalCode());
 PhoneNumber phone = new PhoneNumber();
 phone.setPhone(shipOrder.getFromAddress().getPhoneNo());
 phone.setAreaCode("");
 phone.setCountryCode(shipOrder.getFromAddress().getCountryCode());
 sendAddress.setPhoneNumber(phone);
 return sendAddress;
 }

 public RequestContext getRequestContext(){
 RequestContext reqContext = new RequestContext();
 reqContext.setVersion("1.0");
 reqContext.setLanguage(Language.EN);
 reqContext.setGroupID("1");
 reqContext.setRequestReference("RequestReference");
 return reqContext;

 }


 public List<Rating> getEstimates() throws RemoteException {
 // TODO Auto-generated method stub
 this.getEstimate();
 List<Rating> ratingList = new ArrayList<Rating>();
 Rating rate =new Rating();
 rate.setTotalCost(response.getTotalPrice().doubleValue());

 rate.setTotal(response.getTotalPrice().doubleValue());
 Calendar calendar1 = Calendar.getInstance();
 Calendar calendar2 = Calendar.getInstance();
 String esdeliveryString = response.getEstimatedDeliveryDate().getValue();
 
 DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	

	try {

		Date date = formatter.parse(esdeliveryString);
		String curDate = formatter.format(this.shipOrder.getScheduledShipDate().getTime());
		Date curDateObj = formatter.parse(curDate);
		int days=getWorkingDaysBetweenTwoDates(curDateObj,date);
		  if(days == 0||days == 1){
		   days=days;
		        }
		  else{
		  days=days-1;
		  }
		  rate.setTransitDays(days);
		  rate.setTransitDaysMin(response.getTransitDays());
		/*calendar1.setTime(date);
		calendar2.setTime(curDateObj);
		long miliSecondForDate1 = calendar2.getTimeInMillis();
		long miliSecondForDate2 = calendar1.getTimeInMillis();
		 
		// Calculate the difference in millisecond between two dates
		long diffInMilis = miliSecondForDate2 - miliSecondForDate1;
		int diffInDays = (int) (diffInMilis / (24 * 60 * 60 * 1000));*/
		
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
 

 rate.setDiscounted(true);

 rate.setCarrierId(ShiplinxConstants.CARRIER_PUROLATOR_FREIGHT);
 rate.setCarrierName(customerCarrier.getCarrierName());
 rate.setCustomerCarrier(customerCarrier);
 rate.setServiceId(4100);
 rate.setServiceName("Expedited LTL");


 shipOrder.setBillToAccountNum(customerCarrier.getAccountNumber1());
 double cubedWeightGround=0;
 for(com.meritconinc.shiplinx.model.Package p:shipOrder.getPackages()){
 float actualWeight = p.getWeight().floatValue();
 double ground = PurolatorAPI.getCubedWeight(p.getLength().floatValue(), p.getHeight().floatValue(), p.getWidth().floatValue(), PurolatorAPI.GROUND_CUBING_FACTOR);

 if(actualWeight>ground)
 cubedWeightGround += actualWeight;
 else
 cubedWeightGround += ground;
 }
 rate.setBillWeight(cubedWeightGround);
 int length = 0;
 if(response!=null && response.getAccessorialDetails()!=null && response.getAccessorialDetails().getValue()!=null && response.getAccessorialDetails().getValue().getAccessorialItem()!=null)
 length=response.getAccessorialDetails().getValue().getAccessorialItem().size();
 for(int i=0;i<length;i++){
 Charge charge = new Charge();
 if(response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCharge().doubleValue() != 0){
 if(response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCode().equalsIgnoreCase("LANE MIN")){
 charge.setChargeCode(ShiplinxConstants.GROUP_FREIGHT_CHARGE );
 charge.setCharge(response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCharge().doubleValue());
 charge.setName(ShiplinxConstants.FREIGHT_STRING);
 charge.setCost(response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCharge().doubleValue());
 }else if(response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCode().equalsIgnoreCase("FSC") || response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCode().equalsIgnoreCase("FUEL")){
 charge.setChargeCode(ShiplinxConstants.GROUP_FUEL_CHARGE);
 charge.setCharge(response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCharge().doubleValue());
 charge.setName(ShiplinxConstants.FUEL_SURCHARGE);
 charge.setCost(response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCharge().doubleValue());
 }else{
 charge.setChargeCode(response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCode());
 charge.setCharge(response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCharge().doubleValue());
 charge.setName(response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCode());
 charge.setCost(response.getAccessorialDetails().getValue().getAccessorialItem().get(i).getCharge().doubleValue());
 }
 rate.getCharges().add(charge);
 }
 // charge.setTariffRate(response.getShipmentTaxes().getValue().getTax().get(i).getAmount().doubleValue());


 }
 int lengths =0;
 if(response!=null && response.getShipmentTaxes()!=null && response.getShipmentTaxes().getValue()!=null &&response.getShipmentTaxes().getValue().getTax()!=null )
 lengths = response.getShipmentTaxes().getValue().getTax().size();
 for(int i=0;i<lengths;i++){
 Charge charge = new Charge();
 charge.setChargeCode(ShiplinxConstants.TAX_TAX);
 charge.setCharge(response.getShipmentTaxes().getValue().getTax().get(i).getAmount().doubleValue());
 charge.setName(response.getShipmentTaxes().getValue().getTax().get(i).getType());
 charge.setCost(response.getShipmentTaxes().getValue().getTax().get(i).getAmount().doubleValue());
 // charge.setTariffRate(response.getShipmentTaxes().getValue().getTax().get(i).getAmount().doubleValue());
 rate.getCharges().add(charge);
 }
/*	 if(rate != null && rate.getCharges() != null && rate.getCharges().size()>1 && ! rate.getCharges().get(0).getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FREIGHT_CHARGE)){
*/	 boolean flag=false;
     	 double total = 0;
	 for(int i=0; i<rate.getCharges().size();i++){
		 if(rate.getCharges().get(i).getChargeCode()!=null && rate.getCharges().get(i).getChargeCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FREIGHT_CHARGE)){
			      flag=true;
		 }
	 total += rate.getCharges().get(i).getCharge();
	 }
	 if(!flag){
     Charge charge = new Charge();
	 charge.setChargeCode(ShiplinxConstants.GROUP_FREIGHT_CHARGE );
	 charge.setCharge(response.getTotalPrice().doubleValue() - total);
	 charge.setName(ShiplinxConstants.FREIGHT_STRING);
	 charge.setCost(response.getTotalPrice().doubleValue() - total);
	 rate.getCharges().add(charge);
	 }
	
	 ratingList.add(rate);
	
	 return ratingList;
	 }
	
	
	}