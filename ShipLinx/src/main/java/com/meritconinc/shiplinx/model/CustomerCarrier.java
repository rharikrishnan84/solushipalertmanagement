package com.meritconinc.shiplinx.model;

public class CustomerCarrier {
//	carrieraccount_id, customer_id, country, isdefaultaccount, account_number1, account_number2, 
//	property_1, property_2, property_3, property_4, property_5, carrier_id, isLive
	private long id;
	private String carrierName;
	private boolean defaultAccount;
	private Long customerId;
	private Long carrierAccountId;
	private String country;
	private String toCountry;
	private Long carrierId;
	private boolean live; //using this field for "house" accounts
	private String accountNumber1;
	private String accountNumber2;	
	private String property1;
	private String property2;
	private String property3;
	private String property4;
	private String property5;	
	private long businessId;
	private int count; 
	private Double businessCarrierDiscount;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public CustomerCarrier(){
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
	 * @return Returns the carrierName.
	 */
	public String getCarrierName() {
		return carrierName;
	}
	/**
	 * @param carrierName The carrierName to set.
	 */
	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	
	public boolean isDefaultAccount(){
		return  defaultAccount;
	}
	
	public void setDefaultAccount(boolean isDefaultAccount){
		this.defaultAccount=isDefaultAccount;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getCarrierAccountId() {
		return carrierAccountId;
	}

	public void setCarrierAccountId(Long carrierAccountId) {
		this.carrierAccountId = carrierAccountId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the carrierId
	 */
	public Long getCarrierId() {
		return carrierId;
	}

	/**
	 * @param carrierId the carrierId to set
	 */
	public void setCarrierId(Long carrierId) {
		this.carrierId = carrierId;
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean isLive) {
		this.live = isLive;
	}

	public String getAccountNumber1() {
		return accountNumber1;
	}

	public void setAccountNumber1(String accountNumber1) {
		this.accountNumber1 = accountNumber1;
	}

	public String getAccountNumber2() {
		return accountNumber2;
	}

	public void setAccountNumber2(String accountNumber2) {
		this.accountNumber2 = accountNumber2;
	}

	public String getProperty1() {
		return property1;
	}

	public void setProperty1(String property1) {
		this.property1 = property1;
	}

	public String getProperty2() {
		return property2;
	}

	public void setProperty2(String property2) {
		this.property2 = property2;
	}

	public String getProperty3() {
		return property3;
	}

	public void setProperty3(String property3) {
		this.property3 = property3;
	}

	public String getProperty4() {
		return property4;
	}

	public void setProperty4(String property4) {
		this.property4 = property4;
	}

	public String getProperty5() {
		return property5;
	}

	public void setProperty5(String property5) {
		this.property5 = property5;
	}

	public Double getBusinessCarrierDiscount() {
		return businessCarrierDiscount;
	}

	public void setBusinessCarrierDiscount(Double businessCarrierDiscount) {
		this.businessCarrierDiscount = businessCarrierDiscount;
	}

	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public String getToCountry() {
		return toCountry;
	}
	public void setToCountry(String toCountry) {
		this.toCountry = toCountry;
	}

	
	
}