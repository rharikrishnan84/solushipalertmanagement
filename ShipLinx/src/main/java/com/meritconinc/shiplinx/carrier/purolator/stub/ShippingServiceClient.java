package com.meritconinc.shiplinx.carrier.purolator.stub;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
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

import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.purolator.PurolatorAPI;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ArrayOfOption;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ArrayOfOptionValue;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.ArrayOfService;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.Option;
import com.meritconinc.shiplinx.carrier.purolator.ws.serviceavailability.proxy.Service;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.Address;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ContentDetails;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ArrayOfOptionIDValuePair;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ArrayOfPIN;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ArrayOfPiece;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.BillDutiesToParty;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.BusinessRelationship;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ContentDetail;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.CreateShipmentRequestContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.CreateShipmentResponseContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.Dimension;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.DimensionUnit;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.DutyCurrency;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.DutyInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.Error;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ImportExportType;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.InternationalInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ObjectFactory;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.OptionIDValuePair;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.OptionsInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.OtherInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.PIN;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.PackageInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.PaymentInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.PaymentType;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.PhoneNumber;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.PickupInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.PickupType;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.Piece;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.PrinterType;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ReceiverInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.RequestContext;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ResponseInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.SenderInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.Shipment;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ShippingServiceContract;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ShippingServiceContractCreateShipmentValidationFaultFaultFaultMessage;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.ShippingServiceContractVoidShipmentValidationFaultFaultFaultMessage;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.TotalWeight;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.TrackingReferenceInformation;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.VoidShipmentRequestContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.VoidShipmentResponseContainer;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.Weight;
import com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.WeightUnit;
import com.meritconinc.shiplinx.carrier.utils.PurolatorException;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class ShippingServiceClient {

	private static Logger logger = Logger.getLogger(ShippingServiceClient.class);
	
	private boolean availableDangGoodDeclaration = true;
	private String availableDangerousGoodMode = "";
	private boolean availableDangerousGoods = false;
	private boolean availableSaturdayService = false;
	private boolean documentOnly = true;
	private boolean availableCODValue = false;
	private boolean dangerousGoodSelectedByUser = false;
	private boolean availableSignatureRequired = false;
	private boolean isRes = false;

	private OptionsInformation optInf = new OptionsInformation();
	
	private ShippingOrder order=null;
	private CustomerCarrier customerCarrier = null;

	private String dangerousGoodsClass;

	private ShippingDAO shippingDAO;
	public ShippingServiceClient(ShippingOrder order, CustomerCarrier customerCarrier)
	{
		this.order = order;
		this.customerCarrier = customerCarrier;
	}
	
	
	public ShippingOrder shippingService(){
		try{

			if(order.getDangerousGoods()!=0)
				dangerousGoodSelectedByUser = true;
			ServiceAvailabilityWebServiceClient availabilityServiceClient = new ServiceAvailabilityWebServiceClient();
			JAXBElement<ArrayOfService> availableServices = availabilityServiceClient.getServiceOptions(order, customerCarrier);

			String serviceCode = order.getService().getCode();
			for(Service service: availableServices.getValue().getService()){

				logger.debug("--------service.getID()------"+service.getID());
				//call for only one service which is selected on rating page
				if(serviceCode.equalsIgnoreCase(service.getID())){
					getOptionAvailable(service);
					break;
				}

			}
			callCreateShipment();

			
			//Test Validating a Shipment, do we need to call validate shipment????
//			callValidateShipment(stub,requestContext);

			
			
		} catch(Exception e){
			//e.printStackTrace();
			logger.error("Exception in shippingServiceClient ",e);
			throw new PurolatorException(e.getMessage()); 
		}
		
		return order;
	}
	
	public void voidShipment(){
		
		logger.debug("------Purolator voidShipment-------------");
		//Test Voiding a Shipment
		callVoidShipment();
	}

	public void callCreateShipment(){
		try{
			logger.debug("------------in CallCreateShipment--------------------");
			CreateShipmentRequestContainer reqContainer = new CreateShipmentRequestContainer();
			// Setup the request to perform a create shipment
			Shipment shipment = createShipment();
			reqContainer.setShipment(shipment);
			reqContainer.setPrinterType(PrinterType.THERMAL);


			logger.debug("------Request is ----" + reqContainer);
			
			// Call the service
			CreateShipmentResponseContainer response = sendShippingRequest(reqContainer);
            String respInf = getResponse(response.getResponseInformation());
			
			if(respInf.length() > 0)
				throw new PurolatorException(respInf);
			
            
 			if(response.getExpressChequePIN() != null)
				order.setCODPin(response.getExpressChequePIN().getValue().getValue());
			
			ArrayOfPIN pins = response.getPiecePINs().getValue();
			order.setMasterTrackingNum(response.getShipmentPIN().getValue().getValue());
				
			int i=0;
			
			//total number of pins will be equal to the total number of packages i.e. quantity
			
			if (pins != null && pins.getPIN()!= null && pins.getPIN().size() > 0){
				for (PIN pin : pins.getPIN()){
					String trackingNumber = pin.getValue();
					//first pin will be as master tracking key of shipping order.
					order.getPackages().get(i++).setTrackingNumber(trackingNumber);
					
				}
			}
			
		} catch(Exception e){
			//e.printStackTrace();
			logger.error("Exception ",e);
			throw new PurolatorException(e.getMessage());
		}
	}

//	public void callValidateShipment(ShippingServiceStub stub, RequestContextE context){
//		logger.debug("------------in CallValidateShipment--------------------");
//		try{
//			ValidateShipmentRequest request = new ValidateShipmentRequest();
//			ValidateShipmentRequestContainer reqContainer = new ValidateShipmentRequestContainer();
//
//			// Setup the request to perform a validate shipment
//			Shipment shipment = createShipment();
//			reqContainer.setShipment(shipment);
//			request.setValidateShipmentRequest(reqContainer);
//
//			// Call the service
//			ValidateShipmentResponse response = stub.ValidateShipment(request,context);
//			ValidateShipmentResponseContainer respContainer = response.getValidateShipmentResponse();
//
//			// Display the response
//			logger.debug(getResponse(respContainer.getResponseInformation()));
//			String respInf = getResponse(respContainer.getResponseInformation());
//			
//			if(respInf.length() > 0)
//				throw new ShipOrderException(respInf);
//			
//			if(respContainer.getValidShipment())
//				logger.debug("Valid Shipment");
//			else
//				logger.debug("Invalid Shipment");
//
//			logger.debug("done");
//		} catch(Exception e){
//			//e.printStackTrace();
//			logger.error("Exception "+e);
//			
//		}
//	}


	private void callVoidShipment(){
		logger.debug("------------in CallVoidShipment--------------------");
		try{
			VoidShipmentRequestContainer reqContainer = new VoidShipmentRequestContainer();

			// Setup the request to perform a void shipment
			PIN pin = new PIN();
			pin.setValue(order.getMasterTrackingNum());
			
			reqContainer.setPIN(pin);

			// Call the service
			VoidShipmentResponseContainer response = sendVoidRequest(reqContainer);

			// Display the response
			logger.debug(getResponse(response.getResponseInformation()));
			String respInf = getResponse(response.getResponseInformation());
			
			if(respInf.length() > 0){
				logger.debug("Purolator void response: " + respInf);
				throw new PurolatorException(respInf);
			}
			
			if(response.isShipmentVoided())
				logger.debug("Shipment is voided.");
			else
				logger.debug("Shipment is not voided.");
				
		} catch(Exception e){
			//e.printStackTrace();
			logger.error("Exception ",e);
			throw new PurolatorException(e.getMessage());
		}
	}

	private Shipment createShipment(){
		logger.debug("------------in CreateSampleShipment--------------------");
		ObjectFactory objectFactory = new ObjectFactory();
		Shipment shipment = new Shipment();

		SenderInformation sender = new SenderInformation();
		sender.setAddress(setSenderAddress());
		shipment.setSenderInformation(sender);

		ReceiverInformation receiver = new ReceiverInformation();
		receiver.setAddress(setReceiverAddress());
		//receiver.setTaxNumber("654321");
		shipment.setReceiverInformation(receiver);

		PackageInformation pack = new PackageInformation();

		pack.setServiceID(order.getService().getCode());
		
		pack.setDescription(objectFactory.createPackageInformationDescription(order.getPackages().get(0).getDescription()));
		
		TotalWeight weight = new TotalWeight();
		weight.setValue(order.getTotalActualWeight().intValue());
		weight.setWeightUnit(WeightUnit.LB);
		pack.setTotalWeight(weight);
		pack.setTotalPieces(order.getPackages().size());

		List<com.meritconinc.shiplinx.model.Package> packageList = order.getPackages();
		
		pack.setPiecesInformation(objectFactory.createPackageInformationPiecesInformation(new  ArrayOfPiece()));
		for(com.meritconinc.shiplinx.model.Package p :packageList){
			pack.getPiecesInformation().getValue().getPiece().add(setPiece(p.getLength().doubleValue(),p.getWidth().doubleValue(),p.getHeight().doubleValue(),p.getWeight().doubleValue()));
		}
		
		if(dangerousGoodSelectedByUser)
			pack.setDangerousGoodsDeclarationDocumentIndicator(availableDangGoodDeclaration);
		else
			pack.setDangerousGoodsDeclarationDocumentIndicator(dangerousGoodSelectedByUser); //i.e. false
		
		pack.setOptionsInformation(objectFactory.createOptionsInformation(getOptionInformation()));
		shipment.setPackageInformation(pack);
		
		if(!PurolatorAPI.isDomesticShipment(order)){
			shipment.setInternationalInformation(objectFactory.createInternationalInformation(getInternationInformation()));	
		}
		
		PaymentInformation pay = new PaymentInformation();
		if(!StringUtil.isEmpty(order.getBillToAccountNum())){
			pay.setPaymentType(PaymentType.THIRD_PARTY);
			pay.setRegisteredAccountNumber(customerCarrier.getAccountNumber1());
			pay.setBillingAccountNumber(objectFactory.createPaymentInformationBillingAccountNumber(order.getBillToAccountNum()));
		}
		else{
			pay.setPaymentType(PaymentType.SENDER);
			pay.setRegisteredAccountNumber(customerCarrier.getAccountNumber1());
			pay.setBillingAccountNumber(objectFactory.createPaymentInformationBillingAccountNumber(customerCarrier.getAccountNumber1()));
			order.setBillToAccountNum(customerCarrier.getAccountNumber1());
		}
		shipment.setPaymentInformation(pay);

		PickupInformation pickUp = new PickupInformation();
		if(order.getHoldForPickupRequired()!=null && order.getHoldForPickupRequired())
			pickUp.setPickupType(PickupType.PRE_SCHEDULED);
		else
			pickUp.setPickupType(PickupType.DROP_OFF);
		
		shipment.setPickupInformation(pickUp);
		

//		NotificationInformation note = new NotificationInformation();
//		note.setConfirmationEmailAddress("rahul.raghatate@sumasoft.net");
//		shipment.setNotificationInformation(note);

		TrackingReferenceInformation track = new TrackingReferenceInformation();
		track.setReference1(objectFactory.createTrackingReferenceInformationReference1(order.getReferenceCode()));
//		track.setReference1(objectFactory.createTrackingReferenceInformationReference1(order.getReferenceOne()));
//		track.setReference1(objectFactory.createTrackingReferenceInformationReference1(order.getReferenceCode()));
		shipment.setTrackingReferenceInformation(objectFactory.createReturnShipmentTrackingReferenceInformation(track));

		
		String specialInstructions = order.getToInstructions();
		if(specialInstructions!=null && specialInstructions.length()>30)
			specialInstructions = specialInstructions.substring(0,30); 
		OtherInformation otherInformation = new OtherInformation();
		otherInformation.setSpecialInstructions(objectFactory.createOtherInformationSpecialInstructions(specialInstructions));
		shipment.setOtherInformation(objectFactory.createOtherInformation(otherInformation));

		return shipment;
	}

	private String setWithMaxLength(String s, int max){
		if(s==null || s.length()==0)
			return "";
		
		if(s.length()<=max)
			return s;
		
		return s.substring(0, max);
		
	}
	
	private Address setSenderAddress()	{
		ObjectFactory objectFactory = new ObjectFactory();
		Address addr = new Address();

		com.meritconinc.shiplinx.model.Address shipFromAddress =  order.getFromAddress();

		addr.setName(setWithMaxLength(shipFromAddress.getContactName(),30));
		addr.setCompany(objectFactory.createAddressCompany(setWithMaxLength(shipFromAddress.getAbbreviationName(),30)));
		
		
//		addr.setStreetNumber(".");
		addr.setStreetNumber("b20");

		if(shipFromAddress.getAddress1()!=null){
			if(shipFromAddress.getAddress1().length()<=30) //max allowed length for this field is 25
				addr.setStreetName(shipFromAddress.getAddress1());
			else
			{
				addr.setStreetName(shipFromAddress.getAddress1().substring(0, 30));
				addr.setStreetAddress2(objectFactory.createAddressStreetAddress2(shipFromAddress.getAddress1().substring(30, shipFromAddress.getAddress1().length())));
			}
		}
		if(shipFromAddress.getAddress2()!=null){
			if(shipFromAddress.getAddress2().length()<=25) //max allowed length for this field is 25
				addr.setStreetAddress3(objectFactory.createAddressStreetAddress2(shipFromAddress.getAddress2()));
			else
				addr.setStreetAddress3(objectFactory.createAddressStreetAddress2(shipFromAddress.getAddress2().substring(0, 25)));
		}

		
		addr.setCity(shipFromAddress.getCity());
		addr.setProvince(shipFromAddress.getProvinceCode());
		addr.setCountry(shipFromAddress.getCountryCode());
		addr.setPostalCode(shipFromAddress.getPostalCode().replaceAll(" ", ""));

		PhoneNumber phone = setPhoneNumber(shipFromAddress.getPhoneNo());
		addr.setPhoneNumber(phone);
//		addr.setFaxNumber(phone);
		
		if(addr.getProvince().equalsIgnoreCase("NF"))
			addr.setProvince("NL");
		if(addr.getProvince().equalsIgnoreCase("PQ"))
			addr.setProvince("QC");

		if(addr.getPostalCode().equalsIgnoreCase("N/A"))
			addr.setPostalCode("");
		if(addr.getProvince().equalsIgnoreCase("N/A"))
			addr.setProvince("");

		return addr;
	}

	private Address setReceiverAddress(){
		ObjectFactory objectFactory = new ObjectFactory();
		Address addr = new Address();
		com.meritconinc.shiplinx.model.Address shipToAddress =  order.getToAddress();
		addr.setName(setWithMaxLength(shipToAddress.getContactName(),30));
		addr.setCompany(objectFactory.createAddressCompany(setWithMaxLength(shipToAddress.getAbbreviationName(),30)));
		
		boolean address2LineUsed = false;
		
		if(shipToAddress.getAddress1()!=null){
			if(shipToAddress.getAddress1().length()<=35)
				addr.setStreetName(shipToAddress.getAddress1());
			else{
				addr.setStreetName(shipToAddress.getAddress1().substring(0, 35));
				addr.setStreetAddress2(objectFactory.createAddressStreetAddress2(shipToAddress.getAddress1().substring(35, shipToAddress.getAddress1().length())));
				address2LineUsed = true; //need to know if address2 should go in field 2 or 3
			}
		}
		if(shipToAddress.getAddress2()!=null){
			if(shipToAddress.getAddress2().length()<=25){ //max allowed length for this field is 25
				if(address2LineUsed)
					addr.setStreetAddress3(objectFactory.createAddressStreetAddress3(shipToAddress.getAddress2()));
				else
					addr.setStreetAddress2(objectFactory.createAddressStreetAddress3(shipToAddress.getAddress2()));
			}
			else{
				if(address2LineUsed)
					addr.setStreetAddress3(objectFactory.createAddressStreetAddress3(shipToAddress.getAddress2().substring(0, 25)));
				else
					addr.setStreetAddress2(objectFactory.createAddressStreetAddress3(shipToAddress.getAddress2().substring(0, 25)));
			}
		}

		addr.setStreetNumber(".");
		addr.setCity(shipToAddress.getCity());
		addr.setProvince(shipToAddress.getProvinceCode());
		addr.setCountry(shipToAddress.getCountryCode());
		addr.setPostalCode(shipToAddress.getPostalCode().replaceAll(" ", ""));

		PhoneNumber phone = setPhoneNumber(shipToAddress.getPhoneNo());
		addr.setPhoneNumber(phone);
//		addr.setFaxNumber(phone);

		if(addr.getProvince().equalsIgnoreCase("NF"))
			addr.setProvince("NL");
		if(addr.getProvince().equalsIgnoreCase("PQ"))
			addr.setProvince("QC");

		if(addr.getPostalCode().equalsIgnoreCase("N/A"))
			addr.setPostalCode("");
		if(addr.getProvince().equalsIgnoreCase("N/A"))
			addr.setProvince("");

		if(addr.getPostalCode().equalsIgnoreCase("N/A"))
			addr.setPostalCode("");
		if(addr.getProvince().equalsIgnoreCase("N/A"))
			addr.setProvince("");

		return addr;
	}

	private PhoneNumber setPhoneNumber(String phNo)	{
		PhoneNumber ph = new PhoneNumber();
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
		
		return ph;
	}

	private Piece setPiece(double length,double width,double height,double pweight ){
		ObjectFactory objectFactory = new ObjectFactory();

		Piece piece = new Piece();
		//List<Package> pack = shippingOrder.getPackages();

		Weight weight = new Weight();
		java.math.BigDecimal w = new java.math.BigDecimal(pweight);
		weight.setValue(w);
		weight.setWeightUnit(WeightUnit.LB);
		piece.setWeight(weight);

		if(order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PACKAGE || order.getPackageTypeId().getPackageTypeId() == ShiplinxConstants.PACKAGE_TYPE_PALLET){
			piece.setLength(objectFactory.createPieceLength(setDimension(length)));
			piece.setWidth(objectFactory.createPieceWidth(setDimension(width)));
			piece.setHeight(objectFactory.createPieceHeight(setDimension(height)));
		}else{
			piece.setLength(objectFactory.createPieceLength(setDimension(0.0)));
			piece.setWidth(objectFactory.createPieceWidth(setDimension(0.0)));
			piece.setHeight(objectFactory.createPieceHeight(setDimension(0.0)));
		}

		return piece;
	}

	private Dimension setDimension(double value){
		Dimension dim = new Dimension();
		java.math.BigDecimal v = new java.math.BigDecimal(value);
		dim.setValue(v);
		dim.setDimensionUnit(DimensionUnit.IN);
		return dim;
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

	private InternationalInformation getInternationInformation() {
		ObjectFactory objectFactory = new ObjectFactory();
		InternationalInformation internationalInformation = new InternationalInformation();
		boolean docOnlyAvail = false;//!availableDangerousGoods;
		boolean boolNAFD=false;
		boolean boolFDA=false;
		boolean boolFCC=false;
		internationalInformation.setDocumentsOnlyIndicator(docOnlyAvail);
		

		//DocumentsOnlyIndicator
		logger.debug("---!availableDangerousGoods---" + !availableDangerousGoods);
		//internationalInformation.setDocumentsOnlyIndicator(!availableDangerousGoods);
		internationalInformation.setDocumentsOnlyIndicator(false);
		
		//DutyInformation
		DutyInformation dutyInformation = new DutyInformation();
		dutyInformation.setBusinessRelationship(BusinessRelationship.NOT_RELATED);
		
		dutyInformation.setBillDutiesToParty(BillDutiesToParty.RECEIVER);
		
		if(order.getCustomsInvoice()!=null){
			if(order.getCustomsInvoice().getBillTo()!=null && order.getCustomsInvoice().getBillTo().equalsIgnoreCase(ShiplinxConstants.BILL_TO_SHIPPER)){
				dutyInformation.setBillDutiesToParty(BillDutiesToParty.SENDER);
				order.getCustomsInvoice().setBillToAccountNum(customerCarrier.getAccountNumber1());
			}
		}
		
		if(order.getCustomsInvoice().getCurrency().equals(ShiplinxConstants.CURRENCY_CA_STRING))
			dutyInformation.setCurrency(DutyCurrency.CAD);
		else
			dutyInformation.setCurrency(DutyCurrency.USD);
		
		internationalInformation.setDutyInformation(objectFactory.createDutyInformation(dutyInformation));
		
		ContentDetails ContentDetails = new ContentDetails();
		
		ContentDetail[] contentDetail  = new ContentDetail[1];
		
		if(!docOnlyAvail){
			for(int i=0; i<1;i++){
				logger.debug("------i--------"+i);
				logger.debug("----contentDetail[i]-------"+contentDetail[i]);
				ContentDetail contentDetailTemp = new ContentDetail();
				if((order.getToAddress().getCountryCode()).equals("US")){
					boolNAFD=true;
					boolFDA=true;
					boolFCC=true;
				}
				if(((order.getToAddress().getCountryCode()).equals("CA"))||((order.getToAddress().getCountryCode()).equals("MX"))){
					boolNAFD=true;
				}
				contentDetailTemp.setDescription("desription");
				contentDetailTemp.setHarmonizedCode("123");
				logger.debug("Orgin Country");
				if(order.getCustomsInvoice()!=null){
					/*long customsInvoiceId = order.getCustomsInvoice().getId();*/					
					/*shippingDAO = (ShippingDAO)MmrBeanLocator.getInstance().findBean("shippingDAO");
					List<Products> listProducts=shippingDAO.getCustomsInvoiceProducts(customsInvoiceId);*/
					if(order.getCustomsInvoice().getProducts() != null && order.getCustomsInvoice().getProducts().size()>0){
						contentDetailTemp.setCountryOfManufacture(order.getCustomsInvoice().getProducts().get(0).getProductOrigin());						
					}else{
						contentDetailTemp.setCountryOfManufacture(order.getFromAddress().getCountryCode());
					}
				}else{
					contentDetailTemp.setCountryOfManufacture(order.getFromAddress().getCountryCode());
				}
				contentDetailTemp.setProductCode("123456");
				contentDetailTemp.setQuantity(1);
				Double totalValue = order.getCustomsInvoice().getTotalValue();
				if(totalValue!=null && totalValue>0){
					contentDetailTemp.setUnitValue(new BigDecimal(totalValue));
				}else if(order.getDutiableAmount().floatValue()>0){
					contentDetailTemp.setUnitValue(order.getDutiableAmount());
				}else{
					contentDetailTemp.setUnitValue(new BigDecimal("1"));
				}
				contentDetailTemp.setNAFTADocumentIndicator(boolNAFD);
				contentDetailTemp.setFDADocumentIndicator(boolFDA);
				contentDetailTemp.setFCCDocumentIndicator(boolFCC);
				contentDetailTemp.setNAFTADocumentIndicator(boolNAFD);
				contentDetailTemp.setFDADocumentIndicator(boolFDA);
				contentDetailTemp.setFCCDocumentIndicator(boolFCC);
				contentDetailTemp.setSenderIsProducerIndicator(true);
				contentDetail[i]=contentDetailTemp;
				ContentDetails.getContentDetail().add(contentDetailTemp);
			}
		}
			
		
		internationalInformation.setContentDetails(objectFactory.createContentDetails(ContentDetails));
		
		//ImportExportType
		if(order.getCustomsInvoice().getIncoTerms()!=null && order.getCustomsInvoice().getIncoTerms().equalsIgnoreCase("Temporary")){
			internationalInformation.setImportExportType(objectFactory.createImportExportType(ImportExportType.TEMPORARY));
			}else if(order.getCustomsInvoice().getIncoTerms()!=null && order.getCustomsInvoice().getIncoTerms().equalsIgnoreCase("Repair")){
				internationalInformation.setImportExportType(objectFactory.createImportExportType(ImportExportType.REPAIR));
			}else{
				internationalInformation.setImportExportType(objectFactory.createImportExportType(ImportExportType.PERMANENT));
			}
		
		//CustomsInvoiceDocumentIndicator
		internationalInformation.setCustomsInvoiceDocumentIndicator(availableDangGoodDeclaration);
		
		return internationalInformation;
	}
	
	private String getDigitsFromPH(String phoneNumber) {
		int length = phoneNumber.length();  
		StringBuffer buffer = new StringBuffer();  
		for(int i = 0; i < length; i++) {  
			char ch = phoneNumber.charAt(i);  
			if (Character.isDigit(ch)) {  
				buffer.append(ch);  
			}  
		}  
		return buffer.toString();  
	}

	private void getOptionAvailable(Service service){
		availableDangerousGoodMode = "";
		availableCODValue = false;
		
		try{
    		logger.debug("---------------service------------"+service);
    				logger.debug("\tID %s \n"+ service.getID());
    				
       				JAXBElement<ArrayOfOption> options = service.getOptions();
    				if (options != null && options.getValue() != null && options.getValue().getOption() != null){
   						for (Option option2 : options.getValue().getOption()){
    							logger.debug("option2---"+option2.getID());
    							if("SaturdayDelivery".equalsIgnoreCase(option2.getID())){
									availableSaturdayService = true;
								}
    							if("ExpressCheque".equalsIgnoreCase(option2.getID())){
    								availableCODValue = true;
    							}
    							if("ResidentialSignatureDomestic".equalsIgnoreCase(option2.getID())
    									||"ResidentialSignatureIntl".equalsIgnoreCase(option2.getID())){
    								availableSignatureRequired = true;
       								//if residential is not set, then this is a good indicator that the ship to address is residential, so set it
    								if(!order.getToAddress().isResidential())
    									isRes = true; //created a local boolean 'isRes' as we do not want to set this shipment to residential for other carriers during rating time
    							}
    							ArrayOfOptionValue optionvalues = option2.getPossibleValues();
       							JAXBElement<ArrayOfOption> childoptions = option2.getChildServiceOptions();
       							if (childoptions != null && childoptions.getValue() != null && childoptions.getValue().getOption() != null){
    							
    								for (Option choption : childoptions.getValue().getOption()){
    									logger.debug("choption---"+choption.getID());
    									if("DangerousGoodsMode".equalsIgnoreCase(choption.getID())){
    										optionvalues = choption.getPossibleValues();
        									if (optionvalues != null && optionvalues.getOptionValue() != null){ 
           										availableDangerousGoodMode = optionvalues.getOptionValue().get(0).getValue();
        										availableDangerousGoods = true;
        									}
    									}
    									/*
    									
    									if("ExpressChequeMethodOfPayment".equalsIgnoreCase(choption.getID())){
    										optionvalues = choption.getPossibleValues();
        									if (optionvalues != null && optionvalues.getOptionValue() != null){ 
        										availableCODValue = true;
        									}
    									}*/
    									optionvalues = choption.getPossibleValues();
    								}
    							}
    						}
    					}
    					else{
    						 logger.debug("\tOptions not available");
    					}
    					
    				if("".equals(availableDangerousGoodMode)){
    					availableDangerousGoods = false;
					}
    				logger.debug("----service.getID()--"+service.getID()+"----dngMode---------"+availableDangerousGoodMode 
    						+ "------"+availableDangerousGoods+"----availableCODValue---------"+availableCODValue);
    				
    				
    		}catch (Exception e) {
				e.printStackTrace();
			}
		
		// if user select other than FullyRegulated and service support air mode, then service must not included in rate map
		if(availableDangerousGoodMode.equalsIgnoreCase(PurolatorAPI.PURO_WEB_TRANSPORT_MODE_AIR)
				&& order.getDangerousGoods()!=ShiplinxConstants.DG_FULLY_REGULATED){
			availableDangerousGoods = false;
		}
    		
		logger.debug("---dangGoods---"+availableDangerousGoods);
		
		if(availableDangerousGoodMode.equalsIgnoreCase(PurolatorAPI.PURO_WEB_TRANSPORT_MODE_GROUND))
				availableDangGoodDeclaration = true;
		else
			availableDangGoodDeclaration = false;
		
		if(!availableDangerousGoods){
			availableDangGoodDeclaration = false;
		}
		
	}
	
	private OptionsInformation getOptionInformation() {
		
		ObjectFactory objectFactory = new ObjectFactory();
		OptionIDValuePair option;
		ArrayOfOptionIDValuePair arr = new ArrayOfOptionIDValuePair();
		logger.debug("-----dngMode::"+availableDangerousGoodMode + "----DangerousGoods::"+availableDangerousGoods);
		boolean codSelected = false;
		
		
		if(dangerousGoodSelectedByUser){
			option = new OptionIDValuePair();
			option.setID("DangerousGoods");
			option.setValue("true");
			arr.getOptionIDValuePair().add(option);
			
			option = new OptionIDValuePair();
			option.setID("DangerousGoodsClass");
			
			//only one value is available for Air
			if(availableDangerousGoodMode.equalsIgnoreCase(PurolatorAPI.PURO_WEB_TRANSPORT_MODE_AIR)){
				dangerousGoodsClass = "FullyRegulated";
			}
			
			if(availableDangerousGoodMode.equalsIgnoreCase(PurolatorAPI.PURO_WEB_TRANSPORT_MODE_GROUND)){
				if(order.getDangerousGoods() == ShiplinxConstants.DG_500KG_EXEMPTION){
					dangerousGoodsClass = "LessThan500kgExempt";
				}
				else if(order.getDangerousGoods() == ShiplinxConstants.DG_FULLY_REGULATED){
					dangerousGoodsClass = "FullyRegulated";
				}
				else if(order.getDangerousGoods() == ShiplinxConstants.DG_LIMITED_QUANTITY){
					dangerousGoodsClass = "LimitedQuantities";
				}
			}
			
			option.setValue(dangerousGoodsClass);
			arr.getOptionIDValuePair().add(option);

			option = new OptionIDValuePair();
			option.setID("DangerousGoodsMode");
			option.setValue(availableDangerousGoodMode);
			arr.getOptionIDValuePair().add(option);
			
		}
		
		if(order.getHoldForPickupRequired()!=null && order.getHoldForPickupRequired()){
			option = new OptionIDValuePair();
			option.setID("HoldForPickup");
			option.setValue("true");
			arr.getOptionIDValuePair().add(option);
		}
		logger.debug("-----shippingOrder.getSaturdayDeliveryRequired()------"+ order.getSatDelivery());
		if(order.getSatDelivery()!=null && order.getSatDelivery() /*&& availableSaturdayService*/){
			option = new OptionIDValuePair();
			option.setID("SaturdayDelivery");
			option.setValue("true");
			arr.getOptionIDValuePair().add(option);
		}
		logger.debug("-----shippingOrder.getSignatureRequired()--------"+ order.getSignatureRequired());
		if(availableSignatureRequired && (order.getSignatureRequired()!=ShiplinxConstants.SIGNATURE_REQUIRED_NO || isCODRequested())){
		option = new OptionIDValuePair();
		if(PurolatorAPI.isUSShipment(order) || !PurolatorAPI.isDomesticShipment(order))
				option.setID("ResidentialSignatureIntl");
			else
				option.setID("ResidentialSignatureDomestic");
			option.setValue("true");
			arr.getOptionIDValuePair().add(option);
		}
		else if(availableSignatureRequired && order.getSignatureRequired()==ShiplinxConstants.SIGNATURE_REQUIRED_NO) //residential signature is available but not requested by customer
		{
			if(PurolatorAPI.isDomesticShipment(order))
			{
				option = new OptionIDValuePair();
				option = new OptionIDValuePair();
				option.setID("OriginSignatureNotRequired");
				option.setValue("true");
				arr.getOptionIDValuePair().add(option);
			}
		}
//		Below code is sending OSNR for shipments going to business locations, even though signature is free to business locations
//		Issue with commenting code below is that customer will not be able to request SNR for business locations. Will need to add the SNR instructions in the instructions section
		else if((order.getToAddress().isResidential() || isRes) && availableSignatureRequired && order.getSignatureRequired()==ShiplinxConstants.SIGNATURE_REQUIRED_NO) //residential signature is available but not requested by customer
		{
			if(PurolatorAPI.isDomesticShipment(order))
			{
				option = new OptionIDValuePair();
				option = new OptionIDValuePair();
				option.setID("OriginSignatureNotRequired");
				option.setValue("true");
				arr.getOptionIDValuePair().add(option);
			}
		}

		if(order.getInsuranceValue() > 0){
			option = new OptionIDValuePair();
			option.setID("DeclaredValue");
			option.setValue(String.valueOf(order.getInsuranceValue()));
			arr.getOptionIDValuePair().add(option);
		}

		if(order.getCODPayment() !=null && order.getCODValue() > 0 && PurolatorAPI.isDomesticShipment(order)
				&& !"None".equalsIgnoreCase(order.getCODPayment())){
				codSelected = true;
			
			option = new OptionIDValuePair();
			option.setID("ExpressCheque");
			option.setValue("true");
			arr.getOptionIDValuePair().add(option);
			
			option = new OptionIDValuePair();
			option.setID("ExpressChequeMethodOfPayment");
			
			if(ShiplinxConstants.COD_CERTIFIED_CHECK.equalsIgnoreCase(order.getCODPayment()))
				option.setValue("CertifiedCheque");
			else if(ShiplinxConstants.COD_CHECK.equalsIgnoreCase(order.getCODPayment()))
				option.setValue("Cheque");
			
			arr.getOptionIDValuePair().add(option);
			
			option = new OptionIDValuePair();
			option.setID("ExpressChequeAmount");
			option.setValue(String.valueOf(order.getCODValue()));
			arr.getOptionIDValuePair().add(option);
			optInf.setExpressChequeAddress(objectFactory.createOptionsInformationExpressChequeAddress(setSenderAddress()));
		}

		optInf.setOptions(arr); 
		return optInf;
	}
	
	protected boolean isCODRequested(){
		
		for(com.meritconinc.shiplinx.model.Package p: order.getPackages()){
			if(p.getCodAmount()!=null && p.getCodAmount().floatValue() > 0)
				return true;
		}
		return false;
	
	}

	public CreateShipmentResponseContainer sendShippingRequest(CreateShipmentRequestContainer request) throws PurolatorException{

		CreateShipmentResponseContainer response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(ShippingServiceContract.class);
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(PurolatorAPI.TEST_URL_SHIP);
			else
				factory.setAddress(PurolatorAPI.LIVE_URL_SHIP);
			
			ShippingServiceContract client = (ShippingServiceContract) factory.create();
			
			Client clientProxy = ClientProxy.getClient(client);
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
			Header dummyHeader = new Header(new QName("http://purolator.com/pws/datatypes/v1", "RequestContext"), reqContext,
			                                new JAXBDataBinding(RequestContext.class));
			headers.add(dummyHeader);

			
			((BindingProvider)client).getRequestContext().put(Header.HEADER_LIST, headers);

			response = client.createShipment(request);
			
			logger.debug("Response:" + response);
		} 
		catch(ShippingServiceContractCreateShipmentValidationFaultFaultFaultMessage sem){
			logger.error("Error sending shipping request to Purolator", sem);
		}
		catch (Exception e) {
			logger.error("Error sending shipping request to Purolator", e);
		}

		return response;
	}	

	public VoidShipmentResponseContainer sendVoidRequest(VoidShipmentRequestContainer request) throws PurolatorException{

		VoidShipmentResponseContainer response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(ShippingServiceContract.class);
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(PurolatorAPI.TEST_URL_SHIP);
			else
				factory.setAddress(PurolatorAPI.LIVE_URL_SHIP);
			
			ShippingServiceContract client = (ShippingServiceContract) factory.create();
			
			Client clientProxy = ClientProxy.getClient(client);
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
			Header dummyHeader = new Header(new QName("http://purolator.com/pws/datatypes/v1", "RequestContext"), reqContext,
			                                new JAXBDataBinding(RequestContext.class));
			headers.add(dummyHeader);

			
			((BindingProvider)client).getRequestContext().put(Header.HEADER_LIST, headers);

			response = client.voidShipment(request);
			
			logger.debug("Response:" + response);
		} 
		catch(ShippingServiceContractVoidShipmentValidationFaultFaultFaultMessage sem){
			logger.error("Error sending void request to Purolator", sem);
		}
		catch (Exception e) {
			logger.error("Error sending void request to Purolator", e);
		}

		return response;
	}	

	private RequestContext getRequestContext() {
		// TODO Auto-generated method stub
		RequestContext reqContext = new RequestContext();
		reqContext.setVersion("1.0");
		reqContext.setLanguage(com.meritconinc.shiplinx.carrier.purolator.ws.shipping.proxy.Language.EN);
		reqContext.setGroupID("1");
		reqContext.setRequestReference("RequestReference");
		return reqContext;
	}

}
