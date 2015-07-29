package com.meritconinc.shiplinx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.model.Address;
import com.meritconinc.shiplinx.model.CarrierChargeCode;
import com.meritconinc.shiplinx.model.ChargeGroup;
import com.meritconinc.shiplinx.model.LtlPoundRate;
import com.meritconinc.shiplinx.model.LtlSkidRate;
import com.meritconinc.shiplinx.model.Markup;
import com.meritconinc.shiplinx.model.Zone;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;
import com.meritconinc.shiplinx.model.EshipplusCarrierFilter;
public class MarkupManagerDAOImpl extends SqlMapClientDaoSupport implements MarkupManagerDAO {

  public List<Markup> getMarkupListForCustomer(Markup markup) {
    // TODO Auto-generated method stub
    if (markup != null) {
      // Map<String, Object> paramObj = new HashMap<String, Object>(5);
      // paramObj.put("customerId", markup.getCustomerId());
      // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
      // if (markup.getFromCountryCode() != null)
      // paramObj.put("fromCountryCode", markup.getFromCountryCode());
      // if (markup.getToCountryCode() != null)
      // paramObj.put("toCountryCode", markup.getToCountryCode());
      // if (markup.getServiceId() != null)
      // paramObj.put("serviceId", markup.getServiceId());
    	/*if((markup.getBusinessId()!=null ) && (markup.getBusinessIds()==null|| markup.getBusinessIds().size()<0) ){
    	      List<Long> businessIds=new ArrayList<Long>();
    	      businessIds.add(markup.getBusinessId());
    	      markup.setBusinessIds(businessIds);
    	      
    	     }*/
    	
    	String fromCountry = markup.getFromCountryCode();
    	String toCountry = markup.getToCountryCode();
    	
    	List<String> countries = new ArrayList<String>();
    	countries.add(ShiplinxConstants.COUNTRY_ANY);
    	countries.add(ShiplinxConstants.COUNTRY_CA);
    	countries.add(ShiplinxConstants.COUNTRY_US);
    	countries.add(ShiplinxConstants.COUNTRY_MX);
    	countries.add(ShiplinxConstants.COUNTRY_CN);
    	
    	if(fromCountry != null && !fromCountry.isEmpty() && ShiplinxConstants.COUNTRY_ANY.equalsIgnoreCase(fromCountry)){
    		markup.setFromCountries(countries);
    	}
    	else if(fromCountry != null && !fromCountry.isEmpty() && !ShiplinxConstants.COUNTRY_ANY.equalsIgnoreCase(fromCountry)){
    		List<String> fromCountryCode = new ArrayList<String>();
    		fromCountryCode.add(fromCountry);
    		markup.setFromCountries(fromCountryCode);
    	}
    	
    	if(toCountry != null && !toCountry.isEmpty() && ShiplinxConstants.COUNTRY_ANY.equalsIgnoreCase(toCountry)){
    		markup.setToCountries(countries);
    	}
    	else if(toCountry != null && !toCountry.isEmpty() && !ShiplinxConstants.COUNTRY_ANY.equalsIgnoreCase(toCountry)){
    		List<String> toCountryCode = new ArrayList<String>();
    		toCountryCode.add(toCountry);
    		markup.setToCountries(toCountryCode);
    	}
      List<Markup> markupList = (List<Markup>) getSqlMapClientTemplate().queryForList(
          "findMarkupListForCustomer", markup);
      return markupList;
    }
    return null;
  }

  public Double[] getFlatMarkups() {
    // TODO Auto-generated method stub
    List<Markup> markupList = (List) getSqlMapClientTemplate().queryForList("getFlatMarkups");
    if (markupList != null) {
      Double[] fMarkups = new Double[markupList.size()];
      int i = 0;
      for (Markup m : markupList) {
        if (m.getMarkupFlat() != null)
          fMarkups[i++] = m.getMarkupFlat();
      }

      return fMarkups;
    }
    return null;
  }

  public Integer[] getPercentageMarkups() {
    // TODO Auto-generated method stub
    List<Markup> markupList = (List) getSqlMapClientTemplate().queryForList("getPercentageMarkups");
    if (markupList != null) {
      Integer[] pMarkups = new Integer[markupList.size()];
      int i = 0;
      for (Markup m : markupList) {
        if (m.getMarkupPercentage() != null)
          pMarkups[i++] = m.getMarkupPercentage();
      }

      return pMarkups;
    }
    return null;
  }

  @Override
  public void deleteMarkup(Markup markup) {
    // TODO Auto-generated method stub
    if (markup != null) {
      Map<String, Object> deleteMarkupParamObj = new HashMap<String, Object>(5);
      deleteMarkupParamObj.put("customerId", markup.getCustomerId());
      deleteMarkupParamObj.put("serviceId", markup.getServiceId());
      deleteMarkupParamObj.put("fromCountryCode", markup.getFromCountryCode());
      deleteMarkupParamObj.put("toCountryCode", markup.getToCountryCode());
      deleteMarkupParamObj.put("businessId", markup.getBusinessId());

      getSqlMapClientTemplate().delete("deleteMarkup", deleteMarkupParamObj);
    }
  }

  @Override
  public void addMarkup(Markup markup) {
    // TODO Auto-generated method stub
    if (markup != null) {
      if (markup.getFromWeight() == null)
        markup.setFromWeight(0.0);
      if (markup.getToWeight() == null)
        markup.setToWeight(0.0);
      getSqlMapClientTemplate().insert("addMarkup", markup);
    }
  }

  @Override
  public void updateMarkup(Markup markup) {
    // TODO Auto-generated method stub
    if (markup != null) {
      Map<String, Object> updateMarkupParamObj = new HashMap<String, Object>(10);
      updateMarkupParamObj.put("customerId", markup.getCustomerId());
      updateMarkupParamObj.put("serviceId", markup.getServiceId());
      updateMarkupParamObj.put("fromCountryCode", markup.getFromCountryCode());
      updateMarkupParamObj.put("toCountryCode", markup.getToCountryCode());
      updateMarkupParamObj.put("businessId", markup.getBusinessId());
      updateMarkupParamObj.put("muPercent", markup.getMarkupPercentage());
      updateMarkupParamObj.put("muFlat", markup.getMarkupFlat());
      updateMarkupParamObj.put("disabled", markup.getDisabled());
      updateMarkupParamObj.put("fromWeight", markup.getFromWeight());
      updateMarkupParamObj.put("toWeight", markup.getToWeight());
      updateMarkupParamObj.put("toVariable", markup.getVariable());
      getSqlMapClientTemplate().insert("updateMarkup", updateMarkupParamObj);
    }
  }

  @Override
  public List<Markup> getMarkupListForUniqueMarkup(Markup markup) {
    // TODO Auto-generated method stub
    if (markup != null) {
      // Map<String, Object> paramObj = new HashMap<String, Object>(5);
      // paramObj.put("customerId", markup.getCustomerId());
      // paramObj.put("businessId", UserUtil.getMmrUser().getBusinessId());
      // if (markup.getFromCountryCode() != null)
      // paramObj.put("fromCountryCode", markup.getFromCountryCode());
      // if (markup.getToCountryCode() != null)
      // paramObj.put("toCountryCode", markup.getToCountryCode());
      // if (markup.getServiceId() != null)
      // paramObj.put("serviceId", markup.getServiceId());

      return getSqlMapClientTemplate().queryForList("findMarkupListForUniqueMarkup", markup);
    }
    return null;
  }

  @Override
  public void deleteCustomerMarkup(Long customerId, long businessId) {
    // TODO Auto-generated method stub
    if (customerId != null) {
      Map<String, Object> deleteMarkupParamObj = new HashMap<String, Object>(1);
      deleteMarkupParamObj.put("customerId", customerId);
      deleteMarkupParamObj.put("businessId", businessId);
      getSqlMapClientTemplate().delete("deleteCustomerMarkup", deleteMarkupParamObj);
    }
  }

  public void disableOrEnableServicesForCustomer(long customerId, long[] serviceIds, boolean disable) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("customerId", customerId);
    paramObj.put("serviceIds", serviceIds);
    if (disable)
      paramObj.put("disable", new Boolean(true));
    else
      paramObj.put("disable", new Boolean(false));
    getSqlMapClientTemplate().delete("disableServicesForCustomer", paramObj);

  }

  public List<CarrierChargeCode> searchCharges(CarrierChargeCode carrierChargeCode) {
    List<CarrierChargeCode> ccclst = (List) getSqlMapClientTemplate().queryForList(
        "getChargesByCarrierId", carrierChargeCode);
    return ccclst;
  }

  public void deleteCharges(long chargeId) {
    Map<String, Object> paramObj = new HashMap<String, Object>(1);
    paramObj.put("chargeId", chargeId);
    getSqlMapClientTemplate().delete("deleteChargeById", paramObj);
  }

  public List<CarrierChargeCode> getCharges(CarrierChargeCode carrierChargeCode) {
    List<CarrierChargeCode> ccclst = (List) getSqlMapClientTemplate().queryForList(
        "getChargesByChargeId", carrierChargeCode);
    return ccclst;
  }

  public List<ChargeGroup> getChargeGroups() {
    List<ChargeGroup> cglst = (List) getSqlMapClientTemplate().queryForList("getChargeGroups");
    return cglst;
  }

  public void addOrUpdateCharge(CarrierChargeCode carrierChargeCode, boolean add) {
    if (add)
      getSqlMapClientTemplate().delete("CreateCharge", carrierChargeCode);
    else
      getSqlMapClientTemplate().delete("EditCharge", carrierChargeCode);
  }

  @Override
  public void addPoundRateRecord(LtlPoundRate poundRate) {
    try {
      getSqlMapClientTemplate().insert("addLtlPoundRate", poundRate);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e);
    }
  }

  @Override
  public void addSkidRateRecord(LtlSkidRate skidRate) {
    try {
      getSqlMapClientTemplate().insert("addLtlSkidRate", skidRate);
    } catch (Exception e) {
      e.printStackTrace();
      logger.error(e);
    }
  }

  @Override
  public void deletePoundRateRecord(LtlPoundRate poundRate) {
    getSqlMapClientTemplate().delete("deleteLtlPoundRate", poundRate);
  }

  @Override
  public void deleteSkidRateRecord(LtlSkidRate skidRate) {
    getSqlMapClientTemplate().delete("deleteLtlSkidRate", skidRate);
  }

  @SuppressWarnings("all")
  @Override
  public LtlPoundRate getPoundRate(LtlPoundRate poundRateTobeSearched) {
    List<LtlPoundRate> lprList = (List) getSqlMapClientTemplate().queryForList("getLtlPoundRate",
        poundRateTobeSearched);
    if (lprList != null && lprList.size() > 0)
      return lprList.get(0);
    return null;
  }

  @SuppressWarnings("all")
  @Override
  public LtlPoundRate getPoundRate(LtlPoundRate poundRateTobeSearched, Double totalWeight) {
    if (totalWeight != null)
      poundRateTobeSearched.setRangeFrom(totalWeight.intValue()); // Setting it in RangeFrom
                                                                  // property to compare in ranges
    List<LtlPoundRate> lprList;                                                             // property to compare in ranges
        if(poundRateTobeSearched.getServiceId()!=0){ 
        lprList = (List) getSqlMapClientTemplate().queryForList( "getLtlPoundRateByRange", poundRateTobeSearched);
            }
            else{
                lprList = (List) getSqlMapClientTemplate().queryForList(
                        "getLtlPoundRateByRange1", poundRateTobeSearched);
            }
    if (lprList != null && lprList.size() > 0)
      return lprList.get(0);
    return null;
  }

  @SuppressWarnings("all")
  @Override
  public LtlSkidRate getSkidRate(LtlSkidRate skidRateTobeSearched) {
    List<LtlSkidRate> lsrList = (List) getSqlMapClientTemplate().queryForList("getLtlSkidRate",
        skidRateTobeSearched);
    if (lsrList != null && lsrList.size() > 0)
    	//return  lsrList;
      return lsrList.get(0);
    return null;
  }

  @SuppressWarnings("all")
  @Override
  public Zone findZone(Long zoneStructureId, Address address) {
    if (zoneStructureId != null && address != null) {
      Map<String, Object> paramObj = new HashMap<String, Object>(5);
      paramObj.put("zoneStructureId", zoneStructureId);
      //paramObj.put("postalCode", address.getPostalCode());
      paramObj.put("provinceCode", address.getProvinceCode());
      paramObj.put("countryCode", address.getCountryCode());
      paramObj.put("cityName", address.getCity());

      // List<Zone> listZone = (List) getSqlMapClientTemplate().queryForList("findZoneByPostalCode",
      // paramObj);
      // if (listZone == null || listZone.size() == 0) {
      List<Zone> listZone = (List) getSqlMapClientTemplate().queryForList("findZoneByCity",
          paramObj);
      if (listZone != null && listZone.size() > 0)
        return listZone.get(0);
      // } else {
      // return listZone.get(0);
      // }
    }
    return new Zone();
  }

  public Zone findZoneByPostalCode(Long zoneStructureId, Address address) {
    if (zoneStructureId != null && address != null) {
      Map<String, Object> paramObj = new HashMap<String, Object>(5);
      paramObj.put("zoneStructureId", zoneStructureId);
      paramObj.put("countryCode", address.getCountryCode());
      paramObj.put("postalCode", address.getPostalCode());
      // List<Zone> listZone = (List) getSqlMapClientTemplate().queryForList("findZoneByPostalCode",
      // paramObj);
      // if (listZone == null || listZone.size() == 0) {
      List<Zone> listZone = (List) getSqlMapClientTemplate().queryForList("findZoneByPostalCode",
          paramObj);
      if (listZone != null && listZone.size() > 0)
        return listZone.get(0);
      // } else {
      // return listZone.get(0);
      // }
    }
    return null;
  }

  @Override
  public Zone addZone(Zone zone) {
    if (zone != null) {
      Long id = (Long) getSqlMapClientTemplate().insert("addZone", zone);
      zone.setZoneId(id);
    }

    return zone;
  }

  public CarrierChargeCode getChargesByCode(CarrierChargeCode carrierChargeCode) {
    CarrierChargeCode carrierCharge = (CarrierChargeCode) getSqlMapClientTemplate().queryForObject(
        "getChargesByCode", carrierChargeCode);
    if (carrierCharge != null) {
      return carrierCharge;
    }
    return null;
  }

  // comitted for issue 137

    /*
     * public List<LtlSkidRate> groupingLTLSkidRate(long serviceId, String fromZoneName, String
     * toZoneName) { Map<String, Object> paramObj = new HashMap<String, Object>();
     * paramObj.put("serviceId", serviceId); paramObj.put("fromZone", fromZoneName);
     * paramObj.put("toZone", toZoneName); List<LtlSkidRate> ltlSkidRate = (List)
     * getSqlMapClientTemplate().queryForList( "groupingLTLSkidRate", paramObj); if (ltlSkidRate !=
     * null) { return ltlSkidRate; } return null; }
     */

   public List<LtlSkidRate> groupingLTLSkidRate(long serviceId, long maxLength, long maxWidth,
       long maxHeight, long maxWeight, String fromZoneName, String toZoneName) {
      Map<String, Object> paramObj = new HashMap<String, Object>();
      paramObj.put("serviceId", serviceId);
      paramObj.put("maxHeight", maxHeight);
      paramObj.put("maxLength", maxLength);
      paramObj.put("maxWeight", maxWeight);
      paramObj.put("maxWidth", maxWidth);
    paramObj.put("fromZone", fromZoneName);
    paramObj.put("toZone", toZoneName);
    List<LtlSkidRate> ltlSkidRate = (List) getSqlMapClientTemplate().queryForList(
        "groupingLTLSkidRate", paramObj);
    if (ltlSkidRate != null) {
      return ltlSkidRate;
    }
    return null;
  }

  @Override
  public List<LtlPoundRate> groupingLTLPoundRate(long serviceId, LtlPoundRate ltlPoundRate) {

    Map<String, Object> paramObj = new HashMap<String, Object>();
    paramObj.put("serviceId", serviceId);
    paramObj.put("fromZone", ltlPoundRate.getFromZone());
    paramObj.put("toZone", ltlPoundRate.getToZone());
    paramObj.put("rangeFrom", ltlPoundRate.getRangeFrom());
    paramObj.put("rangeTo", ltlPoundRate.getRangeTo());
    paramObj.put("freightClass", ltlPoundRate.getFreightClass());
    paramObj.put("customerId", ltlPoundRate.getCustomerId());

    List<LtlPoundRate> groupLtlPoundRate = (List) getSqlMapClientTemplate().queryForList(
        "groupingLTLPoundRate", paramObj);
    if (groupLtlPoundRate != null) {
      return groupLtlPoundRate;
    }
    return null;
  }
  public boolean getMarkupListForCustomerAndCarrier(Markup markup) {
	    // TODO Auto-generated method stub
	    if (markup != null) {
	      List<Markup> markupList = (List<Markup>) getSqlMapClientTemplate().queryForList(
	          "getMarkupListForCustomerAndCarrier", markup);
	      if (markupList.size() > 0 || !markupList.isEmpty()) {
	        return true;
	      }
	      return false;
	    }
	    return false;
	  }

	  public List<Markup> getMarkupListForCustomerForFilter(Markup markup) {
	    // TODO Auto-generated method stub
	    if (markup != null) {
	      List<Markup> markupList = (List<Markup>) getSqlMapClientTemplate().queryForList(
	          "getMarkupListForCustomerForFilter", markup);
	      return markupList;
	    }
	    return null;
	  }
	  
	  public List<Markup> getMarkupList(Markup markup) {
		  	    // TODO Auto-generated method stub
		  	    if (markup != null) {
		        List<Markup> markupList = (List<Markup>) getSqlMapClientTemplate().queryForList(
		  	          "findMarkupList", markup);
		  	      return markupList;
		      }
		      return null;
		  	  }
	  
	  public Markup findBaseMarkup(Markup markup){
		  	  if (markup != null) {
		  		  Markup baseMarkup = (Markup) getSqlMapClientTemplate().queryForObject(
		  		          "findBaseMarkup", markup);
		  		      return baseMarkup;
		  		    }
		  		    return null;
		    }

	@Override
	public boolean isCustomerMarkupByDisabled(long customerId) {
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
	    paramObj.put("customerId", customerId);
	    Integer count = (Integer) getSqlMapClientTemplate().queryForObject("isCustomerMarkupByDisabled",
	        paramObj);
	    return (count > 0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Markup> getAllMarkupsForCustomer(Long customerId,long businessId){
		Map<String, Object> paramObj = new HashMap<String, Object>(1);
		paramObj.put("customerId", customerId);
		paramObj.put("businessId", businessId);
		return (List<Markup>)getSqlMapClientTemplate().queryForList("getMarkupListForCustomerAndCarrier1", paramObj);
		
	}
	
	@Override
			public void insertCustomerMarkupByBusiness(long newBusinessId,
						long defaultBusinessId) {
					Map map = new HashMap();
				    map.put("newBusinessId", newBusinessId);
				    map.put("defaultBusinessId", defaultBusinessId);
				    try {
				        getSqlMapClientTemplate().insert("addCustomerMarkupbyBusiness", map);
				      } catch (Exception e) {
				        // log.debug("-----Exception-----"+e);
				        e.printStackTrace();
				      }
					
				}
			
				@Override
			public void insertLtlPoundrateByBusiness(long newBusinessId,
						long defaultBusinessId) {
					Map map = new HashMap();
				    map.put("newBusinessId", newBusinessId);
				    map.put("defaultBusinessId", defaultBusinessId);
				    try {
			        getSqlMapClientTemplate().insert("addLtlPoundratebyBusiness", map);
				      } catch (Exception e) {
			        // log.debug("-----Exception-----"+e);
				        e.printStackTrace();
				      }
					
				}
			
				@Override
				public void insertLtlSkidRateByBusiness(long newBusinessId,
						long defaultBusinessId) {
					Map map = new HashMap();
				    map.put("newBusinessId", newBusinessId);
				    map.put("defaultBusinessId", defaultBusinessId);
				    try {
				        getSqlMapClientTemplate().insert("addLtlSkidRatebyBusiness", map);
				      } catch (Exception e) {
				        // log.debug("-----Exception-----"+e);
				        e.printStackTrace();
				      }
					
				}
		
		
				@Override
				public List<Zone> getOverallZones(String City,Long ZoneStructureId,String Country, String Province) {
						Map<String, Object> paramObj = new HashMap<String, Object>();
					    paramObj.put("City", City);
					    paramObj.put("ZoneStructureId", ZoneStructureId);
					    paramObj.put("Country", Country);
					    					    paramObj.put("Province", Province);
					    @SuppressWarnings("unchecked")
						List<Zone> overAllZones = getSqlMapClientTemplate().queryForList(
					        "getoverAllZones", paramObj);
					    if (overAllZones != null) {
					      return overAllZones;
					    }
					    return null;
					}

	//public List<CarrierChargeCode> getChargesByChargeCodeAndCarrier(
				public CarrierChargeCode getChargesByChargeCodeAndCarrier(
			long carrierId, String chargeCode, long customerId) {

		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("carrierId", carrierId);
		paramObj.put("chargeCode", chargeCode);
		paramObj.put("customerId", customerId);
		List<CarrierChargeCode> groupLtlPoundRate = (List) getSqlMapClientTemplate()
				.queryForList("getChargesByChargeCodeAndCarrier", paramObj);
		/*if (groupLtlPoundRate != null) {
			return groupLtlPoundRate;*/
		if (groupLtlPoundRate == null || groupLtlPoundRate.size()==0) {
						return null;
		}
		//return null;
		else{
						for(CarrierChargeCode carrierChargeCodeTemp : groupLtlPoundRate){
						if(carrierChargeCodeTemp.getCustomerId()>0 && carrierChargeCodeTemp.getCustomerId()==customerId){
								return carrierChargeCodeTemp;
							}
						}
					}
					return groupLtlPoundRate.get(0);
	}

	public List<Markup> findMarkupListForUniqueMarkupUsingCostRange(
			Markup markup) {
		// TODO Auto-generated method stub
		if (markup != null) {
			// Map<String, Object> paramObj = new HashMap<String, Object>(5);
			// paramObj.put("customerId", markup.getCustomerId());
			// paramObj.put("businessId",
			// UserUtil.getMmrUser().getBusinessId());
			// if (markup.getFromCountryCode() != null)
			// paramObj.put("fromCountryCode", markup.getFromCountryCode());
			// if (markup.getToCountryCode() != null)
			// paramObj.put("toCountryCode", markup.getToCountryCode());
			// if (markup.getServiceId() != null)
			// paramObj.put("serviceId", markup.getServiceId());

			return getSqlMapClientTemplate().queryForList(
					"findMarkupListForUniqueMarkupUsingCostRange", markup);
		}
		return null;
	}

	public boolean getEshipCarriersbyCustomerId(String carrierScac,
			Long customerId) {
		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("customerId", customerId);
		paramObj.put("carrierScac", carrierScac);
		try {
			List<EshipplusCarrierFilter> eShipCustomerCarrier = (List<EshipplusCarrierFilter>) getSqlMapClientTemplate()
					.queryForList("getEshipCarriersbyCustomerIdandScac",
							paramObj);
			if (eShipCustomerCarrier.size() > 0) {
				for (EshipplusCarrierFilter EshipCustomerCarrier : eShipCustomerCarrier) {
					if (eShipCustomerCarrier.get(0).getDisabled() == 1) {
						return false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public List<CarrierChargeCode> getChargesByCarrierIdAndGroupCode(
			long carrierId, long chargeGroupId) {

		Map<String, Object> paramObj = new HashMap<String, Object>();
		paramObj.put("carrierId", carrierId);
		paramObj.put("chargeGroupId", chargeGroupId);
		List<CarrierChargeCode> groupLtlPoundRate = (List) getSqlMapClientTemplate()
				.queryForList("getChargesByCarrierIdAndGroupCode", paramObj);
		if (groupLtlPoundRate != null) {
			return groupLtlPoundRate;
		}
		return null;
	}
	public long ismarkupavailable(long businessId){
						long i=0;
						try{
							
							 i = (Long)getSqlMapClientTemplate().queryForObject("ismarkupavailable", businessId);
				
						}catch(Exception e){
							e.printStackTrace();
						}
						if(i>0){
							return 1;
						}else{
							return 0;
						}
					}

}
