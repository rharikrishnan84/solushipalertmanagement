package com.meritconinc.shiplinx.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.dao.CartDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.BatchShipmentInfo;
import com.meritconinc.shiplinx.model.Cart;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.service.CartManager;
import com.meritconinc.shiplinx.service.ShippingService;

public class CartManagerImpl implements CartManager {
	private static final Logger log = LogManager.getLogger(CartManagerImpl.class);
	private CartDAO cartDAO;
	private ShippingService shippingService;
	private CartOrderParser cartOrderParser;

	/**
	 * @return the cartDAO
	 */
	public CartDAO getCartDAO() {
		return cartDAO;
	} 

	/**
	 * @param cartDAO the cartDAO to set
	 */
	public void setCartDAO(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	/**
	 * @return the shippingService
	 */
	public ShippingService getShippingService() {
		return shippingService;
	}

	/**
	 * @param shippingService the shippingService to set
	 */
	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

	/**
	 * @return the cartOrderParser
	 */
	public CartOrderParser getCartOrderParser() {
		return cartOrderParser;
	}

	/**
	 * @param cartOrderParser the cartOrderParser to set
	 */
	public void setCartOrderParser(CartOrderParser cartOrderParser) {
		this.cartOrderParser = cartOrderParser;
	}

	// Get List of Carts By BusinessId
	public List<Cart> getCartListByBusinessId(long businessId) {
		try {
			return cartDAO.getCartListByBusinessId(businessId);
		}catch (Exception ex) {
			log.error("Exception occured to getCartListByBusinessId() method in CartManagerImpl::",ex);
			throw new ShiplinxException(ex.getMessage());
		}
	}

	// Get List of Carts By BusinessId and Customer Id
	public List<Cart> getCustomerCartByCustomerIdandBusinessId(long customerId,long businessId) {
		try {
			return cartDAO.getCustomerCartByCustomerIdandBusinessId(customerId,businessId);
		}catch (Exception ex) {
			log.error("Exception occured to getCustomerCartByCustomerIdandBusinessId() method in CartManagerImpl::",ex);
			throw new ShiplinxException(ex.getMessage());
		}
	}

	// Here Process the Shipement
	public List<ShippingOrder> processShipmentNow(List<ShippingOrder> shipments, Cart cart) {

		try {
			if (shipments != null && cart != null && cart.getCarrierId() != null && cart.getServiceId() != null) {
				BatchShipmentInfo batchShipmentInfo = new BatchShipmentInfo();
				batchShipmentInfo.setBusinessId(UserUtil.getMmrUser().getBusinessId());
				batchShipmentInfo.setCarrierId(cart.getCarrierId());
				batchShipmentInfo.setServiceId(cart.getServiceId());
				return shippingService.processBatchShipments(shipments, batchShipmentInfo);
			}
		} catch (Exception ex) {
			log.error("Exception occured to processShipmentNow() method in CartManagerImpl::",ex);
			throw new ShiplinxException(ex.getMessage());
		}
		return null;
	}

	// Save the shipments into database
	public List<ShippingOrder> createBatchShipments(List<ShippingOrder> shipments) {
		try{
			return shippingService.createBatchShipments(shipments);
		}catch (Exception ex) {
			log.error("Exception occured to createBatchShipments() method in CartManagerImpl::",ex);
			throw new ShiplinxException(ex.getMessage());
		}
	}

	// Get All Unship Orders from Cart 
	public List<ShippingOrder> getAllUnshippedOrder(ShippingOrder so, Cart cart, String cartOrderId){
		try{
			List<ShippingOrder> orderList = new ArrayList<ShippingOrder>();
			orderList = cartOrderParser.getAllUnshippedOrder(so, cart, cartOrderId);
			return orderList;
		}
		catch(Exception ex){
			log.error("Exception occured to getAllUnshippedOrder() method in CartManagerImpl::",ex);
			throw new ShiplinxException(ex.getMessage());
		}
	}

	// Save Cart into customer_cart table
	public void saveCustomerCartDetail(Cart cart){
		try{
			cartDAO.saveCustomerCartDetail(cart);
		}catch(Exception ex){
			log.error("Exception occured to saveCustomerCartDetail() method in CartManagerImpl::",ex);
			throw new ShiplinxException(ex.getMessage());
		}
	}
}
