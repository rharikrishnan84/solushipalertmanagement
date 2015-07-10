package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import java.util.List;

import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.ShipmentStatus;
import com.meritconinc.shiplinx.carrier.eshipplus.WSAccessorialService;
import com.meritconinc.shiplinx.carrier.eshipplus.WSShipmentStatus;

public class GetShipmentStatus {

	public static void main(String[] args) {
		AuthenticationToken authenticationprovider =  AuthenticationProvider.authendication();
		EShipPlusWSv4 eshipplusws4=null;
		if(eshipplusws4 == null){
			eshipplusws4=new EShipPlusWSv4();
			String s = String.valueOf(ShipmentStatus.DELIVERED);
			WSShipmentStatus shipmentStatus = eshipplusws4.getEShipPlusWSv4Soap().getShipmentStatus(s, authenticationprovider);
			System.out.print(shipmentStatus.getStatus()+" ");
			System.out.print(shipmentStatus.getShipmentNumber()+" ");
			System.out.print(shipmentStatus.getVendorName()+" ");
			System.out.print(shipmentStatus.getVendorKey()+" ");
			System.out.print(shipmentStatus.getActualDeliveryDate()+" ");
			System.out.print(shipmentStatus.getVendorScac()+" ");
			
			
			
					
		}

	}

}
