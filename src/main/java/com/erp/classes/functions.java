package com.erp.classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class functions {
	static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public static Date convertDate(String dateString) {

		try {
			Date date = format.parse(dateString);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
