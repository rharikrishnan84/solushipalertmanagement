package com.meritconinc.shiplinx.model;

import com.meritconinc.mmr.utilities.StringUtil;

public class Carrier {

	private Long id;
	private String name;
	private String trackingURL;
	private double businessCarrierDiscount;
	private String implementingClass;
	private String displayName;
	 
	public String getName() {
		if(!StringUtil.isEmpty(displayName))
			return displayName;
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTrackingURL() {
		return trackingURL;
	}
	public void setTrackingURL(String trackingURL) {
		this.trackingURL = trackingURL;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getBusinessCarrierDiscount() {
		return businessCarrierDiscount;
	}
	public void setBusinessCarrierDiscount(double businessCarrierDiscount) {
		this.businessCarrierDiscount = businessCarrierDiscount;
	}
	public String getImplementingClass() {
		return implementingClass;
	}
	public void setImplementingClass(String implementingClass) {
		this.implementingClass = implementingClass;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
}
