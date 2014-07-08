package com.meritconinc.mmr.utilities;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class StringUtil {

	private static final Logger log = Logger.getLogger(StringUtil.class);

	public static boolean isEmpty(String text) {
		boolean isEmpty = false;
		if (text == null) {
			isEmpty = true;
		} else if (text.trim().length() == 0) {
			isEmpty = true;
		} else {
			isEmpty = false;
		}
		return isEmpty;
	}

	public static boolean isEmpty(String[] text) {
		boolean isEmpty = false;
		if (text == null) {
			isEmpty = true;
		} else if (text.length == 0) {
			isEmpty = true;
		} else {
			isEmpty = false;
		}
		return isEmpty;
	} 

	public static boolean isDouble(String text) {
		boolean isDouble = false;
		try {
			new Double(text);
			isDouble = true;
		} catch (Exception ex) {
			isDouble = false;
		}
		return isDouble;
	}

	public static boolean isPositiveDouble(String text) {
		boolean isDouble = false;
		try {
			Double value = new Double(text);
			if (value.doubleValue() >= 0)
				isDouble = true;
			else
				isDouble = false;
		} catch (Exception ex) {
			isDouble = false;
		}
		return isDouble;
	}

	public static boolean isPositiveInteger(String text) {
		boolean isInteger = false;
		try {
			Integer value = new Integer(text);
			if (value.intValue() >= 0)
				isInteger = true;
			else
				isInteger = false;
		} catch (Exception ex) {
			log.debug("Exception...");
			isInteger = false;
		}
		return isInteger;
	}

	public static String addSingleQuotes(String str) {
		return new String("'" + (str == null ? "" : str.trim()) + "'");
	}

	public static String numberOnly(String value) {
		StringTokenizer st = new StringTokenizer(value, ",");
		String withoutCommas = "";
		while (st.hasMoreTokens()) {
			withoutCommas = withoutCommas.concat(st.nextToken());
		}
		return withoutCommas;
	}

	public static Double toDoubleOnly(String value) {
		StringTokenizer st = new StringTokenizer(value, ",");
		String withoutCommas = "";
		while (st.hasMoreTokens()) {
			withoutCommas = withoutCommas.concat(st.nextToken());
		}
		int lastIndex = withoutCommas.length() - 1;
		if (withoutCommas.charAt(0) == '('
				&& withoutCommas.charAt(withoutCommas.length() - 1) == ')') {
			withoutCommas = "-" + withoutCommas.substring(1, lastIndex);
		}
		return new Double(withoutCommas);
	}
	
	public static Integer toIntegerOnly(String value) {
		StringTokenizer st = new StringTokenizer(value, ",");
		String result = "";
		String finalResult = "";
		while (st.hasMoreTokens()) {
			result = result.concat(st.nextToken());
		}
		st = new StringTokenizer(result, ".");
		while (st.hasMoreTokens()) {
			finalResult = finalResult.concat(st.nextToken());
		}

		int lastIndex = finalResult.length() - 2;
		if (finalResult.charAt(0) == '('
				&& finalResult.charAt(finalResult.length() - 1) == ')') {
			finalResult = "-" + finalResult.substring(1, lastIndex);
		}

		return new Integer(finalResult);
	}

	public static boolean isDate(String dateString, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		boolean valid = false;
		try {
			formatter.parse(dateString);
			valid = true;
		} catch (Exception e) {
			valid = false;
		}

		return valid;
	}

	/***************************************************************************
	 * Replace all occurrences of <code>o</code> in <code>str</code> with
	 * <code>n</code>, or only the first occurrence if <code>all</code> is
	 * <code>false</code>. <br>
	 * <code>replace("aaaa", "aa", "bbb", false)</code> returns
	 * <code>"bbbaa"</code> <br>
	 * <code>replace("aaaa", "aa", "bbb", true)</code> returns
	 * <code>"bbbbbb"</code>
	 **************************************************************************/
	public static String replace(String str, String o, String n, boolean all) {
		if (str == null || o == null || o.length() == 0 || n == null)
			throw new IllegalArgumentException("null or empty String");
		StringBuffer result = null;
		int oldpos = 0;
		do {
			int pos = str.indexOf(o, oldpos);
			if (pos < 0)
				break;
			if (result == null)
				result = new StringBuffer();
			result.append(str.substring(oldpos, pos));
			result.append(n);
			pos += o.length();
			oldpos = pos;
		} while (all);
		if (oldpos == 0) {
			return str;
		} else {
			result.append(str.substring(oldpos));
			return new String(result);
		}
	}

	public static String trim(String str) {
		return new String(str == null ? "" : str.trim());
	} 

	public static String stripChars(String phoneNumber)
	{   
	    String returnString = "";
	    char c;
	    // Search through string's characters one by one.
	    // If character is not in bag, append to returnString.
	    for (int i = 0; i < phoneNumber.length(); i++)
	    {   
	        // Check that current character isn't whitespace.
	        c = phoneNumber.charAt(i);
	        if ("1234567890".indexOf(c) > -1) {
	        	returnString += c;
	        	if (log.isDebugEnabled())
	        		log.debug("String: " + returnString);
	        }
	    }
	    return returnString;
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
	    char c;
	    for (int i = 0; i < phoneNumber.length(); i++)
	    {   
	        // Check that current character isn't whitespace.
	        c = phoneNumber.charAt(i);
	        if (c != ' ' && "1234567890()+- ".indexOf(c) == -1) {
	        	return false;
	        }
	    }
		/*
		String s=stripChars(phoneNumber);
		try {
			log.debug("Phone#: " + s);
			Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return false;
		}
		*/
		return true;
	}
	public static double getDouble(String text) {
		// TODO Auto-generated method stub
		if (!StringUtil.isEmpty(text))
			return Double.valueOf(text);
		return 0;
	}

	public static BigDecimal getBigDecimal(String text) {
		// TODO Auto-generated method stub
		if (!StringUtil.isEmpty(text))
			return new BigDecimal(text);
		return null;
	}
	
	public static Integer getInteger(String text) {
		// TODO Auto-generated method stub
		if (!StringUtil.isEmpty(text))
			return new Integer(text);
		return null;
	}	
	
	public static String generateRandomString(int length)
	{
		String characters = "0123456789abcdefghijklmnopqrstuvwxyz";
		Random rng = new Random();
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}

	public static boolean toBoolean(String s) {
		// TODO Auto-generated method stub
		if (!StringUtil.isEmpty(s)) {
			s = s.trim().toUpperCase();
			if (s.startsWith("Y") || s.startsWith("T"))
				return true;
		}
		return false;
	}
	
	public static void buildString(StringBuilder stb, String s, boolean addComma){
		if(!isEmpty(s)){
			stb.append(s);
			if(addComma)
				stb.append(", ");
		}
	}
	
	public static String setWithMaxLength(String s, int max){
		if(s==null || s.length()==0)
			return "";
		
		if(s.length()<=max)
			return s;
		
		return s.substring(0, max);
		
	}

	
}
