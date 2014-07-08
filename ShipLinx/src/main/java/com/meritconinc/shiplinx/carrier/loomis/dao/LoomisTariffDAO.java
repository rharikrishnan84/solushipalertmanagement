package com.meritconinc.shiplinx.carrier.loomis.dao;

import com.meritconinc.shiplinx.carrier.loomis.model.LoomisBeyond;
import com.meritconinc.shiplinx.carrier.loomis.model.LoomisTariff;
import com.meritconinc.shiplinx.carrier.midland.model.MidlandRate;


public interface LoomisTariffDAO {
		public LoomisTariff getTariffRecord(LoomisTariff tariff);
		public LoomisBeyond getBeyondRecord(LoomisBeyond beyond);
		
		public MidlandRate getMidlandRate(MidlandRate rate);


}
 