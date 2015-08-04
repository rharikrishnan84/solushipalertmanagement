
package com.meritconinc.shiplinx.api.model;

import java.util.List;

public class Shipping_lines{
   	private String code;
   	private String price;
   	private String source;
   	private List tax_lines;
   	private String title;

 	public String getCode(){
		return this.code;
	}
	public void setCode(String code){
		this.code = code;
	}
 	public String getPrice(){
		return this.price;
	}
	public void setPrice(String price){
		this.price = price;
	}
 	public String getSource(){
		return this.source;
	}
	public void setSource(String source){
		this.source = source;
	}
 	public List getTax_lines(){
		return this.tax_lines;
	}
	public void setTax_lines(List tax_lines){
		this.tax_lines = tax_lines;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
}
