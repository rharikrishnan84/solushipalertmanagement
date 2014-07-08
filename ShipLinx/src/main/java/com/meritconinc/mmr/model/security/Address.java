package com.meritconinc.mmr.model.security;

import java.io.Serializable;

import com.meritconinc.mmr.utilities.StringUtil;

public class Address implements Serializable {
	static final long serialVersionUID = 17092007;
	
	private int addressId;
	private String addressLine;
	private String city;
	private String stateProvince;
	private String countryCode;
	private String postalZip;
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getAddressLine() {
		return addressLine;
	} 
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountryCode() {
		if (StringUtil.isEmpty(countryCode)) {
			return "";
		} else {
			return countryCode;
		}
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getPostalZip() {
		return postalZip;
	}
	public void setPostalZip(String postalZip) {
		this.postalZip = postalZip;
	}
	public String getStateProvince() {
		return stateProvince;
	}
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}
}
