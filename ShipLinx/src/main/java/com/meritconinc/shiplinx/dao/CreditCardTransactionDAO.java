package com.meritconinc.shiplinx.dao;

import java.util.List;

import com.meritconinc.shiplinx.model.CCTransaction;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.MerchantAccount;

public interface CreditCardTransactionDAO {

	public CCTransaction getCCTransaction(Long id);
	public Long saveCCTransaction(CCTransaction trans);
	public List<CCTransaction> searchCCTransactions(CCTransaction ccTran);	
	public MerchantAccount getMerchantAccount(long businessId, String currency);
	public void updateCCTransaction(CCTransaction trans);
	
	public Long addCreditCard(CreditCard creditCard);
	public void updateCreditCard(CreditCard creditCard);
} 
