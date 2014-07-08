package com.meritconinc.shiplinx.carrier.dhl.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.carrier.dhl.dao.DHLCanadaTariffDAO;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLAccCharges;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLESD;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLTariff;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLZone;
import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidatePiece;
import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidateResponse;
import com.meritconinc.shiplinx.utils.ShiplinxConstants;

public class DHLCanadaTariffDAOImpl  extends SqlMapClientDaoSupport  implements DHLCanadaTariffDAO {
	
	private static Logger log = Logger.getLogger(DHLCanadaTariffDAOImpl.class); 
	
	
	public DHLTariff getTariffRecord(DHLTariff tariff){
		
		//for envelopes the rates are stored as 0.5 lb and 0.23 KG
		if(tariff.getPackageId()==ShiplinxConstants.PACKAGE_TYPE_ENVELOPE && tariff.getWeightLB()>0)
			tariff.setWeightLB(0.5);
		if(tariff.getPackageId()==ShiplinxConstants.PACKAGE_TYPE_ENVELOPE && tariff.getWeightKG()>0)
			tariff.setWeightKG(0.23);
		
		if(tariff.getShipmentType()==ShiplinxConstants.SHIPMENT_TYPE_EXPORT || tariff.getShipmentType()==ShiplinxConstants.SHIPMENT_TYPE_IMPORT){		
			if(tariff.getShipmentType()==ShiplinxConstants.SHIPMENT_TYPE_EXPORT)
				tariff.setZone(tariff.getToZone());
			else
				tariff.setZone(tariff.getFromZone());
			if(tariff.getWeightLB() > 0)
				return getTariffRecordLB(tariff);
			else if(tariff.getWeightKG() > 0)
					return getTariffRecordKG(tariff);
			else return null;
		} 
		
		//Third Country rates to be retrieved
		if(tariff.getShipmentType()==ShiplinxConstants.SHIPMENT_TYPE_THIRD_COUNTRY){
			
			String transitCode = this.getThirdCountryTransitCode(tariff);
			if(transitCode==null)
				return null;
			else tariff.setTransitCode(transitCode);
			
			if(tariff.getWeightLB() > 0)
				return getTariffRecordLBThirdCountry(tariff);
			else if(tariff.getWeightKG() > 0)
					return getTariffRecordKGThirdCountry(tariff);
			else return null;
			
		}
		
		return null; //no domestic services available
	}
	

	private DHLTariff getTariffRecordLBThirdCountry(DHLTariff tariff){
		
		//first get the transit code
		
		//get the max weight for the service
		double maxWeightForService = (Double)getSqlMapClientTemplate().queryForObject("findMaxLBWeightForServiceThirdCountry", tariff.getServiceId());
		//if weight being queried is higher than max weight, then get rate for max weight, and then add to it the per pound/KG rate over the max weight
		//the per pound/KG weight is stored in weight -1
		
		if(tariff.getWeightLB() <= maxWeightForService)
			return (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordLBThirdCountry", tariff);	
		
		double queriedWeight = tariff.getWeightLB();
		double overWeightBy = queriedWeight - maxWeightForService; //steps of 1 lb for over weight
		
		tariff.setWeightLB(maxWeightForService);		
		DHLTariff maxWeightRate = (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordLBThirdCountry", tariff);	
		
		tariff.setWeightLB(new Double(-1));
		DHLTariff perPoundOverRate = (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordLBThirdCountry", tariff);
		
		maxWeightRate.setRateDoc(maxWeightRate.getRateDoc() + (perPoundOverRate.getRateDoc() * overWeightBy));
		maxWeightRate.setRateNonDoc(maxWeightRate.getRateNonDoc() + (perPoundOverRate.getRateNonDoc() * overWeightBy));
		
		return maxWeightRate;
		
	}

	private DHLTariff getTariffRecordKGThirdCountry(DHLTariff tariff){
		//get the max weight for the service
		double maxWeightForService = (Double)getSqlMapClientTemplate().queryForObject("findMaxKGWeightForServiceThirdCountry", tariff.getServiceId());
		//if weight being queried is higher than max weight, then get rate for max weight, and then add to it the per pound/KG rate over the max weight
		//the per pound/KG weight is stored in weight -1
		
		if(tariff.getWeightKG() <= maxWeightForService)
			return (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordKGThirdCountry", tariff);	
		
		double queriedWeight = tariff.getWeightKG();
		double overWeightBy = (queriedWeight - maxWeightForService) * 2; //steps of 0.5 kg for over weight
		
		tariff.setWeightKG(maxWeightForService);		
		DHLTariff maxWeightRate = (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordKGThirdCountry", tariff);	
		
		tariff.setWeightKG(new Double(-1));
		DHLTariff perKGOverRate = (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordKGThirdCountry", tariff);
		
		maxWeightRate.setRateDoc(maxWeightRate.getRateDoc() + (perKGOverRate.getRateDoc() * overWeightBy));
		maxWeightRate.setRateNonDoc(maxWeightRate.getRateNonDoc() + (perKGOverRate.getRateNonDoc() * overWeightBy));
		
		return maxWeightRate;
		
	}

	private DHLTariff getTariffRecordLB(DHLTariff tariff){
		//get the max weight for the service
		double maxWeightForService = (Double)getSqlMapClientTemplate().queryForObject("findMaxLBWeightForService", tariff.getServiceId());
		//if weight being queried is higher than max weight, then get rate for max weight, and then add to it the per pound/KG rate over the max weight
		//the per pound/KG weight is stored in weight -1
		
		if(tariff.getWeightLB() <= maxWeightForService)
			return (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordLB", tariff);	
		
		double queriedWeight = tariff.getWeightLB();
		double overWeightBy = queriedWeight - maxWeightForService; //steps of 1 lb for over weight
		
		tariff.setWeightLB(maxWeightForService);		
		DHLTariff maxWeightRate = (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordLB", tariff);	
		
		tariff.setWeightLB(new Double(-1));
		DHLTariff perPoundOverRate = (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordLB", tariff);
		
		maxWeightRate.setRateDoc(maxWeightRate.getRateDoc() + (perPoundOverRate.getRateDoc() * overWeightBy));
		maxWeightRate.setRateNonDoc(maxWeightRate.getRateNonDoc() + (perPoundOverRate.getRateNonDoc() * overWeightBy));
		
		return maxWeightRate;
		
	}
	
	private DHLTariff getTariffRecordKG(DHLTariff tariff){
		//get the max weight for the service
		double maxWeightForService = (Double)getSqlMapClientTemplate().queryForObject("findMaxKGWeightForService", tariff.getServiceId());
		//if weight being queried is higher than max weight, then get rate for max weight, and then add to it the per pound/KG rate over the max weight
		//the per pound/KG weight is stored in weight -1
		
		if(tariff.getWeightKG() <= maxWeightForService)
			return (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordKG", tariff);	
		
		double queriedWeight = tariff.getWeightKG();
		double overWeightBy = (queriedWeight - maxWeightForService) * 2; //steps of 0.5 kg for over weight
		
		tariff.setWeightKG(maxWeightForService);		
		DHLTariff maxWeightRate = (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordKG", tariff);	
		
		tariff.setWeightKG(new Double(-1));
		DHLTariff perKGOverRate = (DHLTariff)getSqlMapClientTemplate().queryForObject("findTariffRecordKG", tariff);
		
		maxWeightRate.setRateDoc(maxWeightRate.getRateDoc() + (perKGOverRate.getRateDoc() * overWeightBy));
		maxWeightRate.setRateNonDoc(maxWeightRate.getRateNonDoc() + (perKGOverRate.getRateNonDoc() * overWeightBy));
		
		return maxWeightRate;
		
	}

	public DHLZone getZone(DHLZone zone){
		return (DHLZone)getSqlMapClientTemplate().queryForObject("findZoneRecord", zone);		
	}
	
	public String getThirdCountryTransitCode(DHLTariff tariff){
		return (String)getSqlMapClientTemplate().queryForObject("findThirdCountryTransitCode", tariff);		
	}

	public DHLAccCharges getAcccharge(DHLAccCharges accCharge){
		return (DHLAccCharges)getSqlMapClientTemplate().queryForObject("getAccCharge", accCharge);		
	}

	public List<DHLESD> getDHLESDByZip(DHLESD dhlesd){
		return (List<DHLESD>)getSqlMapClientTemplate().queryForList("findESDByZip", dhlesd);		
	}
	public List<DHLESD> getDHLESDByCity(DHLESD dhlesd){
		return (List<DHLESD>)getSqlMapClientTemplate().queryForList("findESDByCity", dhlesd);		
	}

	
	@Override
	public Long addShipValidateResponse(DhlShipValidateResponse res) throws Exception {
		// TODO Auto-generated method stub
		try{
			Long resId = (Long) getSqlMapClientTemplate().insert("addShipValidateResponse", res);
			if (res.getPieces() != null) {
				for (DhlShipValidatePiece p:res.getPieces()) {
					p.setShipValResponseId(resId);
					getSqlMapClientTemplate().insert("addShipValidatePiece", p);
				}
			}
			return resId;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public DhlShipValidateResponse getShipValidateResponse(Long resId, String billNumber) throws Exception {
		// TODO Auto-generated method stub
		try {
			DhlShipValidateResponse res = new DhlShipValidateResponse();
			res.setId(resId);
			res.setAirwayBillNumber(billNumber);
			return (DhlShipValidateResponse) getSqlMapClientTemplate().queryForObject("getShipValidateResponse", res);
		} catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}

	
}
