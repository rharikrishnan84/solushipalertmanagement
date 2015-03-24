package com.meritconinc.mmr.dao.ibatis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.dao.BusinessFilterDAO;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.model.Branch;
import com.meritconinc.shiplinx.model.Country;
import com.meritconinc.shiplinx.model.CountryPartner;
import com.meritconinc.shiplinx.model.CustomerBusiness;
import com.meritconinc.shiplinx.model.Filter;
import com.meritconinc.shiplinx.model.Partner;
import com.meritconinc.shiplinx.model.RelPartnerFilter;
import com.meritconinc.shiplinx.model.UserFilter;

/**
 * 
 * @author selva
 *
 */
public class BusinessFilterDAOImpl extends SqlMapClientDaoSupport implements
		BusinessFilterDAO {

	@Override
	public long addPartner(Partner partner1) {
		// TODO Auto-generated method stub

		long partnerId = (Long) getSqlMapClientTemplate().insert("addPartner",
				partner1);
		//Map<String, Object> paramObj = null;
		/*
		 * for(Integer cid:countryId){ paramObj = new HashMap<String, Object>();
		 * paramObj.put("countryId", cid); paramObj.put("partnerId",
		 * partner_id); paramObj.put("businessId", partner1.getBusinessId());
		 * getSqlMapClientTemplate().insert("addCountryBypartner",paramObj); }
		 */

		//addCountryPartnerById(partnerId, countryId);
		return partnerId;

	}

	public List<Partner> getPartnerList(long bussinessId) {
		// TODO Auto-generated method stub

		@SuppressWarnings("unchecked")
		List<Partner> partnerlist = (List<Partner>) (getSqlMapClientTemplate()
				.queryForList("getPartnerList", bussinessId));
		return partnerlist;
	}

	/*
	 * @Override public Partner getPartnerByName(long partnerId) { // TODO
	 * Auto-generated method stub Map<String, Object> paramObj = new
	 * HashMap<String, Object>(1); paramObj.put("partnerId",partnerId); return
	 * (Partner)getSqlMapClientTemplate().queryForObject("getPartberByName",
	 * paramObj);
	 * 
	 * }
	 */
	@Override
	public void update(Partner partner) {
		// TODO Auto-generated method stub

		getSqlMapClientTemplate().update("updatePartner", partner);

	}

	@Override
	public void deletePartnerById(long partnerId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("partnerId", partnerId);
		getSqlMapClientTemplate().update("deletePartner", paramObj);
		getSqlMapClientTemplate().update("deleteCountryByPartner", paramObj);
	}

	@Override
	public void addCountryByPartnerName(long businessId, long partnerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getCountryIdByCode(String countrycode) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("countrycode", countrycode);
		Integer id = (Integer) getSqlMapClientTemplate().queryForObject(
				"getCountryIdByCode", paramObj);
		return id;
	}

	@Override
	public List<String> getCountryNameListByPartnerId(long partnerId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		Map<String, Object> paramObj1 = new HashMap<String, Object>();
		paramObj.put("partnerId1", partnerId);
		List<Long> countryIdlist = getSqlMapClientTemplate().queryForList(
				"getCountryCodeByPartnerId", paramObj);
		List<String> CountryNameList = new ArrayList<String>();
		for (Long cid : countryIdlist) {
			paramObj1.put("countryId", cid);

			CountryNameList.add((String) getSqlMapClientTemplate()
					.queryForObject("getCountryNameByid", paramObj1));

		}
		return CountryNameList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Branch> getBranchList(long countryPartnerId) {
		// TODO Auto-generated method stub
	//	long bussinessId = UserUtil.getMmrUser().getBusinessId();
		List<Branch> branchlist = (List<Branch>) (getSqlMapClientTemplate()
				.queryForList("getBranchList", countryPartnerId));
		return branchlist;
	}

	@Override
	public void addBranch(Branch branch) {
		// TODO Auto-generated method stub

		getSqlMapClientTemplate().insert("addBranch", branch);
	}

	@Override
	public long getCountryPartnerId(String country, long parterId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("partnerId", parterId);
		//long countryId = getCountryIdByCode(country);
		paramObj.put("countryCode", country);
		long countryPartnerId = (Long) getSqlMapClientTemplate()
				.queryForObject("getCountryPartnerId", paramObj);
		return countryPartnerId;
	}

	@Override
	public Partner getPartnerById(long parterId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("partnerId", parterId);
		Partner partner = (Partner) getSqlMapClientTemplate().queryForObject(
				"getPartnerById", paramObj);
		return partner;
	}
 
	@Override
	public void deleteBranchByPartnerId(String partnerName) {
		// TODO Auto-generated method stub
		long partnerId = Long.parseLong(partnerName);
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("partnerId", partnerId);
		getSqlMapClientTemplate().delete("deleteBranchByPartnerName", paramObj);

	}

	@Override
	public Branch getBranchByBranchId(String partnerId) {
		// TODO Auto-generated method stub
		long branchId = Long.parseLong(partnerId);
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("branchId", branchId);
		Branch branch = (Branch) getSqlMapClientTemplate().queryForObject(
				"getBranchByPartnerId", paramObj);
		return branch;
	}

	@Override
	public String getCountryNameByCPId(long countryPartnerId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("countryPartnerId", countryPartnerId);
		long cid = (Long) getSqlMapClientTemplate().queryForObject(
				"getCountryidByCPId", paramObj);
		paramObj.put("countryId", cid);
		String country = (String) getSqlMapClientTemplate().queryForObject(
				"getCountryNameByid", paramObj);

		return country;
	}

	@Override
	public void updateBranch(Branch branch) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("updateBranch", branch);

	}

	@Override
	public List<Filter> getFilterList() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		long bussinessId = UserUtil.getMmrUser().getBusinessId();
		List<Filter> filter = getSqlMapClientTemplate().queryForList(
				"getFilterList", bussinessId);
		return filter;

	}

	@Override
	public List<Branch> getBranchNameListByPartnerId(long partnerId,
			long countryPartnerId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("countryPartnerId", countryPartnerId);
		paramObj.put("partnerId", partnerId);
		List<Branch> branchList = (List<Branch>) getSqlMapClientTemplate()
				.queryForList("getBranchListByPCPId", paramObj);
		return branchList;
	}

	@Override
	public long addFilter(Filter filter) {
		// TODO Auto-generated method stub
		long key = 0;

		key = ((Long) getSqlMapClientTemplate().insert("addFilter", filter))
				.longValue();
		return key;
	}

	@Override
	public void addFilterCustomer(long businessId, Long[] customerIds,
			Filter filter) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("filterId", filter.getFilterId());
		paramObj.put("partnerId", filter.getPartnerId());
		paramObj.put("CPId", filter.getCountryPartnerId());
		paramObj.put("branchId", filter.getBranchId());
		long relPartnerFilId = (Long) getSqlMapClientTemplate().insert(
				"addRelPartnerFilter", paramObj);
		for (Long id : customerIds) {
			Map<String, Object> paramObj1 = new HashMap<String, Object>();
			paramObj1.put("relPartFilId", relPartnerFilId);
			paramObj1.put("customerId", id);
			getSqlMapClientTemplate()
					.insert("addRelPartnerCustomer", paramObj1);
		}

	}

	@Override
	public Filter getFilterById(long filterId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("filterId", filterId);
		return (Filter) getSqlMapClientTemplate().queryForObject(
				"getFilterById", paramObj);
	}

	@Override
	public List<Long> getFilteredCustomerIdsById(long filterId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("filterId", filterId);
		return (List<Long>) getSqlMapClientTemplate().queryForList(
				"getFilteredCustomerIdsById", paramObj);
	}

	@Override
	public void deleteFilterCustomerById(long filterId, long businessId, long id) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("filterId", filterId);
		paramObj.put("businessId", businessId);
		paramObj.put("customerId", id);
		getSqlMapClientTemplate().delete("deleteFilterCustomerById", paramObj);

	}

	@Override
	public void updateFilter(Filter filter) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("updateFilter", filter);
	}

	@Override
	public void deleteFilter(long filterId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("filterId", filterId);
		getSqlMapClientTemplate().delete("deleteFilter", paramObj);
		getSqlMapClientTemplate().delete("deleteFilterCustomer", paramObj);
	}

	@Override
	public List<Long> getpartnerCountryIds(long partnerId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("partnerId", partnerId);
		return (List<Long>) getSqlMapClientTemplate().queryForList(
				"getpartnerCountryIds", paramObj);
	}

	@Override
	public Country getCountryById(long countryId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("countryId", countryId);
		Country c = (Country) getSqlMapClientTemplate().queryForObject(
				"getCountryById", paramObj);
		c.setCountryName(getCountryNameBymsgId(c.getMsgId()));
		return c;
	}

	private String getCountryNameBymsgId(String msgId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("msgId", msgId);

		return (String) getSqlMapClientTemplate().queryForObject(
				"getCountryNameBymsgId", paramObj);
	}

	/*
	 * @Override public List<Country> getAllCountryls() { // TODO Auto-generated
	 * method stub
	 * 
	 * @SuppressWarnings("unchecked") List<Country>
	 * cis=(List<Country>)getSqlMapClientTemplate
	 * ().queryForList("getAllCountryls"); List<Country> cls=new
	 * ArrayList<Country>(); for(Country c: cis){
	 * c.setCountryName(getCountryNameBymsgId(c.getMsgId())); cls.add(c); }
	 * return cls; }
	 */

	@Override
	public void addCountryPartnerById(long partnerId,
			List<String> countrycodeLs) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		for (String cid : countrycodeLs) {
			paramObj = new HashMap<String, Object>();
			paramObj.put("countryCode", cid);
			paramObj.put("partnerId", partnerId);
			paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
			getSqlMapClientTemplate().insert("addCountryBypartner", paramObj);
		}
	}

	@Override
	public List<Long> getCPIdlistBypartnerId(long partnerId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj = new HashMap<String, Object>();
		paramObj.put("partnerId", partnerId);
		return (List<Long>) getSqlMapClientTemplate().queryForList(
				"getCPIdlistBypartnerId", paramObj);

	}

	@Override
	public String getCountryByCode(String code) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj = new HashMap<String, Object>();
		paramObj.put("countryCode", code);
		String c = (String) getSqlMapClientTemplate().queryForObject(
				"getCountryByCode", paramObj);
		//c.setCountryName(getCountryNameBymsgId(c.getMsgId()));
		return c;
	}

	@Override
	public void deleteCountryPartnerById(long partnerId,
			List<Integer> newCountryids) {
		// TODO Auto-generated method stub

		Map<String, Object> paramObj = new HashMap<String, Object>();

		for (Integer i : newCountryids) {
			paramObj = new HashMap<String, Object>();
			paramObj.put("partnerId", partnerId);
			paramObj.put("countryId", i);
			getSqlMapClientTemplate().update("deleteCountryPartnerById",
					paramObj);

		}
	}

	@Override
	public void addUserFilters(UserFilter userfilter) {
		// TODO Auto-generated method stub
        userfilter.setDivisionId(0);
		getSqlMapClientTemplate().insert("addUserFilters", userfilter);

	}

	@Override
	public List<Filter> getFilterListByUser(String username) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("userName", username);
		List<Long> fids = (List<Long>) getSqlMapClientTemplate().queryForList(
				"getFilterIdByUser", paramObj);
		List<Filter> f = new ArrayList<Filter>();
		for (Long id : fids) {
			// f.add((Filter)getSqlMapClientTemplate().queryForObject("getFilterby",
			// parameterObject))
			f.add(getFilterById(id));
		}
		// List<Filter> f=(List<Filter>)
		// getSqlMapClientTemplate().queryForList("getFilterListByUser",
		// paramObj);
		return f;
	}

	@Override
	public void deleteUserFiltersByFilterId(List<Long> fids, String userName) {
		// TODO Auto-generated method stub

		for (Long id : fids) {
			Map<String, Object> paramObj = new HashMap<String, Object>();
			paramObj.put("filterId", id);
			paramObj.put("userName", userName);
			getSqlMapClientTemplate().update("deleteUserFiltersByFilterId",
					paramObj);
		}
	}

	@Override
	public List<Long> getFilterIdsByUserName(String username) {
		//
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("userName", username);
		return (List<Long>) getSqlMapClientTemplate().queryForList(
				"getFilterIdsByUserName", paramObj);
	}

	@Override
	public List<Long> getCustomerIdsByUser(String userName) {
		// TODO Auto-generated method stub

		Map<String, String> paramObj = new HashMap<String, String>();
		paramObj.put("userName", userName);
		List<UserFilter> listfilter = (List<UserFilter>) getSqlMapClientTemplate()
				.queryForList("getUserFilterByUser", paramObj);
		// List<Long>
		// cusIds=(List<Long>)getSqlMapClientTemplate().queryForList("getCustomerIdsByFilterIds",
		// paramObj);
		List<List<Long>> ids = new ArrayList<List<Long>>();
		List<List<Long>> cusids = new ArrayList<List<Long>>();
		for (UserFilter uf : listfilter) {
			ids.add((List<Long>) getSqlMapClientTemplate().queryForList(
					"getRelPartFilterIdByUF", uf));
		}
		String ids1 = "";
		for (List<Long> l : ids) {
			for (long id : l) {
				cusids.add((List<Long>) getSqlMapClientTemplate().queryForList(
						"getCustomerIdByRelPartnerId", id));
			}
		}

		List<Long> cids = new ArrayList<Long>();
		for (List<Long> id : cusids) {
			for (long i : id) {
				cids.add(i);
			}
		}
		return cids;
	}

	@Override
	public List<Partner> getpartnerListByFilter(Long filterId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("filterId", filterId);
		return (List<Partner>) getSqlMapClientTemplate().queryForList(
				"getpartnerListByFilter", paramObj);

	}

	@Override
	public List<RelPartnerFilter> getRelParterFiltersByFilter(long filterId) {
		// TODO Auto-generated method stub
		return (List<RelPartnerFilter>) getSqlMapClientTemplate().queryForList(
				"getRelParterFiltersByFilter", filterId);
	}

	@Override
	public List<Long> getCustomerByRelIds(long id) {
		// TODO Auto-generated method stub
		return (List<Long>) getSqlMapClientTemplate().queryForList(
				"getCustomerIdByRelPartnerId", id);
	}

	@Override
	public Branch getBranchByBranchId(long partnerId) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("branchId", partnerId);
		Branch branch = (Branch) getSqlMapClientTemplate().queryForObject(
				"getBranchByPartnerId", paramObj);
		return branch;
	}

	@Override
	public long getRelParterFilIdByFilter(Filter filter) {
		// TODO Auto-generated method stub
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("filterId", filter.getFilterId());
		paramObj.put("partnerId", filter.getPartnerId());
		paramObj.put("branchId", filter.getBranchId());
		paramObj.put("CPId", filter.getCountryPartnerId());

		return (Long) getSqlMapClientTemplate().queryForObject(
				"getRelParterFilIdByFilter", paramObj);
	}

	@Override
	public void delRelPartFilCustomers(List<Long> oldCusIds, long relparfilId) {
		// TODO Auto-generated method stub
		for (Long id : oldCusIds) {
			Map<String, Object> paramObj = new HashMap<String, Object>();
			paramObj.put("relparfilId", relparfilId);
			paramObj.put("customerId", id);
			getSqlMapClientTemplate()
					.update("delRelPartFilCustomers", paramObj);

		}
	}

	@Override
	public void addRelPartFilCustomers(List<Long> updatedCusids,
			long relparfilId) {
		// TODO Auto-generated method stub
		for (Long id : updatedCusids) {
			Map<String, Object> paramObj = new HashMap<String, Object>();
			paramObj.put("relPartFilId", relparfilId);
			paramObj.put("customerId", id);
			getSqlMapClientTemplate().insert("addRelPartnerCustomer", paramObj);
		}
	}

	@Override
	public UserFilter getUserFilterByUsername(String userName) {
		// TODO Auto-generated method stub
		
		return (UserFilter) getSqlMapClientTemplate().queryForObject("getUserFilterByUser", userName);
	}

	@Override
	public List<CountryPartner> getCountryPartnerList(long partnerId) {
		// TODO Auto-generated method stub
		List<CountryPartner> countryPartners = (List<CountryPartner>) getSqlMapClientTemplate().queryForList("getCountryPartnerList",partnerId);
		if(countryPartners!=null && countryPartners.size()>0){
			return countryPartners;
		}else{
			return new ArrayList<CountryPartner>();
		}
	}

	@Override
	public void addCountryPartner(CountryPartner countrypartner) {
		// TODO Auto-generated method stub
		 getSqlMapClientTemplate().insert("addCountryPartner",countrypartner);
	}

	@Override
	public CountryPartner getCountryPartnerById(Long countryPartnerId) {
		// TODO Auto-generated method stub
		
		return (CountryPartner) getSqlMapClientTemplate().queryForObject("getCountryPartnerById",countryPartnerId);
	}

	@Override
	public void updateCountryPartner(CountryPartner countrypartner) {
		// TODO Auto-generated method stub
		
		getSqlMapClientTemplate().update("updateCountryPartner",countrypartner);
		
	}

	@Override
	public void addCustomerBusiness(CustomerBusiness customerBusiness) {
		// TODO Auto-generated method stub
		
		getSqlMapClientTemplate().insert("addCustomerBusiness",customerBusiness);
	}

	@Override
	public Partner getpartnerByPartnerName(String partnerName) {
		// TODO Auto-generated method stub
		return (Partner) getSqlMapClientTemplate().queryForObject("getpartnerByPartnerName",partnerName);
	}

	@Override
	public Partner getpartnerByPartnerShortCode(String shortCode) {
		// TODO Auto-generated method stub
		return (Partner) getSqlMapClientTemplate().queryForObject("getpartnerByPartnerShortCode",shortCode);
	}

	@Override
	public Branch getBranchByShortCode(String shortCode) {
		// TODO Auto-generated method stub
		return (Branch)getSqlMapClientTemplate().queryForObject("getBranchByShortCode",shortCode);
	}

	@Override
	public void addPartnerWithCountryList(Partner partner,
			List<String> countryCodeList) {
		// TODO Auto-generated method stub
		
		   long  partnerId=	(Long) getSqlMapClientTemplate().insert("addPartner", partner);
		   Map<String, Object> paramObj=null;
	 		
		 addCountryPartnerById(partnerId,countryCodeList);
		

		
	}

	@Override
	public List<Partner> getAllPartnerList() {
		// TODO Auto-generated method stub
		return (List<Partner>) getSqlMapClientTemplate().queryForList("getAllPartnerList");
		
	}

	@Override
	public List<Branch> getAllBranchList() {
		// TODO Auto-generated method stub
		return (List<Branch>)getSqlMapClientTemplate().queryForList("getAllBranchList");
	}

	@Override
	public Country getCountryObjByCode(String Countrycode) {
		// TODO Auto-generated method stub
		return (Country) getSqlMapClientTemplate().queryForObject("getCountryObjByCode",Countrycode);
	}

   
 
	

}
