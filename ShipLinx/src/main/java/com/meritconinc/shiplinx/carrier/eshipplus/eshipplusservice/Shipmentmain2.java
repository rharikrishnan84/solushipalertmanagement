package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.print.attribute.standard.Destination;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSAccessorialService;
import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSDocument;
import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSItem2;
import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSRate2;
import com.meritconinc.shiplinx.carrier.eshipplus.ArrayOfWSReference;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationToken;
import com.meritconinc.shiplinx.carrier.eshipplus.BookingReferenceNumberType;
import com.meritconinc.shiplinx.carrier.eshipplus.EShipPlusWSv4;
import com.meritconinc.shiplinx.carrier.eshipplus.WSAccessorialService;
import com.meritconinc.shiplinx.carrier.eshipplus.WSBookingRequest;
import com.meritconinc.shiplinx.carrier.eshipplus.WSCountry;
import com.meritconinc.shiplinx.carrier.eshipplus.WSFreightClass;
import com.meritconinc.shiplinx.carrier.eshipplus.WSItem2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSItemPackage;
import com.meritconinc.shiplinx.carrier.eshipplus.WSLocation2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSRate2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSReference;
import com.meritconinc.shiplinx.carrier.eshipplus.WSShipment2;
import com.meritconinc.shiplinx.carrier.eshipplus.WSTime2;
import com.meritconinc.shiplinx.carrier.eshipplus.AuthenticationProvider;

public class Shipmentmain2 {

	
	public static void main(String[] args) {
		
		/*ArrayOfWSItem2 arrayofwsitem2 =  new ArrayOfWSItem2();
		WSShipment2 shipment = new WSShipment2();
		WSFreightClass freightClass = new WSFreightClass();
		WSItemPackage itemPackage = new WSItemPackage();
		WSLocation2  wslocationorigin = new WSLocation2();
		WSLocation2  wslocationdestination= new WSLocation2();
		WSCountry wscountryorigin = new WSCountry();
		WSCountry wscountrydestination = new WSCountry();
		WSItem2 wsitem2 = new WSItem2();
		WSItemPackage wsitempackage = new WSItemPackage();*/
		
		WSShipment2 wsshipment2 = new WSShipment2();
		try {
			
			AuthenticationToken authenticationtoken = AuthenticationProvider.authendication();
			EShipPlusWSv4 eshipplusws4=null;
			
			
	
			if(eshipplusws4 == null){
				eshipplusws4=new EShipPlusWSv4();
			}
				
				//start of shipment
				WSLocation2 wsLocation2 = new WSLocation2();
				//setting the description
				wsLocation2.setDescription("testing origin");
				//setting the postal code
				wsLocation2.setPostalCode("L4V1Y9");
				wsLocation2.setCity("MISSISSAUGA");
				wsLocation2.setState("ON");
				wsLocation2.setStreet("123,Main");
				//setting the origin
				WSCountry country = new WSCountry();
				country.setCode("CA");
				country.setUsesPostalCode(true);
				wsLocation2.setCountry(country);
				wsLocation2.setContact("Harikrishnan");
				wsLocation2.setPhone("9789891402");
				wsshipment2.setOrigin(wsLocation2);
				
				//setting the destination
				WSLocation2 location2 = new WSLocation2();
				location2.setDescription("testing destination");
				location2.setPostalCode("48150");
				location2.setCity("LIVONIA");
				location2.setState("MI");
				location2.setStreet("1234  Main");
				WSCountry countryDestination = new WSCountry();
				countryDestination.setCode("US");
				countryDestination.setUsesPostalCode(true);
				location2.setCountry(countryDestination);
				location2.setContact("Harikrishnan");
				location2.setPhone("9789891402");
				wsshipment2.setDestination(location2);
		
				//shipment date
				wsshipment2.setShipmentDate(getShipmentDate());
				
				
				//earliest pickup
				WSTime2 wsTime2 = new WSTime2();
				wsTime2.setTime("05:00");
				wsshipment2.setEarliestPickup(wsTime2);
				
				//latest pickup
				WSTime2 wsTimeLatestPickup = new WSTime2();
				wsTimeLatestPickup.setTime("17:00");
				wsshipment2.setLatestPickup(wsTimeLatestPickup);
				
				//earliest deleivery
				WSTime2 wsTimeEarliest = new WSTime2();
				wsTimeEarliest.setTime("09:30");
				wsshipment2.setEarliestDelivery(wsTimeEarliest);
				
				//latest deleivery
				WSTime2 wsTimeLatest = new WSTime2();
				wsTimeLatest.setTime("17:30");
				wsshipment2.setLatestDelivery(wsTimeLatest);
				
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
				wsshipment2.setItems(arrayOfWSItem22);
				
				//additional insurance
				wsshipment2.setDeclineAdditionalInsuranceIfApplicable(false);
				
				//hazardous shipment
				wsshipment2.setHazardousMaterialShipment(false);
			
				WSShipment2 rate = eshipplusws4.getEShipPlusWSv4Soap().rate(wsshipment2 ,  authenticationtoken);
			
				System.out.println(rate.getAvailableRates().getWSRate2().listIterator());
				
				//rate.getAvailableRates().getWSRate2().listIterator().
				
				
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
	  
  }
	
	public static XMLGregorianCalendar getShipmentDate(){
		Calendar calendar = Calendar.getInstance();
	      
//	     // get a date to represent "today"
//	     Date today = calendar.getTime();
//	     System.out.println("today:    " + today);
	   
	     // add one day to the date/calendar
	     calendar.add(Calendar.DAY_OF_YEAR, 6);
	      
	     // now get "tomorrow"
	     Date tomorrow = calendar.getTime();
	     XMLGregorianCalendar date2 = null;
	  try {
	   GregorianCalendar c = new GregorianCalendar();
	   c.setTime(tomorrow);
	   date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
	   System.out.println(date2);
	   
	  } catch (DatatypeConfigurationException e) {
	 // TODO: handle exception
	}
	  return date2;
	}
}

