package com.meritconinc.mmr.model.admin;
import java.util.List;




/**
 * 
 * @author selva
 * E-commerce soluship integration ( 5/06/2015)
 *
 */
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;

public class EcommerceConfig {

	private long ecommerceConfigId;
	private long ecommerceStoreId;
	private EcommerceStore ecommerceStore;
	private List<EcommerceStore> ecommerceStoreList;
	private List<Customer> customerList;
	private long customerId;
	private Customer customer;
	private boolean fastEnabled;
	private boolean cheaperEnabled;
    private long businessId;
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	 
	public long getEcommerceStoreId() {
		return ecommerceStoreId;
	}
	public void setEcommerceStoreId(long ecommerceStoreId) {
		this.ecommerceStoreId = ecommerceStoreId;
	}
	public long getEcommerceConfigId() {
		return ecommerceConfigId;
	}
	public void setEcommerceConfigId(long ecommerceConfigId) {
		this.ecommerceConfigId = ecommerceConfigId;
	}
	public boolean isCheaperEnabled() {
		return cheaperEnabled;
	}
	public void setCheaperEnabled(boolean cheaperEnabled) {
		this.cheaperEnabled = cheaperEnabled;
	}
	public boolean isFastEnabled() {
		return fastEnabled;
	}
	public void setFastEnabled(boolean fastEnabled) {
		this.fastEnabled = fastEnabled;
	}
	public EcommerceStore getEcommerceStore() {
		return ecommerceStore;
	}
	public void setEcommerceStore(EcommerceStore ecommerceStore) {
		this.ecommerceStore = ecommerceStore;
	}
	public List<EcommerceStore> getEcommerceStoreList() {
		return ecommerceStoreList;
	}
	public void setEcommerceStoreList(List<EcommerceStore> ecommerceStoreList) {
		this.ecommerceStoreList = ecommerceStoreList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> getCustomerList() {
		this.customerList=(List<Customer>)ActionContext.getContext().getSession().get(ShiplinxConstants.SESSION_BUSINESSFILTER_CUSTOMERID);
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		 
		this.customerList = customerList;
	}
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	 
	
	
}
