package com.meritconinc.shiplinx.dao;

import java.util.List;

import com.meritconinc.shiplinx.model.Cart;
import com.meritconinc.shiplinx.model.EdiInfo;
import com.meritconinc.shiplinx.model.EdiItem;

public interface CartDAO {
	
	public List<Cart> getCartListByBusinessId(long businessId);
	
	public List<Cart> getCustomerCartByCustomerIdandBusinessId(long customerId,long businessId);
	
	//public Cart getCartByCartId(long cartId,long businessId);
	
	// Save Customer Cart
	public void saveCustomerCartDetail(Cart cart);
} 
