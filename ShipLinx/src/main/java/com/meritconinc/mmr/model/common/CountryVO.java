package com.meritconinc.mmr.model.common;

import java.io.Serializable;

public class CountryVO  implements Serializable{

	private static final long serialVersionUID 		= 6102008;

	private String countryCode;
	private String countryName;
	
	public String getCountryCode() {
		return countryCode;
	}
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
