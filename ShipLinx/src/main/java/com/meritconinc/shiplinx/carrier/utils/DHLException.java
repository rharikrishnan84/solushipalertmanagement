package com.meritconinc.shiplinx.carrier.utils;

import com.meritconinc.shiplinx.exception.ShiplinxException;

public class DHLException extends ShiplinxException {

	public DHLException() {
		super();
	}
	
	public DHLException(String msg) {
		super(msg);
		errorMessages.add(msg);
	}

} 
