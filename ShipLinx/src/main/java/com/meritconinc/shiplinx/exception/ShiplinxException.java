package com.meritconinc.shiplinx.exception;

import java.util.ArrayList;
import java.util.List;

public class ShiplinxException extends RuntimeException {

	protected List<String> errorMessages = new ArrayList<String>();
	
	public ShiplinxException(String string) {
		 super(string);
	}
	
	public ShiplinxException() {
		 super();
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
