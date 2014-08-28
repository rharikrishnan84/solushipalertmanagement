package com.meritconinc.shiplinx.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class Rating implements Serializable {

  private long id;
  private long carrierId;
  private long serviceId;
  private long originalServiceId; // Used in DHL to remember the "real" service. See rateShipment
                                  // function, line 122
  private double baseCharge;
  private double fuelSurcharge;
  private double fuel_perc;
  private double totalSurcharge = 0;
  private String carrierName;
  private String serviceName;
  private boolean disabled;
  private String modeTransport;
  // private String transitDays;
  private double billWeight;
  private String billWeightUOM = ShiplinxConstants.WEIGHT_UNITS_LBS; // Currently the system only
                                                                     // accepts and quotes on LBS.
  private String currency;
  private boolean schedPickup = false;
  private String instanceAPIName;
  private int transitDays = 0;
  private int transitDaysMin = 0;
  private boolean isDiscounted = true;
  private double largePackageSurcharges = 0.0;
  private List<Surcharge> surcharges = new ArrayList<Surcharge>();
  private List<Charge> charges = new ArrayList<Charge>();

  private Charge charge = new Charge();

  private double totalCost;
  private double total;

  private double markupFlat;
  private Integer markupPercentage;
  private String markupTypeText;
  private Integer markupType;

  // UPS related
  private boolean ups_switch_res_comm; // indicator that rating response converted residential
                                       // indicator to commercial
  private boolean ups_extended_area; // indicator that rating response has an extended area
                                     // surcharge included, to be used for Canada extended area
                                     // surcharge
  private double handlingCharges;
  private double tariffRate;
  private double tariffFuelSurcharge;
  private double serviceOptionsCharges;
  private double negotiatedRates;

  private double staticFuel = 0;

  // Booking key to be remembered, some carriers send a key in the rating response and this key
  // needs to be sent at booking/shipping time
  private String bookingKey;

  private Service newService;

  private double ratedAsWeight = 0;

  private String loginURL;
  private CustomerCarrier customerCarrier;

  private long zoneStructureId;

  private long slaveServiceId;
  private long slaveCarrierId;
    
  
  private int variable;    
      public int getVariable() {
  	return variable;
  }
  
  public void setVariable(int variable) {
  	this.variable = variable;
  }
  
  
    public long getSlaveCarrierId() {
	return slaveCarrierId;
}

public void setSlaveCarrierId(long slaveCarrierId) {
	this.slaveCarrierId = slaveCarrierId;
}

public String getSlaveCarrierName() {
	return slaveCarrierName;
}

public void setSlaveCarrierName(String slaveCarrierName) {
	this.slaveCarrierName = slaveCarrierName;
}

	private String slaveCarrierName;
  public long getCarrierId() {
    return carrierId;
  }

  public void setCarrierId(long carrierId) {
    this.carrierId = carrierId;
  }

  public long getServiceId() {
    return serviceId;
  }

  public void setServiceId(long serviceId) {
    this.serviceId = serviceId;
  }

  public double getBaseCharge() {
    return baseCharge;
  }

  public void setBaseCharge(double baseCharge) {
    this.baseCharge = baseCharge;
  }

  public double getFuelSurcharge() {
    return fuelSurcharge;
  }

  public void setFuelSurcharge(double fuelSurcharge) {
    this.fuelSurcharge = fuelSurcharge;
  }

  public double getFuel_perc() {
    return fuel_perc;
  }

  public void setFuel_perc(double fuel_perc) {
    this.fuel_perc = fuel_perc;
  }

  public double getTotalSurcharge() {
    DecimalFormat twoDForm = new DecimalFormat("#.##");
    return Double.valueOf(twoDForm.format(totalSurcharge));
    // return totalSurcharge;
  }

  public void setTotalSurcharge(double totalSurcharge) {
    this.totalSurcharge = totalSurcharge;
  }

  public String getCarrierName() {
    return carrierName;
  }

  public void setCarrierName(String carrierName) {
    this.carrierName = carrierName;
  }

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

  public boolean isDisabled() {
    return disabled;
  }

  public void setDisabled(boolean disabled) {
    this.disabled = disabled;
  }

  public String getModeTransport() {
    return modeTransport;
  }

  public void setModeTransport(String modeTransport) {
    this.modeTransport = modeTransport;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  public double getTotal() {
    DecimalFormat twoDForm = new DecimalFormat("#.##");
    return Double.valueOf(twoDForm.format(total));
  }

  public void setTotal(double total) {
    this.total = total;
  }

  public List<Surcharge> getSurcharges() {
    return surcharges;
  }

  public void setSurcharges(List<Surcharge> surcharges) {
    this.surcharges = surcharges;
  }

  /*
   * public String getTransitDays() { return transitDays; } public void setTransitDays(String
   * transitDays) { this.transitDays = transitDays; }
   */
  public double getBillWeight() {
    return billWeight;
  }

  public void setBillWeight(double billWeight) {
    this.billWeight = billWeight;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public boolean isSchedPickup() {
    return schedPickup;
  }

  public void setSchedPickup(boolean schedPickup) {
    this.schedPickup = schedPickup;
  }

  public String getInstanceAPIName() {
    return instanceAPIName;
  }

  public void setInstanceAPIName(String instanceAPIName) {
    this.instanceAPIName = instanceAPIName;
  }

  public double getHandlingCharges() {
    return handlingCharges;
  }

  public void setHandlingCharges(double handlingCharges) {
    this.handlingCharges = handlingCharges;
  }

  public double getServiceOptionsCharges() {
    return serviceOptionsCharges;
  }

  public void setServiceOptionsCharges(double serviceOptionsCharges) {
    this.serviceOptionsCharges = serviceOptionsCharges;
  }

  public double getNegotiatedRates() {
    return negotiatedRates;
  }

  public void setNegotiatedRates(double negotiatedRates) {
    this.negotiatedRates = negotiatedRates;
  }

  public boolean isDiscounted() {
    return isDiscounted;
  }

  public void setDiscounted(boolean isDiscounted) {
    this.isDiscounted = isDiscounted;
  }

  public void setTransitDays(int transitDays) {
    this.transitDays = transitDays;
  }

  public Charge getCharge() {
    return charge;
  }

  public void setCharge(Charge charge) {
    this.charge = charge;
  }

  public List<Charge> getCharges() {
    return charges;
  }

  public void setCharges(List<Charge> charges) {
    this.charges = charges;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public double getLargePackageSurcharges() {
    return largePackageSurcharges;
  }

  public void setLargePackageSurcharges(double largePackageSurcharges) {
    this.largePackageSurcharges = largePackageSurcharges;
  }

  public boolean isUps_switch_res_comm() {
    return ups_switch_res_comm;
  }

  public void setUps_switch_res_comm(boolean ups_switch_res_comm) {
    this.ups_switch_res_comm = ups_switch_res_comm;
  }

  public boolean isUps_extended_area() {
    return ups_extended_area;
  }

  public void setUps_extended_area(boolean ups_extended_area) {
    this.ups_extended_area = ups_extended_area;
  }

  public int getTransitDays() {
    return transitDays;
  }

  public double getTariffRate() {
    return tariffRate;
  }

  public void setTariffRate(double tariffRate) {
    this.tariffRate = tariffRate;
  }

  public String getLoginURL() {
    return loginURL;
  }

  public void setLoginURL(String loginURL) {
    this.loginURL = loginURL;
  }

  public double getTariffFuelSurcharge() {
    return tariffFuelSurcharge;
  }

  public void setTariffFuelSurcharge(double tariffFuelSurcharge) {
    this.tariffFuelSurcharge = tariffFuelSurcharge;
  }

  public CustomerCarrier getCustomerCarrier() {
    return customerCarrier;
  }

  public void setCustomerCarrier(CustomerCarrier customerCarrier) {
    this.customerCarrier = customerCarrier;
  }

  public Integer getMarkupPercentage() {
    return markupPercentage;
  }

  public void setMarkupPercentage(Integer markupPercentage) {
    this.markupPercentage = markupPercentage;
  }

  public String getMarkupTypeText() {
    return markupTypeText;
  }

  public void setMarkupTypeText(String markupTypeText) {
    this.markupTypeText = markupTypeText;
  }

  public Integer getMarkupType() {
    return markupType;
  }

  public void setMarkupType(Integer markupType) {
    this.markupType = markupType;
  }

  public String getBillWeightUOM() {
    return billWeightUOM;
  }

  public void setBillWeightUOM(String billweightUOM) {
    this.billWeightUOM = billweightUOM;
  }

  public long getOriginalServiceId() {
    return originalServiceId;
  }

  public void setOriginalServiceId(long originalServiceId) {
    this.originalServiceId = originalServiceId;
  }

  public Service getNewService() {
    return newService;
  }

  public void setNewService(Service newService) {
    this.newService = newService;
  }

  public String getBookingKey() {
    return bookingKey;
  }

  public void setBookingKey(String bookingKey) {
    this.bookingKey = bookingKey;
  }

  public double getStaticFuel() {
    return staticFuel;
  }

  public void setStaticFuel(double staticFuel) {
    this.staticFuel = staticFuel;
  }

  public int getTransitDaysMin() {
    return transitDaysMin;
  }

  public void setTransitDaysMin(int transitDaysMin) {
    this.transitDaysMin = transitDaysMin;
  }

  public double getRatedAsWeight() {
    return ratedAsWeight;
  }

  public void setRatedAsWeight(double ratedAsWeight) {
    this.ratedAsWeight = ratedAsWeight;
  }

  public double getMarkupFlat() {
    return markupFlat;
  }

  public void setMarkupFlat(double markupFlat) {
    this.markupFlat = markupFlat;
  }

  public static void copyRating(Rating srcRating, Rating targetRating) {
    targetRating.setBillWeight(srcRating.getBillWeight());
    targetRating.setCustomerCarrier(srcRating.getCustomerCarrier());
    targetRating.setServiceId(srcRating.getServiceId());
    targetRating.setInstanceAPIName(srcRating.getInstanceAPIName());
    targetRating.setCarrierId(srcRating.getCarrierId());
    targetRating.setCurrency(srcRating.getCurrency());
    targetRating.setCarrierName(srcRating.getCarrierName());
    targetRating.setServiceName(srcRating.getServiceName());
    targetRating.setLoginURL(srcRating.getLoginURL());
    targetRating.setTransitDays(srcRating.getTransitDays());

    for (Charge c : srcRating.getCharges()) {
      Charge targetCharge = new Charge();
      Charge.copyCharge(c, targetCharge);
      targetRating.getCharges().add(targetCharge);
    }
  }

  public long getZoneStructureId() {
    return zoneStructureId;
  }

  public void setZoneStructureId(long zoneStructureId) {
    this.zoneStructureId = zoneStructureId;
  }

  public long getSlaveServiceId() {
    return slaveServiceId;
  }

  public void setSlaveServiceId(long slaveServiceId) {
    this.slaveServiceId = slaveServiceId;
  }

  public static Comparator PriceComparator = new Comparator() {
    public int compare(Object arg0, Object arg1) {
      double cus1 = ((Rating) arg0).getTotal();
      double cus2 = ((Rating) arg1).getTotal();

      if (cus1 > cus2)
        return 1;
      else if (cus1 < cus2)
        return -1;
      else
        return 0;

    }
  };

}
