package com.meritconinc.shiplinx.carrier.utils;

import com.meritconinc.shiplinx.exception.ShiplinxException;

public class UPSException extends ShiplinxException {

	public UPSException() {
		super();
	}
	
	public UPSException(String msg) {
		super(msg);
		errorMessages.add(msg);
	}

} 
