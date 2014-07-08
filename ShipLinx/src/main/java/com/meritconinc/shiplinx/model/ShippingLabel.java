package com.meritconinc.shiplinx.model;

import com.meritconinc.shiplinx.utils.ShiplinxConstants;


public class ShippingLabel {
	private Long id;
	private long orderId;
	private String trackingNumber;
	private byte[] label;
	private byte[] html;
	private String twoDLabelString;
	private String originId;
	private String shipDateString;
	private String actualWeightString;
	private String systemNumString;
	private String dimmedString;
	private String delivDayString;
	private String delivByString;
	private String ursaCodeString;
	private String serviceCommitmentString;
	private String serviceDestinationString;
	private String formId;
	private String labelType;
	private byte[] codLabel;

	public Long getId() {
		return id;
	} 
	public void setId(Long id) {
		this.id = id;
	}
	public byte[] getLabel() {
		return label;
	}
	public void setLabel(byte[] label) {
		this.label = label;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public byte[] getHtml() {
		return html;
	}
	public void setHtml(byte[] html) {
		this.html = html;
	}
	public String getTwoDLabelString() {
		return twoDLabelString;
	}
	public void setTwoDLabelString(String twoDLabelString) {
		this.twoDLabelString = twoDLabelString;
	}
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public String getActualWeightString() {
		return actualWeightString;
	}
	public void setActualWeightString(String actualWeightString) {
		this.actualWeightString = actualWeightString;
	}
	public String getDimmedString() {
		return dimmedString;
	}
	public void setDimmedString(String dimmedString) {
		this.dimmedString = dimmedString;
	}
	public String getShipDateString() {
		return shipDateString;
	}
	public void setShipDateString(String shipDateString) {
		this.shipDateString = shipDateString;
	}
	public String getSystemNumString() {
		return systemNumString;
	}
	public void setSystemNumString(String systemNumString) {
		this.systemNumString = systemNumString;
	}
	public String getDelivByString() {
		return delivByString;
	}
	public void setDelivByString(String delivByString) {
		this.delivByString = delivByString;
	}
	public String getDelivDayString() {
		return delivDayString;
	}
	public void setDelivDayString(String delivDayString) {
		this.delivDayString = delivDayString;
	}
	public String getServiceCommitmentString() {
		return serviceCommitmentString;
	}
	public void setServiceCommitmentString(String serviceCommitmentString) {
		this.serviceCommitmentString = serviceCommitmentString;
	}
	public String getServiceDestinationString() {
		return serviceDestinationString;
	}
	public void setServiceDestinationString(String serviceDestinationString) {
		this.serviceDestinationString = serviceDestinationString;
	}
	public String getUrsaCodeString() {
		return ursaCodeString;
	}
	public void setUrsaCodeString(String ursaCodeString) {
		this.ursaCodeString = ursaCodeString;
	}
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getLabelType() {
		return labelType;
	}
	public void setLabelType(String labelType) {
		this.labelType = labelType;
	}
	
//	public boolean isImage(){
//		switch(labelType){
//			case ShiplinxConstants.LABEL_TYPE_PNG : return true;
//		}
//		return false;
//	}
	
	public byte[] getCodLabel() {
		return codLabel;
	}
	public void setCodLabel(byte[] codLabel) {
		this.codLabel = codLabel;
	}
	
	
}
