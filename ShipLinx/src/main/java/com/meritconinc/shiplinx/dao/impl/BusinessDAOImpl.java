package com.meritconinc.shiplinx.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.dao.BusinessDAO;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.Invoice;
import com.meritconinc.shiplinx.model.UserBusiness;

import java.util.Date;

import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.model.BusinessEmail;
import com.meritconinc.shiplinx.model.CSSVO;
import com.meritconinc.shiplinx.model.BusinessContact;

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
		Business b=(Business)getSqlMapClientTemplate().queryForObject("getBusinessesById", businessId);
						if(b!=null){
							CSSVO css=(CSSVO)getSqlMapClientTemplate().queryForObject("getCSSDetailsForBusiness",businessId);
							if(css!=null){
								b.setCssVO(css);
							}
					}
						Map<String, Object> paramObj = new HashMap<String, Object>(1);
												paramObj.put("businessId", businessId);
												BusinessContact bc=(BusinessContact) getSqlMapClientTemplate().queryForObject("getBusinessContactBybusiness",paramObj);
										
												if(b!=null){
													b.setBusinessContact(bc);
												}

						
						return b;	
	}

	public Business getBusinessByName(String name){
		return (Business)getSqlMapClientTemplate().queryForObject("getBusinessesByName", name);		
	}
	
	
	
	@Override
				public List<Business> getAllBusiness() {
					List<Business> businesses=null;
					try{
						businesses =  (List<Business>)getSqlMapClientTemplate().queryForList("getAllBusinesses");
					}catch(Exception e){
						e.printStackTrace();
					}
					return businesses;
				}
			
			
			@Override
				public Long addBusiness(Business business) {
				business.setHeaderKey("/mmr/jsp/common/header.jsp");
				business.setActive(true);
				String dateStr = FormattingUtil.getFormattedDate(new Date(), FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
				Date date2 = FormattingUtil.getDate(dateStr, FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
				business.setDateCreated(date2);
				//getSqlMapClientTemplate().insert("addBusiness", business);
				long key = ((Long) getSqlMapClientTemplate().insert("addBusiness", business)).longValue();
				return key;
				}
			
			
				@Override
				public void updateBusiness(Business business) {
					business.setHeaderKey("/mmr/jsp/common/header.jsp");
					business.setActive(true);
					String dateStr = FormattingUtil.getFormattedDate(new Date(), FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
					Date date2 = FormattingUtil.getDate(dateStr, FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
					business.setDateCreated(date2);
					getSqlMapClientTemplate().update("updateBusiness", business);
					
				}
		
		
			@Override
			public void deleteBusiness(String businessId) {
					// TODO Auto-generated method stub
					getSqlMapClientTemplate().delete("deleteBusiness", businessId);
				}


				@Override
				public void addDefaultCustomerCarrier(long businessId) {
					// TODO Auto-generated method stub
					Map<String, Object> paramObj = new HashMap<String, Object>();
					paramObj.put("newBusinessId",businessId);
					paramObj.put("oldBusinessId",1);
					paramObj.put("customerId",0);
					getSqlMapClientTemplate().insert("addDefaultCustomerCarrier",paramObj);
					
				}


				@Override
				public void addDefaultPinsToBusiness(long businessId) {
					// TODO Auto-generated method stub
					Map<String, Object> paramObj = new HashMap<String, Object>();
					paramObj.put("newBusinessId",businessId);
					paramObj.put("oldBusinessId",1);
					getSqlMapClientTemplate().insert("addDefaultPinsToBusiness",paramObj);
				}




				@Override
				public long addCountryLevelBusienss(Business business) {
					// TODO Auto-generated method stub
				business.setHeaderKey("/mmr/jsp/common/header.jsp");
					business.setActive(true);
					String dateStr = FormattingUtil.getFormattedDate(new Date(), FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
					Date date2 = FormattingUtil.getDate(dateStr, FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
					business.setDateCreated(date2);
					
					long key = ((Long) getSqlMapClientTemplate().insert("addCountryLevelBusienss", business)).longValue();
					return key;
				}

				@Override
			public long addParterLevelBusienss(Business business) {
					// TODO Auto-generated method stub
					business.setHeaderKey("/mmr/jsp/common/header.jsp");
					business.setActive(true);
					String dateStr = FormattingUtil.getFormattedDate(new Date(), FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
					Date date2 = FormattingUtil.getDate(dateStr, FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
					business.setDateCreated(date2);
					long key = ((Long) getSqlMapClientTemplate().insert("addParterLevelBusienss", business)).longValue();
					return key;
				}


				@Override
				public List<Business> getPartnerBusiness(Long businessId) {
					// TODO Auto-generated method stub
					
					Map<String, Object> paramObj = new HashMap<String, Object>();
					paramObj.put("parentBusinessId",businessId);
					paramObj.put("isPartner",true);
					/*paramObj.put("partnerBusinessId",partnerBusId);*/
					return (List<Business>) getSqlMapClientTemplate().queryForList("getPartnerBusiness",paramObj);
				}
				
				

				@Override
				public List<Business> getCountryBusiness(Long partnerBusId) {
					// TODO Auto-generated method stub
					Map<String, Object> paramObj = new HashMap<String, Object>();
					paramObj.put("parentBusinessId",partnerBusId);
			paramObj.put("isNation",true);
					 paramObj.put("partnerBusinessId",partnerBusId);
				return (List<Business>) getSqlMapClientTemplate().queryForList("getCountryBusiness",paramObj);
			}


				@Override
				public long addBranchLevelBusiness(Business business) {
					// TODO Auto-generated method stub
					business.setHeaderKey("/mmr/jsp/common/header.jsp");
					business.setActive(true);
				String dateStr = FormattingUtil.getFormattedDate(new Date(), FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
				Date date2 = FormattingUtil.getDate(dateStr, FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
					business.setDateCreated(date2);
			long key = ((Long) getSqlMapClientTemplate().insert("addBranchLevelBusiness", business)).longValue();
		return key;
			}
				@Override
		public List<Business> getBranchBuisness(Long partnerBusId, Long countryBusId) {
			// TODO Auto-generated method stub
				
				Map<String, Object> paramObj = new HashMap<String, Object>();
					paramObj.put("parentBusinessId",countryBusId);
					paramObj.put("isBranch",true);
				 paramObj.put("partnerBusinessId",partnerBusId);
				 paramObj.put("countryBusId", countryBusId);
				return (List<Business>) getSqlMapClientTemplate().queryForList("getBranchBuisness",paramObj);
			}

			@Override
				public List<Business> getBusinessListByLevel(long id) {
					// TODO Auto-generated method stub
				return (List<Business>) getSqlMapClientTemplate().queryForList("getBusinessListByLevel", id);
				}


				@Override
			public List<Business> getHoleBusinessList() {
				// TODO Auto-generated method stub
				return (List<Business>) getSqlMapClientTemplate().queryForList("getHoleBusinessList");
				}

			@Override
				public Business getPartnerBusinessByName(String name,
						Long businessId) {
				// TODO Auto-generated method stub
				Map<String, Object> paramObj = new HashMap<String, Object>();
					paramObj.put("name",name);
		     	 paramObj.put("parentBusId", businessId);
					return (Business)getSqlMapClientTemplate().queryForObject("getPartnerBusinessByName",paramObj);
				}

				@Override
				public Business getCountryBusinessByName(String name,
						Long partnerId) {
					// TODO Auto-generated method stub
					Map<String, Object> paramObj = new HashMap<String, Object>();
				paramObj.put("name",name);
			     	 paramObj.put("partnerId", partnerId);
					return (Business)getSqlMapClientTemplate().queryForObject("getCountryBusinessByName",paramObj);
				}


				@Override
			public Business getBranchBusinessByName(String name,
						Long partnerId, Long countryParterId) {
			// TODO Auto-generated method stub
					Map<String, Object> paramObj = new HashMap<String, Object>();
					paramObj.put("name",name);
		     	paramObj.put("partnerId", partnerId);
			     	paramObj.put("countryPartnerId", countryParterId);
				    return (Business)getSqlMapClientTemplate().queryForObject("getBranchBusinessByName",paramObj);
				}


				@Override
				public Business getSuperParentBusiness(Long businessId) {
					// TODO Auto-generated method stub
					 return (Business)getSqlMapClientTemplate().queryForObject("getSuperParentBusiness",businessId);
				}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserBusiness> getUserBusinessListByUser(String username) {
		// TODO Auto-generated method stub
		return (List<UserBusiness>) getSqlMapClientTemplate().queryForList(
				"getUserBusinessListByUser", username);
	}

	@Override
	public Long adduserBusiness(UserBusiness ub) {
		// TODO Auto-generated method stub
				Long id=(Long) getSqlMapClientTemplate().insert("adduserBusiness", ub);
				 return id;
	}

	@Override
	public void deleteUserBusiness(Long userBusId) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("deleteUserBusiness", userBusId);
	}

	@Override
	public void addCSSDetailsForBusiness(CSSVO cssVO) {

		getSqlMapClientTemplate().insert("addCSSDetailsForBusiness", cssVO);
	}

	@Override
	public void updateCSSDetailsForBusiness(CSSVO cssVO) {
		getSqlMapClientTemplate().update("updateCSSDetailsForBusiness", cssVO);
	}

	@Override
	public CSSVO getCSSDetailsForBusiness(Long businessId) {
		return (CSSVO) getSqlMapClientTemplate().queryForObject(
				"getCSSDetailsForBusiness", businessId);

	}

	@Override
	public List<BusinessEmail> getBusinessEmails(long businessId) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<BusinessEmail> businessEmails = (List<BusinessEmail>) getSqlMapClientTemplate()
				.queryForList("getBusinessEmails", businessId);

		if (businessEmails != null && businessEmails.size() > 0) {
			for (BusinessEmail bm : businessEmails) {
				if (bm != null)
					bm.setHtmlContent(getHtmlContByBusinessEmail(bm));
			}
		}
		return businessEmails;
	}

	@Override
	public BusinessEmail getBusinessEmailById(long busEmailId) {
		// TODO Auto-generated method stub
		BusinessEmail bem = (BusinessEmail) getSqlMapClientTemplate()
				.queryForObject("getBusinessEmailById", busEmailId);
		if (bem != null) {
			bem.setHtmlContent(getHtmlContByBusinessEmail(bem));
		}
		return bem;
	}

	private String getHtmlContByBusinessEmail(BusinessEmail bem) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("locale", bem.getLocale());
		paramObj.put("messageId", bem.getMsgId());
		String html = (String) getSqlMapClientTemplate().queryForObject(
				"getMessage", paramObj);
		return html;
	}

	@Override
	public void addBusinessEmail(BusinessEmail bme) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("addBusinessEmailCont", bme);
	}

	@Override
	public void deleteBusinessEmailByBMId(long businessEmailId) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("deleteBusinessEmailByBMId",
				businessEmailId);
	}

	@Override
	public void updateBusinessEmail(BusinessEmail be3) {
		// TODO Auto-generated method stub

		getSqlMapClientTemplate().update("updateBusinessEmail", be3);
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("locale", be3.getLocale());
		paramObj.put("messageId", be3.getMsgId());
		paramObj.put("msgCont", be3.getHtmlContent());
		getSqlMapClientTemplate().update("updateEmailResources", be3);

	}
	public void setCopyMarkupFlag(long businessId){
						getSqlMapClientTemplate().update("setCopyMarkupFlag",
								businessId);
					}
	@SuppressWarnings("unchecked")
	@Override
	public List<Business> getBusinessForSelectedBusiness(long businessId) {
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("businessId", businessId);
		List<Business> businesses =  (List<Business>)getSqlMapClientTemplate().queryForList("getBusinessForSelectedBusiness",businessId);
		return businesses;
	}
	
	@Override
			public void updateUserBusiness(UserBusiness ub1) {
				// TODO Auto-generated method stub
				getSqlMapClientTemplate().update("updateUserBusiness",ub1);
			}
	@Override
		public void addBusinessContact(BusinessContact businessContact) {
			// TODO Auto-generated method stub
			getSqlMapClientTemplate().insert("addBusinessContact",businessContact);
			
	}
	
	
		@Override
		public BusinessContact getbusinessContactByBusiness(long id) {
			// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
			paramObj.put("businessId", id);
			BusinessContact bc=(BusinessContact) getSqlMapClientTemplate().queryForObject("getBusinessContactBybusiness",paramObj);
			return bc;
		}
		
		@Override
		public void updateBusinessContact(BusinessContact businessContact) {
		// TODO Auto-generated method stub
			
		  getSqlMapClientTemplate().update("updateBusinessContact",businessContact);
			
		}
}