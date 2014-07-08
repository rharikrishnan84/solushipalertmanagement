package com.meritconinc.shiplinx.ccprocessing.processor;

import https.api_authorize_net.soap.v1.ArrayOfCustomerPaymentProfileType;
import https.api_authorize_net.soap.v1.CreateCustomerProfileResponseType;
import https.api_authorize_net.soap.v1.CreateCustomerProfileTransaction;
import https.api_authorize_net.soap.v1.CreateCustomerProfileTransactionResponseType;
import https.api_authorize_net.soap.v1.CreditCardType;
import https.api_authorize_net.soap.v1.CustomerAddressType;
import https.api_authorize_net.soap.v1.CustomerPaymentProfileExType;
import https.api_authorize_net.soap.v1.CustomerPaymentProfileType;
import https.api_authorize_net.soap.v1.CustomerProfileType;
import https.api_authorize_net.soap.v1.CustomerTypeEnum;
import https.api_authorize_net.soap.v1.MerchantAuthenticationType;
import https.api_authorize_net.soap.v1.MessageTypeEnum;
import https.api_authorize_net.soap.v1.ObjectFactory;
import https.api_authorize_net.soap.v1.PaymentType;
import https.api_authorize_net.soap.v1.ProfileTransAuthCaptureType;
import https.api_authorize_net.soap.v1.ProfileTransAuthOnlyType;
import https.api_authorize_net.soap.v1.ProfileTransPriorAuthCaptureType;
import https.api_authorize_net.soap.v1.ProfileTransRefundType;
import https.api_authorize_net.soap.v1.ProfileTransVoidType;
import https.api_authorize_net.soap.v1.ProfileTransactionType;
import https.api_authorize_net.soap.v1.Service;
import https.api_authorize_net.soap.v1.ServiceSoap;
import https.api_authorize_net.soap.v1.UpdateCustomerPaymentProfile;
import https.api_authorize_net.soap.v1.UpdateCustomerPaymentProfileResponseType;
import https.api_authorize_net.soap.v1.ValidationModeEnum;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.ccprocessing.CCRequestObject;
import com.meritconinc.shiplinx.ccprocessing.CCResponseObject;
import com.meritconinc.shiplinx.ccprocessing.ICreditCardProcessor;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.MerchantAccount;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public final class AuthNetCIMProcessor implements ICreditCardProcessor
{
	private final static Logger logger = Logger.getLogger(AuthNetCIMProcessor.class);

	private MerchantAccount ma = null;

	private String responseAuthCode_ = null;
	private int responseCode_ = 0;
	private int responseReasonCode_ = 0;
	private String responseReasonText_;
	private String responseTransactionId_;
	private String responseCardCode; 

	private static final String DELIM_DATA_STRING = "x_delim_data";
	private static final String DELIM_CHAR_STRING = "x_delim_char";
	private static final String DELIM_CHAR_VALUE = "|";
	private static final String RELAY_RESPONSE_STRING = "x_relay_response";
	private static final String DESCRIPTION_STRING = "x_description";
	private static final String DUPLICATE_WINDOW_STRING = "x_duplicate_window";

	private static final int APPROVED_RESPONSE_CODE = 1;

	//CIM
	protected static String apiLoginID = "9kc57YF4P"; // System.getProperty("API_LOGIN_ID");
	protected static String transactionKey = "2c7557K8z6VSMEzT"; // System.getProperty("TRANSACTION_KEY");
	protected static String cp_apiLoginID = System.getProperty("CP_API_LOGIN_ID");
	protected static String cp_transactionKey = System.getProperty("CP_TRANSACTION_KEY");
	protected static String merchantMD5Key = System.getProperty("MD5_HASH_KEY");
    private static final QName SERVICE_NAME = new QName("https://api.authorize.net/soap/v1/", "Service");
    private static final String WSDL_LOCATION_TEST ="https://apitest.authorize.net/soap/v1/Service.asmx?WSDL";
    private static final String WSDL_LOCATION ="https://api.authorize.net/soap/v1/Service.asmx?WSDL";
	private String destinationWSURLString_;

	
	public AuthNetCIMProcessor(MerchantAccount ma)
	{
		this.ma = ma;

		if (ShiplinxConstants.isTestMode())
		{
			destinationWSURLString_ = WSDL_LOCATION_TEST;
			ma.setProperty1(ma.getTestProperty1());
			ma.setProperty2(ma.getTestProperty2());
		}
		else
		{
			destinationWSURLString_ = WSDL_LOCATION;
		}
		
	}

	public boolean verifyCard(CCRequestObject request, long pin){
		return true; //For AuthNetCIM processor, the validation of the card is done as part of the addCard transactoin
	}
	
	public boolean addCard(CreditCard cc, Customer customer){

		try {
			URL wsdlURL = new URL(destinationWSURLString_);
		      
	        Service ss = new Service(wsdlURL, SERVICE_NAME);
	        ServiceSoap port = ss.getServiceSoap();  
	        
			ObjectFactory objFactory = new ObjectFactory();
	
			MerchantAuthenticationType merchantAuthentication = objFactory.createMerchantAuthenticationType();
	        merchantAuthentication.setName(ma.getProperty1());
	        merchantAuthentication.setTransactionKey(ma.getProperty2());
	        
	//		// Create a new credit card
	        CreditCardType creditCard = objFactory.createCreditCardType();
			creditCard.setCardNumber(cc.getCcNumber()); //creditCardNumber);
			creditCard.setExpirationDate(getXmlDate(cc.getCcExpiryYear() + "-" + cc.getCcExpiryMonth()));
			creditCard.setCardCode(cc.getCvd());
			
	//		// Create a billing info
			CustomerAddressType billingInfo = objFactory.createCustomerAddressType();
			billingInfo.setFirstName(cc.getBillingAddress().getContactName());
			billingInfo.setAddress(cc.getBillingAddress().getAddress1());
			billingInfo.setCity(cc.getBillingAddress().getCity());
			billingInfo.setState(cc.getBillingAddress().getProvinceCode());
			billingInfo.setCountry(cc.getBillingAddress().getCountryCode());
			billingInfo.setZip(cc.getBillingAddress().getPostalCode());
			billingInfo.setPhoneNumber(cc.getBillingAddress().getPhoneNo());
	
			PaymentType payment = objFactory.createPaymentType();
			payment.setCreditCard(creditCard);
			
			CustomerPaymentProfileType customerPaymentProfile = objFactory.createCustomerPaymentProfileType();
			customerPaymentProfile.setBillTo(billingInfo);
			customerPaymentProfile.setCustomerType(CustomerTypeEnum.INDIVIDUAL);
			customerPaymentProfile.setPayment(payment);
			
			ArrayOfCustomerPaymentProfileType arrayCustomerPaymentProfile = objFactory.createArrayOfCustomerPaymentProfileType();
			arrayCustomerPaymentProfile.getCustomerPaymentProfileType().add(customerPaymentProfile);
			
			CustomerProfileType customerProfile = objFactory.createCustomerProfileType();
			customerProfile.setMerchantCustomerId(customer.getAccountNumber());
			customerProfile.setEmail(customer.getAddress().getEmailAddress());
			customerProfile.setPaymentProfiles(arrayCustomerPaymentProfile);
	
	       ValidationModeEnum validationMode = ValidationModeEnum.LIVE_MODE;
		       		
			CreateCustomerProfileResponseType response = port.createCustomerProfile(merchantAuthentication, customerProfile, validationMode);
	        logger.debug("createCustomerProfile.result=" + response.toString());
	        if (response != null) {
	        	if (response.getResultCode() == MessageTypeEnum.ERROR && response.getMessages().getMessagesTypeMessage().size() > 0) {
	        		logger.debug("Error: [Code:" + response.getMessages().getMessagesTypeMessage().get(0).getCode() + 
	        							"][Text:" + response.getMessages().getMessagesTypeMessage().get(0).getText() + "]");
	        		return false;
	        	} 
	        }
	
	       	logger.debug("Successfull: [CustomerProfileID:" + response.getCustomerProfileId() + "]");
	       	cc.setProcProfileID(String.valueOf(response.getCustomerProfileId())); //this is the customer profile id to be passed for all future transactions
	       	cc.setProcProfileID2(String.valueOf(response.getCustomerPaymentProfileIdList().getLong().get(0)));
			
			return true;
		}
		catch(Exception e){
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	public boolean updateCard(CreditCard cc, Customer customer){

		try {
			URL wsdlURL = new URL(destinationWSURLString_);
		      
	        Service ss = new Service(wsdlURL, SERVICE_NAME);
	        ServiceSoap port = ss.getServiceSoap();  
	        
			ObjectFactory objFactory = new ObjectFactory();
	
			MerchantAuthenticationType merchantAuthentication = objFactory.createMerchantAuthenticationType();
	        merchantAuthentication.setName(ma.getProperty1());
	        merchantAuthentication.setTransactionKey(ma.getProperty2());
	        
	//		// Create a new credit card
	        CreditCardType creditCard = objFactory.createCreditCardType();
			creditCard.setCardNumber(cc.getCcNumber()); //creditCardNumber);
			creditCard.setExpirationDate(getXmlDate(cc.getCcExpiryYear() + "-" + cc.getCcExpiryMonth()));
			creditCard.setCardCode(cc.getCvd());
			
	//		// Create a billing info
			CustomerAddressType billingInfo = objFactory.createCustomerAddressType();
			billingInfo.setFirstName(cc.getBillingAddress().getContactName());
			billingInfo.setAddress(cc.getBillingAddress().getAddress1());
			billingInfo.setCity(cc.getBillingAddress().getCity());
			billingInfo.setState(cc.getBillingAddress().getProvinceCode());
			billingInfo.setCountry(cc.getBillingAddress().getCountryCode());
			billingInfo.setZip(cc.getBillingAddress().getPostalCode());
			billingInfo.setPhoneNumber(cc.getBillingAddress().getPhoneNo());
	
			PaymentType payment = objFactory.createPaymentType();
			payment.setCreditCard(creditCard);
			
			UpdateCustomerPaymentProfile customerPaymentProfile = objFactory.createUpdateCustomerPaymentProfile();
			customerPaymentProfile.setCustomerProfileId(Long.valueOf(cc.getProcProfileID()));
			customerPaymentProfile.setMerchantAuthentication(merchantAuthentication);
			
			CustomerPaymentProfileExType customerPaymentProfileExType = objFactory.createCustomerPaymentProfileExType();
			customerPaymentProfileExType.setBillTo(billingInfo);
			customerPaymentProfileExType.setCustomerPaymentProfileId(Long.valueOf(cc.getProcProfileID2()));
			payment.setCreditCard(creditCard);
			customerPaymentProfileExType.setPayment(payment);
			customerPaymentProfile.setPaymentProfile(customerPaymentProfileExType);
			
	
	       ValidationModeEnum validationMode = ValidationModeEnum.LIVE_MODE;
		       		
			UpdateCustomerPaymentProfileResponseType response = port.updateCustomerPaymentProfile(merchantAuthentication, Long.valueOf(cc.getProcProfileID()), customerPaymentProfileExType, validationMode);
	        logger.debug("createCustomerProfile.result=" + response);
	        if (response != null) {
	        	if (response.getResultCode() == MessageTypeEnum.ERROR && response.getMessages().getMessagesTypeMessage().size() > 0) {
	        		logger.debug("Error: [Code:" + response.getMessages().getMessagesTypeMessage().get(0).getCode() + 
	        							"][Text:" + response.getMessages().getMessagesTypeMessage().get(0).getText() + "]");
	        		return false;
	        	} 
	        }
	
	       	logger.debug("Successfully updated credit card with AuthNet with customer: " + customer.getName());
			
			return true;
		}
		catch(Exception e){
			logger.error(e.getMessage(), e);
			return false;
		}
	}


	//generates message to be sent to the Auth.Net server for processing of the CC
	//transaction and returns the authorization Id of the processed transaction
	public CCResponseObject authorizeCard(CCRequestObject request, long pin)
	{
		
		CCResponseObject responseObj = new CCResponseObject();
		String referenceNumber = request.getShortCode() + padString("0", String.valueOf(pin), 20);
		try {
			URL wsdlURL = new URL(destinationWSURLString_);
		      
	        Service ss = new Service(wsdlURL, SERVICE_NAME);
	        ServiceSoap port = ss.getServiceSoap();  
	        
			ObjectFactory objFactory = new ObjectFactory();
	
			MerchantAuthenticationType merchantAuthentication = objFactory.createMerchantAuthenticationType();
	        merchantAuthentication.setName(ma.getProperty1());
	        merchantAuthentication.setTransactionKey(ma.getProperty2());
	        
	        CreateCustomerProfileTransaction ccTransaction =  objFactory.createCreateCustomerProfileTransaction();
	        
	        ccTransaction.setTransaction(new ProfileTransactionType());
	        ccTransaction.getTransaction().setProfileTransAuthOnly(new ProfileTransAuthOnlyType());
	        ccTransaction.getTransaction().getProfileTransAuthOnly().setAmount(new BigDecimal(request.getAmount()));
	        ccTransaction.getTransaction().getProfileTransAuthOnly().setCustomerProfileId(Long.valueOf(request.getCreditCard().getProcProfileID()));
	        ccTransaction.getTransaction().getProfileTransAuthOnly().setCustomerPaymentProfileId(Long.valueOf(request.getCreditCard().getProcProfileID2()));

			StringBuffer extraOptions = new StringBuffer();
			createBaseRequest(extraOptions, request.getShortCode());
			
	        CreateCustomerProfileTransactionResponseType response = port.createCustomerProfileTransaction(merchantAuthentication, ccTransaction.getTransaction(), extraOptions.toString());
	
		     logger.debug("createCustomerProfileTransacion.result=" + response);
	        if (response != null) {
	        	if (response.getResultCode() == MessageTypeEnum.ERROR && response.getMessages().getMessagesTypeMessage().size() > 0) {
	        		logger.debug("Error: [Code:" + response.getMessages().getMessagesTypeMessage().get(0).getCode() + 
	        							"][Text:" + response.getMessages().getMessagesTypeMessage().get(0).getText() + "]");
	        		logger.info("Response from AuthNetCIM server: " + response.getDirectResponse());
	    			responseObj.setProcMessage(response.getMessages().getMessagesTypeMessage().get(0).getText());
	        		return responseObj;
	        	} 
	        }
	
		
			processResponse(response.getDirectResponse());
	
			responseObj.setResponseCode(String.valueOf(responseCode_));
			responseObj.setProcessorTranId(responseTransactionId_);
			responseObj.setAuthNumber(responseAuthCode_);
			responseObj.setReferenceNumber(referenceNumber);
			responseObj.setDateAuthorized(new Date());
			responseObj.setOrderId(referenceNumber);
			responseObj.setAmount(request.getAmount());
			responseObj.setProcMessage(responseReasonText_);
	
			if (responseCode_ == APPROVED_RESPONSE_CODE)
			{
				responseObj.setResult(1);
			}
			else
			{
				responseObj.setResult(0);
			}
	
			return responseObj;
		}
		catch(Exception e){
			logger.error(e.getMessage(), e);
			return responseObj;
		}
	}

	//transaction and returns the authorization Id of the processed transaction
	public CCResponseObject processCharge(CCRequestObject request, long pin)
	{
		//check if this is a CIM registered credit card, or if the credit card is a one time use card (from shipment "pay Now" or invoice pay now)
		if(StringUtil.isEmpty(request.getCreditCard().getProcProfileID())){
			AuthNetProcessor processor = new AuthNetProcessor(this.ma);
			return processor.processCharge(request, pin);
		}
		
		CCResponseObject responseObj = new CCResponseObject();
		String referenceNumber = request.getShortCode() + padString("0", String.valueOf(pin), 20);
		try {
			URL wsdlURL = new URL(destinationWSURLString_);
		      
	        Service ss = new Service(wsdlURL, SERVICE_NAME);
	        ServiceSoap port = ss.getServiceSoap();  
	        
			ObjectFactory objFactory = new ObjectFactory();
	
			MerchantAuthenticationType merchantAuthentication = objFactory.createMerchantAuthenticationType();
	        merchantAuthentication.setName(ma.getProperty1());
	        merchantAuthentication.setTransactionKey(ma.getProperty2());
	        
	        CreateCustomerProfileTransaction ccTransaction =  objFactory.createCreateCustomerProfileTransaction();
	        
	        ccTransaction.setTransaction(new ProfileTransactionType());
	        ccTransaction.getTransaction().setProfileTransAuthCapture(new ProfileTransAuthCaptureType());
			String amount = String.valueOf(FormattingUtil.formatDecimalTo2Places(request.getAmount(), FormattingUtil.DECIMAL_2_PLACES_PATTERN));
	        ccTransaction.getTransaction().getProfileTransAuthCapture().setAmount(new BigDecimal(amount));
	        ccTransaction.getTransaction().getProfileTransAuthCapture().setCustomerProfileId(Long.valueOf(request.getCreditCard().getProcProfileID()));
	        ccTransaction.getTransaction().getProfileTransAuthCapture().setCustomerPaymentProfileId(Long.valueOf(request.getCreditCard().getProcProfileID2()));

			StringBuffer extraOptions = new StringBuffer();
			createBaseRequest(extraOptions, request.getShortCode());
			
	        CreateCustomerProfileTransactionResponseType response = port.createCustomerProfileTransaction(merchantAuthentication, ccTransaction.getTransaction(), extraOptions.toString());
	
		     logger.debug("createCustomerProfileTransacion.result=" + response);
	        if (response != null) {
	        	if (response.getResultCode() == MessageTypeEnum.ERROR && response.getMessages().getMessagesTypeMessage().size() > 0) {
	        		logger.debug("Error: [Code:" + response.getMessages().getMessagesTypeMessage().get(0).getCode() + 
	        							"][Text:" + response.getMessages().getMessagesTypeMessage().get(0).getText() + "]");
	        		logger.info("Response from AuthNetCIM server: " + response.getDirectResponse());
	    			responseObj.setProcMessage(response.getMessages().getMessagesTypeMessage().get(0).getText());
	        		return responseObj;
	        	} 
	        }
	
		
			processResponse(response.getDirectResponse());
	
			responseObj.setResponseCode(String.valueOf(responseCode_));
			responseObj.setProcessorTranId(responseTransactionId_);
			responseObj.setAuthNumber(responseAuthCode_);
			responseObj.setReferenceNumber(referenceNumber);
			responseObj.setDateAuthorized(new Date());
			responseObj.setOrderId(referenceNumber);
			responseObj.setAmount(request.getAmount());


			if (responseCode_ == APPROVED_RESPONSE_CODE)
			{
				responseObj.setResult(1);
			}
			else
			{
				responseObj.setResult(0);
			}

			return responseObj;
		}
		catch(Exception e){
			logger.error(e.getMessage(), e);
			return responseObj;
		}
	}


	public CCResponseObject processPostAuth(CCRequestObject request)
	{
		CCResponseObject responseObj = new CCResponseObject();
		try {
			URL wsdlURL = new URL(destinationWSURLString_);
		      
	        Service ss = new Service(wsdlURL, SERVICE_NAME);
	        ServiceSoap port = ss.getServiceSoap();  
	        
			ObjectFactory objFactory = new ObjectFactory();
	
			MerchantAuthenticationType merchantAuthentication = objFactory.createMerchantAuthenticationType();
	        merchantAuthentication.setName(ma.getProperty1());
	        merchantAuthentication.setTransactionKey(ma.getProperty2());
	        
	        CreateCustomerProfileTransaction ccTransaction =  objFactory.createCreateCustomerProfileTransaction();
	        
	        ccTransaction.setTransaction(new ProfileTransactionType());
	        ccTransaction.getTransaction().setProfileTransPriorAuthCapture(new ProfileTransPriorAuthCaptureType());
	        ccTransaction.getTransaction().getProfileTransPriorAuthCapture().setTransId(request.getCCTransaction().getProcessorTransactionId());
	        ccTransaction.getTransaction().getProfileTransPriorAuthCapture().setCustomerProfileId(Long.valueOf(request.getCreditCard().getProcProfileID()));
	        ccTransaction.getTransaction().getProfileTransPriorAuthCapture().setCustomerPaymentProfileId(Long.valueOf(request.getCreditCard().getProcProfileID2()));
	        ccTransaction.getTransaction().getProfileTransPriorAuthCapture().setAmount(new BigDecimal(request.getCCTransaction().getAmount()));
	        
			StringBuffer extraOptions = new StringBuffer();
			createBaseRequest(extraOptions, request.getShortCode());
			
	        CreateCustomerProfileTransactionResponseType response = port.createCustomerProfileTransaction(merchantAuthentication, ccTransaction.getTransaction(), extraOptions.toString());
	
		     logger.debug("createCustomerProfileTransacion.result=" + response);
	        if (response != null) {
	        	if (response.getResultCode() == MessageTypeEnum.ERROR && response.getMessages().getMessagesTypeMessage().size() > 0) {
	        		logger.debug("Error: [Code:" + response.getMessages().getMessagesTypeMessage().get(0).getCode() + 
	        							"][Text:" + response.getMessages().getMessagesTypeMessage().get(0).getText() + "]");
	        		logger.info("Response from AuthNetCIM server: " + response.getDirectResponse());
	    			responseObj.setProcMessage(response.getMessages().getMessagesTypeMessage().get(0).getText());
	        		return responseObj;
	        	} 
	        }
	
		
			processResponse(response.getDirectResponse());
	
			responseObj.setDateAuthorized(new Date());
			responseObj.setReferenceNumber(request.getCCTransaction().getReceiptId());
			responseObj.setProcessorTranId(responseTransactionId_);
			responseObj.setOrderId(request.getCCTransaction().getReceiptId());
			responseObj.setAmount(request.getCCTransaction().getAmount());

			// Approved
			if (responseCode_ == APPROVED_RESPONSE_CODE)
			{
				responseObj.setAuthNumber(request.getCCTransaction().getAuthNum());
				responseObj.setResult(1);
				return responseObj;
			}

			// Declined
			responseObj.setResult(0);
			return responseObj;
		}
		catch(Exception e){
			logger.error(e.getMessage(), e);
			return responseObj;
		}
	}

	// function to void a transaction
	public CCResponseObject voidCharge(CCRequestObject request) {

		CCResponseObject responseObj = new CCResponseObject();
		try {
			URL wsdlURL = new URL(destinationWSURLString_);
		      
	        Service ss = new Service(wsdlURL, SERVICE_NAME);
	        ServiceSoap port = ss.getServiceSoap();  
	        
			ObjectFactory objFactory = new ObjectFactory();
	
			MerchantAuthenticationType merchantAuthentication = objFactory.createMerchantAuthenticationType();
	        merchantAuthentication.setName(ma.getProperty1());
	        merchantAuthentication.setTransactionKey(ma.getProperty2());
	        
	        CreateCustomerProfileTransaction ccTransaction =  objFactory.createCreateCustomerProfileTransaction();
	        
	        ccTransaction.setTransaction(new ProfileTransactionType());
	        ccTransaction.getTransaction().setProfileTransVoid(new ProfileTransVoidType());
	        ccTransaction.getTransaction().getProfileTransVoid().setTransId(request.getCCTransaction().getProcessorTransactionId());
	        ccTransaction.getTransaction().getProfileTransVoid().setCustomerProfileId(Long.valueOf(request.getCreditCard().getProcProfileID()));
	        ccTransaction.getTransaction().getProfileTransVoid().setCustomerPaymentProfileId(Long.valueOf(request.getCreditCard().getProcProfileID2()));

			StringBuffer extraOptions = new StringBuffer();
			createBaseRequest(extraOptions, request.getShortCode());
			
	        CreateCustomerProfileTransactionResponseType response = port.createCustomerProfileTransaction(merchantAuthentication, ccTransaction.getTransaction(), extraOptions.toString());
	
		     logger.debug("createCustomerProfileTransacion.result=" + response);
	        if (response != null) {
	        	if (response.getResultCode() == MessageTypeEnum.ERROR && response.getMessages().getMessagesTypeMessage().size() > 0) {
	        		logger.debug("Error: [Code:" + response.getMessages().getMessagesTypeMessage().get(0).getCode() + 
	        							"][Text:" + response.getMessages().getMessagesTypeMessage().get(0).getText() + "]");
	        		logger.info("Response from AuthNetCIM server: " + response.getDirectResponse());
	    			responseObj.setProcMessage(response.getMessages().getMessagesTypeMessage().get(0).getText());
	        		return responseObj;
	        	} 
	        }
	
		
			processResponse(response.getDirectResponse());
	
			responseObj.setResponseCode(String.valueOf(responseCode_));
			responseObj.setProcessorTranId(responseTransactionId_);
			responseObj.setAuthNumber(responseAuthCode_);
			responseObj.setReferenceNumber(request.getCCTransaction().getReceiptId());
			responseObj.setDateAuthorized(new Date());
			responseObj.setOrderId(request.getCCTransaction().getReceiptId());
//			response.setAmount(request.getCCTransaction().getAmount());

			// Approved
			if (responseCode_ == APPROVED_RESPONSE_CODE)
			{			
				responseObj.setResult(1);			
			}
			else{
				responseObj.setResult(0);
			}

			return responseObj;
		}
		catch(Exception e){
			logger.error(e.getMessage(), e);
			return responseObj;
		}
		
	}

	public CCResponseObject refundCharge(CCRequestObject request) {
		CCResponseObject responseObj = new CCResponseObject();
		try {
			URL wsdlURL = new URL(destinationWSURLString_);
		      
	        Service ss = new Service(wsdlURL, SERVICE_NAME);
	        ServiceSoap port = ss.getServiceSoap();  
	        
			ObjectFactory objFactory = new ObjectFactory();
	
			MerchantAuthenticationType merchantAuthentication = objFactory.createMerchantAuthenticationType();
	        merchantAuthentication.setName(ma.getProperty1());
	        merchantAuthentication.setTransactionKey(ma.getProperty2());
	        
	        CreateCustomerProfileTransaction ccTransaction =  objFactory.createCreateCustomerProfileTransaction();
	        
	        ccTransaction.setTransaction(new ProfileTransactionType());
	        ccTransaction.getTransaction().setProfileTransRefund(new ProfileTransRefundType());
	        ccTransaction.getTransaction().getProfileTransRefund().setTransId(request.getCCTransaction().getProcessorTransactionId());
	        ccTransaction.getTransaction().getProfileTransRefund().setCustomerProfileId(Long.valueOf(request.getCreditCard().getProcProfileID()));
	        ccTransaction.getTransaction().getProfileTransRefund().setCustomerPaymentProfileId(Long.valueOf(request.getCreditCard().getProcProfileID2()));
	        ccTransaction.getTransaction().getProfileTransRefund().setAmount(new BigDecimal(request.getAmount()));

			StringBuffer extraOptions = new StringBuffer();
			createBaseRequest(extraOptions, request.getShortCode());
			
	        CreateCustomerProfileTransactionResponseType response = port.createCustomerProfileTransaction(merchantAuthentication, ccTransaction.getTransaction(), extraOptions.toString());
	
		     logger.debug("createCustomerProfileTransacion.result=" + response);
	        if (response != null) {
	        	if (response.getResultCode() == MessageTypeEnum.ERROR && response.getMessages().getMessagesTypeMessage().size() > 0) {
	        		logger.debug("Error: [Code:" + response.getMessages().getMessagesTypeMessage().get(0).getCode() + 
	        							"][Text:" + response.getMessages().getMessagesTypeMessage().get(0).getText() + "]");
	        		logger.info("Response from AuthNetCIM server: " + response.getDirectResponse());
	    			responseObj.setProcMessage(response.getMessages().getMessagesTypeMessage().get(0).getText());
	        		return responseObj;
	        	} 
	        }
	
		
			processResponse(response.getDirectResponse());
	
			// Set processor transaction data values
			responseObj.setDateAuthorized(new Date());
			responseObj.setReferenceNumber(request.getCCTransaction().getReceiptId());
			responseObj.setProcessorTranId(responseTransactionId_);
			responseObj.setOrderId(request.getCCTransaction().getReceiptId());
			responseObj.setAmount(request.getCCTransaction().getAmount());

			// Approved
			if (responseCode_ == APPROVED_RESPONSE_CODE)
			{
				responseObj.setAuthNumber(responseAuthCode_);
				responseObj.setResult(1);
				return responseObj;
			}

			// Declined
			responseObj.setResult(0);
			return responseObj;

		}
		catch(Exception e){
			logger.error(e.getMessage(), e);
			return responseObj;
		}
	}

	private void createBaseRequest(StringBuffer stb, String requestCode)
	{
		appendToRequest(stb, DELIM_DATA_STRING, "true", false);
		appendToRequest(stb, DELIM_CHAR_STRING, DELIM_CHAR_VALUE, false);
		appendToRequest(stb, RELAY_RESPONSE_STRING, "false", false);
		appendToRequest(stb, DESCRIPTION_STRING, requestCode + " CC Request", false);
		appendToRequest(stb, DUPLICATE_WINDOW_STRING, "0", true);
//		appendToRequest(stb, MARKET_TYPE_STRING, MARKET_TYPE_STRING_VALUE);
//		appendToRequest(stb, DEVICE_TYPE_STRING, "1");
	}

	private void appendToRequest(StringBuffer stb, String param, String value, boolean last)
	{
		if(!last)
			stb.append(param + "=" + value + "&");
		else
			stb.append(param + "=" + value);
	}


	private void processResponse(String response)
	{
		// we need values from positions 1(response code), 3(reason code), 4(reason text), 5(authorization code),
		// 7(transaction ID), 39(card code response) only
		String[] tokens = response.split("\\|", -1);
		responseCode_ = Integer.parseInt(tokens[0]);
		responseReasonCode_ = Integer.parseInt(tokens[2]); // token 3
		responseReasonText_ = tokens[3]; // token 4
		responseAuthCode_ = tokens[4]; // token 5
		responseTransactionId_ = tokens[6];
		responseCardCode = tokens[38];
		
		StringBuffer sb = new StringBuffer(200);
		sb.append("Response from Authorize.Net: ResponseCode: ");
		sb.append(responseCode_);
		sb.append(", ReasonCode: ");
		sb.append(responseReasonCode_);
		sb.append(", ReasonTest: ");
		sb.append(responseReasonText_);
		sb.append(", Authorization Number: ");
		sb.append(responseAuthCode_);
		sb.append(", ProcessorTransactionId: ");
		sb.append(responseTransactionId_);
		sb.append(", CardCodeRespose: ");
		sb.append(responseCardCode);
		logger.info(sb.toString());
	}


	
	private String padString(String padding, String stringToPad, int desiredSize){
		StringBuffer stb = new StringBuffer();
		int diff = desiredSize-stringToPad.length();
		for(int i=0; i<diff; i++)
			stb.append(padding);
		stb.append(stringToPad);
		return stb.toString();
	}

	private static XMLGregorianCalendar getXmlDate(String sDate) {
		try {
			 XMLGregorianCalendar xgc = DatatypeFactory.newInstance().newXMLGregorianCalendar(sDate);
			 xgc.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			 xgc.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
			 xgc.setHour(DatatypeConstants.FIELD_UNDEFINED);
			 xgc.setMinute(DatatypeConstants.FIELD_UNDEFINED);
			 xgc.setSecond(DatatypeConstants.FIELD_UNDEFINED);
			 xgc.setDay(DatatypeConstants.FIELD_UNDEFINED);
//			cal.setTimeZone(javax.xml.datatype.DatatypeConstants.FIELD_UNDEFINED);
			return xgc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
