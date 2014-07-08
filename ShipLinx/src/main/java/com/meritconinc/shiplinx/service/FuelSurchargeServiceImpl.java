package com.meritconinc.shiplinx.service;

import java.util.List;

import com.meritconinc.shiplinx.dao.FuelSurchargeDAO;
import com.meritconinc.shiplinx.model.FuelSurcharge;

public class FuelSurchargeServiceImpl implements FuelSurchargeService {

private FuelSurchargeDAO dao;
	
	public void setFuelSurchargeDAO(FuelSurchargeDAO dao) {
		this.dao = dao;
	}
	
	public List<FuelSurcharge> getFuelSurcharge(FuelSurcharge fuelSurcharge) {
		return dao.getFuelSurcharge(fuelSurcharge);
	}
}
 