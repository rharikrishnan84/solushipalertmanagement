package com.meritconinc.shiplinx.ccprocessing.processor;

/*
 * Created on Jan 14, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */


import java.util.Date;

import org.apache.log4j.Logger;

import JavaAPI.CardVerification;
import JavaAPI.HttpsPostRequest;
import JavaAPI.Purchase;
import JavaAPI.ResAddCC;
import JavaAPI.ResPreauthCC;
import JavaAPI.ResPurchaseCC;
import JavaAPI.ResUpdateCC;
import JavaAPI.ResolveData;
import JavaAPI.ResolverHttpsPostRequest;
import JavaAPI.ResolverReceipt;

import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.ccprocessing.CCRequestObject;
import com.meritconinc.shiplinx.ccprocessing.CCResponseObject;
import com.meritconinc.shiplinx.ccprocessing.CreditCardAuthorizationException;
import com.meritconinc.shiplinx.ccprocessing.ICreditCardProcessor;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.MerchantAccount;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public final class MonerisProcessor implements ICreditCardProcessor
{
	private final static Logger log = Logger.getLogger(MonerisProcessor.class);

	// if the reference # / order_id is a duplicate or if API Token is wrong
	// then the Moneris processor does not send us a response code. So have to
	// check this message in the code and create a special response
	// codes...cannot be any of Moneris' return codes
	private final static String DUPLICATE_ORDER_ID_MESSAGE = "The transaction was not sent to the host because of a duplicate order id";
	private final static String DUPLICATE_ORDER_ID_MESSAGE_CODE = "1000";
	private final static String API_TOKEN_MISMATCH_MESSAGE = "API token mismatch";
	private final static String API_TOKEN_MISMATCH_CODE = "1001";
	private final static String TEST_SERVER = "esqa.moneris.com";
	private final static String LIVE_SERVER = "www3.moneris.com";
	 
	private String CRYPT_VALUE = "7";

	private String url;
	private MerchantAccount ma = null;
	private static String[] acceptableAVSCodes = {"A", "D", "M", "P", "X", "Y", "Z"};
	
	
	public MonerisProcessor()
	{
	}

	public MonerisProcessor(MerchantAccount ma)
	{
		if(ShiplinxConstants.isTestMode())
			url = this.TEST_SERVER;
		else
			url = this.LIVE_SERVER;
		this.ma = ma;
	}

	public boolean verifyCard(CCRequestObject request, long pin){
		
		
		CCResponseObject response = new CCResponseObject();
		
		CreditCard cc = request.getCreditCard();
		String order_id = request.getShortCode() + padString("0", String.valueOf(pin), 20);
		String card = cc.getCcNumber();
		String month = padString("0", cc.getCcExpiryMonth(), 2);
	    String exp = cc.getCcExpiryYear().substring(2,4) + month;
	    log.debug("Car info :" + cc.getMaskedCCNum() + " " + exp);
		log.debug("STORE ID AND API TOKEN IS " + ma.getProperty1() + " " + ma.getProperty2());

	    CardVerification cv = new CardVerification (order_id, card, exp, CRYPT_VALUE);
		/*JavaAPI.CvdInfo cvdInfo = new JavaAPI.CvdInfo("1", cc.getCvd());
		cv.setCvdInfo(cvdInfo);*/
		/*JavaAPI.AvsInfo avsInfo = new JavaAPI.AvsInfo(cc.getBillingAddress().getAddress1(),cc.getBillingAddress().getAddress2(),cc.getBillingAddress().getPostalCode());
		cv.setAvsInfo(avsInfo);*/
		HttpsPostRequest mpgReq = new HttpsPostRequest(url, ma.getProperty1(), ma.getProperty2(), cv);
		JavaAPI.Receipt receipt = mpgReq.getReceipt();
		
		processResponse(receipt, request, response);

		if(response.getResult()==1)
			return true;
		
		return false;
		
	}

	
	public boolean addCard(CreditCard creditCard, Customer customer){
		
		String card = creditCard.getCcNumber();
		String month = padString("0", creditCard.getCcExpiryMonth(), 2);
	    String exp = creditCard.getCcExpiryYear().substring(2,4) + month;
	    log.debug("Car info :" + creditCard.getMaskedCCNum() + " " + exp + "\n");
		String crypt = CRYPT_VALUE;
		String email = customer.getAddress().getEmailAddress();
		String phone = customer.getAddress().getPhoneNo();
		String customer_id = customer.getName();
	
		/*JavaAPI.AvsInfo avsInfo = new JavaAPI.AvsInfo(creditCard.getBillingAddress().getAddress1(),creditCard.getBillingAddress().getAddress2(),creditCard.getBillingAddress().getPostalCode());*/

		
		ResAddCC resAddCC = new ResAddCC(card, exp, crypt);
		resAddCC.setCustId(customer_id);
		resAddCC.setPhone(phone);
		resAddCC.setEmail(email);
		/*resAddCC.setAvsInfo(avsInfo);*/
		
		log.debug("STORE ID AND API TOKEN IS " + ma.getProperty1() + " " + ma.getProperty2());

		ResolverHttpsPostRequest mpgReq =
			new ResolverHttpsPostRequest(url, ma.getProperty1(), ma.getProperty2(), resAddCC);
		ResolverReceipt resreceipt = mpgReq.getResolverReceipt();
		ResolveData resdata = resreceipt.getResolveData();	
		
		if(!resreceipt.getResSuccess().equalsIgnoreCase("true")){
			log.debug("Message = " + resreceipt.getMessage());
			return false;
		}
		
		//card was successfully stored with processor
		
		log.debug("DataKey = " + resreceipt.getDataKey());
		log.debug("ResponseCode = " + resreceipt.getResponseCode());
		log.debug("Message = " + resreceipt.getMessage());
		log.debug("Message = " + resreceipt.getMessage());
		
		log.debug("CVD Result Code = " + resreceipt.getCvdResultCode());
		/*log.debug("AVS Result Code = " + resreceipt.getAvsResultCode());*/
		
		creditCard.setProcProfileID(resreceipt.getDataKey()); //this is the data key to be passed for all future transactions
		
		return true;
		

//		log.debug("TransDate = " + resreceipt.getTransDate());
//		log.debug("TransTime = " + resreceipt.getTransTime());
//		log.debug("Complete = " + resreceipt.getComplete());
//		log.debug("TimedOut = " + resreceipt.getTimedOut());
//		log.debug("ResSuccess = " + resreceipt.getResSuccess());
//		log.debug("PaymentType = " + resreceipt.getPaymentType() + "\n");
//		//Contents of ResolveData
//		log.debug("Cust ID = " + resdata.getResCustId());
//		log.debug("Phone = " + resdata.getResPhone());
//		log.debug("Email = " + resdata.getResEmail());
//		log.debug("Note = " + resdata.getResNote());
//		log.debug("MaskedPan = " + resdata.getResMaskedPan());
//		log.debug("Exp Date = " + resdata.getResExpDate());
//		log.debug("Crypt Type = " + resdata.getResCryptType());
//		log.debug("Avs Street Number = " + resdata.getResAvsStreetNumber());
//		log.debug("Avs Street Name = " + resdata.getResAvsStreetName());
//		log.debug("Avs Zipcode = " + resdata.getResAvsZipcode());
	}

	public boolean updateCard(CreditCard creditCard, Customer customer){
		String card = creditCard.getCcNumber();
		String month = padString("0", creditCard.getCcExpiryMonth(), 2);
	    String exp = creditCard.getCcExpiryYear().substring(2,4) + month;
	    log.debug("Car info :" + creditCard.getMaskedCCNum() + " " + exp + "\n");
		String crypt = CRYPT_VALUE;
		String email = customer.getAddress().getEmailAddress();
		String phone = customer.getAddress().getPhoneNo();
		String customer_id = customer.getName();
	
		/*JavaAPI.AvsInfo avsInfo = new JavaAPI.AvsInfo(creditCard.getBillingAddress().getAddress1(),creditCard.getBillingAddress().getAddress2(),creditCard.getBillingAddress().getPostalCode());*/

		
		ResUpdateCC resUpdateCC = new ResUpdateCC(creditCard.getProcProfileID()); //data key
		resUpdateCC.setCustId(customer_id);
		resUpdateCC.setPhone(phone);
		resUpdateCC.setEmail(email);
		/*resUpdateCC.setAvsInfo(avsInfo);*/
		resUpdateCC.setPan(card);
		resUpdateCC.setExpdate(exp);
		resUpdateCC.setCryptType(crypt);
		
		log.debug("STORE ID AND API TOKEN IS " + ma.getProperty1() + " " + ma.getProperty2());

		ResolverHttpsPostRequest mpgReq =
			new ResolverHttpsPostRequest(url, ma.getProperty1(), ma.getProperty2(), resUpdateCC);
		ResolverReceipt resreceipt = mpgReq.getResolverReceipt();
		ResolveData resdata = resreceipt.getResolveData();	
		
		if(!resreceipt.getResSuccess().equalsIgnoreCase("true")){
			log.debug("Message = " + resreceipt.getMessage());
			return false;
		}
		
		//card was successfully stored with processor
		
		log.debug("DataKey = " + resreceipt.getDataKey());
		log.debug("ResponseCode = " + resreceipt.getResponseCode());
		log.debug("Message = " + resreceipt.getMessage());
		log.debug("Message = " + resreceipt.getMessage());
		
		log.debug("CVD Result Code = " + resreceipt.getCvdResultCode());
		/*log.debug("AVS Result Code = " + resreceipt.getAvsResultCode());*/
		
		return true;
	}
	//generates message to be sent to the Moneris server for processing of the CC
	//transaction and returns the authorization Id of the processed transaction
	public CCResponseObject authorizeCard(CCRequestObject request, long pin)
	{
		CCResponseObject response = new CCResponseObject();
		CreditCard cc = request.getCreditCard();	
		
		String order_id = request.getShortCode() + padString("0", String.valueOf(pin), 20);
		log.debug("Order Id is " + order_id);
		String amount = String.valueOf(FormattingUtil.formatDecimalTo2Places(request.getAmount(), FormattingUtil.DECIMAL_2_PLACES_PATTERN));
		String crypt = CRYPT_VALUE;

		//the test server only authorizes penny values of .00 .01 and .04
		if(ShiplinxConstants.isTestMode())
			amount = "10.10";
		
		log.debug("STORE ID AND API TOKEN IS " + ma.getProperty1() + " " + ma.getProperty2());
		
		if(!StringUtil.isEmpty(cc.getProcProfileID())){ //credit card data is stored with processor
			ResPreauthCC resPreauthCC = new ResPreauthCC(cc.getProcProfileID(), order_id, amount, crypt);
			ResolverHttpsPostRequest mpgReq =
				new ResolverHttpsPostRequest(url, ma.getProperty1(), ma.getProperty2(), resPreauthCC);
				ResolverReceipt resreceipt = mpgReq.getResolverReceipt();
				processResolverResponse(resreceipt, request, response);
				return response;
		}
		
		String card = cc.getCcNumber();
		String month = padString("0", cc.getCcExpiryMonth(), 2);
	    String exp = cc.getCcExpiryYear().substring(2,4) + month;
	    log.debug("Car info :" + cc.getMaskedCCNum() + " " + exp + "\nAmount: " + amount);

	    JavaAPI.PreAuth preauth = new JavaAPI.PreAuth(order_id, amount, card, exp, crypt);
		if(cc.getCvd()!=null && cc.getCvd().length()>0){
			JavaAPI.CvdInfo cvdInfo = new JavaAPI.CvdInfo("1", cc.getCvd());
			preauth.setCvdInfo(cvdInfo);
		}
		
		JavaAPI.HttpsPostRequest mpgReq =
			new JavaAPI.HttpsPostRequest(url, ma.getProperty1(), ma.getProperty2(), preauth);

		JavaAPI.Receipt receipt = mpgReq.getReceipt();

		processResponse(receipt, request, response);

		return response;
	}

	//generates the XML document to be sent to the Moneris server for processing of the CC
	//transaction and returns the authorization Id of the processed transaction
	public CCResponseObject processPostAuth(CCRequestObject request)
		
	{
		CCResponseObject response = new CCResponseObject();

		String order_id = request.getCCTransaction().getReceiptId(); // referenceNumber; Moneris wants ORIGINAL ref number in completions/post auths
		String amount = String.valueOf(FormattingUtil.formatDecimalTo2Places(request.getAmount(), FormattingUtil.DECIMAL_2_PLACES_PATTERN));
		String txn_number = request.getCCTransaction().getProcessorTransactionId();

		String crypt = CRYPT_VALUE;

		//the test server only authorizes penny values of .00 .01 and .04
		if(url.equalsIgnoreCase(TEST_SERVER))
			amount = "10.00";
		
		JavaAPI.HttpsPostRequest mpgReq =
			new JavaAPI.HttpsPostRequest(url, ma.getProperty1(), ma.getProperty2(), new JavaAPI.Completion(order_id, amount, txn_number, crypt));

		JavaAPI.Receipt receipt = mpgReq.getReceipt();
		
		processResponse(receipt, request, response);

		return response;

	}

	
	public CCResponseObject processCharge(CCRequestObject request, long pin)
	
	{
		CCResponseObject response = new CCResponseObject();
		CreditCard cc = request.getCreditCard();	
		
		String request_id = request.getShortCode() + padString("0", String.valueOf(pin), 20);
		log.debug("Request Id is " + request_id);
		String amount = String.valueOf(FormattingUtil.formatDecimalTo2Places(request.getAmount(), FormattingUtil.DECIMAL_2_PLACES_PATTERN));
		String crypt = CRYPT_VALUE;

		//the test server only authorizes penny values of .00 .01 and .04
		if(url.equalsIgnoreCase(TEST_SERVER))
			amount = "10.31";
		
		log.debug("STORE ID AND API TOKEN IS " + ma.getProperty1() + " " + ma.getProperty2());
		log.debug("Expiry month/year is " + cc.getCcExpiryMonth() + " / " + cc.getCcExpiryYear());
		
		if(!StringUtil.isEmpty(cc.getProcProfileID())){ //credit card data is stored with processor
			ResPurchaseCC resPurchaseCC = new ResPurchaseCC(cc.getProcProfileID(), request_id, amount, crypt);
			ResolverHttpsPostRequest mpgReq =
				new ResolverHttpsPostRequest(url, ma.getProperty1(), ma.getProperty2(), resPurchaseCC);
				ResolverReceipt resreceipt = mpgReq.getResolverReceipt();
				processResolverResponse(resreceipt, request, response);
				return response;
		}
		
		String card = cc.getCcNumber();
		String month = padString("0", cc.getCcExpiryMonth(), 2);
	    String exp = cc.getCcExpiryYear().substring(2,4) + month;
	    log.debug("Card info :" + cc.getMaskedCCNum() + " " + exp + "\nAmount: " + amount);
		Purchase p = new Purchase(request_id, amount, card, exp, crypt);
		if(cc.getCvd()!=null && cc.getCvd().length()>0){
			JavaAPI.CvdInfo cvdInfo = new JavaAPI.CvdInfo("1", cc.getCvd());
			p.setCvdInfo(cvdInfo);
		}

		JavaAPI.HttpsPostRequest mpgReq =
			new JavaAPI.HttpsPostRequest(url, ma.getProperty1(), ma.getProperty2(), p);

		JavaAPI.Receipt receipt = mpgReq.getReceipt();

		processResponse(receipt, request, response);
		
		//change the value back to original value in case of TEST transaction
		if(url.equalsIgnoreCase(TEST_SERVER))
			response.setAmount(request.getAmount());
		
		return response;

	}

	

	// function to void a transaction
	public CCResponseObject voidCharge(CCRequestObject request) {
		CCResponseObject response = new CCResponseObject();

		String order_id = request.getCCTransaction().getReceiptId(); 
		String txn_number = request.getCCTransaction().getProcessorTransactionId();
		String crypt = CRYPT_VALUE;

		log.debug("ORDER ID AND TXN NUMBER FOR VOID : " + order_id + " " + txn_number);
		
		JavaAPI.HttpsPostRequest mpgReq = new JavaAPI.HttpsPostRequest(
				url, ma.getProperty1(), ma.getProperty2(),
				new JavaAPI.PurchaseCorrection(order_id, txn_number, crypt));

		JavaAPI.Receipt receipt = mpgReq.getReceipt();

		processResponse(receipt, request, response);
		return response;
	}
	
	public CCResponseObject voidCharge(String order_id, String txn_number) {
		CCResponseObject response = new CCResponseObject();

		String crypt = CRYPT_VALUE;

		log.debug("ORDER ID AND TXN NUMBER FOR VOID : " + order_id + " " + txn_number);
		
		JavaAPI.HttpsPostRequest mpgReq = new JavaAPI.HttpsPostRequest(
				url, ma.getProperty1(), ma.getProperty2(),
				new JavaAPI.PurchaseCorrection(order_id, txn_number, crypt));

		JavaAPI.Receipt receipt = mpgReq.getReceipt();
		log.info("Voiding charge result. ResponseCode/Message : " + receipt.getResponseCode() + " / " + receipt.getMessage());
		return response;
	}

	// function to void a transaction
	public CCResponseObject refundCharge(CCRequestObject request) {
		CCResponseObject response = new CCResponseObject();

		String order_id = request.getCCTransaction().getReceiptId(); 
		String txn_number = request.getCCTransaction().getProcessorTransactionId();
		String crypt = CRYPT_VALUE;
		String amount = String.valueOf(FormattingUtil.formatDecimalTo2Places(request.getAmount(), FormattingUtil.DECIMAL_2_PLACES_PATTERN));

		log.debug("ORDER ID AND TXN NUMBER FOR VOID : " + order_id + " " + txn_number);
		
		JavaAPI.HttpsPostRequest mpgReq = new JavaAPI.HttpsPostRequest(
				url, ma.getProperty1(), ma.getProperty2(),
				new JavaAPI.Refund(order_id, amount, txn_number, crypt));

		JavaAPI.Receipt receipt = mpgReq.getReceipt();

		processResponse(receipt, request, response);
		return response;
	}
	
	private void processResponse(JavaAPI.Receipt receipt,
			CCRequestObject request, CCResponseObject response) {
		response.setAuthNumber(receipt.getAuthCode());
		response.setResponseCode(receipt.getResponseCode());
		response.setProcessorTranId(receipt.getTxnNumber());
		response.setOrderId(receipt.getReceiptId());
		response.setReferenceNumber(receipt.getReferenceNum());
		response.setCvdResponse(receipt.getCvdResultCode());
		response.setProcMessage(receipt.getMessage().trim());

		log.debug("CardType = " + receipt.getCardType());
		log.debug("TransAmount = " + receipt.getTransAmount());
		log.debug("TxnNumber = " + receipt.getTxnNumber());
		log.debug("ReceiptId = " + receipt.getReceiptId());
		log.debug("TransType = " + receipt.getTransType());
		log.debug("ReferenceNum = " + receipt.getReferenceNum());
		log.debug("ResponseCode = " + receipt.getResponseCode());
		log.debug("ISO = " + receipt.getISO());
		log.debug("BankTotals = " + receipt.getBankTotals());
		log.debug("Message = " + receipt.getMessage());
		log.debug("AuthCode = " + receipt.getAuthCode());
		log.debug("Complete = " + receipt.getComplete());
		log.debug("TransDate = " + receipt.getTransDate());
		log.debug("TransTime = " + receipt.getTransTime());
		log.debug("Ticket = " + receipt.getTicket());
		log.debug("TimedOut = " + receipt.getTimedOut());
		log.info("CVD Result Code = " + receipt.getCvdResultCode());
		/*log.info("AVS Result Code = " + receipt.getAvsResultCode());*/

		if ((receipt.getResponseCode().equalsIgnoreCase("NULL"))
				|| (receipt.getResponseCode() == null)) {
			if (receipt.getMessage().trim().equalsIgnoreCase(
					DUPLICATE_ORDER_ID_MESSAGE)) {
				log.warn("Duplicate Reference number / order id");
				response.setResponseCode(DUPLICATE_ORDER_ID_MESSAGE_CODE);
			} else if (receipt.getMessage().trim().equalsIgnoreCase(
					API_TOKEN_MISMATCH_MESSAGE)) {
				log.warn("Error in API token set up");
				response.setResponseCode(new String(API_TOKEN_MISMATCH_CODE));
			}
			try {
				log.error("Message from Moneris server is : "
						+ receipt.getMessage().trim());
			} catch (Exception e) {
				log.error("No message available from Moneris server");
				throw new CreditCardAuthorizationException(
						"Error Communicating with Moneris server : No message!");
			}
			throw new CreditCardAuthorizationException(
					"Error Communicating with Moneris server : "
							+ receipt.getMessage());
		}

		try {
			Float transactionAmount = null;

			if (!receipt.getTransAmount().equalsIgnoreCase("null"))
				transactionAmount = (new Float(receipt.getTransAmount()));
			if (transactionAmount != null)
				response.setAmount((new Float(receipt.getTransAmount()))
						.floatValue());
			else
				response.setAmount(0.0f);
		} catch (NumberFormatException ne) {
			response.setAmount(request.getAmount());
		}

		response.setDateAuthorized(new Date());
		int responseCode = new Integer(response.getResponseCode()).intValue();
		
		//parse the CVD response if applicable
		if(receipt.getCvdResultCode()!=null && receipt.getCvdResultCode().length()>0){
			String indicator = receipt.getCvdResultCode().substring(0, 1);
			String cvdResponseCode = receipt.getCvdResultCode().substring(1, 2);
			log.info("CVD Response : " + indicator + " / " + cvdResponseCode);
			if(indicator.equals("1")){ //CVD verification was requested in preauth
				if(!cvdResponseCode.equalsIgnoreCase("M")){
					log.error("CVD verification failed!");
					if(responseCode<50) //charge was successful, but we need to cancel
						voidCharge(receipt.getReceiptId(),receipt.getTxnNumber());
					response.setResult(-1);
					return;
				}
			}
		}
		//parse the AVS response
//		if(receipt.getAvsResultCode()!=null && receipt.getAvsResultCode().length()>0){
//			String avsResult = receipt.getAvsResultCode();
//			if(avsResult==null){
//				log.error("AVS verification failed!");
//				response.setResult(-1);
//				return;				
//			}	
//			boolean match = false;
//			for(String s: acceptableAVSCodes){
//				if(s.equalsIgnoreCase(avsResult)){
//					match = true;
//					break;
//				}
//			}
//			if(match==false){
//				log.error("AVS verification failed!");
//				response.setResult(-1);
//				return;				
//			}	
//			
//		}
		

		if (responseCode < 50) {
			response.setResult(1); //success
		} else if (responseCode >= 50) {
			response.setResult(-1); //failure
		}
	}
  
	private void processResolverResponse(JavaAPI.ResolverReceipt receipt,
			CCRequestObject request, CCResponseObject response) {
		response.setAuthNumber(receipt.getAuthCode());
		response.setResponseCode(receipt.getResponseCode());
		response.setProcessorTranId(receipt.getTxnNumber());
		response.setOrderId(receipt.getReceiptId());
		response.setReferenceNumber(receipt.getReferenceNum());
		response.setProcMessage(receipt.getMessage().trim());

		log.debug("CardType = " + receipt.getCardType());
		log.debug("TransAmount = " + receipt.getTransAmount());
		log.debug("TxnNumber = " + receipt.getTxnNumber());
		log.debug("ReceiptId = " + receipt.getReceiptId());
		log.debug("TransType = " + receipt.getTransType());
		log.debug("ReferenceNum = " + receipt.getReferenceNum());
		log.debug("ResponseCode = " + receipt.getResponseCode());
		log.debug("ISO = " + receipt.getISO());
		log.debug("Message = " + receipt.getMessage());
		log.debug("AuthCode = " + receipt.getAuthCode());
		log.debug("Complete = " + receipt.getComplete());
		log.debug("TransDate = " + receipt.getTransDate());
		log.debug("TransTime = " + receipt.getTransTime());
		log.debug("TimedOut = " + receipt.getTimedOut());
		log.info("CVD Result Code = " + receipt.getCvdResultCode());
		/*log.info("AVS Result Code = " + receipt.getAvsResultCode());*/

		if ((receipt.getResponseCode().equalsIgnoreCase("NULL"))
				|| (receipt.getResponseCode() == null)) {
			if (receipt.getMessage().trim().equalsIgnoreCase(
					DUPLICATE_ORDER_ID_MESSAGE)) {
				log.warn("Duplicate Reference number / order id");
				response.setResponseCode(DUPLICATE_ORDER_ID_MESSAGE_CODE);
			} else if (receipt.getMessage().trim().equalsIgnoreCase(
					API_TOKEN_MISMATCH_MESSAGE)) {
				log.warn("Error in API token set up");
				response.setResponseCode(new String(API_TOKEN_MISMATCH_CODE));
			}
			try {
				log.error("Message from Moneris server is : "
						+ receipt.getMessage().trim());
			} catch (Exception e) {
				log.error("No message available from Moneris server");
				throw new CreditCardAuthorizationException(
						"Error Communicating with Moneris server : No message!");
			}
			throw new CreditCardAuthorizationException(
					"Error Communicating with Moneris server : "
							+ receipt.getMessage());
		}

		try {
			Float transactionAmount = null;

			if (!receipt.getTransAmount().equalsIgnoreCase("null"))
				transactionAmount = (new Float(receipt.getTransAmount()));
			if (transactionAmount != null)
				response.setAmount((new Float(receipt.getTransAmount()))
						.floatValue());
			else
				response.setAmount(0.0f);
		} catch (NumberFormatException ne) {
			response.setAmount(request.getAmount());
		}

		response.setDateAuthorized(new Date());
		int responseCode = new Integer(response.getResponseCode()).intValue();


		if (responseCode < 50) {
			response.setResult(1); //success
		} else if (responseCode >= 50) {
			response.setResult(-1); //failure
		}
	}

 
	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
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
