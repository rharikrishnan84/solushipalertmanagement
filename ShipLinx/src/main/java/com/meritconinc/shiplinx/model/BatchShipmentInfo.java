package com.meritconinc.shiplinx.model;

public class BatchShipmentInfo {
	private Long businessId;
	private Long carrierId;
	private Long serviceId;
	private String fileName;
	private String batchId;
	public Long getBusinessId() {
		return businessId;
	} 
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public Long getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(Long carrierId) {
		this.carrierId = carrierId;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
}
