package com.meritconinc.shiplinx.model;

public class CountryPartner {
	
	private long countryPartnerId;
	private long partnerId;
	private long addressId;
	private long businessId;
	private Partner partner;
	private Business business;
	private String countryCode;
	private Address countryAddress;
	private String countryName;
	
	public long getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public Address getCountryAddress() {
		return countryAddress;
	}
	public void setCountryAddress(Address countryAddress) {
		this.countryAddress = countryAddress;
	}
	public Business getBusiness() {
		return business;
	}
	public void setBusiness(Business business) {
		this.business = business;
	}
	public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public long getCountryPartnerId() {
		return countryPartnerId;
	}
	public void setCountryPartnerId(long countryPartnerId) {
		this.countryPartnerId = countryPartnerId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	 
 

}
