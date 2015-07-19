package com.nus.adqs.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtil {
	
	public static enum CONVERT_FORMAT{ DATE_FMT_DDMMYYYY, DATE_FMT_DDMMMYYYY, DATE_FMT_DDMMMYYYY_EEE, TIME_FMT, DATE_TIMESTAMP_FMT, DATE_TIME_DDMMMYYYYHHMM};
	public static enum ELAPSED_FMT{SECOND,MINUTE,HOUR,DAY,WEEK,MONTH};

	public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");  
	public static final SimpleDateFormat DATE_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat DATE_TIMESTAMP_ASIAN = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final SimpleDateFormat DATE_01_FMT = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat DATE_02_FMT = new SimpleDateFormat("dd-MMM-yyyy");	
	public static final SimpleDateFormat DATE_03_FMT = new SimpleDateFormat("dd/MM/yyyy (EEE)");
	public static final SimpleDateFormat DATE_04_FMT = new SimpleDateFormat("yyyy-MM-dd");
	//public static final SimpleDateFormat DATE_05_FMT = new SimpleDateFormat("yyMMddhh-mmss");
	public static final SimpleDateFormat DATE_05_FMT = new SimpleDateFormat("yyMMddHHmm");
	public static final SimpleDateFormat DATE_06_FMT = new SimpleDateFormat("MM/dd/yyyy");
	public static final SimpleDateFormat DATE_07_FMT = new SimpleDateFormat("dd.MM.yyyy");
	public static final SimpleDateFormat DATE_08_FMT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	public static final SimpleDateFormat DATE_09_FMT = new SimpleDateFormat("MMM yyyy");
	
	public static final SimpleDateFormat DATE_TIME_FMT = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
	public static final SimpleDateFormat DATE_TIME_FMT_WITH_YY = new SimpleDateFormat("dd-MMM-yy HH:mm");
	public static final SimpleDateFormat TIME_FMT = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat TIME_SECOND_FMT = new SimpleDateFormat("HHmmss");
	
	
	
	public static final String DATE_FMT_DDMMYY =  "ddMMyy" ;
	public static final String DATE_FMT_DDMMYYYY =  "dd/MM/yyyy" ;
	public static final String DATE_FMT_DDMMYYYYHHMMSS =  "dd/MM/yyyy HH:mm:ss" ;
	
	public static final SimpleDateFormat DATE_FMT_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat DATE_FMT_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
	
	
	public static final Date MIN_DATE;
	public static final Date MAX_DATE;
	
	static {
		Date minDate = null;
		Date maxDate = null;
		try {
			minDate = dateFormatter.parse("01/01/0001");
			maxDate = dateFormatter.parse("31/12/9999");
		} catch (java.text.ParseException e) {}
		MIN_DATE = minDate;
		MAX_DATE = maxDate;
	}
	
	public static Date getSystemDate(){return new Date();}
	public static TimeZone getTimeZone(){return TimeZone.getDefault();}
	
	public static Date getNextDate() {
		Calendar nextDay = Calendar.getInstance();
		nextDay.add (Calendar.DAY_OF_YEAR, 1);
		return nextDay.getTime();
	}
	
	public static Calendar getCalendar(Date date){
		Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
	}
	
	public static Calendar getCalendar(Long time){
		Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(time);
        return calendar;
	}
	
	public static Date getStartOfDay(Calendar cal){
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}
	
	public static Date setPreciseHour(Date date, int hour){
		if(date == null) return null;
		Calendar cal = getCalendar(date);
    	cal.set(Calendar.HOUR_OF_DAY, hour);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
    	cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getStartOfDay(Date date){
		if(date == null) return null;
		
		Calendar cal = getCalendar(date);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}
	
	public static Date getStartOfHour(Date date){
		if(date == null) return null;
		
		Calendar cal = getCalendar(date);
    	cal.set(Calendar.MILLISECOND, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}
	
	public static Date getMidOfDay(Date date){
		Calendar cal = getCalendar(date);
    	cal.set(Calendar.HOUR_OF_DAY, 12);
    	cal.set(Calendar.MILLISECOND, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}
	
	public static Date getEndOfDay(Calendar cal){
    	cal.set(Calendar.HOUR_OF_DAY, 23);
    	cal.set(Calendar.MILLISECOND, 999);
    	cal.set(Calendar.SECOND, 59);
    	cal.set(Calendar.MINUTE, 59);
		return cal.getTime();
	}
	
	public static Date getEndOfDay(Date date){
		if(date == null) return null;
		
		Calendar cal = getCalendar(date);
    	cal.set(Calendar.HOUR_OF_DAY, 23);
    	cal.set(Calendar.MILLISECOND, 999);
    	cal.set(Calendar.SECOND, 59);
    	cal.set(Calendar.MINUTE, 59);
		return cal.getTime();
	}
	
	public static Date getEndOfHour(Date date){
		if(date == null) return null;
		
		Calendar cal = getCalendar(date);
    	cal.set(Calendar.MILLISECOND, 999);
    	cal.set(Calendar.SECOND, 59);
    	cal.set(Calendar.MINUTE, 59);
		return cal.getTime();
	}
	
	public static Date getStartTimeLimit() {
		Calendar cal = DateUtil.getCalendar(getSystemDate());
		cal.set(Calendar.YEAR, 1900);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static Date getEndTimeLimit() {
		Calendar cal = DateUtil.getCalendar(getSystemDate());
		cal.set(Calendar.YEAR, 4444);
		cal.set(Calendar.MONTH, 12);
		cal.set(Calendar.DATE, 31);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal.getTime();
	}


	public static Date copyDayMonthYear(Date srcDate, Date desDate){
		Calendar srcCalendar = getCalendar(srcDate);
		Calendar desCalendar = getCalendar(desDate);
		desCalendar.set(Calendar.YEAR, srcCalendar.get(Calendar.YEAR));
		desCalendar.set(Calendar.MONTH, srcCalendar.get(Calendar.MONTH));
		desCalendar.set(Calendar.DATE, srcCalendar.get(Calendar.DATE));
    	return desCalendar.getTime();
	}
	
	public static Date addTime(Date date, int type, int value){
		Calendar calendar = getCalendar(date);
		calendar.add(type, value);
		return calendar.getTime();
	}
	
	public static Date setTime(Date date, int type, int value){
		Calendar calendar = getCalendar(date);
		calendar.set(type, value);
		return calendar.getTime();
	}
	
	public static int[] getElapsedTime(Date dateFrom, Date dateTo){
		long timeInSeconds = dateTo.getTime() - dateFrom.getTime()/1000;
		
		int[] time = new int[3];
		time[0] = (int) (timeInSeconds / 3600);
		timeInSeconds = timeInSeconds - (time[0] * 3600);
		time[1] = (int) (timeInSeconds / 60);
		time[2] = (int) timeInSeconds - (time[1] * 60);
		
		return time;
	}
	
	public static int getElapsedTimeInMinutes(Date dateFrom, Date dateTo){
		if(dateFrom == null || dateTo == null)
			return 0;
		
		long timeInSeconds = (dateTo.getTime()/1000) - (dateFrom.getTime()/1000);
		return (int)timeInSeconds/60;
	}
	
	public static long getDateDifference(Date dateFrom, Date dateTo,ELAPSED_FMT fmt){
		long timeInMillis = dateTo.getTime() - dateFrom.getTime();
		long variable = 1;
		switch (fmt) {
			case SECOND : variable = 1000; break;
			case MINUTE : variable =  (60 * 1000); break;
			case HOUR : variable = (60 * 60 * 1000); break;
			case DAY : variable = (24 * 60 * 60 * 1000); break;
			case WEEK : variable = (7 * 24 * 60 * 60 * 1000); break;
			case MONTH : variable = (30 * 24 * 60 * 60 * 1000); break;
			default : variable = 1;
		}
		return  (new BigDecimal(timeInMillis).divide(new BigDecimal(variable),0,BigDecimal.ROUND_UP)).longValue();
	}
	
	public static int formatMillis(Long timeInMillis, ELAPSED_FMT fmt){
		if(timeInMillis == null)
			return 0;
		
		long variable = 1;
		switch (fmt) {
			case SECOND : variable = 1000; break;
			case MINUTE : variable =  (60 * 1000); break;
			case HOUR : variable = (60 * 60 * 1000); break;
			case DAY : variable = (24 * 60 * 60 * 1000); break;
			case WEEK : variable = (7 * 24 * 60 * 60 * 1000); break;
			case MONTH : variable = (30 * 24 * 60 * 60 * 1000); break;
			default : variable = 1;
		}
		
		return  (new BigDecimal(timeInMillis).divide(new BigDecimal(variable),0,BigDecimal.ROUND_UP)).intValue();
	}
	
	public static String convertDateToString(Date date, CONVERT_FORMAT fmt){
		if(date == null)
			return "";
		
		switch (fmt) {
			case DATE_FMT_DDMMYYYY : return DATE_01_FMT.format(date);
			case DATE_FMT_DDMMMYYYY : return DATE_02_FMT.format(date);
			case DATE_FMT_DDMMMYYYY_EEE : return DATE_03_FMT.format(date);
			case DATE_TIMESTAMP_FMT : return DATE_TIMESTAMP_ASIAN.format(date);
			case TIME_FMT : return TIME_FMT.format(date);
			case DATE_TIME_DDMMMYYYYHHMM: return DATE_TIME_FMT.format(date);
			default : return "";
		}
	}
	
	public static String convertDateToString(Date date, String fmt){
		if(date == null || fmt == null)
			return "";
		
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(date);
	}
	
	public static Date convertStringToDate(String date, String fmt){
		if(StringUtil.isEmpty(date) || fmt == null)
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Date convertXMLGregorianCalendarToDate(XMLGregorianCalendar xmlGregorianCalendar){
		return (xmlGregorianCalendar == null) ? null : xmlGregorianCalendar.toGregorianCalendar().getTime();
	}
	
	public static XMLGregorianCalendar convertDateToXMLGregorianCalendar(Date currentDate) throws DatatypeConfigurationException{
		if(currentDate == null) return null;
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(currentDate);
		return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
	}
	
	public static java.sql.Date convertUtilDateToSqlDate(Date date) {
		if (date != null)
			return new java.sql.Date(date.getTime());
		return null;
	}
	
	public static java.sql.Timestamp convertUtilDateToTimestamp(Date date) {
		if (date != null)
			return new java.sql.Timestamp(date.getTime());
		return null;
	}	
	
	public static Calendar clearTime(Calendar cal){
		if(cal == null) return null;
		
		cal.clear(Calendar.HOUR);
		cal.clear(Calendar.HOUR_OF_DAY);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.clear(Calendar.AM_PM);
		return cal;
	}
	
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static int getCurrentMonth() {
		//Calendar Month start from 0 to 11
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}
	
	public static Date parseDateWith2DigitYear(
	        String dateString,
	        String dateFormat,
	        String nullDateFlag)
	    {
	       
	        if( dateString == null ){
	            return null;
	        }
	    
	        if (nullDateFlag.equalsIgnoreCase(dateString)) {
	            return null;
	        }
	    
	        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
	        df.setLenient(false);
	        
	        //Any dates with 2 digit year > 30 will be considered in the 1900s
	        df.set2DigitYearStart(new GregorianCalendar(1930, 0, 1).getTime()); 
	        
	        try {
	            Date parsedDate = df.parse(dateString);
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(parsedDate);
	    
	            if (cal.get(Calendar.YEAR) > 9999) {
	                throw new IllegalArgumentException("date '" + dateString
	                    + "' cannot have year greater then 9999 '");
	            }
	    
	            return parsedDate;
	        } catch (ParseException e) {
	            throw new IllegalArgumentException("date '" + dateString
	                + "' was not in format '" + dateFormat + "'", e);
	        }
	    }
	
	public static Date parseDate(
	        String dateString,
	        String dateFormat)
	    {
	       
	        if( dateString == null ){
	            return null;
	        }
	    
	            
	        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
	        df.setLenient(false);
	        
	        try {
	            Date parsedDate = df.parse(dateString);
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(parsedDate);
	    
	            if (cal.get(Calendar.YEAR) > 9999) {
	                throw new IllegalArgumentException("date '" + dateString
	                    + "' cannot have year greater then 9999 '");
	            }
	    
	            return parsedDate;
	        } catch (ParseException e) {
	            throw new IllegalArgumentException("date '" + dateString
	                + "' was not in format '" + dateFormat + "'", e);
	        }
	    }

	public static List<Date> getDatesOfWeek(Date date){
		
		List<Date> datesOfWeek = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);        
        calendar.add(Calendar.DATE, (-dayOfWeek+2));        
        int i= 0;
        while(i<7){
        	datesOfWeek.add(calendar.getTime());  
        	calendar.add(Calendar.DATE, 1);
       	  	i+=1;
        }
	   return datesOfWeek;
	
	}
	
	public static List<Date> getDatesOfCurrentWeek(){
		return getDatesOfWeek(new Date());
	}
	
	public static Date getMergedDateAndTime(Date date, String timeInHourMin){
		String timeInHourMinIt[] = timeInHourMin.split(":");
		Date dateSet = setTime(DateUtil.getStartOfDay(date), Calendar.HOUR_OF_DAY, Integer.valueOf(timeInHourMinIt[0]));
		dateSet = setTime(dateSet, Calendar.MINUTE, Integer.valueOf(timeInHourMinIt[1]));
		return dateSet;
	}

	public static long getAgeInDay(Date dob) {
		long ageInDay = 0;
		Calendar dateOfBirthCalendar = Calendar.getInstance();
		dateOfBirthCalendar.setTime(dob);
		Calendar nowCalendar = Calendar.getInstance();
		nowCalendar.setTimeZone(DateUtil.getTimeZone());
		
		long diff = nowCalendar.getTimeInMillis()-dateOfBirthCalendar.getTimeInMillis();
		ageInDay = diff / (1000 * 60 * 60 * 24);
		
		return ageInDay;
	}
	
	public static long getAgeInYear(Date dob){
		long ageInDay = getAgeInDay(dob);
		BigDecimal age = new BigDecimal(ageInDay/365.24).setScale(0, BigDecimal.ROUND_DOWN);
		return age.longValue();
	}
	
	public static long getAgeInMonth(Date dob){
		long ageInDay = getAgeInDay(dob);
		BigDecimal age = new BigDecimal(ageInDay/365.24*12).setScale(0, BigDecimal.ROUND_DOWN);
		return age.longValue();
	}
	
	public static Date getStartDateOfCurrentMonth(){
		return getStartDateOfMonth(new Date());
	}
	
	public static Date getLastDateOfCurrentMonth(){
        return getLastDateOfMonth(new Date());
	}
	
	public static Date getStartDateOfMonth(Date date){
		 Calendar cal = Calendar.getInstance();  
         cal.setTime(date);  
         cal = clearTime(cal);
         cal.set(Calendar.DAY_OF_MONTH, 1);  
         return cal.getTime();
	}
	
	public static Date getLastDateOfMonth(Date date){
		    Calendar cal = Calendar.getInstance();  
	        cal.setTime(date);  
	        cal = clearTime(cal);
	        int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	        cal.set(Calendar.DAY_OF_MONTH, lastDate);  
	        return cal.getTime();
	}
	
	// TODO:: need to change on the calling method, too improper
	public static void validateDateTimeYYYYMMDDHHMMSS(String date) throws Exception{
		if(null != date && date.length() == 14){
			String year = date.substring(0,4);
			String month = date.substring(4, 6);
			String day = date.substring(6, 8);
			String hh = date.substring(8, 10);
			String min = date.substring(10, 12);
			String ss = date.substring(12, 14);
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(year));
	    	cal.set(Calendar.MONTH, (Integer.parseInt(month)-1));
	    	cal.set(Calendar.DATE, Integer.parseInt(day));
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hh));
			cal.set(Calendar.SECOND, Integer.parseInt(ss));
	    	cal.set(Calendar.MINUTE, Integer.parseInt(min));
	    	if(!date.equals(DATE_FMT_YYYYMMDDHHMMSS.format(cal.getTime())))
			throw new Exception("Date format is wrong");
		}else{
			throw new Exception("Date format is wrong");
		}
		
	}
	
	public static Date getStartOfYear(Date date){
		if(date == null) return null;
		
		Calendar cal = getCalendar(date);
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_YEAR, 1);
    	cal.set(Calendar.HOUR_OF_DAY, 0);
    	cal.set(Calendar.MILLISECOND, 0);
    	cal.set(Calendar.SECOND, 0);
    	cal.set(Calendar.MINUTE, 0);
		return cal.getTime();
	}
	
	public static Date toDate(Long time) {
        return new Date(time);
    }
	
	public static String getTimeDifferenceToCurrent(Date d) {
        return getTimeDifference(DateUtil.getSystemDate(), d);
    }
	
	public static String getTimeDifference(Date a, Date b) {
        long time = a.getTime() > b.getTime() ? a.getTime() - b.getTime() : b.getTime() - a.getTime();
        int seconds = (int)((time/1000) % 60);
        int minutes = (int)((time/60000) % 60);
        int hours = (int)((time/3600000) % 24);
        String secondsStr = (seconds<10 ? "0" : "")+seconds;
        String minutesStr = (minutes<10 ? "0" : "")+minutes;
        String hoursStr = (hours<10 ? "0" : "")+hours;
        return hoursStr + "h:" + minutesStr + "m:" + secondsStr + "s";
    }

	/*public static void main(String args[]){
		try{
			System.out.println("20120216114423");
			System.out.println(FaplDateUtil.DATE_FMT_YYYYMMDDHHMMSS.parse("20120216114423"));
			//System.out.println(FaplDateUtil.validateDateTimeYYYYMMDDHHMMSS("20120216114423"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/

}
