package com.meritconinc.shiplinx.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class Country {
	
	private String countryCode;
	private String msgId;
	private String countryName;
	
	
	
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	} 
	public String getCountryCode() {
		return countryCode;
	}
	/**
	 * @param countryCode The countryCode to set.
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	
}