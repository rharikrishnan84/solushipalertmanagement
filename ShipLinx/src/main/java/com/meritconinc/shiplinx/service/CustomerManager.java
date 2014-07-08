package com.meritconinc.shiplinx.service;

import java.util.List;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.shiplinx.model.CreditCard;
import com.meritconinc.shiplinx.model.CreditUsageReport;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.CustomerSalesUser;

public interface CustomerManager{
	
	public void add(Customer customer, CreditCard creditCard)throws Exception;
	public List<Customer> search(Customer customer);
	public void edit(Customer customer,String customerOldName)throws Exception;	
	public Customer getCustomerInfoByCustomerId(long customerId)throws Exception;
	public long getCustomerIdByUserName(String username);
	public void getCustomerInfoByCustomerIdAndCarrierId(Long customerId, long l);
	public CustomerCarrier getCustomerCarrier(long customerId);
	public void saveCustomerCarrier(CustomerCarrier customerCarrier);
	public CustomerCarrier getCustomerCarrierInfoById(long customerId,Long carrierAccountId);
	public void deleteCustomerCarrier(String id);
	public void editCustomerCarrier(CustomerCarrier customerCarrier, Long customerCarrierId);
	public void setOtherCarrierAccountAsFalse(long customerId, String country, long carrierId);
	public CreditUsageReport getCreditUsageReport(long customerId, long busId);
	public boolean sendCustomerMailNotification(long customerId, User user);
	public boolean sendAddCustomerMailNotification(Customer customer, User user);
	public String resetPassword(User user);
	 
	public List<CustomerSalesUser> getCustomerSalesInfoByCustId(long customerId);
	
	public boolean addCustomerSales(CustomerSalesUser customerSalesUser);
	
	public boolean deleteCustomerSales(CustomerSalesUser customerSalesUser);
	
	public boolean updateCustomerSales(CustomerSalesUser customerSalesUser);
	
	public boolean isWarehouseCustomer(long customerId);
	
	public List<CustomerSalesUser> getCustomerSalesInformation(long customerId);
	
}