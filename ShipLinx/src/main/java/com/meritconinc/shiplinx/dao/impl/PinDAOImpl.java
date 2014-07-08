package com.meritconinc.shiplinx.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.dao.PinDAO;
import com.meritconinc.shiplinx.model.PinNumberBlock;

public class PinDAOImpl extends SqlMapClientDaoSupport implements PinDAO{

	public void saveBlock(PinNumberBlock pin_block) {
		try{
			getSqlMapClientTemplate().update("updatePinBlock", pin_block);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	public PinNumberBlock getBlock(String type, long businessId) {
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		paramObj.put("businessId", businessId);
		paramObj.put("type", type);
		
		PinNumberBlock pinBlock =  (PinNumberBlock)getSqlMapClientTemplate().queryForObject("getPinBlock", paramObj);
		return pinBlock;
	}  
	
	public String getPropertyValue(String propertyName){
		return null;
		
	}
	
}
