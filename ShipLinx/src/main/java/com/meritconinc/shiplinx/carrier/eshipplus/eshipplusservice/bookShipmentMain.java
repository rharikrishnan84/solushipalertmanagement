package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSItem2;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.SubmitBookingRequest;
import com.meritconinc.shiplinx.carrier.eshipplus.WSBookingRequest;
import com.meritconinc.shiplinx.carrier.eshipplus.WSCountry;
import com.meritconinc.shiplinx.carrier.eshipplus.WSFreightClass;
import com.meritconinc.shiplinx.carrier.eshipplus.WSItem2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSItemPackage;
import com.meritconinc.shiplinx.carrier.eshipplus.WSLocation2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSShipment2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSTime2;

public class bookShipmentMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        GregorianCalendar d=new GregorianCalendar();
        
		WSLocation2 locationOrigin = new WSLocation2();
		WSCountry countryOrigin = new WSCountry();
		WSLocation2 locationDestination = new WSLocation2();
		WSCountry countryDestination = new WSCountry();
        //coded  
		SubmitBookingRequest submitBookingRequest=new SubmitBookingRequest();
		WSBookingRequest wsBookingRequest=new WSBookingRequest();
		
		AuthenticationToken authenticationtoken = AuthenticationProvider.authendication();
		WSShipment2 shipment2 = new WSShipment2();
		EShipPlusWSv4 eshipplusws4=new EShipPlusWSv4();
		//-------------------------
		/**SET THE ORIGIN*/
		locationOrigin.setDescription("testing origin");
		locationOrigin.setPostalCode("L4V1Y9");
		locationOrigin.setCity("MISSISSAUGA");
		locationOrigin.setState("ON");
		locationOrigin.setStreet("123,Main");
		locationOrigin.setContact("Harikrishnan");
		locationOrigin.setPhone("9789891402");
		
		/**SET THE ORIGIN COUNTRY**/
		countryOrigin.setCode("CA");
		countryOrigin.setUsesPostalCode(true);
		locationOrigin.setCountry(countryOrigin);
		shipment2.setOrigin(locationOrigin);

		/**SET THE DESTINATION**/
		locationDestination .setDescription("testing destination");
		locationDestination.setPostalCode("48150");
		locationDestination.setCity("LIVONIA");
		locationDestination.setState("MI");
		locationDestination.setStreet("1234  Main");
		locationDestination.setContact("Harikrishnan");
		locationDestination.setPhone("9789891402");
		
		/**SET THE DESTINATION COUNTRY**/
		countryDestination.setCode("US");
		countryDestination.setUsesPostalCode(true);
		locationDestination.setCountry(countryDestination);
		shipment2.setDestination(locationDestination);
		
		//shipment date
	
		shipment2.setShipmentDate(getShipmentDate());
		
		
		//earliest pickup
		WSTime2 wsTime2 = new WSTime2();
		wsTime2.setTime("05:00");
		shipment2.setEarliestPickup(wsTime2);
		
		//latest pickup
		WSTime2 wsTimeLatestPickup = new WSTime2();
		wsTimeLatestPickup.setTime("17:00");
		shipment2.setLatestPickup(wsTimeLatestPickup);
		
		//earliest deleivery
		WSTime2 wsTimeEarliest = new WSTime2();
		wsTimeEarliest.setTime("09:30");
		shipment2.setEarliestDelivery(wsTimeEarliest);
		
		//latest deleivery
		WSTime2 wsTimeLatest = new WSTime2();
		wsTimeLatest.setTime("17:30");
		shipment2.setLatestDelivery(wsTimeLatest);
		
		//items
		ArrayOfWSItem2 arrayOfWSItem22 = new ArrayOfWSItem2();
		WSItem2 item2 = new WSItem2();
		item2.setWeight(new BigDecimal(100.00));
		item2.setPackagingQuantity(1);
		item2.setLength(new BigDecimal(40));
		item2.setHeight(new BigDecimal(40));
		item2.setWidth(new BigDecimal(40));
		
		WSItemPackage itemPackage2 = new WSItemPackage();
		itemPackage2.setKey(258);
		item2.setPackaging(itemPackage2);
		
		WSFreightClass freightClass2 = new WSFreightClass();
		freightClass2.setFreightClass(50);
		item2.setFreightClass(freightClass2);
		
		ArrayList<WSItem2> arrayList = new ArrayList<WSItem2>();
		arrayList.add(item2);
		arrayOfWSItem22.wsItem2 = arrayList;
		shipment2.setItems(arrayOfWSItem22);
		
		//additional insurance
		shipment2.setDeclineAdditionalInsuranceIfApplicable(false);
		
		//hazardous shipment
		shipment2.setHazardousMaterialShipment(false);
		
		
		//------------------------------
		
		
		WSShipment2 Shipment2 = eshipplusws4.getEShipPlusWSv4Soap().book(shipment2, authenticationtoken);
		
		
	}
	
	private static XMLGregorianCalendar getShipmentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.DAY_OF_YEAR, 5);
		Date futuredate=calendar.getTime();
		XMLGregorianCalendar xmlgregorianCalendar =null;
		try {
			
			GregorianCalendar gregorianCalendar= new GregorianCalendar();
			calendar.setTime(futuredate);
			xmlgregorianCalendar= DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
		}
		catch (Exception e) {
		}
		return xmlgregorianCalendar;
		
	}

}
