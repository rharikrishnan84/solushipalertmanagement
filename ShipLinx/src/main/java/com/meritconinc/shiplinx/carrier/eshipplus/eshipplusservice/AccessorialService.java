package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import java.util.List;

import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.WSAccessorialService;

public class AccessorialService {

	public static void main(String[] args) {
		
		AuthenticationToken authenticationtoken = AuthenticationProvider.authendication();
		EShipPlusWSv4 eshipplusws4=null;
		try{
		if(eshipplusws4 == null){
			eshipplusws4=new EShipPlusWSv4();
			List<WSAccessorialService> accessorialservice = eshipplusws4.getEShipPlusWSv4Soap().getAccessorialServices(authenticationtoken).getWSAccessorialService();
			for(WSAccessorialService acc : accessorialservice)
			{
				
				acc.getBillingCode();
				
				System.out.println(acc.getServiceCode()+" : "+acc.getBillingCode()+" : "+acc.getServiceDescription());
			}
					
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
