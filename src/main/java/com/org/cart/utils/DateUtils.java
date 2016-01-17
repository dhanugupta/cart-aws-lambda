package com.org.cart.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.org.cart.model.DateObj;

public class DateUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

	private static final String DATEFORMAT="MM-dd-yyyy hh:mm:ss";
	private static final String DATEFORMATNEW="MMddyyyy";

	DateUtils () {
		//NOOP private constructor
	}

	public static Date createDate(Date date) {

		if(date==null) {
			return null;
		}

		return new Date(date.getTime());
	}

	public static Date parseDate(String s) {
		return DatatypeConverter.parseDate(s).getTime();
	}

	public static String printDate(Date dt) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(dt);
		return DatatypeConverter.printDate(cal);
	}

	public static Date toDate(XMLGregorianCalendar xmlDate) {
		if(xmlDate==null) {
			return null;
		}

		return xmlDate.toGregorianCalendar().getTime();
	}

	public static XMLGregorianCalendar toXmlDate(Date date) {
		try {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(date);
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (Exception e) {
			LOGGER.warn("Error converting date to xml : {}", e.getMessage(), e);
			return null;
		}
	}

	public static long daysBetween(Calendar startDate, Calendar endDate) {
		  Calendar date = (Calendar) startDate.clone();
		  long daysBetween = 0;
		  while (date.before(endDate)) {
		    date.add(Calendar.DAY_OF_MONTH, 1);
		    daysBetween++;
		  }
		  return daysBetween;
	}

	public static Calendar dateToCalendar(Date date){
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
	}

	public static Date stringToDate(String str){
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
		try {
			Date date = sdf.parse(str);
			return date;
		} catch (ParseException e) {
			LOGGER.warn("Error in parsing Date : {} ",e.getMessage(),e);
			return null;
		}
	}

	public static boolean isSameDate(String start, String end){
	    SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMATNEW);
	    return dateFormat.format(stringToDate(start)).equals(dateFormat.format(stringToDate(end)));

	}

	public static boolean isSameDate(String start, Date end){
	    SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMATNEW);
	    return dateFormat.format(stringToDate(start)).equals(dateFormat.format(end));

	}

	public static Calendar addNumberDays(Calendar startDate,int number){
		 Calendar date = (Calendar) startDate.clone();
		 date.add(Calendar.DATE, number);
		 return date;
	}

	public static String getStringDate(Date dt){
		String returnDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		returnDate = sdf.format(dt);

		return returnDate;
	}

    public static DateObj dateDifference(String dateStart, String dateStop){

    	//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		DateObj dtObj = new DateObj();

		try {

			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			dtObj.setDays(diffDays);
			dtObj.setHours(diffHours);
			dtObj.setMinutes(diffMinutes);
			dtObj.setSeconds(diffSeconds);

		} catch (Exception e) {
			LOGGER.warn("error warning",e);
		}

    	return dtObj;
    }


}
