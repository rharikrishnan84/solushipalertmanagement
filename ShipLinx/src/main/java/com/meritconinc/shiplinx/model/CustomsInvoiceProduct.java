package com.meritconinc.shiplinx.model;

import java.math.BigDecimal;

public class CustomsInvoiceProduct {

	private long productId;
	private long customsInvoiceId;
	private String productDesc;
	private String productHC;
	private String productOrigin;
	private int productQuantity;
	private double productUnitPrice;
	private BigDecimal unitPrice;
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	private Long productWeight;
	public long getCustomsInvoiceId() {
		return customsInvoiceId;
	}
	public void setCustomsInvoiceId(long customsInvoiceId) {
		this.customsInvoiceId = customsInvoiceId;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductHC() {
		return productHC;
	} 
	public void setProductHC(String productHC) {
		this.productHC = productHC;
	}
	public String getProductOrigin() {
		return productOrigin;
	}
	public void setProductOrigin(String productOrigin) {
		this.productOrigin = productOrigin;
	}
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public double getProductUnitPrice() {
		return productUnitPrice;
	}
	public void setProductUnitPrice(double productUnitPrice) {
		this.productUnitPrice = productUnitPrice;
	}
	public Long getProductWeight() {
		return productWeight;
	}
	public void setProductWeight(Long productWeight) {
		this.productWeight = productWeight;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	
	public Double getProductTotalPrice(){
		double d = 0d;
		return d = (Double.valueOf(this.productQuantity) * Double.valueOf(this.productUnitPrice));
	  
	}
	
}
