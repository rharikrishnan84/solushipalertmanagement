package com.meritconinc.shiplinx.dao;


import java.util.List; 

import com.meritconinc.shiplinx.model.Business;

public interface BusinessDAO{
	
	public boolean isBusinessRegistered(String username);
	List<Business> search(Business customer);
	public Business getBusiessById(long businessId);
	public Business getBusinessByName(String name);
	public List<Business> getAllBusiness();
						public Long addBusiness(Business business);
						
						public void updateBusiness(Business business);
						
						public void deleteBusiness(String businessId);
						public void addDefaultCustomerCarrier(long businessId);
						public void addDefaultPinsToBusiness(long businessId);
						public List<Business> getCountryBusiness(Long partnerBusinessId);
						public long addCountryLevelBusienss(Business business);
						public long addParterLevelBusienss(Business business);
						public List<Business> getPartnerBusiness(Long businessId);
						public long addBranchLevelBusiness(Business business);
						public List<Business> getBranchBuisness(
								Long partnerId, Long countryPartnerId);
						public List<Business> getBusinessListByLevel(long id);
						public List<Business> getHoleBusinessList();
					public Business getPartnerBusinessByName(String name,
								Long businessId);
						public Business getCountryBusinessByName(String name,
								Long partnerId);
						public Business getBranchBusinessByName(String name,
								Long partnerId, Long countryParterId);
					public Business getSuperParentBusiness(Long businessId);
			 
}