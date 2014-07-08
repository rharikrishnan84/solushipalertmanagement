package com.meritconinc.shiplinx.carrier.ups.model;

public class UPSAccCharges {

	public long  businessId;
	public String chargeCode;
	public String chargeCodeLevel2;
	public float tariff;
	public float cost;
	public float charge;
	public boolean perPackage;
	public float minCharge;
	public float maxCharge;
	public String countryCode;
	public float perUnits;
	
	public UPSAccCharges(){} 
	
	public UPSAccCharges(long business_id, String charge_code, String charge_code_level2, String country_code){
		this.businessId = business_id;
		this.chargeCode = charge_code;
		this.chargeCodeLevel2 = charge_code_level2;
		this.countryCode = country_code;
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

	public float getTariff() {
		return tariff;
	}
	public void setTariff(float tariff) {
		this.tariff = tariff;
	}

	public float getPerUnits() {
		return perUnits;
	}
	public void setPerUnits(float perUnits) {
		this.perUnits = perUnits;
	}
	
	
}
