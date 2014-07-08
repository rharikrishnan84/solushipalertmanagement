package com.meritconinc.shiplinx.carrier.dhl.dao;

import java.util.List;

import com.meritconinc.shiplinx.carrier.dhl.model.DHLAccCharges;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLESD;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLTariff;
import com.meritconinc.shiplinx.carrier.dhl.model.DHLZone;
import com.meritconinc.shiplinx.carrier.dhl.model.DhlShipValidateResponse;


public interface DHLCanadaTariffDAO {
		public DHLTariff getTariffRecord(DHLTariff tariff);
		public DHLZone getZone(DHLZone zone);
		public DHLAccCharges getAcccharge(DHLAccCharges accCharge);
		public DhlShipValidateResponse getShipValidateResponse(Long resId, String billNumber) throws Exception;
		public Long addShipValidateResponse(DhlShipValidateResponse res) throws Exception;
		public List<DHLESD> getDHLESDByZip(DHLESD dhlesd);				
		public List<DHLESD> getDHLESDByCity(DHLESD dhlesd); 
}
