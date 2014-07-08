package com.meritconinc.shiplinx.model;

import java.io.Serializable;

import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class FuelSurcharge implements Serializable {
	private Long id;
	private long carrierId;
	private String type;
	private double value;
	private String fromCountry;
	private String toCountry;
	
	public long getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(long carrierId) {
		this.carrierId = carrierId;
	}
	public Long getId() {
		return id;
	} 
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public String getFromCountry() {
		return fromCountry;
	}
	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}
	public String getToCountry() {
		return toCountry;
	}
	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}
	public String getCarrierName() {
		switch((int)getCarrierId()) {
		case ShiplinxConstants.CARRIER_FEDEX:
			return "Federal Express";
		/*
		case ShiplinxConstants.CARRIER_PUROLATOR:
			return "Purolator";
		
		case ShiplinxConstants.CARRIER_CWW:
			return "Canada WorldWide";*/
		default:
			return ShiplinxConstants.NOT_APPLICABLE;
		}
	}
}