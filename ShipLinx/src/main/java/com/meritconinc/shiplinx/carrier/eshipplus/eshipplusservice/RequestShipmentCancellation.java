package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;
import java.util.List;

import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSMessage;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.ShipmentStatus;
import com.meritconinc.shiplinx.carrier.eshipplus.WSMessage;
import com.meritconinc.shiplinx.carrier.eshipplus.WSShipmentStatus;

public class RequestShipmentCancellation {

	public static void main(String[] args) {
		
		AuthenticationToken authenticationprovider =  AuthenticationProvider.authendication();
		EShipPlusWSv4 eshipplusws4=null;
		
		try {
			
			if(eshipplusws4 == null){
				eshipplusws4=new EShipPlusWSv4();
				String s = String.valueOf(ShipmentStatus.DELIVERED);
				//List<ArrayOfWSMessage> shipmentStatus = eshipplusws4.getEShipPlusWSv4Soap().requestShipmentCancellation (s, authenticationprovider).getMessages();
				//System.out.println(shipmentStatus);
				
						
			}
		} catch (Exception e) {
		}

	}

}
