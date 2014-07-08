package com.meritconinc.shiplinx.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import com.meritconinc.mmr.utilities.StringUtil;
import com.meritconinc.shiplinx.utils.CarrierErrorMessage;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import java.util.Calendar;
import java.util.TimeZone;
import com.meritconinc.mmr.model.security.User;
import com.meritconinc.mmr.utilities.security.UserUtil;
public class ShippingOrder implements Serializable {

  public ShippingOrder() {
	  try {
      User user = UserUtil.getMmrUser();
      if (user != null && user.getTimeZone() != null && !user.getTimeZone().isEmpty()) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(user.getTimeZone()));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        dateFormat.setTimeZone(cal.getTimeZone());
        timeFormat.setTimeZone(cal.getTimeZone());
        scheduledShipDate_web = dateFormat.format(cal.getTime());
      } else {
        scheduledShipDate_web = FormattingUtil.getFormattedDate(new Date(),
            FormattingUtil.DATE_FORMAT_WEB);
      }
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  public static final int DIM_TYPE_US = 1;
  public static final int DIM_TYPE_METRIC = 2;

  public static final int SIGNATURE_REQUIRED_NO = 1;
  public static final int SIGNATURE_REQUIRED_INDIRECT = 2;
  public static final int SIGNATURE_REQUIRED_DIRECT = 3;
  public static final int SIGNATURE_REQUIRED_ADULT = 4;

  public static final int COD_RETURN_TYPE_SENDER = 1;
  public static final int COD_RETURN_TYPE_OTHER = 2;
  public static final String DATE_FORMAT = "MM/dd/yyyy";

  private Long customerId;
  private Long id;
  private String orderNum;
  private long shipToId;
  private long shipFromId;
  private PackageType packageTypeId;
  private Package pack;
  private Long carrierId;
  private Long serviceId;
  private Long slaveServiceId;
  private Long originalServiceId;
  private Long statusId;
  private String statusName;
  private String referenceCodeName;
  private String referenceCode;
  private Boolean satDelivery = false;
  private Boolean docsOnly;
  private Boolean holdForPickupRequired;
  private Boolean insideDelivery;
  // private String pickupTime;
  private String specialEquipment;
  private String CODPayment;
  private Double CODValue = 0d;
  private String CODCurrency;
  private String CODPayableTO;
  private String CODPin;
  private Integer dangerousGoods = 0;
  private String reqDeliveryDate;
  private Timestamp scheduledShipDate;
  private Double baseCharge;
  private Double totalCharge;
  private Double fuelCharges;
  private Timestamp dateCreated = new Timestamp(new Date().getTime());
  // private Long pickUpNum;
  private String referenceOneName;
  private String referenceOne;
  private String referenceTwoName;
  private String referenceTwo;
  private String carrierReference;
  private String masterTrackingNum;
  private String fromInstructions;
  private String toInstructions;
  private String fromAttention;
  private String toAttention;
  private Short res;
  private Boolean fromTailgate;
  private Boolean toTailgate;
  private Boolean notifyRecipient;
  private Boolean confirmDelivery;
  private Boolean recidential;
  private Integer quantity;
  private CustomerCarrier customerCarrier;
  private Short signatureRequired = ShiplinxConstants.SIGNATURE_REQUIRED_NO;
  private Package packageArray[] = new Package[35];
  private List<Package> packages = new ArrayList<Package>();
  private int dimType = 0;
  private float insuredAmount;
  private float insuranceValue;
  private Customer customer;
  private String rateIndex;
  private List<Rating> rateList;
  private String carrierName;
  private String serviceName;
  private String orderStatus;
  private String trackingURL;
  private Timestamp scheduledAt = new Timestamp(new Date().getTime());
  private String isOrderEdited;
  private String currency;
  private boolean ediVerified;
  private String manifestNumber = ""; // to be deleted
  private List<Charge> charges = new ArrayList<Charge>();
  private List<Charge> chargesForInvoice = new ArrayList<Charge>();

  private boolean live;
  private String proofOfDelivery;
  private Date dateOfDelivery; // to be deleted
  private String fromCountry;

  private String fromDate;
  private String toDate;
  // private Date fromDateTime;
  // private Date toDateTime;

  private long businessId;
  private Business business;

  // private String carrierPickUpConf;
  private String fromZone;
  private String toZone;
  private Date podDateTime;
  private String podReceiver;

  private Address fromAddress;
  private Address toAddress;

  private Service service;
  private Carrier carrier;

  private Integer markPercent;
  private Integer markType;
  private float fuelPercent;
  private Integer billingStatus;

  private Double paidAmount = 0.0;
  private Double appliedAmount = 0.0;
  private Double refundedAmount = 0.0;

  private Float billedWeight;
  private String billedWeightUOM;
  private Float quotedWeight;
  private String quotedWeightUOM;
  private Double ratedAsWeight;

  private BigDecimal dutiableAmount = new BigDecimal(0.00);

  private List<CCTransaction> ccTransactions = new ArrayList<CCTransaction>();;

  private List<ShippingLabel> labels = new ArrayList<ShippingLabel>();

  private Pickup pickup = new Pickup();

  private boolean customsInvoiceRequired;
  private CustomsInvoice customsInvoice = new CustomsInvoice();

  private List<Products> warehouseProducts;

  private Products warehouseProduct = new Products();

  private Products warehouseProductEach = new Products();

  private int fulfilled;

  private boolean tradeShowPickup;
  private boolean tradeShowDelivery;
  private boolean insidePickup;
  private boolean appointmentPickup;
  private boolean appointmentDelivery;

  // web only
  private String scheduledShipDate_web;
  private String ediInvoiceNumber;
  private boolean paymentRequired;
  private CreditCard creditCard;
  private Boolean saveToAddress;
  private Boolean saveFromAddress;
  private Double weightToMarkup = null; // used during the rating process when we do not know which
                                        // rate/service will be selected
  private String orderBy = null;
  private String order = null;
  private String referenceValue;
  private String branch;

  private String salesAgentUsername;

  private String createdBy;
  private int unitmeasure;
  private Boolean isInternationalShipment;

  private Boolean showCancelledShipments;
  private String cancelledShipments;

  private Long carrierId_web;
  private Long serviceId_web;
  // for EDI processing
  private ShippingOrder dbShipment = null;

  private String batchId = null;
  private String message = null;
  private Long webCustomerId = null;

  private LoggedEvent loggedEvent;

  // Quick Ship Implementation
  private boolean quickShipRequired;
  private boolean fastestMethod;
  private boolean cheapestMethod;

  private String billToType;
  private String billToAccountNum;
  private String billToAccountCountry;
  private String billToAccountPostalCode;

  // for invoicing
  private String customerName;
  private double totalToCharge;
  private double totalToCost;
  private double previouslyBilled;

  private String cancelledInvoice;
  private boolean isInvoiced;
  private Double quoteTotalCost;
  private Double quoteTotalCharge;
  private Double actualTotalCost;
  private Double actualTotalCharge;
  private String fromProvinceName;
  private String toProvinceName; 

  public String getFromProvinceName() {
	return fromProvinceName;
}

public void setFromProvinceName(String fromProvinceName) {
	this.fromProvinceName = fromProvinceName;
}

public String getToProvinceName() {
	return toProvinceName;
}

public void setToProvinceName(String toProvinceName) {
	this.toProvinceName = toProvinceName;
}

// Start Issue No: 107
  /*
   * private String cancelledInvoice;
   * 
   * public String getCancelledInvoice() { return cancelledInvoice; } public void
   * setCancelledInvoice(String cancelledInvoice) { this.cancelledInvoice = cancelledInvoice; }
   */
  // End
  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PackageType getPackageTypeId() {
    return packageTypeId;
  }

  public void setPackageTypeId(PackageType packageTypeId) {
    this.packageTypeId = packageTypeId;
  }

  public Long getCarrierId() {
    return carrierId;
  }

  public void setCarrierId(Long carrierId) {
    this.carrierId = carrierId;
  }

  public Long getServiceId() {
    return serviceId;
  }

  public void setServiceId(Long serviceId) {
    this.serviceId = serviceId;
  }
  
  public Long getSlaveServiceId() {
		return slaveServiceId;
	}
	
	public void setSlaveServiceId(Long slaveServiceId) {
		this.slaveServiceId = slaveServiceId;
	}

  public Long getOriginalServiceId() {
    return originalServiceId;
  }


  public void setOriginalServiceId(Long originalServiceId) {
    this.originalServiceId = originalServiceId;
  }

  public Long getStatusId() {
    return statusId;
  }
  public int getUnitmeasure() {
	    return unitmeasure;
	  }

	  public void setUnitmeasure(int unitmeasure) {
	    this.unitmeasure = unitmeasure;
	  }

  public void setStatusId(Long statusId) {
    this.statusId = statusId;
  }

  public String getStatusName() {
    return statusName;
  }

  public void setStatusName(String statusName) {
    this.statusName = statusName;
  }

  public String getReferenceCode() {
    return referenceCode;
  }

  public void setReferenceCode(String referenceCode) {
    this.referenceCode = referenceCode;
  }

  public Boolean getSatDelivery() {
    return satDelivery;
  }

  public void setSatDelivery(Boolean satDelivery) {
    this.satDelivery = satDelivery;
  }

  public Boolean getHoldForPickupRequired() {
    return holdForPickupRequired;
  }

  public void setHoldForPickupRequired(Boolean holdForPickupRequired) {
    this.holdForPickupRequired = holdForPickupRequired;
  }

  public Boolean getInsideDelivery() {
    return insideDelivery;
  }

  public void setInsideDelivery(Boolean insideDelivery) {
    this.insideDelivery = insideDelivery;
  }

  // public String getPickupTime() {
  // return pickupTime;
  // }
  // public void setPickupTime(String pickupTime) {
  // this.pickupTime = pickupTime;
  // }
  public String getSpecialEquipment() {
    return specialEquipment;
  }

  public void setSpecialEquipment(String specialEquipment) {
    this.specialEquipment = specialEquipment;
  }

  public String getCODPayment() {
    return CODPayment;
  }

  public void setCODPayment(String payment) {
    CODPayment = payment;
  }

  public Double getCODValue() {
    return CODValue;
  }

  public void setCODValue(Double value) {
    CODValue = value;
  }

  public String getCODCurrency() {
    return CODCurrency;
  }

  public void setCODCurrency(String currency) {
    CODCurrency = currency;
  }

  public String getCODPayableTO() {
    return CODPayableTO;
  }

  public void setCODPayableTO(String payableTO) {
    CODPayableTO = payableTO;
  }

  public Integer getDangerousGoods() {
    return dangerousGoods;
  }

  public void setDangerousGoods(Integer dangerousGoods) {
    this.dangerousGoods = dangerousGoods;
  }

  public String getDangerousGoodsName() {
    if (Integer.valueOf(dangerousGoods) == ShiplinxConstants.DG_NONE)
      return ShiplinxConstants.DG_NONE_TEXT;
    if (Integer.valueOf(dangerousGoods) == ShiplinxConstants.DG_LIMITED_QUANTITY)
      return ShiplinxConstants.DANGEROUS_GOODS_LIMITED;
    if (Integer.valueOf(dangerousGoods) == ShiplinxConstants.DG_500KG_EXEMPTION)
      return ShiplinxConstants.DANGEROUS_GOODS_500KG_EXEMPTION;
    if (Integer.valueOf(dangerousGoods) == ShiplinxConstants.DG_FULLY_REGULATED)
      return ShiplinxConstants.DANGEROUS_GOODS_FULLY_REGULATED;
    return "";
  }

  public void setDangerousGoodsName(String dgStatus) {
    if (!StringUtil.isEmpty(dgStatus)) {
      if (dgStatus == ShiplinxConstants.DG_NONE_TEXT)
        dangerousGoods = ShiplinxConstants.DG_NONE;
      if (dgStatus == ShiplinxConstants.DANGEROUS_GOODS_LIMITED)
        dangerousGoods = ShiplinxConstants.DG_LIMITED_QUANTITY;
      if (dgStatus == ShiplinxConstants.DANGEROUS_GOODS_500KG_EXEMPTION)
        dangerousGoods = ShiplinxConstants.DG_500KG_EXEMPTION;
      if (dgStatus == ShiplinxConstants.DANGEROUS_GOODS_FULLY_REGULATED)
        dangerousGoods = ShiplinxConstants.DG_FULLY_REGULATED;
    }
  }

  public String getReqDeliveryDate() {
    return reqDeliveryDate;
  }

  public void setReqDeliveryDate(String reqDeliveryDate) {
    this.reqDeliveryDate = reqDeliveryDate;
  }

  public Timestamp getScheduledShipDate() {
    return scheduledShipDate;
  }

  public void setScheduledShipDate(Timestamp scheduledShipDate) {
    this.scheduledShipDate = scheduledShipDate;
  }

  public Double getBaseCharge() {
    return baseCharge;
  }

  public void setBaseCharge(Double baseCharges) {
    this.baseCharge = baseCharges;
  }

  public Double getFuelCharges() {
    return fuelCharges;
  }

  public void setFuelCharges(Double fuelCharges) {
    this.fuelCharges = fuelCharges;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Timestamp dateCreated) {
    this.dateCreated = dateCreated;
  }

  // public Long getPickUpNum() {
  // return pickUpNum;
  // }
  // public void setPickUpNum(Long pickUpNum) {
  // this.pickUpNum = pickUpNum;
  // }
  public String getReferenceOne() {
    return referenceOne;
  }

  public void setReferenceOne(String referenceOne) {
    this.referenceOne = referenceOne;
  }

  public String getReferenceTwo() {
    return referenceTwo;
  }

  public void setReferenceTwo(String referenceTwo) {
    this.referenceTwo = referenceTwo;
  }

  public String getMasterTrackingNum() {
    return masterTrackingNum;
  }

  public void setMasterTrackingNum(String masterTrackingNum) {
    this.masterTrackingNum = masterTrackingNum;
  }

  public String getFromInstructions() {
    return fromInstructions;
  }

  public void setFromInstructions(String fromInstructions) {
    this.fromInstructions = fromInstructions;
  }

  public String getToInstructions() {
    return toInstructions;
  }

  public void setToInstructions(String toInstructions) {
    this.toInstructions = toInstructions;
  }

  public String getFromAttention() {
    return fromAttention;
  }

  public void setFromAttention(String fromAttention) {
    this.fromAttention = fromAttention;
  }

  public String getToAttention() {
    return toAttention;
  }

  public void setToAttention(String toAttention) {
    this.toAttention = toAttention;
  }

  public Short getRes() {
    return res;
  }

  public void setRes(Short res) {
    this.res = res;
  }

  public Boolean isFromTailgate() {
    return fromTailgate;
  }

  public Boolean getFromTailgate() {
    return fromTailgate;
  }

  public void setFromTailgate(Boolean fromTailgate) {
    this.fromTailgate = fromTailgate;
  }

  public Boolean isToTailgate() {
    return toTailgate;
  }

  public Boolean getToTailgate() {
    return toTailgate;
  }

  public void setToTailgate(Boolean toTailgate) {
    this.toTailgate = toTailgate;
  }

  public Boolean getNotifyRecipient() {
    return notifyRecipient;
  }

  public Boolean getConfirmDelivery() {
    return confirmDelivery;
  }

  public Boolean isRecidential() {
    return recidential;
  }

  public Boolean getRecidential() {
    return recidential;
  }

  public void setRecidential(Boolean recidential) {
    this.recidential = recidential;
  }

  public Boolean isSaveToAddress() {
    if (saveToAddress == null)
      return false;
    return saveToAddress;
  }

  public void setSaveToAddress(Boolean saveToAddress) {
    this.saveToAddress = saveToAddress;
  }

  public Boolean isSaveFromAddress() {
    if (saveFromAddress == null)
      return false;
    return saveFromAddress;
  }

  public void setSaveFromAddress(Boolean saveFromAddress) {
    this.saveFromAddress = saveFromAddress;
  }

  public void setNotifyRecipient(Boolean notifyRecipient) {
    this.notifyRecipient = notifyRecipient;
  }

  public void setConfirmDelivery(Boolean confirmDelivery) {
    this.confirmDelivery = confirmDelivery;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Package getPack() {
    return pack;
  }

  public void setPack(Package pack) {
    this.pack = pack;
  }

  // public CustomerCarrier getCustomerCarrier() {
  // return customerCarrier;
  // }
  // public void setCustomerCarrier(CustomerCarrier customerCarrier) {
  // this.customerCarrier = customerCarrier;
  // }
  public Short getSignatureRequired() {
    return signatureRequired;
  }

  public void setSignatureRequired(Short signatureRequired) {
    this.signatureRequired = signatureRequired;
  }

  /*
   * public Package[] getPackages() { return packages; } public void setPackages(Package[] packages)
   * { this.packages = packages; }
   */
  /**
   * @return the packages
   */
  public List<Package> getPackages() {
    return packages;
  }

  /**
   * @param packages
   *          the packages to set
   */
  public void setPackages(List<Package> packages) {
    this.packages = packages;
  }

  /**
   * @return the packageArray
   */
  public Package[] getPackageArray() {
    return packageArray;
  }

  /**
   * @param packageArray
   *          the packageArray to set
   */
  public void setPackageArray(Package[] packageArray) {
    this.packageArray = packageArray;
  }

  public int getDimType() {
    return dimType;
  }

  public void setDimType(int dimType) {
    this.dimType = dimType;
  }

  public float getInsuredAmount() {
    return insuredAmount;
  }

  public void setInsuredAmount(float insuredAmount) {
    this.insuredAmount = insuredAmount;
  }

  public float getInsuranceValue() {
    return insuranceValue;
  }

  public void setInsuranceValue(float insuranceValue) {
    this.insuranceValue = insuranceValue;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getRateIndex() {
    return rateIndex;
  }

  public void setRateIndex(String rateIndex) {
    this.rateIndex = rateIndex;
  }

  public List<Rating> getRateList() {
    return rateList;
  }

  public void setRateList(List<Rating> rateList) {
    this.rateList = rateList;
  }

  public Double getTotalCharge() {
    return totalCharge;
  }

  public void setTotalCharge(Double totalCharge) {
    this.totalCharge = totalCharge;
  }

  public String getCarrierName() {
    if (StringUtil.isEmpty(this.carrierName))
      this.setCarrierName(ShiplinxConstants.getCarrierName(this.getCarrierId().intValue()));
    return this.carrierName;
  }

  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName;
  }

  public String getServiceName() {
    return this.serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getOrderStatus() {
    return this.orderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getTrackingURL() {
    return trackingURL;
  }

  public void setTrackingURL(String trackingURL) {
    this.trackingURL = trackingURL;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getCurrency() {
    return currency;
  }

  public void setEdiVerified(boolean b) {
    ediVerified = b;

  }

  public boolean isEdiVerified() {
    return ediVerified;
  }

  public String getManifestNumber() {
    return manifestNumber;
  }

  public void setManifestNumber(String manifestNumber) {
    this.manifestNumber = manifestNumber;
  }

  public List<Charge> getCharges() {
    return charges;
  }

  public void setCharges(List<Charge> surcharges) {
    this.charges = surcharges;
  }

  public void setScheduledAt(Timestamp scheduledAt) {
    this.scheduledAt = scheduledAt;
  }

  public Timestamp getScheduledAt() {
    return this.scheduledAt;
  }

  public void setIsOrderEdited(String isOrderEdited) {
    this.isOrderEdited = isOrderEdited;
  }

  public void getIsOrderEdited(String isOrderEdited) {
    this.isOrderEdited = isOrderEdited;
  }

  public boolean isShipmentInternational() {
    if (!fromAddress.getCountryCode().equalsIgnoreCase(toAddress.getCountryCode()))
      return true;
    else
      return false;
  }

  public boolean isLive() {
    return live;
  }

  public void setLive(boolean isLive) {
    this.live = isLive;
  }

  public String getProofOfDelivery() {
    return proofOfDelivery;
  }

  public void setProofOfDelivery(String proofOfDelivery) {
    this.proofOfDelivery = proofOfDelivery;
  }

  public Date getDateOfDelivery() {
    return dateOfDelivery;
  }

  public void setDateOfDelivery(Date dateOfDelivery) {
    this.dateOfDelivery = dateOfDelivery;
  }

  public String getFromCountry() {
    return fromCountry;
  }

  public void setFromCountry(String fromCountry) {
    this.fromCountry = fromCountry;
  }

  public String getFromDate() {
    return fromDate;
  }

  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }

  public String getToDate() {
    return toDate;
  }

  public void setToDate(String toDate) {
    this.toDate = toDate;
  }

  // public String getCarrierPickUpConf() {
  // return carrierPickUpConf;
  // }
  // public void setCarrierPickUpConf(String carrierPickUpConf) {
  // this.carrierPickUpConf = carrierPickUpConf;
  // }
  public long getBusinessId() {
    return businessId;
  }

  public void setBusinessId(long businessId) {
    this.businessId = businessId;
  }

  public Business getBusiness() {
    return business;
  }

  public void setBusiness(Business business) {
    this.business = business;
  }

  public long getShipToId() {
    return shipToId;
  }

  public void setShipToId(long shipToId) {
    this.shipToId = shipToId;
  }

  public long getShipFromId() {
    return shipFromId;
  }

  public void setShipFromId(long shipFromId) {
    this.shipFromId = shipFromId;
  }

  public Address getFromAddress() {
    return fromAddress;
  }

  public void setFromAddress(Address fromAddress) {
    this.fromAddress = fromAddress;
  }

  public Address getToAddress() {
    return toAddress;
  }

  public void setToAddress(Address toAddress) {
    this.toAddress = toAddress;
  }

  public String getFromZone() {
    return fromZone;
  }

  public void setFromZone(String fromZone) {
    this.fromZone = fromZone;
  }

  public String getToZone() {
    return toZone;
  }

  public void setToZone(String toZone) {
    this.toZone = toZone;
  }

  public Date getPodDateTime() {
    return podDateTime;
  }

  public void setPodDateTime(Date podDateTime) {
    this.podDateTime = podDateTime;
  }

  public String getPodReceiver() {
    return podReceiver;
  }

  public void setPodReceiver(String podReceiver) {
    this.podReceiver = podReceiver;
  }

  public Date getFromDateTime() {
    if (!StringUtil.isEmpty(fromDate))
      return FormattingUtil.getDate(fromDate, FormattingUtil.DATE_FORMAT_WEB);
    return null;
  }

  public Date getToDateTime() {
    if (!StringUtil.isEmpty(toDate))
      return FormattingUtil.getDateEndOfDay(toDate, FormattingUtil.DATE_FORMAT_WEB_ENDOFDAY);
    return null;
  }

  public Integer getMarkPercent() {
    return markPercent;
  }

  public void setMarkPercent(Integer markPercent) {
    this.markPercent = markPercent;
  }

  public Integer getMarkType() {
    return markType;
  }

  public void setMarkType(Integer markType) {
    this.markType = markType;
  }

  public boolean isPaymentRequired() {
    return paymentRequired;
  }

  public void setPaymentRequired(boolean paymentRequired) {
    this.paymentRequired = paymentRequired;
  }

  public CreditCard getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(CreditCard creditCard) {
    this.creditCard = creditCard;
  }

  public String getScheduledShipDate_web() {
    return scheduledShipDate_web;
  }

  public void setScheduledShipDate_web(String scheduledShipDate_web) {
    this.scheduledShipDate_web = scheduledShipDate_web;
  }

  public Double getPaidAmount() {
    return paidAmount;
  }

  public void setPaidAmount(Double paidAmount) {
    this.paidAmount = paidAmount;
  }

  public Double getAppliedAmount() {
    return appliedAmount;
  }

  public void setAppliedAmount(Double appliedAmount) {
    this.appliedAmount = appliedAmount;
  }

  public Double getRefundedAmount() {
    return refundedAmount;
  }

  public void setRefundedAmount(Double refundedAmount) {
    this.refundedAmount = refundedAmount;
  }

  public Integer getBillingStatus() {
    return billingStatus;
  }

  public void setBillingStatus(Integer billingStatus) {
    this.billingStatus = billingStatus;
  }

  public String getBillingStatusText() {
    /*
     * if (billingStatus != null && billingStatus.intValue()>=0 && billingStatus.intValue() <
     * ShiplinxConstants.BILLING_STATUS_LIST.length) { return
     * ShiplinxConstants.BILLING_STATUS_LIST[billingStatus];
     * 
     * }
     */
    if (billingStatus != null
        && billingStatus.intValue() == ShiplinxConstants.BILLING_STATUS_NOT_INVOICED)
      return ShiplinxConstants.BILLING_STATUS_NOT_INVOICED_TEXT;
    else if (billingStatus != null
        && billingStatus.intValue() == ShiplinxConstants.BILLING_STATUS_AWAITING_CONFIRMATION)
      return ShiplinxConstants.BILLING_STATUS_AWAITING_CONFIRMATION_TEXT;
    else if (billingStatus != null
        && billingStatus.intValue() == ShiplinxConstants.BILLING_STATUS_CUSTOMER_CONFIRMATION)
      return ShiplinxConstants.BILLING_STATUS_CUSTOMER_CONFIRMATION_TEXT;
    else if (billingStatus != null
        && billingStatus.intValue() == ShiplinxConstants.BILLING_STATUS_ORPHAN)
      return ShiplinxConstants.BILLING_STATUS_ORPHAN_TEXT;
    else if (billingStatus != null
        && billingStatus.intValue() == ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE)
      return ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE_TEXT;
    else if (billingStatus != null
        && billingStatus.intValue() == ShiplinxConstants.BILLING_STATUS_INVOICED)
      return ShiplinxConstants.BILLING_STATUS_INVOICED_TEXT;
    else if (billingStatus != null
        && billingStatus.intValue() == ShiplinxConstants.BILLING_STATUS_THIRD_PARTY)
      return ShiplinxConstants.BILL_TO_THIRD_PARTY;
    else if (billingStatus != null
        && billingStatus.intValue() == ShiplinxConstants.BILLING_STATUS_COLLECT)
      return ShiplinxConstants.BILL_TO_COLLECT;

    return "";
  }

  public void setBillingStatusText(String status) {
    if (!StringUtil.isEmpty(status)) {
      /*
       * for (int i=0; i < ShiplinxConstants.BILLING_STATUS_LIST.length; i++) { String s =
       * ShiplinxConstants.BILLING_STATUS_LIST[i]; if (s.equals(status)) { billingStatus = i;
       * return; } }
       */
      if (status.equalsIgnoreCase(ShiplinxConstants.BILLING_STATUS_NOT_INVOICED_TEXT))
        billingStatus = ShiplinxConstants.BILLING_STATUS_NOT_INVOICED;
      else if (status.equalsIgnoreCase(ShiplinxConstants.BILLING_STATUS_AWAITING_CONFIRMATION_TEXT))
        billingStatus = ShiplinxConstants.BILLING_STATUS_AWAITING_CONFIRMATION;
      else if (status.equalsIgnoreCase(ShiplinxConstants.BILLING_STATUS_CUSTOMER_CONFIRMATION_TEXT))
        billingStatus = ShiplinxConstants.BILLING_STATUS_CUSTOMER_CONFIRMATION;
      else if (status.equalsIgnoreCase(ShiplinxConstants.BILLING_STATUS_ORPHAN_TEXT))
        billingStatus = ShiplinxConstants.BILLING_STATUS_ORPHAN;
      else if (status.equalsIgnoreCase(ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE_TEXT))
        billingStatus = ShiplinxConstants.BILLING_STATUS_READY_TO_INVOICE;
      else if (status.equalsIgnoreCase(ShiplinxConstants.BILLING_STATUS_INVOICED_TEXT))
        billingStatus = ShiplinxConstants.BILLING_STATUS_INVOICED;
    }
    // billingStatus=0;
  }

  public String getEdiInvoiceNumber() {
    return ediInvoiceNumber;
  }

  public void setEdiInvoiceNumber(String ediInvoiceNumber) {
    this.ediInvoiceNumber = ediInvoiceNumber;
  }

  public List<Charge> getChargesForInvoice() {
    return chargesForInvoice;
  }

  public void setChargesForInvoice(List<Charge> chargesForInvoice) {
    this.chargesForInvoice = chargesForInvoice;
  }

  // returns the total of all charges that are bill-able (i.e. READY TO INVOICE) for this shipment
  public double getTotalBillable() {
    return getTotalCharge(new int[] { ShiplinxConstants.CHARGE_READY_TO_INVOICE });
  }

  public double getTotalBillableCost() {
    return getTotalCost(new int[] { ShiplinxConstants.CHARGE_READY_TO_INVOICE });
  }

  public double getTotalBillableTariff() {
    return getTotalTariff(new int[] { ShiplinxConstants.CHARGE_READY_TO_INVOICE });
  }

  public double getTotalBilled() {
    return getTotalCharge(new int[] { ShiplinxConstants.CHARGE_INVOICED });
  }

  public double getTotalChargeQuoted() {
	  return getTotalChargeAmount(new int[] { ShiplinxConstants.CHARGE_QUOTED });
    // return getTotalCharge(new int[]{ShiplinxConstants.CHARGE_QUOTED});
  }

  public double getTotalCostQuoted() {
	  return getTotalCostAmount(new int[] { ShiplinxConstants.CHARGE_QUOTED });

  }

  public double getTotalChargeActual() {
	  return getTotalChargeAmount(new int[] { ShiplinxConstants.CHARGE_TYPE_ACTUAL });
    // return getTotalCharge(new int[]{ShiplinxConstants.CHARGE_PENDING_RELEASE,
    // ShiplinxConstants.CHARGE_READY_TO_INVOICE,ShiplinxConstants.CHARGE_INVOICED});
  }

  public double getTotalCostActual() {
	  return getTotalCostAmount(new int[] { ShiplinxConstants.CHARGE_TYPE_ACTUAL });
  }

  private double getTotalChargeAmount(int[] type) {
    double total = 0;
    for (Charge c : charges) {
      for (int s : type) {
        if (c != null && c.getCharge() != null && c.getType() == s
            && c.getStatus() != ShiplinxConstants.CHARGE_CANCELLED)
          total = (FormattingUtil.add(total, c.getCharge().doubleValue())).doubleValue();
      }
    }
    return FormattingUtil.roundFigureRates(total, 2);
  }

  private double getTotalCostAmount(int[] type) {
    double total = 0;
    for (Charge c : charges) {
      for (int s : type) {
        if (c != null && c.getCost() != null && c.getType() == s
            && c.getStatus() != ShiplinxConstants.CHARGE_CANCELLED)
          total = (FormattingUtil.add(total, c.getCost().doubleValue())).doubleValue();
      }
    }
    return FormattingUtil.roundFigureRates(total, 2);

  }

  private double getTotalCharge(int[] status) {
    double total = 0;
    for (Charge c : charges) {
      for (int s : status) {
        if (c.getStatus() == s)
          total = (FormattingUtil.add(total, c.getCharge().doubleValue())).doubleValue();
      }
    }
    return FormattingUtil.roundFigureRates(total, 2);
  }

  // Start Issue No:44
  /*
   * private double getTotalCharge1(int[] status){ double total = 0; for(Charge c:charges){ for(int
   * s:status){ if(c.getStatus()==s && c.getType()==0){ total = (FormattingUtil.add(total,
   * c.getCharge().doubleValue())).doubleValue(); }
   * 
   * } } return FormattingUtil.roundFigureRates(total, 2); } private double getTotalCharge2(int[]
   * status){ double total = 0; for(Charge c:charges){ for(int s:status){ if(c.getStatus()==s &&
   * c.getType()==1){ total= (FormattingUtil.add(total, c.getCharge().doubleValue())).doubleValue();
   * }
   * 
   * } } return FormattingUtil.roundFigureRates(total, 2); }
   */
  // End
  private double getTotalCost(int[] status) {
    double total = 0;
    for (Charge c : charges) {
      for (int s : status) {
        if (c.getStatus() == s)
          total = (FormattingUtil.add(total, c.getCost().doubleValue())).doubleValue();
      }
    }
    return FormattingUtil.roundFigureRates(total, 2);
  }

  private double getTotalTariff(int[] status) {
    double total = 0;
    for (Charge c : charges) {
      for (int s : status) {
        if (c.getStatus() == s)
          total = (FormattingUtil.add(total, c.getTariffRate().doubleValue())).doubleValue();
      }
    }
    return FormattingUtil.roundFigureRates(total, 2);
  }

  public List<Charge> getQuotedCharges() {
    if (charges != null) {
      List<Charge> qCharges = new ArrayList<Charge>();
      for (Charge c : charges)
        if (c.getType() == ShiplinxConstants.CHARGE_TYPE_QUOTED)
          qCharges.add(c);
      return qCharges;
    }
    return null;
  }

  public List<Charge> getActualCharges() {
    if (charges != null) {
      List<Charge> aCharges = new ArrayList<Charge>();
      for (Charge c : charges)
        if (c.getType() == ShiplinxConstants.CHARGE_TYPE_ACTUAL)
          aCharges.add(c);
      return aCharges;
    }
    return null;
  }

  public List<ShippingLabel> getLabels() {
    return labels;
  }

  public void setLabels(List<ShippingLabel> labels) {
    this.labels = labels;
  }

  public List<CCTransaction> getCcTransactions() {
    return ccTransactions;
  }

  public void setCcTransactions(List<CCTransaction> ccTransactions) {
    this.ccTransactions = ccTransactions;
  }

  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }

  // public String getStatusName() {
  // if(statusId==null)
  // return "";
  // switch(statusId.intValue()) {
  // case ShiplinxConstants.STATUS_DISPATCHED: return MessageUtil.getMessage("status.rfs"); //name
  // changed from "dispatched" to "rfs"
  // case ShiplinxConstants.STATUS_INTRANSIT: return MessageUtil.getMessage("status.intransit");
  // case ShiplinxConstants.STATUS_DELIVERED: return MessageUtil.getMessage("status.delivered");
  // case ShiplinxConstants.STATUS_CANCELLED: return MessageUtil.getMessage("status.cancelled");
  // case ShiplinxConstants.STATUS_EXCEPTION: return MessageUtil.getMessage("status.exception");
  // case ShiplinxConstants.STATUS_READYTOPROCESS: return
  // MessageUtil.getMessage("status.ready_to_process");
  // case ShiplinxConstants.STATUS_SENT_TOWAREHOUSE: return
  // MessageUtil.getMessage("status.sent.warehouse");
  // case ShiplinxConstants.STATUS_RECEIVED_BYWAREHOUSE: return
  // MessageUtil.getMessage("status.received.warehouse");
  // case ShiplinxConstants.STATUS_SHIPPED: return MessageUtil.getMessage("status.shipped");
  // }
  // return "";
  // }
  public BigDecimal getDutiableAmount() {
    return dutiableAmount;
  }

  public void setDutiableAmount(BigDecimal d) {
    this.dutiableAmount = d;
  }

  public String getSalesAgentUsername() {
    return salesAgentUsername;
  }

  public void setSalesAgentUsername(String salesAgentUsername) {
    this.salesAgentUsername = salesAgentUsername;
  }

  public ShippingOrder getDbShipment() {
    return dbShipment;
  }

  public void setDbShipment(ShippingOrder dbShipment) {
    this.dbShipment = dbShipment;
  }

  public Boolean getDocsOnly() {
    return docsOnly;
  }

  public void setDocsOnly(Boolean docsOnly) {
    this.docsOnly = docsOnly;
  }

  public Pickup getPickup() {
    return pickup;
  }

  public void setPickup(Pickup pickup) {
    this.pickup = pickup;
  }

  public boolean isCustomsInvoiceRequired() {
    return customsInvoiceRequired;
  }

  public void setCustomsInvoiceRequired(boolean customsInvoiceRequired) {
    this.customsInvoiceRequired = customsInvoiceRequired;
  }

  public CustomsInvoice getCustomsInvoice() {
    return customsInvoice;
  }

  public void setCustomsInvoice(CustomsInvoice customsInvoice) {
    this.customsInvoice = customsInvoice;
  }

  public Boolean getIsInternationalShipment() {
    if (!fromAddress.getCountryCode().equalsIgnoreCase(toAddress.getCountryCode()))
      isInternationalShipment = true;
    else
      isInternationalShipment = false;
    return isInternationalShipment;
  }

  public void setIsInternationalShipment(Boolean isInternationalShipment) {
    this.isInternationalShipment = isInternationalShipment;
  }

  public String getBatchId() {
    return batchId;
  }

  public void setBatchId(String batchId) {
    this.batchId = batchId;
  }

  // Everything weight related
  public void setBilledWeightUOM(String billedWeightUOM) {
    this.billedWeightUOM = billedWeightUOM;
  }

  public String getBilledWeightUOM() {
    if (StringUtil.isEmpty(billedWeightUOM))
      return ShiplinxConstants.WEIGHT_UNITS_LBS;
    return billedWeightUOM;
  }

  public Float getBilledWeight() {
    return billedWeight;
  }

  public void setBilledWeight(Float billedWeight) {
    this.billedWeight = billedWeight;
  }

  public Float getQuotedWeight() {
    if (quotedWeight != null && quotedWeight > 0)
      return (float) FormattingUtil.roundFigureRates(quotedWeight, 2);
    else
      return getTotalActualWeight().floatValue(); // this is for backward compatibility

  }

  public void setQuotedWeight(Float quotedWeight) {
    this.quotedWeight = quotedWeight;
  }

  public String getQuotedWeightUOM() {
    return quotedWeightUOM;
  }

  public void setQuotedWeightUOM(String quotedWeightUOM) {
    this.quotedWeightUOM = quotedWeightUOM;
  }

  public Double getTotalActualWeight() {
    // TODO Auto-generated method stub
    double weight = 0;
    if (this.getPackages() != null) {
      for (Package p : this.getPackages()) {
        if (p.getWeight() != null)
          weight += p.getWeight().floatValue();
      }
    }
    return FormattingUtil.roundFigureRates(weight, 2);
  }

  public Double getTotalWeight() {

    if (getBilledWeight() != null && getBilledWeight() > 0) {
      return FormattingUtil.roundFigureRates(getBilledWeight(), 2);
    }

    // TODO Auto-generated method stub
    double weight = 0;
    if (this.getPackages() != null) {
      for (Package p : this.getPackages()) {
        if (p.getBilledWeight() != null)
          weight += p.getBilledWeight();
        else
          weight += p.getWeight().floatValue();
      }
    }
    return FormattingUtil.roundFigureRates(weight, 2);
  }

  public Double getTotalWeightWithDimFactor(BigDecimal dimFact) {
    double weight = 0;
    if (this.getPackages() != null) {
      for (Package p : this.getPackages()) {
    	  if (p != null && p.getWidth() != null && p.getHeight() != null && dimFact != null
    	            && dimFact.signum() != 0) {
    		  weight += p.getLength().doubleValue() * p.getWidth().doubleValue()
             * p.getHeight().doubleValue() / dimFact.doubleValue();

    	        }

      }
    }
    return FormattingUtil.roundFigureRates(weight, 2);
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Double getWeightToMarkup() {
    return weightToMarkup;
  }

  public void setWeightToMarkup(Double weightToMarkup) {
    this.weightToMarkup = weightToMarkup;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void addMessage(Exception e) {
    // TODO Auto-generated method stub
    if (e != null) {
      StringBuffer sbMsg = new StringBuffer();

      if (e.getMessage() != null) {
        sbMsg.append(e.getMessage());
        sbMsg.append(" ");
      }
      if (e.getCause() != null) {
        sbMsg.append(e.getCause().getMessage());
      }
      if (sbMsg.length() == 0)
        sbMsg.append(e.getClass().getName());

      addMessage(sbMsg.toString());
    }
  }

  public void addMessage(List<CarrierErrorMessage> errMessages) {
    // TODO Auto-generated method stub
    if (errMessages != null) {
      for (CarrierErrorMessage e : errMessages) {
        if (!StringUtil.isEmpty(e.getMessage()))
          addMessage(e.getMessage());
      }
    }
  }

  private void addMessage(String s) {
    // TODO Auto-generated method stub
    if (!StringUtil.isEmpty(s)) {
      StringBuffer sbMsg = new StringBuffer();

      if (!StringUtil.isEmpty(message)) {
        sbMsg.append(message);
        sbMsg.append(" | ");
      }
      sbMsg.append("[");
      sbMsg.append(this.scheduledShipDate_web); // Formated current date
      sbMsg.append("] [");

      sbMsg.append(s);

      sbMsg.append("]");

      message = sbMsg.toString();
    }
  }

  public boolean isAdditionalFieldsEditable() {
    if (statusId != null) {
      if (statusId.longValue() == ShiplinxConstants.STATUS_DISPATCHED
          || statusId.longValue() == ShiplinxConstants.STATUS_INTRANSIT
          || statusId.longValue() == ShiplinxConstants.STATUS_DELIVERED
          || statusId.longValue() == ShiplinxConstants.STATUS_PRE_DISPATCHED
          || statusId.longValue() == ShiplinxConstants.STATUS_CLOSED)
        return true;
    }
    return false;
  }

  public Long getWebCustomerId() {
    if (webCustomerId == null || webCustomerId.longValue() < 0)
      webCustomerId = customerId;
    return webCustomerId;
  }

  public void setWebCustomerId(Long webCustomerId) {
    this.webCustomerId = webCustomerId;
  }

  public Boolean getShowCancelledShipments() {
    return showCancelledShipments;
  }

  public void setShowCancelledShipments(Boolean showCancelledShipments) {
    this.showCancelledShipments = showCancelledShipments;
  }

  public String getCancelledShipments() {
    return cancelledShipments;
  }

  public void setCancelledShipments(String cancelledShipments) {
    this.cancelledShipments = cancelledShipments;
  }

  public LoggedEvent getLoggedEvent() {
    return loggedEvent;
  }

  public void setLoggedEvent(LoggedEvent loggedEvent) {
    this.loggedEvent = loggedEvent;
  }

  public String getCODPin() {
    return CODPin;
  }

  public void setCODPin(String pin) {
    CODPin = pin;
  }

  public List<Products> getWarehouseProducts() {
    return warehouseProducts;
  }

  public void setWarehouseProducts(List<Products> warehouseProducts) {
    this.warehouseProducts = warehouseProducts;
  }

  public Products getWarehouseProduct() {
    return warehouseProduct;
  }

  public void setWarehouseProduct(Products warehouseProduct) {
    this.warehouseProduct = warehouseProduct;
  }

  public Products getWarehouseProductEach() {
    return warehouseProductEach;
  }

  public void setWarehouseProductEach(Products warehouseProductEach) {
    this.warehouseProductEach = warehouseProductEach;
  }

  public int getFulfilled() {
    return fulfilled;
  }

  public void setFulfilled(int fullfilled) {
    this.fulfilled = fullfilled;
  }

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public boolean isTradeShowPickup() {
    return tradeShowPickup;
  }

  public void setTradeShowPickup(boolean tradeShowPickup) {
    this.tradeShowPickup = tradeShowPickup;
  }

  public boolean isTradeShowDelivery() {
    return tradeShowDelivery;
  }

  public void setTradeShowDelivery(boolean tradeShowDelivery) {
    this.tradeShowDelivery = tradeShowDelivery;
  }

  public boolean isInsidePickup() {
    return insidePickup;
  }

  public void setInsidePickup(boolean insidePickup) {
    this.insidePickup = insidePickup;
  }

  public boolean isAppointmentPickup() {
    return appointmentPickup;
  }

  public void setAppointmentPickup(boolean appointmentPickup) {
    this.appointmentPickup = appointmentPickup;
  }

  public boolean isAppointmentDelivery() {
    return appointmentDelivery;
  }

  public void setAppointmentDelivery(boolean appointmentDelivery) {
    this.appointmentDelivery = appointmentDelivery;
  }

  public Carrier getCarrier() {
    return carrier;
  }

  public void setCarrier(Carrier carrier) {
    this.carrier = carrier;
  }

  public String getReferenceCodeName() {
    return referenceCodeName;
  }

  public void setReferenceCodeName(String referenceCodeName) {
    this.referenceCodeName = referenceCodeName;
  }

  public String getReferenceOneName() {
    return referenceOneName;
  }

  public void setReferenceOneName(String referenceOneName) {
    this.referenceOneName = referenceOneName;
  }

  public String getReferenceTwoName() {
    return referenceTwoName;
  }

  public void setReferenceTwoName(String referenceTwoName) {
    this.referenceTwoName = referenceTwoName;
  }

  public String getCarrierReference() {
    return carrierReference;
  }

  public void setCarrierReference(String carrierReference) {
    this.carrierReference = carrierReference;
  }

  public String getReferenceValue() {
    return referenceValue;
  }

  public void setReferenceValue(String referenceValue) {
    this.referenceValue = referenceValue;
  }

  public boolean isFastestMethod() {
    return fastestMethod;
  }

  public void setFastestMethod(boolean fastestMethod) {
    this.fastestMethod = fastestMethod;
  }

  public boolean isCheapestMethod() {
    return cheapestMethod;
  }

  public void setCheapestMethod(boolean cheapestMethod) {
    this.cheapestMethod = cheapestMethod;
  }

  public boolean isQuickShipRequired() {
    return quickShipRequired;
  }

  public void setQuickShipRequired(boolean quickShipRequired) {
    this.quickShipRequired = quickShipRequired;
  }

  public float getFuelPercent() {
    return fuelPercent;
  }

  public void setFuelPercent(float fuelPercent) {
    this.fuelPercent = fuelPercent;
  }

  public Long getCarrierId_web() {
    return carrierId_web;
  }

  public void setCarrierId_web(Long carrierId_web) {
    this.carrierId_web = carrierId_web;
  }

  public Long getServiceId_web() {
    return serviceId_web;
  }

  public void setServiceId_web(Long serviceId_web) {
    this.serviceId_web = serviceId_web;
  }

  public Double getRatedAsWeight() {
    return ratedAsWeight;
  }

  public void setRatedAsWeight(Double ratedAsWeight) {
    this.ratedAsWeight = ratedAsWeight;
  }

  public String getBillToType() {
    return billToType;
  }

  public void setBillToType(String billToType) {
    this.billToType = billToType;
  }

  public String getBillToAccountNum() {
    return billToAccountNum;
  }

  public void setBillToAccountNum(String billToAccountNum) {
    this.billToAccountNum = billToAccountNum;
  }

  public String getBillToAccountCountry() {
    return billToAccountCountry;
  }

  public void setBillToAccountCountry(String billToAccountCountry) {
    this.billToAccountCountry = billToAccountCountry;
  }

  public String getBillToAccountPostalCode() {
    return billToAccountPostalCode;
  }

  public void setBillToAccountPostalCode(String billToAccountPostalCode) {
    this.billToAccountPostalCode = billToAccountPostalCode;
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public double getTotalToCharge() {
    return FormattingUtil.roundFigureRates(totalToCharge, 2);
  }

  public void setTotalToCharge(double totalToCharge) {
    this.totalToCharge = totalToCharge;
  }

  public double getTotalToCost() {
    return FormattingUtil.roundFigureRates(totalToCost, 2);
  }

  public void setTotalToCost(double totalToCost) {
    this.totalToCost = totalToCost;
  }

  public double getPreviouslyBilled() {
    return FormattingUtil.roundFigureRates(previouslyBilled, 2);
  }

  public void setPreviouslyBilled(double previouslyBilled) {
    this.previouslyBilled = previouslyBilled;
  }

  public void setStatusIdFromString(String sid) {
    if (!StringUtil.isEmpty(sid)) {
      int n = Integer.parseInt(sid);
      if (n > 0)
        setStatusId(new Long(n));
    }
  }

  public String getCancelledInvoice() {
    return cancelledInvoice;
  }

  public void setCancelledInvoice(String cancelledInvoice) {
    this.cancelledInvoice = cancelledInvoice;
  }
  public boolean isInvoiced1() {
	      return isInvoiced;
	    }
	    public void setInvoiced(boolean isInvoiced) {
	      this.isInvoiced = isInvoiced;
	  }
	    public Double getQuoteTotalCost() {
	        return quoteTotalCost;
	      }

	      public void setQuoteTotalCost(Double quoteTotalCost) {
	        this.quoteTotalCost = quoteTotalCost;
	      }

	      public Double getQuoteTotalCharge() {
	        return quoteTotalCharge;
	      }

	      public void setQuoteTotalCharge(Double quoteTotalCharge) {
	        this.quoteTotalCharge = quoteTotalCharge;
	      }

	      public Double getActualTotalCost() {
	        return actualTotalCost;
	      }

	      public void setActualTotalCost(Double actualTotalCost) {
	        this.actualTotalCost = actualTotalCost;
	      }

	      public Double getActualTotalCharge() {
	        return actualTotalCharge;
	      }

	      public void setActualTotalCharge(Double actualTotalCharge) {
	        this.actualTotalCharge = actualTotalCharge;
	      }



public boolean isInvoiced() {
	// TODO Auto-generated method stub
	return false;
}
public static void getLocaleTimeByUser(String userTimeZone) throws Exception {
    if (userTimeZone != null) {
      Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(userTimeZone));
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
      df.setTimeZone(cal.getTimeZone());
      tf.setTimeZone(cal.getTimeZone());
      System.out.println(df.format(cal.getTime()));
      System.out.println(tf.format(cal.getTime()));

    } else {

     throw new Exception("User doesn't have timezone");
    }

  }


}
