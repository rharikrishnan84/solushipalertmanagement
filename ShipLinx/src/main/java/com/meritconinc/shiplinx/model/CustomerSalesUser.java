package com.meritconinc.shiplinx.model;

public class CustomerSalesUser {
	
	private int id;
	private long customerId;
	private String salesAgent;
	private double commissionPercentage=0; //this is for small packages
	private double commissionPercentagePerPalletService=0;
	private double commissionPercentagePerSkidService=0;
	private double commisionPercentagePerCHB=0;
	private double commisionPercentagePerFPA=0;
	private double commisionPercentagePerFWD=0;
	private String email;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getSalesAgent() {
		return salesAgent;
	}
	public void setSalesAgent(String salesAgent) {
		this.salesAgent = salesAgent;
	}
	public double getCommissionPercentage() {
		return commissionPercentage;
	} 
	public void setCommissionPercentage(double commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getCommissionPercentagePerPalletService() {
		return commissionPercentagePerPalletService;
	}
	public void setCommissionPercentagePerPalletService(
			double commissionPercentagePerPalletService) {
		this.commissionPercentagePerPalletService = commissionPercentagePerPalletService;
	}
	public double getCommissionPercentagePerSkidService() {
		return commissionPercentagePerSkidService;
	}
	public void setCommissionPercentagePerSkidService(
			double commissionPercentagePerSkidService) {
		this.commissionPercentagePerSkidService = commissionPercentagePerSkidService;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getCommisionPercentagePerCHB() {
		return commisionPercentagePerCHB;
	}
	public void setCommisionPercentagePerCHB(double commisionPercentagePerCHB) {
		this.commisionPercentagePerCHB = commisionPercentagePerCHB;
	}
	
	public double getCommisionPercentagePerFPA() {
				return commisionPercentagePerFPA;
			}
			public void setCommisionPercentagePerFPA(double commisionPercentagePerFPA) {
				this.commisionPercentagePerFPA = commisionPercentagePerFPA;
			}
			public double getCommisionPercentagePerFWD() {
				return commisionPercentagePerFWD;
			}
			public void setCommisionPercentagePerFWD(double commisionPercentagePerFWD) {
				this.commisionPercentagePerFWD = commisionPercentagePerFWD;
			}
	
}
