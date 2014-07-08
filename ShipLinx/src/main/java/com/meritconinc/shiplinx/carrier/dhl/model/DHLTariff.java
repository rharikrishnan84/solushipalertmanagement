package com.meritconinc.shiplinx.carrier.dhl.model;

import com.meritconinc.shiplinx.model.ShippingOrder;

public class DHLTariff {

	public Long  businessId;
	public Long serviceId;
	public Long packageId;
	public Double weightLB;
	private String zone; //this is used for export and import
	public String toZone; //this is used for third country
	public String fromZone; //this is used for third country
	private String transitCode; //this is used for third country
	public Double rateNonDoc;
	public Double rateDoc;
	public Double weightKG;
	private int shipmentType;
	
		
	public DHLTariff(long serviceId, long packageId, Double weightlb, Double weightkg, String fromZone, String toZone, int shipmentType){
		this.serviceId = serviceId;
		this.packageId = packageId;
		this.weightLB = weightlb;
		this.weightKG = weightkg;
		this.fromZone = fromZone;
		this.toZone = toZone;
		this.shipmentType = shipmentType;
	}
	 
	public DHLTariff(){
		
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
	public Double getRateNonDoc() {
		return rateNonDoc;
	}
	public void setRateNonDoc(Double rateNonDoc) {
		this.rateNonDoc = rateNonDoc;
	}
	public Double getRateDoc() {
		return rateDoc;
	}
	public void setRateDoc(Double rateDoc) {
		this.rateDoc = rateDoc;
	}
	public Double getWeightKG() {
		return weightKG;
	}
	public void setWeightKG(Double weightKG) {
		this.weightKG = weightKG;
	}

	public String getToZone() {
		return toZone;
	}
	public void setToZone(String toZone) {
		this.toZone = toZone;
	}

	public String getFromZone() {
		return fromZone;
	}
	public void setFromZone(String fromZone) {
		this.fromZone = fromZone;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public int getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(int shipmentType) {
		this.shipmentType = shipmentType;
	}

	public String getTransitCode() {
		return transitCode;
	}

	public void setTransitCode(String transitCode) {
		this.transitCode = transitCode;
	}
	
}
