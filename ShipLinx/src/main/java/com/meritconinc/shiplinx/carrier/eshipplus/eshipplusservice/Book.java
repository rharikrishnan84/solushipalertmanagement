package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.BookingRequestStatus;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.WSBookingRequest;
import com.meritconinc.shiplinx.carrier.eshipplus.WSBookingRequestStatus;

public class Book {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
AuthenticationToken authenticationtoken  = AuthenticationProvider.authendication();
		
		EShipPlusWSv4 eshipplusws4 = null; 
		try{
			if(eshipplusws4 == null){
				eshipplusws4 = new EShipPlusWSv4();
				String  bookingNumber = "100"; 
				WSBookingRequestStatus obj  = eshipplusws4.getEShipPlusWSv4Soap().getBookingRequestStatus(bookingNumber, authenticationtoken);
				
				System.out.print(obj.getBookingNumber());
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}