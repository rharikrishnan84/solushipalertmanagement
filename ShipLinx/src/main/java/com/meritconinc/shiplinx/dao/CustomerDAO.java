package com.meritconinc.shiplinx.dao;


import java.util.List;

import com.meritconinc.shiplinx.model.Billduty;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.CustomerSalesUser;
import com.meritconinc.shiplinx.model.CustomerTier;

public interface CustomerDAO{
	
	public long addCustomer(Customer customer);
	public void addUser(Customer customer,long customerId);
	public boolean isCustomerRegistered(String name, long businessId, long customerId);
	List<Customer> search(Customer customer);
	public Customer getCustomerInfoByCustomerId(long customerId, long businessId);	
	public void edit(Customer customer);
	public long getCustomerIdByUserName(String username); 
	public CustomerCarrier getCustomerCarrier(long customerId);
	public boolean isCarrierAccountRegistered(String accountNum, long carrierId);
	public void saveCustomerCarrier(CustomerCarrier customerCarrier);
	public CustomerCarrier getCustomerCarrierInfoById(long customerId, Long carrierAccountId);
	public void deleteCustomerCarrier(String carrierAccountId);
	public void editCustomerCarrier(CustomerCarrier customerCarrier,Long customerCarrierId);
	public void setDefaultCarrierAccount(long customerId, String country, long carrierId);
	public List<CustomerCarrier> getCustomerCarrierByAccountNumber(String accountNumber);
	public Long getCustomerIdByName(String name, Long busId);
	public Customer getCustomerByApiInfo(String apiUserName, String apiUserPassword);
	public List<CustomerSalesUser> getCustomerSalesUser(CustomerSalesUser csu);
	
	public long addCustomerSales(CustomerSalesUser customerSalesUser);
	
	public void deleteCustomerSalesUser(CustomerSalesUser customerSalesUser);
	
	public void updateCustomerSalesUser(CustomerSalesUser customerSalesUser);
	
	public List<CustomerTier> getCustomerByTier(Customer customer);
	
	public boolean isWarehouseCustomer(long customerId);
	public List<CustomerSalesUser> getCustomerSalesInformation(long customerId);
	public CustomerSalesUser getCustomerSalesUserById(long id);
	public List<Billduty> getBilldutyList(String locale);
	
	public int findUnpaidInvoiceDuration(long customerId,int holdTerms);
	public String getCustomerCurrencyById(Long customerId);
	
	public void updateCustomerStatus(long custId,String customerStatus);
	
	public void updateAvailableCredit(double availableCredit, long customerId);
	public double getAvailableCredit(long customerId);
}