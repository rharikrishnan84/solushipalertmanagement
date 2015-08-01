package com.meritconinc.shiplinx.dao;


import java.util.List; 

import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.UserBusiness;
import com.meritconinc.shiplinx.model.BusinessEmail;
import com.meritconinc.shiplinx.model.CSSVO;
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
					
					public List<UserBusiness> getUserBusinessListByUser(
														String username);
												public void adduserBusiness(UserBusiness ub);
												public void deleteUserBusiness(Long userBusId);
												
												
												public void addCSSDetailsForBusiness(CSSVO cssVO);
												
										public void updateCSSDetailsForBusiness(CSSVO cssVO);
										public CSSVO getCSSDetailsForBusiness(Long businessId);
										public List<BusinessEmail> getBusinessEmails(long businessId);
										public BusinessEmail getBusinessEmailById(long busEmailId);
										public void addBusinessEmail(BusinessEmail bme);
										public void deleteBusinessEmailByBMId(long businessEmailId);
										public void updateBusinessEmail(BusinessEmail be3);
										public void setCopyMarkupFlag(long businessId);
}