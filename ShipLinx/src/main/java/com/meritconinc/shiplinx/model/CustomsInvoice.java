package com.meritconinc.shiplinx.model;

import java.util.List;

public class CustomsInvoice {

	private long id;
	private long orderId;
	private String billTo;
	private String billToAccountNum;
	private Long billToAddressId;
	private Address billToAddress = new Address();
	private Long buyerAddressId;
	private Address buyerAddress = new Address();
	private String reference;
	private String currency;
	private Double totalValue;
	private Long totalWeight;
	private String taxId;
	private Long brokerAddressId;
	private Address brokerAddress;
	private String exportData; //General field for exporting-country-specific export data (e.g. B13A for CA, FTSR Exemption or AES Citation for US).
	private String exportDataName;
	private String incoTerms;
	
	public String getIncoTerms() {
		return incoTerms;
	} 

	public void setIncoTerms(String incoTerms) {
		this.incoTerms = incoTerms;
	}

	private List<CustomsInvoiceProduct> products;
	
	public Long getBuyerAddressId() {
		return buyerAddressId;
	}

	public void setBuyerAddressId(Long buyerAddressId) {
		this.buyerAddressId = buyerAddressId;
	}

	public Address getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(Address buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getBillTo() {
		return billTo;
	}

	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}

	public String getBillToAccountNum() {
		return billToAccountNum;
	}

	public void setBillToAccountNum(String billToAccountNum) {
		this.billToAccountNum = billToAccountNum;
	}

	public Long getBillToAddressId() {
		return billToAddressId;
	}

	public void setBillToAddressId(Long billToAddressId) {
		this.billToAddressId = billToAddressId;
	}

	public Address getBillToAddress() {
		return billToAddress;
	}

	public void setBillToAddress(Address billToAddress) {
		this.billToAddress = billToAddress;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		if(reference!=null){
		this.reference = reference.replaceAll(",", "");
		}else{
			this.reference = reference;
		}
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) {
		this.totalValue = totalValue;
	}

	public Long getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Long totalWeight) {
		this.totalWeight = totalWeight;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public Long getBrokerAddressId() {
		return brokerAddressId;
	}

	public void setBrokerAddressId(Long brokerAddressId) {
		this.brokerAddressId = brokerAddressId;
	}

	public Address getBrokerAddress() {
		return brokerAddress;
	}

	public void setBrokerAddress(Address brokerAddress) {
		this.brokerAddress = brokerAddress;
	}

	public List<CustomsInvoiceProduct> getProducts() {
		return products;
	}

	public void setProducts(List<CustomsInvoiceProduct> products) {
		this.products = products;
	}

	public String getExportData() {
		return exportData;
	}
	public void setExportData(String exportData) {
		this.exportData = exportData;
	}

	public String getExportDataName() {
		return exportDataName;
	}

	public void setExportDataName(String exportDataName) {
		this.exportDataName = exportDataName;
	}
	
}
