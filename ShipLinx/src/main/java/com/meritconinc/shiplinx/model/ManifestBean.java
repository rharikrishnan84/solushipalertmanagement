package com.meritconinc.shiplinx.model;

public class ManifestBean {
    Integer s_No;
    String masterTrackingNum;
    String cosigneeAddress;
    String productType;
    Integer productPieces;
    String productWeight;
    Double declaredValue;
    Double charge_HST_GST_temp;
    Double totalChargeTemp;
    Double COD_value;
    Double SH_DG;
    
	
	public String getMasterTrackingNum() {
		return masterTrackingNum;
	}
	public Double getSH_DG() {
		return SH_DG;
	}
	public void setSH_DG(Double sH_DG) {
		SH_DG = sH_DG;
	}
	public void setMasterTrackingNum(String masterTrackingNum) {
		this.masterTrackingNum = masterTrackingNum;
	}
	public String getCosigneeAddress() {
		return cosigneeAddress;
	}
	public void setCosigneeAddress(String cosigneeAddress) {
		this.cosigneeAddress = cosigneeAddress;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getS_No() {
		return s_No;
	}
	public void setS_No(Integer s_No) {
		this.s_No = s_No;
	}
	public Integer getProductPieces() {
		return productPieces;
	}
	public void setProductPieces(Integer productPieces) {
		this.productPieces = productPieces;
	}
	public String getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(String productWeight) {
		this.productWeight = productWeight;
	}
	public Double getDeclaredValue() {
		return declaredValue;
	}
	public void setDeclaredValue(Double declaredValue) {
		this.declaredValue = declaredValue;
	}
	public Double getCharge_HST_GST_temp() {
		return charge_HST_GST_temp;
	}
	public void setCharge_HST_GST_temp(Double charge_HST_GST_temp) {
		this.charge_HST_GST_temp = charge_HST_GST_temp;
	}
	public Double getTotalChargeTemp() {
		return totalChargeTemp;
	}
	public void setTotalChargeTemp(Double totalChargeTemp) {
		this.totalChargeTemp = totalChargeTemp;
	}
	public Double getCOD_value() {
		return COD_value;
	}
	public void setCOD_value(Double cOD_value) {
		COD_value = cOD_value;
	}
	
    

}
