	package com.meritconinc.shiplinx.service;

import java.io.InputStream;
import java.util.List;

import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.Charge;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.LtlPoundRate;
import com.meritconinc.shiplinx.model.LtlSkidRate;
import com.meritconinc.shiplinx.model.Markup;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.model.ShippingOrder;
import com.meritconinc.shiplinx.model.Zone;

public interface MarkupManager {
  public List<Markup> getMarkupListForCustomer(Markup markup);

  public Double[] getFlatMarkups();

  public Integer[] getPercentageMarkups();

  public void deleteMarkup(Markup markup);

  // public void addMarkup(Markup markup) throws MarkupAlreadyExistsException;
  public Markup addMarkup(Markup markup);

  public void applyToAllCustomersMarkup(Markup markup);

  public void updateMarkup(Markup markup);

  public void calculatMarkup(ShippingOrder shipment, Charge charge);

  public Markup getUniqueMarkup(Markup markup);

  public Markup getMarkupObj(ShippingOrder shipment);

  public Double applyMarkup(ShippingOrder shipment, Charge charge, boolean setShipmentMarkup);

  public void copyCustomerMarkup(Long sourceCusId, Long targetCusId, long businessId);

  public void disableOrEnableAllServicesForCustomerAndCarrier(long customerId, long carrierId,
      boolean disable);

  public List<CarrierChargeCode> searchCharges(CarrierChargeCode carrierChargeCode);

  public void deleteCharges(long chargeId);

  public List<CarrierChargeCode> getCharges(CarrierChargeCode carrierChargeCode);

  public List<ChargeGroup> getChargeGroups();

  public void addOrUpdateCharge(CarrierChargeCode carrierChargeCode, boolean add);

  public Service uploadRateTemplateFile(Service service, Long customerId, Long busId,
      String uploadFileName, InputStream is, boolean b);

  public CarrierChargeCode getChargesByCode(CarrierChargeCode carrierChargeCode);

  public List<LtlSkidRate> groupingLTLSkidRate(long serviceId, long maxLength, long maxWidth,
     long maxHeight, long maxWeight, String fromZoneName, String toZoneName);

  public Zone findZone(Long zoneStructureId, Address address);

  public LtlPoundRate getPoundRate(LtlPoundRate poundRateTobeSearched, Double totalWeight);

  public List<LtlPoundRate> groupingLTLPoundRate(long serviceId, LtlPoundRate ltlPoundRate);

public LtlSkidRate getSkidRate(LtlSkidRate skidRateTobeSearched);
public boolean getMarkupListForCustomerAndCarrier(Markup markup);

  public List<Markup> getMarkupListForCustomerForFilter(Markup markup);
  public List<Markup> getMarkupList(Markup markup);
  public Markup getUniqueMarkupList(Markup markup);
  public Markup findBaseMarkup(Markup markup);
  public boolean isCustomerMarkupByDisabled(long customerId);
  
  public List<Markup> getAllMarkupsForCustomer(Long customerId, long l);
  
  public List<Zone> getOverallZones(String City, Long ZoneStructureId, String Country, String Province);
}
