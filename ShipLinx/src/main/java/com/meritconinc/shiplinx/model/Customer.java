package com.meritconinc.shiplinx.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class Customer {
	
	private long id;
	private String username;
	private String password;
	private String retypePassword;
	private String name;
	private Boolean active;
	private Date dateCreated;
	private String invoicingEmail;
	private String salesAgent;
	/**
	 * Start: Sales Agent 2 Implementation
	 */
	private String salesAgent2;
	/**
	 * End: Sales Agent 2 Implementation
	 */
	private String timeZone;
	private boolean deleted;
	private String apiUsername;
	private String apiPassword;
//	private String email;
	private String accountNumber;
	private long businessId;
	private CustomerCarrier customerCarrier;
	private List<com.meritconinc.shiplinx.model.CustomerCarrier> carrieraccounts = 
		new ArrayList<com.meritconinc.shiplinx.model.CustomerCarrier>();
	
	private int payableDays;
	private int paymentType = ShiplinxConstants.PAYMENT_TYPE_ON_CREDIT;
	private int paymentTypeLevel;
	
	private long addressId;
	private Address address;
	
	private boolean warehouseCustomer;
	
	private long primaryWarehouseId;
	private Warehouse primaryWarehouse;
	
	public boolean isReference() {
		return reference;
	}

	public void setReference(boolean reference) {
		this.reference = reference;
	}

	private CreditCard creditCardActive;
	
	private CreditCard newCreditCard;
	
	private String branch;
	
	private String apcontactName;
	
	private String apPhone;
	
	private boolean reference=false;
	
	private User user;
	private boolean chbCustomer;
	public boolean isChbCustomer() {
		return chbCustomer;
	}

	public void setChbCustomer(boolean chbCustomer) {
		this.chbCustomer = chbCustomer;
	}

	public String getApcontactName() {
		return apcontactName;
	}

	public void setApcontactName(String apcontactName) {
		this.apcontactName = apcontactName;
	}

	public String getApPhone() {
		return apPhone;
	}

	public void setApPhone(String apPhone) {
		this.apPhone = apPhone;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//this is to accommodate IC requirement to add an additional handling fee for a specific customer. Should allow generalization of this feature to all businesses  
	private double additionalHandlingCharge;
	private BigDecimal creditLimit = new BigDecimal(0.00);

	//this list will hold the list of sales users associated with the corresponding customer
	private List<CustomerSalesUser> customerSalesUser = 
		new ArrayList<CustomerSalesUser>();
	
	//this list will hold the list of carriers that are activated for the customer
	private List<Carrier> customerSelectedCarriers = 
		new ArrayList<Carrier>();
	
	//monthly spend
	private BigDecimal monthlySpend= new BigDecimal(0.00).setScale(2);
	//CustomerTier
	private CustomerTier customerTier = new CustomerTier();
	
	//Products
	private List<Products> productList = new ArrayList<Products>();
	
	private boolean onlineSignup = false;
	
	//web only
	private String addressSearchString;
	
	private String defaultCurrency;
	
	public Customer(){
		setTimeZone("Canada/Eastern");
		address = new Address();
		customerCarrier = new CustomerCarrier();
	}
		
	/**
	 * @return Returns the id.
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return Returns the businessName.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param businessName The businessName to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return Returns the dateCreated.
	 */
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getInvoicingEmail() {
		return invoicingEmail;
	}
	public void setInvoicingEmail(String invoicingEmail) {
		this.invoicingEmail = invoicingEmail;
	}
	
	public String getSalesAgent() {
		return salesAgent;
	}

	public void setSalesAgent(String salesAgent) {
		this.salesAgent = salesAgent;
	}

	public String getApiUsername() {
		return apiUsername;
	}
	public void setApiUsername(String apiUsername) {
		this.apiUsername = apiUsername;
	}
	/**
	 * @return Returns the timeZone.
	 */
	public String getTimeZone() {
		return timeZone;
	}
	/**
	 * @param timeZone The timeZone to set.
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	/**
	 * @return Returns the apiPassword.
	 */
	public String getApiPassword() {
		return apiPassword;
	}

	/**
	 * @param apiPassword The apiPassword to set.
	 */
	public void setApiPassword(String apiPassword) {
		this.apiPassword = apiPassword;
	}

	/**
	 * @return Returns the deleted.
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted The deleted to set.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
		
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public CustomerCarrier getCustomerCarrier() {
		return customerCarrier;
	}

	public void setCustomerCarrier(CustomerCarrier customerCarrier) {
		this.customerCarrier = customerCarrier;
	}
		
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	/**
	 * @return Returns the carrieraccounts.
	 */
	public List<com.meritconinc.shiplinx.model.CustomerCarrier> getCarriers() {
		return carrieraccounts;
	}

	/**
	 * @param packages The packages to set.
	 */
	public void setCarriers(List<com.meritconinc.shiplinx.model.CustomerCarrier> carrieraccounts) {
		this.carrieraccounts = carrieraccounts;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public int getPayableDays() {
		return payableDays;
	}

	public void setPayableDays(int payableDays) {
		this.payableDays = payableDays;
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(int paymentType) {
		this.paymentType = paymentType;
	}

	public String getRetypePassword() {
		return retypePassword;
	}
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public double getAdditionalHandlingCharge() {
		return additionalHandlingCharge;
	}
	public void setAdditionalHandlingCharge(double additionalHandlingCharge) {
		this.additionalHandlingCharge = additionalHandlingCharge;
	}

	public String getSalesAgent2() {
		return salesAgent2;
	}
	public void setSalesAgent2(String salesAgent2) {
		this.salesAgent2 = salesAgent2;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public List<CustomerSalesUser> getCustomerSalesUser() {
		return customerSalesUser;
	}
	public void setCustomerSalesUser(List<CustomerSalesUser> customerSalesUser) {
		this.customerSalesUser = customerSalesUser;
	}

	public List<Carrier> getCustomerSelectedCarriers() {
		return customerSelectedCarriers;
	}

	public void setCustomerSelectedCarriers(List<Carrier> customerSelectedCarriers) {
		this.customerSelectedCarriers = customerSelectedCarriers;
	}

	public BigDecimal getMonthlySpend() {
		return monthlySpend;
	}

	public void setMonthlySpend(BigDecimal monthlySpend) {		
		this.monthlySpend = monthlySpend;
	}

	public CustomerTier getCustomerTier() {
		return customerTier;
	}

	public void setCustomerTier(CustomerTier customerTier) {
		this.customerTier = customerTier;
	}

	public List<Products> getProductList() {
		return productList;
	}

	public void setProductList(List<Products> productList) {
		this.productList = productList;
	}

	public boolean isWarehouseCustomer() {
		return warehouseCustomer;
	}

	public void setWarehouseCustomer(boolean warehouseCustomer) {
		this.warehouseCustomer = warehouseCustomer;
	}

	public long getPrimaryWarehouseId() {
		return primaryWarehouseId;
	}

	public void setPrimaryWarehouseId(long primaryWarehouseId) {
		this.primaryWarehouseId = primaryWarehouseId;
	}

	public Warehouse getPrimaryWarehouse() {
		return primaryWarehouse;
	}

	public void setPrimaryWarehouse(Warehouse primaryWarehouse) {
		this.primaryWarehouse = primaryWarehouse;
	}

	public CreditCard getCreditCardActive() {
		return creditCardActive;
	}
	public void setCreditCardActive(CreditCard creditCardActive) {
		this.creditCardActive = creditCardActive;
	}

	public CreditCard getNewCreditCard() {
		return newCreditCard;
	}

	public void setNewCreditCard(CreditCard newCreditCard) {
		this.newCreditCard = newCreditCard;
	}

	public boolean isOnlineSignup() {
		return onlineSignup;
	}
	public void setOnlineSignup(boolean onlineSignup) {
		this.onlineSignup = onlineSignup;
	}

	public int getPaymentTypeLevel() {
		return paymentTypeLevel;
	}
	public void setPaymentTypeLevel(int paymentTypeLevel) {
		this.paymentTypeLevel = paymentTypeLevel;
	}

	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAddressSearchString() {
		return addressSearchString;
	}
	public void setAddressSearchString(String addressSearchString) {
		this.addressSearchString = addressSearchString;
	}

	public String getDefaultCurrency() {
		return defaultCurrency;
	}

	public void setDefaultCurrency(String defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}
	
	
	
}