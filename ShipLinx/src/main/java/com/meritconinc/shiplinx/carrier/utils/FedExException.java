package com.meritconinc.shiplinx.carrier.utils;

import com.meritconinc.shiplinx.exception.ShiplinxException;

public class FedExException extends ShiplinxException {

	public FedExException() {
		super();
	}
	
	public FedExException(String msg) {
		super(msg);
		errorMessages.add(msg);
	}
	
} 
