package com.meritconinc.shiplinx.carrier.ups.model;

public class UPSTariff {

	public Long  businessId;
	public Long serviceId;
	public Long packageId;
	public Double weightLB;
	public String zone;
	public Double rate;
	
	public UPSTariff(long serviceId, long packageId, Double weightlb, String zone){
		this.serviceId = serviceId;
		this.packageId = packageId;
		this.weightLB = weightlb;
		this.zone = zone;
	}
	
	public UPSTariff(){
		
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
	public Double getWeightLB() {
		return weightLB;
	}
	public void setWeightLB(Double weightLB) {
		this.weightLB = weightLB;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}

	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}

}
