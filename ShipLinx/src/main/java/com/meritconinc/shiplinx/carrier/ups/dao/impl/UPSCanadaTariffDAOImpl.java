package com.meritconinc.shiplinx.carrier.ups.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.carrier.ups.dao.UPSCanadaTariffDAO;
import com.meritconinc.shiplinx.carrier.ups.model.UPSAccCharges;
import com.meritconinc.shiplinx.carrier.ups.model.UPSCANEAS;
import com.meritconinc.shiplinx.carrier.ups.model.UPSDASZipsUS;
import com.meritconinc.shiplinx.carrier.ups.model.UPSTariff;
import com.meritconinc.shiplinx.carrier.ups.model.UPSZoneDiscount;
import com.meritconinc.shiplinx.model.Service;
import com.meritconinc.shiplinx.utils.FormattingUtil;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class UPSCanadaTariffDAOImpl  extends SqlMapClientDaoSupport  implements UPSCanadaTariffDAO {
	
	private static Logger log = Logger.getLogger(UPSCanadaTariffDAOImpl.class); 
	
	
	public UPSTariff getTariffRecord(UPSTariff tariff){ 
		
		if(tariff.getZone()==null){
			log.error("Need zone information to determine UPS Canada tariff rates");
			return null;
		}
		
		double maxWeightForService = (Double)getSqlMapClientTemplate().queryForObject("upsFindMaxWeightForZone", tariff.getZone());

		if(tariff.getWeightLB() <= maxWeightForService){
			Double rate = (Double)getSqlMapClientTemplate().queryForObject("getUPSTariffRate", tariff);
			tariff.setRate(rate);
			return tariff;
		}
		
		double queriedWeight = tariff.getWeightLB();
		double overWeightBy = queriedWeight - maxWeightForService; //steps of 1 lb for over weight

		tariff.setWeightLB(maxWeightForService);		
		Double rateForMaxWeight = (Double)getSqlMapClientTemplate().queryForObject("getUPSTariffRate", tariff);	
		
		tariff.setWeightLB(new Double(-1));
		Double rateForOverWeight = (Double)getSqlMapClientTemplate().queryForObject("getUPSTariffRate", tariff) * overWeightBy;	
		
		tariff.setWeightLB(queriedWeight);
		tariff.setRate((FormattingUtil.add(rateForMaxWeight, rateForOverWeight)).doubleValue());
		
		return tariff;
		
	}
	
	public UPSAccCharges getAccCharge(UPSAccCharges upsAccCharge) {
		logger.debug("Accessorial search for business / chargeCode / chargeCode2 / country : " + upsAccCharge.getBusinessId() + " / " + upsAccCharge.getChargeCode() + " / " + upsAccCharge.getChargeCodeLevel2() + " / " + upsAccCharge.getCountryCode() );
		UPSAccCharges result = (UPSAccCharges)getSqlMapClientTemplate().queryForObject("findUPSAccCharge", upsAccCharge);
		
		if(result!=null)
			return result;
		
		logger.debug("No accessorial found for business / chargeCode / chargeCode2 / country : " + upsAccCharge.getBusinessId() + " / " + upsAccCharge.getChargeCode() + " / " + upsAccCharge.getChargeCodeLevel2() + " / " + upsAccCharge.getCountryCode() );
		upsAccCharge.setBusinessId(0);
		
		result = (UPSAccCharges)getSqlMapClientTemplate().queryForObject("findUPSAccCharge", upsAccCharge);

		if(result!=null)
			return result;

		logger.debug("No default accessorial found for business / chargeCode / chargeCode2 / country : " + upsAccCharge.getBusinessId() + " / " + upsAccCharge.getChargeCode() + " / " + upsAccCharge.getChargeCodeLevel2() + " / " + upsAccCharge.getCountryCode() );
		return null;
		
	}
	
	public UPSDASZipsUS getUPSDASZipsUSByZipCode(String zipcode) {
		
		UPSDASZipsUS result = (UPSDASZipsUS)getSqlMapClientTemplate().queryForObject("findUPSDASZipsUSByZipCode", zipcode);
		return result;
		
	}
	
	public UPSCANEAS getUPSCANEAS(String toPostal, String city, String countryCode) {
		
		UPSCANEAS upsCANEAS = new UPSCANEAS();
		
		//If the ship to is Canada, we can easily find the record based on to postal code
		if(countryCode.equalsIgnoreCase(ShiplinxConstants.CANADA)){
			upsCANEAS.setPostalCode(toPostal);
			upsCANEAS.setCountryCode(ShiplinxConstants.CANADA);
			return (UPSCANEAS)getSqlMapClientTemplate().queryForObject("findUPSCANEAS", upsCANEAS);
		}
		
		//if other country, first try to find by postal code, if not then city

		upsCANEAS = new UPSCANEAS();
		upsCANEAS.setPostalCode(toPostal);
		upsCANEAS.setCountryCode(countryCode);
		upsCANEAS = (UPSCANEAS)getSqlMapClientTemplate().queryForObject("findUPSCANEAS", upsCANEAS);
		if(upsCANEAS!=null)
			return upsCANEAS;

		upsCANEAS = new UPSCANEAS();
		upsCANEAS.setPostalCode(null);
		upsCANEAS.setCity(city);
		upsCANEAS.setCountryCode(countryCode);
		return (UPSCANEAS)getSqlMapClientTemplate().queryForObject("findUPSCANEAS", upsCANEAS);
		
	}

	public UPSZoneDiscount getDiscountByZoneAndPackage(UPSZoneDiscount upsZoneDiscount){
		
		if(upsZoneDiscount.getZone()==null){
			log.error("Need zone information to determine UPS discounts");
			return null;
		}
		
		UPSZoneDiscount result = (UPSZoneDiscount)getSqlMapClientTemplate().queryForObject("findUPSDiscountByZone", upsZoneDiscount);
		
		return result;
	
	}

	public Service getServiceCodeByService(Service s){
		
		Service result = (Service)getSqlMapClientTemplate().queryForObject("getUPSServiceCode", s);
		return result;
	
	}

}
