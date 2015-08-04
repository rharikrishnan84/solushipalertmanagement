package com.meritconinc.shiplinx.model;


/**
 * 
 * @author SELVA GANESH
 * date- 15-07-2015
 *
 */
public class ProductPackageMap {

	private long productPackageId;
	private long packageId;
	private long  quanity;
	private Long productId;
	private long customerId;
	private PackageTypes packages;
	private Products product;
	
	public long getQuanity() {
		return quanity;
	}
	public void setQuanity(long quanity) {
		this.quanity = quanity;
	}
	 
	public long getPackageId() {
		return packageId;
	}
	public void setPackageId(long packageId) {
		this.packageId = packageId;
	}
	public long getProductPackageId() {
		return productPackageId;
	}
	public void setProductPackageId(long productPackageId) {
		this.productPackageId = productPackageId;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
 
	public Products getProduct() {
		return product;
	}
	public void setProduct(Products product) {
		this.product = product;
	}
	public PackageTypes getPackages() {
		return packages;
	}
	public void setPackages(PackageTypes packages) {
		this.packages = packages;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
}
