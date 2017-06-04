package edu.zjut.tempest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {
	
	public final static String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	public static String datetime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
		return sdf.format(date);
	}
	
	public static Date datetime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
		Date datetime = null;
		try {
			datetime = sdf.parse(date);
		} catch (ParseException e) {
			System.out.println("ParseException: sdf.parse解析失败!");
		}
		return datetime;
	}
	
}
