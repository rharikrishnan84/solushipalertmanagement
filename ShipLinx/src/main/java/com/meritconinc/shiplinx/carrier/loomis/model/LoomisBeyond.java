package com.meritconinc.shiplinx.carrier.loomis.model;

public class LoomisBeyond {

	public String fromPostalCode;
	public String toPostalCode;
	public String priceZone;
	public String zoneDesc;
	public String zoneType;
	public String mainZone;
	public String field7;
	public String beyondZone;
	public String srvCode;
	public Float interProvBase;
	public Float interProvOverWeight; 
	
	public LoomisBeyond(String fromPostalCode, String toPostalCode,
			String priceZone, String zoneDesc, String zoneType,
			String mainZone, String field7, String beyondZone, String srvCode,
			Float interProvBase, Float interProvOverWeight) {
		this.fromPostalCode = fromPostalCode;
		this.toPostalCode = toPostalCode;
		this.priceZone = priceZone;
		this.zoneDesc = zoneDesc;
		this.zoneType = zoneType;
		this.mainZone = mainZone;
		this.field7 = field7;
		this.beyondZone = beyondZone;
		this.srvCode = srvCode;
		this.interProvBase = interProvBase;
		this.interProvOverWeight = interProvOverWeight;
	}

	public LoomisBeyond(){}
	
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
	public String getPriceZone() {
		return priceZone;
	}
	public void setPriceZone(String priceZone) {
		this.priceZone = priceZone;
	}
	public String getZoneDesc() {
		return zoneDesc;
	}
	public void setZoneDesc(String zoneDesc) {
		this.zoneDesc = zoneDesc;
	}
	public String getZoneType() {
		return zoneType;
	}
	public void setZoneType(String zoneType) {
		this.zoneType = zoneType;
	}
	public String getMainZone() {
		return mainZone;
	}
	public void setMainZone(String mainZone) {
		this.mainZone = mainZone;
	}
	public String getField7() {
		return field7;
	}
	public void setField7(String field7) {
		this.field7 = field7;
	}
	public String getBeyondZone() {
		return beyondZone;
	}
	public void setBeyondZone(String beyondZone) {
		this.beyondZone = beyondZone;
	}
	public String getSrvCode() {
		return srvCode;
	}
	public void setSrvCode(String srvCode) {
		this.srvCode = srvCode;
	}
	public Float getInterProvBase() {
		return interProvBase;
	}
	public void setInterProvBase(Float interProvBase) {
		this.interProvBase = interProvBase;
	}
	public Float getInterProvOverWeight() {
		return interProvOverWeight;
	}
	public void setInterProvOverWeight(Float interProvOverWeight) {
		this.interProvOverWeight = interProvOverWeight;
	}
	
	
}
