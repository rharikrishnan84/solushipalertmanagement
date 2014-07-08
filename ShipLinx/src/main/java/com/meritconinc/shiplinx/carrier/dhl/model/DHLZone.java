package com.meritconinc.shiplinx.carrier.dhl.model;

public class DHLZone {

	private String  zoneCode;
	private String countryCode;
	private String countryName;
	private String weightUnit;
	private String dimUnit;
	private String region;
	
	public DHLZone(){
		
	}
	
	public DHLZone(String country_code){
		this.countryCode = country_code;
	}
	
	public String getZoneCode() {
		return zoneCode;
	}
	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
 
	public String getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(String weightUnit) {
		this.weightUnit = weightUnit;
	}

	public String getDimUnit() {
		return dimUnit;
	}

	public void setDimUnit(String dimUnit) {
		this.dimUnit = dimUnit;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	
}
