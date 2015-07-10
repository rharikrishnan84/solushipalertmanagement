package com.meritconinc.shiplinx.carrier.eshipplus.eshipplusservice;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class JavaDateAddExample {

	public static void main(String[] args) {
		 // get a calendar instance, which defaults to "now"
	    Calendar calendar = Calendar.getInstance();
	     
//	    // get a date to represent "today"
//	    Date today = calendar.getTime();
//	    System.out.println("today:    " + today);
	  
	    // add one day to the date/calendar
	    calendar.add(Calendar.DAY_OF_YEAR, 3);
	     
	    // now get "tomorrow"
	    Date tomorrow = calendar.getTime();

		try {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(tomorrow);
			XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			System.out.println(date2);
		} catch (DatatypeConfigurationException e) {
	// TODO: handle exception
}
	


	}

}
