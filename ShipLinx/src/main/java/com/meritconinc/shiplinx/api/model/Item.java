package com.meritconinc.shiplinx.api.model;

import java.util.List;
import java.util.Map;

public class Item {
	//cast form shopify
	 private String price;

	    private String product_id;

	    private String taxable;

	    private String requires_shipping;

	    private String vendor;

	    private String name;

	    private String variant_id;

	    private String grams;

	    private String quantity;

	    private String properties;

	    private String fulfillment_service;

	    private String sku;
	
	//for soluship
	private Long productSolushipId;
	private Long packageTypesId;
	private Long packageSlice;
	private Long remainSlice;
	private float weidthlb;
	private List<Long> cartVarients;
	private Package itemPack;

	 

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProduct_id() {
		return product_id;
	}

	@Override
	public String toString() {
		return "Item [productSolushipId=" + productSolushipId
				+ ", packageTypesId=" + packageTypesId + ", packageSlice="
				+ packageSlice + ", remainSlice=" + remainSlice + ", weidthlb="
				+ weidthlb + ", cartVarients=" + cartVarients + "]";
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getTaxable() {
		return taxable;
	}

	public void setTaxable(String taxable) {
		this.taxable = taxable;
	}

	public String getRequires_shipping() {
		return requires_shipping;
	}

	public void setRequires_shipping(String requires_shipping) {
		this.requires_shipping = requires_shipping;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVariant_id() {
		return variant_id;
	}

	public void setVariant_id(String variant_id) {
		this.variant_id = variant_id;
	}

	public String getGrams() {
		return grams;
	}

	public void setGrams(String grams) {
		this.grams = grams;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getProperties() {
		return properties;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public String getFulfillment_service() {
		return fulfillment_service;
	}

	public void setFulfillment_service(String fulfillment_service) {
		this.fulfillment_service = fulfillment_service;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Long getProductSolushipId() {
		return productSolushipId;
	}

	public void setProductSolushipId(Long productSolushipId) {
		this.productSolushipId = productSolushipId;
	}

	public Long getPackageTypesId() {
		return packageTypesId;
	}

	public void setPackageTypesId(Long packageTypesId) {
		this.packageTypesId = packageTypesId;
	}

	public Long getPackageSlice() {
		return packageSlice;
	}

	public void setPackageSlice(Long packageSlice) {
		this.packageSlice = packageSlice;
	}

	public Long getRemainSlice() {
		return remainSlice;
	}

	public void setRemainSlice(Long remainSlice) {
		this.remainSlice = remainSlice;
	}

	public float getWeidthlb() {
		return weidthlb;
	}

	public void setWeidthlb(float weidthlb) {
		this.weidthlb = weidthlb;
	}

	public List<Long> getCartVarients() {
		return cartVarients;
	}

	public void setCartVarients(List<Long> cartVarients) {
		this.cartVarients = cartVarients;
	}

	public Package getItemPack() {
		return itemPack;
	}

	public void setItemPack(Package itemPack) {
		this.itemPack = itemPack;
	}
	
 	 
	 
 

}
