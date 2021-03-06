package com.meritconinc.shiplinx.service;

import java.util.List;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.shiplinx.model.Billduty;
import com.meritconinc.shiplinx.model.BusinessMarkup;
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
	public boolean sendSalesRepAddCustomerMailNotification(Customer customer, User user);	
	public List<Billduty> getBilldutyList(String locale);
	
	public void updateAvailableCredit(double availableCredit, long customerId);
		public double getAvailableCredit(long customerId);
		public int findUnpaidInvoiceDuration(long customerId, int holdTerms);
		
		public List<Customer> getAllCustomers();
						public List<Customer> getAllCustomersByBusinessLevel(Long businessId);
						public List<Customer> getAllCustomersByPartnerLevel(Long businessId,
								Long partnerId);
						public List<Customer> getAllCustomersByNationLevel(Long businessId,
								Long partnerId, Long countryPartnerId);
						public List<Customer> getAllCustomersByBranchLevel(Long businessId,
								Long partnerId, Long countryPartnerId, long branchId);
						public List<Customer> getCustomersBySalesUser(String username);
	public List<Customer> getAllCustomerForBusiness(
			long parseLong);
	public void createCustomerForParentBusiness(BusinessMarkup businessMarkup);
	public Long getCustomerIdByName(String name, Long businessId);
}