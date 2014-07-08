package com.meritconinc.shiplinx.model;

import com.meritconinc.shiplinx.utils.ShiplinxConstants;




public class MerchantAccount{
	private static final long serialVersionUID = 1L;


	
/*[CONSTRUCTOR MARKER BEGIN]*/
	public MerchantAccount () {
		
	}

	/**
	 * Constructor for primary key
	 */
	public MerchantAccount (long id) {
		this.id=id;
	}

	// primary key
	private long id;
 
	// fields
	private long businessId;
	private java.lang.String processor;
	private java.lang.String property1;
	private java.lang.String property2;
	private java.lang.String property3;
	private java.lang.String property4;
	private java.lang.String property5;
	private java.lang.String testProperty1;
	private java.lang.String testProperty2;
	private java.lang.String testProperty3;
	private java.lang.String testProperty4;
	private java.lang.String testProperty5;
	private boolean active;
	private String currency;


	public String getProperty1(){		
		if(ShiplinxConstants.isTestMode())
			return this.getTestProperty1();
		return property1;
	}
	
	public String getProperty2(){		
		if(ShiplinxConstants.isTestMode())
			return this.getTestProperty2();
		return property2;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public java.lang.String getProcessor() {
		return processor;
	}

	public void setProcessor(java.lang.String processor) {
		this.processor = processor;
	}

	public java.lang.String getProperty3() {
		return property3;
	}

	public void setProperty3(java.lang.String property3) {
		this.property3 = property3;
	}

	public java.lang.String getProperty4() {
		return property4;
	}

	public void setProperty4(java.lang.String property4) {
		this.property4 = property4;
	}

	public java.lang.String getProperty5() {
		return property5;
	}

	public void setProperty5(java.lang.String property5) {
		this.property5 = property5;
	}

	public java.lang.String getTestProperty1() {
		return testProperty1;
	}

	public void setTestProperty1(java.lang.String testProperty1) {
		this.testProperty1 = testProperty1;
	}

	public java.lang.String getTestProperty2() {
		return testProperty2;
	}

	public void setTestProperty2(java.lang.String testProperty2) {
		this.testProperty2 = testProperty2;
	}

	public java.lang.String getTestProperty3() {
		return testProperty3;
	}

	public void setTestProperty3(java.lang.String testProperty3) {
		this.testProperty3 = testProperty3;
	}

	public java.lang.String getTestProperty4() {
		return testProperty4;
	}

	public void setTestProperty4(java.lang.String testProperty4) {
		this.testProperty4 = testProperty4;
	}

	public java.lang.String getTestProperty5() {
		return testProperty5;
	}

	public void setTestProperty5(java.lang.String testProperty5) {
		this.testProperty5 = testProperty5;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public void setProperty1(java.lang.String property1) {
		this.property1 = property1;
	}

	public void setProperty2(java.lang.String property2) {
		this.property2 = property2;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
}