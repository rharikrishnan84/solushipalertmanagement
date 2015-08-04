package com.meritconinc.mmr.model.admin;

public class EcommercePlatform {
	
	private long ecomPlatformId;
	private String name;
	private String url;
	private String apiKey;
	private String sharedSceret;
	private String email;
	private String password;
	private String carrierServiceUrl;
	private String createShipmentUrl;
	public long getEcomPlatformId() {
		return ecomPlatformId;
	}
	public void setEcomPlatformId(long ecomPlatformId) {
		this.ecomPlatformId = ecomPlatformId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getSharedSceret() {
		return sharedSceret;
	}
	public void setSharedSceret(String sharedSceret) {
		this.sharedSceret = sharedSceret;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCarrierServiceUrl() {
		return carrierServiceUrl;
	}
	public void setCarrierServiceUrl(String carrierServiceUrl) {
		this.carrierServiceUrl = carrierServiceUrl;
	}
	public String getCreateShipmentUrl() {
		return createShipmentUrl;
	}
	public void setCreateShipmentUrl(String createShipmentUrl) {
		this.createShipmentUrl = createShipmentUrl;
	}

}
