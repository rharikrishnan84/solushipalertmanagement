/**
 * 
 */
package com.meritconinc.shiplinx.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.meritconinc.mmr.utilities.StringUtil;

/**
 * @author Rizwan Merchant
 *
 */

public class CreditCard {
	private Long id;
	private String ccNumber;
	private String ccExpiryMonth;
	private String ccExpiryYear;
	private Date dateCreated;
	private boolean active;
	private String cvd;
	private String ccLast4;
	private String procProfileID;
	private String procProfileID2;
	private long customerId;
	private long billingAddressId;
	private Address billingAddress;
	 
	private Customer customer;
    
	public CreditCard(){
		billingAddress = new Address();
		dateCreated = new Date();
	}
	
 	public CreditCard(Long id, String ccNumber, String ccLast4, String ccExpiryMonth, 
			String ccExpiryYear, Date dateCreated, boolean active, String procProfileID, String procProfileID2, long billingAddressId, long customerId) {
		this.id = id;
		this.ccNumber = ccNumber;
		this.ccLast4 = ccLast4;
		this.ccExpiryMonth = ccExpiryMonth;
		this.ccExpiryYear = ccExpiryYear;
		this.dateCreated = dateCreated;
		this.active = active;
		this.procProfileID = procProfileID;
		this.procProfileID2 = procProfileID2;
		this.billingAddressId = billingAddressId;
		this.customerId = customerId;
	}

	/**
	 * @return Returns the active.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active The active to set.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return Returns the ccExpiryMonth.
	 */
	public String getCcExpiryMonth() {
		return ccExpiryMonth;
	}

	/**
	 * @param ccExpiryMonth The ccExpiryMonth to set.
	 */
	public void setCcExpiryMonth(String ccExpiryMonth) {
		this.ccExpiryMonth = ccExpiryMonth;
	}

	/**
	 * @return Returns the ccExpiryYear.
	 */
	public String getCcExpiryYear() {
		return ccExpiryYear;
	}

	/**
	 * @param ccExpiryYear The ccExpiryYear to set.
	 */
	public void setCcExpiryYear(String ccExpiryYear) {
		this.ccExpiryYear = ccExpiryYear;
	}

	/**
	 * @return Returns the ccNumber.
	 */
	public String getCcNumber() {
		return ccNumber;
	}

	/**
	 * @param ccNumber The ccNumber to set.
	 */
	public void setCcNumber(String ccNumber) {
		this.ccNumber = ccNumber;
	}

	/**
	 * @return Returns the customerId.
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId The customerId to set.
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return Returns the dateCreated.
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated The dateCreated to set.
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCvd() {
		return cvd;
	}

	public void setCvd(String cvd) {
		this.cvd = cvd;
	}

	public String getMaskedCCNum(){
		
		String last4 = ccLast4;
		if(!StringUtil.isEmpty(last4))
			return  new String("*"+last4);
		int len = ccNumber.length();
		last4 = ccNumber.substring(len-4, len);
		return  new String("*"+last4);
	}
	
	public boolean isMasked(){
		if(ccNumber.indexOf("*")!=-1)
			return true;
		return false;
	}
	
	public String toString(){
		return (this.ccNumber + " " + this.ccExpiryMonth + " " + this.ccExpiryYear);
	}

	public String getCcLast4() {
		return ccLast4;
	}

	public void setCcLast4Digits(String ccLast4) {
		this.ccLast4 = ccLast4;
	}

	public String getProcProfileID() {
		return procProfileID;
	}

	public void setProcProfileID(String procProfileID) {
		this.procProfileID = procProfileID;
	}

	public String getProcProfileID2() {
		return procProfileID2;
	}

	public void setProcProfileID2(String procProfileID2) {
		this.procProfileID2 = procProfileID2;
	}

	public long getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(long billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public void setCcLast4(String ccLast4) {
		this.ccLast4 = ccLast4;
	}

	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



}
