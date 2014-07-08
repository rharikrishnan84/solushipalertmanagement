package com.meritconinc.shiplinx.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.model.FuelSurcharge;


public class FuelSurchargeDAOImpl  extends SqlMapClientDaoSupport implements
		FuelSurchargeDAO {


	public List<FuelSurcharge> getFuelSurcharge(FuelSurcharge fuelSurcharge) {
		List<FuelSurcharge> fuelSurcharges =  (List)getSqlMapClientTemplate().queryForList("getFuelSurcharges", fuelSurcharge);
		logger.debug(" ------ CHECK RETRIEVED customerCarrier IN DAO  "+fuelSurcharge);
		return fuelSurcharges;
	}

} 

