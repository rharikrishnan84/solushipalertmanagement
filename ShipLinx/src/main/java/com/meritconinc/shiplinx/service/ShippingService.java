package com.meritconinc.shiplinx.service;

import java.io.InputStream;
import java.util.List;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidateResponse;
import com.meritconinc.shiplinx.model.BatchShipmentInfo;
import com.meritconinc.shiplinx.model.BillingStatus;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.CCTransaction;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.CurrencySymbol;
import com.meritconinc.shiplinx.model.ExchangeRateCurrency;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.CustomsInvoice;
import com.meritconinc.shiplinx.model.DangerousGoods;
import com.meritconinc.shiplinx.model.InvoiceCharge;
import com.meritconinc.shiplinx.model.OrderProduct;
import com.meritconinc.shiplinx.model.OrderStatus;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;

public interface ShippingService {

  public List<String> findPackageTypeByName(String name);

  public PackageType findPackageType(String type);

  public List<PackageType> getPackages();

  public List getServices();

  public void save(ShippingOrder shippingOrder) throws Exception;

  /**
   * Add Packages
   * 
   * @param packageList
   * @param orderId
   */
  public void addPackage(List<Package> packageList, Long orderId) throws Exception;

  public Long saveCharge(Charge shippingCharge) throws Exception;

  public Long getServiceIdByName(String serviceName);

  public List<PackageType> getAllPackages();

  public void saveCharges(List<Charge> surchargesList, Long id) throws Exception;

  public List<OrderStatus> getShippingOrdersAllStatus();

  public List<OrderStatus> getShippingOrdersStatusOptions(long currentStatus);

  public List<String> getSearchOrderResult(ShippingOrder order);

  public List<String> getTodaysOrderResult(long customerId);

  public PackageType findOrderPackageType(long orderId);

  public ShippingOrder getShippingOrder(long orderId);

  public ShippingOrder getShippingNewOrder(long orderId);

  public List<Package> getShippingPackages(long orderId);

  public void updateShippingOrder(ShippingOrder order);

  public void deleteShippingOrder(long orderId);

  public Service getServiceById(Long serviceId);

  public List<Charge> getShippingOrderCharges(long orderId);

  public void updateStatus(Long long1, Long long2, long statusDelivered, String comment)
      throws Exception;

  public List<ShippingOrder> search(long carrierId);

  public List<CarrierChargeCode> getChargeListByCarrierAndCodes(long carrierId, String chargeCode,
      String chargeCodeLevel2);

  public CarrierChargeCode getChargeByCarrierAndCodes(long carrierId, String chargeCode,
      String chargeCodeLevel2);

  public ChargeGroup getChargeGroup(int groupId);

  public List<Long> getAllCustomersWithPendingCharges(long businessId);

  public List<ShippingOrder> getShipments(ShippingOrder so);

  public List<ShippingOrder> getUnbilledShipments(long businessId, long customerId, String branch);

  public boolean releaseCharges(String ediInvoiceNumber);

  public void assignCustomerToShipments(List<Long> shipmentIds, Long customerId);

  public void setShipmentsReadyForInvoice(List<Long> shipmentIds);

  public List<ShippingOrder> getOrdersByTrackingNumber(Long carrierId, String trackingNumber);

  public List<ShippingOrder> getOrdersByMasterTrackingNumber(Long carrierId, String trackingNumber);

  public void updateCharge(Charge charge);

  public boolean isPaymentRequired(ShippingOrder order);

  public CCTransaction processPayment(ShippingOrder order, CCTransaction transaction);

  public void deleteCharge(Long id);

  public void updatePackage(Package pkg);

  public String getTrackingURL(long orderId);

  public void applyAdditionalHandling(ShippingOrder order, Rating rate, int chargeType);

  public boolean sendCustomerEmailNotification(User user, ShippingOrder objSO, int selected);

  public boolean saveCustomsInvoice(CustomsInvoice ci);

  public CustomsInvoice getCustomsInvoiceByOrderId(long orderId);

  public List<ShippingOrder> uploadBatchShipmentFile(BatchShipmentInfo batchInfo, InputStream is);

  public List<ShippingOrder> createBatchShipments(List<ShippingOrder> shipments);

  public List<ShippingOrder> processBatchShipments(List<ShippingOrder> shipments,
      BatchShipmentInfo batchInfo);

  public void update(ShippingOrder shippingOrder) throws Exception;

  public void submitToWarehouse(ShippingOrder shippingOrder) throws Exception;

  public Service getServiceByCarrierAndTransitCode(Long carrierId, String transitCode);

  public void updateOrderStatus(long orderId, long statusId);

  public void updateOrderStatus(long orderId, long statusId, String Comment, boolean boolprivate);

  public List<OrderProduct> getOrderProducts(long orderId);

  public List<ShippingOrder> searchShipmentsByStatusIdsAndCarrier(List<Long> statusIds,
      long carrierId);

  public List<ShippingOrder> searchReferenceShipments(ShippingOrder order);

  public ShippingOrder repeatOrder(String strOrderId,String customs);

  public void runBillingUpdate();

  public List<BillingStatus> getShippingBillingAllStatus();

  public List<DangerousGoods> getDangerousGoodsAll();

  public boolean sendShipmentNotificationMail(ShippingOrder so, Business business);

  public void assignCustomerToShipment(ShippingOrder shipment, Long customerId);

  public boolean sendCustomerEmailNotificationRequest(User user, ShippingOrder objSO, int selected);

  public boolean sendCancelShipmentNotificationMail(ShippingOrder so, Business business);

  public List<CarrierChargeCode> getChargeCodeListByCarrierId(long carrierId);

  public List<CarrierChargeCode> getChargeNameListByCarrierIdAndChargeCode(long carrierId,
      String chargeCode);

  public void deleteLabel(long orderId);

  public Charge getChargeById(Long id);

  public void deleteChargesByOrderId(Long id);

  public DhlShipValidateResponse getDHLvalidateResponce(DhlShipValidateResponse so);

  public List<InvoiceCharge> getChargeandOrderByInvoiceId(int invoiceId);

  public Charge getChargesByOrderandInvoiceId(long chargeId, long orderId);
  //Written By Mohan R
  public CarrierChargeCode getChargeByChargeGroupId(long carrierId, int chargeGroupId);
  //End
  public List<ShippingOrder> findShipmentsAdminById(ShippingOrder so);
  
  public List<ExchangeRateCurrency> getAllExchangeRateCurrency();
    
  public void updateExchangeRateCurrency(ExchangeRateCurrency exchangeRate);

  public Double getExchangeRate(String currencyCode, String currencyCode2);

  public CurrencySymbol getCurrencyCodeByCountryName(String fromCountry);

  public List<CurrencySymbol> getallCurrencySymbol();
  public CurrencySymbol getSymbolByCurrencycode(String currencyCode); 
  
  public String getCurrencyByCountry(String accCountry);
  
  public int getCountryIdByCountryCode(String accCountry,String currencyCode);
  
  public String getCurrencyByAccountNumber(String accountNumber);
  
  public CurrencySymbol getCurrencyCodeById(int id);

public void updateShippingOrderCurrency(ShippingOrder ediShipment);
  
}