package com.meritconinc.shiplinx.ccprocessing.processor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import org.apache.log4j.Logger;

import com.meritconinc.shiplinx.ccprocessing.CCRequestObject;
import com.meritconinc.shiplinx.ccprocessing.CCResponseObject;
import com.meritconinc.shiplinx.ccprocessing.CreditCardAuthorizationException;
import com.meritconinc.shiplinx.ccprocessing.ICreditCardProcessor;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.MerchantAccount;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public final class AuthNetProcessor implements ICreditCardProcessor
{
	private final static Logger logger__ = Logger.getLogger(AuthNetProcessor.class);

	private String destinationURLString_;
	private MerchantAccount ma = null;

	private String responseAuthCode_ = null;
	private int responseCode_ = 0;
	private int responseReasonCode_ = 0;
	private String responseReasonText_;
	private String responseTransactionId_;
	private String responseCardCode;
 
	private static final String LOGIN_ID_STRING = "x_login";
	private static final String TRAN_KEY_STRING = "x_tran_key";
	private static final String VERSION_STRING = "x_version";
	private static final String VERSION_VALUE_STRING = "3.1";
	private static final String TEST_REQUEST_STRING = "x_test_request";
	private static final String METHOD_STRING = "x_method";
	private static final String METHOD_VALUE_STRING = "CC";
	private static final String TYPE_STRING = "x_type";
	private static final String AMOUNT_STRING = "x_amount";
	private static final String DELIM_DATA_STRING = "x_delim_data";
	private static final String DELIM_CHAR_STRING = "x_delim_char";
	private static final String DELIM_CHAR_VALUE = "|";
	private static final String RELAY_RESPONSE_STRING = "x_relay_response";
	private static final String MARKET_TYPE_STRING = "x_market_type";
	private static final String DEVICE_TYPE_STRING = "x_device_type";
	private static final String DESCRIPTION_STRING = "x_description";
	private static final String RESPONSE_FORMAT_STRING = "x_response_format";
	private static final String RESPONSE_FORMAT_DELIMITED_VALUE = "1";
	private static final String MARKET_TYPE_STRING_VALUE = "2";
	private static final String USER_REF_STRING = "x_user_ref";
	private static final String PROCESSOR_TRANSACTION_ID_STRING = "x_ref_trans_id";
	private static final String CARD_NUM_STRING = "x_card_num";
	private static final String CARD_CODE_STRING = "x_card_code";
	private static final String CARD_EXP_DATE_STRING = "x_exp_date";
	private static final String DUPLICATE_WINDOW_STRING = "x_duplicate_window";

	private static final int APPROVED_RESPONSE_CODE = 1;
	private static final int DECLINED_RESPONSE_CODE = 2;
	private static final int ERROR_RESPONSE_CODE = 3;

	private static final String TRANSACTION_TYPE_AUTH_ONLY = "AUTH_ONLY";
	private static final String TRANSACTION_TYPE_PRIOR_AUTH_CAPTURE = "PRIOR_AUTH_CAPTURE";
	private static final String TRANSACTION_TYPE_AUTH_CAPTURE = "AUTH_CAPTURE";
	private static final String TRANSACTION_TYPE_CREDIT = "CREDIT";
	private static final String TRANSACTION_TYPE_VOID = "VOID";

	private static final String PROD_URL = "https://cardpresent.authorize.net/gateway/transact.dll";
	private static final String TEST_URL = "https://test.authorize.net/gateway/transact.dll";
	private static final String TEST_LOGIN = "6r6C7sF3D";
	private static final String TEST_KEY = "67sQ2D72t36f3RdD";

	public AuthNetProcessor(MerchantAccount ma)
	{
		this.ma = ma;

		if (ShiplinxConstants.isTestMode())
		{
			destinationURLString_ = TEST_URL;
			ma.setProperty1(TEST_LOGIN);
			ma.setProperty2(TEST_KEY);
		}
		else
		{
			destinationURLString_ = PROD_URL;
		}
		
		if(ma.getProperty3()!=null && ma.getProperty3().length()>0)
			destinationURLString_ = ma.getProperty3();
		
	}

	public boolean verifyCard(CCRequestObject request, long pin){
		return false;
	}

	public boolean addCard(CreditCard creditCard, Customer customer){
		return false;
	}

	public boolean updateCard(CreditCard creditCard, Customer customer){
		return false;
	}

	
	//generates message to be sent to the Auth.Net server for processing of the CC
	//transaction and returns the authorization Id of the processed transaction
	public CCResponseObject authorizeCard(CCRequestObject request, long pin)
	{
		CCResponseObject response = new CCResponseObject();
		CreditCard cc = request.getCreditCard();	

		StringBuffer sb = new StringBuffer();
		createBaseRequest(sb);
		String amount = String.valueOf(FormattingUtil.formatDecimalTo2Places(request.getAmount(), FormattingUtil.DECIMAL_2_PLACES_PATTERN));

		appendToRequest(sb, TYPE_STRING, TRANSACTION_TYPE_AUTH_ONLY);
		appendToRequest(sb, AMOUNT_STRING, amount);
		appendToRequest(sb, CARD_NUM_STRING, cc.getCcNumber());
		String month = padString("0", cc.getCcExpiryMonth(), 2);
	    String exp = month + cc.getCcExpiryYear().substring(2,4);
		appendToRequest(sb, CARD_EXP_DATE_STRING, exp);
		String referenceNumber = request.getShortCode() + padString("0", String.valueOf(pin), 20);
		appendToRequest(sb, USER_REF_STRING, referenceNumber);

		if(cc.getCvd()!=null && cc.getCvd().length()>0){
			appendToRequest(sb, CARD_CODE_STRING, cc.getCvd());
		}

		
		String responseString = sendRequest(sb.toString());
		processResponse(responseString);

		response.setResponseCode(String.valueOf(responseCode_));
		response.setProcessorTranId(responseTransactionId_);
		response.setAuthNumber(responseAuthCode_);
		response.setReferenceNumber(referenceNumber);
		response.setDateAuthorized(new Date());
		response.setOrderId(referenceNumber);
		response.setAmount(request.getAmount());
		response.setProcMessage(responseReasonText_);

		if (responseCode_ == APPROVED_RESPONSE_CODE)
		{
			response.setResult(1);
		}
		else
		{
			response.setResult(0);
		}

		return response;
	}


	public CCResponseObject processCharge(CCRequestObject request, long pin)
	{

		CCResponseObject response = new CCResponseObject();
		CreditCard cc = request.getCreditCard();	
		String amount = String.valueOf(FormattingUtil.formatDecimalTo2Places(request.getAmount(), FormattingUtil.DECIMAL_2_PLACES_PATTERN));

		StringBuffer sb = new StringBuffer();
		createBaseRequest(sb);
		appendToRequest(sb, TYPE_STRING, TRANSACTION_TYPE_AUTH_CAPTURE);
		appendToRequest(sb, AMOUNT_STRING, amount);
		appendToRequest(sb, CARD_NUM_STRING, cc.getCcNumber());
		String month = padString("0", cc.getCcExpiryMonth(), 2);
	    String exp = month + cc.getCcExpiryYear().substring(2,4);
		appendToRequest(sb, CARD_EXP_DATE_STRING, exp);
		String referenceNumber = request.getShortCode() + padString("0", String.valueOf(pin), 20);
		appendToRequest(sb, USER_REF_STRING, referenceNumber);
		if(cc.getCvd()!=null && cc.getCvd().length()>0){
			appendToRequest(sb, CARD_CODE_STRING, cc.getCvd());
		}

		String responseString = sendRequest(sb.toString());
		processResponse(responseString);

		response.setResponseCode(String.valueOf(responseCode_));
		response.setProcessorTranId(responseTransactionId_);
		response.setAuthNumber(responseAuthCode_);
		response.setReferenceNumber(referenceNumber);
		response.setDateAuthorized(new Date());
		response.setOrderId(referenceNumber);
		response.setAmount(request.getAmount());
		response.setProcMessage(responseReasonText_);


		if (responseCode_ == APPROVED_RESPONSE_CODE)
		{
			response.setResult(1);
		}
		else
		{
			response.setResult(0);
		}

		return response;
	}

	public CCResponseObject processPostAuth(CCRequestObject request)
	{
		CCResponseObject response = new CCResponseObject();

		StringBuffer sb = new StringBuffer();
		createBaseRequest(sb);
		appendToRequest(sb, TYPE_STRING, TRANSACTION_TYPE_PRIOR_AUTH_CAPTURE);
		appendToRequest(sb, PROCESSOR_TRANSACTION_ID_STRING, request.getCCTransaction().getProcessorTransactionId());
		appendToRequest(sb, USER_REF_STRING, request.getCCTransaction().getReceiptId());

		String responseString = sendRequest(sb.toString());
		processResponse(responseString);

		// Set ProcessorTransactionData values from processor
		response.setDateAuthorized(new Date());
		response.setReferenceNumber(request.getCCTransaction().getReceiptId());
		response.setProcessorTranId(responseTransactionId_);
		response.setOrderId(request.getCCTransaction().getReceiptId());
		response.setAmount(request.getCCTransaction().getAmount());
		response.setProcMessage(responseReasonText_);

		// Approved
		if (responseCode_ == APPROVED_RESPONSE_CODE)
		{
			response.setAuthNumber(request.getCCTransaction().getAuthNum());
			response.setResult(1);
			return response;
		}

		// Declined
		response.setResult(0);
		return response;
	}

	// function to void a transaction
	public CCResponseObject voidCharge(CCRequestObject request) {

		CCResponseObject response = new CCResponseObject();
		StringBuffer sb = new StringBuffer();
		createBaseRequest(sb);
		appendToRequest(sb, TYPE_STRING, TRANSACTION_TYPE_VOID);

		appendToRequest(sb, PROCESSOR_TRANSACTION_ID_STRING, request.getCCTransaction().getProcessorTransactionId());
		appendToRequest(sb, USER_REF_STRING, request.getCCTransaction().getReceiptId());
//		String amount = String.valueOf(FormattingUtil.formatDecimalTo2Places(request.getCCTransaction().getAmount(), FormattingUtil.DECIMAL_2_PLACES_PATTERN));
//		appendToRequest(sb, AMOUNT_STRING, amount);

		String responseString = sendRequest(sb.toString());
		processResponse(responseString);

		response.setResponseCode(String.valueOf(responseCode_));
		response.setProcessorTranId(responseTransactionId_);
		response.setAuthNumber(responseAuthCode_);
		response.setReferenceNumber(request.getCCTransaction().getReceiptId());
		response.setDateAuthorized(new Date());
		response.setOrderId(request.getCCTransaction().getReceiptId());
		response.setProcMessage(responseReasonText_);

		// Approved
		if (responseCode_ == APPROVED_RESPONSE_CODE)
		{			
			response.setResult(1);			
		}
		else{
			response.setResult(0);
		}

		return response;
		
	}

	public CCResponseObject refundCharge(CCRequestObject request) {
		CCResponseObject response = new CCResponseObject();

		StringBuffer sb = new StringBuffer();
		createBaseRequest(sb);
		appendToRequest(sb, TYPE_STRING, TRANSACTION_TYPE_CREDIT);
		appendToRequest(sb, PROCESSOR_TRANSACTION_ID_STRING, request.getCCTransaction().getProcessorTransactionId());
		appendToRequest(sb, USER_REF_STRING, request.getCCTransaction().getReceiptId());
		String card_charged = request.getCCTransaction().getCardNumCharged();
		
		appendToRequest(sb, CARD_NUM_STRING, card_charged.substring(card_charged.length()-4, card_charged.length()));
		String amount = String.valueOf(FormattingUtil.formatDecimalTo2Places(request.getAmount(), FormattingUtil.DECIMAL_2_PLACES_PATTERN));
		appendToRequest(sb, AMOUNT_STRING, amount);

		String responseString = sendRequest(sb.toString());
		processResponse(responseString);

		// Set processor transaction data values
		response.setDateAuthorized(new Date());
		response.setReferenceNumber(request.getCCTransaction().getReceiptId());
		response.setProcessorTranId(responseTransactionId_);
		response.setOrderId(request.getCCTransaction().getReceiptId());
		response.setAmount(request.getCCTransaction().getAmount());
		response.setProcMessage(responseReasonText_);

		// Approved
		if (responseCode_ == APPROVED_RESPONSE_CODE)
		{
			response.setAuthNumber(responseAuthCode_);
			response.setResult(1);
			return response;
		}

		// Declined
		response.setResult(0);
		return response;
	}

	private void createBaseRequest(StringBuffer stb)
	{
		appendToRequest(stb, LOGIN_ID_STRING, ma.getProperty1());
		appendToRequest(stb, TRAN_KEY_STRING, ma.getProperty2());
		appendToRequest(stb, VERSION_STRING, VERSION_VALUE_STRING);
		// appendToRequest(stb, TEST_REQUEST_STRING, "TRUE");
		appendToRequest(stb, METHOD_STRING, METHOD_VALUE_STRING);
		 appendToRequest(stb, DELIM_DATA_STRING, "TRUE");
		appendToRequest(stb, DELIM_CHAR_STRING, DELIM_CHAR_VALUE);
		 appendToRequest(stb, RELAY_RESPONSE_STRING, "FALSE");
		appendToRequest(stb, DESCRIPTION_STRING, "SHIPLINX CC Request");
		appendToRequest(stb, RESPONSE_FORMAT_STRING, RESPONSE_FORMAT_DELIMITED_VALUE);
		appendToRequest(stb, DUPLICATE_WINDOW_STRING, "0");

//		appendToRequest(stb, MARKET_TYPE_STRING, MARKET_TYPE_STRING_VALUE);
//		appendToRequest(stb, DEVICE_TYPE_STRING, "1");
	}

	private void appendToRequest(StringBuffer stb, String param, String value)
	{
		stb.append(param + "=" + value + "&");
	}

	private String sendRequest(String request) throws CreditCardAuthorizationException
	{
		
		try
		{
			URL url = null;
			url = new URL(destinationURLString_);

			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setUseCaches(false);

			// not necessarily required but fixes a bug with some servers
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			// POST the data in the string buffer
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			if (logger__.isDebugEnabled())
			{
				logger__.debug("AuthorizeNet request: " + request);
			}
			else
			{
				logger__.info("Sending request to Authorize.Net");
			}
			out.write(request.getBytes());
			out.flush();
			out.close();

			// process and read the gateway response
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String response;
			response = in.readLine();
			in.close(); // no more data
			logger__.debug("AuthorizeNet response: " + response);

			return response;
		}
		catch (IOException e)
		{
			throw new CreditCardAuthorizationException("Authorize.NET Processor is Offline");
		}

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
		logger__.info(sb.toString());
	}


	
	private String padString(String padding, String stringToPad, int desiredSize){
		StringBuffer stb = new StringBuffer();
		int diff = desiredSize-stringToPad.length();
		for(int i=0; i<diff; i++)
			stb.append(padding);
		stb.append(stringToPad);
		return stb.toString();
	}


}
