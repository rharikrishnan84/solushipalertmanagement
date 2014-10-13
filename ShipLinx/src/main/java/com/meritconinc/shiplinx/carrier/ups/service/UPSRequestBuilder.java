package com.meritconinc.shiplinx.carrier.ups.service;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;

import com.cwsi.eshipper.carrier.ups.rate.RatingServiceSelectionRequestDocument;
import com.cwsi.eshipper.carrier.ups.rate.RatingServiceSelectionResponseDocument;
import com.cwsi.eshipper.carrier.ups.rate.AddressDocument.Address;
import com.cwsi.eshipper.carrier.ups.rate.DimensionsDocument.Dimensions;
import com.cwsi.eshipper.carrier.ups.rate.PackageServiceOptionsDocument.PackageServiceOptions;
import com.cwsi.eshipper.carrier.ups.rate.PackageWeightDocument.PackageWeight;
import com.cwsi.eshipper.carrier.ups.rate.PackagingTypeDocument.PackagingType;
import com.cwsi.eshipper.carrier.ups.rate.PaymentInformationDocument.PaymentInformation;
import com.cwsi.eshipper.carrier.ups.rate.PrepaidDocument.Prepaid;
import com.cwsi.eshipper.carrier.ups.rate.RatingServiceSelectionRequestDocument.RatingServiceSelectionRequest;
import com.cwsi.eshipper.carrier.ups.rate.ReferenceNumberDocument.ReferenceNumber;
import com.cwsi.eshipper.carrier.ups.rate.RequestDocument.Request;
import com.cwsi.eshipper.carrier.ups.rate.ServiceDocument.Service;
import com.cwsi.eshipper.carrier.ups.rate.ShipFromDocument.ShipFrom;
import com.cwsi.eshipper.carrier.ups.rate.ShipToDocument.ShipTo;
import com.cwsi.eshipper.carrier.ups.rate.ShipmentDocument.Shipment;
import com.cwsi.eshipper.carrier.ups.rate.ShipmentServiceOptionsDocument.ShipmentServiceOptions;
import com.cwsi.eshipper.carrier.ups.rate.ShipperDocument.Shipper;
import com.cwsi.eshipper.carrier.ups.rate.StructuredPhoneNumberDocument.StructuredPhoneNumber;
import com.cwsi.eshipper.carrier.ups.track.TrackRequestDocument;
import com.cwsi.eshipper.carrier.ups.track.TrackResponseDocument;
import com.cwsi.eshipper.carrier.ups.track.TrackRequestDocument.TrackRequest;
import com.cwsi.eshipper.carrier.ups.track.TrackResponseDocument.TrackResponse;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.carrier.utils.UPSException;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class UPSRequestBuilder {

	private Logger logger = Logger.getLogger(UPSRequestBuilder.class);
	
	private static final String UNIT_INCHES_STRING = "IN";
	private static final String UNIT_LBS_STRING = "LBS";
	private static final String UNIT_CMS_STRING = "CM";
	private static final String UNIT_KGS_STRING = "KGS";

	public RatingServiceSelectionResponseDocument createAndSendRateRequest(ShippingOrder shippingOrder, String serviceCode, CustomerCarrier customerCarrier) throws UPSException{
		logger.debug("-----createAndSendRateRequest-----");
		RatingServiceSelectionResponseDocument response_doc = null;
		
		try{  
			RatingServiceSelectionRequestDocument document = RatingServiceSelectionRequestDocument.Factory.newInstance();
			RatingServiceSelectionRequest rating_request = document.addNewRatingServiceSelectionRequest();		

			Request request = rating_request.addNewRequest(); 
			rating_request.addNewCustomerClassification().setCode("04"); //this to force UPS to send retail published rates as opposed to daily rates


			request.setRequestAction("rate");

			if(serviceCode == null)
				request.addNewRequestOption().setStringValue("Shop");
			else{
				request.addNewRequestOption().setStringValue("Rate");			
			}

			com.meritconinc.shiplinx.model.Address from = shippingOrder.getFromAddress();
			com.meritconinc.shiplinx.model.Address  to = shippingOrder.getToAddress();

			Shipment shipment = rating_request.addNewShipment();

			PaymentInformation payment_info = shipment.addNewPaymentInformation();
			Prepaid prepaid = payment_info.addNewPrepaid();

			Shipper shipper = shipment.addNewShipper();

			String accountNum = customerCarrier.getAccountNumber1();
			if(accountNum!=null && accountNum.length()>6){ //the EDI file is sending back the account # padded with zeros on the left. WE are storing the padded zeros in our customerCarrier record, so need to remove them before sending for rating/shipping
				int length = accountNum.length();				
				shipper.setShipperNumber(accountNum.substring(length-6, length));
			}
			else
				shipper.setShipperNumber(customerCarrier.getAccountNumber1());    

			prepaid.addNewBillShipper().setAccountNumber(shipper.getShipperNumber());		

			//return service, if country of origin is not US or Canada
//			if(!(from.getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) || from.getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA))){
//				shipment.addNewReturnService().setCode("9");
//			}
			com.meritconinc.shiplinx.model.Address shipper_address = new com.meritconinc.shiplinx.model.Address();
			if(customerCarrier.getCountry().equals(ShiplinxConstants.US)){
				shipper_address = shippingOrder.getBusiness().getUsAddress();
			}else{
				shipper_address = shippingOrder.getBusiness().getAddress();
			}
			

			logger.debug("-------------------shipper_address.getProvince()-----------------------------------");
			Address address = shipper.addNewAddress();
			address.setCity(shipper_address.getCity());
			address.setStateProvinceCode(shipper_address.getProvinceCode());
			if(address.getStateProvinceCode().equalsIgnoreCase("PQ"))
				address.setStateProvinceCode("QC");
			address.setPostalCode(shipper_address.getPostalCode());
			address.setCountryCode(shipper_address.getCountryCode());
			address.setAddressLine1(StringUtil.setWithMaxLength(shipper_address.getAddress1(), 35));
			address.setConsigneeName(StringUtil.setWithMaxLength(shipper_address.getAbbreviationName(), 35));
			shipper.addNewPhoneNumber();
			StructuredPhoneNumber shipper_phone = shipper.getPhoneNumber().addNewStructuredPhoneNumber();

			logger.debug("-------------------shipper------------------------------------"+shipper);

			ShipFrom ship_from = shipment.addNewShipFrom();
			ship_from.setCompanyName(StringUtil.setWithMaxLength(from.getAbbreviationName(), 35));
			ship_from.setAttentionName(StringUtil.setWithMaxLength(from.getContactName(), 35));
			ship_from.addNewPhoneNumber();
			StructuredPhoneNumber s_phone_from = ship_from.getPhoneNumber().addNewStructuredPhoneNumber();
			s_phone_from.setPhoneLineNumber(from.getPhoneNo());
			Address ship_from_address = ship_from.addNewAddress();
			ship_from_address.setAddressLine1(StringUtil.setWithMaxLength(from.getAddress1(), 35));
			ship_from_address.setAddressLine2(StringUtil.setWithMaxLength(from.getAddress2(), 35));
			ship_from_address.setCity(from.getCity());
			ship_from_address.setStateProvinceCode(from.getProvinceCode());
			if(ship_from_address.getStateProvinceCode().equalsIgnoreCase("PQ"))
				ship_from_address.setStateProvinceCode("QC");
			ship_from_address.setPostalCode(from.getPostalCode());
			ship_from_address.setCountryCode(from.getCountryCode());

			logger.debug("-------------------ship_from_address------------------------------------"+ship_from_address);


			ShipTo ship_to = shipment.addNewShipTo();
			ship_to.setCompanyName(StringUtil.setWithMaxLength(to.getAbbreviationName(), 35));
			ship_to.setAttentionName(StringUtil.setWithMaxLength(to.getContactName(), 35));
			ship_to.addNewPhoneNumber();
			StructuredPhoneNumber s_phone_to = ship_to.getPhoneNumber().addNewStructuredPhoneNumber();
			s_phone_to.setPhoneLineNumber(to.getPhoneNo());
			Address ship_to_address = ship_to.addNewAddress();
			ship_to_address.setAddressLine1(StringUtil.setWithMaxLength(to.getAddress1(), 35));
			ship_to_address.setAddressLine2(StringUtil.setWithMaxLength(to.getAddress2(), 35));
			ship_to_address.setCity(to.getCity());
			ship_to_address.setStateProvinceCode(to.getProvinceCode());
			if(ship_to_address.getStateProvinceCode().equalsIgnoreCase("PQ"))
				ship_to_address.setStateProvinceCode("QC");
			ship_to_address.setPostalCode(to.getPostalCode());
			ship_to_address.setCountryCode(to.getCountryCode());
			ship_to_address.setConsigneeName(StringUtil.setWithMaxLength(to.getAbbreviationName(), 35));


			if(to.isResidential())
				ship_to_address.addNewResidentialAddressIndicator();


			if(shippingOrder.getReferenceCode()!= null && shippingOrder.getReferenceCode().length()>0){
				ReferenceNumber ref_num = shipment.addNewReferenceNumber();
				ref_num.setCode("02");
				ref_num.setValue(shippingOrder.getReferenceCode());
			}

			if(serviceCode!=null){
				Service service = shipment.addNewService();
				service.setCode(serviceCode);
			}

			Date date = Calendar.getInstance().getTime();

			date = shippingOrder.getScheduledShipDate();
			
				
			shipment.setPickupDate(FormattingUtil.getFormattedDate(new Date(shippingOrder.getScheduledShipDate().getTime()), FormattingUtil.DATE_FORMAT_yyyyMMDD));

			int i=0;
			for(com.meritconinc.shiplinx.model.Package p: shippingOrder.getPackages()){	

				com.cwsi.eshipper.carrier.ups.rate.PackageDocument.Package pack = shipment.addNewPackage();
				PackagingType pack_type = pack.addNewPackagingType();
				//set package 
				pack_type.setCode(UPSAPI.getUPSPackageCode(shippingOrder.getPackageTypeId().getPackageTypeId().intValue()));
				if(shippingOrder.getPackageTypeId().getPackageTypeId()!=ShiplinxConstants.PACKAGE_TYPE_ENVELOPE){
					Dimensions dims = pack.addNewDimensions();
	
					/////////////
					if(!(shippingOrder.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) 
							|| shippingOrder.getFromAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US)))
						shippingOrder.setDimType(ShippingOrder.DIM_TYPE_METRIC);
					else
						shippingOrder.setDimType(ShippingOrder.DIM_TYPE_US);
					////////////
	
					if(shippingOrder.getDimType()==ShippingOrder.DIM_TYPE_US)
						dims.addNewUnitOfMeasurement().setCode(UNIT_INCHES_STRING);
					else
						dims.addNewUnitOfMeasurement().setCode(UNIT_CMS_STRING);
	
					dims.setHeight(String.valueOf(p.getHeight()));
					dims.setWidth(String.valueOf(p.getWidth()));
					dims.setLength(String.valueOf(p.getLength()));
	
					logger.debug("------(shippingOrder.getDimType()---"+shippingOrder.getDimType());
					PackageWeight p_weight = pack.addNewPackageWeight();
					if(shippingOrder.getDimType()==ShippingOrder.DIM_TYPE_US)
						p_weight.addNewUnitOfMeasurement().setCode(UNIT_LBS_STRING);	
					else
						p_weight.addNewUnitOfMeasurement().setCode(UNIT_KGS_STRING);
	
					p_weight.setWeight(String.valueOf(p.getWeight()));	
				}


				PackageServiceOptions options = pack.addNewPackageServiceOptions();
				if(p.getCodAmount().floatValue() > 0){
					options.addNewCOD().addNewCODAmount().setMonetaryValue(String.valueOf(p.getCodAmount()));
					options.getCOD().setCODCode("3");
					options.getCOD().setCODFundsCode("8");	
				} else if(shippingOrder.getSignatureRequired() != ShiplinxConstants.SIGNATURE_REQUIRED_NO) {
					if(shippingOrder.getSignatureRequired() == 2) {
						options.addNewDeliveryConfirmation().setDCISType("1");
					} else if(shippingOrder.getSignatureRequired() == 3) {
						options.addNewDeliveryConfirmation().setDCISType("2");
					} else if(shippingOrder.getSignatureRequired() == 4) {
						options.addNewDeliveryConfirmation().setDCISType("3");
					}
				}		

				if(i==0 && shippingOrder.getInsuranceValue()>0)
					options.addNewInsuredValue().setMonetaryValue(String.valueOf(shippingOrder.getInsuranceValue()));	

				i++;
			}		

			ShipmentServiceOptions options = shipment.addNewShipmentServiceOptions();

			if(shippingOrder.getSatDelivery()){
				options.addNewSaturdayDelivery();
			}

			rating_request.getShipment().addNewRateInformation().addNewNegotiatedRatesIndicator();

			//UPSConnection connection = new UPSConnection(franchise_to_bill);
			UPSConnection connection = new UPSConnection();
			connection.setCustomerCarrier(customerCarrier);

			connection.setUrl(UPSAPI.LIVE_URL_RATE);

			logger.debug("------connection.send----111----- ");
			String response = connection.send(document, UPSAPI.REQUEST_TYPE_RATE);

			try{
				response_doc = RatingServiceSelectionResponseDocument.Factory.parse(response);
				logger.debug("----response_doc-------- "+response_doc);
			}
			catch(XmlException e) {
				logger.error("Error parsing returned XML from UPS: " + e.getMessage());
				throw new UPSException("Error reading response from UPS Server: " + e.getMessage());            
			}

			UPSResponseHelper response_helper = new UPSResponseHelper();
			UPSException exception = response_helper.getRatingError(response_doc);

			logger.debug("exception.getErrorMessages().size(): " + exception.getErrorMessages().size());

			if(exception.getErrorMessages().size() > 0){
				throw exception;
			}

		}catch (UPSException e) {
			e.printStackTrace();
			logger.error(e);
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw new UPSException("Error in getting rates");
		}
		return response_doc;
	}

//	public ShipmentAcceptResponseDocument createAndSendShipRequest(ShippingOrder shippingOrder, String serviceCode)  throws UPSException{		
//
//		com.meritconinc.shiplinx.model.Address from = shippingOrder.getFromAddress();
//		com.meritconinc.shiplinx.model.Address to = shippingOrder.getToAddress();
//
//
//		com.cwsi.eshipper.carrier.ups.ship.ShipmentConfirmRequestDocument request_doc = ShipmentConfirmRequestDocument.Factory.newInstance();
//		ShipmentConfirmRequest shipping_request = request_doc.addNewShipmentConfirmRequest();
//
//		com.cwsi.eshipper.carrier.ups.ship.LabelSpecificationDocument.LabelSpecification label_spec = shipping_request.addNewLabelSpecification();
//		com.cwsi.eshipper.carrier.ups.ship.LabelImageFormatDocument.LabelImageFormat format = label_spec.addNewLabelImageFormat();
//		label_spec.addNewLabelPrintMethod().setCode("GIF");
//		format.setCode("GIF");	
//
//		com.cwsi.eshipper.carrier.ups.ship.RequestDocument.Request request = shipping_request.addNewRequest();			
//		request.setRequestAction("ShipConfirm");
//		request.addNewRequestOption().setStringValue("validate");
//
//		com.cwsi.eshipper.carrier.ups.ship.ShipmentType shipment = shipping_request.addNewShipment();
//
//		//description is required for intl shipments
//		if(!shippingOrder.getFromAddress().getCountryCode().equals(shippingOrder.getToAddress().getCountryCode())){
//			/*if(shippingOrder.getCustomsInvoice()!=null){
//				if(shippingOrder.getCustomsInvoice().getProducts()!=null && shippingOrder.getCustomsInvoice().getProducts().size()>0)
//				shipment.setDescription(((CustomsInvoiceProduct)shippingOrder.getCustomsInvoice().getProducts().get(0)).getDescription());
//			}
//			else*/
//			shipment.setDescription("Various");
//		}
//
//		com.cwsi.eshipper.carrier.ups.ship.ShipperDocument.Shipper shipper = shipment.addNewShipper();
//
//		shipper.setName(from.getAbbreviationName());
//
//		com.cwsi.eshipper.carrier.ups.ship.PaymentInformationDocument.PaymentInformation payment_info = shipment.addNewPaymentInformation();
//		com.cwsi.eshipper.carrier.ups.ship.PrepaidDocument.Prepaid prepaid = payment_info.addNewPrepaid();
//
//		String custUpsAcctNum = "";
//		if(from.getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA)){
//			prepaid.addNewBillShipper().setAccountNumber(customerCarrier.getAccountNumber1());		//"5F1399";
//			shipper.setShipperNumber(customerCarrier.getAccountNumber1());    	//"5F132X";
//			custUpsAcctNum = customerCarrier.getAccountNumber1();//"249570559";
//		}
//		else if(from.getCountryCode().equalsIgnoreCase(ShiplinxConstants.US)){
//			prepaid.addNewBillShipper().setAccountNumber(customerCarrier.getAccountNumber1());   	//"4170E0";
//			shipper.setShipperNumber(customerCarrier.getAccountNumber1());   	//    "4170AR";
//			custUpsAcctNum = customerCarrier.getAccountNumber1();//"249570559";
//		}
//		else{
//			logger.info("Could not determine which UPS account to use (US/CAD)?, not returning any rates");
//			return null;
//		}
//		
//		logger.debug("-----custUpsAcctNum-----"+custUpsAcctNum);
//		//return service, if country of origin is not US or Canada
//		if(!(from.getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) || from.getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA))){
//			shipment.addNewReturnService().setCode("9");
//		}
//
//
//		com.cwsi.eshipper.carrier.ups.ship.AddressDocument.Address address = shipper.addNewAddress();
//		logger.debug("-------------------shipper_address.getProvince()-----------------------------------");
//
//		com.meritconinc.shiplinx.model.Address shipper_address = shippingOrder.getBusiness().getAddress();
//
//		address.setCity(shipper_address.getCity());
//		address.setStateProvinceCode(shipper_address.getProvinceCode());
//		if(address.getStateProvinceCode().equalsIgnoreCase("PQ"))
//			address.setStateProvinceCode("QC");
//		address.setPostalCode(shipper_address.getPostalCode());
//		address.setCountryCode(shipper_address.getCountryCode());
//		address.setAddressLine1(shipper_address.getAddress1());
//		address.setConsigneeName(shipper_address.getAbbreviationName());
//		shipper.addNewPhoneNumber();
//
//
//		///////////////
//		//TODO: configure the US address in database
//		/*if(from.getCountryCode().equalsIgnoreCase(ShiplinxConstants.US)){
//			address.setCountryCode(ShiplinxConstants.US);
//			address.setPostalCode("90210");
//			address.setCity("Beverly Hills");
//			address.setStateProvinceCode("CA");
//		}*/
//		////////////
//
//		//address.setCity(custInfo.getCity());
//		//address.setStateProvinceCode(custInfo.getProvince());
//		//address.setPostalCode(custInfo.getPostalCode());
//		//address.setCountryCode(custInfo.getCountry());
//
//
//		logger.debug("-------------------shipper------------------------------------"+shipper);
//
//		com.cwsi.eshipper.carrier.ups.ship.ShipFromDocument.ShipFrom ship_from = shipment.addNewShipFrom();
//		ship_from.setCompanyName(from.getAbbreviationName());
//		ship_from.setAttentionName(from.getContactName());
//		ship_from.addNewPhoneNumber();
//		com.cwsi.eshipper.carrier.ups.ship.StructuredPhoneNumberDocument.StructuredPhoneNumber s_phone_from = ship_from.getPhoneNumber().addNewStructuredPhoneNumber();
//		s_phone_from.setPhoneLineNumber(from.getPhoneNo());
//		com.cwsi.eshipper.carrier.ups.ship.AddressDocument.Address ship_from_address = ship_from.addNewAddress();
//		ship_from_address.setAddressLine1(from.getAddress1());
//		ship_from_address.setAddressLine2(from.getAddress2());
//		ship_from_address.setCity(from.getCity());
//		ship_from_address.setStateProvinceCode(from.getProvinceCode());
//		if(ship_from_address.getStateProvinceCode().equalsIgnoreCase("PQ"))
//			ship_from_address.setStateProvinceCode("QC");
//		ship_from_address.setPostalCode(from.getPostalCode());
//		ship_from_address.setCountryCode(from.getCountryCode());
//
//		logger.debug("-------------------ship_from_address------------------------------------"+ship_from_address);
//
//
//		com.cwsi.eshipper.carrier.ups.ship.ShipToDocument.ShipTo ship_to = shipment.addNewShipTo();
//		ship_to.setCompanyName(to.getAbbreviationName());
//		ship_to.setAttentionName(to.getContactName());
//		ship_to.addNewPhoneNumber();
//		com.cwsi.eshipper.carrier.ups.ship.StructuredPhoneNumberDocument.StructuredPhoneNumber s_phone_to = ship_to.getPhoneNumber().addNewStructuredPhoneNumber();
//		s_phone_to.setPhoneLineNumber(to.getPhoneNo());
//		com.cwsi.eshipper.carrier.ups.ship.AddressDocument.Address ship_to_address = ship_to.addNewAddress();
//		ship_to_address.setAddressLine1(to.getAddress1());
//		ship_to_address.setAddressLine2(to.getAddress2());
//		ship_to_address.setCity(to.getCity());
//		ship_to_address.setStateProvinceCode(to.getProvinceCode());
//		if(ship_to_address.getStateProvinceCode().equalsIgnoreCase("PQ"))
//			ship_to_address.setStateProvinceCode("QC");
//		ship_to_address.setPostalCode(to.getPostalCode());
//		ship_to_address.setCountryCode(to.getCountryCode());
//
//		logger.debug("-------------------ship_to_address------------------------------------"+ship_to_address);		
//
//
//
//
//		if(to.isResidential())
//			ship_to_address.addNewResidentialAddressIndicator();
//
//		if(shippingOrder.getReferenceCode()!= null && shippingOrder.getReferenceCode().length()>0){
//			if(!(from.getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) && to.getCountryCode().equalsIgnoreCase(ShiplinxConstants.US))){
//				com.cwsi.eshipper.carrier.ups.ship.ReferenceNumberType ref_num = shipment.addNewReferenceNumber();
//				ref_num.setValue(shippingOrder.getReferenceCode());
//			}
//		}
//
//		com.cwsi.eshipper.carrier.ups.ship.CodeType service = shipment.addNewService();
//
//		if (shippingOrder.getToAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA)){
//			service.setCode(getMappedServiceCode(serviceCode));
//		}else{
//			service.setCode(serviceCode);
//		}
//
//		logger.debug("--------------shippingOrder.getScheduledShipDate()------------------"+shippingOrder.getScheduledShipDate());
//		
//		
//		shipment.setPickupDate(FormattingUtil.getFormattedDate(new Date(shippingOrder.getScheduledShipDate().getTime()), FormattingUtil.DATE_FORMAT_yyyyMMDD));
//
//		logger.debug("-------------Package Info-------------------------------");
//		int i=0;
//		for(com.meritconinc.shiplinx.model.Package p: shippingOrder.getPackages()){		
//			com.cwsi.eshipper.carrier.ups.ship.PackageDocument.Package pack = shipment.addNewPackage();
//			com.cwsi.eshipper.carrier.ups.ship.CodeType pack_type = pack.addNewPackagingType();
//			pack_type.setCode(UPSAPI.getUPSPackageCode(shippingOrder.getPackageTypeId().getPackageTypeId().intValue()));
//			/*if(shippingOrder.getReturnOrderId() != 0) {
//				pack.setDescription("Return Shipment");
//			}*/
//
//			//UPS does not accept dims and weight info for env
//			if(shippingOrder.getPackageTypeId().getPackageTypeId()!=ShiplinxConstants.PACKAGE_TYPE_ENVELOPE){
//				com.cwsi.eshipper.carrier.ups.ship.DimensionsDocument.Dimensions dims = pack.addNewDimensions();
//				if(shippingOrder.getDimType()==ShippingOrder.DIM_TYPE_US)
//					dims.addNewUnitOfMeasurement().setCode(UNIT_INCHES_STRING);
//				else
//					dims.addNewUnitOfMeasurement().setCode(UNIT_CMS_STRING);
//				dims.setHeight(String.valueOf(p.getHeight()));
//				dims.setWidth(String.valueOf(p.getWidth()));
//				dims.setLength(String.valueOf(p.getLength()));
//
//				com.cwsi.eshipper.carrier.ups.ship.WeightType p_weight = pack.addNewPackageWeight();
//				if(shippingOrder.getDimType()==ShippingOrder.DIM_TYPE_US)
//					p_weight.addNewUnitOfMeasurement().setCode(UNIT_LBS_STRING);	
//				else
//					p_weight.addNewUnitOfMeasurement().setCode(UNIT_KGS_STRING);
//				p_weight.setWeight(String.valueOf(p.getWeight()));
//			} 
//			if(shippingOrder.getReferenceCode()!= null && shippingOrder.getReferenceCode().length()>0){
//				if(from.getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) && to.getCountryCode().equalsIgnoreCase(ShiplinxConstants.US)){
//					com.cwsi.eshipper.carrier.ups.ship.ReferenceNumberType ref_num = pack.addNewReferenceNumber();
//					ref_num.setValue(shippingOrder.getReferenceCode());
//				}
//			}
//
//			logger.debug("-------------Package info------PackageServiceOptionsDocument-----------------------");
//
//			com.cwsi.eshipper.carrier.ups.ship.PackageServiceOptionsDocument.PackageServiceOptions options = pack.addNewPackageServiceOptions();
//			if(p.getCodAmount().intValue() > 0){
//				options.addNewCOD().addNewCODAmount().setMonetaryValue(String.valueOf(p.getCodAmount().intValue()));
//				options.getCOD().setCODCode("3");
//				options.getCOD().setCODFundsCode("8");	
//			} else if(shippingOrder.getFromAddress().getCountryCode().equals(ShiplinxConstants.US) && shippingOrder.getSignatureRequired() != 1) {
//				if(shippingOrder.getSignatureRequired() == 2) {
//					options.addNewDeliveryConfirmation().setDCISType("1");
//				} else if(shippingOrder.getSignatureRequired() == 3) {
//					options.addNewDeliveryConfirmation().setDCISType("2");
//				} else if(shippingOrder.getSignatureRequired() == 4) {
//					options.addNewDeliveryConfirmation().setDCISType("3");
//				}
//			}		
//
//			logger.debug("------------getInsuranceValue------------------");
//
//			if(i==0 && shippingOrder.getInsuranceValue()>0)
//				options.addNewInsuredValue().setMonetaryValue(String.valueOf(shippingOrder.getInsuranceValue()));	
//
//			i++;
//		}		
//
//		logger.debug("------------addNewInvoiceLineTotal------------------");
//
//		if(from.getCountryCode().equalsIgnoreCase(ShiplinxConstants.US) && (shippingOrder.getPackageTypeId().getPackageTypeId()!=ShiplinxConstants.PACKAGE_TYPE_ENVELOPE)){
//			if(to.getCountryCode().equalsIgnoreCase(ShiplinxConstants.CANADA) || to.getCountryCode().equalsIgnoreCase("PR")){ 
//				shipment.addNewInvoiceLineTotal();
//				shipment.getInvoiceLineTotal().setMonetaryValue("1");
//			}
//		}
//
//
//		com.cwsi.eshipper.carrier.ups.ship.ShipmentServiceOptionsDocument.ShipmentServiceOptions options = shipment.addNewShipmentServiceOptions();
//
//		if(shippingOrder.getSatDelivery()){
//			options.addNewSaturdayDelivery();
//		}
//
//		/*if(shippingOrder.getSchedPickup()!=null){
//			PickupDetails pickup_details = options.addNewOnCallAir().addNewPickupDetails();
//			String pickup_date = FormattingUtil.getFormattedDate(shippingOrder.getSchedPickup().getPickupDate(), FormattingUtil.DATE_FORMAT_yyyyMMDD);
//			String pickup_time = shippingOrder.getSchedPickup().getPickupTimeHour() + shippingOrder.getSchedPickup().getPickupTimeMinute();
//			String latest_pickup_time = shippingOrder.getSchedPickup().getCloseTimeHour() + shippingOrder.getSchedPickup().getCloseTimeMinute();
//			pickup_details.setPickupDate(pickup_date);
//			pickup_details.setEarliestTimeReady(pickup_time);
//			pickup_details.setLatestTimeReady(latest_pickup_time);
//			pickup_details.addNewContactInfo().setName(o.getSchedPickup().getContactName());
//			pickup_details.getContactInfo().addNewPhoneNumber().addNewStructuredPhoneNumber().setPhoneLineNumber(shippingOrder.getSchedPickup().getContactPhone());
//		}*/
//
//		shipment.addNewRateInformation().addNewNegotiatedRatesIndicator();		
//
//		logger.debug("--------connection-------------");
//
//		UPSConnection connection = new UPSConnection();
//		connection.setCustomerCarrier(customerCarrier);
////		connection.setUrl(UPSAPI.LIVE_URL_SHIP_CONFIRM);
////		else
//			connection.setUrl(UPSAPI.TEST_URL_SHIP_CONFIRM);
//
//		String response = connection.send(request_doc, UPSAPI.REQUEST_TYPE_SHIP);
//
//		ShipmentConfirmResponseDocument response_doc = null;
//
//		try{
//			response_doc = ShipmentConfirmResponseDocument.Factory.parse(response);
//		}
//		catch(XmlException e) {
//			logger.error("Error parsing returned XML from UPS: " + e.getMessage());
//			throw new UPSException("Error reading response from UPS Server: " + e.getMessage());            
//		}
//
//		UPSResponseHelper response_helper = new UPSResponseHelper();
//		UPSException exception = response_helper.getShipmentConfirmError(response_doc);
//
//		if(exception.getErrorMessages().size() > 0){
//			logger.debug("------exception.getErrorMessages()----------"+exception.getErrorMessages());
//
//			throw exception;
//		}
//
//		//if no errors, try and get the shipmentAccept response based on the shipmentDigest returned
//		String shipment_digest = response_doc.getShipmentConfirmResponse().getShipmentDigest();
//		if(shipment_digest == null || shipment_digest.length()==0)
//			throw new UPSException("Shipping Digest returned was empty!");
//
//		String shipment_id = response_doc.getShipmentConfirmResponse().getShipmentIdentificationNumber();
//		shippingOrder.setManifestNumber(shipment_id);
//		shippingOrder.setMasterTrackingNum(shipment_id);
//
//		ShipmentAcceptRequestDocument request_accept_doc = ShipmentAcceptRequestDocument.Factory.newInstance();
//		request_accept_doc.addNewShipmentAcceptRequest().addNewRequest().setRequestAction("ShipAccept");
//		request_accept_doc.getShipmentAcceptRequest().setShipmentDigest(shipment_digest);
//
//		connection = new UPSConnection();
//		connection.setCustomerCarrier(customerCarrier);
//		logger.debug("------TEST_URL_SHIP_CONFIRM--------");
//
////		if(customerCarrier.isLive())
////			connection.setUrl(UPSAPI.LIVE_URL_SHIP_ACCEPT);
////		else
//			connection.setUrl(UPSAPI.TEST_URL_SHIP_ACCEPT);
//
//		response = connection.send(request_accept_doc, UPSAPI.REQUEST_TYPE_SHIP);
//		ShipmentAcceptResponseDocument response_accept_doc = null;
//
//		try{
//			response_accept_doc = ShipmentAcceptResponseDocument.Factory.parse(response);
//		}
//		catch(XmlException e) {
//			logger.error("Error parsing returned XML from UPS: " + e.getMessage());
//			throw new UPSException("Error reading response from UPS Server: " + e.getMessage());            
//		}
//		exception = response_helper.getShipmentAcceptError(response_accept_doc);	
//
//		if(exception.getErrorMessages().size() > 0){
//			throw exception;
//		}	
//
//		return response_accept_doc;
//	}


	public String getMappedServiceCode(String serviceCode){
		if (serviceCode.equals("13")){
			serviceCode = "65";
		}else if(serviceCode.equals("02")){
			serviceCode = "08";
		}

		return serviceCode;
	}

	public TrackResponse createAndSendTrackRequest(ShippingOrder order, CustomerCarrier customerCarrier) {	

		TrackResponseDocument response_doc = null;

		TrackRequestDocument request_doc = TrackRequestDocument.Factory.newInstance();
		TrackRequest request = request_doc.addNewTrackRequest();

		request.addNewRequest().setRequestAction("Track");
		request.setTrackingNumber(order.getMasterTrackingNum());

		UPSConnection connection = new UPSConnection();
		connection.setCustomerCarrier(customerCarrier);
//		if(order.getCustomerCarrier().isLive())
//			connection.setUrl(UPSAPI.LIVE_URL_TRACK);
//		else
			connection.setUrl(UPSAPI.TEST_URL_TRACK);
		String response = connection.send(request, UPSAPI.REQUEST_TYPE_TRACK);

		try{
			response_doc = TrackResponseDocument.Factory.parse(response);
		}
		catch(XmlException e) {
			logger.error("Error parsing returned XML from UPS: " + e.getMessage());
			throw new UPSException("Error reading response from UPS Server: " + e.getMessage());            
		}

		UPSResponseHelper response_helper = new UPSResponseHelper();
		UPSException exception = response_helper.getTrackError(response_doc);

		if(exception.getErrorMessages().size() > 0){
			throw exception;
		}
		return response_doc.getTrackResponse();
	}

//	public boolean createAndSendVoidRequest(ShippingOrder order) {			
//		VoidShipmentResponseDocument response_doc = null;
//		try{
//			VoidShipmentRequestDocument request_doc = VoidShipmentRequestDocument.Factory.newInstance();
//			VoidShipmentRequest request = request_doc.addNewVoidShipmentRequest();
//			request.addNewRequest().setRequestAction("Void");
//			logger.debug("-----order.getMasterTrackingNum()---"+order.getMasterTrackingNum());
//			request.setShipmentIdentificationNumber(order.getMasterTrackingNum());
//			UPSConnection connection = new UPSConnection();
//			connection.setCustomerCarrier(order.getCustomerCarrier());
//			if(order.isLive())
//				connection.setUrl(UPSAPI.LIVE_URL_VOID);
//			else
//				connection.setUrl(UPSAPI.TEST_URL_VOID);
//			String response1 = connection.send(request, UPSAPI.REQUEST_TYPE_VOID);
//			try{
//				response_doc = VoidShipmentResponseDocument.Factory.parse(response1);
//				logger.debug("-----response_doc---"+response_doc);
//			}
//			catch(XmlException e) {
//				logger.error("Error parsing returned XML from UPS: " + e.getMessage());
//				throw new UPSException("Error reading response from UPS Server: " + e.getMessage());            
//			}
//
////			UPSResponseHelper response_helper = new UPSResponseHelper();
////			UPSException exception = response_helper.getVoidError(response_doc);
//
////			if(exception.getErrorMessages().size() > 0){
////			throw exception;
////			}
//
//			try{
//				if(response_doc.getVoidShipmentResponse().getStatus().getStatusType().getCode().equalsIgnoreCase("1")){
//					return true;
//				}
//
//			}
//			catch(Exception e){
//				e.printStackTrace();
//				return false;
//			}
//
//		}catch(Exception e){
//			e.printStackTrace();
//			return false;
//		}
//		return false;
//	}
	
//	   public Map<String, String> createAndSendTimeInTransitRequest(ShippingOrder o, String serviceCode) {
//	    	
//	    	TimeInTransitResponseDocument response_doc = null;
//	    	
//	    	
//	    	TimeInTransitRequestDocument request_doc = TimeInTransitRequestDocument.Factory.newInstance();
//	    	TimeInTransitRequest transit_request = request_doc.addNewTimeInTransitRequest();
//	    	
//	    	com.cwsi.eshipper.carrier.ups.transit.RequestDocument.Request transit = transit_request.addNewRequest();
//	    	transit.setRequestAction("TimeInTransit");
//	    	
//	    	TransactionReference trans_ref = transit.addNewTransactionReference();
//	    	trans_ref.addNewCustomerContext();
//	    	trans_ref.setXpciVersion("1.0002");
//	    	
//	    	com.meritconinc.shiplinx.model.Address from = o.getFromAddress();
//	        com.meritconinc.shiplinx.model.Address to  = o.getToAddress();
//	        
//	    	TransitFrom transit_from = transit_request.addNewTransitFrom();
//	    	AddressArtifacts address_from_format = transit_from.addNewAddressArtifactFormat();
//	    	     
//		    address_from_format.setPoliticalDivision2(from.getCity());
//		    address_from_format.setPoliticalDivision1(from.getProvinceCode());
//		    address_from_format.setCountryCode(from.getCountryCode());
//	        address_from_format.setPostcodePrimaryLow(from.getPostalCode());
//	        
//	        TransitTo transit_to = transit_request.addNewTransitTo();
//	        AddressArtifacts address_to_format = transit_to.addNewAddressArtifactFormat();
//	        
//	        address_to_format.setPoliticalDivision2(to.getCity());
//	        address_to_format.setPoliticalDivision1(to.getProvinceCode());
//	        address_to_format.setCountryCode(to.getCountryCode());
//	        address_to_format.setPostcodePrimaryLow(to.getPostalCode());
//	        
//	        transit_request.setPickupDate(FormattingUtil.getFormattedDate(o.getScheduledShipDate(), FormattingUtil.DATE_FORMAT_yyyyMMDD));
//	        
//	        ShipmentWeight shipment_weight = transit_request.addNewShipmentWeight();
//	        if(o.getDimType()==ShippingOrder.DIM_TYPE_US)
//	        	shipment_weight.addNewUnitOfMeasurement().setCode(UNIT_LBS_STRING);
//	        else
//	        	shipment_weight.addNewUnitOfMeasurement().setCode(UNIT_KGS_STRING);
//	        UnitOfMeasurement unit = shipment_weight.getUnitOfMeasurement();
//	        unit.setDescription("");
//	        shipment_weight.setWeight(String.valueOf(o.getTotalWeight()));
//	        
//	        transit_request.setTotalPackagesInShipment(String.valueOf(o.getQuantity()));
//	        
//	        if(!from.getCountryCode().equals(ShiplinxConstants.US) || !to.getCountryCode().equals(ShiplinxConstants.US)){
//	        	InvoiceLineTotal invoiceLineTotal = transit_request.addNewInvoiceLineTotal();
//	        	if(from.getCountryCode().equals(ShiplinxConstants.CANADA)) {
//	        		invoiceLineTotal.setCurrencyCode(ShiplinxConstants.CURRENCY_CA_STRING);
//	        	} else {
//	        		invoiceLineTotal.setCurrencyCode(ShiplinxConstants.CURRENCY_US_STRING);
//	        	}	
//	        	invoiceLineTotal.setMonetaryValue("9999.99");
//	        	
//	        }
//	        	
//			//UPSConnection connection = new UPSConnection(franchise_to_bill);
//			UPSConnection connection = new UPSConnection();
//			connection.setCustomerCarrier(o.getCustomerCarrier());
//
//	        if(ShiplinxConstants.isTestMode())
//	        	connection.setUrl(UPSAPI.TEST_URL_TIMEINTRANSIT);
//	    	else
//	    		connection.setUrl(UPSAPI.LIVE_URL_TIMEINTRANSIT);
//	    	
//	    	String response = connection.send(request_doc, UPSAPI.REQUEST_TYPE_TIMEINTRANSIT);
//	    	    	
//	    	try{
//	    		response_doc = TimeInTransitResponseDocument.Factory.parse(response);
//	    	}
//	    	catch(XmlException e){
//	    		logger.error("Error parsing returned Transit Time Response XML from UPS: " + e.getMessage());
//	    		return null;
//	    	}
//	    
//	    	UPSResponseHelper response_helper = new UPSResponseHelper();
//	    	UPSException exception = response_helper.getTimeInTransitError(response_doc);
//	    	
//	    	if(exception.getErrorMessages().size() > 0){
//	    		return null;
//	    	}
//	    	
//	    	return response_helper.extractCodeAndTransitDays(response_doc);
//	         
//	    }

}
