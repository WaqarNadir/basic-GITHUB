package com.erp.classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class functions {
	public static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat DateformatSlash = new SimpleDateFormat("dd/MM/YYYY");

	public static Date formatDate(String dateString) {

		try {
			Date date = format.parse(dateString);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getCurrentDate() {
		Date currentDate = new Date(System.currentTimeMillis());
		return DateformatSlash.format(currentDate);
	}

	public static java.sql.Date addDays(int days, Date date) {
		Date currentDate = new Date(System.currentTimeMillis());
		if (date != null) {
			currentDate = date;
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(currentDate);
		cal.add(Calendar.DATE, days);
		Long timeInMilli = cal.getTimeInMillis();
		java.sql.Date endDate = new java.sql.Date(timeInMilli);
		return endDate;
	}

	public static java.sql.Date getSQLDate(String dateString) {
		Date utilDate = formatDate(dateString);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;

	}

}
