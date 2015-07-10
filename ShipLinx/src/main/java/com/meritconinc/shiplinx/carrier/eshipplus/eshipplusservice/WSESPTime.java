package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import java.util.Iterator;
import java.util.List;

import javax.xml.ws.WebServiceException;

import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.WSTime2;

public class WSESPTime {

	public static void main(String[] args) {
		
		AuthenticationToken authenticationtoken  = AuthenticationProvider.authendication();
		EShipPlusWSv4  eshipplus= null;
		try {
			if(eshipplus == null){
				eshipplus = new EShipPlusWSv4();
				List<WSTime2> wstime = eshipplus.getEShipPlusWSv4Soap().getTimes(authenticationtoken).getWSTime2();
				
				for(WSTime2  wst: wstime)
				{
					System.out.println(wst.getTime());
				
				}
			}
		} catch (WebServiceException e) {
			// TODO: handle exception
		}
		
				

	}

}
