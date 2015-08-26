package com.meritconinc.shiplinx.dao;

import java.util.List;
import java.util.Map;

import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidateResponse;
import com.meritconinc.shiplinx.model.BillingStatus;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.CustomsInvoice;
import com.meritconinc.shiplinx.model.CustomsInvoiceProduct;
import com.meritconinc.shiplinx.model.DangerousGoods;
import com.meritconinc.shiplinx.model.CurrencySymbol;
import com.meritconinc.shiplinx.model.ExchangeRateCurrency;
import com.meritconinc.shiplinx.model.InvoiceCharge;
import com.meritconinc.shiplinx.model.OrderProduct;
import com.meritconinc.shiplinx.model.OrderStatus;
import com.meritconinc.shiplinx.model.FutureReference;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.Products;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingLabel;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.model.FutureReferencePackages;
import com.meritconinc.shiplinx.model.AccessorialServices;
import com.meritconinc.shiplinx.model.AddressCheckList;
import com.meritconinc.shiplinx.model.EshipplusCarrierFilter;
import com.meritconinc.shiplinx.model.EshipplusPackage;

public interface ShippingDAO {

  public List<String> findPackageTypeByName(String name);

  public PackageType findPackageType(String name);

  public List<PackageType> getPackages();

  public List getServices();

  void save(ShippingOrder shippingOrder) throws Exception;

  /**
   * Add Packages
   * 
   * @param packageList
   * @param orderId
   */
  public void addPackage(List<Package> packageList, Long orderId) throws Exception;

  public Long saveCharges(Charge shippingCharge) throws Exception;

  public Long getServiceIdByName(String serviceName);

  public List<PackageType> getAllPackages();

  public List<OrderStatus> getShippingOrdersAllStatus();

  public List<OrderStatus> getShippingOrdersStatusOptions(long currentStatus);

  public List<String> getSearchOrderResult(ShippingOrder order);

  public void saveLabel(ShippingLabel label);
  
  public CarrierChargeCode getChargeCodeById(long id);

  public List<ShippingLabel> getLabelsByOrderId(long longValue);

  public List<String> getTodaysOrderResult(long customerId);

  public PackageType findOrderPackageType(long orderId);

  public ShippingOrder getShippingOrder(long orderId);

  public List<ShippingOrder> getShippingOrderByCurrentShipDate();

  public List<Package> getShippingPackages(long orderId);

  // public void updateShippingOrder(long orderId);
  public void updateShippingOrder(ShippingOrder order);

  public void deleteShippingOrder(long orderId);

  public Service getServiceById(Long serviceId);

  public List<Charge> getShippingOrderCharges(long orderId);

  public List<Charge> getShippingOrderChargesByInvoice(long orderId, long invoiceId);

  public List<ShippingOrder> search(long carrierId);

  public List<CarrierChargeCode> getChargeListByCarrierAndCodes(long carrierId, String chargeCode,
      String chargeCodeLevel2);

  public CarrierChargeCode getChargeByCarrierAndCodes(long carrierId, String chargeCode,
      String chargeCodeLevel2);

  public ChargeGroup getChargeGroup(int groupId);

  public List<Long> getAllCustomersWithPendingCharges(long businessId);

  public List<Long> getAllShipmentsWithPendingChargesForCustomer(long customerId);

  public List<ShippingOrder> getOrdersByTrackingNumber(Long carrierId, String trackingNumber);

  public List<ShippingOrder> getOrdersByMasterTrackingNumber(Long carrierId, String trackingNumber);

  public void updateCharge(Charge shippingCharge) throws Exception;

  public void updatePackage(Package pkg);

  public List<ShippingOrder> getShipments(ShippingOrder so);

  public List<ShippingOrder> getLiveUnpaidShipments(long customerId);

  public List<ShippingOrder> getUnbilledShipments(long businessId, long customerId, String branch);

  public List<ShippingOrder> searchShipmentsByOrderIdsAndCustomerAndCurrency(List<Long> orderIds,
      long customerId, String currency);

  public List<Long> getCustomerIdsByOrderIds(List<Long> orderIds);

  public List<String> getCurrencyByOrderIds(List<Long> orderIds);

  public void releaseCharges(String ediInvoiceNumber);

  public void updateShippingOrderCustomerBilling(ShippingOrder shipment);

  public void updateShippingOrderBillingInfo(ShippingOrder shipment);

  public void updateShippingOrderBillingStatus(ShippingOrder shipment);

  public void deleteCharge(Long id);

  public boolean addCustomsInvoiceInformation(CustomsInvoice ci);

  public boolean addCustomsInvoiceProdutInformation(CustomsInvoiceProduct cip);

  public CustomsInvoice getCustomsInvoiceByOrderId(long orderId);

  public boolean deleteCustomsInvoice(long ciId);

  public ShippingOrder findByBatchId(String batchId);

  public void updateShippingOrderExtended(ShippingOrder order);

  public void saveProductDetails(Products product, long orderId);

  public Service getServiceByCarrierAndTransitCode(Long carrierId, String transitCode);

  public void addPackage(Package pack);

  public void updateOrderStatus(long OrderId, long statusId);

  public void updateOrderFulfilled(Products prods, long orderId);

  public List<OrderProduct> getOrderProducts(long orderId);

  public List<ShippingOrder> searchShipmentsByStatusIdsAndCarrier(List<Long> statusIds,
      long carrierId);

  public List<ShippingOrder> searchReferenceShipments(ShippingOrder order);

  public List<BillingStatus> getShippingBillingAllStatus();

  public List<DangerousGoods> getDangerousGoodsAll();

  public List<Products> getCustomsInvoiceProducts(long customsInvoiceId);

  // public void updateDeletedInvoice(Charge shippingCharge) throws Exception;

  public void updateDeletedInvoice(Charge shippingCharge) throws Exception;

  public List<CarrierChargeCode> getChargeCodeListByCarrierId(long carrierId);

  public List<CarrierChargeCode> getChargeNameListByCarrierIdAndChargeCode(long carrierId,
      String chargeCode);

  public void deleteLabel(long orderId);

  public void updateTotalCharges(ShippingOrder order);
  
  public Charge getChargeById(Long id);
  
  public void deleteChargesByOrderId(Long id);
  
  public DhlShipValidateResponse getDHLvalidateResponce(DhlShipValidateResponse validateObj);
  public List<InvoiceCharge> getChargeandOrderByInvoiceId(int invoiceId);
  public Charge getChargesByOrderandInvoiceId(long chargeId, long orderId);
  //Written By Mohan R
  public CarrierChargeCode getChargeByChargeGroupId(long carrierId, int chargeGroupId);
  //
  //public void updateEDI(String ediNumber,long chargeId);
  public void updateEDI(String ediNumber,long chargeId, boolean commissionable);
  public Carrier getCarrierByServiceId(Long serviceId);
  
  public List<CarrierChargeCode> getChargeListByCarrierAndCodesGroup(long carrierId, String chargeCode,
		  		        String chargeCodeLevel2, int chargeGroupId);
  public CarrierChargeCode getChargeByCarrierAndCodesGroup(long carrierId, String chargeCode,
		  		      String chargeCodeLevel2, int chargeGroupId);
  public void updateShippingOrderBillingStatus(Map<String,Object> details);
  public void updateLoggedEvent(Map<String,Object> details);
  public String getTrackingNumberFromPackage(String trackingNumber);
  public List<ShippingOrder> findShipmentsAdminById(ShippingOrder so);
  public List<ExchangeRateCurrency> getAllExchangeRateCurrency();

  public void updateExchangeRateCurrency(ExchangeRateCurrency exchangeRate);

  public Double getExchangeRate(String currencyCode, String currencyCode2);

  public CurrencySymbol getCurrencyCodeByCountryName(String fromCountry);

  public List<CurrencySymbol> getallCurrencySymbol();
  
  public CurrencySymbol getSymbolByCurrencycode(String currencyCode);
  public CurrencySymbol getCurrencyCodeById(int id);
  public void updateShippingOrderCurrency(long orderId, String newCurrency);
  
  
  public String getCurrencyByCountry(String accCountry);
  
  public int getCountryIdByCountryCode(String accCountry,String currencyCode);
  public List<ShippingOrder> getShippingOrders(List<Long> soluShipOrderIds);
  public List<ShippingOrder> getOrdersByAddressId(long fromAddressId);
  public void updateBilledUOM(long id1);
  
  public Long insertFutureReference(FutureReference fc);
  public List<FutureReference>getFutureReference(List<Long> businessIds);
  public void deleteFutureReference(Long id2);
  public FutureReference showFutureReference(Long id1);

  public void insertFuturePackages(FutureReferencePackages futureRefPack);
  public List<FutureReferencePackages> showFutureReferencePackage(Long id1);
  public List<Long> getBusinessIdsByorderIds(List<Long> orderIds);
    
    public List<ShippingOrder> getUnbilledShipmentsBySinglebus(Long busid, long l,
    		String branch);

    public List<ShippingOrder> getEshipPlusActiveShipments();

	public List<AccessorialServices> getAllAccessorialServices(long typeNot);

	public List<AddressCheckList> getAddressCheckListByAddressId(Long addressId);

	public void addNewAddressCheckList(AddressCheckList fromAddressCheckList);

	public void updateAddressCheckList(AddressCheckList addressCheckList);

	public AccessorialServices getAccessorialServiceByServiceGroupCode(
			String serviceCode, long typeNot);

	public AccessorialServices getAccessorialServiceByServiceCode(
			String serviceCode);

	public List<EshipplusCarrierFilter> getEshipPlusCarrierByCustomerId(
			Long customerId);

	public void updateEshipCarrierFilter(EshipplusCarrierFilter eshipCC,
			String sCustomerId);

	public void updateUserFreightClassMode(int i, Long customerId);

	public List<EshipplusPackage> getEshipplusPackagesList();

	public void updateEshipLabel(long orderId, byte[] outputLabel);

	public Long getLatestOrderId();

	public ChargeGroup getChargeDetailsByProvince(String province);

	public int checkAccessorial(String chargeCode);

	public int getCurrencyIdByCurrencyCode(String currency);
	
	public int saveDuplicateOrder(ShippingOrder dupOrder);
	
	public int saveDuplicateCharges(List<Charge> charges);
	
	public ShippingOrder getShippingOrderByReferenceOne(long parseLong, String shipmentCancelled);
	
	public List<ShippingOrder> getShipmentsForTrack(ShippingOrder so);

	public List<Charge> getChargesByOrderIds(List<Long> orderIds);

	public List<Package> getPackagesByOrderIds(List<Long> orderIds);
	
	public List<CustomsInvoice> getCustomsInvoiceByOrderIds(List<Long> orderIds);
}