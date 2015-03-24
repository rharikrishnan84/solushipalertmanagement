package com.meritconinc.shiplinx.model;

/**
 * 
 * @author selva
 *
 */
public class UserFilter {

	private long userFilterId;
	private long divisionId;
	private Filter filter;
	private long businessId;
	private String userName;
	private long partnerId;
	private Partner partner;
	private long countryPartnerId;
	private Branch branch;
	private String CountryName;
	private long branchId;
	
	private boolean businessLevel=false;
	private boolean partnerLevel=false;
	private boolean nationLevel=false;
	private boolean branchLevel=false;
	
	public long getUserFilterId() {
		return userFilterId;
	}
	public void setUserFilterId(long userFilterId) {
		this.userFilterId = userFilterId;
	}
 
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	public long getCountryPartnerId() {
		return countryPartnerId;
	}
	public void setCountryPartnerId(long countryPartnerId) {
		this.countryPartnerId = countryPartnerId;
	}
	public long getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}
	public String getCountryName() {
		return CountryName;
	}
	public void setCountryName(String countryName) {
		CountryName = countryName;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}
	public Filter getFilter() {
		return filter;
	}
	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	public long getDivisionId() {
		return divisionId;
	}
	public void setDivisionId(long divisionId) {
		this.divisionId = divisionId;
	}
	public boolean isBranchLevel() {
		return branchLevel;
	}
	public void setBranchLevel(boolean branchLevel) {
		this.branchLevel = branchLevel;
	}
	public boolean isNationLevel() {
		return nationLevel;
	}
	public void setNationLevel(boolean nationLevel) {
		this.nationLevel = nationLevel;
	}
	public boolean isPartnerLevel() {
		return partnerLevel;
	}
	public void setPartnerLevel(boolean partnerLevel) {
		this.partnerLevel = partnerLevel;
	}
	public boolean isBusinessLevel() {
		return businessLevel;
	}
	public void setBusinessLevel(boolean businessLevel) {
		this.businessLevel = businessLevel;
	}
	
	
	
}
