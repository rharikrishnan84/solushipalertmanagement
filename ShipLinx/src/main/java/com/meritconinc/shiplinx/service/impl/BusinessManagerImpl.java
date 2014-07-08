package com.meritconinc.shiplinx.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.meritconinc.mmr.dao.UserDAO;
import com.meritconinc.mmr.exception.CustomerNameAlreadyTakenException;
import com.meritconinc.mmr.exception.UsernameAlreadyTakenException;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.service.BusinessManager;

public class BusinessManagerImpl implements BusinessManager{
	
	private static final Logger log = LogManager.getLogger(BusinessManagerImpl.class);
	BusinessDAO businessDAO;
	/**
	 * @param businessDAO the businessDAO to set
	 */
	public void setBusinessDAO(BusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	} 

	public boolean isBusinessRegistered(String username){
		return businessDAO.isBusinessRegistered(username);
	}
	
	public List<Business> search(Business business){
		return businessDAO.search(business);
	}

	public void add(final Business business)throws Exception{
		
		try{
		if(businessDAO.isBusinessRegistered(business.getName())){
			throw new CustomerNameAlreadyTakenException(business.getName());
		}
		
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	
	
	public void edit(final Business business,String businessOldName)throws Exception{
		
		try{
		if((!businessOldName.equals(business.getName())) && businessDAO.isBusinessRegistered(business.getName())) {
			throw new CustomerNameAlreadyTakenException(business.getName());
		}
		
		//businessDAO.edit(business);
		
		}catch (CustomerNameAlreadyTakenException e) {
			throw new CustomerNameAlreadyTakenException(business.getName());
		}catch (Exception e) {
			log.error("");
		}
	}
	
	public Business getBusinessById(long businessId){
		return businessDAO.getBusiessById(businessId);
	}
	
	public Business getBusiessByName(String name){
		return businessDAO.getBusinessByName(name);
	}

}