package com.meritconinc.shiplinx.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.dao.CartDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Cart;

public class CartDAOImpl extends SqlMapClientDaoSupport implements CartDAO {

	private static final Logger log = LogManager.getLogger(CartDAOImpl.class);

	public List<Cart> getCartListByBusinessId(long businessId) {
		try{
			Map<String, Long> map = new HashMap<String, Long>(1);
			map.put("businessId", businessId);
			return getSqlMapClientTemplate().queryForList("getCartListByBusinessId",map);}
		catch(Exception ex){
			log.error("Exception occured to getCartListByBusinessId() method in CartDAOImpl::",ex);
			throw new ShiplinxException(ex.getMessage());
		}
	} 

	public List<Cart> getCustomerCartByCustomerIdandBusinessId(long customerId,long businessId) {
		try{
			Map<String, Long> map = new HashMap<String, Long>(1);
			map.put("customerId", customerId);
			map.put("businessId", businessId);
			return getSqlMapClientTemplate().queryForList("getCustomerCartByCustomerIdandBusinessId",map);}
		catch(Exception ex){
			log.error("Exception occured to getCustomerCartByCustomerIdandBusinessId() method in CartDAOImpl::",ex);
			throw new ShiplinxException(ex.getMessage());
		}
	}

	public void saveCustomerCartDetail(Cart cart){
		try{
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cart_id", cart.getCartId());
			map.put("customer_id", cart.getCustomerId());
			map.put("business_id", cart.getBusinessId());
			map.put("property_1", cart.getUrlName());
			map.put("property_2", cart.getToken());
			getSqlMapClientTemplate().insert("saveCustomerCart", map);
		}catch(Exception ex){
			log.error("Exception occured to saveCustomerCartDetail() method in CartDAOImpl::",ex);
			throw new ShiplinxException(ex.getMessage());
		}
	}
}
