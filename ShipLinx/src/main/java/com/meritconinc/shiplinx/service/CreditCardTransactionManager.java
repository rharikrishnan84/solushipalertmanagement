package com.meritconinc.shiplinx.service;

import java.util.List;

import com.meritconinc.shiplinx.model.CCTransaction;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.Customer;

public interface CreditCardTransactionManager {
	
	public CCTransaction getCCTransaction(String id);
	public void saveTransaction(CCTransaction trans);
	public void updateTransaction(CCTransaction trans);
	public List<CCTransaction> searchCCTransactions(CCTransaction ccTran);

	public CCTransaction authorizeCard(double amount, CreditCard cc, long businessId, String currency) throws Exception;
	public CCTransaction authorizeCard(double amount, CreditCard cc, Customer customer) throws Exception;
	public void processPostAuth(CCTransaction t, Customer customer) throws Exception;
	public boolean voidCharge(CCTransaction t, Customer customer) throws Exception;
	public CCTransaction chargeCard(CCTransaction t, double amount, CreditCard cc, String currency, Customer customer) throws Exception;
	public boolean refundCharge(CCTransaction t, Customer customer) throws Exception;
	 
	public boolean addCreditCard(CreditCard cc, Customer customer);
	public boolean updateCreditCard(CreditCard cc, Customer customer);	
}
