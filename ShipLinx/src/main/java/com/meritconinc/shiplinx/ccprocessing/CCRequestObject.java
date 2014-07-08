package com.meritconinc.shiplinx.ccprocessing;

import com.meritconinc.shiplinx.model.CCTransaction;
import com.meritconinc.shiplinx.model.CreditCard;

public class CCRequestObject {

	public CCRequestObject(CreditCard cc, double amount, String shortCode){
		this.creditCard = cc;
		this.amount = amount;
		this.shortCode = shortCode;
	}
	
	public CCRequestObject(){		
	}
	
	private CreditCard creditCard;
	private double amount;
	private CCTransaction cCTransaction;
	private String shortCode;
	 
	/**
	 * @return Returns the cCTransaction.
	 */
	public CCTransaction getCCTransaction() {
		return cCTransaction;
	}
	/**
	 * @param transaction The cCTransaction to set.
	 */
	public void setCCTransaction(CCTransaction transaction) {
		cCTransaction = transaction;
	}
	/**
	 * @return Returns the amount.
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * @param amount The amount to set.
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * @return Returns the creditCard.
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}
	/**
	 * @param creditCard The creditCard to set.
	 */
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	
	
	
}
