package com.meritconinc.shiplinx.carrier.ups.stub;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.ups.service.UPSAPI;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.AccountAddressType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.BillShipperType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.BillThirdPartyChargeType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.CurrencyMonetaryType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.DeliveryConfirmationType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.DimensionsType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ErrorDetailType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.Errors;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.LabelImageFormatType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.LabelSpecificationType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.PSOCODType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.PackageDeclaredValueType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.PackageServiceOptionsType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.PackageType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.PackageWeightType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.PaymentInfoType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.RequestType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ReturnServiceType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipAddressType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipFromType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipPhoneType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipPortType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipService;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipToAddressType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipToType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipUnitOfMeasurementType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipmentChargeType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipmentErrorMessage;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipmentRequest;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipmentResponse;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipmentType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipperType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.TransactionReferenceType;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.UPSSecurity;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ShipmentType.ShipmentServiceOptions;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.UPSSecurity.ServiceAccessToken;
import com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.UPSSecurity.UsernameToken;
import com.meritconinc.shiplinx.carrier.utils.UPSException;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class ShipmentRequestWebServiceClient{  
	private static Logger logger = Logger.getLogger(ShipmentRequestWebServiceClient.class);
	ShipmentRequest request = new ShipmentRequest();
	private ShippingOrder order=null;
	private CustomerCarrier customerCarrier=null;
	String currency=null;

	
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
 
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public ShipmentRequestWebServiceClient(ShippingOrder order, CustomerCarrier customerCarrier)
	{
		this.order = order;
		this.customerCarrier = customerCarrier;
		
	}
	
	public ShipmentRequest buildRequest() {   
	    
        setOrder(); 
        
        return request;
       // return sendRequest();
        
        
	}
	
//	public ShipmentResponse sendRequest() {
//		logger.debug("Ship request for service: "
//				+ request.getShipment().getService().getCode()
//				+ ", using account #: "
//				+ request.getShipment().getShipper().getShipperNumber());
//		ShipmentResponse reply = null;
//		try {
//			// Initialize the service
//			ShipService service;
//			ShipPortType port;
//
//			service = new ShipService(new URL("http://wwwcie.ups.com/webservices/Ship"));
//			// service = new ShipService(new
//			// URL("https://onlinetools.ups.com/webservices/Ship"));
//			// service = new ShipService();
//			updateEndPoint(service);
//			port = service.getShipPort();
//			UPSSecurity upsSecurity = new UPSSecurity();
//			upsSecurity.setServiceAccessToken(new ServiceAccessToken());
//			upsSecurity.getServiceAccessToken().setAccessLicenseNumber(customerCarrier.getProperty1());
//			upsSecurity.setUsernameToken(new UsernameToken());
//			upsSecurity.getUsernameToken().setUsername(customerCarrier.getProperty2());
//			upsSecurity.getUsernameToken().setPassword(customerCarrier.getProperty3());
//			// This is the call to the web service passing in a RateRequest and
//			// returning a RateReply
//			reply = port.processShipment(request, upsSecurity); // Service call
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("error:" + e.getMessage(), e);
//		}
//
//		return reply;
//	}
	
	
	public ShipmentResponse sendRequest() throws UPSException{

		ShipmentResponse response = null;
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(ShipPortType.class);
			
			if(ShiplinxConstants.isTestMode())
				factory.setAddress(UPSAPI.TEST_URL_WS_SHIP);		// Test
			else
				factory.setAddress(UPSAPI.LIVE_URL_WS_SHIP);		// Test

			// SSL Setup
			ShipPortType client = (ShipPortType) factory.create();
			
			response = client.processShipment(request, getUpsSecurity());

		} 
		catch(ShipmentErrorMessage sem){
			StringBuilder stb = new StringBuilder();
			Errors errors = sem.getFaultInfo(); 
			for(ErrorDetailType edt: errors.getErrorDetail()){
				stb.append(edt.getPrimaryErrorCode().getCode() + ": ");
				stb.append(edt.getPrimaryErrorCode().getDescription() + ". ");
			}
						
			throw new UPSException(stb.toString());
		}
		catch (Exception e) {
			throw new UPSException(e.getMessage());
		}

		return response;
	}	
	
	
	private UPSSecurity getUpsSecurity() {
		// TODO Auto-generated method stub
		UPSSecurity upsSecurity = new UPSSecurity();
		upsSecurity.setServiceAccessToken(new ServiceAccessToken());
		upsSecurity.getServiceAccessToken().setAccessLicenseNumber(customerCarrier.getProperty1());
		upsSecurity.setUsernameToken(new UsernameToken());
		upsSecurity.getUsernameToken().setUsername(customerCarrier.getProperty2());
		upsSecurity.getUsernameToken().setPassword(customerCarrier.getProperty3());
		return upsSecurity;
	}

	private static void updateEndPoint(ShipService service) {
//		service.getServiceName()
//		String endPoint;
//		if(ShiplinxConstants.isTestMode()))
//			endPoint=FedExNewWebAPI.TEST_URL_RATE;	
//		else
//			endPoint=FedExNewWebAPI.LIVE_URL_RATE;	
//		if (endPoint != null) {
//			service.
//		}
	}
	public void setOrder() {
		
		try{
			
			com.meritconinc.shiplinx.model.Address from = order.getFromAddress();
			com.meritconinc.shiplinx.model.Address to = order.getToAddress();

			RequestType type = new RequestType(); 
			type.getRequestOption().add("validate");
			TransactionReferenceType tran_ref = new TransactionReferenceType();
			tran_ref.setTransactionIdentifier(order.getBusiness().getShortCode());
			type.setTransactionReference(tran_ref);
			request.setRequest(type);

			LabelSpecificationType label_spec = new LabelSpecificationType();
			label_spec.setLabelImageFormat(new LabelImageFormatType());
			label_spec.getLabelImageFormat().setCode("GIF");
			request.setLabelSpecification(label_spec);
			
			//in case of a forward shipment, set the shipper address as the from address, as this is the address that is displayed on the label
			//in case of return shipment, we need to set the address of the account number as the shipper address
			
			Address shipperAddress = null;
			if(!order.getFromAddress().getCountryCode().equalsIgnoreCase(customerCarrier.getCountry()))
				shipperAddress = order.getBusiness().getAddress();
			else
				shipperAddress = from;
			
			ShipmentType shipment = new ShipmentType();
			shipment.setDescription("Various");
			ShipperType shipper = new ShipperType();
			shipper.setName(StringUtil.setWithMaxLength(shipperAddress.getAbbreviationName(), 35));
			shipper.setAttentionName(StringUtil.setWithMaxLength(shipperAddress.getContactName(), 35));
			//shipper.setTaxIdentificationNumber("");
			shipper.setPhone(new ShipPhoneType());
			shipper.getPhone().setNumber(shipperAddress.getPhoneNo());
//			shipper.getPhone().setExtension("");
			
			String accountNum = customerCarrier.getAccountNumber1();
			if(accountNum!=null && accountNum.length()>6){ //the EDI file is sending back the account # padded with zeros on the left. WE are storing the padded zeros in our customerCarrier record, so need to remove them before sending for rating/shipping
				int length = accountNum.length();				
				shipper.setShipperNumber(accountNum.substring(length-6, length));
			}
			else
				shipper.setShipperNumber(accountNum);    

//			shipper.setFaxNumber(from.getFaxNo());
			shipper.setEMailAddress(shipperAddress.getEmailAddress());
			shipper.setAddress(new ShipAddressType());
			shipper.getAddress().setCity(shipperAddress.getCity());
			shipper.getAddress().setCountryCode(shipperAddress.getCountryCode());
			shipper.getAddress().setPostalCode(shipperAddress.getPostalCode().replaceAll(" ", ""));
			shipper.getAddress().setStateProvinceCode(shipperAddress.getProvinceCode());
			if(shipper.getAddress().getStateProvinceCode().equalsIgnoreCase("PQ"))
				shipper.getAddress().setStateProvinceCode("QC");
			shipper.getAddress().getAddressLine().add(0, StringUtil.setWithMaxLength(shipperAddress.getAddress1(),35));
			shipper.getAddress().getAddressLine().add(1, StringUtil.setWithMaxLength(shipperAddress.getAddress2(),35));

			ShipFromType shipFrom = new ShipFromType();
			shipFrom.setName(StringUtil.setWithMaxLength(from.getAbbreviationName(),35));
			shipFrom.setAttentionName(StringUtil.setWithMaxLength(from.getContactName(), 35));
			//shipFrom.setTaxIdentificationNumber("");
			shipFrom.setPhone(new ShipPhoneType());
			shipFrom.getPhone().setNumber(from.getPhoneNo());
//			shipFrom.getPhone().setExtension("");
//			shipFrom.setFaxNumber(from.getFaxNo());
			shipFrom.setAddress(new ShipAddressType());
			shipFrom.getAddress().setCity(from.getCity());
			shipFrom.getAddress().setCountryCode(from.getCountryCode());
			shipFrom.getAddress().setPostalCode(from.getPostalCode().replaceAll(" ", ""));
			shipFrom.getAddress().setStateProvinceCode(from.getProvinceCode());
			if(shipFrom.getAddress().getStateProvinceCode().equalsIgnoreCase("PQ"))
				shipFrom.getAddress().setStateProvinceCode("QC");
			shipFrom.getAddress().getAddressLine().add(0, StringUtil.setWithMaxLength(from.getAddress1(),35));
			shipFrom.getAddress().getAddressLine().add(1, StringUtil.setWithMaxLength(from.getAddress2(),35));

			ShipToType shipTo = new ShipToType();
			shipTo.setName(StringUtil.setWithMaxLength(to.getAbbreviationName(),35));
			shipTo.setAttentionName(StringUtil.setWithMaxLength(to.getContactName(),35));
			//shipTo.setTaxIdentificationNumber("");
			shipTo.setPhone(new ShipPhoneType());
			shipTo.getPhone().setNumber(to.getPhoneNo());
//			shipTo.getPhone().setExtension("");
//			shipTo.setFaxNumber(to.getFaxNo());
			shipTo.setAddress(new ShipToAddressType());
			shipTo.getAddress().setCity(to.getCity());
			shipTo.getAddress().setCountryCode(to.getCountryCode());
			shipTo.getAddress().setPostalCode(to.getPostalCode().replaceAll(" ", ""));
			shipTo.getAddress().setStateProvinceCode(to.getProvinceCode());
			if(shipTo.getAddress().getStateProvinceCode().equalsIgnoreCase("PQ"))
				shipTo.getAddress().setStateProvinceCode("QC");
			shipTo.getAddress().getAddressLine().add(0, StringUtil.setWithMaxLength(to.getAddress1(),35));
			shipTo.getAddress().getAddressLine().add(1, StringUtil.setWithMaxLength(to.getAddress2(),35));
//			shipTo.setLocationID("");			
			if(order.getToAddress().isResidential())
				shipTo.getAddress().setResidentialAddressIndicator("true");
			
			shipment.setShipper(shipper);
			shipment.setShipFrom(shipFrom);
			shipment.setShipTo(shipTo);

			shipment.setPaymentInformation(new PaymentInfoType());
			
			//Transportation charges are the responsibility of the account being passed
			//Duties and Taxes will be billed to receiver. This needs to be made dynamic based on information to be collected on customs document form
			ShipmentChargeType shipment_charge_type_transportation = new ShipmentChargeType();
			shipment_charge_type_transportation.setType("01"); //Transportation
			//bill transportation charges to third party
			if(!StringUtil.isEmpty(order.getBillToAccountNum())){
				shipment_charge_type_transportation.setBillThirdParty(new BillThirdPartyChargeType());
				shipment_charge_type_transportation.getBillThirdParty().setAccountNumber(StringUtil.setWithMaxLength(order.getBillToAccountNum(), 6));
				shipment_charge_type_transportation.getBillThirdParty().setAddress(new AccountAddressType());				
				shipment_charge_type_transportation.getBillThirdParty().getAddress().setCountryCode(order.getBillToAccountCountry());
				shipment_charge_type_transportation.getBillThirdParty().getAddress().setPostalCode(order.getBillToAccountPostalCode());
			}
			else{
				shipment_charge_type_transportation.setBillShipper(new BillShipperType());
				shipment_charge_type_transportation.getBillShipper().setAccountNumber(shipper.getShipperNumber());
				order.setBillToAccountNum(shipper.getShipperNumber());
			}
			shipment.getPaymentInformation().getShipmentCharge().add(0, shipment_charge_type_transportation);
			if(order.getCustomsInvoice()!=null && order.isCustomsInvoiceRequired()){
				if(order.getCustomsInvoice().getBillTo().equalsIgnoreCase(ShiplinxConstants.BILL_TO_SHIPPER)){
					ShipmentChargeType shipment_charge_type_duties = new ShipmentChargeType();
					shipment_charge_type_duties.setType("02"); //Duties and Taxes
					shipment_charge_type_duties.setBillShipper(new BillShipperType());
					shipment_charge_type_duties.getBillShipper().setAccountNumber(shipper.getShipperNumber());
					shipment.getPaymentInformation().getShipmentCharge().add(1, shipment_charge_type_duties);
					order.getCustomsInvoice().setBillToAccountNum(shipper.getShipperNumber());
				}
				else if(!StringUtil.isEmpty(order.getCustomsInvoice().getBillToAccountNum()) || order.getCustomsInvoice().getBillTo().equalsIgnoreCase(ShiplinxConstants.BILL_TO_THIRD_PARTY)){
					ShipmentChargeType shipment_charge_type_duties = new ShipmentChargeType();
					shipment_charge_type_duties.setType("02"); //Duties and Taxes
					shipment_charge_type_duties.setBillThirdParty(new BillThirdPartyChargeType());
					shipment_charge_type_duties.getBillThirdParty().setAccountNumber(order.getCustomsInvoice().getBillToAccountNum());
					shipment_charge_type_duties.getBillThirdParty().setAddress(new AccountAddressType());
					shipment_charge_type_duties.getBillThirdParty().getAddress().setCountryCode(order.getCustomsInvoice().getBillToAddress().getCountryCode());
					shipment_charge_type_duties.getBillThirdParty().getAddress().setPostalCode(order.getCustomsInvoice().getBillToAddress().getPostalCode());
					shipment.getPaymentInformation().getShipmentCharge().add(1, shipment_charge_type_duties);
				}			
			}
//			ReferenceNumberType ref1 = new ReferenceNumberType();
//			ref1.setValue("TEST");//order.getReferenceCode());
//			shipment.getReferenceNumber().add(ref1);
			
			shipment.setService(new com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.ServiceType());
			shipment.getService().setCode(order.getService().getCode());
			
			/*
			 * 08 May 2012 Rizwan Merchant
			 * 
			 * Implemented this solution in order to facilitate conversion of codes for some services.
			 * UPS returns different service codes when rating and accepts different codes for shipping
			 * under the same service.
			 * 
			 * Service (Destination) TNT Rating Shipping Tracking
			 * Express Saver (CA) 20 13 65 065 or 04
			 * Expedited (CA) 19 02 08 008 or 17
			 * 
			 */
			
			if(customerCarrier.getCarrierId() != ShiplinxConstants.CARRIER_UPS_USA){
				if (shipment.getService().getCode().equals("13")){
					shipment.getService().setCode("65");
				}else if(shipment.getService().getCode().equals("02")){
					shipment.getService().setCode("08");
				}
			}
			//InvoiceLineTotal element is required for shipments from US to Canada or PR
			if(order.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US)){
					if(order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) || order.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.PUERTORICO)){
					shipment.setInvoiceLineTotal(new CurrencyMonetaryType());
					if(customerCarrier.getCountry().equals(ShiplinxConstants.CANADA))
						shipment.getInvoiceLineTotal().setCurrencyCode(ShiplinxConstants.CURRENCY_CA_STRING);
					else
						shipment.getInvoiceLineTotal().setCurrencyCode(ShiplinxConstants.CURRENCY_US_STRING);
					Double invoiceTotal = new Double(order.getDutiableAmount().doubleValue());
					if(invoiceTotal<1) //minimum value is 1
						invoiceTotal = 10.0;
					shipment.getInvoiceLineTotal().setMonetaryValue(String.valueOf(invoiceTotal.intValue()));
				}
			}
			
			shipment.setShipmentServiceOptions(new ShipmentServiceOptions());
			if(order.getSatDelivery())
				shipment.getShipmentServiceOptions().setSaturdayDeliveryIndicator("true");
			
			//if the origin is not the same as the country of account, then treat this as a return shipment
			if(!order.getFromAddress().getCountryCode().equalsIgnoreCase(customerCarrier.getCountry())){		
				shipment.setReturnService(new ReturnServiceType());
				shipment.getReturnService().setCode("9");
			}
				
			
			for(com.meritconinc.shiplinx.model.Package p: order.getPackages()){	
			
				PackageType pack = new PackageType();
				if(p.getDescription()==null || p.getDescription().length()==0)
					pack.setDescription("Various");
				else
					pack.setDescription(StringUtil.setWithMaxLength(p.getDescription(), 35));
				pack.setPackaging(new com.meritconinc.shiplinx.carrier.ups.ws.ship.proxy.PackagingType());
				pack.getPackaging().setCode(UPSAPI.getUPSPackageCode(order.getPackageTypeId().getPackageTypeId().intValue()));
				//Dennis has asked to not send dims in the shipping request, hardcoding for now
				if(order.getBusinessId()!=2 && order.getPackageTypeId().getPackageTypeId()!=ShiplinxConstants.PACKAGE_TYPE_ENVELOPE){
					pack.setDimensions(new DimensionsType());
					pack.getDimensions().setUnitOfMeasurement(new ShipUnitOfMeasurementType());
					pack.getDimensions().getUnitOfMeasurement().setCode(UPSAPI.UNIT_INCHES_STRING);
					pack.getDimensions().setHeight(p.getHeight().toString());
					pack.getDimensions().setWidth(p.getWidth().toString());
					pack.getDimensions().setLength(p.getLength().toString());
				}
				pack.setPackageWeight(new PackageWeightType());
				pack.getPackageWeight().setUnitOfMeasurement(new ShipUnitOfMeasurementType());
				pack.getPackageWeight().getUnitOfMeasurement().setCode(UPSAPI.UNIT_LBS_STRING);
				pack.getPackageWeight().setWeight(p.getWeight().toString());
//				pack.getReferenceNumber().set(0, ""); //Add ability to assign reference numbers for each package
				
				//COD
				pack.setPackageServiceOptions(new PackageServiceOptionsType());
				if(p.getCodAmount().floatValue() > 0){
					pack.getPackageServiceOptions().setCOD(new PSOCODType());
					pack.getPackageServiceOptions().getCOD().setCODAmount(new CurrencyMonetaryType());
					pack.getPackageServiceOptions().getCOD().getCODAmount().setMonetaryValue(p.getCodAmount().toString());
		
					if(customerCarrier.getCountry().equals(ShiplinxConstants.CANADA))
						pack.getPackageServiceOptions().getCOD().getCODAmount().setCurrencyCode(ShiplinxConstants.CURRENCY_CA_STRING);
					else
						pack.getPackageServiceOptions().getCOD().getCODAmount().setCurrencyCode(ShiplinxConstants.CURRENCY_US_STRING);
					pack.getPackageServiceOptions().getCOD().setCODFundsCode("8"); //hard-coding for cashier's check or money order only					
				}
				
				//Delivery Confirmation
				if(order.getSignatureRequired() > 1){
					pack.getPackageServiceOptions().setDeliveryConfirmation(new DeliveryConfirmationType());
					if(order.getSignatureRequired() == 2) //Delivery Confirmation
						pack.getPackageServiceOptions().getDeliveryConfirmation().setDCISType("1");
					if(order.getSignatureRequired() == 3) //Delivery Confirmation Signature
						pack.getPackageServiceOptions().getDeliveryConfirmation().setDCISType("2");
					if(order.getSignatureRequired() == 4) //Delivery Confirmation Adult
						pack.getPackageServiceOptions().getDeliveryConfirmation().setDCISType("3");
				}
				
				//Insurance
				if(p.getInsuranceAmount().doubleValue() > 0){
					pack.getPackageServiceOptions().setDeclaredValue(new PackageDeclaredValueType());
					if(customerCarrier.getCountry().equals(ShiplinxConstants.CANADA))
						pack.getPackageServiceOptions().getDeclaredValue().setCurrencyCode(ShiplinxConstants.CURRENCY_CA_STRING);
					else
						pack.getPackageServiceOptions().getDeclaredValue().setCurrencyCode(ShiplinxConstants.CURRENCY_US_STRING);
					pack.getPackageServiceOptions().getDeclaredValue().setMonetaryValue(p.getInsuranceAmount().toString());
					
				}
				shipment.getPackage().add(pack);
			
			}
			
			request.setShipment(shipment);
			
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.getMessage());
		}
	}

}
