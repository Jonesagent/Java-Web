package org.sg.sgg.date;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtils extends Date {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(Date.class);
	
	
	public DateUtils() {
		super();
	}
	
	
	/**
	 * 得到当前年
	 * @return
	 */
	public static int getCurrYear(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
	/**
	 * 得到当前月份
	 * 注意，这里的月份依然是从0开始的
	 * @return
	 */
	public static int getCurrMonth(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH);
	}
	/**
	 * 得到当前日
	 * @return
	 */
	public static int getCurrDay(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 得到当前星期
	 * @return
	 */
	public static int getCurrWeek(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * 得到当前小时
	 * @return
	 */
	public static int getCurrHour(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR);
	}
	/**
	 * 得到当前分钟
	 * @return
	 */
	public static int getCurrMinute(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}
	/**
	 * 得到当前秒
	 * @return
	 */
	public static int getCurrSecond(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.SECOND);
	}
	/**
	 * Date类型转换到Calendar类型
	 * @param date
	 * @return
	 */
	public static Calendar Date2Calendar(Date date){
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 return cal;
	}
	/**
	 * Calendar类型转换到Date类型
	 * @param cal
	 * @return
	 */
	public static Date calendar2Date(Calendar cal){
		return cal.getTime();
	}
	/**
	 * Date类型转换到Timestamp类型
	 * @param date
	 * @return
	 */
	public static Timestamp date2Timestamp(Date date){
		return new Timestamp(date.getTime());
	}
	/**
	 * Calendar类型转换到Timestamp类型
	 * @return
	 */
	public static Timestamp calendar2Timestamp(Calendar cal){
		return new Timestamp(cal.getTimeInMillis());
	}
	/**
	 * Timestamp类型转换到Calendar类型
	 * @param timestamp
	 * @return
	 */
	public static Calendar timestamp2Calendar(Timestamp timestamp){
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp);
		return cal;
	}
	/**
	 * 当前时间的下一天时间
	 * 
	 * @return
	 */
	public static Date nextDate() {
		return nextDate(new DateUtils(), 1);
	}

	/**
	 * 得到当前时间的毫秒数
	 * 
	 * @return
	 */
	public static Long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取任意时间后num天的时间
	 * 
	 * @param date
	 *            java.util.Date
	 * @param num
	 * @return
	 */
	public static Date nextDate(Date date, int num) {
		Calendar cla = Calendar.getInstance();
		cla.setTime(date);
		cla.add(Calendar.DAY_OF_YEAR, num);
		return cla.getTime();
	}

	/**
	 * 获取任意时间后num天的时间
	 * 
	 * @param date
	 *            String; <br>
	 *            格式支持�?<br>
	 *            &nbsp;&nbsp; yyyy-MM-dd HH:mm:ss <br>
	 *            &nbsp;&nbsp; yyyy年MM月dd日HH时mm分ss�?<br>
	 *            &nbsp;&nbsp; yyyy/MM/dd HH:mm:ss <br>
	 *            &nbsp;&nbsp; 默认时间格式
	 * @param num
	 *            int
	 * @return java.util.Date
	 * @throws ParseException
	 */
	public static Date nextDate(String date, int num) throws ParseException {
		if (date == null)
			return null;
		SimpleDateFormat sdf = null;
		if (date.indexOf("-") != -1)
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		else if (date.indexOf("-") != -1)
			sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss");
		else if (date.indexOf("/") != -1)
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		else if (date.indexOf("CST") != -1)
			sdf = new SimpleDateFormat();
		else
			System.out.println("no match format:");
		return nextDate(sdf.parse(date), num);
	}

	/**
	 * 获取当天时间num天后的时间<br>
	 * 如果num小于0则返回当前时间的前num天的时间<br>
	 * &nbsp;&nbsp;&nbsp;，否则返回当天时间后num天的时间
	 * 
	 * @param num
	 *            int;
	 * @return java.util.Date
	 */
	public static Date nextDate(int num) {
		return nextDate(new Date(), num);
	}

	/**
	 * 取得当前日期是多少周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		/**
		* 设置一年中第一个星期所需的最少天数，例如，如果定义第一个星期包含一年第一个月的第一天，则使用值 1 调用此方法。
		 * 如果最少天数必须是一整个星期，则使用值 7 调用此方法。
		 **/
		c.setMinimalDaysInFirstWeek(1);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 得到某一年周的总数
	 * 
	 * @param year
	 * @return
	 */
	public static int getMaxWeekNumOfYear(int year) {
		Calendar c = Calendar.getInstance();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}

	/**
	 * 得到某年某周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(int year, int week) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 设置周一
		c.setFirstDayOfWeek(Calendar.MONDAY);
		return c.getTime();
	}

	/**
	 *得到当周的第一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);// 设置周一
		c.setFirstDayOfWeek(Calendar.MONDAY);
		return c.getTime();
	}

	/**
	 * 得到某年某周的最后一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(int year, int week) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.WEEK_OF_YEAR, week);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 得到当前周的周的最后一天
	 * 
	 * @param year
	 * @param week
	 * @return
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 得到某年某月的第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getFirstDayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 获得当前月的第一天
	 * 
	 * @param year
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 *得到某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastDayOfMonth(int year, int month) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 获得当前月的最后一天
	 * 
	 * @param year
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 得到某年某季度第一天
	 * 
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getFirstDayOfQuarter(int year, int quarter) {
		int month = 0;
		if (quarter > 4) {
			return null;
		} else {
			month = (quarter - 1) * 3 + 1;
		}
		return getFirstDayOfMonth(year, month);
	}

	/**
	 * 得到某年某季度最后一天
	 * 
	 * @param year
	 * @param quarter
	 * @return
	 */
	public static Date getLastDayOfQuarter(int year, int quarter) {
		int month = 0;
		if (quarter > 4) {
			return null;
		} else {
			month = quarter * 3;
		}
		return getLastDayOfMonth(year, month);
	}

	/**
	 *  得到某年第一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getFirstDayOfYear(int year) {
		return getFirstDayOfQuarter(year, 1);
	}
	/**
	 * 得到当年第一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getFirstDayOfYear(Date date) {
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		int year=c.get(Calendar.YEAR);
		return getFirstDayOfYear(year);
	}
	/**
	 * 得到某年最后一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getLastDayOfYear(int year) {
		return getLastDayOfQuarter(year, 4);
	}
	/**
	 * 得到当年最后一天
	 * 
	 * @param year
	 * @return
	 */
	public static Date getLastDayOfYear(Date date) {
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		int year=c.get(Calendar.YEAR);
		return getLastDayOfYear(year);
	}
	 /**
     * 功能：获取本周的开始时间
     * 示例：2013-05-13 00:00:00
     */   
	public static Date getWeekStart() {// 当周开始时间
            Calendar currentDate = Calendar.getInstance();
            currentDate.setFirstDayOfWeek(Calendar.MONDAY);
            currentDate.set(Calendar.HOUR_OF_DAY, 0);
            currentDate.set(Calendar.MINUTE, 0);
            currentDate.set(Calendar.SECOND, 0);
            currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            return (Date) currentDate.getTime();
    }
	
	
	 /**
     * 功能：获取本周的结束时间
     * 示例：2013-05-19 23:59:59
     */   
    public static Date getWeekEnd() {// 当周结束时间
            Calendar currentDate = Calendar.getInstance();
            currentDate.setFirstDayOfWeek(Calendar.MONDAY);
            currentDate.set(Calendar.HOUR_OF_DAY, 23);
            currentDate.set(Calendar.MINUTE, 59);
            currentDate.set(Calendar.SECOND, 59);
            currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            return (Date) currentDate.getTime();
    }
    /**
     * 得到指定或者当前时间前n天的Calendar
     * @param day
     * @return
     */
    @SuppressWarnings("unused")
	public static Calendar getBeforeNDays(int n){
    	Calendar cal = null;
    	//偏移量，给定n天的毫秒数
    	long offset = n * 24 * 60 * 60 * 1000;
    	if (cal != null) {
			cal.setTimeInMillis(cal.getTimeInMillis() - offset);
		}else {
			cal = Calendar.getInstance();
			cal.setTimeInMillis(cal.getTimeInMillis() - offset);
		}
    	return cal;
    }
    /**
     * 得到指定或者当前时间后n天的Calendar
     * @param n
     * @return
     */
    @SuppressWarnings("unused")
	public static Calendar getAfterNDays(int n){
    	Calendar cal = null;
    	//偏移量，给定n天的毫秒数
    	long offset = n*24*60*60*1000;
    	if (cal != null) {
    		cal.setTimeInMillis(cal.getTimeInMillis() + offset);
		}else {
			cal = Calendar.getInstance();
			cal.setTimeInMillis(cal.getTimeInMillis() + offset);
		}
    	return cal;
    }
    /**
     * 获取当前时间的后一天
     * @return
     */
    @SuppressWarnings("unused")
	public static Calendar getTomorrowDay(){
    	 long offset = 1*24*60*60*1000;
    	 Calendar cal = null;
    	 if (cal != null) {
    		 cal.setTimeInMillis(cal.getTimeInMillis() + offset);
		}else {
			cal = Calendar.getInstance();
			cal.setTimeInMillis(cal.getTimeInMillis() + offset);
		}
    	 return cal;
    }
    /**
     * 获取当前时间的上一天
     * @return
     */
    @SuppressWarnings("unused")
	public static Calendar getYesterDay(){
    	 long offset = 1*24*60*60*1000;
    	 Calendar cal = null;
    	 if (cal != null) {
			cal.setTimeInMillis(cal.getTimeInMillis() - offset);
		}else {
			cal = Calendar.getInstance();
			cal.setTimeInMillis(cal.getTimeInMillis() - offset);
		}
    	 return cal;
    	 
    }
}
