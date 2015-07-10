package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;


import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.BookingRequestStatus;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;

public class BookingRequestStatuss {

	public static void main(String[] args) {
		AuthenticationToken authenticationtoken  = AuthenticationProvider.authendication();
		
		EShipPlusWSv4 eshipplusws4 = null; 
		try{
			if(eshipplusws4 == null){
				eshipplusws4 = new EShipPlusWSv4();
				String  bookingNumber = "5"; 
				BookingRequestStatus obj  = eshipplusws4.getEShipPlusWSv4Soap().getBookingRequestStatus(bookingNumber, authenticationtoken).getStatus();
						
				
					System.out.print(obj.name());
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
