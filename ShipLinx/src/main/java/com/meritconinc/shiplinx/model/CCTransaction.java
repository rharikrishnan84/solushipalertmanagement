package com.meritconinc.shiplinx.model;

import java.util.Date;

public class CCTransaction {
	private Long id;
	private long entityId;
	private int entityType;
	private String authNum;
	private double amount;
	private int status;
	private Date dateCreated;
	private String referenceNumber;
	private String processorTransactionId;
	private String procMessage;
	private String receiptId;
	private String cardNumCharged;
	private int chargeType;
	private long customerId;
	private String currency;
	private Long businessId;
	 
	
	public static final int DECLINED_STATUS = 10;
	public static final int AUTHORIZED_STATUS = 20;
	public static final int PROCESSED_STATUS = 30;
	public static final int VOID_STATUS = 40;
	public static final int REFUNDED_STATUS = 50;
	
	
	public static final int ENTITY_TYPE_ORDER = 10;
	public static final int ENTITY_TYPE_INVOICE = 20;
	
	public CCTransaction(String receiptId, String authNum, String referenceNumber, String procTranId, double amount, int status, String currency){
		this.receiptId = receiptId;
		this.authNum = authNum;
		this.referenceNumber = referenceNumber;
		this.processorTransactionId = procTranId;
		this.amount = amount;
		this.status = status;
		dateCreated = new Date();
		this.currency = currency;
	}
	
	public CCTransaction(){		
		dateCreated = new Date();
	}
	
		public String getStatusString() {
		switch(this.status) {
			case DECLINED_STATUS: return "DECLINED";
			case AUTHORIZED_STATUS: return "AUTHORIZED";
			case PROCESSED_STATUS: return "PROCESSED";
			case VOID_STATUS: return "VOID";
			case REFUNDED_STATUS: return "REFUNDED";
		}
		return "UNKNOWN";
	}
	
	public String getEntityTypeString() {
		switch(this.getEntityType()) {
			case ENTITY_TYPE_INVOICE: return "INVOICE";
		}
		return "UNKNOWN";
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getAuthNum() {
		return authNum;
	}
	public void setAuthNum(String authNum) {
		this.authNum = authNum;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return Returns the referenceNumber.
	 */
	public String getReferenceNumber() {
		return referenceNumber;
	}

	/**
	 * @param referenceNumber The referenceNumber to set.
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	/**
	 * @return Returns the processorTransactionId.
	 */
	public String getProcessorTransactionId() {
		return processorTransactionId;
	}

	/**
	 * @param processorTransactionId The processorTransactionId to set.
	 */
	public void setProcessorTransactionId(String processorTransactionId) {
		this.processorTransactionId = processorTransactionId;
	}

	/**
	 * @return Returns the procMessage.
	 */
	public String getProcMessage() {
		return procMessage;
	}

	/**
	 * @param procMessage The procMessage to set.
	 */
	public void setProcMessage(String procMessage) {
		this.procMessage = procMessage;
	}

	/**
	 * @return Returns the receiptId.
	 */
	public String getReceiptId() {
		return receiptId;
	}

	/**
	 * @param receiptId The receiptId to set.
	 */
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}

	/**
	 * @return Returns the cardNumCharged.
	 */
	public String getCardNumCharged() {
		return cardNumCharged;
	}

	/**
	 * @param cardNumCharged The cardNumCharged to set.
	 */
	public void setCardNumCharged(String cardNumCharged) {
		this.cardNumCharged = cardNumCharged;
	}

	public int getChargeType() {
		return chargeType;
	}

	public void setChargeType(int chargeType) {
		this.chargeType = chargeType;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return Returns the currency.
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency The currency to set.
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getEntityType() {
		return entityType;
	}

	public void setEntityType(int entity_type) {
		this.entityType = entity_type;
	}

	public long getEntityId() {
		return entityId;
	}

	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}

	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	
}
