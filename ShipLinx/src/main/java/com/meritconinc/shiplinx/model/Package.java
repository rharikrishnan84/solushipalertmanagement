package com.meritconinc.shiplinx.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Package implements Serializable {
	private long packageId;
	private ShippingOrder shippingOrder;
	private String description;
	private BigDecimal length;
	private BigDecimal height;
	private BigDecimal weight;
	private BigDecimal width;
	private String trackingNumber;
	private BigDecimal insuranceAmount;
	private BigDecimal codAmount;
	private String freightClass;
	private String type;
	private long orderId;
	private String position;
	private Float billedWeight;
	private byte[] label;
	private ShippingLabel labelInfo;
	private String reference1;
	private String reference2;
	private String reference3;
	private String weightUOM;
	private boolean isDoc;
	private String dimmedString;
	private boolean isWidthTwo;
	   private boolean isHeightTwo;
	   private boolean isLengthTwo;
	 
	public long getPackageId() {
		return packageId;
	}
	public void setPackageId(long packageId) {
		this.packageId = packageId;
	}
	public ShippingOrder getShippingOrder() {
		return shippingOrder;
	}
	public void setShippingOrder(ShippingOrder shippingOrder) {
		this.shippingOrder = shippingOrder;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal  getLength() {
		return length;
	}
	public void setLength(BigDecimal length) {
		this.length = length;
	}
	public BigDecimal getHeight() {
		return height;
	}
	public void setHeight(BigDecimal height) {
		this.height = height;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public BigDecimal getWidth() {
		return width;
	}
	public void setWidth(BigDecimal width) {
		this.width = width;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public BigDecimal getInsuranceAmount() {
		return insuranceAmount;
	}
	public void setInsuranceAmount(BigDecimal insuranceAmount) {
		this.insuranceAmount = insuranceAmount;
	}
	public BigDecimal getCodAmount() {
		return codAmount;
	}
	public void setCodAmount(BigDecimal codAmount) {
		this.codAmount = codAmount;
	}
	public String getFreightClass() {
		return freightClass;
	}
	public void setFreightClass(String freightClass) {
		this.freightClass = freightClass;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Float getBilledWeight() {
		return billedWeight;
	}
	public void setBilledWeight(Float billedWeight) {
		this.billedWeight = billedWeight;
	}
	public byte[] getLabel() {
		return label;
	}
	public void setLabel(byte[] label) {
		this.label = label;
	}
	public ShippingLabel getLabelInfo() {
		return labelInfo;
	}
	public void setLabelInfo(ShippingLabel labelInfo) {
		this.labelInfo = labelInfo;
	}
	public String getReference1() {
		return reference1;
	}
	public void setReference1(String reference1) {
		this.reference1 = reference1;
	}
	public String getReference2() {
		return reference2;
	}
	public void setReference2(String reference2) {
		this.reference2 = reference2;
	}
	public String getReference3() {
		return reference3;
	}
	public void setReference3(String reference3) {
		this.reference3 = reference3;
	}
	public String getWeightUOM() {
		return weightUOM;
	}
	public void setWeightUOM(String weightUOM) {
		this.weightUOM = weightUOM;
	}
	public boolean isDoc() {
		return isDoc;
	}
	public void setDoc(boolean isDoc) {
		this.isDoc = isDoc;
	}
	public String getDimmedString() {
		return dimmedString;
	}
	public void setDimmedString(String dimmedString) {
		this.dimmedString = dimmedString;
	}
	public boolean isWidthTwo() {
		return isWidthTwo;
	}
	public void setWidthTwo(boolean isWidthTwo) {
		this.isWidthTwo = isWidthTwo;
	}
	public boolean isHeightTwo() {
		return isHeightTwo;
	}
	public void setHeightTwo(boolean isHeightTwo) {
		this.isHeightTwo = isHeightTwo;
	}
	public boolean isLengthTwo() {
		return isLengthTwo;
	}
	public void setLengthTwo(boolean isLengthTwo) {
		this.isLengthTwo = isLengthTwo;
	}

	
	
}
