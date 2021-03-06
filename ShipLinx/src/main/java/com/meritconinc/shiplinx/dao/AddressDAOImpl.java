package com.meritconinc.shiplinx.dao;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.AddressType;
import com.meritconinc.shiplinx.model.Country;
import com.meritconinc.shiplinx.model.Province;
import com.meritconinc.shiplinx.model.ShippingOrder;

public class AddressDAOImpl extends SqlMapClientDaoSupport implements
		AddressDAO {
	private static final Logger log = LogManager
			.getLogger(AddressDAOImpl.class);

	public List<Address> findaddressesByCustomer(Long custId) {
		List<Address> address = new ArrayList<Address>();
		try {
			Map<String, Object> paramObj = new HashMap<String, Object>(1);
			paramObj.put("customerId", custId);
			address = (List<Address>) getSqlMapClientTemplate().queryForList(
					"searchAddress", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}

	public Long add(Address address) {
		Long id = (Long) getSqlMapClientTemplate().insert("addAddresses",
				address);

		return id;
	}

	public List<String> getCanadianProvinces() {

		List<String> provinces = (List<String>) getSqlMapClientTemplate()
				.queryForList("getCanadianProvinces");
		return provinces;
	}

	public List<String> getUSStates() {

		List<String> states = (List<String>) getSqlMapClientTemplate()
				.queryForList("getUSStates");
		return states;
	}

	public List<Country> getCountries() {
		return getSqlMapClientTemplate().queryForList("getAllCountries");
	}

	public void delete(String id) {
		
		getSqlMapClientTemplate().delete("deleteaddress", id);

	}

	public void edit(Address address) {
		try {
			getSqlMapClientTemplate().update("updateaddress", address);

			if (address.getDefaultFromAddress() == true
					&& address.getCustomerId() > 0)
				getSqlMapClientTemplate().update("updateFromAddressNotDefault",
						address);
			if (address.getDefaultToAddress() == true
					&& address.getCustomerId() > 0)
				getSqlMapClientTemplate().update("updateToAddressNotDefault",
						address);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Address findAddressById(String id) {
		Address address = (Address) getSqlMapClientTemplate().queryForObject(
				"findAddressById", Long.valueOf(id));
		return address;
	}

	public void addDistributionList(Address address) {
		getSqlMapClientTemplate().insert("uploadDistribution_Address", address);
		log.debug("@@@@@@@@------@@@@@@@@@------Address entered for ----------------------------:"
				+ address.getAbbreviationName());
	}

	public void deleteDistributionList(String distributionListId,
			Long customerId) {
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		paramObj.put("distributionListId", distributionListId);
		paramObj.put("customerId", customerId);
		getSqlMapClientTemplate().delete("deleteDistributionAddress", paramObj);
		// getSqlMapClientTemplate().delete("deleteDistributionList", paramObj);

	}

	public List<String> findDistributionListForCustomer(Long customerId) {
		log.debug("ADDRESS BOOK CHECK INSIDE DAO  -- findDistributionListForCustomer -- "
				+ customerId);
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		paramObj.put("customerId", customerId);
		List<String> distributionList = (List<String>) getSqlMapClientTemplate()
				.queryForList("searchDistributionList", paramObj);
		log.debug("distributionList  ---- " + distributionList);
		return distributionList;
	}

	public List<Province> getProvinces(String country) {
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		paramObj.put("countryCode", country);
		return getSqlMapClientTemplate().queryForList("getProvinces", paramObj);
	}

	public Province getProvince(String provinceCode) {
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		if (provinceCode.equalsIgnoreCase("QC"))
			provinceCode = new String("PQ");
		paramObj.put("provinceCode", provinceCode);
		return (Province) getSqlMapClientTemplate().queryForObject(
				"getProvince", paramObj);
	}

	public Address findDefaultToAddressForCustomer(Long customerId) {
		Address address = null;
		try {
			log.debug("ADDRESS BOOK CHECK INSIDE DAO  findDefaultToAddressForCustomer---- ");

			Map<String, Object> paramObj = new HashMap<String, Object>(1);
			logger.debug(" customerId 111 " + customerId);
			paramObj.put("customerId", customerId);
			address = (Address) getSqlMapClientTemplate().queryForObject(
					"findDefaultToAddressForCustomer", paramObj);
			logger.debug(" RUCHITA CHECK RETRIEVED address IN DAO  " + address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;

	}

	public Address findDefaultFromAddressForCustomer(Long customerId) {
		log.debug("ADDRESS BOOK CHECK INSIDE DAO  findDefaultFromAddressForCustomer---- ");
		Address address = null;
		try {
			Map<String, Object> paramObj = new HashMap<String, Object>(1);
			logger.debug(" customerId 2222 " + customerId);
			paramObj.put("customerId", customerId);
			address = (Address) getSqlMapClientTemplate().queryForObject(
					"findDefaultFromAddressForCustomer", paramObj);
			logger.debug("findDefaultFromAddressForCustomer " + address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;

	}
	
	public Address findDefaultCustomsBrokerAddress(Long customerId,String countryCode) {
		log.debug("ADDRESS BOOK CHECK INSIDE DAO  defaultCustomsBrokerAddress---- ");
		Address address = null;
		try {
			Map<String, Object> paramObj = new HashMap<String, Object>(1);			
			paramObj.put("customerId", customerId);
			paramObj.put("countryCode", countryCode);			
			address = (Address) getSqlMapClientTemplate().queryForObject(
					"findDefaultCustomsBrokerAddress", paramObj);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;

	}
		
	public Address findToAddressForOrder(Long orderId) {
		Address address = null;
		try {
			log.debug("ADDRESS BOOK CHECK INSIDE DAO  findToAddressForOrder---- ");

			Map<String, Object> paramObj = new HashMap<String, Object>(1);
			logger.debug(" orderId 111 " + orderId);
			paramObj.put("orderId", orderId);
			address = (Address) getSqlMapClientTemplate().queryForObject(
					"getToAddressForOrder", paramObj);
			logger.debug(" RUCHITA CHECK RETRIEVED address IN DAO  " + address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;

	}

	public Address findFromAddressForOrder(Long orderId) {
		log.debug("ADDRESS BOOK CHECK INSIDE DAO  findFromAddressForOrder---- ");
		Address address = null;
		try {
			Map<String, Object> paramObj = new HashMap<String, Object>(1);
			logger.debug(" orderId 2222 " + orderId);
			paramObj.put("orderId", orderId);
			address = (Address) getSqlMapClientTemplate().queryForObject(
					"getFromAddressForOrder", paramObj);
			logger.debug("getFromAddressForOrder " + address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;

	}

	public List<Address> findAddresses(Address addressBook) {
		List<Address> address = new ArrayList();
		try {
			address = (List) getSqlMapClientTemplate().queryForList(
					"findAddressesByCustomer", addressBook);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}

	/*
	 * Start : Sumanth Kulkarni S Date: 14 Oct 2011
	 */
	public List<Address> searchAddresses(Address add) {

		List<Address> address = new ArrayList();
		try {
			address = (List) getSqlMapClientTemplate().queryForList(
					"findAddressesByCustomerSearch", add);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}
	
	public List<Address> searchCustomsBrokerAddress(String customerId,String countryCode) {

		List<Address> address = new ArrayList<Address>();
		try {
			Map<String, Object> paramObj = new HashMap<String, Object>(1);
			paramObj.put("customerId", customerId);	
			paramObj.put("countryCode", countryCode);	
			address = (List<Address>) getSqlMapClientTemplate().queryForList(
					"searchCustomsBrokerAddress", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}
	
	public List<Address> searchShipperAndConsigneeAddress(String customerId) {

		List<Address> address = new ArrayList<Address>();
		try {
			Map<String, Object> paramObj = new HashMap<String, Object>(1);
			paramObj.put("customerId", customerId);					
			address = (List<Address>) getSqlMapClientTemplate().queryForList(
					"searchShipperAndConsigneeAddress", paramObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}

	/*
	 * End : Sumanth Kulkarni S Date: 14 Oct 2011
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.meritconinc.shiplinx.dao.AddressDAO#getCustomBrokers()
	 */

	@Override
	public List<AddressType> getCustomBrokers() {
		List<AddressType> brokList = new ArrayList<AddressType>();
		try {
			brokList = (List) getSqlMapClientTemplate().queryForList("getAllBrokers");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return brokList;
	}

	@Override
	public List<Address> findaddressbyid(Long customerId) {
		try {
			Map<String, Object> paramObj = new HashMap<String, Object>(1);
			paramObj.put("customerId", customerId);
		return (List<Address>) getSqlMapClientTemplate().queryForList(
					"getCustomerUser", paramObj);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Address findDefaultFromAddressForAddress(Long addressId) {
		log.debug("ADDRESS BOOK CHECK INSIDE DAO  findDefaultFromAddressForAddress---- ");
		Address address = null;
		try {
			Map<String, Object> paramObj = new HashMap<String, Object>(1);
			logger.debug("addressId" + addressId);
			paramObj.put("addressId", addressId);
			address = (Address) getSqlMapClientTemplate().queryForObject(
					"findDefaultFromAddressForAddress", paramObj);
			logger.debug("findDefaultFromAddressForAddress " + address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;

	}
	 @Override
	  	public Province getProvinceName(String provinceCode) {
	  		Map<String,String> m=new HashMap<String,String>();
	  		m.put("province_code",provinceCode );
	  		return (Province) getSqlMapClientTemplate().queryForObject("getProvinceName",m);
	  		
	  	}

	 public void setSendNotification(Long addressId){
		 		 try{
		 		 Map<String,Object> paramObj=new HashMap<String,Object>();
		 		 paramObj.put("addressId", addressId);
		 		 getSqlMapClientTemplate().update("setSendNotification",paramObj);
		 		 } catch(Exception e){
		 			 e.printStackTrace();
		 		 }
		 	 }	 
	 
	 @Override
  	public Long findAddressId(long id) {
 		// TODO Auto-generated method stub
 		Map<String, Object> paramObj = new HashMap<String, Object>(2);
 		paramObj.put("addressId",id );
 		long addressId=0;
 		try{
 			addressId= (Long) getSqlMapClientTemplate().queryForObject("getAddressId",paramObj);
 			
 		}catch(NullPointerException e){
 			e.printStackTrace();
 		}
 		
 		return addressId;
 	 		
 	 }
	 
	 public List<Address> searchAddressMatch ( String address, String countryCode, String postalCode, String abbreviationName){
		 		 		 		 		 
 		 List<Address> address1 = new ArrayList<Address>();
 		 try {
 		 	Map<String, Object> paramObj = new HashMap<String, Object>(1);
 		 	paramObj.put("address", address);
 		 	paramObj.put("countryCode", countryCode);
 		 	paramObj.put("postalCode", postalCode);
 		 	paramObj.put("abbreviationName", abbreviationName);
 		 	address1 = (List<Address>) getSqlMapClientTemplate().queryForList(
 		 		 			"searchAddressMatch", paramObj);
 		 } catch (Exception e) {
 		 		 e.printStackTrace();
 	       }
 		 return address1;
 	 }
		 	 
 	 public void updateCustomerIdInAddress(Long customerId, Long addressId){
 		 		  Map<String, Long> paramObj = new HashMap<String, Long>();
 		 		  paramObj.put("customerId", customerId);
 		 	      paramObj.put("addressId", addressId);
 		 		  try {
 		 			    getSqlMapClientTemplate().update("updateCustomerIdInAddress", paramObj);
 		 	      } catch (Exception e) {
 		 			   e.printStackTrace();
 		 		    }
 	 }
 	 
 	public long getCustomerIdByAddressMatch (ShippingOrder shipment ){
 		long cusId = 0;
		try {
 		 	Map<String, Object> paramObj = new HashMap<String, Object>(1);
 		 	paramObj.put("fromAddress", shipment.getFromAddress().getAddress1());
 		 	paramObj.put("fromCountryCode", shipment.getFromAddress().getCountryCode());
 		 	paramObj.put("fromPostalCode", shipment.getFromAddress().getPostalCode());
 		 	//paramObj.put("fromAbbreviationName",shipment.getFromAddress().getAbbreviationName());
 		 	StringBuilder fromAbbreviationName = new StringBuilder();
 		 	String fromTemp=shipment.getFromAddress().getAbbreviationName();
 		 	if(fromTemp!=null && !fromTemp.isEmpty() && fromTemp.contains("'")){
 		 		for (String temp: fromTemp.split("'")){
 		 			fromAbbreviationName.append(temp+"_");
	 	         }
 		 		fromTemp=fromAbbreviationName.toString();
 		 		fromAbbreviationName.delete(0, fromAbbreviationName.length());
 		 		fromAbbreviationName.append(fromTemp.substring(0,fromTemp.length()-1));
 		 	}
 		 	else{
 		 		fromAbbreviationName.append(fromTemp);
 		 	}
 		 	paramObj.put("fromAbbreviationName",fromAbbreviationName.toString());
 		 	paramObj.put("toAddress", shipment.getToAddress().getAddress1());
 		 	paramObj.put("toCountryCode", shipment.getToAddress().getCountryCode());
 		 	paramObj.put("toPostalCode", shipment.getToAddress().getPostalCode());
 		 	//paramObj.put("toAbbreviationName", shipment.getToAddress().getAbbreviationName());
 		 	StringBuilder toAbbreviationName = new StringBuilder();
 		 	String toTemp=shipment.getToAddress().getAbbreviationName();
 		 	if(toTemp!=null && !toTemp.isEmpty() && toTemp.contains("'")){
 		 		for (String temp: toTemp.split("'")){
 		 	         toAbbreviationName.append(temp+"_");
 		 	    }
 		 		toTemp=toAbbreviationName.toString();
 		 		toAbbreviationName.delete(0, toAbbreviationName.length());
 		 		toAbbreviationName.append(toTemp.substring(0,toTemp.length()-1));
 		 	}else{
 		 		toAbbreviationName.append(toTemp);
 		 	}
 		 	paramObj.put("toAbbreviationName",toAbbreviationName.toString());
 		 	/*Object obj = (Object) getSqlMapClientTemplate().queryForObject("searchAddressMatch", paramObj);
 		 	if(obj != null){
 		 		cusId = (Long)obj;
 		 		return cusId;
 		 	}*/
 		 	
 		 	@SuppressWarnings("unchecked")
			List<Long> customerIdList = (List<Long>) getSqlMapClientTemplate().queryForList("searchAddressMatch", paramObj);
 		 	if(customerIdList!=null && customerIdList.size()==1){
 		 		return	customerIdList.get(0);
 		 	}
		} catch (Exception e) {
 		 		 e.printStackTrace();
 	       }
		return  cusId;
 }
 
 public long getCustomerIdForFromAddress (ShippingOrder shipment ){
	 long cusId = 0;
		try {
 		 	Map<String, Object> paramObj = new HashMap<String, Object>(1);
 		 	paramObj.put("fromAddress", shipment.getFromAddress().getAddress1());
 		 	paramObj.put("fromCountryCode", shipment.getFromAddress().getCountryCode());
 		 	paramObj.put("fromPostalCode", shipment.getFromAddress().getPostalCode());
 		 	paramObj.put("fromAbbreviationName",shipment.getFromAddress().getAbbreviationName());
 		 	Object obj = (Object) getSqlMapClientTemplate().queryForObject("getCustomerIdForFromAddress", paramObj);
 		 	if(obj != null){
 		 		cusId = (Long)obj;
 		 		return cusId;
 		 	}
		} catch (Exception e) {
	 		 e.printStackTrace();
       }
	return  cusId;
 }
 
 public long getCustomerIdForToAddress (ShippingOrder shipment ){
	 long cusId = 0;
		try {
 		 	Map<String, Object> paramObj = new HashMap<String, Object>(1);
 		 	paramObj.put("toAddress", shipment.getFromAddress().getAddress1());
 		 	paramObj.put("toCountryCode", shipment.getFromAddress().getCountryCode());
 		 	paramObj.put("toPostalCode", shipment.getFromAddress().getPostalCode());
 		 	paramObj.put("toAbbreviationName",shipment.getFromAddress().getAbbreviationName());
 		 	Object obj = (Object) getSqlMapClientTemplate().queryForObject("getCustomerIdForToAddress", paramObj);
 		 	if(obj != null){
 		 		cusId = (Long)obj;
 		 		return cusId;
 		 	}
		} catch (Exception e) {
	 		 e.printStackTrace();
       }
	return  cusId;
 }
 @Override
 public List<Address> getAddressByIds(List<Long> addressIds) {
 	List<Address> address = new ArrayList<Address>();
 	try {
 		Map<String, Object> paramObj = new HashMap<String, Object>(1);
 		paramObj.put("addressIds", addressIds);
 		address = (List<Address>) getSqlMapClientTemplate().queryForList(
 				"getAddressByIds", paramObj);
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
 	return address;
 }

}