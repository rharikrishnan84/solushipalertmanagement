package com.meritconinc.shiplinx.model;

import java.util.List;

import com.meritconinc.shiplinx.utils.CarrierErrorMessage;

public class BatchShipmentResponse {
	private ShippingOrder order;
	private List<CarrierErrorMessage> errors;
	public ShippingOrder getOrder() {
		return order;
	} 
	public void setOrder(ShippingOrder order) {
		this.order = order;
	}
	public List<CarrierErrorMessage> getErrors() {
		return errors;
	}
	public void setErrors(List<CarrierErrorMessage> errors) {
		this.errors = errors;
	}
}
