package com.meritconinc.shiplinx.model;

public class EdiInfo {

	private Long businessId;
	private Long carrierId;
	private String ediFolder;
	private String fileType;
	private String ediFormat;
	private String version;
	
	
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
	public String getEdiFolder() {
		return ediFolder;
	}
	public void setEdiFolder(String ediFolder) {
		this.ediFolder = ediFolder;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getEdiFormat() {
		return ediFormat;
	}
	public void setEdiFormat(String format) {
		this.ediFormat = format;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
}
