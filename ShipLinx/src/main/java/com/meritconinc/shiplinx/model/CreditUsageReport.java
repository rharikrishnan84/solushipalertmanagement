package com.meritconinc.shiplinx.model;

import java.io.Serializable;
import java.util.List;

import com.meritconinc.shiplinx.utils.FormattingUtil;

public class CreditUsageReport implements Serializable {
	static final long serialVersionUID = 17092007;
	
	private List<Invoice> unpaidInvoices;
	private double unpaidInvoiceAmount;
	private List<ShippingOrder> liveUnpaidShipments;
	private double liveUnpaidShipmentsAmount;
	public List<Invoice> getUnpaidInvoices() {
		return unpaidInvoices;
	}
	public void setUnpaidInvoices(List<Invoice> unpaidInvoices) {
		this.unpaidInvoices = unpaidInvoices;
	}
	public double getUnpaidInvoiceAmount() {
		return unpaidInvoiceAmount;
	}
	public void setUnpaidInvoiceAmount(double unpaidInvoiceAmount) {
		this.unpaidInvoiceAmount = unpaidInvoiceAmount;
	} 
	public List<ShippingOrder> getLiveUnpaidShipments() {
		return liveUnpaidShipments;
	}
	public void setLiveUnpaidShipments(List<ShippingOrder> liveUnpaidShipments) {
		this.liveUnpaidShipments = liveUnpaidShipments;
	}
	public double getLiveUnpaidShipmentsAmount() {
		return liveUnpaidShipmentsAmount;
	}
	public void setLiveUnpaidShipmentsAmount(double liveUnpaidShipmentsAmount) {
		this.liveUnpaidShipmentsAmount = liveUnpaidShipmentsAmount;
	}
	
	public double getTotalCreditUsed(){
		return (FormattingUtil.add(unpaidInvoiceAmount, liveUnpaidShipmentsAmount)).doubleValue();
	}
	
}
