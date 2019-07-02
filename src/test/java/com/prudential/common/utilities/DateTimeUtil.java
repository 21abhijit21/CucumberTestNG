package com.prudential.common.utilities;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Class for Date & Time methods
 * @author Abhijit Bhattacharyya
 *
 */
public class DateTimeUtil {
	/**
	 * To get system date
	 */
	public static String getSystemDate(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		String sysDate = dateFormat.format(date);
		return sysDate;
	}

	/**
	 * To get time stamp
	 */
	public static String getCurrentTimestamp() {
		String timeStamp = new Timestamp(System.currentTimeMillis()).toString();
		timeStamp = timeStamp.replaceAll("[ :.]", "_");
		return timeStamp;
	}
	
	/**
	 * To get total execution time 
	 */
	public static String getTotalTime(Date startTime, Date endTime)
	{		
		if (endTime == null)	
		{
			endTime = new Date();
			endTime = new Date();
		}
		
		long diff = endTime.getTime() - startTime.getTime();				
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffSeconds = diff / 1000 % 60;
		return diffHours + ":" + diffMinutes + ":" + diffSeconds;									
	}	
}
