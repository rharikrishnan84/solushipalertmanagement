package com.meritconinc.shiplinx.carrier.ups.model;


public class UPSCANEAS  {
	
	private String countryCode;
	private String fromPostal;
	private String toPostal;
	private String city;
	private double charge;
	private String postalCode;
	
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getFromPostal() {
		return fromPostal;
	} 
	public void setFromPostal(String fromPostal) {
		this.fromPostal = fromPostal;
	}
	public String getToPostal() {
		return toPostal;
	}
	public void setToPostal(String toPostal) {
		this.toPostal = toPostal;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public double getCharge() {
		return charge;
	}
	public void setCharge(double charge) {
		this.charge = charge;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
}

