package com.demo.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * ��ȡ����ʱ�伸��֮���ʱ��
	 * @param date
	 * @param n ȡ֮ǰ��ʹ�ø���
	 * @return
	 */
	public static Date getAfterDaysDate(Date date, int n) {
		Calendar  cal =  Calendar.getInstance();
		cal.setTime(date);
	    cal.add(Calendar.DATE, n);
	    return cal.getTime();
	}
	
	/**
	 * ȡ������������
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int getIntervalDays(Date fDate, Date oDate) {
		long intervalMilli = oDate.getTime() - fDate.getTime();
		return (int) (intervalMilli / (24 * 60 * 60 * 1000));
	}
	
}
