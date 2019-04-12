package pro.bank.utils;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateFormat {

	/**
	 * 字符串转日期
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static Date StringToDate(String dateStr, String formatStr) {
		SimpleDateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	public static String getAge(int age) {
		Calendar cale = Calendar.getInstance();
		int year = cale.get(Calendar.YEAR);
		String result = year - age + "";
		return result.substring(result.length() - 2, result.length() - 1) + "0";
	}

	public static String get30day(int num) {
		long time = System.currentTimeMillis() + (1000L * 60 * 60 * 24 * num);
		String pattern = "yyyy-MM-dd";
		Date date = new Date();
		if (time > 0) {
			date.setTime(time);
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String DateToString(Date date, String formatStr) {
		SimpleDateFormat dd = new SimpleDateFormat(formatStr);
		String currDate = dd.format(date);
		return currDate;
	}

	/**
	 * 获取当月第一天
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String DateOfMonthFirst() {
		SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
		// 获取当月第一天：
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String first = monthformat.format(c.getTime());
		return first + " 00:00:00";
	}

	/**
	 * 获取前月的第一天
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String DateOfLastMonthFirst() {
		SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
		// 获取前月的第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.add(Calendar.MONTH, -1);
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String format2 = monthformat.format(cal_1.getTime());
		return format2 + " 00:00:00";
	}

	/**
	 * 获取当月最后一天
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String DateOfMonthEnd() {
		SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
		// 获取当月最后一天
		Calendar ca = Calendar.getInstance();
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = monthformat.format(ca.getTime());
		return last + " 23:59:59";
	}

	/**
	 * 获取前月的最后一天
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String DateOfLastMonthEnd() {
		SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
		// 获取前月的最后一天
		Calendar cale = Calendar.getInstance();
		cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		String format3 = monthformat.format(cale.getTime());
		return format3 + " 23:59:59";
	}

	public static Date beginTimeFormat(Date beginTime) {
		String begin = DateFormat.DateToString(beginTime, "yyyy-MM-dd");
		begin += " 00:00";
		return DateFormat.StringToDate(begin, "yyyy-MM-dd HH:mm");
	}

	public static Date endTimeFormat(Date endTime) {
		String end = DateFormat.DateToString(endTime, "yyyy-MM-dd");
		end += " 23:59";
		return DateFormat.StringToDate(end, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 查询未来第3天日期
	 * 
	 * @return
	 */
	public static Date DateOf3Day() {
		SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
		// 获取当月最后一天
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, 1);
		String last = monthformat.format(ca.getTime());
		Date stringToDate = StringToDate(last + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
		return stringToDate;
	}

	/**
	 * 查询未来第7天日期
	 * 
	 * @return
	 */
	public static Date DateOfDay1(Date time, int day) {
		SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取当月最后一天
		Calendar ca = Calendar.getInstance();
		ca.setTime(time);
		ca.add(Calendar.DAY_OF_MONTH, day);
		String last = monthformat.format(ca.getTime());
		Date stringToDate = StringToDate(last, "yyyy-MM-dd HH:mm:ss");
		return stringToDate;
	}

	/**
	 * 比较两个日期的大小
	 * 
	 * @return
	 */
	public static int compare_date(Date dt1, Date dt2) {
		try {
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
	}

	/**
	 * 查询未来第7天日期
	 * 
	 * @return
	 */
	public static Date DateOf7Day() {
		SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd");
		// 获取当月最后一天
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.DAY_OF_MONTH, 7);
		String last = monthformat.format(ca.getTime());
		Date stringToDate = StringToDate(last + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
		return stringToDate;
	}

	public static Date mhkDate(int month) {
		Date date = new Date();// 当前日期
		Calendar calendar = Calendar.getInstance();// 日历对象
		calendar.setTime(date);// 设置当前日期
		calendar.add(Calendar.MONTH, month);// 月份减一
		return calendar.getTime();
	}

	// 按天计息 获取未来几天
	public static Date timeDate(int time) {
		Calendar c = Calendar.getInstance();
		// 当前的day_of_month加一就是明天时间
		c.add(Calendar.DAY_OF_MONTH, time);
		return c.getTime();
	}
	//获取相差天数
	public static int getTimeDistance(Date beginDate, Date endDate) {
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.setTime(beginDate);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(endDate);
		long beginTime = beginCalendar.getTime().getTime();
		long endTime = endCalendar.getTime().getTime();
		int betweenDays = (int) ((endTime - beginTime) / (1000 * 60 * 60 * 24));// 先算出两时间的毫秒数之差大于一天的天数

		endCalendar.add(Calendar.DAY_OF_MONTH, -betweenDays);// 使endCalendar减去这些天数，将问题转换为两时间的毫秒数之差不足一天的情况
		endCalendar.add(Calendar.DAY_OF_MONTH, -1);// 再使endCalendar减去1天
		//加上当天的时间
		betweenDays +=1;
		if (beginCalendar.get(Calendar.DAY_OF_MONTH) == endCalendar.get(Calendar.DAY_OF_MONTH))// 比较两日期的DAY_OF_MONTH是否相等
			return betweenDays + 1; // 相等说明确实跨天了
		else
			return betweenDays + 0; // 不相等说明确实未跨天
	}
	
	
	public static long get2days(Date start, Date end, Date start1,Date end1) throws ParseException{
        long reapertdays = 0;//重叠天数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 标准时间
        Date bt = start;
        Date ot = end;
        // 目标时间
        Date st = start1;
        Date ed = end1;

        long btlong = Math.min(bt.getTime(), ot.getTime());// 开始时间
        long otlong = Math.max(bt.getTime(), ot.getTime());// 结束时间
        long stlong = Math.min(st.getTime(), ed.getTime());// 开始时间
        long edlong = Math.max(st.getTime(), ed.getTime());// 结束时间
        if ((stlong >= btlong && stlong <= otlong) || (edlong >= btlong && edlong <= otlong)) {
            // 一定有重叠部分
            long sblong = stlong >= btlong ? stlong : btlong;
            long eblong = otlong >= edlong ? edlong : otlong;
            String sblongs = sdf.format(sblong);
            String eblongs = sdf.format(eblong);

            Date d1=sdf.parse(sblongs);
            Date d2=sdf.parse(eblongs);

            reapertdays=(d2.getTime()-d1.getTime()+1000000)/(3600*24*1000);//重叠天数
            int timeDistance = getTimeDistance(start1, end1);
            return reapertdays > 0?reapertdays+1:reapertdays;
        }
        return reapertdays;  
  }
	
	
	public static void main(String[] args) throws ParseException {
		//查询日期
		Date sd = StringToDate("2018-6-1", "yyyy-MM-dd");
		Date ed = StringToDate("2018-6-20", "yyyy-MM-dd");
		//台账日期
		Date _sd = StringToDate("2018-2-13", "yyyy-MM-dd");
		Date _ed = StringToDate("2019-2-19", "yyyy-MM-dd");
	    System.out.println(get2days(_sd,_ed,sd,ed));
	    System.out.println(getTimeDistance(sd,ed));
	}
}
