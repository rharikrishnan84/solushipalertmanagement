package com.meritconinc.shiplinx.carrier.loomis.dao.impl;

import org.apache.log4j.Logger;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.meritconinc.shiplinx.carrier.loomis.dao.LoomisTariffDAO;
import com.meritconinc.shiplinx.carrier.loomis.model.LoomisBeyond;
import com.meritconinc.shiplinx.carrier.loomis.model.LoomisTariff;
import com.meritconinc.shiplinx.carrier.midland.model.MidlandRate;
import com.meritconinc.shiplinx.utils.FormattingUtil;

public class LoomisTariffDAOImpl  extends SqlMapClientDaoSupport  implements LoomisTariffDAO {
	
	private static Logger log = Logger.getLogger(LoomisTariffDAOImpl.class); 
	
	
	public LoomisTariff getTariffRecord(LoomisTariff tariff){
		
		String fromZone = (String)getSqlMapClientTemplate().queryForObject("getZone", tariff.getFromPostalCode());
		if(fromZone==null){
			log.error("Could not determine Loomis zone for postal code: " + tariff.getFromPostalCode());
			return null;
		}
		String toZone = (String)getSqlMapClientTemplate().queryForObject("getZone", tariff.getToPostalCode());
		if(toZone==null){
			log.error("Could not determine Loomis zone for postal code: " + tariff.getToPostalCode());
			return null;
		} 
		
		tariff.setFromZone(fromZone);
		tariff.setToZone(toZone);
		
		String transitCode = (String)getSqlMapClientTemplate().queryForObject("getTransitCode", tariff);
		if(transitCode==null){
			log.error("Could not determine Loomis transit code for zones (from/to): " + tariff.getFromZone() + " / " + tariff.getToZone());
			return null;
		}
		
		tariff.setTransitCode(transitCode);
		
		double maxWeightForService = (Double)getSqlMapClientTemplate().queryForObject("findMaxWeightForService", tariff.getServiceId());

		if(tariff.getWeight() <= maxWeightForService){
			Double rate = (Double)getSqlMapClientTemplate().queryForObject("getTariffRate", tariff);
			tariff.setRate(rate);
			return tariff;
		}
		
		double queriedWeight = tariff.getWeight();
		double overWeightBy = queriedWeight - maxWeightForService; //steps of 1 lb for over weight

		tariff.setWeight(maxWeightForService);		
		Double rateForMaxWeight = (Double)getSqlMapClientTemplate().queryForObject("getTariffRate", tariff);	
		
		tariff.setWeight(new Double(-1));
		Double rateForOverWeight = (Double)getSqlMapClientTemplate().queryForObject("getTariffRate", tariff) * overWeightBy;	
		
		tariff.setWeight(queriedWeight);
		tariff.setRate((FormattingUtil.add(rateForMaxWeight, rateForOverWeight)).doubleValue());
		
		return tariff;
		
	}
	
	public LoomisBeyond getBeyondRecord(LoomisBeyond beyond){
		if(beyond==null || beyond.getFromPostalCode()==null)
			return null;
		
		beyond = (LoomisBeyond)getSqlMapClientTemplate().queryForObject("getBeyondRecord", beyond);
		
		return beyond;

		
	}

//	Putting all midland queries here for now, move into separate file if required later 
	public MidlandRate getMidlandRate(MidlandRate rate){
		if(rate==null || rate.getServiceId()==0)
			return null;
		
		rate = (MidlandRate)getSqlMapClientTemplate().queryForObject("getMidlandRate", rate);
		
		return rate;
	}
	
	
}
