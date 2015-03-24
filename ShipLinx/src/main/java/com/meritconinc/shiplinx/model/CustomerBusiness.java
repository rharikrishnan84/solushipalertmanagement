package com.meritconinc.shiplinx.model;

public class CustomerBusiness {
	
		private long customerBusinessId;
		private long businessId;
		private long customerId;
		private long partnerId;
		private long countryPartnerId;
		private long branchId;
		private String userName;
	  private boolean partnerLevel;
	  private boolean nationLevel;
	  private boolean branchLevel;
	  private boolean divitionLevel;
	  private boolean businessLevel;
	public long getCustomerBusinessId() {
		return customerBusinessId;
	}
	public void setCustomerBusinessId(long customerBusinessId) {
		this.customerBusinessId = customerBusinessId;
	}
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}
	public long getCountryPartnerId() {
		return countryPartnerId;
	}
	public void setCountryPartnerId(long countryPartnerId) {
		this.countryPartnerId = countryPartnerId;
	}
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isPartnerLevel() {
		return partnerLevel;
	}
	public void setPartnerLevel(boolean partnerLevel) {
		this.partnerLevel = partnerLevel;
	}
	public boolean isNationLevel() {
		return nationLevel;
	}
	public void setNationLevel(boolean nationLevel) {
		this.nationLevel = nationLevel;
	}
	public boolean isBranchLevel() {
		return branchLevel;
	}
	public void setBranchLevel(boolean branchLevel) {
		this.branchLevel = branchLevel;
	}
	public boolean isDivitionLevel() {
		return divitionLevel;
	}
	public void setDivitionLevel(boolean divitionLevel) {
		this.divitionLevel = divitionLevel;
	}
	public boolean isBusinessLevel() {
		return businessLevel;
	}
	public void setBusinessLevel(boolean businessLevel) {
		this.businessLevel = businessLevel;
	}


	

}
