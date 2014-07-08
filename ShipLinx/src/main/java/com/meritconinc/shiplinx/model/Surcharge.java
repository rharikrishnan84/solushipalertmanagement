package com.meritconinc.shiplinx.model;

import java.text.DecimalFormat;

public class Surcharge {
	private String name;
	private double charges;
	private double amount;
	private int chargeId;
	
	public Surcharge() {		
	}
	
	public Surcharge(String name, double amount) {
		this.name = name;
		this.amount = amount;
	}
	
	public Surcharge(String name, double amount, int chargeId) {
		this.name = name;
		this.amount = amount;
		this.chargeId = chargeId;
	}
	
	
	public String getName() {
		return name;
	} 
	public void setName(String name) {
		this.name = name;
	}
	public double getCharges() {
		return charges;
	}
	public void setCharges(double charges) {
		this.charges = charges;
	}

	public double getAmount() {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		return Double.valueOf(twoDForm.format(amount));
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getChargeId() {
		return chargeId;
	}

	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}
}
