package com.meritconinc.shiplinx.model;
import java.util.List;
public class SalesRecord {

	private double totalCost;
	private double totalAmount;
	private String currency;
	private Integer month = 0;
	private long businessId;
	private long customerId;
	private String year;
			
	private int[] paymentStatusList = new int[10];
	
	private String monthName="0";
	private String totalCostDisplay;
	private String totalAmountDisplay;
	private List<Long> businessIds;
	//web only
	private String branch;
		
	public SalesRecord(double amount, String currency, int month){
		this.totalCost = amount;
		this.currency = currency;
		this.month = month;
	} 
	
	public SalesRecord(){
		
	}
	
	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return Returns the month.
	 */
	public Integer getMonth() {
		return month;
	}
	/**
	 * @param month The month to set.
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	/**
	 * @return Returns the total.
	 */

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int[] getPaymentStatusList() {
		return paymentStatusList;
	}
	public void setPaymentStatusList(int[] paymentStatusList) {
		this.paymentStatusList = paymentStatusList;
	}

	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getTotalCostDisplay() {
		return totalCostDisplay;
	}

	public void setTotalCostDisplay(String totalCostDisplay) {
		this.totalCostDisplay = totalCostDisplay;
	}

	public String getTotalAmountDisplay() {
		return totalAmountDisplay;
	}

	public void setTotalAmountDisplay(String totalAmountDisplay) {
		this.totalAmountDisplay = totalAmountDisplay;
	}

	public List<Long> getBusinessIds() {
		return businessIds;
	}

	public void setBusinessIds(List<Long> businessIds) {
		this.businessIds = businessIds;
	}

	
	
}
