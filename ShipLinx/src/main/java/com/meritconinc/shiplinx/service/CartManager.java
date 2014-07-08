package com.meritconinc.shiplinx.service;

import java.util.List;

import com.meritconinc.shiplinx.model.Cart;
import com.meritconinc.shiplinx.model.ShippingOrder;

public interface CartManager {
	
	//Get List of Carts by Business Id
	public List<Cart> getCartListByBusinessId(long businessId);
	
	//Get List of Carts by Business Id and Customer Id
	public List<Cart> getCustomerCartByCustomerIdandBusinessId(long customerId,long businessId);
	
	//Get All Unshipped orders from Cart
	public List<ShippingOrder> getAllUnshippedOrder(ShippingOrder so, Cart cart, String cartOrderId);
	 
	// Process the shipment
	public List<ShippingOrder> processShipmentNow(List<ShippingOrder> shipments, Cart cart);
	
	//Save Shipments to Database
	public List<ShippingOrder> createBatchShipments(List<ShippingOrder> shipments);
	
	// Save Customer Cart
	public void saveCustomerCartDetail(Cart cart);
	
}
