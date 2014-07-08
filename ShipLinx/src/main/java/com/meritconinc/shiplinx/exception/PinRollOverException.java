package com.meritconinc.shiplinx.exception;


public class PinRollOverException extends ShiplinxException {
	Throwable rootCause_ = null;
	  
	 /**
	   * Constructor for InvalidCSVDataException.
	   */
	public PinRollOverException() {
		super();
	}

	/**
	 * Constructor for PinRollOverException.
	 * @param message
	 */
	public PinRollOverException(String message) {
		super(message);
	} 

	/**
	 * Constructor for PinRollOverException.
	 * @param message
	 * @param cause
	 */
	public PinRollOverException(String message, Throwable cause) {
		super(message);
   	rootCause_ = cause;
	}

	/**
	 * Constructor for PinRollOverException.
	 * @param cause
	 */
	public PinRollOverException(Throwable cause) {
		rootCause_ = cause;
	}

	public Throwable getCause() {
		return rootCause_;
	}
}