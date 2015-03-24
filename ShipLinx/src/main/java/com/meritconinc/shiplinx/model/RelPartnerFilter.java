package com.meritconinc.shiplinx.model;

import java.util.List;

public class RelPartnerFilter {
	
	private long relPartnerFilId;
	private long filterId;
	private Branch branch;
	private Partner partner;
	private long partnerId;
	private long countryPatnerId;
	private long branchId;
	private String countryName;
	private List<Customer> customers;
	public long getRelPartnerFilId() {
		return relPartnerFilId;
	}
	public void setRelPartnerFilId(long relPartnerFilId) {
		this.relPartnerFilId = relPartnerFilId;
	}
	public long getFilterId() {
		return filterId;
	}
	public void setFilterId(long filterId) {
		this.filterId = filterId;
	}
	public long getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}
	public long getCountryPatnerId() {
		return countryPatnerId;
	}
	public void setCountryPatnerId(long countryPatnerId) {
		this.countryPatnerId = countryPatnerId;
	}
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
 
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Partner getPartner() {
		return partner;
	}
	public void setPartner(Partner partner) {
		this.partner = partner;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	

}
