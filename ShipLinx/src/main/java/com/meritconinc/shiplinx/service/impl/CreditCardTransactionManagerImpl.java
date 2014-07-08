package com.meritconinc.shiplinx.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.meritconinc.mmr.exception.CardProcessingException;
import com.meritconinc.shiplinx.ccprocessing.CCProcessorFactory;
import com.meritconinc.shiplinx.ccprocessing.CCRequestObject;
import com.meritconinc.shiplinx.ccprocessing.CCResponseObject;
import com.meritconinc.shiplinx.ccprocessing.CreditCardAuthorizationException;
import com.meritconinc.shiplinx.ccprocessing.ICreditCardProcessor;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.CreditCardTransactionDAO;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.CCTransaction;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.MerchantAccount;
import com.meritconinc.shiplinx.service.CreditCardTransactionManager;
import com.meritconinc.shiplinx.service.PinBlockManager;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class CreditCardTransactionManagerImpl implements CreditCardTransactionManager {

	private static Logger logger = Logger.getLogger(CreditCardTransactionManagerImpl.class);
	
	private CreditCardTransactionDAO ccTransactionDAO;
	private PinBlockManager pinBlockManager;
	private BusinessDAO businessDAO;
	private AddressDAO addressDAO;

	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	} 

	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public void setPinBlockManager(PinBlockManager mgr){
		this.pinBlockManager = mgr;
	}

	public void setCcTransactionDAO(CreditCardTransactionDAO ccTransactionDAO) {
		this.ccTransactionDAO = ccTransactionDAO;
	}

	public CCTransaction getCCTransaction(String id) {
		return ccTransactionDAO.getCCTransaction(Long.valueOf(id));
	}

	public void saveTransaction(CCTransaction trans) {
		ccTransactionDAO.saveCCTransaction(trans);
	}

	public void updateTransaction(CCTransaction trans) {
		ccTransactionDAO.updateCCTransaction(trans);
	}

	public List<CCTransaction> searchCCTransactions(CCTransaction ccTran){
		return ccTransactionDAO.searchCCTransactions(ccTran);
	}

	public boolean addCreditCard(CreditCard cc, Customer customer){
		
		Business business = businessDAO.getBusiessById(customer.getBusinessId());

		CCRequestObject cc_request = new CCRequestObject(cc, 0, business.getShortCode());
		MerchantAccount ma = ccTransactionDAO.getMerchantAccount(customer.getBusinessId(), business.getDefaultCurreny());
		ICreditCardProcessor processor = CCProcessorFactory.getProcessor(ma);	
		long[] pins = pinBlockManager.getNewPinNumbers(ma.getProcessor(), 1, customer.getBusinessId());

		boolean cardVerified = processor.verifyCard(cc_request, pins[0]);
		if(ShiplinxConstants.isTestMode())
			cardVerified = true;
		if(!cardVerified)
			return cardVerified;
		
		boolean cardAddedToProc = processor.addCard(cc, customer);
		
		if(!cardAddedToProc)
			return cardAddedToProc;
		
		int len = cc.getCcNumber().length();
		String last4 = cc.getCcNumber().substring(len-4, len);
		CreditCard ccToAdd = new CreditCard(cc.getId(), null, last4, cc.getCcExpiryMonth(), cc.getCcExpiryYear(), new Date(), true, cc.getProcProfileID(), cc.getProcProfileID2(), cc.getBillingAddressId(), cc.getCustomerId());
		
		//Make current creditcards for this customer inactive
		CreditCard ccCurrent = new CreditCard();
		ccCurrent.setCustomerId(cc.getCustomerId());
		ccCurrent.setActive(false);
		ccTransactionDAO.updateCreditCard(ccCurrent);
		
		//Now add the credit Card to our database if card was added to the processor
		ccTransactionDAO.addCreditCard(ccToAdd);
		
		return true;
	}

	
	public boolean updateCreditCard(CreditCard cc, Customer customer){
		
		Business business = businessDAO.getBusiessById(customer.getBusinessId());

		CCRequestObject cc_request = new CCRequestObject(cc, 0, business.getShortCode());
		MerchantAccount ma = ccTransactionDAO.getMerchantAccount(customer.getBusinessId(), business.getDefaultCurreny());
		ICreditCardProcessor processor = CCProcessorFactory.getProcessor(ma);	
		long[] pins = pinBlockManager.getNewPinNumbers(ma.getProcessor(), 1, customer.getBusinessId());

		boolean cardVerified = processor.verifyCard(cc_request, pins[0]);
		if(ShiplinxConstants.isTestMode())
			cardVerified = true;
		if(!cardVerified)
			return cardVerified;
		
		cc.setProcProfileID(customer.getCreditCardActive().getProcProfileID());
		cc.setProcProfileID2(customer.getCreditCardActive().getProcProfileID2());
		
		boolean cardAddedToProc = processor.updateCard(cc, customer);
		
		if(!cardAddedToProc)
			return cardAddedToProc;
		
		int len = cc.getCcNumber().length();
		String last4 = cc.getCcNumber().substring(len-4, len);
		CreditCard ccToAdd = new CreditCard(cc.getId(), null, last4, cc.getCcExpiryMonth(), cc.getCcExpiryYear(), new Date(), true, cc.getProcProfileID(), cc.getProcProfileID2(), cc.getBillingAddressId(), cc.getCustomerId());
		
		//Make current creditcards for this customer inactive
		CreditCard ccCurrent = new CreditCard();
		ccCurrent.setCustomerId(cc.getCustomerId());
		ccCurrent.setActive(false);
		ccTransactionDAO.updateCreditCard(ccCurrent);
		
		//Now add the credit Card to our database if card was added to the processor
		ccTransactionDAO.addCreditCard(ccToAdd);
		
		return true;
	}

	public CCTransaction authorizeCard(double amount, CreditCard cc, Customer customer) throws Exception{
		logger.info("Attempting to auth card for customer " + customer.getName() + ", amount " + amount + " , " + customer.getAddress().getCountryCode());
		CCTransaction transaction = null; 
		
		Business business = businessDAO.getBusiessById(customer.getBusinessId());
		String shortCode = business.getShortCode();
		if(shortCode == null)
			shortCode = "";
		
		CCRequestObject cc_request = new CCRequestObject(cc, amount, shortCode);
		CCResponseObject cc_response = null;
		
		String currency = ShiplinxConstants.CURRENCY_CA_STRING;
		if(customer.getAddress().getCountryCode().equalsIgnoreCase(ShiplinxConstants.US))
			currency = ShiplinxConstants.CURRENCY_US_STRING;
		else
			currency = ShiplinxConstants.CURRENCY_CA_STRING;

		MerchantAccount ma = ccTransactionDAO.getMerchantAccount(customer.getBusinessId(), currency);
		if(ma==null){
			logger.error("No merchant account found for business " + business.getName() + " and currency " + currency);
			throw new CardProcessingException("Could not charge credit card!");
		}
		ICreditCardProcessor processor = CCProcessorFactory.getProcessor(ma);		
		long[] pins = pinBlockManager.getNewPinNumbers(ma.getProcessor(), 1, customer.getBusinessId());

		cc.setCustomer(customer);
		
		//		if credit card was not authorized then return failure
		try{
			cc_response = processor.authorizeCard(cc_request, pins[0]);
		}
		catch(CreditCardAuthorizationException e){
//			e.printStackTrace();
			logger.error("Error authorizing card", e);
			transaction = new CCTransaction();
			transaction.setProcMessage(e.getMessage());
			transaction.setAuthNum("");
			transaction.setCustomerId(customer.getId());
			transaction.setBusinessId(customer.getBusinessId());
			saveTransaction(transaction);
			return transaction;
		}
//		cc was authorized or declined
		if(cc_response.getResult()==1)
			transaction = new CCTransaction(cc_response.getOrderId(), cc_response.getAuthNumber(),cc_response.getReferenceNumber(), cc_response.getProcessorTranId(), cc_response.getAmount(), CCTransaction.AUTHORIZED_STATUS, currency);
		else
			transaction = new CCTransaction(cc_response.getOrderId(), cc_response.getAuthNumber(),cc_response.getReferenceNumber(), cc_response.getProcessorTranId(), cc_response.getAmount(), CCTransaction.DECLINED_STATUS, currency);
		transaction.setCardNumCharged(cc.getMaskedCCNum());
		transaction.setProcMessage(cc_response.getProcMessage());		
		saveTransaction(transaction);
		return transaction;
	}

	public CCTransaction authorizeCard(double amount, CreditCard cc, long businessId, String currency) throws Exception{
		CCTransaction transaction = null; 
		
		Business business = businessDAO.getBusiessById(businessId);
		logger.info("Attempting to auth card for business " + business.getName() + ", amount " + amount + " , currency " + currency);
		String shortCode = business.getShortCode();
		if(shortCode == null)
			shortCode = "";
		
		CCRequestObject cc_request = new CCRequestObject(cc, amount, shortCode);
		CCResponseObject cc_response = null;
		
	
		MerchantAccount ma = ccTransactionDAO.getMerchantAccount(businessId, currency);
		if(ma==null){
			logger.error("No merchant account found for business " + business.getName() + " and currency " + currency);
			throw new CardProcessingException("Could not charge credit card!");
		}
		ICreditCardProcessor processor = CCProcessorFactory.getProcessor(ma);		
		long[] pins = pinBlockManager.getNewPinNumbers(ma.getProcessor(), 1, businessId);

		//		if credit card was not authorized then return failure
		try{
			cc_response = processor.authorizeCard(cc_request, pins[0]);
		}
		catch(CreditCardAuthorizationException e){
//			e.printStackTrace();
			logger.error("Error authorizing card", e);
			transaction = new CCTransaction();
			transaction.setProcMessage(e.getMessage());
			transaction.setAuthNum("");
			transaction.setCustomerId(0);
			transaction.setBusinessId(businessId);
			transaction.setStatus(CCTransaction.DECLINED_STATUS);
			saveTransaction(transaction);
			return transaction;
		}
//		cc was authorized or declined
		if(cc_response.getResult()==1)
			transaction = new CCTransaction(cc_response.getOrderId(), cc_response.getAuthNumber(),cc_response.getReferenceNumber(), cc_response.getProcessorTranId(), cc_response.getAmount(), CCTransaction.AUTHORIZED_STATUS, currency);
		else
			transaction = new CCTransaction(cc_response.getOrderId(), cc_response.getAuthNumber(),cc_response.getReferenceNumber(), cc_response.getProcessorTranId(), cc_response.getAmount(), CCTransaction.DECLINED_STATUS, currency);
		transaction.setCardNumCharged(cc.getMaskedCCNum());
		transaction.setProcMessage(cc_response.getProcMessage());		
		saveTransaction(transaction);
		return transaction;
	}
	
	
	public void processPostAuth(CCTransaction t, Customer customer) throws Exception{
		Business business = businessDAO.getBusiessById(customer.getBusinessId());
		String shortCode = business.getShortCode();
		if(shortCode == null)
			shortCode = "";

		CCRequestObject cc_request = new CCRequestObject();
		cc_request.setShortCode(shortCode);
		cc_request.setCCTransaction(t);
		cc_request.setAmount(t.getAmount());
		CCResponseObject cc_response = null;
	
		MerchantAccount ma = ccTransactionDAO.getMerchantAccount(customer.getBusinessId(), t.getCurrency());
		if(ma==null){
			logger.error("No merchant account found for business " + 1 + " and currency " + t.getCurrency());
			throw new CardProcessingException("Could not charge credit card!");
		}
		ICreditCardProcessor processor = CCProcessorFactory.getProcessor(ma);		

		logger.info("Attempting to charge card for amount " + t.getAmount() + " for customer " + customer.getName());
//		if credit card was not authorized then return failure
		try{
			cc_response = processor.processPostAuth(cc_request);
		}
		catch(CreditCardAuthorizationException e){
//			e.printStackTrace();
			logger.error("Internal error..unable to settle a previously authorized transaction!!", e);
			return;
		}

		if(cc_response.getResult()!=1){
			logger.error("Internal error..unable to settle a previously authorized transaction!!");
			logger.error("Message from Moneris processor: " + cc_response.getProcMessage());
			return;			
		}
		
		//settled		
		t.setProcessorTransactionId(cc_response.getProcessorTranId());
		t.setStatus(CCTransaction.PROCESSED_STATUS);
		if(customer.getId()!=0)
			t.setCustomerId(customer.getId());
		saveTransaction(t);
	}

	public CCTransaction chargeCard(CCTransaction transaction, double amount, CreditCard cc, String currency, Customer customer) throws Exception{

		logger.info("Attempting to charge card for customer " + customer.getName() + ", amount " + amount + " , " + currency);
		
		Business business = businessDAO.getBusiessById(customer.getBusinessId());
		String shortCode = business.getShortCode();
		if(shortCode == null)
			shortCode = "";
		
		CCRequestObject cc_request = new CCRequestObject(cc, amount, shortCode);
		CCResponseObject cc_response = null;
		
		MerchantAccount ma = ccTransactionDAO.getMerchantAccount(customer.getBusinessId(), currency);
		if(ma==null){
			logger.error("No merchant account found for business " + business.getName() + " and currency " + currency);
			throw new CardProcessingException("Could not charge credit card, no merchant account found!");
		}
		ICreditCardProcessor processor = CCProcessorFactory.getProcessor(ma);		
		long[] pins = pinBlockManager.getNewPinNumbers(ma.getProcessor(), 1, customer.getBusinessId());
		
		//if credit card was not charged then return failure
		try{
			cc_response = processor.processCharge(cc_request, pins[0]);
		}
		catch(CreditCardAuthorizationException e){
//			e.printStackTrace();
			logger.error("Error charging card: <" + e.getMessage() + ">");
			logger.debug(e.getMessage(), e.getCause());
			transaction = new CCTransaction();
			transaction.setCustomerId(customer.getId());
			transaction.setBusinessId(business.getId());
			transaction.setProcMessage(e.getMessage());
			transaction.setAuthNum("");
			saveTransaction(transaction);
			return transaction;
		}
		
		transaction.setReceiptId(cc_response.getOrderId());
		transaction.setAuthNum(cc_response.getAuthNumber());
		transaction.setReferenceNumber(cc_response.getReferenceNumber());
		transaction.setProcessorTransactionId(cc_response.getProcessorTranId());
		transaction.setAmount(cc_response.getAmount());
		transaction.setCurrency(currency);
//		cc was authorized or declined
		if(cc_response.getResult()==1)
			transaction.setStatus(CCTransaction.PROCESSED_STATUS);
		else
			transaction.setStatus(CCTransaction.DECLINED_STATUS);
		
		transaction.setCardNumCharged(cc.getMaskedCCNum());
		transaction.setProcMessage(cc_response.getProcMessage());	
		transaction.setCustomerId(customer.getId());
		saveTransaction(transaction);
//		email receipt to customer
//		emailReceipt(transaction, customer);
		return transaction;
	}


	public boolean voidCharge(CCTransaction t, Customer customer) throws Exception{

		Business business = businessDAO.getBusiessById(customer.getBusinessId());
		String shortCode = business.getShortCode();
		if(shortCode == null)
			shortCode = "";
		CCRequestObject cc_request = new CCRequestObject();
		cc_request.setShortCode(shortCode);
		cc_request.setCCTransaction(t);
		CCResponseObject cc_response = null;
		logger.info("Attempting to void card for amount " + t.getAmount() + " for customer " + customer.getName());

		MerchantAccount ma = ccTransactionDAO.getMerchantAccount(customer.getBusinessId(), t.getCurrency());
		if(ma==null){
			logger.error("No merchant account found for business " + 1 + " and currency " + t.getCurrency());
			throw new CardProcessingException("Could not charge credit card!");
		}
		ICreditCardProcessor processor = CCProcessorFactory.getProcessor(ma);		

		//		if credit card was not authorized then return failure
		try{
			cc_response = processor.voidCharge(cc_request);
		}
		catch(CreditCardAuthorizationException e){
//			e.printStackTrace();
			logger.error("Internal error..unable to void a previously charged transaction!!", e);
			//attempt to refund the transaction
			boolean refunded = refundCharge(t, customer);
			return refunded;
		}

		if(cc_response.getResult()!=1){
			logger.error("Internal error..unable to void a previously charged transaction!!");
			logger.error("Message from Moneris processor: " + cc_response.getProcMessage());
			//attempt to refund the transaction
			boolean refunded = refundCharge(t, customer);
			return refunded;			
		}
		
		//voided
		t.setReferenceNumber(cc_response.getReferenceNumber());
		t.setStatus(CCTransaction.VOID_STATUS);
		updateTransaction(t);	
		return true;
	}
	
	public boolean refundCharge(CCTransaction t, Customer customer) throws Exception{

		Business business = businessDAO.getBusiessById(customer.getBusinessId());
		String shortCode = business.getShortCode();
		if(shortCode == null)
			shortCode = "";
		CCRequestObject cc_request = new CCRequestObject();
		cc_request.setShortCode(shortCode);
		cc_request.setCCTransaction(t);
		cc_request.setAmount(t.getAmount());
		CCResponseObject cc_response = null;

		MerchantAccount ma = ccTransactionDAO.getMerchantAccount(customer.getBusinessId(), t.getCurrency());
		if(ma==null){
			logger.error("No merchant account found for business " + 1 + " and currency " + t.getCurrency());
			throw new CardProcessingException("Could not charge credit card!");
		}
		ICreditCardProcessor processor = CCProcessorFactory.getProcessor(ma);		

		logger.info("Attempting to refund card for amount " + t.getAmount() + " for customer " + customer.getName());
//		if credit card was not authorized then return failure
		try{
			cc_response = processor.refundCharge(cc_request);
		}
		catch(CreditCardAuthorizationException e){
//			e.printStackTrace();
			logger.error("Internal error..unable to refund a previously charged transaction!!", e);
			return false;
		}

		if(cc_response.getResult()!=1){
			logger.error("Internal error..unable to refund a previously charged transaction!!");
			logger.error("Message from processor: " + cc_response.getProcMessage());
			return false;			
		}
		
		//refunded
		t.setReferenceNumber(cc_response.getReferenceNumber());
		t.setStatus(CCTransaction.REFUNDED_STATUS);
		updateTransaction(t);	
		return true;
	}
	
}
