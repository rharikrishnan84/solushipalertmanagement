package com.meritconinc.shiplinx.dao;

import java.util.List;

import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.Service;

public interface CarrierServiceDAO {

  public List<CustomerCarrier> getCutomerCarrier(Long customerId);

  public List<Service> getServicesForCarrier(Long carrierId);

  public Service getService(Long serviceId);

  public Service getServiceByCarrierIdAndTransitCode(Long carrierId, String transitCode);

  public Service getServiceByCarrierIdAndCode(Long carrierId, String code);

  public Carrier getCarrier(Long carrierId);

  public Carrier getCarrierByBusiness(Long carrierId, Long businessId);

  public CustomerCarrier getCutomerCarrierDefaultAccount(long carrierId, long customerId);

  public CustomerCarrier getOrderCutomerCarrier(Long orderId);

  public List<CustomerCarrier> getAllCutomerCarrier(long businessId, Long customerId);

  public List<CustomerCarrier> getAllCustomersCarrier(long businessId, long carrierId);

  public List<Carrier> getCarriers();

  public CustomerCarrier getDefAccountByCountry(long carrierId, long customerId, String country);

  public List<CustomerCarrier> getCarrierAccounts(Long customerId, String countryName);

  public CustomerCarrier getCarrierAccount(Long customerId, Long businessId, Long carrierId,
      String country, String toCountry);

  public List<Carrier> getCarriersForBusiness(long businessId);
  public void setupNewBusinessCarrier(long newBusinessId,long defaultBusinessId);

  public Long getMaxServiceIdForCarrier(long carrierId);

  public void addService(Service service) throws Exception;

  public List<Service> findMasterServiceId(long serviceId);

  public List<Carrier> getCustomerCarriersForBusiness(long customerId);

  public List<Service> getCustomerServicesForCarrier(Long carrierId, Long customerId);
  
  public List<Service> getServicesByCarrierId(long carrierId);
  
  public List<Service> getServicesForCarrierAdmin(Long carrierId);
  
  public List<Service> getAllServices();
  
  public String getCurrencyByAccount(String accountNumber1);
  
  boolean getSchdulerFlagByDomain(String domain);

}