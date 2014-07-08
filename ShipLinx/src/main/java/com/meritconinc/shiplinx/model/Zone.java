package com.meritconinc.shiplinx.model;

public class Zone {
	private Long zoneId;
	private Long zoneStructureId;
	private String zoneName;
	private String cityName;
	private String provinceCode;
	private String countryCode;
	private String fromPostalCode;
	private String toPostalCode;
	
	public Long getZoneId() {
		return zoneId;
	}
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}
	public Long getZoneStructureId() {
		return zoneStructureId;
	}
	public void setZoneStructureId(Long zoneStructureId) {
		this.zoneStructureId = zoneStructureId;
	}
	public String getZoneName() {
		return zoneName;
	} 
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
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
	public static Zone getObject(Long zoneStructureId, Address address) {
		Zone z = new Zone();
		z.setZoneStructureId(zoneStructureId);
		z.setCityName(address.getCity());
		z.setCountryCode(address.getCountryCode());
		z.setProvinceCode(address.getProvinceCode());
		z.setZoneName(z.getCityName() + z.getProvinceCode() + z.getCountryCode());
		
		return z;
	}
}
