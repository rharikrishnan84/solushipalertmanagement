package com.meritconinc.shiplinx.carrier.midland.model;

public class MidlandRate {

	public long serviceId;
	public String fromZone;
	public String toZone;
	public double baseRate;
	public double perPoundTier1;
	public double perPoundTier2; 

	public MidlandRate(){}
	
	public MidlandRate(long serviceId, String fromZone, String toZone){
		this.serviceId = serviceId;
		this.fromZone = fromZone;
		this.toZone = toZone;
	}

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
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

	public double getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(double baseRate) {
		this.baseRate = baseRate;
	}

	public double getPerPoundTier1() {
		return perPoundTier1;
	}

	public void setPerPoundTier1(double perPoundTier1) {
		this.perPoundTier1 = perPoundTier1;
	}

	public double getPerPoundTier2() {
		return perPoundTier2;
	}

	public void setPerPoundTier2(double perPoundTier2) {
		this.perPoundTier2 = perPoundTier2;
	}
	
	
	
}
