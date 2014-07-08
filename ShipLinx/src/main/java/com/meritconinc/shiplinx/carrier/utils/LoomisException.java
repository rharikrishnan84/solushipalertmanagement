package com.meritconinc.shiplinx.carrier.utils;

import com.meritconinc.shiplinx.exception.ShiplinxException;

public class LoomisException extends ShiplinxException {

	public LoomisException() {
		super();
	}
	
	public LoomisException(String msg) {
		super(msg);
		errorMessages.add(msg);
	} 

}
