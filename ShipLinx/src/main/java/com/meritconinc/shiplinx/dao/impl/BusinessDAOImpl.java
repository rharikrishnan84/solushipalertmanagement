package com.meritconinc.shiplinx.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.Invoice;



public class BusinessDAOImpl extends SqlMapClientDaoSupport implements BusinessDAO{
	private static final Logger log = LogManager.getLogger(BusinessDAOImpl.class);
	
	
	public boolean isBusinessRegistered(String name) {
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		paramObj.put("name", name);
		Integer count =  (Integer)getSqlMapClientTemplate().queryForObject("v", paramObj);
		return (count > 0);
	} 
	
	
	public List<Business> search(Business business){
		List<Business> businesses=null;
		try{
			businesses =  (List<Business>)getSqlMapClientTemplate().queryForList("getBusinesses",business);
		}catch(Exception e){
			e.printStackTrace();
		}
		return businesses;
	}

	public Business getBusiessById(long businessId){
		return (Business)getSqlMapClientTemplate().queryForObject("getBusinessesById", businessId);		
	}

	public Business getBusinessByName(String name){
		return (Business)getSqlMapClientTemplate().queryForObject("getBusinessesByName", name);		
	}


}