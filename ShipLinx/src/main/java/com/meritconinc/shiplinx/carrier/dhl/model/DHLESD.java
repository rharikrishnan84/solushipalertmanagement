package com.meritconinc.shiplinx.carrier.dhl.model;


public class DHLESD {

	private String countryCode;
	private String stateCode;
	private String stateName;
	private String IATACode;
	private String cityName; //this is used for export and import
	private String empty; //this is used for third country
	private String zipCodeStart; //this is used for third country
	private String zipCodeEnd; //this is used for third country
	private String irr;
	private String empty2;
	private String dhlServiceAreaCode;
	private String irr2;
	private String dayDefinite;
		
	public DHLESD(String countryCode, String stateCode, String stateName, String IATACode, 
			String cityName, String empty, String zipCodeStart, String zipCodeEnd, String irr,
			String empty2, String dhlServiceAreaCode, String irr2, String dayDefinite){
		 
		this.countryCode = countryCode;
		this.stateCode = stateCode;
		this.stateName = stateName;
		this.IATACode = IATACode;
		this.cityName = cityName;
		this.empty = empty;
		this.zipCodeStart = zipCodeStart;
		this.zipCodeEnd = zipCodeEnd;
		this.irr = irr;
		this.empty2 = empty2;
		this.dhlServiceAreaCode = dhlServiceAreaCode;
		this.irr2 = irr2;
		this.dayDefinite = dayDefinite;
		
	}
	
	public DHLESD(){
		
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getIATACode() {
		return IATACode;
	}

	public void setIATACode(String code) {
		IATACode = code;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getEmpty() {
		return empty;
	}

	public void setEmpty(String empty) {
		this.empty = empty;
	}

	public String getZipCodeStart() {
		return zipCodeStart;
	}

	public void setZipCodeStart(String zipCodeStart) {
		this.zipCodeStart = zipCodeStart;
	}

	public String getZipCodeEnd() {
		return zipCodeEnd;
	}

	public void setZipCodeEnd(String zipCodeEnd) {
		this.zipCodeEnd = zipCodeEnd;
	}

	public String getIrr() {
		return irr;
	}

	public void setIrr(String irr) {
		this.irr = irr;
	}

	public String getEmpty2() {
		return empty2;
	}

	public void setEmpty2(String empty2) {
		this.empty2 = empty2;
	}

	public String getDhlServiceAreaCode() {
		return dhlServiceAreaCode;
	}

	public void setDhlServiceAreaCode(String dhlServiceAreaCode) {
		this.dhlServiceAreaCode = dhlServiceAreaCode;
	}

	public String getIrr2() {
		return irr2;
	}

	public void setIrr2(String irr2) {
		this.irr2 = irr2;
	}

	public String getDayDefinite() {
		return dayDefinite;
	}

	public void setDayDefinite(String dayDefinite) {
		this.dayDefinite = dayDefinite;
	}
	
}
