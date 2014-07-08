package com.meritconinc.shiplinx.exception;


public class PinBlockException extends ShiplinxException {
	Throwable rootCause_ = null;
	  
	 /**
	   * Constructor for InvalidCSVDataException.
	   */
	public PinBlockException() {
		super();
	}

	/**
	 * Constructor for PinBlockException.
	 * @param message
	 */
	public PinBlockException(String message) {
		super(message);
	}
 
	/**
	 * Constructor for PinBlockException.
	 * @param message
	 * @param cause
	 */
	public PinBlockException(String message, Throwable cause) {
		super(message);
   	rootCause_ = cause;
	}

	/**
	 * Constructor for PinBlockException.
	 * @param cause
	 */
	public PinBlockException(Throwable cause) {
		rootCause_ = cause;
	}

	public Throwable getCause() {
		return rootCause_;
	}
}