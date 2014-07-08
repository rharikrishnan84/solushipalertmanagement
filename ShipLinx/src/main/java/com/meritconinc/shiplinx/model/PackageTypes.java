package com.meritconinc.shiplinx.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PackageTypes implements Serializable{
	
	private long packageTypeId;
	private String packageName;
	private String packageDesc;
	private double packageLength;
	private double packageHeight;
	private double packageWeight;
	private double packageWidth;
	private long customerId;
	
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageDesc() {
		return packageDesc;
	}
	public void setPackageDesc(String packageDesc) {
		this.packageDesc = packageDesc;
	} 
	public double getPackageLength() {
		return packageLength;
	}
	public void setPackageLength(double packageLength) {
		this.packageLength = packageLength;
	}
	public double getPackageHeight() {
		return packageHeight;
	}
	public void setPackageHeight(double packageHeight) {
		this.packageHeight = packageHeight;
	}
	public double getPackageWeight() {
		return packageWeight;
	}
	public void setPackageWeight(double packageWeight) {
		this.packageWeight = packageWeight;
	}
	public double getPackageWidth() {
		return packageWidth;
	}
	public void setPackageWidth(double packageWidth) {
		this.packageWidth = packageWidth;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getPackageTypeId() {
		return packageTypeId;
	}
	public void setPackageTypeId(long packageTypeId) {
		this.packageTypeId = packageTypeId;
	}
	
}
