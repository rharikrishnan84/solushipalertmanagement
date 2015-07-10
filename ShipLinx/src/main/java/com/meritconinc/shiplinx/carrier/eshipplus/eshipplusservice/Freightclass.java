package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;


import java.util.List;

import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.WSItemPackage;

public class Freightclass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
AuthenticationToken authenticationtoken  = AuthenticationProvider.authendication();
		
		EShipPlusWSv4 eshipplusws4 = null; 
		try{
			if(eshipplusws4 == null){
				eshipplusws4 = new EShipPlusWSv4();
				List<WSItemPackage> country = eshipplusws4.getEShipPlusWSv4Soap().getItemPackaging(authenticationtoken).getWSItemPackage();
				
				for(WSItemPackage c : country){
					//System.out.print(c.getPackageName());
					System.out.println(c.getKey()+" :"+c.getDefaultHeight() + "  : "+c.getDefaultLength() +"  : "+c.getDefaultWidth()+":  "+ c.getMessages());
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
