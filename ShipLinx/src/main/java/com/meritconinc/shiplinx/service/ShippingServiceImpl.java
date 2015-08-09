package com.meritconinc.shiplinx.service;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.Common;
import com.meritconinc.mmr.utilities.MessageUtil;
import com.meritconinc.mmr.utilities.MmrBeanLocator;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.mmr.utilities.mail.MailHelper;
import com.meritconinc.mmr.utilities.security.UserUtil;
import com.meritconinc.shiplinx.carrier.CarrierService;
import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidateResponse;
import com.meritconinc.shiplinx.dao.AddressDAO;
import com.meritconinc.shiplinx.model.FutureReference;
import com.meritconinc.shiplinx.dao.CarrierServiceDAO;
import com.meritconinc.shiplinx.dao.CustomerDAO;
import com.meritconinc.shiplinx.dao.LoggedEventDAO;
import com.meritconinc.shiplinx.dao.ShippingDAO;
import com.meritconinc.shiplinx.exception.ShiplinxException;
import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.BatchShipmentInfo;
import com.meritconinc.shiplinx.model.BillingStatus;
import com.meritconinc.shiplinx.model.CurrencySymbol;
import com.meritconinc.shiplinx.model.ExchangeRateCurrency;
import com.meritconinc.shiplinx.model.Business;
import com.meritconinc.shiplinx.model.CCTransaction;
import com.meritconinc.shiplinx.model.Carrier;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.Customer;
import com.meritconinc.shiplinx.model.CustomerCarrier;
import com.meritconinc.shiplinx.model.CustomsInvoice;
import com.meritconinc.shiplinx.model.CustomsInvoiceProduct;
import com.meritconinc.shiplinx.model.DangerousGoods;
import com.meritconinc.shiplinx.model.InvoiceCharge;
import com.meritconinc.shiplinx.model.LoggedEvent;
import com.meritconinc.shiplinx.model.OrderProduct;
import com.meritconinc.shiplinx.model.OrderStatus;
import com.meritconinc.shiplinx.model.Package;
import com.meritconinc.shiplinx.model.PackageType;
import com.meritconinc.shiplinx.model.Products;
import com.meritconinc.shiplinx.model.Rating;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingLabel;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.opensymphony.xwork2.ActionContext;
import com.meritconinc.shiplinx.model.FutureReferencePackages;
import com.soluship.businessfilter.util.BusinessFilterUtil;

import com.meritconinc.shiplinx.model.AccessorialServices;
import com.meritconinc.shiplinx.model.AddressCheckList;
import com.meritconinc.shiplinx.model.EshipplusCarrierFilter;
import com.meritconinc.shiplinx.model.EshipplusPackage;

public class ShippingServiceImpl implements ShippingService {

  private static final Logger log = LogManager.getLogger(ShippingServiceImpl.class);
  ShippingDAO shippingDAO;
  LoggedEventDAO loggedEventDAO;
  private AddressDAO addressDAO;
  private ProductManager productManagerService;
  private CreditCardTransactionManager creditCardService;
  private MarkupManager markupManagerService;
  private PinBlockManager pinBlockManager;
  private CarrierServiceDAO carrierServiceDAO;
  private CustomerDAO customerDAO;
  private CarrierServiceManager carrierServiceManager;
  private List<ShippingOrder> shipments = null;
  private BatchShipmentInfo batchInfo = null;
  private ShippingService shippingService;
 
  public List<String> findPackageTypeByName(String name) {
    return getShippingDAO().findPackageTypeByName(name);
  }

  public MarkupManager getMarkupManagerService() {
    return markupManagerService;
  }

  public void setCreditCardService(CreditCardTransactionManager creditCardService) {
    this.creditCardService = creditCardService;
  }

  public void setMarkupManagerService(MarkupManager markupManagerService) {
    this.markupManagerService = markupManagerService;
  }

  public void setPinBlockManager(PinBlockManager pinBlockManager) {
    this.pinBlockManager = pinBlockManager;
  }

  public ShippingDAO getShippingDAO() {
    return shippingDAO;
  }

  public void setShippingDAO(ShippingDAO shippingDAO) {
    this.shippingDAO = shippingDAO;
  }

  public LoggedEventDAO getLoggedEventDAO() {
    return loggedEventDAO;
  }

  public void setLoggedEventDAO(LoggedEventDAO loggedEventDAO) {
    this.loggedEventDAO = loggedEventDAO;
  }

  public void setAddressDAO(AddressDAO addressDAO) {
    this.addressDAO = addressDAO;
  }

  public void setCarrierServiceDAO(CarrierServiceDAO carrierServiceDAO) {
    this.carrierServiceDAO = carrierServiceDAO;
  }

  public void setCustomerDAO(CustomerDAO customerDAO) {
    this.customerDAO = customerDAO;
  }

  public List<PackageType> getPackages() {
    return getShippingDAO().getPackages();
  }

  public PackageType findPackageType(String type) {
    return getShippingDAO().findPackageType(type);
  }

  public List getServices() {
    return getShippingDAO().getServices();
  }

  public CarrierServiceManager getCarrierServiceManager() {
    return carrierServiceManager;
  }

  public void setCarrierServiceManager(CarrierServiceManager carrierServiceManager) {
    this.carrierServiceManager = carrierServiceManager;
  }

  public void save(ShippingOrder shippingOrder) throws Exception {

    // if the order# is not assigned, then assign in
    if (shippingOrder.getOrderNum() == null || shippingOrder.getOrderNum().length() == 0) {
      String[] pins = this.pinBlockManager.getNewPrefixedPinNumbers(
          ShiplinxConstants.PIN_TYPE_ORDER_NUMBERS, 1, shippingOrder.getBusinessId());
      shippingOrder.setOrderNum(pins[0]);
    }
    shippingOrder.getFromAddress().setDefaultFromAddress(false);
    shippingOrder.getFromAddress().setDefaultToAddress(false);
    shippingOrder.getToAddress().setDefaultFromAddress(false);
    shippingOrder.getToAddress().setDefaultToAddress(false);
    if(shippingOrder.getCODValue()!=null && shippingOrder.getCODValue()>0 && shippingOrder.getCarrierId()==ShiplinxConstants.CARRIER_PUROLATOR){
    	    	if(shippingOrder.getCODPin()==null || shippingOrder.getCODPin().isEmpty()){
    	    		shippingOrder.setCODPin(ShiplinxConstants.DEFAULT_COD_PIN);
    	    	}
    	    }
    // save the address to address book if requested on page. This is done
    // by simply adding the customer_id to the address object
    if (shippingOrder.isSaveFromAddress())
      shippingOrder.getFromAddress().setCustomerId(shippingOrder.getCustomerId());
    else
      shippingOrder.getFromAddress().setCustomerId(0);
    if (shippingOrder.isSaveToAddress())
      shippingOrder.getToAddress().setCustomerId(shippingOrder.getCustomerId());
    else
      shippingOrder.getToAddress().setCustomerId(0);

    long fromAddId = addressDAO.add(shippingOrder.getFromAddress());
    long toAddId = addressDAO.add(shippingOrder.getToAddress());

    shippingOrder.setShipFromId(fromAddId);
    shippingOrder.setShipToId(toAddId);
    // Set the username who created the shipment
    if (StringUtil.isEmpty(shippingOrder.getCreatedBy())) {
      if (UserUtil.getMmrUser() != null)
        shippingOrder.setCreatedBy(UserUtil.getMmrUser().getUsername());
      else
        shippingOrder.setCreatedBy(ShiplinxConstants.SYSTEM);
    }

    // if tracking URL is not set, then try to set it here
    // For example, when the shipment is created through EDI upload
    try {
      if (StringUtil.isEmpty(shippingOrder.getTrackingURL())
          && shippingOrder.getCarrierId() != null && shippingOrder.getCarrierId() > 0
          && !StringUtil.isEmpty(shippingOrder.getMasterTrackingNum())) {
        Carrier carrier = this.carrierServiceDAO.getCarrier(shippingOrder.getCarrierId());

        WebApplicationContext context = WebApplicationContextUtils
            .getWebApplicationContext((ServletContext) ActionContext.getContext().get(
                ServletActionContext.SERVLET_CONTEXT));

        CarrierService carrierService = (CarrierService) context.getBean(carrier
            .getImplementingClass());
        String url = carrierService.getTrackingURL(shippingOrder);
        shippingOrder.setTrackingURL(url);
      }
    } catch (Exception e) {
      log.error(
          "Could not determine tracking URL for carrier/tracking#" + shippingOrder.getCarrierId()
              + " / " + shippingOrder.getMasterTrackingNum(), e);
    }

    getShippingDAO().save(shippingOrder);
    if (shippingOrder.getCarrierId() != null && shippingOrder.getCarrierId() == 80
        && shippingOrder.getCustomerId() != null) {
      CustomerCarrier customerCarrier = this.carrierServiceDAO.getCutomerCarrierDefaultAccount(
          shippingOrder.getCarrierId(), shippingOrder.getCustomerId());
      if (customerCarrier != null) {
        String masterTrackingNumber = customerCarrier.getAccountNumber1()
            + customerCarrier.getProperty5();
        shippingOrder.setMasterTrackingNum(masterTrackingNumber);
        String customerCarrierProperty5 = customerCarrier.getProperty5();
        int customerCarrierProperty5Update = Integer.parseInt(customerCarrierProperty5);
        customerCarrierProperty5Update++;
        customerCarrierProperty5 = String.valueOf(customerCarrierProperty5Update);
        customerCarrier.setProperty5(customerCarrierProperty5);
        long carrierAccountId = customerCarrier.getCarrierAccountId().longValue();
        this.customerDAO.editCustomerCarrier(customerCarrier, Long.valueOf(carrierAccountId));

        getShippingDAO().updateShippingOrder(shippingOrder);
      }
    }

    log.debug("---------saveCharges-----------");    
    saveCharges(shippingOrder.getCharges(), shippingOrder.getId());
    shippingDAO.updateShippingOrderBillingStatus(shippingOrder);

    log.debug("---------addPackage-----------" + shippingOrder.getPackages());
    addPackage(shippingOrder.getPackages(), shippingOrder.getId());
    log.debug("---------saveLabel-----------");
    for (ShippingLabel label : shippingOrder.getLabels()) {
      label.setOrderId(shippingOrder.getId().longValue());
      getShippingDAO().saveLabel(label);
    }

    log.debug("-----------save product details");
    saveProductDetails(shippingOrder);
    log.debug("---------saveCustomsInvoice-----------");
    if (shippingOrder.getCustomsInvoice() != null && shippingOrder.isCustomsInvoiceRequired()) {
      shippingOrder.getCustomsInvoice().setOrderId(shippingOrder.getId().longValue());
      this.saveCustomsInvoice(shippingOrder.getCustomsInvoice());
    }

  }

  public void update(ShippingOrder so) throws Exception {
    // if the order# is not assigned, then assign in
    if (so != null && so.getId().longValue() > 0 && !StringUtil.isEmpty(so.getOrderNum())) {
      // ShippingOrder
      if (so.getCarrierId() != null && so.getCarrierId() == 80 && so.getCustomerId() != null) {
        CustomerCarrier customerCarrier = this.carrierServiceDAO.getCutomerCarrierDefaultAccount(
            so.getCarrierId(), so.getCustomerId());
        if (customerCarrier != null && customerCarrier.getProperty5() != so.getMasterTrackingNum()) {
          String masterTrackingNumber = customerCarrier.getAccountNumber1()
              + customerCarrier.getProperty5();
          so.setMasterTrackingNum(masterTrackingNumber);
          String customerCarrierProperty5 = customerCarrier.getProperty5();
          int customerCarrierProperty5Update = Integer.parseInt(customerCarrierProperty5);
          customerCarrierProperty5Update++;
          customerCarrierProperty5 = String.valueOf(customerCarrierProperty5Update);
          customerCarrier.setProperty5(customerCarrierProperty5);
          long carrierAccountId = customerCarrier.getCarrierAccountId().longValue();
          this.customerDAO.editCustomerCarrier(customerCarrier, Long.valueOf(carrierAccountId));
        }
      }
      
      if(so!=null && so.getFromAddress()!=null){
    	 Object fromUpdate =  ActionContext.getContext().getSession().get("ship_from_update");
    	 if(fromUpdate!=null){
    		 so.getFromAddress().setAddressId((Long) fromUpdate);
    		 
    	 }
      }
      if(so!=null && so.getToAddress()!=null){
    	  Object toUpdate = ActionContext.getContext().getSession().get("ship_to_update");
    	  if(toUpdate!=null){
    		  so.getToAddress().setAddressId((Long)toUpdate);
    	  }
      }
      this.shippingDAO.updateShippingOrderExtended(so);

      // update From and To Addresses
      if (so.getFromAddress() != null)
        this.addressDAO.edit(so.getFromAddress());
      if (so.getToAddress() != null)
        this.addressDAO.edit(so.getToAddress());

      // charges
      if (so.getCharges() != null) {
        for (Charge shippingCharge : so.getCharges()) {
          if (shippingCharge.getId() == null || shippingCharge.getId().longValue() <= 0) {
            shippingCharge.setOrderId(so.getId());
            getShippingDAO().saveCharges(shippingCharge);
          }
        }
      }

      shippingDAO.updateShippingOrderBillingStatus(so);

      // Packages
      if (so.getPackages() != null && so.getPackages().size() > 0) {
        for (Package p : so.getPackages()) {
          if (p.getPackageId() <= 0) {
            // Add Package
            p.setOrderId(so.getId());
            this.shippingDAO.addPackage(p);
          } else {
            // Update Package
            updatePackage(p);
          }
        }
      }
      // Labels
      for (ShippingLabel label : so.getLabels()) {
        if (label.getId() == null || label.getId().longValue() <= 0) {
          label.setOrderId(so.getId().longValue());
          getShippingDAO().saveLabel(label);
        }
      }
      // CustomsInvoice
      if (so.getCustomsInvoice() != null && so.isCustomsInvoiceRequired()) {
        if (so.getCustomsInvoice().getId() > 0) {
          deleteCustomsInvoice(so.getCustomsInvoice().getId()); // get
          // rid
          // of
          // the
          // entire
          // existing
          // CI
          // and
          // create
          // a
          // new
          // one
        }
        so.getCustomsInvoice().setOrderId(so.getId().longValue());
        saveCustomsInvoice(so.getCustomsInvoice());
      }
      // Change Customer
      if (so.getCustomerId() == null) {
        so.setCustomerId(0L);
      }
      if (so.getWebCustomerId() != null && so.getWebCustomerId().longValue() > 0
          && so.getCustomerId().longValue() != so.getWebCustomerId().longValue()) {
        // Check if charges are not invoiced yet, customer can only be
        // changed if charges are not invoiced yet
        boolean isReassign = true;
        for (Charge c : so.getCharges()) {
          if (c.getStatus().intValue() == ShiplinxConstants.CHARGE_INVOICED) {
            isReassign = false;
            break;
          }
        }
        if (isReassign) {
          // Change Customer
          this.assignCustomerToShipment(so, so.getWebCustomerId());
        }
      }
    }
  }

  /**
   * Add Packages
   * 
   * @param packageList
   * @param orderId
   */
  public void addPackage(List<Package> packageList, Long orderId) throws Exception {
    getShippingDAO().addPackage(packageList, orderId);
  }
  public void updatePackage(Package pkg) {
    shippingDAO.updatePackage(pkg);
  }

  public Long saveCharge(Charge shippingCharge) throws Exception {
    return getShippingDAO().saveCharges(shippingCharge);
  }

  public Long getServiceIdByName(String serviceName) {
    return getShippingDAO().getServiceIdByName(serviceName);
  }

  public List<PackageType> getAllPackages() {
    return getShippingDAO().getAllPackages();
  }

  public void saveCharges(List<Charge> surchargesList, Long orderId) throws Exception {
    try {
      /* ShippingOrder order =shippingDAO.getShippingOrder(orderId); */
      for (Charge shippingCharge : surchargesList) {
        shippingCharge.setOrderId(orderId);
        if (shippingCharge != null && shippingCharge.getStatus() == null) {
          shippingCharge.setStatus(0);
        }
        
        if(shippingCharge.getTariffInLocalCurrency()!=null && shippingCharge.getTariffInLocalCurrency()!=0){
        	shippingCharge.setTariffRate(shippingCharge.getTariffInLocalCurrency());
        }
        // Start Issue No:44
        /*
         * if(shippingCharge!=null && shippingCharge.getChargeCode()==null &&
         * shippingCharge.getChargeCodeLevel2()==null) { List<CarrierChargeCode>
         * carrierChargeCodeList=
         * shippingDAO.getChargeListByCarrierAndCodes(order.getCarrierId(),shippingCharge
         * .getChargeCode(), shippingCharge.getChargeCodeLevel2()); if(carrierChargeCodeList !=
         * null){ for (CarrierChargeCode carrierChargeCode : carrierChargeCodeList) {
         * if(carrierChargeCode.getChargeName().equals(shippingCharge.getName()) ||
         * carrierChargeCode.getGroupCode().equals(shippingCharge.getName())){
         * shippingCharge.setChargeCode(carrierChargeCode.getChargeCode());
         * shippingCharge.setChargeCodeLevel2(carrierChargeCode.getChargeCodeLevel2());
         * shippingCharge.setCurrency(order.getCurrency()); } } } }
         */
        // End
        getShippingDAO().saveCharges(shippingCharge);
      }
    } catch (Exception e) {
      log.error("Could not add charge for shipment " + orderId, e);
      throw e;
    }
  }

  public List<OrderStatus> getShippingOrdersAllStatus() {

    return getShippingDAO().getShippingOrdersAllStatus();

  }

  public List<OrderStatus> getShippingOrdersStatusOptions(long currentStatus) {

    return getShippingDAO().getShippingOrdersStatusOptions(currentStatus);

  }

  public List<String> getSearchOrderResult(ShippingOrder order) {
    return getShippingDAO().getSearchOrderResult(order);

  }

  public List<ShippingOrder> getOrdersByTrackingNumber(Long carrierId, String trackingNumber) {

    // if tracking number is null or length is 0, then return null
    if (trackingNumber == null || trackingNumber.length() == 0)
      return null;

    return getShippingDAO().getOrdersByTrackingNumber(carrierId, trackingNumber);
  }

  public List<ShippingOrder> getOrdersByMasterTrackingNumber(Long carrierId, String trackingNumber) {

    // if tracking number is null or length is 0, then return null
    if (trackingNumber == null || trackingNumber.length() == 0)
      return null;

    return getShippingDAO().getOrdersByMasterTrackingNumber(carrierId, trackingNumber);
  }

  public List<String> getTodaysOrderResult(long customerId) {

    return getShippingDAO().getTodaysOrderResult(customerId);
  }

  public PackageType findOrderPackageType(long orderId) {

    return getShippingDAO().findOrderPackageType(orderId);
  }

  public ShippingOrder getShippingOrder(long orderId) {
    ShippingOrder order = getShippingDAO().getShippingOrder(orderId);
    if (order != null) {
      order.setPaymentRequired(isPaymentRequired(order));
      fixBigDecimalPrecision(order);
    }
    if (order.getScheduledShipDate() != null)
      order.setScheduledShipDate_web(FormattingUtil.getFormattedDate(order.getScheduledShipDate(),
          FormattingUtil.DATE_FORMAT_WEB));

    try {
      if (order.getFromAddress().getProvinceCode().equalsIgnoreCase("QC"))
        order.getFromAddress().setProvinceCode("PQ");
      if (order.getToAddress().getProvinceCode().equalsIgnoreCase("QC"))
        order.getToAddress().setProvinceCode("PQ");
    } catch (Exception e) {
    }

    return order;

  }

  public ShippingOrder getShippingNewOrder(long orderId) {
    ShippingOrder order = getShippingDAO().getShippingOrder(orderId);
    if (order != null) {
      order.setPaymentRequired(isPaymentRequired(order));
      if (order.getUnitmeasure() == 2 && order.getSaveShipmet()==0) {
	      for (Package pack : order.getPackages()) {
	        double lengthDouble = pack.getLength().doubleValue() / 0.39370;
	        double widthDouble = pack.getWidth().doubleValue() / 0.39370;
	        double heightDouble = pack.getHeight().doubleValue() / 0.39370;
	        double weightDouble = pack.getWeight().doubleValue()/2.2;
	        BigDecimal length = new BigDecimal(lengthDouble);
	        BigDecimal width = new BigDecimal(widthDouble);
	        BigDecimal height = new BigDecimal(heightDouble);
	        pack.setWeight(new BigDecimal(weightDouble).setScale(2,BigDecimal.ROUND_HALF_UP));
	        pack.setLength(new BigDecimal(FormattingUtil.roundFigureRates(lengthDouble, 0)));
	        pack.setWidth(new BigDecimal(FormattingUtil.roundFigureRates(widthDouble, 0)));
	        pack.setHeight(new BigDecimal(FormattingUtil.roundFigureRates(heightDouble, 0)));
	      }
	    }else{
      for (Package p : order.getPackages()) {
        p.setLength(new BigDecimal(FormattingUtil.roundFigureRates(p.getLength().doubleValue(), 0)));
        p.setHeight(new BigDecimal(FormattingUtil.roundFigureRates(p.getHeight().doubleValue(), 0)));
        p.setWidth(new BigDecimal(FormattingUtil.roundFigureRates(p.getWidth().doubleValue(), 0)));
    	 
	    }
    }
    }
    return order;

  }

  private void fixBigDecimalPrecision(ShippingOrder order) {
    // TODO Auto-generated method stub
    if (order != null && order.getPackages() != null && order.getPackages().size() > 0) {
      for (Package p : order.getPackages()) {
        p.setLength(Common.fixPrecision(p.getLength()));
        p.setHeight(Common.fixPrecision(p.getHeight()));
        p.setWidth(Common.fixPrecision(p.getWidth()));
        // p.setCodAmount(Common.fixPrecision(p.getCodAmount()));
        // p.setInsuranceAmount(Common.fixPrecision(p.getInsuranceAmount()));
      }
    }
  }

  public String getTrackingURL(long orderId) {
    String url = null;
    try {
      ShippingOrder order = getShippingOrder(orderId);
      if (order.getCarrierId() == null || order.getCarrierId() == 0)
        return null;
      Carrier carrier = this.carrierServiceDAO.getCarrier(order.getCarrierId());
      if (carrier == null) {
        return null;
      }
      WebApplicationContext context = WebApplicationContextUtils
          .getWebApplicationContext((ServletContext) ActionContext.getContext().get(
              ServletActionContext.SERVLET_CONTEXT));
      CarrierService carrierService = (CarrierService) context.getBean(carrier
          .getImplementingClass());
      url = carrierService.getTrackingURL(order);
    } catch (Exception e) {
      log.error("Could not generate trackign URL for order with id" + orderId, e);
    }
    return url;

  }

  public List<Package> getShippingPackages(long orderId) {
    return getShippingDAO().getShippingPackages(orderId);
  }

  public void updateShippingOrder(ShippingOrder order) {
    getShippingDAO().updateShippingOrder(order);
  }

  public void deleteShippingOrder(long orderId) {
    getShippingDAO().deleteShippingOrder(orderId);
  }

  public Service getServiceById(Long serviceId) {
    return getShippingDAO().getServiceById(serviceId);
  }

  public List<Charge> getShippingOrderCharges(long orderId) {
    return getShippingDAO().getShippingOrderCharges(orderId);
  }

  public void updateStatus(Long userId, Long orderId, long statusId, String comment)
      throws Exception {
    SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");
    ShippingOrder order = getShippingDAO().getShippingOrder(orderId);
    order.setStatusId(statusId);
    getShippingDAO().save(order);

    /*
     * LoggedEvent event = new LoggedEvent(); OrderStatus status =
     * getShippingDAO().getStatus(statusId); event.setDateCreated(new Date());
     * event.setUserId(userId.longValue()); event.setMessageType("status");
     * event.setEntityId(String.valueOf(orderId.longValue())); event.setEntityType("order");
     * event.setMessage(status.getName() + ":" + formatter.format(event.getDateCreated()));
     * event.setComment(comment); event.setFranchiseId(order.getFranchiseId());
     * eventDAO.saveEvent(event);
     */
    /*
     * if (order.getShipFromAddress().isConfirmDelivery() && statusId ==
     * ShiplinxConstants.STATUS_DELIVERED) { // send a delivery confirmation to the sender
     * //emailManager.notifySenderOnDelivery(order); }
     */

  }

  public List<ShippingOrder> search(long carrierId) {
    return getShippingDAO().search(carrierId);
  }

  public List<ShippingOrder> searchShipmentsByStatusIdsAndCarrier(List<Long> statusIds,
      long carrierId) {
    return getShippingDAO().searchShipmentsByStatusIdsAndCarrier(statusIds, carrierId);
  }

  public List<CarrierChargeCode> getChargeListByCarrierAndCodes(long carrierId, String chargeCode,
      String chargeCodeLevel2) {
    return this.shippingDAO.getChargeListByCarrierAndCodes(carrierId, chargeCode, chargeCodeLevel2);
  }

  public CarrierChargeCode getChargeByCarrierAndCodes(long carrierId, String chargeCode,
      String chargeCodeLevel2) {
    return this.shippingDAO.getChargeByCarrierAndCodes(carrierId, chargeCode, chargeCodeLevel2);
  }

  public ChargeGroup getChargeGroup(int groupId) {
    return shippingDAO.getChargeGroup(groupId);
  }

  public List<Long> getAllCustomersWithPendingCharges(long businessId) {
    return this.shippingDAO.getAllCustomersWithPendingCharges(businessId);
  }

  @Override
  public List<ShippingOrder> getShipments(ShippingOrder so) {
    // TODO Auto-generated method stub
    return this.shippingDAO.getShipments(so);
  }

  public List<ShippingOrder> getUnbilledShipments(long businessId, long customerId, String branch) {
    return shippingDAO.getUnbilledShipments(businessId, customerId, branch);
  }

  @Override
  public boolean releaseCharges(String ediInvoiceNumber) {
    // TODO Auto-generated method stub
    if (ediInvoiceNumber != null) {
      this.shippingDAO.releaseCharges(ediInvoiceNumber);
    }

    return true;
  }

  @Override
  public void assignCustomerToShipments(List<Long> shipmentIds, Long customerId) {
    // TODO Auto-generated method stub
    if (shipmentIds != null && shipmentIds.size() > 0 && customerId != null) {
      for (Long id : shipmentIds) {
        ShippingOrder shipment = shippingDAO.getShippingOrder(id);
        if(shipment.getFromAddress().getAddressId() > 0 ){
    	    addressDAO.updateCustomerIdInAddress(customerId, shipment.getFromAddress().getAddressId());
        }
        if(shipment.getFromAddress().getAddressId() > 0 ){
    	    addressDAO.updateCustomerIdInAddress(customerId, shipment.getToAddress().getAddressId());
        }
        assignCustomerToShipment(shipment, customerId);
      }
    }
  }

  public void assignCustomerToShipment(ShippingOrder shipment, Long customerId) {
    shipment.setCustomerId(customerId);
    shipment.setBusinessId(UserUtil.getMmrUser().getBusinessId());

    log.info("Assigning customer to order: " + customerId + "/" + shipment.getId());
    // have to reset the markup information - TEESTTTT THIS!!!
    shipment.setMarkPercent(null);
    shipment.setMarkType(null);

    applyAdditionalHandling(shipment, null, ShiplinxConstants.CHARGE_TYPE_ACTUAL);

    for (Charge charge : shipment.getCharges()) {
      if (charge.getType() == ShiplinxConstants.CHARGE_TYPE_ACTUAL
          && (charge.getStatus() == ShiplinxConstants.CHARGE_PENDING_RELEASE || charge.getStatus() == ShiplinxConstants.CHARGE_READY_TO_INVOICE)) {
        log.info("Looking up charge for carrier/code/code2: " + shipment.getCarrierId() + " / "
            + charge.getChargeCode() + " / " + charge.getChargeCodeLevel2());
        CarrierChargeCode chargeGroupCode = getChargeByCarrierAndCodes(shipment.getCarrierId(),
            charge.getChargeCode(), charge.getChargeCodeLevel2());

        if (chargeGroupCode != null
            && (chargeGroupCode.getGroupCode()
                .equalsIgnoreCase(ShiplinxConstants.GROUP_FUEL_CHARGE) || chargeGroupCode
                .getGroupCode().equalsIgnoreCase(ShiplinxConstants.GROUP_FREIGHT_CHARGE))) {
          charge.setCharge(markupManagerService.applyMarkup(shipment, charge, true));
        }
        if(charge.getEdiInvoiceNumber() == null){
        charge.setStatus(ShiplinxConstants.BILLING_STATUS_CUSTOMER_CONFIRMATION);
        }
        updateCharge(charge);
        // else {
        // if(charge.getTariffRate()!=null)
        // charge.setCharge(charge.getTariffRate());
        // else
        // charge.setCharge(charge.getCost());
        // }
      }
    }
    shipment.setBillingStatus(ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE);
    this.shippingDAO.updateShippingOrderCustomerBilling(shipment);
  }

  private ShippingOrder getShipment(Long id) {
    // TODO Auto-generated method stub
    ShippingOrder so = new ShippingOrder();
    so.setId(id);
    List<ShippingOrder> soList = this.shippingDAO.getShipments(so);
    if (soList != null && soList.size() > 0)
      return soList.get(0);
    return null;
  }

  // private void applyMarkup(ShippingOrder shipment, Charge charge) {
  // // TODO Auto-generated method stub
  // double amount = new Double(0.0);
  // if (shipment.getMarkPercent() != null) {
  // if (charge.getCost() != null) {
  // if (shipment.getMarkType() != null) {
  // if (shipment.getMarkType().intValue() == ShiplinxConstants.TYPE_MARKDOWN)
  // {
  // float f = (charge.getTariffRate().floatValue() +
  // charge.getDiscountAmount().floatValue()) *
  // shipment.getMarkPercent().intValue() / 100;
  // amount = charge.getCost() - f;
  // } else {
  // float f = charge.getCost().floatValue() *
  // shipment.getMarkPercent().intValue() / 100;
  // amount = charge.getCost() + f;
  // }
  // }
  // }
  // }
  // charge.setCharge( amount );
  // }

  // private Markup getMarkupObj(ShippingOrder shipment) {
  // // TODO Auto-generated method stub
  // Markup m = new Markup();
  // m.setBusinessId(UserUtil.getMmrUser().getBusinessId());
  // m.setCustomerId(shipment.getCustomerId());
  // if (shipment.getFromAddress() != null)
  // m.setFromCountryCode(shipment.getFromAddress().getCountryCode());
  // if (shipment.getToAddress() != null)
  // m.setToCountryCode(shipment.getToAddress().getCountryCode());
  //
  // Double weight = shipment.getTotalWeight();
  // if (weight != null && weight.doubleValue() > 0) {
  // m.setFromWeight(weight);
  // m.setToWeight(weight);
  // }
  // m.setServiceId(shipment.getService().getId());
  //
  // return m;
  // }

  @Override
  public void setShipmentsReadyForInvoice(List<Long> shipmentIds) {
    // TODO Auto-generated method stub
    if (shipmentIds != null && shipmentIds.size() > 0) {
      for (Long id : shipmentIds) {
        ShippingOrder shipment = getShipment(id);
        shipment.setBillingStatus(ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE);
        this.shippingDAO.updateShippingOrderBillingInfo(shipment);
      }
    }

  }

  @Override
  public void updateCharge(Charge charge) {
    // TODO Auto-generated method stub
    try {
      getShippingDAO().updateCharge(charge);
    } catch (Exception e) {
      log.debug("---exception---" + e);
    }
  }

  public boolean isPaymentRequired(ShippingOrder order) {
    // TODO Auto-generated method stub

    Customer c = order.getCustomer();

    if (c != null && c.getPaymentType() == ShiplinxConstants.PAYMENT_TYPE_CREDIT_CARD
        && c.getPaymentTypeLevel() == ShiplinxConstants.PAYMENT_TYPE_LEVEL_SHIPMENT
        && order.getTotalChargeQuoted() > 0 && order.getPaidAmount() == 0)
      return true;

    return false;

  }

  public CCTransaction processPayment(ShippingOrder order, CCTransaction transaction) {
    if (transaction == null)
      transaction = new CCTransaction();
    transaction.setEntityId(order.getId());
    transaction.setEntityType(CCTransaction.ENTITY_TYPE_ORDER);
    transaction.setBusinessId(order.getBusinessId());

    try {
      transaction = creditCardService.chargeCard(transaction, order.getTotalChargeQuoted(),
          order.getCreditCard(), order.getCurrency(), order.getCustomer());
    } catch (Exception e) {
      log.error("Unable to charge card!", e);
      return null;
    }

    if (transaction.getStatus() == CCTransaction.PROCESSED_STATUS) {
      order.setPaidAmount(order.getTotalChargeQuoted());
      try {
        shippingDAO.updateShippingOrder(order);
        order.setPaymentRequired(isPaymentRequired(order));
      } catch (Exception e) {
        log.error("There was an error saving order information after charge", e);
        // in this case, the credit card charge must be refunded
        try {
          creditCardService.voidCharge(transaction, order.getCustomer());
        } catch (Exception ev) {
          log.error("Error voiding charge! Needs to be investigated", ev);
          // send email notification??
        }

      }
    }
    return transaction;
  }

  @Override
  public void deleteCharge(Long id) {
    // TODO Auto-generated method stub
    if (id != null && id.longValue() > 0) {
      this.shippingDAO.deleteCharge(id);
    }
  }

  public void applyAdditionalHandling(ShippingOrder order, Rating rate, int chargeType) {

    try {
      // check if addtl handling was already applied
    	if(order.getCharges()!=null){
    		                 for (Charge charge : order.getCharges()) {
    		                   try { // putting the below code in block, in case charge does
    		                     // not have values such as code, code level2, name etc
    		                     if (charge.getChargeCode().equalsIgnoreCase(ShiplinxConstants.CHARGE_CODE_UPS_ACC)
    		                         && charge.getChargeCodeLevel2().equalsIgnoreCase(
    		                             ShiplinxConstants.CHARGE_CODE_LEVEL_2_UPS_OTH)
    		                         && charge.getName().equalsIgnoreCase(
    		                             ShiplinxConstants.CHARGE_NAME_ADDITIONAL_HANDLING)
    		                         && charge.getType() == chargeType) {
    		                       log.info("Additional Handling was already added to this order!");
    		                       return;
    		                     }
    		                   } catch (Exception e) {
    		                   }
    		           
    		                 }
    		           }

      if (order.getCustomerId() == null || order.getCustomerId() == 0) {
        return;
      }

      Customer customer = customerDAO.getCustomerInfoByCustomerId(order.getCustomerId(),
          order.getBusinessId());
      if (customer == null || customer.getAdditionalHandlingCharge() == 0)
        return;

      Charge c = new Charge();
      
      if(order!=null && order.getCarrierId()!=null){
    	        c.setCarrierId(order.getCarrierId());
    	        }
    	        if(order!=null && order.getCarrierName()!=null){
    	    	  c.setCarrierName(order.getCarrierName());
    	        }

     /* c.setChargeCode(ShiplinxConstants.CHARGE_CODE_UPS_ACC);
      c.setChargeCodeLevel2(ShiplinxConstants.CHARGE_CODE_LEVEL_2_UPS_OTH);
      c.setName(ShiplinxConstants.CHARGE_NAME_ADDITIONAL_HANDLING);
      c.setCharge(customer.getAdditionalHandlingCharge());
      c.setCost(0.0);
      c.setTariffRate(c.getCharge());
      c.setCurrency(order.getCurrency());*/
    	        
    	        String fromCurrency = order.getCurrency();
    	            	        String toCurrency   = customer.getDefaultCurrency();
    	            	        if(toCurrency==null || toCurrency.isEmpty()){
    	            	      	  toCurrency=fromCurrency;
    	            	        }
    	            	        Double exchRate = 1.0;
    	            	        if(fromCurrency!=null && toCurrency!=null){
    	            	      	  if(fromCurrency.equalsIgnoreCase(toCurrency)){
    	            	          	  exchRate = 1.0;
    	            	            }
    	            	            if(!fromCurrency.equalsIgnoreCase(toCurrency)){
    	            	      		  exchRate=getShippingService().getExchangeRate(fromCurrency,toCurrency);
    	            	            }
    	            	        }
    	            	        c.setChargeCode(ShiplinxConstants.CHARGE_CODE_UPS_ACC);
    	            	        c.setChargeCodeLevel2(ShiplinxConstants.CHARGE_CODE_LEVEL_2_UPS_OTH);
    	            	        c.setName(ShiplinxConstants.CHARGE_NAME_ADDITIONAL_HANDLING);
    	            	        c.setCharge(customer.getAdditionalHandlingCharge()*exchRate);
    	            	        c.setCost(0.0);
    	            	        c.setTariffRate(c.getCharge()*exchRate);
    	            	        BigDecimal exch = new BigDecimal(exchRate);
    	            	        c.setExchangerate(exch);
    	            	        //c.setCurrency(order.getCurrency());
    	            	        int as = 1;
    	            	        if(fromCurrency != null && !(fromCurrency.isEmpty())){
    	        	    	        as=shippingDAO.getCurrencyIdByCurrencyCode(order.getCurrency());
    	        	    	        c.setCostcurrency(as);
    	            	        }else if(order.getCustomer().getDefaultCurrency() != null 
    	            	        		&& !(order.getCustomer().getDefaultCurrency().isEmpty())){
    	            	        	as=shippingDAO.getCurrencyIdByCurrencyCode(order.getCustomer().getDefaultCurrency());
    	            	        	c.setCostcurrency(as);
    	            	        } else{
    	            	        	as=shippingDAO.getCurrencyIdByCurrencyCode("CAD");
    	            	        	c.setCostcurrency(as);
    	            	        }
    	            	        
    	            	        if(order.getCustomer().getDefaultCurrency() != null 
    	            	        		&& !(order.getCustomer().getDefaultCurrency().isEmpty())){
    	            	        	as=shippingDAO.getCurrencyIdByCurrencyCode(order.getCustomer().getDefaultCurrency());
    	            	        	c.setChargecurrency(as);
    	            	        } else if(fromCurrency != null && !(fromCurrency.isEmpty())){
    	            	        	as=shippingDAO.getCurrencyIdByCurrencyCode(fromCurrency);
    	            	        	c.setChargecurrency(as);
    	            	        } else{
    	            	        	c.setChargecurrency(as);
    	            	        }
      if (chargeType == ShiplinxConstants.CHARGE_TYPE_ACTUAL) { // coming
        // from
        // EDI
        // processing
        c.setType(ShiplinxConstants.CHARGE_TYPE_ACTUAL);
        c.setStatus(ShiplinxConstants.CHARGE_READY_TO_INVOICE);
        if (order.getId() != null && order.getId() > 0) {
          c.setOrderId(order.getId());
          saveCharge(c);
        } else {
          order.getCharges().add(c); // this is a new order and the
          // charges will be saved as part
          // of save method
        }
      } else { // coming from rating
        c.setType(ShiplinxConstants.CHARGE_TYPE_QUOTED);
        c.setStatus(ShiplinxConstants.CHARGE_QUOTED);
        rate.getCharges().add(c);
      }
      log.debug("Additional Handling added to order with id: " + order.getId());

    } catch (Exception e) {
      log.error("Unable to add additional handling charge", e);
    }

  }

  public boolean sendCustomerEmailNotification(User user, ShippingOrder so, int isel) {
    boolean retval = false;
    String toAddress = user.getEmail();

    long serviceId = so.getRateList().get(isel).getServiceId();
    Service service = carrierServiceDAO.getService(serviceId);
    if (toAddress == null || toAddress.length() == 0) {
      log.error("User's email address is not sent, cannot send an email quote!");
      return false;
    }
    try {
      // GROUP_EMAIL_ADDRESS_en_CA
      String locale = user.getLocale();
      Customer customer = customerDAO.getCustomerInfoByCustomerId(so.getCustomerId(),
          so.getBusinessId());
      String subject = MessageUtil.getMessage("message.send.rates.notification.subject", locale);
      subject = new String(subject.replaceAll("%CUSTOMERNAME", customer.getName()));

      /*   String body = MessageUtil.getMessage(user.getBusiness().getRatesNotificationBody(), locale);
         */
            String body=BusinessFilterUtil.getEmailBody(so.getBusinessId(), ShiplinxConstants.MSGID_EMAIL_SHIP_RATE);
            
      
            if (body == null || body.length() == 0) {
          	    body = MessageUtil.getMessage(user.getBusiness().getRatesNotificationBody(), locale);
            }

      if (body == null || body.length() == 0) {
        log.error("Cannot find template to send rates notification for business "
            + user.getBusiness().getName());
        return false;
      }

      body = new String(body.replaceAll("%BUSINESSNAME", user.getBusiness().getName()));
      body = new String(body.replaceAll("%SFROMZIP", so.getFromAddress().getPostalCode()));
      body = new String(body.replaceAll("%SFROMPROVINCE", so.getFromAddress().getProvinceCode()));
      body = new String(body.replaceAll("%SFROMCOUNTRY", so.getFromAddress().getCountryCode()));
      body = new String(body.replaceAll("%SFROMCITY", so.getFromAddress().getCity()));
      body = new String(body.replaceAll("%STOCITY", so.getToAddress().getCity()));
      body = new String(body.replaceAll("%STOZIP", so.getToAddress().getPostalCode()));
      body = new String(body.replaceAll("%STOPROVINCE", so.getToAddress().getProvinceCode()));
      body = new String(body.replaceAll("%STOCOUNTRY", so.getToAddress().getCountryCode()));
      body = new String(body.replaceAll("%CARRIERSERVICE", so.getRateList().get(isel)
          .getCarrierName()
          + " " + so.getRateList().get(isel).getServiceName()));
      body = new String(body.replaceAll("%TRANSITDAYS", so.getRateList().get(isel).getTransitDays()
          + " day(s)"));
      body = new String(body.replaceAll("%TOTALWEIGHT", so.getRateList().get(isel).getBillWeight()
          + " " + so.getRateList().get(isel).getBillWeightUOM()));
      BigDecimal totalCharge = new BigDecimal(so.getRateList().get(isel).getTotal());
      body = new String(body.replaceAll("%TOTALCHARGES",
          "&#36;" + totalCharge.setScale(2, BigDecimal.ROUND_HALF_UP))); // &#36; is the
      // special
      // character
      // used for
      // Dollar Symbol
      // in html.
      String body_charges = new String("<ul>");
      for (int k = 0; k < so.getRateList().get(isel).getCharges().size(); k++) {
        BigDecimal charge = new BigDecimal(so.getRateList().get(isel).getCharges().get(k)
            .getCharge());
        body_charges = body_charges + "<li>"
            + so.getRateList().get(isel).getCharges().get(k).getName() + ": &#36;"
            + charge.setScale(2, BigDecimal.ROUND_HALF_UP) + "</li>";
      }
      body_charges = body_charges + "</ul>";
      body = new String(body.replaceAll("%CHARGEDETAILS", body_charges));

      // Package details
      /*
       * String package_details = new String("<ul>"); for (Package p : so.getPackages()) { String
       * type = p.getType(); String packageType = type.replaceAll("[^\\p{L}\\p{Nd}]", "");
       * packageType = packageType.replaceAll("\\d", ""); package_details = package_details + "<li>"
       * + p.getLength() + " x " + p.getWidth() + " x " + p.getHeight() + " - " + p.getWeight() +
       * " " + so.getBilledWeightUOM() + " - Class " + p.getFreightClass() + " - " + packageType +
       * " - " + p.getDescription() + "</li>";
       */
      if (service != null && service.getEmailType().equalsIgnoreCase("SPD")) {
        String package_details = new String("<ul>");
        for (Package p : so.getPackages()) {
        	package_details = package_details + "<li>" + p.getLength().setScale(2, BigDecimal.ROUND_HALF_UP) + " x " + p.getWidth().setScale(2, BigDecimal.ROUND_HALF_UP) + " x "
        			           + p.getHeight().setScale(2, BigDecimal.ROUND_HALF_UP) + " - " + p.getWeight().setScale(2, BigDecimal.ROUND_HALF_UP) + " " + so.getBilledWeightUOM() + " - "
              + p.getDescription() + "</li>";
        }
        package_details = package_details + "</ul>";
        String emptyString = "";
        body = new String(body.replaceAll("%PACKAGEDETAILS", package_details));
        body = new String(body.replaceAll("%ADDITIONALSERVICESTITLE", emptyString));
        body = new String(body.replaceAll("%ADDITIONALSERVICES", emptyString));
        body = new String(body.replaceAll("%ENDLINE", emptyString));
        String value = "1";
        body = new String(body.replaceAll("%VALUE", value));
        body = new String(body.replaceAll("%MAILADDRESS", user.getBusiness().getAddress()
            .getEmailAddress()));
      } else {
        String packageType = "", type;
        // Package details
        String package_details = new String("<ul>");
        for (Package p : so.getPackages()) {
          type = p.getType();
          if (type != null && !type.isEmpty()) {
            packageType = type.replaceAll("[^\\p{L}\\p{Nd}]", "");
            packageType = packageType.replaceAll("\\d", "");
          }
          if (p.getFreightClass() == null) {
            p.setFreightClass("");
          }
         package_details = package_details + "<li>" + p.getLength().setScale(2, BigDecimal.ROUND_HALF_UP) + " x " + p.getWidth().setScale(2, BigDecimal.ROUND_HALF_UP) + " x "
              + p.getHeight().setScale(2, BigDecimal.ROUND_HALF_UP) + " - " + p.getWeight().setScale(2, BigDecimal.ROUND_HALF_UP) + " " + so.getBilledWeightUOM() + " - Class "
              + p.getFreightClass() + " - " + packageType + " - " + p.getDescription() + "</li>";
        }
        package_details = package_details + "</ul>";
        body = new String(body.replaceAll("%PACKAGEDETAILS", package_details));
        String additional_services_title = "Additional Services:";
        body = new String(body.replaceAll("%ADDITIONALSERVICESTITLE", additional_services_title));
        String endLine = "-------------------------------------------------------";
        body = new String(body.replaceAll("%ENDLINE", endLine));
        String additional_services = new String("<ul>");
        if (so != null && so.getFromAddress().isResidential()) {
          additional_services = additional_services + "<li> From Residential </li>";
        }
        if (so != null && so.getToAddress().isResidential()) {
          additional_services = additional_services + "<li> To Residential </li>";
        }
        if (so != null && so.isTradeShowPickup()) {
          additional_services = additional_services + "<li> Tradeshow Pickup </li>";
        }
        if (so != null && so.isTradeShowDelivery()) {
          additional_services = additional_services + "<li> Tradeshow Delivery </li>";
        }
        if (so != null && so.isInsidePickup()) {
          additional_services = additional_services + "<li> Inside Pickup </li>";
        }
        if (so != null && so.isAppointmentPickup()) {
          additional_services = additional_services + "<li> Appointment Pickup </li>";
        }
        if (so != null && so.isAppointmentDelivery()) {
          additional_services = additional_services + "<li> Appointment Delivery </li>";
        }
        if (so != null && so.isFromTailgate()) {
          additional_services = additional_services + "<li> Tailgate Pickup </li>";
        }
        if (so != null && so.isToTailgate()) {
          additional_services = additional_services + "<li> Tailgate Delivery </li>";
        }
        if (so != null && so.getSatDelivery()) {
          additional_services = additional_services + "<li> Saturday Delivery </li>";
        }
        if (so != null && so.getHoldForPickupRequired()) {
          additional_services = additional_services + "<li> Hold for Pickup </li>";
        }
        if (so != null && so.getCODValue() > 0) {
          additional_services = additional_services + "<li> COD &#36;" + so.getCODValue() + "</li>";
        }
        if (so != null && so.getInsuranceValue() > 0) {
          additional_services = additional_services + "<li> Insurance &#36;"
              + so.getInsuranceValue() + "</li>";
        }
        if (additional_services != null && !additional_services.isEmpty()) {
          additional_services = additional_services + "</ul>";
          body = new String(body.replaceAll("%ADDITIONALSERVICES", additional_services));
        }
        String value = "2";
        body = new String(body.replaceAll("%VALUE", value));
        body = new String(body.replaceAll("%MAILADDRESS", user.getBusiness().getLtlEmail()));
      }
      /*
       * package_details = package_details + "</ul>"; body = new
       * String(body.replaceAll("%PACKAGEDETAILS", package_details));
       * 
       * String additional_services = new String("<ul>"); if (so != null &&
       * so.getFromAddress().isResidential()) { additional_services = additional_services +
       * "<li> From Residential </li>"; } if (so != null && so.getToAddress().isResidential()) {
       * additional_services = additional_services + "<li> To Residential </li>"; } if (so != null
       * && so.isTradeShowPickup()) { additional_services = additional_services +
       * "<li> Tradeshow Pickup </li>"; } if (so != null && so.isTradeShowDelivery()) {
       * additional_services = additional_services + "<li> Tradeshow Delivery </li>"; } if (so !=
       * null && so.isInsidePickup()) { additional_services = additional_services +
       * "<li> Inside Pickup </li>"; } if (so != null && so.isAppointmentPickup()) {
       * additional_services = additional_services + "<li> Appointment Pickup </li>"; } if (so !=
       * null && so.isAppointmentDelivery()) { additional_services = additional_services +
       * "<li> Appointment Delivery </li>"; } if (so != null && so.isFromTailgate()) {
       * additional_services = additional_services + "<li> Tailgate Pickup </li>"; } if (so != null
       * && so.isToTailgate()) { additional_services = additional_services +
       * "<li> Tailgate Delivery </li>"; } if (so != null && so.getSatDelivery()) {
       * additional_services = additional_services + "<li> Saturday Delivery </li>"; } if (so !=
       * null && so.getHoldForPickupRequired()) { additional_services = additional_services +
       * "<li> Hold for Pickup </li>"; } if (so != null && so.getCODValue() > 0) {
       * additional_services = additional_services + "<li> COD &#36;" + so.getCODValue() + "</li>";
       * } if (so != null && so.getInsuranceValue() > 0) { additional_services = additional_services
       * + "<li> Insurance &#36;" + so.getInsuranceValue() + "</li>"; } if (additional_services !=
       * null && !additional_services.isEmpty()) { additional_services = additional_services +
       * "</ul>"; body = new String(body.replaceAll("%ADDITIONALSERVICES", additional_services)); }
       */

      List<String> bccAddresses = new ArrayList<String>();
      // bccAddresses.add(user.getBusiness().getAddress().getEmailAddress());
      String emailAddress = user.getBusiness().getAddress().getEmailAddress();
      if (emailAddress != null) {
        String emails[] = emailAddress.split(";");
        for (String email : emails) {
          bccAddresses.add(email);
        }
      }
      log.debug("From ADDRESS: " + user.getBusiness().getAddress().getEmailAddress());
      String fromAddress = bccAddresses.get(0);
      log.debug("New From ADDRESS: " + fromAddress);

      retval = MailHelper.sendEmailNow2(user.getBusiness().getSmtpHost(), user.getBusiness()
          .getSmtpUsername(), user.getBusiness().getSmtpPassword(), user.getBusiness().getName(),
          user.getBusiness().getSmtpPort(), fromAddress, toAddress,

          bccAddresses, subject, body, null, true);
    } catch (MessagingException e) {
      log.error("Error sending email - Messaging Exception: ", e);
      retval = false;
    } catch (Exception e) {
      log.error("Error sending email - Exception: ", e);
      retval = false;
    }

    return retval;
  }

  public boolean sendShipmentNotificationMail(ShippingOrder so, Business business) {
    boolean retval = false;
    String toAddress = null;
    String strAttention = null;
    if (so.getToAddress() != null && so.getToAddress().isSendNotification()
        && so.getFromAddress() != null && !so.getFromAddress().isSendNotification()) {
      toAddress = so.getToAddress().getEmailAddress();
      strAttention = so.getToAddress().getContactName();
    } else if (so.getFromAddress() != null && so.getFromAddress().isSendNotification()
        && so.getToAddress() != null && !so.getToAddress().isSendNotification()) {
      toAddress = so.getFromAddress().getEmailAddress();
      strAttention = so.getFromAddress().getContactName();
    } else if (so.getToAddress() != null && so.getFromAddress() != null
        && so.getToAddress().isSendNotification() && so.getFromAddress().isSendNotification()) {
      toAddress = so.getFromAddress().getEmailAddress() + ";" + so.getToAddress().getEmailAddress();
      strAttention = "Customer";
    }else if(so.getCustomer().isChbCustomer() && so.getFromAddress().getCountryCode() != so.getToAddress().getCountryCode()){
    	    	toAddress = "customsdistribution@integratedcarriers.com";
    	    	strAttention = so.getFromAddress().getContactName();
    }
    if (toAddress == null || toAddress.length() == 0) {
      log.error("User's email address is not sent, cannot send an email quote!");
      return false;
    }
    try {
      // GROUP_EMAIL_ADDRESS_en_CA

    	String subject = null;
    	        if(so.getCustomer().isChbCustomer() && so.getFromAddress().getCountryCode() != so.getToAddress().getCountryCode()){
    	      	  subject = "New CHB Shipment";
    	        }else{
    	      	  subject = MessageUtil.getMessage("label.subject.shipment.notification");
    	        }

    	        /* String body = MessageUtil.getMessage("mail.shipment.notification.body");*/
    	              String body=BusinessFilterUtil.getEmailBody(so.getBusinessId(), ShiplinxConstants.MSGID_EMAIL_SHIP_NOTIFY);
    	             
    	             if (body == null || body.length() == 0) {
    	            	body = MessageUtil.getMessage("mail.shipment.notification.body");
    	              }

      if (body == null || body.length() == 0) {
        log.error("Cannot find template to send rates notification for business "
            + business.getName());
        return false;
      }

      body = new String(body.replaceAll("%ATTENTION", strAttention));
      body = new String(
          body.replaceAll(
              "%ShipDate",
              FormattingUtil.getFormattedDate(so.getScheduledShipDate(),
                  FormattingUtil.DATE_FORMAT_WEB) + ""));
      body = new String(body.replaceAll("%SFROMCOMPANY", so.getFromAddress().getAbbreviationName()));
      body = new String(body.replaceAll("%SFROMADDRESS1", so.getFromAddress().getAddress1()));
      body = new String(body.replaceAll("%SFROMADDRESS2%", so.getFromAddress().getAddress2()));
      body = new String(body.replaceAll("%SFROMZIP", so.getFromAddress().getPostalCode()));
      body = new String(body.replaceAll("%SFROMPROVINCE", so.getFromAddress().getProvinceCode()));
      body = new String(body.replaceAll("%SFROMCOUNTRY", so.getFromAddress().getCountryCode()));
      body = new String(body.replaceAll("%SFROMCITY", so.getFromAddress().getCity()));
      body = new String(body.replaceAll("%STOCOMPANY", so.getToAddress().getAbbreviationName()));
      body = new String(body.replaceAll("%STOADDRESS1", so.getToAddress().getAddress1()));
      body = new String(body.replaceAll("%STOADDRESS2%", so.getToAddress().getAddress2()));
      body = new String(body.replaceAll("%STOCITY", so.getToAddress().getCity()));
      body = new String(body.replaceAll("%STOZIP", so.getToAddress().getPostalCode()));
      body = new String(body.replaceAll("%STOPROVINCE", so.getToAddress().getProvinceCode()));
      body = new String(body.replaceAll("%STOCOUNTRY", so.getToAddress().getCountryCode()));
      body = new String(body.replaceAll("%CARRIERSERVICE", so.getCarrierName() + " "
          + so.getService().getName()));
      body = new String(body.replaceAll("%TOTALPIECES", so.getQuantity() + " Pcs"));
      body = new String(body.replaceAll("%TOTALWEIGHT", so.getRateList().get(0).getBillWeight()
          + " " + so.getBilledWeightUOM()));
      if (so.getTrackingURL() != null) {
        body = new String(body.replaceAll("%TRACKINGURL", so.getTrackingURL()));
      } else {
        body = new String(body.replaceAll("%TRACKINGURL", ""));
      }

      retval = MailHelper.sendEmailNow2(business.getSmtpHost(), business.getSmtpUsername(),
          business.getSmtpPassword(), business.getName(), business.getSmtpPort(), business
              .getAddress().getEmailAddress(), toAddress, null, subject, body, null, true);
    } catch (MessagingException e) {
      log.error("Error sending email - Messaging Exception: ", e);
      retval = false;
    } catch (Exception e) {
      log.error("Error sending email - Exception: ", e);
      retval = false;
    }

    return retval;
  }

  public boolean saveCustomsInvoice(CustomsInvoice ci) {
    boolean ret = true;

    // *FKhan* 20-Sep.2012
    // Bill To Cannot be null
    if (ci.getBillTo() == null)
      ci.setBillTo("");

    // Checking null for billToAddress and Brocker Address
    // For SouthMedic SQL Server Views - Shipment Creation these address are
    // null
    if (ci.getBillToAddress() != null)
      ci.setBillToAddressId(addressDAO.add(ci.getBillToAddress()));
    else
      ci.setBillToAddressId(null);

    if (ci.getBuyerAddress() != null)
      ci.setBuyerAddressId(addressDAO.add(ci.getBuyerAddress()));
    else
      ci.setBuyerAddressId(null);

    // if (ci.getBrokerAddress() != null &&
    // (ci.getBrokerAddress().getAbbreviationName()!=null &&
    // ci.getBrokerAddress().getAbbreviationName().length()>0) ||
    // (ci.getBrokerAddress().getContactName()!=null &&
    // ci.getBrokerAddress().getContactName().length()>0)){
    if (ci.getBrokerAddress() != null
        && !StringUtil.isEmpty(ci.getBrokerAddress().getAbbreviationName())
        && !StringUtil.isEmpty(ci.getBrokerAddress().getContactName())) {
      ci.setBrokerAddressId(addressDAO.add(ci.getBrokerAddress()));
    } else
      ci.setBrokerAddressId(null);
    try {

      ret = shippingDAO.addCustomsInvoiceInformation(ci);
      if (ret) // Insert Product information only if Customs Details are
      // inserted successfully
      {
        if (ci.getProducts() != null) {
          for (CustomsInvoiceProduct cip : ci.getProducts()) {
            cip.setCustomsInvoiceId(ci.getId());
            ret = shippingDAO.addCustomsInvoiceProdutInformation(cip);
          }
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
      ret = false;
    }

    return ret;
  }

  public CustomsInvoice getCustomsInvoiceByOrderId(long orderId) {
    return shippingDAO.getCustomsInvoiceByOrderId(orderId);
  }

  public boolean deleteCustomsInvoice(long ciId) {
    return shippingDAO.deleteCustomsInvoice(ciId);
  }

  @Override
  public List<ShippingOrder> uploadBatchShipmentFile(BatchShipmentInfo batchInfo, InputStream is) {
    // TODO Auto-generated method stub
    try {
      BatchShipmentHelper helper = new BatchShipmentHelper(this.getAllPackages(),
          this.getShippingDAO(), customerDAO);
      return helper.uploadParseFile(batchInfo, is);
    } catch (ShiplinxException e) {
      throw e;
    } catch (Exception ex) {
      ex.printStackTrace();
      log.error(ex);
      throw new ShiplinxException(ex.getMessage());
    }
  }

  @Override
  public List<ShippingOrder> createBatchShipments(List<ShippingOrder> shipments) {
    // TODO Auto-generated method stub
    try {
      if (shipments != null) {
        for (ShippingOrder so : shipments) {
          if (so != null && !StringUtil.isEmpty(so.getBatchId())
              && (so.getStatusId() == null || so.getStatusId().longValue() <= 0)) {
            so.setStatusId(new Long(ShiplinxConstants.STATUS_READYTOPROCESS));
            this.save(so);
            log.debug(so.getId());
          }
        }

        return shipments;
      }
    } catch (Exception e) {
      e.printStackTrace();
      log.error(e);
      String msg = BatchShipmentHelper.getExceptionMessage(e,
          BatchShipmentHelper.FAILED_TO_CREATE_BATCH_SHIPMENT_RECORD);
      throw new ShiplinxException(msg);
    }
    return null;
  }

  @Override
  public List<ShippingOrder> processBatchShipments(List<ShippingOrder> _shipments,
      BatchShipmentInfo _batchInfo) {
    // TODO Auto-generated method stub
    try {
      if (_shipments != null && _batchInfo != null && _batchInfo.getCarrierId() != null
          && _batchInfo.getServiceId() != null) {
        this.shipments = _shipments;
        this.batchInfo = _batchInfo;
        new Thread() {
          public void run() {
            beginProcessing(shipments, batchInfo);
          }
        }.start();

        return shipments;
      }
    } catch (Exception e) {
      e.printStackTrace();
      log.error(e);
      String msg = BatchShipmentHelper.getExceptionMessage(e,
          BatchShipmentHelper.FAILED_TO_PROCESS_BATCH_SHIPMENT_RECORD);
      throw new ShiplinxException(msg);
    }
    return null;
  }

  protected void beginProcessing(List<ShippingOrder> shipments, BatchShipmentInfo batchInfo) {
    // TODO Auto-generated method stub
    if (shipments != null && batchInfo != null) {
      for (ShippingOrder so : shipments) {
        if (so != null
            && !StringUtil.isEmpty(so.getBatchId())
            && so.getStatusId() != null
            && (so.getStatusId().longValue() == ShiplinxConstants.STATUS_READYTOPROCESS || so
                .getStatusId().longValue() == ShiplinxConstants.STATUS_EXCEPTION)) {
          so.setService(this.shippingDAO.getServiceById(batchInfo.getServiceId()));
          so.setServiceId(so.getService().getId());
          // process Shipment
          if (this.carrierServiceManager == null) {
            setCarrierServiceManager((CarrierServiceManager) MmrBeanLocator.getInstance().findBean(
                "carrierServiceManager"));
          }
          try {
            // Messages will be kept for the last shipment only,
            // initializing it
            so.setMessage(null);
            this.carrierServiceManager.shipOrder(so, null); // rating);
          } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            so.setStatusId(new Long(ShiplinxConstants.STATUS_READYTOPROCESS));
            so.addMessage(e);
          }
          // Get the error messages returned from the carriers, if
          // there are any
          List<CarrierErrorMessage> errMessages = this.carrierServiceManager.getErrorMessages();
          if (errMessages != null && errMessages.size() > 0) {
            // so.setStatusId(new
            // Long(ShiplinxConstants.STATUS_EXCEPTION));
            so.addMessage(errMessages);
          }
          this.updateShippingOrder(so);
        }
      }
    }
  }

  @Override
  public Service getServiceByCarrierAndTransitCode(Long carrierId, String transitCode) {
    // TODO Auto-generated method stub
    return this.shippingDAO.getServiceByCarrierAndTransitCode(carrierId, transitCode);
  }

  public void submitToWarehouse(ShippingOrder shippingOrder) {
    String systemLog = null;
    String locale = UserUtil.getMmrUser().getLocale();
    LoggedEvent loggedEvent = new LoggedEvent();
    try {
      if (shippingOrder.getScheduledShipDate_web() != null
          && shippingOrder.getScheduledShipDate_web().length() > 0) {
        Date date = FormattingUtil.getDate(shippingOrder.getScheduledShipDate_web(),
            FormattingUtil.DATE_FORMAT_WEB);
        shippingOrder.setScheduledShipDate(new Timestamp(date.getTime()));
      } else
        shippingOrder.setScheduledShipDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));

      // Email Notification
      if (!sendOrderEmailNotification(UserUtil.getMmrUser(), shippingOrder))
        throw new Exception("Sending Order Notification Email Exception");

      // save shipment and product details
      save(shippingOrder);

      // update product count
      if (productManagerService.updateProductsCounts(shippingOrder,
          ShiplinxConstants.STATUS_SENT_TOWAREHOUSE)) {
        // update a field "fulfilled" in shipping_order table to 1,
        // default is 0. This means the shipment order has been
        // fulfilled.
        shippingOrder.setFulfilled(1); // active
        // update the Shipping order only if everything is ok
        updateShippingOrder(shippingOrder);
      }

      // Logging event for creating order
      systemLog = MessageUtil.getMessage("label.created.warehouse.order", locale);
      systemLog = new String(systemLog.replaceAll("%ORDERNO", "'" + shippingOrder.getId() + "'"));
      systemLog = new String(systemLog.replaceAll("%DATETIME", "'" + new Date() + "'"));

      // Set the loggedEvent Details
      loggedEvent.setEntityType(ShiplinxConstants.ENTITY_TYPE_ORDER_VALUE); // Entity
      // -
      // Warehouse
      // Order
      loggedEvent.setEntityId(shippingOrder.getId());// Order ID
      loggedEvent.setEventDateTime(new Date()); // Current Date
      loggedEvent.setEventUsername(UserUtil.getMmrUser().getUsername()); // Current
      // User
      loggedEvent.setSystemLog(systemLog); // System generated Message Log
      // Log the Event into the DB
      loggedEventDAO.addLoggedEventInfo(loggedEvent); // Added Event Log
      // into DB

    } catch (Exception e) {
      log.error("Exception occured in submitting order to warehouse" + e);
      e.printStackTrace();
      throw new ShiplinxException(e.getMessage());
    }

  }

  public boolean sendOrderEmailNotification(User user, ShippingOrder so) {
    boolean retval = true;
    String toAddress = user.getBusiness().getWarehouseEmail();

    if (toAddress == null || toAddress.length() == 0) {
      log.error("User's email address is not set, cannot send an email quote!");
      return false;
    }
    try {
      // GROUP_EMAIL_ADDRESS_en_CA
      String locale = user.getLocale();

      String strProductName = MessageUtil.getMessage("label.product.name", locale);

      String strProductQty = MessageUtil.getMessage(
          "label.shippingOrder.additionalServices.quantity", locale);

      String subject = MessageUtil.getMessage("label.created.warehouse.order.subject", locale);
      subject = new String(subject.replaceAll("%CUSTOMERNAME", so.getCustomer().getName()));

      /*String body = MessageUtil.getMessage(user.getBusiness().getOrderNotificationBody(), locale);
            */
            String body=BusinessFilterUtil.getEmailBody(so.getBusinessId(),ShiplinxConstants.MSGID_EMAIL_WAREHOUSE_ORDER);
            
            if (body == null || body.length() == 0) {
          	   body = MessageUtil.getMessage(user.getBusiness().getOrderNotificationBody(), locale); 
              }

      if (body == null || body.length() == 0) {
        log.error("Cannot find template to send order creation notification for customer "
            + so.getCustomer().getName());
        return false;
      }

      body = new String(body.replaceAll("%CUSTOMERNAME", so.getCustomer().getName()));
      body = new String(body.replaceAll("%BUSINESSNAME", user.getBusiness().getName()));
      body = new String(body.replaceAll("%SFROMZIP", so.getFromAddress().getPostalCode()));
      body = new String(body.replaceAll("%SFROMPROVINCE", so.getFromAddress().getProvinceCode()));
      body = new String(body.replaceAll("%SFROMCOUNTRY", so.getFromAddress().getCountryCode()));
      body = new String(body.replaceAll("%SFROMCITY", so.getFromAddress().getCity()));
      body = new String(body.replaceAll("%STOCITY", so.getToAddress().getCity()));
      body = new String(body.replaceAll("%STOZIP", so.getToAddress().getPostalCode()));
      body = new String(body.replaceAll("%STOPROVINCE", so.getToAddress().getProvinceCode()));
      body = new String(body.replaceAll("%STOCOUNTRY", so.getToAddress().getCountryCode()));

      // Package details
      String product_details = new String("<table><tr><td>" + strProductName + "</td><td>"
          + strProductQty + "</td></tr>");
      for (Products p : so.getWarehouseProducts()) {
        product_details = product_details + "<tr><td>" + p.getProductName() + "</td><td>"
            + p.getOrderedQuantity() + "</td></tr>";
      }
      product_details = product_details + "</table>";
      body = new String(body.replaceAll("%PRODUCTCART", product_details));

      List<String> bccAddresses = new ArrayList<String>();
      // bccAddresses.add(user.getBusiness().getAddress().getEmailAddress());

      retval = MailHelper.sendEmailNow2(user.getBusiness().getSmtpHost(), user.getBusiness()
          .getSmtpUsername(), user.getBusiness().getSmtpPassword(), user.getBusiness().getName(),
          user.getBusiness().getSmtpPort(), user.getBusiness().getAddress().getEmailAddress(),
          toAddress, bccAddresses, subject, body, null, true);
    } catch (MessagingException e) {
      log.error("Error sending email - Messaging Exception: ", e);
      retval = false;
    } catch (Exception e) {
      log.error("Error sending email - Exception: ", e);
      retval = false;
    }
    return retval;
  }

  private void saveProductDetails(ShippingOrder shippingOrder) {
    try {
      if (shippingOrder.getWarehouseProducts() != null) {
        if (shippingOrder.getWarehouseProducts().size() > 0) {
          for (Products prod : shippingOrder.getWarehouseProducts()) {

            // Save the Product details.
            this.shippingDAO.saveProductDetails(prod, shippingOrder.getId());
          }
        }
      }
    } catch (Exception ex) {
      log.error("Exception occured while saving product details:", ex);
      throw new ShiplinxException(ex.getMessage());
    }
  }

  public void updateOrderStatus(long OrderId, long statusId) {
    try {
      LoggedEvent loggedEvent = new LoggedEvent();
      ShippingOrder so = this.shippingDAO.getShippingOrder(OrderId);
      String systemLog = null;
      String locale = UserUtil.getMmrUser().getLocale();
      // Check if status is already "Received By Warehouse", then dont
      // update.
      if (!so.getStatusId().equals(ShiplinxConstants.STATUS_RECEIVED_BYWAREHOUSE)) {
        // Logging event for creating order
        systemLog = MessageUtil.getMessage("label.updated.warehouse.order", locale);
        systemLog = new String(systemLog.replaceAll("%ORDER", "'" + OrderId + "'"));
        this.shippingDAO.updateOrderStatus(OrderId, statusId);

        // Set the loggedEvent Details
        loggedEvent.setEntityType(ShiplinxConstants.ENTITY_TYPE_ORDER_VALUE); // Entity
        // -
        // Warehouse
        // Order
        loggedEvent.setEntityId(OrderId);// Order ID
        loggedEvent.setEventDateTime(new Date()); // Current Date
        loggedEvent.setEventUsername(UserUtil.getMmrUser().getUsername()); // Current User
        loggedEvent.setSystemLog(systemLog); // System generated Message
        // Log
        // Log the Event into the DB
        loggedEventDAO.addLoggedEventInfo(loggedEvent); // Added Event
        // Log into DB
      }
    } catch (Exception e) {
      log.error("Exception occured while updating product status to 'Received by Warehouse':" + e);
      e.printStackTrace();
      throw new ShiplinxException(e.getMessage());
    }
  }

  public void updateOrderStatus(long OrderId, long statusId, String comment, boolean boolprivate) {
    try {
      ShippingOrder so = this.shippingDAO.getShippingOrder(OrderId);
      String systemLog = null;
      String strUserName = null;
      if (StringUtil.isEmpty(so.getCreatedBy())) {
        if (UserUtil.getMmrUser() != null)
          strUserName = UserUtil.getMmrUser().getUsername();
        else
          strUserName = ShiplinxConstants.SYSTEM;
      }
      // String locale = UserUtil.getMmrUser().getLocale();
      // Check if status is already the same status which it is updating
      // to, then dont update.
      if (!so.getStatusId().equals(statusId) || !StringUtil.isEmpty(comment)) {
        // Logging event for creating order
        systemLog = MessageUtil.getMessage("label.updated.shipment.order");
        systemLog = new String(systemLog.replaceAll("%ORDER", "'" + OrderId + "'"));
        this.shippingDAO.updateOrderStatus(OrderId, statusId);

        logUpdateAction(statusId,strUserName, OrderId, systemLog, boolprivate, comment);

      }
    } catch (Exception e) {
      log.error("Exception occured while updating product status to 'Received by Warehouse':" + e);
      e.printStackTrace();
      throw new ShiplinxException(e.getMessage());
    }

  }

  private void logUpdateAction(long statusId,String strUserName, long OrderId, String systemLog,
      boolean boolprivate, String Comment) {
    // Logged Event Initialization
    LoggedEvent loggedEvent = new LoggedEvent();
    // Set the loggedEvent Details
    loggedEvent.setEntityType(statusId); // Entity
    // -
    // Warehouse
    // Order
    loggedEvent.setEntityId(OrderId);// Order ID
    loggedEvent.setEventDateTime(new Date()); // Current Date
    loggedEvent.setEventUsername(strUserName); // Current User
    loggedEvent.setSystemLog(systemLog); // System generated Message Log
    loggedEvent.setPrivateMessage(boolprivate);
    loggedEvent.setMessage(Comment);
    // Log the Event into the DB
    loggedEventDAO.addLoggedEventInfo(loggedEvent); // Added Event Log into
    // DB

  }

  public ProductManager getProductManagerService() {
    return productManagerService;
  }

  public void setProductManagerService(ProductManager productManagerService) {
    this.productManagerService = productManagerService;
  }

  public List<OrderProduct> getOrderProducts(long orderId) {
    return this.shippingDAO.getOrderProducts(orderId);
  }

  public List<ShippingOrder> searchReferenceShipments(ShippingOrder order) {
    return this.shippingDAO.searchReferenceShipments(order);
  }

  public ShippingOrder repeatOrder(String orderId,String customs) {
    // ShippingOrder order = getShippingOrder(Long.valueOf(orderId));

    ShippingOrder order = getShippingNewOrder(Long.parseLong(orderId));

    if(customs!=null && customs.equalsIgnoreCase("true")){
    	    	order.setCustomsInvoiceRequired(true);
    	    }
    ShippingOrder newShippingOrder = new ShippingOrder();

    copyOrder(order, newShippingOrder);

    resetOrder(newShippingOrder);

    return newShippingOrder;
  }

  private void resetOrder(ShippingOrder order) {
    order.setId(0L);
    order.setCarrierId(0L);
    order.setServiceId(0L);
    // setting the status to 'Ready to Process' for repeated orders
    order.setStatusId(Long.parseLong(ShiplinxConstants.STATUS_READYTOPROCESS + ""));
  }

  private void copyOrder(ShippingOrder sourceOrder, ShippingOrder destOrder) {
    try {
      destOrder.setCustomerId(sourceOrder.getCustomerId());
      destOrder.setBusinessId(sourceOrder.getBusinessId());
      List<Package> packageList = sourceOrder.getPackages();

      if (packageList.size() != 0)
        destOrder.setPackages(packageList);

      // Setting the package Id of every Package to 0 to ensure new entry
      // of the instance into DB
      for (Package p : destOrder.getPackages()) {
        p.setPackageId(0L);
      }
      destOrder.setCODValue(0d);

      PackageType packageType = new PackageType();
      if (sourceOrder.getPackageTypeId() != null) {
        packageType = findPackageType(sourceOrder.getPackageTypeId().getType());
        destOrder.setPackageTypeId(packageType);
      }
      // set default quantity
      if (sourceOrder.getQuantity() == null)
        destOrder.setQuantity(1);
      else
        destOrder.setQuantity(sourceOrder.getQuantity());

      // reset the service if it has been set
      destOrder.setService(null);

      if (destOrder.getToAddress() == null)
        destOrder.setToAddress(new Address());
      if (destOrder.getFromAddress() == null)
        destOrder.setFromAddress(new Address());
      destOrder.getToAddress().copyAddress(sourceOrder.getToAddress());
      destOrder.getFromAddress().copyAddress(sourceOrder.getFromAddress());

      // set other values
      destOrder.setInsuranceValue(sourceOrder.getInsuranceValue());
      destOrder.setInsuredAmount(sourceOrder.getInsuredAmount());
      destOrder.setReferenceCode(sourceOrder.getReferenceCode());
      destOrder.setLinkToOrder(sourceOrder.getId());
      // For additional services
      destOrder.setSignatureRequired(sourceOrder.getSignatureRequired());

      destOrder.setReferenceCode(sourceOrder.getReferenceCode());
      destOrder.setSatDelivery(sourceOrder.getSatDelivery());
      destOrder.setHoldForPickupRequired(sourceOrder.getHoldForPickupRequired());
      destOrder.setInsideDelivery(sourceOrder.getInsideDelivery());
      // destOrder.setPickup(sourceOrder.getPickup());
      destOrder.setSpecialEquipment(sourceOrder.getSpecialEquipment());
      destOrder.setCODPayment(sourceOrder.getCODPayment());
      destOrder.setCODValue(sourceOrder.getCODValue());
      destOrder.setCODCurrency(sourceOrder.getCODCurrency());
      destOrder.setCODPayableTO(sourceOrder.getCODPayableTO());
      destOrder.setDangerousGoods(sourceOrder.getDangerousGoods());
      destOrder.setReqDeliveryDate(sourceOrder.getReqDeliveryDate());
      destOrder.setScheduledShipDate(sourceOrder.getScheduledShipDate());
      destOrder.setReferenceOne(sourceOrder.getReferenceOne());
      destOrder.setReferenceTwo(sourceOrder.getReferenceTwo());
      destOrder.setMasterTrackingNum(sourceOrder.getMasterTrackingNum());
      destOrder.setRes(sourceOrder.getRes());
      destOrder.setFromTailgate(sourceOrder.getFromTailgate());
      destOrder.setToTailgate(sourceOrder.getToTailgate());
      destOrder.setFromAttention(sourceOrder.getFromAttention());
      destOrder.setToAttention(sourceOrder.getToAttention());
      destOrder.setNotifyRecipient(sourceOrder.getNotifyRecipient());
      destOrder.setConfirmDelivery(sourceOrder.getConfirmDelivery());
      destOrder.setDocsOnly(sourceOrder.getDocsOnly());

      /*
       * //customs invoice if(sourceOrder.isCustomsInvoiceRequired()) { destOrder
       * .setCustomsInvoiceRequired(sourceOrder.isCustomsInvoiceRequired ());
       * destOrder.setCustomsInvoice(sourceOrder.getCustomsInvoice()); }
       */
      
      destOrder.setSaveFromAddress(sourceOrder.isSaveFromAddress());
            destOrder.setSaveToAddress(sourceOrder.isSaveToAddress());
            destOrder.setUnitmeasure(sourceOrder.getUnitmeasure());
            destOrder.setDutiableAmount(sourceOrder.getDutiableAmount());
            destOrder.setTradeShowDelivery(sourceOrder.isTradeShowDelivery());
            destOrder.setTradeShowPickup(sourceOrder.isTradeShowPickup());
            destOrder.setInsidePickup(sourceOrder.isInsidePickup());
            destOrder.setAppointmentDelivery(sourceOrder.isAppointmentDelivery());
            destOrder.setAppointmentPickup(sourceOrder.isAppointmentPickup());
            destOrder.setToTailgate(sourceOrder.isToTailgate());
            destOrder.setDutiableAmount(sourceOrder.getDutiableAmount());
            
            
             if(sourceOrder.isCustomsInvoiceRequired()) { 
          	   destOrder.setCustomsInvoiceRequired(sourceOrder.isCustomsInvoiceRequired ());
          	   destOrder.setCustomsInvoice(sourceOrder.getCustomsInvoice());
                	 for(int i=0;i<sourceOrder.getCustomsInvoice().getProducts().size();i++){
            		   destOrder.getCustomsInvoice().getProducts().get(i).setUnitPrice(new BigDecimal(sourceOrder.getCustomsInvoice().getProducts().get(i).getProductUnitPrice()));
            	   } 
          	   }
             

    } catch (Exception e) {
      log.error("Error occured in copying Source Object to dest object", e);
    }

  }

  public List<DangerousGoods> getDangerousGoodsAll() {
    return shippingDAO.getDangerousGoodsAll();
  }

  // one time run
  public void runBillingUpdate() {
    ShippingOrder so = new ShippingOrder();
    List<ShippingOrder> orders = getShipments(so);

    for (ShippingOrder order : orders) {
      shippingDAO.updateShippingOrderBillingStatus(order);
    }
  }

  public List<BillingStatus> getShippingBillingAllStatus() {
    return getShippingDAO().getShippingBillingAllStatus();
  }

  private void updateShipmentBillingStatus(long orderId) {
    ShippingOrder order = getShippingOrder(orderId);
    shippingDAO.updateShippingOrderBillingStatus(order);
  }

  @Override
  public boolean sendCustomerEmailNotificationRequest(User user, ShippingOrder so, int isel) {
    boolean retval = false;
    long serviceId = so.getRateList().get(isel).getServiceId();
    Service service = carrierServiceDAO.getService(serviceId);
    try {
      // GROUP_EMAIL_ADDRESS_en_CA
      String locale = user.getLocale();
      Customer customer = customerDAO.getCustomerInfoByCustomerId(so.getCustomerId(),
          so.getBusinessId());
      String subject = MessageUtil.getMessage("message.send.rates.notification.subject", locale);
      subject = new String(subject.replaceAll("%CUSTOMERNAME", customer.getName()));

      /*String body = MessageUtil.getMessage(user.getBusiness().getRatesNotificationBody(), locale);*/
      String body=BusinessFilterUtil.getEmailBody(so.getBusinessId(), ShiplinxConstants.MSGID_EMAIL_SHIP_RATE);
      
      
            if (body == null || body.length() == 0) {
          	  body  = MessageUtil.getMessage(user.getBusiness().getRatesNotificationBody(), locale);
            }
      if (body == null || body.length() == 0) {
        log.error("Cannot find template to send rates notification for business "
            + user.getBusiness().getName());
        return false;
      }

      body = new String(body.replaceAll("%BUSINESSNAME", user.getBusiness().getName()));
      body = new String(body.replaceAll("%SFROMZIP", so.getFromAddress().getPostalCode()));
      body = new String(body.replaceAll("%SFROMPROVINCE", so.getFromAddress().getProvinceCode()));
      body = new String(body.replaceAll("%SFROMCOUNTRY", so.getFromAddress().getCountryCode()));
      body = new String(body.replaceAll("%SFROMCITY", so.getFromAddress().getCity()));
      body = new String(body.replaceAll("%STOCITY", so.getToAddress().getCity()));
      body = new String(body.replaceAll("%STOZIP", so.getToAddress().getPostalCode()));
      body = new String(body.replaceAll("%STOPROVINCE", so.getToAddress().getProvinceCode()));
      body = new String(body.replaceAll("%STOCOUNTRY", so.getToAddress().getCountryCode()));
      body = new String(body.replaceAll("%CARRIERSERVICE", so.getRateList().get(isel)
          .getCarrierName()
          + " " + so.getRateList().get(isel).getServiceName()));
      body = new String(body.replaceAll("%TRANSITDAYS", so.getRateList().get(isel).getTransitDays()
          + " day(s)"));
      body = new String(body.replaceAll("%TOTALWEIGHT", so.getRateList().get(isel).getBillWeight()
          + " " + so.getRateList().get(isel).getBillWeightUOM()));
      BigDecimal totalCharge = new BigDecimal(so.getRateList().get(isel).getTotal());
      body = new String(body.replaceAll("%TOTALCHARGES",
          "&#36;" + totalCharge.setScale(2, BigDecimal.ROUND_HALF_UP))); // &#36; is the
      // special
      // character
      // used for
      // Dollar Symbol
      // in html.
      String body_charges = new String("<ul>");
      for (int k = 0; k < so.getRateList().get(isel).getCharges().size(); k++) {
        BigDecimal charge = new BigDecimal(so.getRateList().get(isel).getCharges().get(k)
            .getCharge());
        body_charges = body_charges + "<li>"
            + so.getRateList().get(isel).getCharges().get(k).getName() + ": &#36;"
            + charge.setScale(2, BigDecimal.ROUND_HALF_UP) + "</li>";
      }
      body_charges = body_charges + "</ul>";
      body = new String(body.replaceAll("%CHARGEDETAILS", body_charges));

      // Package details
      /*
       * String package_details = new String("<ul>"); for (Package p : so.getPackages()) { String
       * type = p.getType(); String packageType = type.replaceAll("[^\\p{L}\\p{Nd}]", "");
       * packageType = packageType.replaceAll("\\d", ""); package_details = package_details + "<li>"
       * + p.getLength() + " x " + p.getWidth() + " x " + p.getHeight() + " - " + p.getWeight() +
       * " " + so.getBilledWeightUOM() + " - Class " + p.getFreightClass() + " - " + packageType +
       * " - " + p.getDescription() + "</li>"; } package_details = package_details + "</ul>"; body =
       * new String(body.replaceAll("%PACKAGEDETAILS", package_details));
       */
      if (service != null && service.getEmailType().equalsIgnoreCase("SPD")) {
        String package_details = new String("<ul>");
        for (Package p : so.getPackages()) {
        	
        	package_details = package_details + "<li>" + p.getLength().setScale(2, BigDecimal.ROUND_HALF_UP) + " x " + p.getWidth().setScale(2, BigDecimal.ROUND_HALF_UP) + " x "
              + p.getHeight().setScale(2, BigDecimal.ROUND_HALF_UP) + " - " + p.getWeight().setScale(2, BigDecimal.ROUND_HALF_UP) + " " + so.getBilledWeightUOM() + " - "
              + p.getDescription() + "</li>";
        }
        package_details = package_details + "</ul>";
        String emptyString = "";
        body = new String(body.replaceAll("%PACKAGEDETAILS", package_details));
        body = new String(body.replaceAll("%ADDITIONALSERVICESTITLE", emptyString));
        body = new String(body.replaceAll("%ADDITIONALSERVICES", emptyString));
        body = new String(body.replaceAll("%ENDLINE", emptyString));
        String value = "1";
        body = new String(body.replaceAll("%VALUE", value));
        body = new String(body.replaceAll("%MAILADDRESS", user.getBusiness().getAddress()
            .getEmailAddress()));
        /*
         * String additional_services = new String("<ul>"); if (so != null &&
         * so.getFromAddress().isResidential()) { additional_services = additional_services +
         * "<li> From Residential </li>"; } if (so != null && so.getToAddress().isResidential()) {
         * additional_services = additional_services + "<li> To Residential </li>"; } if (so != null
         * && so.isTradeShowPickup()) { additional_services = additional_services +
         * "<li> Tradeshow Pickup </li>"; } if (so != null && so.isTradeShowDelivery()) {
         * additional_services = additional_services + "<li> Tradeshow Delivery </li>"; } if (so !=
         * null && so.isInsidePickup()) { additional_services = additional_services +
         * "<li> Inside Pickup </li>"; } if (so != null && so.isAppointmentPickup()) {
         * additional_services = additional_services + "<li> Appointment Pickup </li>"; } if (so !=
         * null && so.isAppointmentDelivery()) { additional_services = additional_services +
         * "<li> Appointment Delivery </li>"; } if (so != null && so.isFromTailgate()) {
         * additional_services = additional_services + "<li> Tailgate Pickup </li>"; } if (so !=
         * null && so.isToTailgate()) { additional_services = additional_services +
         * "<li> Tailgate Delivery </li>"; } if (so != null && so.getSatDelivery()) {
         * additional_services = additional_services + "<li> Saturday Delivery </li>"; } if (so !=
         * null && so.getHoldForPickupRequired()) { additional_services = additional_services +
         * "<li> Hold for Pickup </li>"; } if (so != null && so.getCODValue() > 0) {
         * additional_services = additional_services + "<li> COD &#36;" + so.getCODValue() +
         * "</li>"; } if (so != null && so.getInsuranceValue() > 0) { additional_services =
         * additional_services + "<li> Insurance &#36;" + so.getInsuranceValue() + "</li>"; } if
         * (additional_services != null && !additional_services.isEmpty()) { additional_services =
         * additional_services + "</ul>"; body = new String(body.replaceAll("%ADDITIONALSERVICES",
         * additional_services)); }
         */
      } else {
        String packageType = "", freightClass = "", type;
        // Package details
        String package_details = new String("<ul>");
        for (Package p : so.getPackages()) {
          type = p.getType();
          if (type != null && !type.isEmpty()) {
            packageType = type.replaceAll("[^\\p{L}\\p{Nd}]", "");
            packageType = packageType.replaceAll("\\d", "");
          }
          if (p.getFreightClass() == null) {
            p.setFreightClass("");
          }
          package_details = package_details + "<li>" + p.getLength().setScale(2, BigDecimal.ROUND_HALF_UP) + " x " + p.getWidth().setScale(2, BigDecimal.ROUND_HALF_UP) + " x "
        		            + p.getHeight().setScale(2, BigDecimal.ROUND_HALF_UP) + " - " + p.getWeight().setScale(2, BigDecimal.ROUND_HALF_UP) + " " + so.getBilledWeightUOM() + " - Class "
              + p.getFreightClass() + " - " + packageType + " - " + p.getDescription() + "</li>";
        }
        package_details = package_details + "</ul>";
        body = new String(body.replaceAll("%PACKAGEDETAILS", package_details));
        String additional_services_title = "Additional Services:";
        body = new String(body.replaceAll("%ADDITIONALSERVICESTITLE", additional_services_title));
        String endLine = "-------------------------------------------------------";
        body = new String(body.replaceAll("%ENDLINE", endLine));
        String additional_services = new String("<ul>");
        if (so != null && so.getFromAddress().isResidential()) {
          additional_services = additional_services + "<li> From Residential </li>";
        }
        if (so != null && so.getToAddress().isResidential()) {
          additional_services = additional_services + "<li> To Residential </li>";
        }
        if (so != null && so.isTradeShowPickup()) {
          additional_services = additional_services + "<li> Tradeshow Pickup </li>";
        }
        if (so != null && so.isTradeShowDelivery()) {
          additional_services = additional_services + "<li> Tradeshow Delivery </li>";
        }
        if (so != null && so.isInsidePickup()) {
          additional_services = additional_services + "<li> Inside Pickup </li>";
        }
        if (so != null && so.isAppointmentPickup()) {
          additional_services = additional_services + "<li> Appointment Pickup </li>";
        }
        if (so != null && so.isAppointmentDelivery()) {
          additional_services = additional_services + "<li> Appointment Delivery </li>";
        }
        if (so != null && so.isFromTailgate()) {
          additional_services = additional_services + "<li> Tailgate Pickup </li>";
        }
        if (so != null && so.isToTailgate()) {
          additional_services = additional_services + "<li> Tailgate Delivery </li>";
        }
        if (so != null && so.getSatDelivery()) {
          additional_services = additional_services + "<li> Saturday Delivery </li>";
        }
        if (so != null && so.getHoldForPickupRequired()) {
          additional_services = additional_services + "<li> Hold for Pickup </li>";
        }
        if (so != null && so.getCODValue() > 0) {
          additional_services = additional_services + "<li> COD &#36;" + so.getCODValue() + "</li>";
        }
        if (so != null && so.getInsuranceValue() > 0) {
          additional_services = additional_services + "<li> Insurance &#36;"
              + so.getInsuranceValue() + "</li>";
        }
        if (additional_services != null && !additional_services.isEmpty()) {
          additional_services = additional_services + "</ul>";
          body = new String(body.replaceAll("%ADDITIONALSERVICES", additional_services));
        }
        String value = "2";
        body = new String(body.replaceAll("%VALUE", value));
        body = new String(body.replaceAll("%MAILADDRESS", user.getBusiness().getLtlEmail()));
      }
      List<String> bccAddresses = new ArrayList<String>();
      // bccAddresses.add(user.getBusiness().getAddress().getEmailAddress());
      String emailAddress = user.getBusiness().getAddress().getEmailAddress();
      if (emailAddress != null) {
        String emails[] = emailAddress.split(";");
        for (String Email : emails) {
          bccAddresses.add(Email);
        }
      }
      log.debug("From ADDRESS: " + user.getBusiness().getAddress().getEmailAddress());
      String fromAddress = bccAddresses.get(0);
      log.debug("New From ADDRESS: " + fromAddress);
      retval = MailHelper.sendEmailNow2(user.getBusiness().getSmtpHost(), user.getBusiness()
          .getSmtpUsername(), user.getBusiness().getSmtpPassword(), user.getBusiness().getName(),
          user.getBusiness().getSmtpPort(), fromAddress, user.getBusiness().getLtlEmail(),
          bccAddresses, subject, body, null, true);
    } catch (MessagingException e) {
      log.error("Error sending email - Messaging Exception: ", e);
      retval = false;
    } catch (Exception e) {
      log.error("Error sending email - Exception: ", e);
      retval = false;
    }

    return retval;
  }

  // Issue No:162 Fixing Start

  public boolean sendCancelShipmentNotificationMail(ShippingOrder so, Business business) {
    boolean retval = false;
    String toAddress = business.getLtlEmail();
    if (toAddress == null || toAddress.length() == 0) {
      log.error("User's email address is not sent, cannot send an email quote!");
      return false;
    }
    ShippingOrder shippingOrder = new ShippingOrder();
        if(so != null && so.getScheduledShipDate() == null){
        	shippingOrder = shippingDAO.getShippingOrder(so.getId());    	
        	so = shippingOrder;
        }
    try {
      // GROUP_EMAIL_ADDRESS_en_CA

      String subject = MessageUtil.getMessage("label.subject.cancel.shipment.notification");

      /*String body = MessageUtil.getMessage("mail.cancel.shipment.notification.body");
*/
             String body=BusinessFilterUtil.getEmailBody(so.getBusinessId(),ShiplinxConstants.MSGID_EMAIL_SHIP_CANCEL_NOTIFY);
             if (body == null || body.length() == 0) {
          	     body = MessageUtil.getMessage("mail.cancel.shipment.notification.body");
               } 
      if (body == null || body.length() == 0) {
        log.error("Cannot find template to send rates notification for business "
            + business.getName());
        return false;
      }
      body = new String(
          body.replaceAll(
              "%ShipDate",
              FormattingUtil.getFormattedDate(so.getScheduledShipDate(),
                  FormattingUtil.DATE_FORMAT_WEB) + ""));
      body = new String(body.replaceAll("%SFROMCOMPANY", so.getFromAddress().getAbbreviationName()));
      body = new String(body.replaceAll("%SFROMADDRESS1", so.getFromAddress().getAddress1()));
      body = new String(body.replaceAll("%SFROMADDRESS2%", so.getFromAddress().getAddress2()));
      body = new String(body.replaceAll("%SFROMZIP", so.getFromAddress().getPostalCode()));
      body = new String(body.replaceAll("%SFROMPROVINCE", so.getFromAddress().getProvinceCode()));
      body = new String(body.replaceAll("%SFROMCOUNTRY", so.getFromAddress().getCountryCode()));
      body = new String(body.replaceAll("%SFROMCITY", so.getFromAddress().getCity()));
      body = new String(body.replaceAll("%STOCOMPANY", so.getToAddress().getAbbreviationName()));
      body = new String(body.replaceAll("%STOADDRESS1", so.getToAddress().getAddress1()));
      body = new String(body.replaceAll("%STOADDRESS2%", so.getToAddress().getAddress2()));
      body = new String(body.replaceAll("%STOCITY", so.getToAddress().getCity()));
      body = new String(body.replaceAll("%STOZIP", so.getToAddress().getPostalCode()));
      body = new String(body.replaceAll("%STOPROVINCE", so.getToAddress().getProvinceCode()));
      body = new String(body.replaceAll("%STOCOUNTRY", so.getToAddress().getCountryCode()));
      body = new String(body.replaceAll("%CARRIERSERVICE", so.getCarrierName() + " "
          + so.getService().getName()));
      body = new String(body.replaceAll("%TOTALPIECES", so.getQuantity() + " Pcs"));
      if(so.getRateList() != null){
      body = new String(body.replaceAll("%TOTALWEIGHT", so.getRateList().get(0).getBillWeight()
          + " " + so.getBilledWeightUOM()));
      }else if(so.getBilledWeight() != null){
    	      	  body = new String(body.replaceAll("%TOTALWEIGHT", so.getBilledWeight()
    	      	          + " " + so.getBilledWeightUOM()));
    	        }else{
    	      	  body = new String(body.replaceAll("%TOTALWEIGHT", so.getQuotedWeight()
    	      	          + " " + so.getBilledWeightUOM()));
    	        }
      if (so.getTrackingURL() != null) {
        body = new String(body.replaceAll("%TRACKINGURL", so.getTrackingURL()));
      } else {
        body = new String(body.replaceAll("%TRACKINGURL", ""));
      }
      if(so.getTrackingURL()==null && so.getCarrierId().intValue()==ShiplinxConstants.CARRIER_PUROLATOR_FREIGHT){
    	      	   if(so.getMasterTrackingNum()!=null){
    	      	  body = new String(body.replaceAll("%TRACKINGURL", so.getMasterTrackingNum()));
    	      	   } else {
    	      	   body = new String(body.replaceAll("%TRACKINGURL", ""));
    	      	   }
    	      	   }
    	      	   if (so.getPickup() != null && so.getPickup().getConfirmationNum() !=null && !so.getPickup().getConfirmationNum().isEmpty()) {
    	      	   body = new String(body.replaceAll("%PICKUPCONFIRMATION",so.getPickup().getConfirmationNum()));
    	      	   } else {
    	      	   body = new String(body.replaceAll("%PICKUPCONFIRMATION", ""));
    	      	   }
      String fromAddress = null;
      if (so.getFromAddress() != null && so.getFromAddress().getEmailAddress() != null) {
        fromAddress = so.getFromAddress().getEmailAddress();
      } else if (so.getToAddress() != null && so.getToAddress().getEmailAddress() != null) {
        fromAddress = so.getToAddress().getEmailAddress();
      } else {
        fromAddress = business.getAddress().getEmailAddress();
      }
     
      if(so.getCustomer().isChbCustomer() && so.getFromAddress().getCountryCode() != so.getToAddress().getCountryCode()){
          List<String> bccAddress = new ArrayList<String>();
          bccAddress.add("customsdistribution@integratedcarriers.com");
    	  retval = MailHelper.sendEmailNow2(business.getSmtpHost(), business.getSmtpUsername(),
    	            business.getSmtpPassword(), business.getName(), business.getSmtpPort(), fromAddress,
    	            business.getAddress().getEmailAddress(), bccAddress, subject, body, null, true);
      }else if (so.getService() != null && so.getService().getEmailType().equalsIgnoreCase("SPD")) {
    	  if(so.getCarrierId()!=null && so.getCarrierId().intValue()==ShiplinxConstants.CARRIER_PUROLATOR_FREIGHT){
    		      		   List<String> bccAddress = new ArrayList<String>();
    		      		   bccAddress.add(business.getCancelPurolatorFreightEmail());
    		      		   retval = MailHelper.sendEmailNow2(business.getSmtpHost(), business.getSmtpUsername(),
    		      		   business.getSmtpPassword(), business.getName(), business.getSmtpPort(), fromAddress,
    		      		   business.getAddress().getEmailAddress(), bccAddress, subject, body, null, true);
    		      		   }else{
    		      		   retval = MailHelper.sendEmailNow2(business.getSmtpHost(), business.getSmtpUsername(),
    		      		   business.getSmtpPassword(), business.getName(), business.getSmtpPort(), fromAddress,
    		      		   business.getAddress().getEmailAddress(), null, subject, body, null, true);
    		      		   }
      }else {
    	  if(so.getCarrierId()!=null && so.getCarrierId().intValue()==ShiplinxConstants.CARRIER_PUROLATOR_FREIGHT){
    		      		   List<String> bccAddress = new ArrayList<String>();
    		      		   bccAddress.add(business.getCancelPurolatorFreightEmail());
    		      		   retval = MailHelper.sendEmailNow2(business.getSmtpHost(), business.getSmtpUsername(),
    		      		   business.getSmtpPassword(), business.getName(), business.getSmtpPort(), fromAddress,
    		      		   business.getLtlEmail(), bccAddress, subject, body, null, true);
    		      		   }else{
    		      		   retval = MailHelper.sendEmailNow2(business.getSmtpHost(), business.getSmtpUsername(),
    		      		   business.getSmtpPassword(), business.getName(), business.getSmtpPort(), fromAddress,
    		     		   business.getLtlEmail(), null, subject, body, null, true);
    		      		   }
      }
    } catch (MessagingException e) {
      log.error("Error sending email - Messaging Exception: ", e);
      retval = false;
    } catch (Exception e) {
      log.error("Error sending email - Exception: ", e);
      retval = false;
    }

    return retval;
  }

  public List<CarrierChargeCode> getChargeCodeListByCarrierId(long carrierId) {
    return this.shippingDAO.getChargeCodeListByCarrierId(carrierId);
  }

  public List<CarrierChargeCode> getChargeNameListByCarrierIdAndChargeCode(long carrierId,
      String chargeCode) {
    return this.shippingDAO.getChargeNameListByCarrierIdAndChargeCode(carrierId, chargeCode);
  }

  // Issue No:162 Fixing End

  public void deleteLabel(long orderId) {
    getShippingDAO().deleteLabel(orderId);
  }

  public Charge getChargeById(Long id) {
    return shippingDAO.getChargeById(id);
  }

  public void deleteChargesByOrderId(Long id) {
    if (id != null && id.longValue() > 0) {
      this.shippingDAO.deleteChargesByOrderId(id);
    }
  }

  public DhlShipValidateResponse getDHLvalidateResponce(DhlShipValidateResponse so) {
    // TODO Auto-generated method stub
    return this.shippingDAO.getDHLvalidateResponce(so);
  }

  @Override
  public List<InvoiceCharge> getChargeandOrderByInvoiceId(int invoiceId) {

    return (List<InvoiceCharge>) shippingDAO.getChargeandOrderByInvoiceId(invoiceId);
  }

  @Override
  public Charge getChargesByOrderandInvoiceId(long chargeId, long orderId) {
    return (Charge) shippingDAO.getChargesByOrderandInvoiceId(chargeId, orderId);
  }
  //Written By Mohan R
  public CarrierChargeCode getChargeByChargeGroupId(long carrierId, int chargeGroupId) {
	    return this.shippingDAO.getChargeByChargeGroupId(carrierId, chargeGroupId);
	  }
  //End

@Override
public List<ShippingOrder> findShipmentsAdminById(ShippingOrder so) {
	
	return this.shippingDAO.findShipmentsAdminById(so);
}

public List<ExchangeRateCurrency> getAllExchangeRateCurrency(){
	return this.shippingDAO.getAllExchangeRateCurrency();
}

public void updateExchangeRateCurrency(ExchangeRateCurrency exchangeRate){
	this.shippingDAO.updateExchangeRateCurrency(exchangeRate);
}
public Double getExchangeRate(String currencyCode, String currencyCode2){
	return this.shippingDAO.getExchangeRate(currencyCode,currencyCode2);
}
public CurrencySymbol getCurrencyCodeByCountryName(String fromCountry){
	return this.shippingDAO.getCurrencyCodeByCountryName(fromCountry);
}
public List<CurrencySymbol> getallCurrencySymbol(){
	return this.shippingDAO.getallCurrencySymbol();
}
@Override
public CurrencySymbol getSymbolByCurrencycode(String currencyCode) {
	
	return this.shippingDAO.getSymbolByCurrencycode(currencyCode);
}

@Override
public String getCurrencyByCountry(String accCountry) {
	// TODO Auto-generated method stub
	return this.shippingDAO.getCurrencyByCountry(accCountry);
}

@Override
public int getCountryIdByCountryCode(String accCountry,String currencyCode) {
	// TODO Auto-generated method stub
	
	return this.shippingDAO.getCountryIdByCountryCode(accCountry,currencyCode);
}

@Override
public String getCurrencyByAccountNumber(String accountNumber) {
	// TODO Auto-generated method stub
	return carrierServiceDAO.getCurrencyByAccount(accountNumber);
}

@Override
public CurrencySymbol getCurrencyCodeById(int id) {
	return this.shippingDAO.getCurrencyCodeById(id);
}

@Override
public void updateShippingOrderCurrency(ShippingOrder ediShipment) {
	// TODO Auto-generated method stub
	if(ediShipment!=null && ediShipment.getPackages()!=null && ediShipment.getPackages().size() == 0){
		if(ediShipment.getDbShipment()!=null && !ediShipment.getDbShipment().equals("") && ediShipment.getDbShipment().getCharges().size()>0){
		shippingDAO.updateShippingOrderCurrency(ediShipment.getDbShipment().getCharges().get(0).getOrderId(), ediShipment.getCurrency());
		}
		else if(ediShipment.getCharges()!=null && ediShipment.getCharges().size()>0){
			shippingDAO.updateShippingOrderCurrency(ediShipment.getCharges().get(0).getOrderId(), ediShipment.getCurrency());
		}
	}
}

@Override
public Long insertFutureReference(FutureReference fc) {
	// TODO Auto-generated method stub
	return shippingDAO.insertFutureReference(fc);
	
}

@Override
public void insertFuturePackages(FutureReferencePackages futureRefPack) {
	// TODO Auto-generated method stub
	shippingDAO.insertFuturePackages(futureRefPack);
	
}
	public List<ShippingOrder> getOrdersByAddressId(long fromAddressId){
		return this.shippingDAO.getOrdersByAddressId(fromAddressId);
	}
	
	// For eshipplus
	public List<AccessorialServices> getAllAccessorialServices(long typeNot) {
		return this.shippingDAO.getAllAccessorialServices(typeNot);
	}

	public List<AddressCheckList> getAddressCheckListByAddressId(Long addressId) {
		return this.shippingDAO.getAddressCheckListByAddressId(addressId);
	}

	public void addNewAddressCheckList(AddressCheckList AddressCheckList) {
		this.shippingDAO.addNewAddressCheckList(AddressCheckList);
	}

	public void updateAddressCheckList(AddressCheckList AddressCheckList) {
		this.shippingDAO.updateAddressCheckList(AddressCheckList);
	}

	public AccessorialServices getAccessorialServiceByServiceGroupCode(
			String ServiceGroupCode, long typeNot) {
		return this.shippingDAO.getAccessorialServiceByServiceGroupCode(
				ServiceGroupCode, typeNot);
	}

	public AccessorialServices getAccessorialServiceByServiceCode(
			String ServiceGroupCode) {
		return this.shippingDAO
				.getAccessorialServiceByServiceCode(ServiceGroupCode);
	}

	public List<EshipplusCarrierFilter> getEshipPlusCarrierByCustomerId(
			Long customerId) {
		return this.shippingDAO.getEshipPlusCarrierByCustomerId(customerId);
	}

	public void updateEshipCarrierFilter(EshipplusCarrierFilter eshipCC,
			String sCustomerId) {
		this.shippingDAO.updateEshipCarrierFilter(eshipCC, sCustomerId);
	}

	public void updateUserFreightClassMode(int i, Long customerId) {
		this.shippingDAO.updateUserFreightClassMode(i, customerId);
	}

	public List<EshipplusPackage> getEshipplusPackagesList() {
		return this.shippingDAO.getEshipplusPackagesList();
	}

	public Long getLatestOrderId() {
		return this.shippingDAO.getLatestOrderId();
	}

	@Override
	public ChargeGroup getChargeDetailsByProvince(String province) {
		return this.shippingDAO.getChargeDetailsByProvince(province);
	}

	public int checkAccessorial(String chargeCode) {
		return this.shippingDAO.checkAccessorial(chargeCode);
	}

	public ShippingService getShippingService() {
		return shippingService;
	}

	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

	@Override
	public int saveDuplicateOrder(ShippingOrder dupOrder,ShippingOrder order) {
			long fromAddId = addressDAO.add(order.getFromAddress());
		    long toAddId = addressDAO.add(order.getToAddress());
		    if(fromAddId!=0 && toAddId!=0){
		    	dupOrder.setShipFromId(fromAddId);
		    	dupOrder.setShipToId(toAddId);
		    }
			int i=this.shippingDAO.saveDuplicateOrder(dupOrder);
			if(i==1){
				for(Charge charge:order.getCharges()){
					charge.setOrderId(charge.getOrderId()+1);
					if(charge.getCostWithNoBM()!=null)
						charge.setCost(charge.getCostWithNoBM());
					if(charge.getChargeWithBM()!=null)
						charge.setCharge(charge.getChargeWithBM());
					charge.setStatus(ShiplinxConstants.CHARGE_PENDING_RELEASE);
				}
				 i=this.shippingDAO.saveDuplicateCharges(order.getCharges());
			}
			return i;
	}
	

}