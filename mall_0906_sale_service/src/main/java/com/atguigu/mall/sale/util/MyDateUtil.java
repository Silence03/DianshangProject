package com.atguigu.mall.sale.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MyDateUtil {
	
	//日期的加减
	public static Date getMyDate(int days) {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);
		Date date = c.getTime();
		return date;
	}
	//时间的格式
	public static String getMyTime(Date date,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String datestr = sdf.format(date);
		return datestr;
	}
}
