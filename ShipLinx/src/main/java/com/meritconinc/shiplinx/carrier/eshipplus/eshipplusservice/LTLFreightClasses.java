package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import java.util.List;

import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.WSFreightClass;


public class LTLFreightClasses {

	public static void main(String[] args) {
AuthenticationToken authenticationtoken  = AuthenticationProvider.authendication();
		
		EShipPlusWSv4 eshipplusws4 = null; 
		try{
			if(eshipplusws4 == null){
				eshipplusws4 = new EShipPlusWSv4();
				List<WSFreightClass> wsfreightclass = eshipplusws4.getEShipPlusWSv4Soap().getLTLFreightClasses(authenticationtoken).getWSFreightClass();
				
				for(WSFreightClass ws : wsfreightclass){
					System.out.println("FreightClass: "+ws.getFreightClass());
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
