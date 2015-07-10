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
import com.meritconinc.shiplinx.carrier.eshipplus.WSCountry;
import com.meritconinc.shiplinx.carrier.eshipplus.WSFreightClass;
import com.meritconinc.shiplinx.carrier.eshipplus.WSItem2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSItemPackage;
import com.meritconinc.shiplinx.carrier.eshipplus.WSLocation2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSShipment2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSTime2;

public class Shipmentmain {

	@SuppressWarnings({ "unused", "static-access" })
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
	public static void main(String[] args) {
		WSShipment2 wsshipment2 = new WSShipment2();
		WSLocation2 locationOrigin = new WSLocation2();
		WSLocation2 locationDestination = new WSLocation2();
		WSCountry countryOrigin = new WSCountry();
		WSCountry countryDestination = new WSCountry();
		WSItem2 item2 = new WSItem2();
		WSTime2 wsTimeLatestPickup = new WSTime2();
		WSTime2 wsTimeEarliest = new WSTime2();
		WSTime2 wsTimeLatest = new WSTime2();
		WSFreightClass wsFreightClass = new WSFreightClass();
		WSItemPackage itemPacage= new WSItemPackage();
		ArrayOfWSItem2 arrayofwsItem2 = new ArrayOfWSItem2();
	
		try {
			AuthenticationToken authenticationToken= AuthenticationProvider.authendication();
			EShipPlusWSv4 eshipPluswv4 = null;
			
			if(eshipPluswv4 == null)
			{
				eshipPluswv4 = new EShipPlusWSv4();
			}
			
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
			wsshipment2.setOrigin(locationOrigin);

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
			wsshipment2.setDestination(locationDestination);
			
			/** SET THE EarliestPickup**/
			WSTime2 wsTime2 = new WSTime2();
			wsTime2.setTime("05:00");
			wsshipment2.setEarliestPickup(wsTime2);
			
			/** SET THE LatestPickup**/
			wsTimeLatestPickup.setTime("17:00");
			wsshipment2.setLatestPickup(wsTimeLatestPickup);

			/** SET THE EarliestDelivery**/
			wsTimeEarliest.setTime("09:30");
			wsshipment2.setEarliestDelivery(wsTimeEarliest);
			
			/** SET THE LatestDelivery**/
			wsTimeLatest.setTime("17:30");
			wsshipment2.setLatestDelivery(wsTimeLatest);
			
			/** SET THE ARRAY OF ITEM**/
			item2.setWeight(new BigDecimal(100.00));
			item2.setPackagingQuantity(1);
			item2.setLength(new BigDecimal(40));
			item2.setHeight(new BigDecimal(40));
			item2.setWidth(new BigDecimal(40));
			
			
			itemPacage.setKey(258);
			item2.setPackaging(itemPacage);
			
			/**sSET THE FREIGHTCLASS**/
			wsFreightClass.setFreightClass(50);
			item2.setFreightClass(wsFreightClass);
			
			ArrayList<WSItem2> arrayofItem = new ArrayList<WSItem2>();
			arrayofItem.add(item2);
			
					
			arrayofwsItem2.wsItem2 = arrayofItem;
			wsshipment2.setItems(arrayofwsItem2);
			
			
			wsshipment2.setDeclineAdditionalInsuranceIfApplicable(false);
			wsshipment2.setHazardousMaterialShipment(false);
			
			WSShipment2 getRate =eshipPluswv4.getEShipPlusWSv4Soap().rate(wsshipment2, authenticationToken);
			System.out.println(getRate.getAvailableRates());

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
