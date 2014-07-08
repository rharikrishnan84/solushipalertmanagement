package com.meritconinc.shiplinx.carrier.utils;

import com.meritconinc.shiplinx.exception.ShiplinxException;

public class CarrierException extends ShiplinxException {

	public CarrierException() {
		super();
	}
	
	public CarrierException(String msg) {
		super(msg);
		errorMessages.add(msg);
	} 

}
