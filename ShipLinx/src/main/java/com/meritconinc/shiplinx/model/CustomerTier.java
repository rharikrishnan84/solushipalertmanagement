package com.meritconinc.shiplinx.model;

public class CustomerTier {
	
	private long businessId;
	private long CustomerId;
	private double fromSpend;
	private double toSpend;
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public long getCustomerId() {
		return CustomerId;
	}
	public void setCustomerId(long customerId) {
		CustomerId = customerId;
	}
	public double getFromSpend() {
		return fromSpend;
	}
	public void setFromSpend(double fromSpend) {
		this.fromSpend = fromSpend;
	} 
	public double getToSpend() {
		return toSpend;
	}
	public void setToSpend(double toSpend) {
		this.toSpend = toSpend;
	}
	
	
	

}
