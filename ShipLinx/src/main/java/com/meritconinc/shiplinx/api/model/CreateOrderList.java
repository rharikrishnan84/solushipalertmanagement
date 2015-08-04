package com.meritconinc.shiplinx.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.ShippingOrder;

public class CreateOrderList implements Serializable{
private static final long serialVersionUID = 8475010962885555252L;
	
	
private ShippingOrder orders; 

private Rating rating;
public Rating getRating() {
	return rating;
}
public void setRating(Rating rating) {
	this.rating = rating;
}
public ShippingOrder getOrders() {
	return orders;
}
public void setOrders(ShippingOrder orders) {
	this.orders = orders;
}
}
