package com.meritconinc.shiplinx.service;

import java.util.List;

import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;

public interface BusinessManager{
	
	public boolean isBusinessRegistered(String username);
	List<Business> search(Business customer);
	Business getBusinessById(long businessId);
	public Business getBusiessByName(String name);
	
} 