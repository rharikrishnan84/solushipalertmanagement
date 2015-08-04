
package com.meritconinc.shiplinx.api.model;

import java.util.List;

public class Customer{
   	private boolean accepts_marketing;
   	private String created_at;
   	private Default_address default_address;
   	private String email;
   	private String first_name;
   	private Number id;
   	private String last_name;
   	private String last_order_id;
   	private String last_order_name;
   	private String multipass_identifier;
   	private String note;
   	private Number orders_count;
   	private String state;
   	private String tags;
   	private boolean tax_exempt;
   	private String total_spent;
   	private String updated_at;
   	private boolean verified_email;

 	public boolean getAccepts_marketing(){
		return this.accepts_marketing;
	}
	public void setAccepts_marketing(boolean accepts_marketing){
		this.accepts_marketing = accepts_marketing;
	}
 	public String getCreated_at(){
		return this.created_at;
	}
	public void setCreated_at(String created_at){
		this.created_at = created_at;
	}
 	public Default_address getDefault_address(){
		return this.default_address;
	}
	public void setDefault_address(Default_address default_address){
		this.default_address = default_address;
	}
 	public String getEmail(){
		return this.email;
	}
	public void setEmail(String email){
		this.email = email;
	}
 	public String getFirst_name(){
		return this.first_name;
	}
	public void setFirst_name(String first_name){
		this.first_name = first_name;
	}
 	public Number getId(){
		return this.id;
	}
	public void setId(Number id){
		this.id = id;
	}
 	public String getLast_name(){
		return this.last_name;
	}
	public void setLast_name(String last_name){
		this.last_name = last_name;
	}
 	public String getLast_order_id(){
		return this.last_order_id;
	}
	public void setLast_order_id(String last_order_id){
		this.last_order_id = last_order_id;
	}
 	public String getLast_order_name(){
		return this.last_order_name;
	}
	public void setLast_order_name(String last_order_name){
		this.last_order_name = last_order_name;
	}
 	public String getMultipass_identifier(){
		return this.multipass_identifier;
	}
	public void setMultipass_identifier(String multipass_identifier){
		this.multipass_identifier = multipass_identifier;
	}
 	public String getNote(){
		return this.note;
	}
	public void setNote(String note){
		this.note = note;
	}
 	public Number getOrders_count(){
		return this.orders_count;
	}
	public void setOrders_count(Number orders_count){
		this.orders_count = orders_count;
	}
 	public String getState(){
		return this.state;
	}
	public void setState(String state){
		this.state = state;
	}
 	public String getTags(){
		return this.tags;
	}
	public void setTags(String tags){
		this.tags = tags;
	}
 	public boolean getTax_exempt(){
		return this.tax_exempt;
	}
	public void setTax_exempt(boolean tax_exempt){
		this.tax_exempt = tax_exempt;
	}
 	public String getTotal_spent(){
		return this.total_spent;
	}
	public void setTotal_spent(String total_spent){
		this.total_spent = total_spent;
	}
 	public String getUpdated_at(){
		return this.updated_at;
	}
	public void setUpdated_at(String updated_at){
		this.updated_at = updated_at;
	}
 	public boolean getVerified_email(){
		return this.verified_email;
	}
	public void setVerified_email(boolean verified_email){
		this.verified_email = verified_email;
	}
	
	
	
}
