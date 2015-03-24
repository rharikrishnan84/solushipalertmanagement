package com.meritconinc.shiplinx.model;

import java.util.List;

/**
*
* 
* @author selva
*/
public class Partner {

		private String partnerName;
		private String firstName;
		private String lastName;
		private long partnerId;
		private List<String> countryList;
		private long countryCode;
		private String branch;
		private String business;
		private long businessId;
		private String email;
		private String company;
		private String phoneNumber;
		private String fax;
		private String phoneExt;
		private long addressId;
		private String shortCode;
		private Address partnerAddress;
		

		public long getAddressId() {
			return addressId;
		}
		public void setAddressId(long addressId) {
			this.addressId = addressId;
		}
	
		public String getPhoneExt() {
			return phoneExt;
		}
		public void setPhoneExt(String phoneExt) {
			this.phoneExt = phoneExt;
		}
	    
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public long getBusinessId() {
			return businessId;
		}
		public void setBusinessId(long businessId) {
			this.businessId = businessId;
		}
		public String getBusiness() {
			return business;
		}
		public void setBusiness(String business) {
			this.business = business;
		}
		public String getBranch() {
			return branch;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public long getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(long countryCode) {
			this.countryCode = countryCode;
		}
	
		public long getPartnerId() {
			return partnerId;
		}
		public void setPartnerId(long partnerId) {
			this.partnerId = partnerId;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
	
		public String getPartnerName() {
			return partnerName;
		}
		public void setPartnerName(String partnerName) {
			this.partnerName = partnerName;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public List<String> getCountryList() {
			return countryList;
		}
		public void setCountryList(List<String> countryList) {
			this.countryList = countryList;
		}
		public String getFax() {
			return fax;
		}
		public void setFax(String fax) {
			this.fax = fax;
		}
		public String getShortCode() {
			return shortCode;
		}
		public void setShortCode(String shortCode) {
			this.shortCode = shortCode;
		}
		public Address getPartnerAddress() {
			return partnerAddress;
		}
		public void setPartnerAddress(Address partnerAddress) {
			this.partnerAddress = partnerAddress;
		}


}
