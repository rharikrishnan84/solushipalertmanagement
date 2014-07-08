package com.meritconinc.shiplinx.carrier.dhl;

import java.util.List;

import com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.SpecialService;


public class ShipmentRequest extends com.meritconinc.shiplinx.carrier.dhl.xml.shipvalidateglobal.request.ShipmentRequest {
	
	public void setSpecialService(List<SpecialService> listOfSpecialService){
		super.specialService=listOfSpecialService;
	}
}
 