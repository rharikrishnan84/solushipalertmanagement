package com.meritconinc.shiplinx.dao;


import java.util.List; 

import com.meritconinc.shiplinx.model.Business;

public interface BusinessDAO{
	
	public boolean isBusinessRegistered(String username);
	List<Business> search(Business customer);
	public Business getBusiessById(long businessId);
	public Business getBusinessByName(String name);

}