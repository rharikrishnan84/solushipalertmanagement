package com.meritconinc.shiplinx.model;

public class InvoiceCharge {
	
	private int id;
	
	private int invoiceId;
	
	private int chargeId;
	
	private int orderId;
	
	private String cancelledInvoice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getChargeId() {
		return chargeId;
	}

	public void setChargeId(int chargeId) {
		this.chargeId = chargeId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCancelledInvoice() {
		return cancelledInvoice;
	}

	public void setCancelledInvoice(String cancelledInvoice) {
		this.cancelledInvoice = cancelledInvoice;
	}
	
	

}
