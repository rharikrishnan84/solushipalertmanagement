
package com.meritconinc.shiplinx.api.model;

import java.util.List;

public class Tax_lines{
   	private String price;
   	private Number rate;
   	private String title;

 	public String getPrice(){
		return this.price;
	}
	public void setPrice(String price){
		this.price = price;
	}
 	public Number getRate(){
		return this.rate;
	}
	public void setRate(Number rate){
		this.rate = rate;
	}
 	public String getTitle(){
		return this.title;
	}
	public void setTitle(String title){
		this.title = title;
	}
}
