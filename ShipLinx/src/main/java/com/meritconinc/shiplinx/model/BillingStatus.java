package com.meritconinc.shiplinx.model;

import java.io.Serializable;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class BillingStatus implements Serializable{

	private static final Logger log = LogManager.getLogger(Address.class);
	private Long billingStatusId;
	private String billingStatusText;
	
	public BillingStatus()
	{ 
	
	}
	public BillingStatus(long i, String n) {
		billingStatusId = i;
		billingStatusText = n;
	}
	public Long getBillingStatusId() {
		return billingStatusId;
	}
	public void setBillingStatusId(Long billingStatusId) {
		this.billingStatusId = billingStatusId;
	}
	public String getBillingStatusText() {
		return billingStatusText;
	}
	public void setBillingStatusText(String billingStatusText) {
		this.billingStatusText = billingStatusText;
	}
	

	
	
}
