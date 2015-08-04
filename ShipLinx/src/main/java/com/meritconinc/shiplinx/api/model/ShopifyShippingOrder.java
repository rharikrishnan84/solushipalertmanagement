
package com.meritconinc.shiplinx.api.model;

import java.util.List;

public class ShopifyShippingOrder{
	
   	private Billing_address billing_address;
   	private String browser_ip;
   	private boolean buyer_accepts_marketing;
   	private String cancel_reason;
   	private String cancelled_at;
   	private String cart_token;
   	private Number checkout_id;
   	private String checkout_token;
   	private Client_details client_details;
   	private String closed_at;
   	private boolean confirmed;
   	private String created_at;
   	private String currency;
   	private Customer customer;
   	private String device_id;
   	private List discount_codes;
   	private String email;
   	private String financial_status;
   	private String fulfillment_status;
   	private List fulfillments;
   	private String gateway;
   	private Number id;
   	private String landing_site;
   	private String landing_site_ref;
   	private List<Line_items> line_items;
   	private String location_id;
   	private String name;
   	private String note;
   	private List note_attributes;
   	private Number number;
   	private Number order_number;
   	private Payment_details payment_details;
   	private List payment_gateway_names;
   	private String processed_at;
   	private String processing_method;
   	private String reference;
   	private String referring_site;
   	private List refunds;
   	private Shipping_address shipping_address;
   	private List<Shipping_lines> shipping_lines;
   	private String source;
   	private String source_identifier;
   	private String source_name;
   	private String source_url;
   	private String subtotal_price;
   	private String tags;
   	private List tax_lines;
   	private boolean taxes_included;
   	private boolean test;
   	private String token;
   	private String total_discounts;
   	private String total_line_items_price;
   	private String total_price;
   	private String total_price_usd;
   	private String total_tax;
   	private Double total_weight;
   	private String updated_at;
   	private String user_id;
   	private String storeName;

 	public Billing_address getBilling_address(){
		return this.billing_address;
	}
	public void setBilling_address(Billing_address billing_address){
		this.billing_address = billing_address;
	}
 	public String getBrowser_ip(){
		return this.browser_ip;
	}
	public void setBrowser_ip(String browser_ip){
		this.browser_ip = browser_ip;
	}
 	public boolean getBuyer_accepts_marketing(){
		return this.buyer_accepts_marketing;
	}
	public void setBuyer_accepts_marketing(boolean buyer_accepts_marketing){
		this.buyer_accepts_marketing = buyer_accepts_marketing;
	}
 	public String getCancel_reason(){
		return this.cancel_reason;
	}
	public void setCancel_reason(String cancel_reason){
		this.cancel_reason = cancel_reason;
	}
 	public String getCancelled_at(){
		return this.cancelled_at;
	}
	public void setCancelled_at(String cancelled_at){
		this.cancelled_at = cancelled_at;
	}
 	public String getCart_token(){
		return this.cart_token;
	}
	public void setCart_token(String cart_token){
		this.cart_token = cart_token;
	}
 	public Number getCheckout_id(){
		return this.checkout_id;
	}
	public void setCheckout_id(Number checkout_id){
		this.checkout_id = checkout_id;
	}
 	public String getCheckout_token(){
		return this.checkout_token;
	}
	public void setCheckout_token(String checkout_token){
		this.checkout_token = checkout_token;
	}
 	public Client_details getClient_details(){
		return this.client_details;
	}
	public void setClient_details(Client_details client_details){
		this.client_details = client_details;
	}
 	public String getClosed_at(){
		return this.closed_at;
	}
	public void setClosed_at(String closed_at){
		this.closed_at = closed_at;
	}
 	public boolean getConfirmed(){
		return this.confirmed;
	}
	public void setConfirmed(boolean confirmed){
		this.confirmed = confirmed;
	}
 	public String getCreated_at(){
		return this.created_at;
	}
	public void setCreated_at(String created_at){
		this.created_at = created_at;
	}
 	public String getCurrency(){
		return this.currency;
	}
	public void setCurrency(String currency){
		this.currency = currency;
	}
 	public Customer getCustomer(){
		return this.customer;
	}
	public void setCustomer(Customer customer){
		this.customer = customer;
	}
 	public String getDevice_id(){
		return this.device_id;
	}
	public void setDevice_id(String device_id){
		this.device_id = device_id;
	}
 	public List getDiscount_codes(){
		return this.discount_codes;
	}
	public void setDiscount_codes(List discount_codes){
		this.discount_codes = discount_codes;
	}
 	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
	}
 	public String getFinancial_status(){
		return this.financial_status;
	}
	public void setFinancial_status(String financial_status){
		this.financial_status = financial_status;
	}
 	public String getFulfillment_status(){
		return this.fulfillment_status;
	}
	public void setFulfillment_status(String fulfillment_status){
		this.fulfillment_status = fulfillment_status;
	}
 	public List getFulfillments(){
		return this.fulfillments;
	}
	public void setFulfillments(List fulfillments){
		this.fulfillments = fulfillments;
	}
 	public String getGateway(){
		return this.gateway;
	}
	public void setGateway(String gateway){
		this.gateway = gateway;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public String getLanding_site(){
		return this.landing_site;
	}
	public void setLanding_site(String landing_site){
		this.landing_site = landing_site;
	}
 	public String getLanding_site_ref(){
		return this.landing_site_ref;
	}
	public void setLanding_site_ref(String landing_site_ref){
		this.landing_site_ref = landing_site_ref;
	}
  
 	public String getLocation_id(){
		return this.location_id;
	}
	public void setLocation_id(String location_id){
		this.location_id = location_id;
	}
 	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
 	public String getNote(){
		return this.note;
	}
	public void setNote(String note){
		this.note = note;
	}
 	public List getNote_attributes(){
		return this.note_attributes;
	}
	public void setNote_attributes(List note_attributes){
		this.note_attributes = note_attributes;
	}
 	public Number getNumber(){
		return this.number;
	}
	public void setNumber(Number number){
		this.number = number;
	}
 	public Number getOrder_number(){
		return this.order_number;
	}
	public void setOrder_number(Number order_number){
		this.order_number = order_number;
	}
 	public Payment_details getPayment_details(){
		return this.payment_details;
	}
	public void setPayment_details(Payment_details payment_details){
		this.payment_details = payment_details;
	}
 	public List getPayment_gateway_names(){
		return this.payment_gateway_names;
	}
	public void setPayment_gateway_names(List payment_gateway_names){
		this.payment_gateway_names = payment_gateway_names;
	}
 	public String getProcessed_at(){
		return this.processed_at;
	}
	public void setProcessed_at(String processed_at){
		this.processed_at = processed_at;
	}
 	public String getProcessing_method(){
		return this.processing_method;
	}
	public void setProcessing_method(String processing_method){
		this.processing_method = processing_method;
	}
 	public String getReference(){
		return this.reference;
	}
	public void setReference(String reference){
		this.reference = reference;
	}
 	public String getReferring_site(){
		return this.referring_site;
	}
	public void setReferring_site(String referring_site){
		this.referring_site = referring_site;
	}
 	public List getRefunds(){
		return this.refunds;
	}
	public void setRefunds(List refunds){
		this.refunds = refunds;
	}
 	public Shipping_address getShipping_address(){
		return this.shipping_address;
	}
	public void setShipping_address(Shipping_address shipping_address){
		this.shipping_address = shipping_address;
	}
 	public List<Shipping_lines> getShipping_lines(){
		return this.shipping_lines;
	}
	public void setShipping_lines(List<Shipping_lines> shipping_lines){
		this.shipping_lines = shipping_lines;
	}
 	public String getSource(){
		return this.source;
	}
	public void setSource(String source){
		this.source = source;
	}
 	public String getSource_identifier(){
		return this.source_identifier;
	}
	public void setSource_identifier(String source_identifier){
		this.source_identifier = source_identifier;
	}
 	public String getSource_name(){
		return this.source_name;
	}
	public void setSource_name(String source_name){
		this.source_name = source_name;
	}
 	public String getSource_url(){
		return this.source_url;
	}
	public void setSource_url(String source_url){
		this.source_url = source_url;
	}
 	public String getSubtotal_price(){
		return this.subtotal_price;
	}
	public void setSubtotal_price(String subtotal_price){
		this.subtotal_price = subtotal_price;
	}
 	public String getTags(){
		return this.tags;
	}
	public void setTags(String tags){
		this.tags = tags;
	}
 	public List getTax_lines(){
		return this.tax_lines;
	}
	public void setTax_lines(List tax_lines){
		this.tax_lines = tax_lines;
	}
 	public boolean getTaxes_included(){
		return this.taxes_included;
	}
	public void setTaxes_included(boolean taxes_included){
		this.taxes_included = taxes_included;
	}
 	public boolean getTest(){
		return this.test;
	}
	public void setTest(boolean test){
		this.test = test;
	}
 	public String getToken(){
		return this.token;
	}
	public void setToken(String token){
		this.token = token;
	}
 	public String getTotal_discounts(){
		return this.total_discounts;
	}
	public void setTotal_discounts(String total_discounts){
		this.total_discounts = total_discounts;
	}
 	public String getTotal_line_items_price(){
		return this.total_line_items_price;
	}
	public void setTotal_line_items_price(String total_line_items_price){
		this.total_line_items_price = total_line_items_price;
	}
 	public String getTotal_price(){
		return this.total_price;
	}
	public void setTotal_price(String total_price){
		this.total_price = total_price;
	}
 	public String getTotal_price_usd(){
		return this.total_price_usd;
	}
	public void setTotal_price_usd(String total_price_usd){
		this.total_price_usd = total_price_usd;
	}
 	public String getTotal_tax(){
		return this.total_tax;
	}
	public void setTotal_tax(String total_tax){
		this.total_tax = total_tax;
	}
 	 
 
 	public String getUpdated_at(){
		return this.updated_at;
	}
	public void setUpdated_at(String updated_at){
		this.updated_at = updated_at;
	}
 	public String getUser_id(){
		return this.user_id;
	}
	public void setUser_id(String user_id){
		this.user_id = user_id;
	}
	public Double getTotal_weight() {
		return total_weight;
	}
	public void setTotal_weight(Double total_weight) {
		this.total_weight = total_weight;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public List<Line_items> getLine_items() {
		return line_items;
	}
	public void setLine_items(List<Line_items> line_items) {
		this.line_items = line_items;
	}
}
