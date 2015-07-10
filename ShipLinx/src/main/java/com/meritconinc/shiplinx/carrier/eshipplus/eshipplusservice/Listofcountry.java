package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import java.util.List;


import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.Rate;
import com.meritconinc.shiplinx.carrier.eshipplus.WSAccessorialService;
import com.meritconinc.shiplinx.carrier.eshipplus.WSCountry;
import com.meritconinc.shiplinx.carrier.eshipplus.WSMessage;
import com.meritconinc.shiplinx.carrier.eshipplus.WSRate2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSTime2;

public class Listofcountry {
	

			
	public static void main(String[] args) {
		
		AuthenticationToken authenticationtoken  = AuthenticationProvider.authendication();
		
		EShipPlusWSv4 eshipplusws4 = null; 
		try{
			if(eshipplusws4 == null){
				eshipplusws4 = new EShipPlusWSv4();
				List<WSCountry> country = eshipplusws4.getEShipPlusWSv4Soap().getCountries(authenticationtoken).getWSCountry();
				
				for(WSCountry c : country){
					System.out.print(c.getName());
					System.out.print(c.getCode());
					System.out.println();
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{

//			List<WSTime2> time = eshipplusws4.getEShipPlusWSv4Soap().getTimes(authenticationtoken).getWSTime2();
//			for(WSTime2 wt : time){
//				System.out.println(wt.getTime());
//			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{

//			List<WSAccessorialService> accservice = eshipplusws4.getEShipPlusWSv4Soap().getAccessorialServices(authenticationtoken).getWSAccessorialService();
//			for(WSAccessorialService acc : accservice){
//				System.out.println(acc.getServiceDescription());
//			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try{

//			List<WSAccessorialService> accservice = eshipplusws4.getEShipPlusWSv4Soap().getAccessorialServices(authenticationtoken).getWSAccessorialService();
//			for(WSAccessorialService acc : accservice){
//				System.out.println(acc.getServiceDescription());
//			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
