package com.meritconinc.shiplinx.action;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.exception.CustomerNameAlreadyTakenException;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Customer;


/**
 * <code>Set welcome message.</code>
 */
public class CustomerInfoManagerAction extends CustomerManagerBaseAction {
	private static final long serialVersionUID	= 25092007;
	private static final Logger log = LogManager.getLogger(CustomerManagerAction.class);

	public String execute() throws Exception {
		log.debug("INSIDE CUSTOMER INFO MANAGER ___________________________EXECUTE");
		Customer customer=this.getCustomer();
		return SUCCESS;
	} 

	public String init() throws Exception {
		try {
			initialize();
			User loggedInUser = getLoginUser();
			long customerId = loggedInUser.getCustomerId();
			Customer customer = getService().getCustomerInfoByCustomerId(customerId);
			customer.setUsername(loggedInUser.getUsername());
			customer.setId(customerId);
			getSession().put("oldBusinessName", customer.getName());
			Address ad = new Address();
			ad.setProvinceCode(customer.getAddress().getProvinceCode());
			setAddress(ad);
			setCustomer(customer);
		} catch (Exception e) {
			addActionError(getText("error.timeZones"));
		}
		return SUCCESS;
	}
	
	

	public String edit() throws Exception {
		try {
			Customer customer = getCustomer();
			Address ad = getAddress();
			customer.getAddress().setProvinceCode(ad.getProvinceCode());
			getService().edit(customer,	(String) getSession().get("oldBusinessName"));
			getSession().remove("oldBusinessName");
		} catch (CustomerNameAlreadyTakenException ce) {
			addActionError(getText("error.customername.taken"));
			return INPUT;
		}
		addActionMessage(getText("customer.save.successfully"));
		return SUCCESS;
	}
	
			
	public void prepare() throws Exception {
		//setAvailableRoles();
	}
	
	public Address getAddress() {
		Address address = (Address)getSession().get("address");
		if (address == null) {
			address = new Address();
			setAddress(address);
		}
		return address;
	}
	public void setAddress(Address address) {

		getSession().put("address", address);
	}	


}
