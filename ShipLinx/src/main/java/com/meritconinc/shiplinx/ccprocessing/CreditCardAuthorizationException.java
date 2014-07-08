package com.meritconinc.shiplinx.ccprocessing;

import com.meritconinc.shiplinx.exception.ShiplinxException;


public class CreditCardAuthorizationException extends ShiplinxException {
	
	public CreditCardAuthorizationException() {
		super();
	}
	
	public CreditCardAuthorizationException(String msg) {
		super(msg);
	}
} 
