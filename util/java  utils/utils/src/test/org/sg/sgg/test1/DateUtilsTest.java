package org.sg.sgg.test1;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;
import org.sg.sgg.date.DateUtils;

public class DateUtilsTest {
	
	SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar calendar = null;
	@Test
	public void test1(){
		calendar = DateUtils.getBeforeNDays(2);
		System.out.println(calendar.getTime());
		String date  = s.format(calendar.getTime());
		System.out.println(date);
	}
	@Test
	public void test2(){
		calendar = DateUtils.getAfterNDays(2);
		System.out.println(calendar.getTime());
		String date  = s.format(calendar.getTime());
		System.out.println(date);
	}
	@Test
	public void test3(){
		calendar  = DateUtils.getTomorrowDay();
		System.out.println(calendar.getTime());
		String date  = s.format(calendar.getTime());
		System.out.println(date);
	}
	@Test
	public void test4(){
		calendar  = DateUtils.getYesterDay();
		System.out.println(calendar.getTime());
		String date  = s.format(calendar.getTime());
		System.out.println(date);
	}
	
	@Test
	public void test5(){}
	@Test
	public void test6(){}
	@Test
	public void test7(){}
	@Test
	public void test8(){}
	@Test
	public void test9(){}
}
