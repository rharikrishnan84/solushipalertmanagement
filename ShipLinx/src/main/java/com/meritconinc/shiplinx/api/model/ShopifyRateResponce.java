package com.meritconinc.shiplinx.api.model;

import java.util.Date;

public class ShopifyRateResponce {

	
	private String service_name;
	private String service_code;
	private double total_price;
	private String currency;
	private String min_delivery_date;
	private String max_delivery_date;
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getService_code() {
		return service_code;
	}
	public void setService_code(String service_code) {
		this.service_code = service_code;
	}
 
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	 
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public String getMin_delivery_date() {
		return min_delivery_date;
	}
	public void setMin_delivery_date(String min_delivery_date) {
		this.min_delivery_date = min_delivery_date;
	}
	public String getMax_delivery_date() {
		return max_delivery_date;
	}
	public void setMax_delivery_date(String max_delivery_date) {
		this.max_delivery_date = max_delivery_date;
	}
	
}
