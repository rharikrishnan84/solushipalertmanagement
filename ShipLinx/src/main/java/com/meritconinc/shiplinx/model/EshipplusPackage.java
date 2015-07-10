package com.meritconinc.shiplinx.model;

public class EshipplusPackage {
	
	private Long id;
	private Long packageKey;
	private Double defHeight;
	private Double defLength;
	private Double defWidth;
	private String message;
	private String icPackageName;
	private String eshipPackageName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPackageKey() {
		return packageKey;
	}
	public void setPackageKey(Long packageKey) {
		this.packageKey = packageKey;
	}
	public Double getDefHeight() {
		return defHeight;
	}
	public void setDefHeight(Double defHeight) {
		this.defHeight = defHeight;
	}
	public Double getDefLength() {
		return defLength;
	}
	public void setDefLength(Double defLength) {
		this.defLength = defLength;
	}
	public Double getDefWidth() {
		return defWidth;
	}
	public void setDefWidth(Double defWidth) {
		this.defWidth = defWidth;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIcPackageName() {
		return icPackageName;
	}
	public void setIcPackageName(String icPackageName) {
		this.icPackageName = icPackageName;
	}
	public String getEshipPackageName() {
		return eshipPackageName;
	}
	public void setEshipPackageName(String eshipPackageName) {
		this.eshipPackageName = eshipPackageName;
	}
	
	
	

}
