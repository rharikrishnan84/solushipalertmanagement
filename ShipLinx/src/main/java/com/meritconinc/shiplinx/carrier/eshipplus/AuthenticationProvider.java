package com.meritconinc.shiplinx.carrier.eshipplus;

import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;

public abstract  class AuthenticationProvider{
	
	private final static String Username ="ryan.blakey"; 
	private final static String Password = "ry1234";
	private final static String Accesscode ="TENANT1"; 
	private final static String AccessKey ="dd9c2694-672b-4498-9a96-2a96f3f82364";
	
	public static AuthenticationToken authendication() {
		
		AuthenticationToken authenticationtoken = new AuthenticationToken();
		authenticationtoken.setUsername(Username);
		authenticationtoken.setPassword(Password);
		authenticationtoken.setAccessCode(Accesscode);
		authenticationtoken.setAccessKey(AccessKey);
		
		return authenticationtoken;
	}

}
