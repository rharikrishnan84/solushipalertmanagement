package com.meritconinc.mmr.utilities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {

	private static final Logger log = Logger.getLogger(DateUtil.class);

	public static Calendar getToday() {
		Calendar rightNow = Calendar.getInstance();
		return rightNow;
	}

	public static Date getDate(int diff) {
		Calendar newDate = getToday();
		newDate.add(Calendar.DATE, diff);
		return newDate.getTime();
	}
	public static Date convertStringToDate(String dateString, String pattern) {
		if ( !StringUtil.isEmpty(dateString)  && !StringUtil.isEmpty(pattern)) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			try {
				return formatter.parse(dateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

		return null;
	}

	public static String convertDateToString(Date fromDateTime, String pattern) {
		// TODO Auto-generated method stub
		if ( fromDateTime != null  && !StringUtil.isEmpty(pattern)) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			try {
				return formatter.format(fromDateTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public static Timestamp convert(Date date) {
		// TODO Auto-generated method stub
		if (date != null) {
			Timestamp t = new Timestamp(date.getTime());
			return t;
		}
		return null;
	}	
}
