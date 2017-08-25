package com.shop.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {

	public static Calendar getCurrentDay() {
		Calendar cal = Calendar.getInstance();
		return cal;
	}

	/**
	 * 获取今天
	 * 
	 * @return
	 */
	public static Date getToday() {
		Calendar cal = getCurrentDay();
		return cal.getTime();
	}

	/**
	 * 获取某一天的后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTomorrow(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, +1);
		return cal.getTime();
	}

	public static String yyyyMMdd(Date date) {
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}

	public static String getHHmmss(Date date) {
		return new SimpleDateFormat("HHmmss").format(date);
	}

	public static String yyyy1MM1dd(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static String yyyyMMddHHmmss(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static Date yyyy1MM1ddHH1mm1ss(String dateStr) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
	}
}
