package com.meritconinc.shiplinx.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.model.FutureReference;
import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidateResponse;
import com.meritconinc.shiplinx.model.BillingStatus;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.CurrencySymbol;
import com.meritconinc.shiplinx.model.ExchangeRateCurrency;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.CustomsInvoice;
import com.meritconinc.shiplinx.model.CustomsInvoiceProduct;
import com.meritconinc.shiplinx.model.DangerousGoods;
import com.meritconinc.shiplinx.model.InvoiceCharge;
import com.meritconinc.shiplinx.model.OrderProduct;
import com.meritconinc.shiplinx.model.OrderStatus;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.Products;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingLabel;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.model.UserBusiness;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.meritconinc.shiplinx.model.FutureReferencePackages;
import com.meritconinc.shiplinx.model.FutureReference;
import com.meritconinc.mmr.constants.Constants;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.shiplinx.model.Business;
import com.soluship.businessfilter.util.BusinessFilterUtil;
import com.meritconinc.shiplinx.model.AccessorialServices;
import com.meritconinc.shiplinx.model.AddressCheckList;
import com.meritconinc.shiplinx.model.EshipplusCarrierFilter;
import com.meritconinc.shiplinx.model.EshipplusPackage;
public class ShippingDAOImpl extends SqlMapClientDaoSupport implements ShippingDAO {
  private static final Logger log = LogManager.getLogger(ShippingDAOImpl.class);

  public List<String> findPackageTypeByName(String name) {

    List<String> packageType = null;
    try {
      packageType = (List<String>) getSqlMapClientTemplate().queryForList("getPackage", name);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return packageType;
  }

  public List getPackages() {
    List packageType = null;
    try {
      packageType = (List) getSqlMapClientTemplate().queryForList("getAllPackages");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return packageType;
  }

  public PackageType findPackageType(String type) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("type", type);
    PackageType packageType = null;
    try {
      packageType = (PackageType) getSqlMapClientTemplate().queryForObject("getPackageType",
          paramObj);
      // log.debug("-----"+packageType);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return packageType;
  }

  public List getServices() {

    List services = null;
    try {
      services = (List) getSqlMapClientTemplate().queryForList("getServices");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return services;
  }

  public void save(ShippingOrder shippingOrder) throws Exception {
    //Map<String, Object> paramObj = new HashMap<String, Object>(1);
    User user = UserUtil.getMmrUser();
    if (user != null && user.getTimeZone() != null && !user.getTimeZone().isEmpty()) {
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone()));
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      dateFormat.setTimeZone(cal.getTimeZone());
      shippingOrder.setDateCreated(Timestamp.valueOf(dateFormat.format(cal.getTime())));
   } else {
      shippingOrder.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    }

    try {
      getSqlMapClientTemplate().insert("addShippingOrder", shippingOrder);
    } catch (Exception e) {
      // log.debug("-----Exception-----"+e);
      e.printStackTrace();
      throw e;
    }

  }

  /**
   * Add Packages
   * 
   * @param packageList
   * @param orderId
   */
  public void addPackage(List<Package> packageList, Long orderId) throws Exception {
    int i = 0;
    try {
      for (Package pack : packageList) {
        pack.setOrderId(orderId);        
        getSqlMapClientTemplate().insert("addPackage", pack);
      }
    } catch (Exception e) {
      logger.debug("----addPackage----Exception----" + e);
      throw e;
    }
  }

  public Long saveCharges(Charge shippingCharge) throws Exception {
    // log.debug("------saveCharges----"+shippingCharge);
    try {
      return (Long) getSqlMapClientTemplate().insert("addCharge", shippingCharge);
    } catch (Exception e) {
      // log.debug("------Exception----"+e);
      throw e;
    }
  }

  public Long getServiceIdByName(String serviceName) {
    Long seriveId = 0L;
    try {
      seriveId = ((Service) getSqlMapClientTemplate().queryForObject("getServiceByName",
          serviceName)).getId();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return seriveId;
  }

  public List<PackageType> getAllPackages() {
    // TODO Auto-generated method stub
    try {
      return getSqlMapClientTemplate().queryForList("getAllPackages");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<OrderStatus> getShippingOrdersStatusOptions(long currentStatus) {

    List<OrderStatus> orderStatus = null;
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("currStatus", currentStatus);
    try {
      orderStatus = (List) getSqlMapClientTemplate().queryForList("getShippingOrdersStatusOptions",
          paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orderStatus;
  }

  public List<OrderStatus> getShippingOrdersAllStatus() {

    List<OrderStatus> orderStatus = null;
    try {
      orderStatus = (List) getSqlMapClientTemplate().queryForList("getShippingOrdersAllStatus");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orderStatus;
  }

  public List<String> getSearchOrderResult(ShippingOrder order) {

    List<String> searchResult = null;
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("carrierId", order.getCarrierId());
      paramObj.put("serviceId", order.getServiceId());
      paramObj.put("statusId", order.getStatusId());
      paramObj.put("masterTrackingNum", order.getMasterTrackingNum());

      paramObj.put("toDate",
          (("".equals(order.getToDate()) || order.getToDate() == null) ? Calendar.getInstance()
              .getTime() : order.getToDate()));

      if (!"".equals(order.getFromDate()))
        paramObj.put("fromDate", order.getFromDate());

      searchResult = (List<String>) getSqlMapClientTemplate().queryForList("getSearchOrderResult",
          paramObj);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return searchResult;
  }

  public void saveLabel(ShippingLabel label) {
    // log.debug("------saveLabel----"+label);
    try {
      getSqlMapClientTemplate().insert("saveLabel", label);
    } catch (Exception e) {
      // log.debug("------"+e);
      e.printStackTrace();
    }
  }

  public List<ShippingLabel> getLabelsByOrderId(long orderId) {
    List<ShippingLabel> shippingLabel = null;
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("orderId", orderId);
      shippingLabel = (List<ShippingLabel>) getSqlMapClientTemplate().queryForList(
          "getShippingLabelByOrderId", paramObj);
    } catch (Exception e) {
      // log.debug("--------"+e);
      e.printStackTrace();
    }
    return shippingLabel;
  }

  public List<String> getTodaysOrderResult(long customerId) {

    List<String> orderResult = null;
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("customerId", customerId);

      orderResult = (List<String>) getSqlMapClientTemplate().queryForList("getTodaysOrderResult",
          paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return orderResult;
  }

  public PackageType findOrderPackageType(long orderId) {

    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("orderId", orderId);
    PackageType packageType = null;
    try {
      packageType = (PackageType) getSqlMapClientTemplate().queryForObject("findOrderPackageType",
          paramObj);
      // log.debug("-----"+packageType);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return packageType;
  }

  public ShippingOrder getShippingOrder(long orderId) {

    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("orderId", orderId);
    ShippingOrder shipingOrder = null;
    try {
      shipingOrder = (ShippingOrder) getSqlMapClientTemplate().queryForObject(
          "getShippingOrderById", paramObj);
      // log.debug("-----"+shipingOrder);
      // log.debug("-----"+shipingOrder.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return shipingOrder;
  }

  public List<ShippingOrder> getShippingOrderByCurrentShipDate() {
    List<ShippingOrder> currentShipment = new ArrayList<ShippingOrder>();
    try {
      currentShipment = (List) getSqlMapClientTemplate().queryForList(
          "getShippingOrderByCurrentShipDate", new HashMap());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return currentShipment;
  }

  public List<Package> getShippingPackages(long orderId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("orderId", orderId);
    List<Package> listPackage = null;
    try {
      listPackage = (List<Package>) getSqlMapClientTemplate().queryForList("getShippingPackages",
          paramObj);
      // log.debug("-----"+listPackage);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return listPackage;

  }

  // public void updateShippingOrder(long orderId){
  // Map<String, Object> paramObj = new HashMap<String, Object>(1);
  // paramObj.put("orderId", orderId);
  // try{
  // getSqlMapClientTemplate().update("updateShippingOrder", paramObj);
  // }catch(Exception e){
  // e.printStackTrace();
  // }
  // }
  //
  public void updateShippingOrder(ShippingOrder order) {
    getSqlMapClientTemplate().update("updateShippingOrder", order);
    updateShippingOrderBillingStatus(order);
  }

  public void deleteShippingOrder(long orderId) {
    // log.debug("----deleteShippingOrder-----");
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("orderId", orderId);
    try {
      getSqlMapClientTemplate().update("deleteShippingOrder", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public Service getServiceById(Long serviceId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("serviceId", serviceId);
    Service service = null;
    try {
      service = (Service) getSqlMapClientTemplate().queryForObject("getService", paramObj);
      // log.debug("-----"+service);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return service;
  }

  public List<Charge> getShippingOrderCharges(long orderId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("orderId", orderId);
    List<Charge> listCharge = null;
    try {
      listCharge = (List<Charge>) getSqlMapClientTemplate().queryForList("getShippingCharges",
          paramObj);
      // log.debug("-----"+listCharge);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return listCharge;
  }

  public List<Charge> getShippingOrderChargesByInvoice(long orderId, long invoiceId) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("orderId", orderId);
    paramObj.put("invoiceId", invoiceId);
    List<Charge> listCharge = null;
    try {
      listCharge = (List<Charge>) getSqlMapClientTemplate().queryForList(
          "getShippingChargesByInvoice", paramObj);
      // log.debug("-----"+listCharge);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return listCharge;
  }

  public List<ShippingOrder> search(long carrierId) {
    // log.debug("--search---"+carrierId);

    List<ShippingOrder> searchResult = null;
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("carrierId", carrierId);
      // paramObj.put("customerId", customerId);
      searchResult = (List<ShippingOrder>) getSqlMapClientTemplate().queryForList(
          "searchOrderByCarrierId", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return searchResult;
  }

  //public List<CarrierChargeCode> getChargeListByCarrierAndCodes(long carrierId, String chargeCode,
  public List<CarrierChargeCode> getChargeListByCarrierAndCodes(long carrierId, String chargeCode,
      String chargeCodeLevel2) {

    // Currently we have 2 carriers for UPS, hard-coding here so that the charge codes are shared.
    // Other option is to duplicate the charge code entries
    // in the carrier_charge_code table for carrier id 5
    if (carrierId == ShiplinxConstants.CARRIER_UPS_USA)
      carrierId = ShiplinxConstants.CARRIER_UPS;

    List<CarrierChargeCode> searchResult = null;
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("carrierId", carrierId);
      paramObj.put("chargeCode", chargeCode);
      paramObj.put("chargeCodeLevel2", chargeCodeLevel2);
      searchResult = (List<CarrierChargeCode>) getSqlMapClientTemplate().queryForList(
          "getChargeByCarrierAndCodes", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return searchResult;
  }

  public ChargeGroup getChargeGroup(int groupId) {

    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("groupId", groupId);
      return (ChargeGroup) getSqlMapClientTemplate().queryForObject("getChargeGroup", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public CarrierChargeCode getChargeByCarrierAndCodes(long carrierId, String chargeCode,
      String chargeCodeLevel2) {

    // Currently we have 2 carriers for UPS, hard-coding here so that the charge codes are shared.
    // Other option is to duplicate the charge code entries
    // in the carrier_charge_code table for carrier id 5
    if (carrierId == ShiplinxConstants.CARRIER_UPS_USA)
      carrierId = ShiplinxConstants.CARRIER_UPS;
    long customerid=0;
    if(UserUtil.getMmrUser()!=null){
      customerid=UserUtil.getMmrUser().getCustomerId();
    }
    List<CarrierChargeCode> searchResult = this.getChargeListByCarrierAndCodes(carrierId,
        chargeCode, chargeCodeLevel2);
    /*if (searchResult == null || searchResult.size() == 0)
      return null;
    return searchResult.get(0);*/
    if (searchResult != null && searchResult.size()>0){
    	   for(CarrierChargeCode carrierChargeCodeTemp:searchResult){
    	    	if(carrierChargeCodeTemp.getCustomerId()>0 && carrierChargeCodeTemp.getCustomerId()==customerid){
    	    		return carrierChargeCodeTemp;
    	    	}
    	   	 else{
    	    		 if(carrierChargeCodeTemp.getCustomerId()==0){
    	   	        return carrierChargeCodeTemp;
    	   		 }
    	    	    }	
    	    }
    	    }
    
            return null;
  }

  public List<Long> getAllCustomersWithPendingCharges(long businessId) {
    List<Long> searchResult = null;
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("businessId", businessId);
      searchResult = (List<Long>) getSqlMapClientTemplate().queryForList(
          "getAllCustomersWithPendingCharges", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return searchResult;

  }

  public List<Long> getAllShipmentsWithPendingChargesForCustomer(long customerId) {
    List<Long> searchResult = null;
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("customerId", customerId);
      searchResult = (List<Long>) getSqlMapClientTemplate().queryForList(
          "getAllShipmentsWithPendingChargesForCustomer", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return searchResult;

  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ShippingOrder> getOrdersByTrackingNumber(Long carrierId, String trackingNumber) {
    // TODO Auto-generated method stub
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("carrierId", carrierId);
      paramObj.put("masterTrackingNum", trackingNumber);

      return getSqlMapClientTemplate().queryForList("searchOrderByTrackingNumber", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ShippingOrder> getOrdersByMasterTrackingNumber(Long carrierId, String trackingNumber) {
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("carrierId", carrierId);
      paramObj.put("masterTrackingNum", trackingNumber);

      return getSqlMapClientTemplate().queryForList("searchOrderByMasterTrackingNumber", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void updateCharge(Charge charge) throws Exception {
    // TODO Auto-generated method stub
    try {
      /*Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("name", charge.getName());
      paramObj.put("cost", charge.getCost());
      paramObj.put("status", charge.getStatus());
      paramObj.put("charge", charge.getCharge());
      paramObj.put("discountAmount", charge.getDiscountAmount());
      paramObj.put("tariffRate", charge.getCalculateTax());
      paramObj.put("id", charge.getId());
      paramObj.put("carrierId", charge.getCarrierId());
      paramObj.put("carrierName", charge.getCarrierName());
      paramObj.put("ediNumber", charge.getEdiInvoiceNumber());
      paramObj.put("costcurr", charge.getCostcurrency());
      paramObj.put("chargecurr", charge.getChargecurrency());
      paramObj.put("exchangerate", charge.getExchangerate());
      paramObj.put("calculateTax", charge.getCalculateTax());*/
      

      getSqlMapClientTemplate().update("updateCharge", charge);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updatePackage(Package pkg) {
    // TODO Auto-generated method stub
    try {
      // Map<String, Object> paramObj = new HashMap<String, Object>();
      // paramObj.put("reference1", pkg.getReference1());
      // paramObj.put("reference2", pkg.getReference2());
      // paramObj.put("reference3", pkg.getReference3());
      // paramObj.put("weight", pkg.getWeight());
      // paramObj.put("billedWeight", pkg.getBilledWeight());
      // paramObj.put("type", pkg.getType());
      // paramObj.put("packageId", pkg.getPackageId());

      getSqlMapClientTemplate().update("updatePackage", pkg);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ShippingOrder> getUnbilledShipments(long businessId, long customerId, String branch) {
    // TODO Auto-generated method stub
    try {
    	    	List<Long> businessIds=new ArrayList<Long>();
    	    	    	if(businessId>0){
    	    	              businessIds=BusinessFilterUtil.getBusIdParentId(businessId);
    	    	    	}else if(businessId==0){
    	    	    		 BusinessDAO businessDAO=(BusinessDAO)MmrBeanLocator.getInstance().findBean("businessDAO");
    	    		  		   List<Business>	allbusList=businessDAO.getHoleBusinessList();
    	    	    	   List<Long> busids=new ArrayList<Long>();
    	    					for(Business bs:allbusList){
    	    						 busids.add(bs.getId());
    	    					}
    	    				businessIds=busids;
    	    	    	}

    	    	    	List<UserBusiness> ubs=null;
    	    	    	    	    	    	if(UserUtil.getMmrUser()!=null){
    	    	    		    	    	    	ubs=BusinessFilterUtil.getUserBusinessByUserName(UserUtil.getMmrUser().getUsername());
    	    	    		    	    	    	
    	    	    		    	    	    	if(businessIds!=null && businessIds.size()>0 && ubs!=null && ubs.size()>0){
    	    	    		    	    	    		List<Long> userBusIds=new ArrayList<Long>();
    	    	    		    	    	    		userBusIds.addAll(businessIds);
    	    	    		    	    	    		businessIds.clear();
    	    	    		    	    	    		userBusIds.addAll(BusinessFilterUtil.getUserBusinessIds(UserUtil.getMmrUser().getUsername(), ubs));
    	    	    		    	    	    		businessIds.addAll(BusinessFilterUtil.getvalidatedBusIds(userBusIds));
    	    	    		    	    	    		
    	    	    		    	    	    	}
    	    	    	    	    	    	}
    	    	    	    	    	    	
    	    	    	
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("businessIds",businessIds);
      paramObj.put("customerId", customerId);
      paramObj.put("branch", branch);
      paramObj.put("statusId", ShiplinxConstants.CHARGE_READY_TO_INVOICE);

      return getSqlMapClientTemplate().queryForList("searchUnbilledShipments2", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<ShippingOrder> getLiveUnpaidShipments(long customerId) {
    // TODO Auto-generated method stub
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("customerId", customerId);
      paramObj.put("statusId", ShiplinxConstants.STATUS_CANCELLED);

      return getSqlMapClientTemplate().queryForList("searchLiveUnpaidShipments", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<ShippingOrder> searchShipmentsByOrderIdsAndCustomerAndCurrency(List<Long> orderIds,
      long customerId, String currency) {
    // TODO Auto-generated method stub
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("orderIds", orderIds);
      paramObj.put("customerId", customerId);
      paramObj.put("currency", currency);
      return getSqlMapClientTemplate().queryForList(
          "searchShipmentsByOrderIdsAndCustomerAndCurrency", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<ShippingOrder> searchShipmentsByStatusIdsAndCarrier(List<Long> statusIds,
      long carrierId) {
    // TODO Auto-generated method stub
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("statusIds", statusIds);
      paramObj.put("carrierId", carrierId);
      return getSqlMapClientTemplate().queryForList("searchShipmentsByStatusIdsAndCarrier",
          paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<Long> getCustomerIdsByOrderIds(List<Long> orderIds) {
    // TODO Auto-generated method stub
    try {
      return getSqlMapClientTemplate().queryForList("searchCustomerIdsByOrderIds", orderIds);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<String> getCurrencyByOrderIds(List<Long> orderIds) {
    // TODO Auto-generated method stub
    try {
      return getSqlMapClientTemplate().queryForList("searchCurrencyByOrderIds", orderIds);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<ShippingOrder> getShipments(ShippingOrder so) {
    // TODO Auto-generated method stub
    try {
      if (so.getCustomerId() != null && so.getCustomerId().longValue() <= 0)
        so.setCustomerId(null);
      if (so.getCarrierId() != null && so.getCarrierId().longValue() <= 0)
        so.setCarrierId(null);
      if (so.getServiceId() != null && so.getServiceId().longValue() <= 0)
        so.setServiceId(null);
      if (so.getFromDate() != null && so.getFromDate().trim().isEmpty())
        so.setFromDate(null);
      if (so.getMasterTrackingNum() != null && so.getMasterTrackingNum().trim().isEmpty())
        so.setMasterTrackingNum(null);
      if (so.getEdiInvoiceNumber() != null && so.getEdiInvoiceNumber().trim().isEmpty())
        so.setEdiInvoiceNumber(null);
      if (so.getShowCancelledShipments() != null && so.getShowCancelledShipments())
        so.setCancelledShipments("Y"); // checked - show cancelled shipments
      else
        so.setCancelledShipments("N"); // not checked - dont show cancelled shipments
      if (so.getStatusId() != null && so.getStatusId().longValue() <= 0)
        so.setStatusId(null);

      // If search is conducted by order id or order num or tracking number, or batch id or
      // reference, then excluded date range search
      if ((!StringUtil.isEmpty(so.getMasterTrackingNum()))
          || (so.getId() != null && so.getId() > 0) || (!StringUtil.isEmpty(so.getOrderNum()))
          || (!StringUtil.isEmpty(so.getEdiInvoiceNumber()))
          || (!StringUtil.isEmpty(so.getBatchId())) || (!StringUtil.isEmpty(so.getReferenceCode()))) {
        so.setFromDate(null);
        so.setToDate(null);
      }

      if (StringUtil.isEmpty(so.getOrderBy())) {
        so.setOrderBy("o.order_id");
      } else {// set the field
        if (so.getOrderBy().equalsIgnoreCase("Order #"))
          so.setOrderBy("o.order_id");
        else if (so.getOrderBy().equalsIgnoreCase("Shipment Date"))
          so.setOrderBy("o.scheduled_ship_date");
        else
          so.setOrderBy("o.order_id");
      }

      if (StringUtil.isEmpty(so.getOrder())) {
        so.setOrder("asc");

      }

      User user = UserUtil.getMmrUser();
      String role = user.getUserRole();
      String username = null;
      if (user != null)
        username = user.getUsername();

      log.info("User " + username + " performing search from / to: " + so.getFromDate() + " / "
          + so.getToDate());

      List<ShippingOrder> list_match = new ArrayList<ShippingOrder>();
      String queryId1 = "";
      String queryId2 = "";
      String queryId3 = "";
      if ("SEARCH_SHIPMENTS".equalsIgnoreCase(so.getPurpose())){
          queryId1 = "findShipments";
          queryId2 = "findShipmentsAdminById";
          queryId3 = "findShipmentsAdmin";
      }else{
          queryId1 = "findShipments1";
          queryId2 = "findShipmentsAdminById1";
          queryId3 = "findShipmentsAdmin1";
      }
      so.setPurpose(""); // Clear purpose
      List<ShippingOrder> list = getSqlMapClientTemplate().queryForList(queryId1, so);
    	  if (role.equalsIgnoreCase(ShiplinxConstants.ROLE_BUSINESSADMIN)) {
    	  List<ShippingOrder> list2 = new ArrayList<ShippingOrder>();
          if(list.size()==0 && so.getId()!=null && so.getId()>0){
          	list2 = getSqlMapClientTemplate().queryForList(queryId2, so);
          }else{
          	list2 = getSqlMapClientTemplate().queryForList(queryId3, so);
          }
          if (list.size() == 0) {
            list.addAll(list2);
          } else {
            for (int i = 0; i < list2.size(); i++) {
              boolean flagDublicate = true;
              for (int j = 0; j < list.size(); j++) {
              	if (list2.get(i).getId().equals(list.get(j).getId())) {
                  flagDublicate = false;
                }
              }
              if (flagDublicate) {
                list_match.add(list2.get(i));
              }
            }
            list.addAll(list_match);
          }
        }
    	  else if(role.equalsIgnoreCase(Constants.SYS_ADMIN_ROLE_CODE)){
    		      	  List<ShippingOrder> list2 = new ArrayList<ShippingOrder>();
    		            list2 = getSqlMapClientTemplate().queryForList("searchOrderBySystemAdmin", so);
    		            if (list.size() == 0) {
    		              list.addAll(list2);
    		            } else {
    		              for (int i = 0; i < list2.size(); i++) {
    		                boolean flagDublicate = true;
    		                for (int j = 0; j < list.size(); j++) {
    		                	if (list2.get(i).getId().equals(list.get(j).getId())) {
    		                    flagDublicate = false;
    		                  }
    		                }
    		                if (flagDublicate) {
    		                  list_match.add(list2.get(i));
    		                }
    		              }
    		              list.addAll(list_match);
    		            }
    		        }
      return list;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void releaseCharges(String ediInvoiceNumber) {
    // TODO Auto-generated method stub
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("ediInvoiceNumber", ediInvoiceNumber);
      paramObj.put("statusPendingRelease", ShiplinxConstants.CHARGE_PENDING_RELEASE);
      paramObj.put("statusReadyToInvoice", ShiplinxConstants.CHARGE_READY_TO_INVOICE);
      paramObj.put("billingStatusAwaitingConfirmation",
          ShiplinxConstants.BILLING_STATUS_AWAITING_CONFIRMATION);
      paramObj.put("statusChargeInvoice", ShiplinxConstants.CHARGE_INVOICED);

      List<ShippingOrder> orderList = getSqlMapClientTemplate().queryForList("selectOrderIdForEdiInvoiceNumber", paramObj);
            
            for(ShippingOrder s:orderList){
          	 if(s.getId()!=null){
          	  paramObj.put("order_id", s.getId());  
          	  getSqlMapClientTemplate().update("releaseCharges", paramObj);
          	  log.debug("ORDER_ID :"+s.getId()+" is updated with status 20");
            }
            } 
          }catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateShippingOrderCustomerBilling(ShippingOrder shipment) {
    // TODO Auto-generated method stub
    Map<String, Object> paramObj = new HashMap<String, Object>(5);
    try {
      if (shipment != null) {
        for (Charge charge : shipment.getCharges()) {
          this.updateCharge(charge);
        }
        paramObj.put("orderId", shipment.getId());
        paramObj.put("customerId", shipment.getCustomerId());
        paramObj.put("markPercent", shipment.getMarkPercent());
        paramObj.put("markType", shipment.getMarkType());
        paramObj.put("billingStatus", shipment.getBillingStatus());

        getSqlMapClientTemplate().update("updateShippingOrderCustomerBilling", paramObj);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateShippingOrderBillingInfo(ShippingOrder shipment) {
    // TODO Auto-generated method stub
    Map<String, Object> paramObj = new HashMap<String, Object>(5);
    try {
      if (shipment != null) {
        // for (Charge charge:shipment.getCharges()) {
        // this.updateCharge(charge);
        // }
        paramObj.put("orderId", shipment.getId());
        paramObj.put("customerId", shipment.getCustomerId());
        paramObj.put("markPercent", shipment.getMarkPercent());
        paramObj.put("markType", shipment.getMarkType());
        paramObj.put("billingStatus", shipment.getBillingStatus());

        getSqlMapClientTemplate().update("updateShippingOrderCustomerBilling", paramObj);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateShippingOrderBillingStatus(ShippingOrder shipment) {
    // TODO Auto-generated method stub
    int billingStatus = determineShipmentBillingStatus(shipment);
    log.debug("Billing Status for order " + shipment.getId() + " is " + billingStatus
        + " // previously " + shipment.getBillingStatus());
    if (shipment.getBillingStatus() != null && shipment.getBillingStatus() == billingStatus
        && !shipment.isInvoiced())

      return;

    Map<String, Object> paramObj = new HashMap<String, Object>(5);
    try {
      if (shipment != null) {
        paramObj.put("orderId", shipment.getId());
        paramObj.put("billingStatus", billingStatus);

        getSqlMapClientTemplate().update("updateShippingOrderBillingStatus", paramObj);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deleteCharge(Long id) {
    // TODO Auto-generated method stub
    if (id != null) {
      Map<String, Object> paramObj = new HashMap<String, Object>(1);
      paramObj.put("id", id);
      getSqlMapClientTemplate().delete("deleteCharge", paramObj);
    }
  }

  public boolean deleteCustomsInvoice(long ciId) {
    if (ciId > 0) {
      Map<String, Object> paramObj = new HashMap<String, Object>(1);
      paramObj.put("id", ciId);
      getSqlMapClientTemplate().delete("deleteCustomsInvoiceProduct", paramObj);
      getSqlMapClientTemplate().delete("deleteCustomsInvoice", paramObj);
    }
    return true;

  }

  public boolean addCustomsInvoiceInformation(CustomsInvoice ci) {
    boolean retval = true;
    try {
      long id = (Long) getSqlMapClientTemplate().insert("addCI", ci);
      ci.setId(id);
    } catch (Exception e) {
      retval = false;
      log.error("Could not add customs invoice!", e);
    }
    return retval;
  }

  public boolean addCustomsInvoiceProdutInformation(CustomsInvoiceProduct cip) {
    boolean retval = true;
    try {
      getSqlMapClientTemplate().insert("addCIProduct", cip);
    } catch (Exception e) {
      retval = false;
      log.error("Could not add customs invoice product!", e);
    }

    return retval;

  }

  public List<Products> getCustomsInvoiceProducts(long customsInvoiceId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    List<Products> productsList = new ArrayList<Products>();
    try {
      paramObj.put("customsInvoiceId", customsInvoiceId);
      productsList = (List) getSqlMapClientTemplate().queryForList("getCustomsInvoiceProducts",
          paramObj);

    } catch (Exception e) {
      log.error("Error Occured while getting the Order Product List" + e);
      e.printStackTrace();
    }

    return productsList;
  }

  public CustomsInvoice getCustomsInvoiceByOrderId(long orderId) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("orderId", orderId);
    return (CustomsInvoice) getSqlMapClientTemplate().queryForObject("getCustomsInvoiceByOrderId",
        orderId);
  }

  @Override
  public ShippingOrder findByBatchId(String batchId) {
    // TODO Auto-generated method stub
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("batchId", batchId);
    return (ShippingOrder) getSqlMapClientTemplate().queryForObject("findShipmentByBatchId",
        paramObj);
  }

  @Override
  public void updateShippingOrderExtended(ShippingOrder order) {
    // TODO Auto-generated method stub
    getSqlMapClientTemplate().update("updateShippingOrderExtended", order);
  }

  public void saveProductDetails(Products product, long orderId) {
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("productId", product.getProductId());
    paramObj.put("orderId", orderId);
    paramObj.put("orderedQuantity", product.getOrderedQuantity());
    getSqlMapClientTemplate().insert("addProductsToShipment", paramObj);
  }

  public void updateOrderFulfilled(Products product, long orderId) {
    // update the fulfilled value in order_product table.
    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("productId", product.getProductId());
    paramObj.put("orderId", orderId);
    paramObj.put("fulfilled", product.getOrderedQuantity());
    getSqlMapClientTemplate().update("updatefulfilledProductOrder", paramObj);
  }

  @Override
  public Service getServiceByCarrierAndTransitCode(Long carrierId, String transitCode) {
    // TODO Auto-generated method stub
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>(2);
      paramObj.put("carrierId", carrierId);
      paramObj.put("transitCode", transitCode);
      return (Service) getSqlMapClientTemplate().queryForObject(
          "getServiceByCarrierAndTransitCode", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public void addPackage(Package pack) {
    try {
      getSqlMapClientTemplate().insert("addPackage", pack);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e);
    }
  }

  public void updateOrderStatus(long OrderId, long statusId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(5);
    try {
      paramObj.put("orderId", OrderId);
      paramObj.put("statusId", statusId);
      getSqlMapClientTemplate().update("updateShippingOrderStatus", paramObj);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e);
    }
  }

  public List<OrderProduct> getOrderProducts(long orderId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    List<OrderProduct> oplist = new ArrayList<OrderProduct>();
    try {
      paramObj.put("orderId", orderId);
      oplist = (List) getSqlMapClientTemplate().queryForList("getOrderProducts", paramObj);

    } catch (Exception e) {
      log.error("Error Occured while getting the Order Product List" + e);
      e.printStackTrace();
    }

    return oplist;
  }

  public List<ShippingOrder> searchReferenceShipments(ShippingOrder order) {
    User user = UserUtil.getMmrUser();
    String username = null;
    if (user != null)
      username = user.getUsername();

    log.info("User " + username + " performing search from / to: " + order.getFromDate() + " / "
        + order.getToDate() + " / " + order.getReferenceValue());
    List<ShippingOrder> soList = new ArrayList<ShippingOrder>();
    try {
      soList = (List) getSqlMapClientTemplate().queryForList("searchReferenceShipments", order);
    } catch (Exception e) {
      log.error("Error Occured while getting the Shipments by Reference", e);
    }
    return soList;
  }

  public List<BillingStatus> getShippingBillingAllStatus() {
    List<BillingStatus> billingStatus = null;
    try {
      billingStatus = (List) getSqlMapClientTemplate().queryForList("getShippingBillingAllStatus");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return billingStatus;
  }

  private int determineShipmentBillingStatus(ShippingOrder order) {

    // if the order is an orphan or if we are waiting for admin to confirm the linked customer, then
    // leave status as is
    if (order.getBillingStatus() != null
        && (order.getBillingStatus().intValue() == ShiplinxConstants.BILLING_STATUS_AWAITING_CONFIRMATION || order
            .getBillingStatus().intValue() == ShiplinxConstants.BILLING_STATUS_ORPHAN))
      return order.getBillingStatus();

    // if customer is not known, this should be marked as an orphan
    if (order.getCustomerId() != null && order.getCustomerId() == 0)
      return ShiplinxConstants.BILLING_STATUS_ORPHAN;

    // if shipment is TP or Collect, then mark accordingly
    if (order.getBillToType() != null) {
      if (order.getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_THIRD_PARTY)) {
        return ShiplinxConstants.BILLING_STATUS_THIRD_PARTY;
      }
      if (order.getBillToType().equalsIgnoreCase(ShiplinxConstants.BILL_TO_COLLECT)) {
        return ShiplinxConstants.BILLING_STATUS_COLLECT;
      }
    }

    // if there are no actual charges for the shipment, then it means that the shipment was never
    // invoiced
    if (order.getActualCharges() == null || order.getActualCharges().size() == 0)
      return ShiplinxConstants.BILLING_STATUS_NOT_INVOICED;

    // if there are any charges that are pending release or ready to invoice, then we set the
    // shipment as ready to invoice
    for (Charge c : order.getActualCharges()) {
      if (c.getStatus() == ShiplinxConstants.CHARGE_PENDING_RELEASE
    		  || c.getStatus() == ShiplinxConstants.CHARGE_READY_TO_INVOICE
              || (c.getStatus() == ShiplinxConstants.CHARGE_CANCELLED && order.getBillingStatus() != ShiplinxConstants.BILLING_STATUS_INVOICED)) {

        return ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE;
      }
    }

    boolean allInvoiced = true;
    for (Charge c : order.getActualCharges()) {
    	if (c.getStatus() != ShiplinxConstants.CHARGE_INVOICED
    			&& c.getStatus() != ShiplinxConstants.CHARGE_CANCELLED) {

        allInvoiced = false;
      }
    }

    if (allInvoiced)
      return ShiplinxConstants.BILLING_STATUS_INVOICED;

    log.debug("Could not determine billing status of order " + order.getId());

    return order.getBillingStatus();

  }

  public List<DangerousGoods> getDangerousGoodsAll() {
    List<DangerousGoods> dgList = new ArrayList<DangerousGoods>();
    try {
      dgList = (List) getSqlMapClientTemplate().queryForList("getDangerousGoodsAll");

    } catch (Exception e) {
      // TODO: handle exception
    }

    return dgList;

  }

  // Start Issue No: 107
  /*
   * public void updateDeletedInvoice(Charge charge)throws Exception{ try{ Map<String,Object>
   * paramObj=new HashMap<String,Object>(); paramObj.put("id", charge.getId());
   * getSqlMapClientTemplate().update("updateInvoiceCancelStatus",paramObj); }catch(Exception e){
   * e.printStackTrace(); } }
   */
  // End

  @Override
  public void updateDeletedInvoice(Charge charge) throws Exception {
    try {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("id", charge.getId());

      getSqlMapClientTemplate().update("updateInvoiceCancelStatus", paramObj);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public List<CarrierChargeCode> getChargeCodeListByCarrierId(long carrierId) {
	    List<CarrierChargeCode> searchResult = null;
	    try {
	      Map<String, Object> paramObj = new HashMap<String, Object>();
	      paramObj.put("carrierId", carrierId);
	      searchResult = (List<CarrierChargeCode>) getSqlMapClientTemplate().queryForList(
	          "getChargeCodeListByCarrierId", paramObj);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	    return searchResult;

	  }

	  public List<CarrierChargeCode> getChargeNameListByCarrierIdAndChargeCode(long carrierId,
	      String chargeCode) {
	    List<CarrierChargeCode> searchResult = null;
	    try {
	      Map<String, Object> paramObj = new HashMap<String, Object>();
	      paramObj.put("carrierId", carrierId);
	      paramObj.put("chargeCode", chargeCode);
	      searchResult = (List<CarrierChargeCode>) getSqlMapClientTemplate().queryForList(
	          "getChargeCodeListByCarrierIdAndChargeCode", paramObj);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	    return searchResult;

	  }

	  public void deleteLabel(long orderId) {
	    try {
	      if (orderId > 0) {
	        getSqlMapClientTemplate().delete("deleteLabel", orderId);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  public void updateTotalCharges(ShippingOrder order) {
	    getSqlMapClientTemplate().update("updateTotalCharges", order);
	    updateShippingOrderBillingStatus(order);
	  }
	  public Charge getChargeById(Long id) {
        try {
		      return (Charge) getSqlMapClient().queryForObject("getChargeById", id);
		    } catch (Exception e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }
		    return null;
		 }
	  public void deleteChargesByOrderId(Long id) {
    // TODO Auto-generated method stub
    if (id != null) {
      Map<String, Object> paramObj = new HashMap<String, Object>(1);
      paramObj.put("orderId", id);
      getSqlMapClientTemplate().delete("deleteChargesByOrderId", paramObj);
    }
  }

	  public DhlShipValidateResponse getDHLvalidateResponce(DhlShipValidateResponse validateObj) {
    // TODO Auto-generated method stub
    Map<String, Object> paramObj = new HashMap<String, Object>(5);
    try {
      if (validateObj != null) {
        DhlShipValidateResponse DhlVal = (DhlShipValidateResponse) getSqlMapClientTemplate()
            .queryForObject("getShipValidateResponse", validateObj);
        return DhlVal;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
   return null;
  }
	  @Override
  public List<InvoiceCharge> getChargeandOrderByInvoiceId(int invoiceId) {

    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("invoiceId", invoiceId);
    return (List<InvoiceCharge>) getSqlMapClientTemplate().queryForList(
        "getChargeandOrderByInvoiceId", paramObj);
  }
  @Override
  public Charge getChargesByOrderandInvoiceId(long chargeId, long orderId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("chargeId", chargeId);
    paramObj.put("orderId", orderId);
    return (Charge) getSqlMapClientTemplate().queryForObject("getChargesByOrderandInvoiceId",
        paramObj);
  }
  
  //Written By Mohan R
  public CarrierChargeCode getChargeByChargeGroupId(long carrierId, int chargeGroupId) {

	    if (carrierId == ShiplinxConstants.CARRIER_UPS_USA)
	      carrierId = ShiplinxConstants.CARRIER_UPS;

	    CarrierChargeCode searchResult = null;
	    try {
	      Map<String, Object> paramObj = new HashMap<String, Object>();
	      paramObj.put("carrierId", carrierId);
	      paramObj.put("chargeGroupId", chargeGroupId);	      
	      searchResult = (CarrierChargeCode) getSqlMapClientTemplate().queryForObject(
	          "getChargeByChargeGroupId", paramObj);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	    return searchResult;
	  }
  
  //public void updateEDI(String ediNumber,long chargeId){
  public void updateEDI(String ediNumber,long chargeId,boolean commissionable){
	  	  Map<String, Object> paramObj = new HashMap<String, Object>();
	        paramObj.put("ediNumber",ediNumber);
	        paramObj.put("id", chargeId);
	        paramObj.put("commissionable",commissionable);
	  	  getSqlMapClientTemplate().update("updateEDI", paramObj);
	    }
	   
  //End
  public Carrier getCarrierByServiceId(Long serviceId) {
	    	Map<String, Object> paramObj = new HashMap<String, Object>();
	     	Carrier carrierDet=new Carrier();
	      	paramObj.put("serviceId", serviceId);
	      	carrierDet = (Carrier) getSqlMapClientTemplate().queryForObject("getCarrierByServiceId", paramObj);
	      	return carrierDet;
	      }
  
  @Override
    public CarrierChargeCode getChargeCodeById(long id) {
  	return (CarrierChargeCode) getSqlMapClientTemplate().queryForObject("getChargeCodeById", id);
     }
     
  public List<CarrierChargeCode> getChargeListByCarrierAndCodesGroup(long carrierId, String chargeCode,
		  		  	      String chargeCodeLevel2, int chargeGroupId){
		  		  
		  		  	    // Currently we have 2 carriers for UPS, hard-coding here so that the charge codes are shared.
		  		  	    // Other option is to duplicate the charge code entries
		  		  	    // in the carrier_charge_code table for carrier id 5
		  		  	    if (carrierId == ShiplinxConstants.CARRIER_UPS_USA)
		  		  	      carrierId = ShiplinxConstants.CARRIER_UPS;
		  		  
		  		  	    List<CarrierChargeCode> searchResult = null;
		  		  	    try {
		  		  	      Map<String, Object> paramObj = new HashMap<String, Object>();
		  		  	      paramObj.put("carrierId", carrierId);
		  		  	      paramObj.put("chargeCode", chargeCode);
		  		  	      paramObj.put("chargeCodeLevel2", chargeCodeLevel2);
		  		  	      paramObj.put("chargeGroupId", chargeGroupId);
		  		  	      searchResult = (List<CarrierChargeCode>) getSqlMapClientTemplate().queryForList(
		  		  	          "getChargeByCarrierAndCodesGroup", paramObj);
		  		  	    } catch (Exception e) {
		  		  	      e.printStackTrace();
		  		  	    }
		  		  
		  		       return searchResult;
		  		    }
		    
		        public CarrierChargeCode getChargeByCarrierAndCodesGroup(long carrierId, String chargeCode,
		  		  	      String chargeCodeLevel2, int chargeGroupId){
		  		  	// Currently we have 2 carriers for UPS, hard-coding here so that the charge codes are shared.
		  		  	    // Other option is to duplicate the charge code entries
		  		  	    // in the carrier_charge_code table for carrier id 5
		  		  	    if (carrierId == ShiplinxConstants.CARRIER_UPS_USA)
		  		  	      carrierId = ShiplinxConstants.CARRIER_UPS;
		  		  
		  		  	    List<CarrierChargeCode> searchResult = this.getChargeListByCarrierAndCodesGroup(carrierId,
		  		  	        chargeCode, chargeCodeLevel2, chargeGroupId);
		  		  	    if (searchResult == null || searchResult.size() == 0)
		  		  	      return null;
		  		  	    return searchResult.get(0);
		  		     }
		        
		        public void updateLoggedEvent(Map<String,Object> details) {
		        						// TODO Auto-generated method stub
		        						ShippingOrder shippingOrder=new ShippingOrder(); 		        						
		        						details.put("entityType", ShiplinxConstants.PAYMENT_EXCEPTION);
		        						details.put("deleteFlag", false);
		        						// shippingOrder=(ShippingOrder)getSqlMapClientTemplate().queryForObject("selectOrderId",
		        						// details);
		        						long orderId = (Long) getSqlMapClientTemplate().queryForObject(
		        								"getOrderFromPackageUsingPin", details.get("trackingNo"));
		        						shippingOrder = (ShippingOrder) getSqlMapClientTemplate()
		        								.queryForObject("getShippingOrderById", orderId);
		        						details.put("orderId", orderId);
		        						if( details.get("podDate") == null){
		        							details.put("podDate", shippingOrder.getScheduledShipDate());
		        						}
		        						if(shippingOrder.getCustomerId() != null && shippingOrder.getCustomerId() !=0){
		        							details.put("customerId", shippingOrder.getCustomerId());
		        							String customerName=(String)getSqlMapClientTemplate().queryForObject("getCustomerNameByCustomerId",details);
		        							details.put("userName", customerName);
		        						}
		        						else{
		        							details.put("userName", "admin");
		        						}
		        							
		        						getSqlMapClientTemplate().insert("updateLoggedEvent", details);
		        					}

				@Override
				public void updateShippingOrderBillingStatus(
						Map<String, Object> details) {
					// TODO Auto-generated method stub
					long orderId = (Long)getSqlMapClientTemplate().queryForObject("getOrderFromPackageUsingPin",details.get("trackingNo"));
										if(orderId>0){
											details.put("orderId",orderId);
											getSqlMapClientTemplate().update("updateBillingStatus",details);
										}
					
				}
				public String getTrackingNumberFromPackage(String trackingNumber){ 
										return (String)getSqlMapClientTemplate().queryForObject("getTrackingNumberFromPackage",trackingNumber);
									}

				@Override
				public List<ShippingOrder> findShipmentsAdminById(
						ShippingOrder so) {
					
					return (List)getSqlMapClientTemplate().queryForList("findShipmentsAdminById", so);
				}
				
				public List<ExchangeRateCurrency> getAllExchangeRateCurrency(){
					return (List)getSqlMapClientTemplate().queryForList("getAllExchangeRateCurrency");
				}

				public void updateExchangeRateCurrency(ExchangeRateCurrency exchangeRate){
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date now = new Date();
					String DateTime=dateFormat.format(now);
					exchangeRate.setDate(Timestamp.valueOf(DateTime));
					getSqlMapClientTemplate().update("updateExchangeRateCurrency", exchangeRate);
				}

				public Double getExchangeRate(String currencyCode, String currencyCode2){
					Map<String, Object> paramObj = new HashMap<String, Object>();
					paramObj.put("currencyCode", currencyCode);
					paramObj.put("currencyCode2", currencyCode2);
					return (Double) getSqlMapClientTemplate().queryForObject("getExchangeRate", paramObj);
				}
				public CurrencySymbol getCurrencyCodeByCountryName(String countryName){
					Map<String, Object> paramObj=new HashMap<String, Object>();
					paramObj.put("countryCode", countryName);
					return (CurrencySymbol) getSqlMapClientTemplate().queryForObject("getCurrencyCodeByCountryName", paramObj);
				}

				public List<CurrencySymbol> getallCurrencySymbol(){
					return  ( List<CurrencySymbol>)getSqlMapClientTemplate().queryForList("getallCurrencySymbol");
				}
				@Override
								public CurrencySymbol getSymbolByCurrencycode(
										String currencyCode) {
									return (CurrencySymbol)getSqlMapClientTemplate().queryForObject("getSymbolByCurrencycode",currencyCode);
								}
				public CurrencySymbol getCurrencyCodeById(int id){
					return (CurrencySymbol)getSqlMapClientTemplate().queryForObject("getCurrencyCodeById",id);
					
				}
				
				public void updateShippingOrderCurrency(long orderId, String newCurrency){
				     Map<String, Object> paramObj = new HashMap<String, Object>();
				     paramObj.put("orderId", orderId);
				     paramObj.put("newCurrency", newCurrency);
				     getSqlMapClientTemplate().update("updateShippingOrderCurrency", paramObj);
				    }
				
				@Override
								public String getCurrencyByCountry(String accCountry) {
									// TODO Auto-generated method stub
									Map<String, Object> paramObj = new HashMap<String, Object>();
									paramObj.put("acccountry", accCountry);
									String cur=(String) getSqlMapClientTemplate().queryForObject("getCurrencyByCountry", paramObj);
									return cur;
								}
				
								@Override
								public int getCountryIdByCountryCode(String accCountry,String currencyCode) {
									// TODO Auto-generated method stub
									Map<String, Object> paramObj = new HashMap<String, Object>();
									paramObj.put("acccountry", accCountry);
									paramObj.put("currencyCode",currencyCode);
									Integer cid=(Integer)getSqlMapClientTemplate().queryForObject("getCountryIdByCountryCode", paramObj);
									if(cid!=null){
										return cid.intValue();
									}else{
										return 1;
									}
								}
								
								@Override
		public List<ShippingOrder> getShippingOrders(
				List<Long> soluShipOrderIds) {
				    List<ShippingOrder> shipOrders=null;
				    shipOrders = getSqlMapClientTemplate().queryForList("getShippingOrderByIds", soluShipOrderIds);
				    return shipOrders;    
				  }
								
		public List<ShippingOrder> getOrdersByAddressId(long fromAddressId){
			 List<ShippingOrder> searchResult = null;
			 try{
				Map<String, Object> paramObj=new HashMap<String, Object>();
				paramObj.put("fromAddressId", fromAddressId);
				searchResult = (List<ShippingOrder>)getSqlMapClientTemplate().queryForList("getOrdersByAddressId", paramObj);
   			 } catch(Exception e){
						e.printStackTrace();
			   }
			return searchResult;
		}
								
		@Override
		public void updateBilledUOM(long id1) {
			// TODO Auto-generated method stub
			getSqlMapClientTemplate().update("updateBilledUOM", id1);	
		}
		
		@Override
										public Long insertFutureReference(FutureReference fc) {
											System.out.println("Enter impl");
											/*long in;
											in=((Long)getSqlMapClientTemplate().insert("addFutureReference",fc)).longValue();*/
											Long in = null;
											try{
											in=(Long)getSqlMapClientTemplate().insert("addFutureReference",fc);
											}
											catch(Exception e){
											e.printStackTrace();
											}
											System.out.println(in);
											return in;
										
								
										}
		
										@Override
										public List<FutureReference> getFutureReference(List<Long> businessIds) {
											// TODO Auto-generated method stub
											Map<String, Object> paramObj = new HashMap<String, Object>();
											paramObj.put("businessIds", businessIds);
											List<FutureReference> fc1 =getSqlMapClientTemplate().queryForList("getFutureReference",paramObj);
											 											
											
											return fc1;
										}
		
										@Override
										public void deleteFutureReference(Long id) {
											// TODO Auto-generated method stub
											getSqlMapClientTemplate().delete("deleteFutureReference",id);
										}
		
										@Override
										public FutureReference showFutureReference(Long id) {
											// TODO Auto-generated method stub
											 FutureReference fc2=(FutureReference) getSqlMapClientTemplate().queryForObject("showFutureReference", id);
											return fc2;
										}
		
										@Override
										public void insertFuturePackages(FutureReferencePackages futureRefPackList) {
											// TODO Auto-generated method stub
											getSqlMapClientTemplate().insert("addFutureReferencePackages",futureRefPackList);
											
										}
												@Override
										public List<FutureReferencePackages> showFutureReferencePackage(Long id1) {
											// TODO Auto-generated method stub
											List<FutureReferencePackages>frpList=getSqlMapClientTemplate().queryForList("showFutureReferencePackage", id1);
											return frpList;
										}
												
												
												 @Override
												 												 	public List<Long> getBusinessIdsByorderIds(
												 												 			List<Long> orderIds) {
												 												 		// TODO Auto-generated method stub
												 												 	  try {
												 												 	      return (List<Long>)getSqlMapClientTemplate().queryForList("getBusinessIdsByorderIds", orderIds);
												 												 	    } catch (Exception e) {
												 												 	      e.printStackTrace();
												 												 	    }
												 												     return null;
												 												 	}	
												 												 
												 												 
												 												 
												 												 @Override
												 												 								public List<ShippingOrder> getUnbilledShipmentsBySinglebus(
												 												 										Long businessId, long customerId, String branch) {
												 												 									// TODO Auto-generated method stub
												 												 									try {
												 												 								    	List<Long> businessIds=new ArrayList<Long>();
												 												 								    	if(businessId>0){
												 												 								              businessIds.add(businessId);
												 												 							    	} 
												 												 								      
												 												 								      Map<String, Object> paramObj = new HashMap<String, Object>();
												 												 								   /*   paramObj.put("businessId", businessId);*/
												 												 								      paramObj.put("businessIds",businessIds);
												 												 								      paramObj.put("customerId", customerId);
												 												 								      paramObj.put("branch", branch);
												 												 								      paramObj.put("statusId", ShiplinxConstants.CHARGE_READY_TO_INVOICE);
												 												 
												 												 								      return getSqlMapClientTemplate().queryForList("searchUnbilledShipments2", paramObj);
												 												 								    } catch (Exception e) {
												 												 								      e.printStackTrace();
												 												 								    }
												 												 							    return null;
												 												 							}
												 													
												 												@Override
												 												public ChargeGroup getChargeDetailsByProvince(String province) {
												 													
												 													Map<String, Object> paramObj = new HashMap<String, Object>();
												 													paramObj.put("provinceCode", province);
												 													paramObj.put("CountryCode", "CA");
												 													ChargeGroup cc=(ChargeGroup) getSqlMapClientTemplate().queryForObject(
												 															"getChargeDetailsByProvince",paramObj);
												 													return cc;
												 												}

	public List<ShippingOrder> getEshipPlusActiveShipments() {
		ShippingOrder so = new ShippingOrder();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("searchdate", dateFormat.format(date));
		paramObj.put("carrierId", ShiplinxConstants.CARRIER_ESHIPPLUS);
		paramObj.put("statusCanceled", ShiplinxConstants.STATUS_CANCELLED);
		List<ShippingOrder> list = getSqlMapClientTemplate().queryForList(
				"findEShipPlusShipments", paramObj);
		return list;
	}

	public List<AccessorialServices> getAllAccessorialServices(long typeNot) {

		AccessorialServices accessorialServiceList = new AccessorialServices();
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("typeNot", typeNot);
		return (List<AccessorialServices>) getSqlMapClientTemplate()
				.queryForList("getAllAccessorialServices", paramObj);

	}

	public List<AddressCheckList> getAddressCheckListByAddressId(Long addressId) {
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("addressId", addressId);
		return (List<AddressCheckList>) getSqlMapClientTemplate().queryForList(
				"getAddressCheckListByAddressId", paramObj);
	}

	public void addNewAddressCheckList(AddressCheckList addressCheckList) {
		getSqlMapClientTemplate().insert("addNewAddressCheckList",
				addressCheckList);

	}

	public void updateAddressCheckList(AddressCheckList addressCheckList) {
		getSqlMapClientTemplate().update("updateAddressCheckList",
				addressCheckList);
	}

	public AccessorialServices getAccessorialServiceByServiceGroupCode(
			String serviceGroupCode, long typeNot) {
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("serviceGroupCode", serviceGroupCode);
		paramObj.put("typeNot", typeNot);
		return (AccessorialServices) getSqlMapClientTemplate().queryForObject(
				"getAccessorialServiceByServiceGroupCode", paramObj);

	}

	public AccessorialServices getAccessorialServiceByServiceCode(
			String serviceCode) {
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("serviceCode", serviceCode);
		return (AccessorialServices) getSqlMapClientTemplate().queryForObject(
				"getAccessorialServiceByServiceCode", paramObj);

	}

	public List<EshipplusCarrierFilter> getEshipPlusCarrierByCustomerId(
			Long customerId) {
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("customerId", customerId);
		return (List<EshipplusCarrierFilter>) getSqlMapClientTemplate()
				.queryForList("getEshipPlusCarrierByCustomerId", paramObj);
	}

	public void updateEshipCarrierFilter(EshipplusCarrierFilter eshipCC,
			String sCustomerId) {
		Map<String, Object> paramObj = new HashMap<String, Object>();

		paramObj.put("customerId", sCustomerId);
		paramObj.put("eShipCarrier_id", eshipCC.getEshipCarrierId());
		paramObj.put("disabled", eshipCC.getDisabled());
		paramObj.put("eshipCarrierName", eshipCC.getEshipCarrierName());
		try {
			getSqlMapClientTemplate().update("updateEshipCarrierFilter",
					paramObj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void updateUserFreightClassMode(int disabled, Long customerId) {
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("autoFreight", disabled);
		paramObj.put("customerId", customerId);
		User user = UserUtil.getMmrUser();
		paramObj.put("username", user.getUsername());
		try {
			int i=getSqlMapClientTemplate().update("updateUserFreightClassMode",paramObj);
			if(disabled!=0 && i==0){
				log.error("Error in updating user table");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<EshipplusPackage> getEshipplusPackagesList() {
		// return (List<EshipplusPackage>)
		// getSqlMapClientTemplate().queryForList(
		// "getEshipplusPackagesList");
		return getSqlMapClientTemplate().queryForList(
				"getEshipplusPackagesList");
	}

	public void updateEshipLabel(long orderId, byte[] outputLabel) {
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("orderId", orderId);
		paramObj.put("label", outputLabel);
		paramObj.put("updated", 1);
		getSqlMapClientTemplate().update("updateEshipLabel", paramObj);
	}

	public Long getLatestOrderId() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"getLatestOrderId");
	}

	public int checkAccessorial(String chargeCode) {
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("serviceCode", chargeCode);

		int obj = (Integer) getSqlMapClientTemplate().queryForObject(
				"checkAccessorial", paramObj);
		if (obj > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public int getCurrencyIdByCurrencyCode(String currency){
					Integer cid=(Integer)getSqlMapClientTemplate().queryForObject("getCurrencyIdByCurrencyCode", currency);
					if(cid!=null){
						return cid.intValue();
					}else{
						return 1;
				}
				}
	@Override
	public int saveDuplicateOrder(ShippingOrder dupOrder) {
		//Map<String, Object> paramObj = new HashMap<String, Object>(1);
		User user = UserUtil.getMmrUser();
		if (user != null && user.getTimeZone() != null && !user.getTimeZone().isEmpty()) {
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone()));
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(cal.getTimeZone());
			dupOrder.setDateCreated(Timestamp.valueOf(dateFormat.format(cal.getTime())));  
		}
		else {
			dupOrder.setDateCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		    }
		try {
			getSqlMapClientTemplate().insert("addShippingOrder", dupOrder);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	@Override
	public int saveDuplicateCharges(List<Charge> charges) {
		try {
			for (Charge shippingCharge : charges) {
				getSqlMapClientTemplate().insert("addCharge", shippingCharge);
			}
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}	

   /**
    * Only return not cancelled shipments
    */
     @Override 
   public ShippingOrder getShippingOrderByReferenceOne(long referenceNo1,String cancelled) {
   // TODO Auto-generated method stub
   Map<String, Object> paramObj = new HashMap<String, Object>();
   paramObj.put("referenceNo1", referenceNo1);
   paramObj.put("cancelled", cancelled);
   ShippingOrder so=(ShippingOrder) getSqlMapClientTemplate().queryForObject("getShippingOrderByReferenceOne",paramObj);
   return so;
   }
	
}