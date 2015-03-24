package com.meritconinc.mmr.dao;

import java.util.List;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.shiplinx.model.Branch;
import com.meritconinc.shiplinx.model.Country;
import com.meritconinc.shiplinx.model.CountryPartner;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerBusiness;
import com.meritconinc.shiplinx.model.Filter;
import com.meritconinc.shiplinx.model.Partner;
import com.meritconinc.shiplinx.model.RelPartnerFilter;
import com.meritconinc.shiplinx.model.UserFilter;

public interface BusinessFilterDAO {

	long addPartner(Partner partner1 );
	List<Partner> getPartnerList(long businessId);
	//Partner getPartnerById(long partnerId);
	void update(Partner partner);
	void deletePartnerById(long partnerId);
	void addCountryByPartnerName(long businessId,long partnerId);
	Integer getCountryIdByCode(String countrycode);
	List<String> getCountryNameListByPartnerId(long partnerId);
	List<Branch> getBranchList(long countryPartnerId);
	void addBranch(Branch branch);
	long getCountryPartnerId(String country, long parterId);
	Partner getPartnerById(long parterId);
	void deleteBranchByPartnerId(String partnerName);
	Branch getBranchByBranchId(String partnerId);
	String getCountryNameByCPId(long countryPartnerId);
	void updateBranch(Branch branch);
	List<Filter> getFilterList();
	List<Branch> getBranchNameListByPartnerId(long partnerId,long countryPartnerId);
	//List<Customer> getCustomerListByBusinessId(long businessId);
	long addFilter(Filter filter);
	void addFilterCustomer(long filterId, Long[] longs, Filter filter);
	Filter getFilterById(long parseLong);
	List<Long> getFilteredCustomerIdsById(long filterId);
	void deleteFilterCustomerById(long filterId, long businessId, long id);
	void updateFilter(Filter filter);
	void deleteFilter(long filterId);
	List<Long> getpartnerCountryIds(long partnerId);
	Country getCountryById(long countryId);
	//List<Country> getAllCountryls();
	void addCountryPartnerById(long partnerId,List<String> newCPids);
	List<Long> getCPIdlistBypartnerId(long partnerId);
	String getCountryByCode(String code);
	void deleteCountryPartnerById(long partnerId, List<Integer> newCountryids);
	void addUserFilters(UserFilter userfilter);
	List<Filter> getFilterListByUser(String username);
	void deleteUserFiltersByFilterId(List<Long> fids, String string);
	List<Long> getFilterIdsByUserName(String username);
	List<Long> getCustomerIdsByUser(String username);
	List<Partner> getpartnerListByFilter(Long filterId);
	List<RelPartnerFilter> getRelParterFiltersByFilter(long filterId);
	List<Long> getCustomerByRelIds(long relPartnerFilId);
	Branch getBranchByBranchId(long branchId);
	long getRelParterFilIdByFilter(Filter filter);
	void delRelPartFilCustomers(List<Long> oldCusIds, long relparfilId);
	void addRelPartFilCustomers(List<Long> updatedCusids, long relparfilId);
	UserFilter getUserFilterByUsername(String username);
	List<CountryPartner> getCountryPartnerList(long partnerId);
	void addCountryPartner(CountryPartner countrypartner);
	CountryPartner getCountryPartnerById(Long long1);
	void updateCountryPartner(CountryPartner countrypartner);
	void addCustomerBusiness(CustomerBusiness customerbus);
	Partner getpartnerByPartnerName(String partnerName);
	Partner getpartnerByPartnerShortCode(String ShortCode);
	Branch getBranchByShortCode(String shortCode);
	void addPartnerWithCountryList(Partner partner,
			List<String> countryCodeList);
	List<Partner> getAllPartnerList();
	List<Branch> getAllBranchList();
	Country getCountryObjByCode(String code);

	


}
