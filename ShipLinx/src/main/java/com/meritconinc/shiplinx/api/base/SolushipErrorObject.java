package com.meritconinc.shiplinx.api.base;

import java.io.Serializable;

public class SolushipErrorObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4333454783431821132L;
	
	private Integer statusCode;
	
	private String message;
	
	private Object data;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
