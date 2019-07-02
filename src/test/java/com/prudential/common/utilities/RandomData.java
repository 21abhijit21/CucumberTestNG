package com.prudential.common.utilities;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;
/**
 * Class for Generic methods
 * @author Abhijit Bhattacharyya
 *
 */
public class RandomData {
	/**
	 * To generate random string
	 * @param maximumLength
	 * @return
	 */
    public static String getCharacterString(int maximumLength) {
        // TODO - add a loop that creates a string as long as needed and not
        // just the standard UUID length
        String charString = UUID.randomUUID().toString() + UUID.randomUUID().toString() 
                + UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString();
        charString = charString.replace("-", "").substring(0, maximumLength);
        return charString;
    }

    /**
     * To generate random date of birth
     * @param startYear
     * @param endYear
     * @return
     */
    public static String getDateOfBirth(int startYear, int endYear) {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(startYear, endYear);
        gc.set(Calendar.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(Calendar.DAY_OF_YEAR));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        String month = Integer.toString(gc.get(Calendar.MONTH));
        String yearS = Integer.toString(gc.get(Calendar.YEAR));
        // TODO - If random day of month is greater than 28 but month is a month that doesn't have
        // 28 days then registration methods will fail. Below is a short/easy work around for now
        String day = "";
        Integer dayPre = gc.get(Calendar.DAY_OF_MONTH);
        if (dayPre > 28) {
            day = "28";
        } else {
            day = Integer.toString(dayPre);
        }
        
        String dob = month.replace("0", "1") + "-" + day + "-" + yearS;
        return dob;
    }

    /**
     * To generate random number
     * @param minValue
     * @param maxValue
     * @return
     */
    public static Integer getInteger(int minValue, int maxValue) {
        java.util.Random rand = new java.util.Random();
        int randomNum = rand.nextInt((maxValue - minValue) + 1) + minValue;
        return randomNum;
    }

    /**
     *To generate random number in the provide range
     * @param start
     * @param end
     * @return
     */
    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

}
