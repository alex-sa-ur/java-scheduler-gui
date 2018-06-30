package edu.miami.cis324.hw4.alejandrosanchez;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/*
 * Class used for:
 * - Calculating Date differences
 * - Conversions between String and Date
 */

/**
 * @author alejandrosanchez
 *
 */

public abstract class DateUtil {
	private static Date 				today 			= new Date();
	private static SimpleDateFormat 	defaultFormat 	=  new SimpleDateFormat("yyyy-MM-dd, HH:mm");
	
	/*
	 * Methods:
	 * - dayDifference(): 		returns number of days between today and another day
	 * 							returns > 0 if other day is after today
	 * 							returns < 0 if other day is before today
	 * - yearDifference():		calls dayDifference() and divides by 365
	 * - getFromString(): 		uses a SimpleDateFormat to convert text into a Date object
	 * - dateToString():		formats a Date according to a SimpleDateFormat
	 */
	
	public static Integer dayDifference(Date other) {
		return (Integer) ((int) ((other.getTime() - today.getTime())/ (1000 * 60 * 60 * 24)) + 1);
	}
	
	public static Integer yearDifference(Date other) {
		return (dayDifference(other))/365;
	}
	
	public static Date getFromString(String date) {
		return getFromString(date, defaultFormat);
	}
	
	public static Date getFromString(String date, SimpleDateFormat format) {
		try {
			return format.parse(date);
		} catch (ParseException e) {
			System.out.print("Exception converting String to Date: " + e);
			return null;
		}
	}
	
	public static String dateToString(Date date, SimpleDateFormat format) {
		return format.format(date);
	}
	
	public static String dateToString(Date date) {
		return dateToString(date, defaultFormat);
	}
}
