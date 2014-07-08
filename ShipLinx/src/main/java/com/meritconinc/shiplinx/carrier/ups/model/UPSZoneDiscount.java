package com.meritconinc.shiplinx.carrier.ups.model;

public class UPSZoneDiscount {

	public Long  businessId;
	public Long serviceId;
	public Long packageId;
	public Double discountPerc;
	public String zone;
	public String accountCountry;
	private String serviceCode;
	
	public UPSZoneDiscount(long businessId, long serviceId, String serviceCode, long packageId, String accountCountry, String zone){
		this.serviceId = serviceId;
		this.packageId = packageId;
		this.businessId = businessId;
		this.zone = zone;
		this.accountCountry = accountCountry;
		this.serviceCode = serviceCode;
	}
	
	public UPSZoneDiscount(){
		
	} 
	
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public Long getPackageId() {
		return packageId;
	}
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}

	public Double getDiscountPerc() {
		return discountPerc;
	}

	public void setDiscountPerc(Double discountPerc) {
		this.discountPerc = discountPerc;
	}

	public String getAccountCountry() {
		return accountCountry;
	}

	public void setAccountCountry(String accountCountry) {
		this.accountCountry = accountCountry;
	}

	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}


}
