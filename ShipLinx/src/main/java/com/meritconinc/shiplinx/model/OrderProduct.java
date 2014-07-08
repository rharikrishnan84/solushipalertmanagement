package com.meritconinc.shiplinx.model;

public class OrderProduct 
{
	private long orderId;
	private long productId;
	private long orderedQuantity;
	private long fulfilledQuantity;
	
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public long getOrderedQuantity() {
		return orderedQuantity;
	}
	public void setOrderedQuantity(long orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	} 
	public long getFulfilledQuantity() {
		return fulfilledQuantity;
	}
	public void setFulfilledQuantity(long fulfilledQuantity) {
		this.fulfilledQuantity = fulfilledQuantity;
	}
	
	
	
	

}
