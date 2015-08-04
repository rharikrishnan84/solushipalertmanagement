package com.meritconinc.mmr.model.admin;

public class EcommerceLog {

	private long ecommerceLogId;
	private long shopifyOrderId;
	private long customerId;
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getShopifyOrderId() {
		return shopifyOrderId;
	}
	public void setShopifyOrderId(long shopifyOrderId) {
		this.shopifyOrderId = shopifyOrderId;
	}
	public long getEcommerceLogId() {
		return ecommerceLogId;
	}
	public void setEcommerceLogId(long ecommerceLogId) {
		this.ecommerceLogId = ecommerceLogId;
	}
	
}
