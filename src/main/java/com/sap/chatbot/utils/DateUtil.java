package com.sap.chatbot.utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtil {

	/**
	 * Param weekNr - 0,1,2.. Return week of year + 0, 1, 2 (i,e, 19, 20)
	 */
	public static int getWeekOfYear() {

		ZoneId z = ZoneId.of("Europe/Bucharest");
		Instant instant = Instant.now();
		ZonedDateTime zdt = instant.atZone(z);
		Calendar calendar = GregorianCalendar.from(zdt);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

//	public static void main(String[] args) {
//		Locale locale = new Locale("ro", "RO");
//		ZoneId z = ZoneId.of("Europe/Bucharest");
//		Instant instant = Instant.now();
//		ZonedDateTime zdt = instant.atZone(z);
//		System.out.println("zdt:" + zdt);
//
//		DateTimeFormatter f = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(locale);
//		String output = zdt.format(f);
//		System.out.println("Si mai avem:"+output);
//		
//		//Replace when you feel like it, the old calendar should disappear
//		Calendar calendar = GregorianCalendar.from(zdt);
//		if (calendar.isWeekDateSupported()) {
//			System.out.println("avem:"+calendar.get(Calendar.WEEK_OF_YEAR));
//		}
//	}

}