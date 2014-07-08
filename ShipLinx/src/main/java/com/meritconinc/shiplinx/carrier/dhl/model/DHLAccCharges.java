package com.meritconinc.shiplinx.carrier.dhl.model;

public class DHLAccCharges {

	public long  businessId;
	public String chargeCode;
	public String chargeCodeLevel2;
	public float cost;
	public float charge;
	public boolean perPackage;
	public float minCharge;
	public float maxCharge;
	public String countryCode;
	
	public DHLAccCharges(long businessId, String chargeCode, String chargeCodeLevel2, String countryCode){
		this.businessId = businessId;
		this.chargeCode = chargeCode;
		this.chargeCodeLevel2 = chargeCodeLevel2;
		this.countryCode = countryCode;
	}
	 
	public DHLAccCharges(){
	}
		public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public String getChargeCode() {
		return chargeCode;
	}
	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}
	public String getChargeCodeLevel2() {
		return chargeCodeLevel2;
	}
	public void setChargeCodeLevel2(String chargeCodeLevel2) {
		this.chargeCodeLevel2 = chargeCodeLevel2;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getCharge() {
		return charge;
	}
	public void setCharge(float charge) {
		this.charge = charge;
	}
	public boolean isPerPackage() {
		return perPackage;
	}
	public void setPerPackage(boolean perPackage) {
		this.perPackage = perPackage;
	}
	public float getMaxCharge() {
		return maxCharge;
	}
	public void setMaxCharge(float maxCharge) {
		this.maxCharge = maxCharge;
	}
	public float getMinCharge() {
		return minCharge;
	}
	public void setMinCharge(float minCharge) {
		this.minCharge = minCharge;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	
}
