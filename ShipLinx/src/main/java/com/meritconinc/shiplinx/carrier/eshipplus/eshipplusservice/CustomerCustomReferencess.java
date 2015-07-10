package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import java.util.List;

import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.WSReference;

public class CustomerCustomReferencess {

	public static void main(String[] args) {
		
AuthenticationToken authenticationtoken  = AuthenticationProvider.authendication();
		
		EShipPlusWSv4 eshipplusws4 = null; 
		try{
			if(eshipplusws4 == null){
				eshipplusws4 = new EShipPlusWSv4();
				List<WSReference> wsreference = eshipplusws4.getEShipPlusWSv4Soap().getCustomerCustomReferences(authenticationtoken).getWSReference();
				
				for(WSReference c : wsreference){
					c.setName("Parthiban");
					c.setValue("1000");
					System.out.print(c.getName()+" : ");
					System.out.print(c.getValue());
//					System.out.println(c.getMessages().getWSMessage());
				}
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}


	}

}
