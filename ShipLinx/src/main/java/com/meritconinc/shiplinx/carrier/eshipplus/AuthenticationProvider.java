package com.meritconinc.shiplinx.carrier.eshipplus;

import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.model.CustomerCarrier;

public abstract  class AuthenticationProvider{
	
	private final static String Username ="ryan.blakey"; 
	private final static String Password = "Reynard1";
	private final static String Accesscode ="TENANT1"; 
	private final static String AccessKey ="a33b98de-a066-4766-ac9e-1eab39ce6806";
	
	public static AuthenticationToken authendication() {
		
		AuthenticationToken authenticationtoken = new AuthenticationToken();
		authenticationtoken.setUsername(Username);
		authenticationtoken.setPassword(Password);
		authenticationtoken.setAccessCode(Accesscode);
		authenticationtoken.setAccessKey(AccessKey);
		return authenticationtoken;
	}

	public static AuthenticationToken authendication(CustomerCarrier customerCarrier) {
		
		AuthenticationToken authenticationtoken = new AuthenticationToken();
		authenticationtoken.setUsername(customerCarrier.getProperty1());
		authenticationtoken.setPassword(customerCarrier.getProperty2());
		authenticationtoken.setAccessCode(customerCarrier.getProperty3());
		authenticationtoken.setAccessKey(customerCarrier.getProperty4());
		return authenticationtoken;
	}
}
