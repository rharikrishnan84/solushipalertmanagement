package com.meritconinc.shiplinx.dao;

import java.util.List;

import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.BusinessMarkup;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.LtlPoundRate;
import com.meritconinc.shiplinx.model.LtlSkidRate;
import com.meritconinc.shiplinx.model.Markup;
import com.meritconinc.shiplinx.model.Zone;

public interface MarkupManagerDAO {
  public List<Markup> getMarkupListForCustomer(Markup markup);

  public Double[] getFlatMarkups();

  public Integer[] getPercentageMarkups();

  public void deleteMarkup(Markup markup);

  public void addMarkup(Markup markup);

  public void updateMarkup(Markup markup);

  public List<Markup> getMarkupListForUniqueMarkup(Markup markup);

  public void deleteCustomerMarkup(Long targetCusId, long businessId);

  public void disableOrEnableServicesForCustomer(long customerId, long[] serviceIds, boolean disable);

  public List<CarrierChargeCode> searchCharges(CarrierChargeCode carrierChargeCode);

  public void deleteCharges(long chargeId);

  public List<CarrierChargeCode> getCharges(CarrierChargeCode carrierChargeCode);

  public List<ChargeGroup> getChargeGroups();

  public void addOrUpdateCharge(CarrierChargeCode carrierChargeCode, boolean add);

  public void addPoundRateRecord(LtlPoundRate poundRate);

  public void addSkidRateRecord(LtlSkidRate skidRate);

  public void deletePoundRateRecord(LtlPoundRate poundRate);

  public void deleteSkidRateRecord(LtlSkidRate skidRate);

  public LtlPoundRate getPoundRate(LtlPoundRate poundRateTobeSearched);

  public LtlPoundRate getPoundRate(LtlPoundRate poundRateTobeSearched, Double totalWeight);

  public LtlSkidRate getSkidRate(LtlSkidRate skidRateTobeSearched);
  //public List<LtlSkidRate> getSkidRate(LtlSkidRate skidRateTobeSearched);

  public Zone findZone(Long zoneStructureId, Address address);

  public Zone findZoneByPostalCode(Long zoneStructureId, Address address);

  public Zone addZone(Zone zone);

  public CarrierChargeCode getChargesByCode(CarrierChargeCode carrierChargeCode);

  public List<LtlSkidRate> groupingLTLSkidRate(long serviceId, long maxLength, long maxWidth,
      long maxHeight, long maxWeight, String fromZoneName, String toZoneName);


  public List<LtlPoundRate> groupingLTLPoundRate(long serviceId, LtlPoundRate ltlPoundRate);
  
  public boolean getMarkupListForCustomerAndCarrier(Markup markup);

  public List<Markup> getMarkupListForCustomerForFilter(Markup markup);
  public List<Markup> getMarkupList(Markup markup);
  public Markup findBaseMarkup(Markup markup);
  public boolean isCustomerMarkupByDisabled(long customerId);
  
  public List<Markup> getAllMarkupsForCustomer(Long customerId, long businessId);
  public void insertCustomerMarkupByBusiness(long newBusinessId,long defaultBusinessId);
        
        public void insertLtlPoundrateByBusiness(long newBusinessId,long defaultBusinessId);
        
        public void insertLtlSkidRateByBusiness(long newBusinessId,long defaultBusinessId);
        
	public List<Zone> getOverallZones(String City, Long ZoneStructureId,
			String Country, String Province);

	public boolean getEshipCarriersbyCustomerId(String carrierScac,
			Long customerId);

	public List<CarrierChargeCode> getChargesByCarrierIdAndGroupCode(
			long carrierId, long chargeGroupId);

	//public List<CarrierChargeCode> getChargesByChargeCodeAndCarrier(
	public CarrierChargeCode getChargesByChargeCodeAndCarrier(
			long carrierId, String chargeCode, long customerId);

	public List<Markup> findMarkupListForUniqueMarkupUsingCostRange(
			Markup markup);
	public boolean isMarkupDisabled(long businessId);
	public BusinessMarkup getuniqueBusinessMarkup(
			BusinessMarkup businessMarkup);
			
	public List<BusinessMarkup> getAllBusinessMarkupsForCustomer(
			BusinessMarkup businessMarkup);
				
	public List<BusinessMarkup> addBusinessMarkup(BusinessMarkup markup);
	
	List<BusinessMarkup> deleteBusinessMarkup(BusinessMarkup markup);
	
	List<BusinessMarkup> updateBusinessMarkup(BusinessMarkup markup);
	
	public BusinessMarkup getuniqueBusinessToBusinessMarkup(BusinessMarkup businessMarkup);
	
	public BusinessMarkup getAdditionalInfoForBusinessMarkup(BusinessMarkup businessMarkup2);
	
	public List<BusinessMarkup> getBusinessMarkups(BusinessMarkup businessMarkup2);
	
	public List<BusinessMarkup> getAllBusinessMarkups(BusinessMarkup businessMarkup);
	
	public BusinessMarkup getUniqueCustomBusinessMarkup(BusinessMarkup businessMarkup);
}
