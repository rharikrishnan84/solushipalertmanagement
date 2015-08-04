
package com.meritconinc.shiplinx.api.model;

import java.util.List;

public class Line_items{
   	private Number fulfillable_quantity;
   	private String fulfillment_service;
   	private String fulfillment_status;
   	private boolean gift_card;
   	private Number grams;
   	private Number id;
   	private String name;
   	private String price;
   	private boolean product_exists;
   	private Number product_id;
   	private List properties;
   	private Number quantity;
   	private boolean requires_shipping;
   	private String sku;
   	private List tax_lines;
   	private boolean taxable;
   	private String title;
   	private String total_discount;
   	private Number variant_id;
   	private String variant_inventory_management;
   	private String variant_title;
   	private String vendor;

 	public Number getFulfillable_quantity(){
		return this.fulfillable_quantity;
	}
	public void setFulfillable_quantity(Number fulfillable_quantity){
		this.fulfillable_quantity = fulfillable_quantity;
	}
 	public String getFulfillment_service(){
		return this.fulfillment_service;
	}
	public void setFulfillment_service(String fulfillment_service){
		this.fulfillment_service = fulfillment_service;
	}
 	public String getFulfillment_status(){
		return this.fulfillment_status;
	}
	public void setFulfillment_status(String fulfillment_status){
		this.fulfillment_status = fulfillment_status;
	}
 	public boolean getGift_card(){
		return this.gift_card;
	}
	public void setGift_card(boolean gift_card){
		this.gift_card = gift_card;
	}
 	public Number getGrams(){
		return this.grams;
	}
	public void setGrams(Number grams){
		this.grams = grams;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getPrice(){
		return this.price;
	}
	public void setPrice(String price){
		this.price = price;
	}
 	public boolean getProduct_exists(){
		return this.product_exists;
	}
	public void setProduct_exists(boolean product_exists){
		this.product_exists = product_exists;
	}
 	public Number getProduct_id(){
		return this.product_id;
	}
	public void setProduct_id(Number product_id){
		this.product_id = product_id;
	}
 	public List getProperties(){
		return this.properties;
	}
	public void setProperties(List properties){
		this.properties = properties;
	}
 	public Number getQuantity(){
		return this.quantity;
	}
	public void setQuantity(Number quantity){
		this.quantity = quantity;
	}
 	public boolean getRequires_shipping(){
		return this.requires_shipping;
	}
	public void setRequires_shipping(boolean requires_shipping){
		this.requires_shipping = requires_shipping;
	}
 	public String getSku(){
		return this.sku;
	}
	public void setSku(String sku){
		this.sku = sku;
	}
 	public List getTax_lines(){
		return this.tax_lines;
	}
	public void setTax_lines(List tax_lines){
		this.tax_lines = tax_lines;
	}
 	public boolean getTaxable(){
		return this.taxable;
	}
	public void setTaxable(boolean taxable){
		this.taxable = taxable;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
 	public String getTotal_discount(){
		return this.total_discount;
	}
	public void setTotal_discount(String total_discount){
		this.total_discount = total_discount;
	}
 	public Number getVariant_id(){
		return this.variant_id;
	}
	public void setVariant_id(Number variant_id){
		this.variant_id = variant_id;
	}
 	public String getVariant_inventory_management(){
		return this.variant_inventory_management;
	}
	public void setVariant_inventory_management(String variant_inventory_management){
		this.variant_inventory_management = variant_inventory_management;
	}
 	public String getVariant_title(){
		return this.variant_title;
	}
	public void setVariant_title(String variant_title){
		this.variant_title = variant_title;
	}
 	public String getVendor(){
		return this.vendor;
	}
	public void setVendor(String vendor){
		this.vendor = vendor;
	}
}
