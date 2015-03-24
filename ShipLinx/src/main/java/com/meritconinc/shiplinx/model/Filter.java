package com.meritconinc.shiplinx.model;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Filter {
	
	private long filterId;
	private long businessId;
	private long partnerId;
	private long countryPartnerId;
	private long branchId;
	private String filterName;
	private String description;
	private List<Customer> customerList;
	private List<Customer> filterCustomers;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public long getBranchId() {
		return branchId;
	}
	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
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
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	public List<Customer> getFilterCustomers() {
		return filterCustomers;
	}
	public void setFilterCustomers(List<Customer> filterCustomers) {
		this.filterCustomers = filterCustomers;
	}
	public long getCountryPartnerId() {
		return countryPartnerId;
	}
	public void setCountryPartnerId(long countryPartnerId) {
		this.countryPartnerId = countryPartnerId;
	}
	 @Override
	    public int hashCode() {
	        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
	            // if deriving: appendSuper(super.hashCode()).
	            append(filterId).	    
	            toHashCode();
	    }

	    @Override
	    public boolean equals(Object obj) {
	       if (!(obj instanceof Customer))
	            return false;
	        if (obj == this)
	            return true;

	        Filter rhs = (Filter) obj;
	        return new EqualsBuilder().append(filterId, rhs.filterId).isEquals();
	    }
	
	

}

