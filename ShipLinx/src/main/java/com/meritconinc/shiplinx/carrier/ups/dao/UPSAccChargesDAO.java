package com.meritconinc.shiplinx.carrier.ups.dao;

import com.meritconinc.shiplinx.carrier.ups.model.UPSAccCharges;


public interface UPSAccChargesDAO {
		public UPSAccCharges getAccCharge(Long franchiseId, String chargeCode, String chargeCodeLevel2, String country);
} 
