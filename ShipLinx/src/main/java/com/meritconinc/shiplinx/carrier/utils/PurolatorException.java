package com.meritconinc.shiplinx.carrier.utils;

import com.meritconinc.shiplinx.exception.ShiplinxException;

public class PurolatorException extends ShiplinxException {

	public PurolatorException() {
		super();
	}
	
	public PurolatorException(String msg) {
		super(msg);
		errorMessages.add(msg);
	}
 
}
