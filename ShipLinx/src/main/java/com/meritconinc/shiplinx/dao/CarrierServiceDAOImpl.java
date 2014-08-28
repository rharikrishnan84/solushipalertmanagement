package com.meritconinc.shiplinx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Service;

/**
 * 
 * 
 * @author rahul.raghatate
 * 
 */
public class CarrierServiceDAOImpl extends SqlMapClientDaoSupport implements CarrierServiceDAO {
  private static final Logger log = LogManager.getLogger(CarrierServiceDAOImpl.class);

  public List<CustomerCarrier> getCutomerCarrier(Long customerId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("customerId", customerId);
    List<CustomerCarrier> customerCarrierList = (List) getSqlMapClientTemplate().queryForList(
        "getCustomerCarrier", paramObj);
    return customerCarrierList;
  }

  public List<Service> getServicesForCarrier(Long carrierId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("carrierId", carrierId);
    List<Service> carrierServicesList = new ArrayList<Service>();
    try {
      carrierServicesList = (List) getSqlMapClientTemplate().queryForList("getServicesForCarrier",
          paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return carrierServicesList;
  }

  public Carrier getCarrier(Long carrierId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("carrierId", carrierId);
    Carrier carrier = (Carrier) getSqlMapClientTemplate().queryForObject("getCarrier", paramObj);
    return carrier;
  }

  public Carrier getCarrierByBusiness(Long carrierId, Long businessId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("carrierId", carrierId);
    paramObj.put("businessId", businessId);
    Carrier carrier = (Carrier) getSqlMapClientTemplate().queryForObject("getCarrierByBusiness",
        paramObj);
    return carrier;

  }

  public Service getService(Long serviceId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("serviceId", serviceId);
    Service service = (Service) getSqlMapClientTemplate().queryForObject("getService", paramObj);
    return service;
  }

  public Service getServiceByCarrierIdAndTransitCode(Long carrierId, String transitCode) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("carrierId", carrierId);
    paramObj.put("transitCode", transitCode);
    Service service = (Service) getSqlMapClientTemplate().queryForObject(
        "getServiceByCarrierIdAndTransitCode", paramObj);
    return service;

  }

  public Service getServiceByCarrierIdAndCode(Long carrierId, String code) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("carrierId", carrierId);
    paramObj.put("code", code);
    Service service = (Service) getSqlMapClientTemplate().queryForObject(
        "getServiceByCarrierIdAndCode", paramObj);
    return service;

  }

  public CustomerCarrier getCutomerCarrierDefaultAccount(long carrierId, long customerId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("carrierId", carrierId);
    paramObj.put("customerId", customerId);
    CustomerCarrier customerCarrier = (CustomerCarrier) getSqlMapClientTemplate().queryForObject(
        "getCutomerCarrierDefaultAccount", paramObj);
    return customerCarrier;
  }

  public CustomerCarrier getDefAccountByCountry(long carrierId, long customerId, String country) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("carrierId", carrierId);
    paramObj.put("customerId", customerId);
    paramObj.put("country", country);

    logger.debug("carrierId ------" + carrierId);
    logger.debug("customerId ------" + customerId);
    logger.debug("-----country ------" + country);

    CustomerCarrier customerCarrier = (CustomerCarrier) getSqlMapClientTemplate().queryForObject(
        "getDefAccountByCountry", paramObj);
    return customerCarrier;
  }

  public CustomerCarrier getOrderCutomerCarrier(Long orderId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("orderId", orderId);
    CustomerCarrier customerCarrier = (CustomerCarrier) getSqlMapClientTemplate().queryForObject(
        "getOrderCustomerCarrier", paramObj);
    return customerCarrier;
  }

  public List<CustomerCarrier> getAllCutomerCarrier(long businessId, Long customerId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("customerId", customerId);
    paramObj.put("businessId", businessId);
    List<CustomerCarrier> customerCarrierList = (List) getSqlMapClientTemplate().queryForList(
        "getAllCustomerCarrier", paramObj);
    return customerCarrierList;
  }

  public List<Carrier> getCarriers() {
    List<Carrier> carrierList = (List) getSqlMapClientTemplate().queryForList("getAllCarrier");
    return carrierList;
  }

  public List<CustomerCarrier> getCarrierAccounts(Long customerId, String country) {

    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("customerId", customerId);
    paramObj.put("country", country);
    List<CustomerCarrier> customerCarrierList = (List) getSqlMapClientTemplate().queryForList(
        "getCarrierAccounts", paramObj);
    return customerCarrierList;
  }

  public CustomerCarrier getCarrierAccount(Long customerId, Long businessId, Long carrierId,
      String country, String toCountry) {

    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("customerId", customerId);
    paramObj.put("businessId", businessId);
    paramObj.put("carrierId", carrierId);
    paramObj.put("country", country);
    paramObj.put("toCountry", toCountry);
    CustomerCarrier customerCarrierList = (CustomerCarrier) getSqlMapClientTemplate()
        .queryForObject("getCarrierAccount", paramObj);
    return customerCarrierList;
  }

  public List<Carrier> getCarriersForBusiness(long businessId) {
    List<Carrier> carrierList = (List) getSqlMapClientTemplate().queryForList(
        "getAllCarrierForBusiness", businessId);
    return carrierList;
  }

  public Long getMaxServiceIdForCarrier(long carrierId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("carrierId", carrierId);
    Long maxServiceId = (Long) getSqlMapClientTemplate().queryForObject(
        "getMaxServiceIdForCarrier", paramObj);
    return maxServiceId;

  }

  public void addService(Service service) throws Exception {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    try {
      getSqlMapClientTemplate().insert("addService", service);
    } catch (Exception e) {
      // log.debug("-----Exception-----"+e);
      e.printStackTrace();
      throw e;
    }

  }

  public List<CustomerCarrier> getAllCustomersCarrier(long businessId, long carrierId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("businessId", businessId);
    paramObj.put("carrierId", carrierId);
    List<CustomerCarrier> customerCarrierList = (List) getSqlMapClientTemplate().queryForList(
        "getAllCustomersCarrier", paramObj);
    return customerCarrierList;
  }

  public List<Service> findMasterServiceId(long serviceId) {
    List<Service> customerServiceList = (List) getSqlMapClientTemplate().queryForList(
        "findMasterServiceId", serviceId);
    return customerServiceList;
  }

  public List<Carrier> getCustomerCarriersForBusiness(long customerId) {

    List<Carrier> customercarrierList = (List) getSqlMapClientTemplate().queryForList(
        "getCustomerCarrierForBusiness", customerId);
    return customercarrierList;

  }

  @Override
  public List<Service> getCustomerServicesForCarrier(Long carrierId, Long customerId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("carrierId", carrierId);
    paramObj.put("customerId", customerId);
    List<Service> carrierServicesList = new ArrayList<Service>();
    try {
      carrierServicesList = (List) getSqlMapClientTemplate().queryForList(
          "getCustomerforServicesForCarrier", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return carrierServicesList;
  }
  public List<Service> getServicesByCarrierId(long carrierId){
	  return (List<Service>)getSqlMapClientTemplate().queryForList("getServicesByCarrierId", carrierId);
	 }
  
  public List<Service> getServicesForCarrierAdmin(Long carrierId) {
	  	    Map<String, Object> paramObj = new HashMap<String, Object>(1);
	  	    paramObj.put("carrierId", carrierId);
	  	    List<Service> carrierServicesList = new ArrayList<Service>();
	  	    try {
	  	      carrierServicesList = (List) getSqlMapClientTemplate().queryForList("getServicesForCarrieradmin",
	  	          paramObj);
	  	    } catch (Exception e) {
	  	      e.printStackTrace();
	  	    }
	  	    return carrierServicesList;
	  	  }
}