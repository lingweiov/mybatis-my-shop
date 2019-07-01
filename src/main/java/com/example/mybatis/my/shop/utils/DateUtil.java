package com.example.mybatis.my.shop.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
/*	public static Date createDate(int year,int month,int date,int hour,int minute,int second){
		Calendar cl = Calendar.getInstance();
		cl.set(year, month-1, date, hour, 
				minute,second);
		return cl.getTime();
	}*/
	//��������
	public static Date createDate(int year,int month,int day)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		return cal.getTime();
	}
	
	public static String weekAboutFristDayofMonth(int year,int month){
		Calendar cl = Calendar.getInstance();
		cl.set(year, month-1,1);
		
		int week = cl.get(Calendar.DAY_OF_MONTH);
		return (week==1)?"������":("����"+(week-1));
	}
	
	public static String formatDate(Date date,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String str = sdf.format(date);
		return str;
	}
	
	/**
	 * ���ַ���ת��������
	 */
	public static Date parse(String sdate,String pattern){
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		try {
			d = sdf.parse(sdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return d;
	}
	
	/*@SuppressWarnings("all")
	public static Integer mulDate(Date start,Date end){
		int minute = end.getMinutes()-start.getMinutes();
		int second = end.getSeconds()-start.getSeconds();
		if(minute==0){
			second = Math.abs(second);
			return second;
		}else{
			return Math.abs(minute)*60+Math.abs(second);
		}
	}*/
}


