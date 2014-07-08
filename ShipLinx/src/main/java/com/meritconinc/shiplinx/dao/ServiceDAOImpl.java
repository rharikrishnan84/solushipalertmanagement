package com.meritconinc.shiplinx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Service;

public class ServiceDAOImpl extends SqlMapClientDaoSupport implements ServiceDAO{
	private static final Logger log = LogManager.getLogger(ServiceDAOImpl.class);

	public Service getService(Long serviceId) {
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		logger.debug(" ------ CHECK ID DAO  "+serviceId);
		paramObj.put("serviceId", serviceId);
		Service service =  (Service)getSqlMapClientTemplate().queryForObject("getService", paramObj);
		logger.debug(" ------ service IN DAO  "+service);
		return service;
	} 
}