package com.meritconinc.shiplinx.carrier.loomis.model;

public class LoomisTariff {

	public Long serviceId;
	public Long packageId;
	public Double weight;
	public String transitCode;
	public Double rate;
	public String fromPostalCode;
	public String toPostalCode;
	public String fromZone;
	public String toZone; 
	
	public LoomisTariff(long serviceId, long packageId, Double weight, String transit_code, String from_postal_code, String to_postal_code, String from_zone, String to_zone){
		this.serviceId = serviceId;
		this.packageId = packageId;
		this.weight = weight;
		this.transitCode = transit_code;
		this.fromPostalCode = from_postal_code;
		this.toPostalCode = to_postal_code;
		this.fromZone = from_zone;
		this.toZone = to_zone;		
	}
	
	public LoomisTariff(){}

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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getTransitCode() {
		return transitCode;
	}

	public void setTransitCode(String transitCode) {
		this.transitCode = transitCode;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getFromPostalCode() {
		return fromPostalCode;
	}

	public void setFromPostalCode(String fromPostalCode) {
		this.fromPostalCode = fromPostalCode;
	}

	public String getToPostalCode() {
		return toPostalCode;
	}

	public void setToPostalCode(String toPostalCode) {
		this.toPostalCode = toPostalCode;
	}

	public String getFromZone() {
		return fromZone;
	}

	public void setFromZone(String fromZone) {
		this.fromZone = fromZone;
	}

	public String getToZone() {
		return toZone;
	}

	public void setToZone(String toZone) {
		this.toZone = toZone;
	}
	
	
	
	
}
