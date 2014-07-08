package com.meritconinc.shiplinx.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ARTransaction {
 
	private Long arId;
	private Long invoiceId;
	private Double amount;
	private String modeOfPayment;
	private String paymentRefNum;
	private Timestamp paymentDate;
	private Long businessId;
	private Long customerId;
	private Long invoiceNum;
	private Customer customer;
	
	//search related, for web only
	private String fromTransactionDate_web;
	private String toTransactionDate_web;
	private Date fromTransactionDate;
	private Date toTransactionDate;

	
	private String paymentDate_web;
	
	public Long getArId() {
		return arId;
	}
	public void setArId(Long arId) {
		this.arId = arId;
	}
	public Long getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public String getPaymentRefNum() {
		return paymentRefNum;
	}
	public void setPaymentRefNum(String paymentRefNum) {
		this.paymentRefNum = paymentRefNum;
	}
	public Timestamp getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentDate_web() {
		return paymentDate_web;
	}
	public void setPaymentDate_web(String paymentDate_web) {
		this.paymentDate_web = paymentDate_web;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public String getFromTransactionDate_web() {
		return fromTransactionDate_web;
	}
	public void setFromTransactionDate_web(String fromTransactionDate_web) {
		this.fromTransactionDate_web = fromTransactionDate_web;
	}
	public String getToTransactionDate_web() {
		return toTransactionDate_web;
	}
	public void setToTransactionDate_web(String toTransactionDate_web) {
		this.toTransactionDate_web = toTransactionDate_web;
	}
	public Date getFromTransactionDate() {
		return fromTransactionDate;
	}
	public void setFromTransactionDate(Date fromTransactionDate) {
		this.fromTransactionDate = fromTransactionDate;
	}
	public Date getToTransactionDate() {
		return toTransactionDate;
	}
	public void setToTransactionDate(Date toTransactionDate) {
		this.toTransactionDate = toTransactionDate;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Long getInvoiceNum() {
				return invoiceNum;
			}
			public void setInvoiceNum(Long invoiceNum) {
				this.invoiceNum = invoiceNum;
			}
	
	
}
