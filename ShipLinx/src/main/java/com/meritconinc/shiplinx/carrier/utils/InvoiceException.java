package com.meritconinc.shiplinx.carrier.utils;

import java.util.ArrayList;
import java.util.List;

import com.meritconinc.shiplinx.exception.ShiplinxException;

public class InvoiceException extends ShiplinxException {

	List<String> errorMessages = new ArrayList<String>();
	
	
	public InvoiceException() {
		super();
	} 
	
	public InvoiceException(String msg) {
		super(msg);
		errorMessages.add(msg);
	}

	/**
	 * @return Returns the errorMessages.
	 */
	public List<String> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * @param errorMessages The errorMessages to set.
	 */
	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
}
