package com.meritconinc.shiplinx.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.log4j.Logger;

public class FormattingUtil {

	private static Logger logger = Logger.getLogger(FormattingUtil.class);
	
	public static String DECIMAL_2_PLACES_PATTERN = "####.00";
	public static String DECIMAL_2_PLACES_PATTERN_LENGTH_8 = "#####.00";

	private static SimpleDateFormat BASIC_DATE_FORMAT = new SimpleDateFormat("MMMMM dd, yyyy");
	public static SimpleDateFormat DATE_FORMAT_YYMMDD = new SimpleDateFormat("yyMMdd");
	public static SimpleDateFormat DATE_FORMAT_yyyyMMDD = new SimpleDateFormat("yyyyMMdd");
	public static SimpleDateFormat DATE_FORMAT_YYYYMMDD = new SimpleDateFormat("yyyy/MM/dd");
	public static SimpleDateFormat DATE_FORMAT_MMDDYYYY = new SimpleDateFormat("MM/dd/yyyy");
	public static SimpleDateFormat DATE_FORMAT_DDMMYYYY = new SimpleDateFormat("ddMMyyyy");
	public static SimpleDateFormat DATE_FORMAT_DDMMMYYYY = new SimpleDateFormat("dd/MMM/yyyy");
	public static SimpleDateFormat DATE_FORMAT_WEB = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat DATE_FORMAT_WEB_ENDOFDAY = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat TIME_FORMAT_WEB = new SimpleDateFormat("HH:mm");
	public static SimpleDateFormat PUROLATOR_LIVE_DATE_FORMAT_MMDDYYYY = new SimpleDateFormat("MM/dd/yyyy");
	public static SimpleDateFormat DATE_FORMAT_YYMMDD_HHMM = new SimpleDateFormat("yyMMdd HHmm");
	public static SimpleDateFormat DATE_FORMAT_YYMMDDHHMM = new SimpleDateFormat("yyMMddHHmm");
	public static SimpleDateFormat DATE_FORMAT_YYYYMMDD_HHMMSS = new SimpleDateFormat("yyyyMMdd HHmmss");
	public static SimpleDateFormat DATE_FORMAT_DDMMMYY = new SimpleDateFormat("ddMMMyy");
	public static SimpleDateFormat DATE_FORMAT_DDMMMYY_HHMM = new SimpleDateFormat("ddMMMyy HH:mm");
	
	 
//	public static SimpleDateFormat DATE_FORMAT_MMDDYYYY_HHMMSS  = new SimpleDateFormat("MMddyyyyHHmmss");

	public static SimpleDateFormat DATE_FORMAT_YYYYMMDDHHMM = new SimpleDateFormat("yyyyMMddHHmm");
	public static SimpleDateFormat DATE_FORMAT_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
	
	public static SimpleDateFormat DATE_FORMAT_MMDDYYYY_HHMMSS = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	public static SimpleDateFormat MG_LABEL_DATE_FORMAT = new SimpleDateFormat("MMMMM d, yyyy");


	private static final String PLAIN_ASCII =
	      "AaEeIiOoUu"    // grave
	    + "AaEeIiOoUuYy"  // acute
	    + "AaEeIiOoUuYy"  // circumflex
	    + "AaEeIiOoUuYy"  // tilde
	    + "AaEeIiOoUuYy"  // umlaut
	    + "Aa"            // ring
	    + "Cc"            // cedilla
	    ;

	private static final String UNICODE =
	 "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"             
	+"\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD" 
	+"\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" 
	+"\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177" 
	+"\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF" 
	+"\u00C5\u00E5"                                                             
	+"\u00C7\u00E7"                                                             
	 ;
		
	public static String formatDecimalTo2Places(float val, String pattern)
	{
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(val);
	}

	public static String formatDecimalTo2Places(double val, String pattern)
	{
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(val);
	}
	public static Double formatDecimalTo2PlacesDouble(double val) {
		String s = formatDecimalTo2Places(val, FormattingUtil.DECIMAL_2_PLACES_PATTERN);
		return Double.valueOf(s);
	}
	
	public static String getBasicDateFormat(Date d){
		return BASIC_DATE_FORMAT.format(d);
	}


	public static String getFormattedDate(Date d, SimpleDateFormat sdf){
		return sdf.format(d);
	}

	public static String getFormattedDate(Date d, String timeZone, SimpleDateFormat sdf){
		TimeZone t = sdf.getTimeZone();
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		String date = sdf.format(d);
		sdf.setTimeZone(t);
		return date;
	}
	
	public static Date getDate(String date){
		try{
			return DATE_FORMAT_WEB.parse(date);
		}
		catch(Exception e){
			logger.error("Could not convert string " + date + " to Date type!!");
			return new Date();
		}
	}

	public static Date getDate(String date,SimpleDateFormat sdf){
		try{
			Date d =  sdf.parse(date);
			return d;
		}
		catch(Exception e){
			logger.error("Could not convert string " + date + " to Date type!!");
			return null;
		}
	}
	
	//this function returns the date with hours, mins, secs set as 23:59:59
	public static Date getDateEndOfDay(String date,SimpleDateFormat sdf){
		try {
			StringBuilder toDate = new StringBuilder(date);
			toDate.append(" 23:59:59");			
			Date d =  sdf.parse(toDate.toString());
			return d;
		}
		catch(Exception e){
			logger.error("Could not convert string " + date + " to Date type!!");
			return null;
		}
	}
	
	public static Date addDaysToDate(Date date, int numOfDays){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, numOfDays);
		return c.getTime();
	}
	
	public static String removeLeadingZeros(String s){
		
		if(s==null || s.length()==0)
			return "";
		
		String strTrimmed = null;
		char [] carr = s.toCharArray();
		int iEnd=0;
//		need this only if string begins with zero
		if (carr[0] == '0') {
			for (int k = 1;k<carr.length;k++) {
			if (carr[k] != '0') {
				iEnd = k-1;
				break;
				}
			} //for loop ends
			strTrimmed = s.substring(iEnd + 1,s.length());
			return strTrimmed;
		} //if ends">
		return s;
	}
	
	public static String padWithLeading(String s, int length, String padString){
		if(s==null)
			return "";
		if(s.length() >= length)
			return s;
		
		int lengthToPad = length - s.length();
		
		StringBuilder stb = new StringBuilder();
		for (int i=0; i<lengthToPad; i++){
			stb.append(padString);
		}
		stb.append(s);
		
		return stb.toString();
		
	}

	public static String maximizeStringLength(String s, int maxLength){
		if(s==null)
			return "";
		if(s.length() <= maxLength)
			return s;
		
		return s.substring(0, maxLength);		
	}
	
	public static final String cleanPhoneNum(String phone){
		if(phone==null || phone.length()==0)
			return "";
		
		return(phone.replaceAll("-", "").replaceAll(" ", ""));	
	}
	
	// remove accentuated from a string and replace with ascii equivalent
    public static String convertNonAscii(String s) {
       if(s==null)
    	   return s;
    	
       StringBuffer sb = new StringBuffer();
       int n = s.length();
       for (int i = 0; i < n; i++) {
          char c = s.charAt(i);
          int pos = UNICODE.indexOf(c);
          if (pos > -1){
              sb.append(PLAIN_ASCII.charAt(pos));
          }
          else {
              sb.append(c);
          }
       }
       return sb.toString();
    }
    
	//to round figure the rates upto 2 decimal places 
	public static double roundFigureRates(double rateValue, int digitsAfterDecimal) {
		BigDecimal value = new BigDecimal(rateValue); 
		value = value.setScale(digitsAfterDecimal, BigDecimal.ROUND_HALF_UP);
		return value.doubleValue();

		
		//		double pTemp = (double)Math.pow(10,digitsAfterDecimal);
//		rateValue = rateValue * pTemp;
//		double tmp = Math.abs(Math.round(rateValue));
//		return (double)tmp/pTemp;
	}
	
	// default to read a double primitive value of 18 digit
	// precision
	public static final NumberFormat DEFAULT_DECIMAL_FORMAT =
	    new DecimalFormat ("#.0#################");
	public static final BigDecimal ZERO = new BigDecimal ("0");

	public static BigDecimal add (double a, double b) {
	    String s = DEFAULT_DECIMAL_FORMAT.format(a);
	    BigDecimal bd = new BigDecimal (s);
	    return add (bd, b);
	}

	public static BigDecimal subtract(double a, double b) {
	    String s = DEFAULT_DECIMAL_FORMAT.format(a);
	    BigDecimal bd = new BigDecimal (s);
	    return add (bd, -1*b);
	}

	public static BigDecimal add (Double a, Double b) {
		if(a==null)
			a = new Double(0);
		if(b==null)
			b = new Double(0);
		
	    return add (a.doubleValue(), b.doubleValue());
	}

	public static BigDecimal add (BigDecimal a, double b) {
	    String s = DEFAULT_DECIMAL_FORMAT.format(b);
	    BigDecimal bd = new BigDecimal (s);
	    return add (a, bd);
	}

	public static BigDecimal add (BigDecimal a, BigDecimal b) {
	    if (a == null) return (b == null) ? ZERO : b;
	    return a.add (b);
	}

	public static double convertKgToLb(BigDecimal kg){
		double lb = kg.doubleValue() * 2.2046;
		return roundFigureRates(lb,2);
	}

}
