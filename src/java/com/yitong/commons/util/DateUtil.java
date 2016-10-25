package com.yitong.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.yitong.commons.model.Properties;

/**
 * 日期格式化
 * 
 */
public class DateUtil {

	public static final String DATE_FORMATTER = Properties
			.getString("DATE_FORMATTER");

	public static java.util.Date parseDate(String dateStr, String format) {
		if (StringUtil.isEmpty(dateStr))
			return null;
		java.util.Date date = null;
		try {
			java.text.DateFormat df = new java.text.SimpleDateFormat(format);
			String dt = dateStr;// .replaceAll("-", "/");
			if ((!dt.equals("")) && (dt.length() < format.length())) {
				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
						"0");
			}
			date = (java.util.Date) df.parse(dt);
		} catch (Exception e) {
		}
		return date;
	}

	public static java.util.Date parseDate(String dateStr) {
		return parseDate(dateStr, DATE_FORMATTER);
	}

	public static String todayStr() {
		return formatDateToStr(new Date(), DATE_FORMATTER);
	}

	public static Date today() {
		return parseDate(todayStr(), DATE_FORMATTER);
	}

	/**
	 * @param date
	 *            需要格式化的日期對像
	 * @param formatter
	 *            格式化的字符串形式
	 * @return 按照formatter指定的格式的日期字符串
	 * @throws ParseException
	 *             無法解析的字符串格式時拋出
	 */
	public static String formatDateToStr(Date date, String formatter) {
		if (date == null)
			return "";
		try {
			return new java.text.SimpleDateFormat(formatter).format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 生成默认格式的日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateToStr(Date date) {
		return formatDateToStr(date, DATE_FORMATTER);
	}

	/**
	 * 將日期按照指定的模式格式化
	 * 
	 * @param date
	 *            {@link Date}
	 * @param format
	 *            如：yyyy/MM/dd
	 * @return 返回空表示格式化產生異常
	 */
	public static String format(java.util.Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new java.text.SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 将一种字符日期转化成另外一种日期格式
	 * 
	 * @param date
	 * @param fmtSrc
	 * @param fmtTag
	 * @return
	 */
	public static String format(String date, String fmtSrc, String fmtTag) {
		if (null == fmtSrc)
			return null;
		if (fmtSrc.equals(fmtTag)) {
			return date;
		}
		String year, month, daty;
		int Y, M, D;
		fmtSrc = fmtSrc.toUpperCase();
		Y = fmtSrc.indexOf("YYYY");
		M = fmtSrc.indexOf("MM");
		D = fmtSrc.indexOf("DD");
		if (Y < 0 || M < 0 || D < 0) {
			return date;
		}
		year = date.substring(Y, Y + 4);
		month = date.substring(M, M + 2);
		daty = date.substring(D, D + 2);
		fmtTag = fmtTag.toUpperCase();
		fmtTag = fmtTag.replaceAll("YYYY", year);
		fmtTag = fmtTag.replaceAll("MM", month);
		fmtTag = fmtTag.replaceAll("DD", daty);
		return fmtTag;
	}

	/**
	 * 計算指定年月的日期數
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int maxDayOfMonth(int year, int month) {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 4:
		case 6:
		case 9:
		case 11:
			return 30;
		case 2:
			boolean isRunYear = (year % 400 == 0)
					|| (year % 4 == 0 && year % 100 != 0);
			return isRunYear ? 29 : 28;
		default:
			return 31;
		}
	}

	/**
	 * 获取指定年月的日期數
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int maxDayOfMonth(String year, String month) {
		return maxDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
	}

	/**
	 * 获取指定年月上月月末日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastMonthDate(String year, String month) {
		return getLastMonthDate(Integer.parseInt(year), Integer.parseInt(month));
	}

	/**
	 * 获取指定年月上月月末日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastMonthDate(int year, int month) {
		if (month <= 1) {
			year -= 1;
			month = 12;
		} else {
			month -= 1;
		}
		StringBuffer bfDate = new StringBuffer();
		bfDate.append(year);
		if (month < 10)
			bfDate.append("0");
		bfDate.append(month);
		bfDate.append(maxDayOfMonth(year, month));
		return bfDate.toString();
	}

	/**
	 * 提前N天的日期
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date beforeDate(Date date, int days) {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.setTime(date);
		c.add(java.util.Calendar.DAY_OF_YEAR, -days);
		return c.getTime();

	}
	
	
	/**
	 * 一周前的日期
	 * 
	 * @return
	 */
	public static Date getLastWeek() {
		java.util.Calendar c = java.util.Calendar.getInstance();
		c.add(java.util.Calendar.DAY_OF_YEAR, -7);
		return c.getTime();

	}

	
	/**
	 * 获取任意两个时间之间间隔的年份，每年 以一个map返回 
	 * 获取传入参数不分前后顺序
	 * 用于hbase历史数据按年分表查询场景
	 * @param start
	 * @param end
	 * @return 
	 */
	public static Map<String,Map<String,Date>> getStartStopBetween(Date start,Date end){
		
		
		Map<String,Map<String,Date>> result = new TreeMap<String,Map<String,Date>>();
		
		if(start.before(end)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String startYear = sdf.format(start);
			String endYear = sdf.format(end);
			int startYearInt = Integer.valueOf(startYear);
			int endYearInt = Integer.valueOf(endYear);
			int between = endYearInt - startYearInt;
			if(0 == between){
				Map<String,Date> innerMap = new HashMap<String,Date>();
				innerMap.put("start", start);
				innerMap.put("stop", end);
				result.put(startYear, innerMap);
			}
			else{
				try {
					int i = 1;
					SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMddHHmmss");
					Map<String,Date> innerMap = new HashMap<String,Date>();
					innerMap.put("start", start);
					innerMap.put("stop", sdff.parse(startYear+"1231235959"));
					result.put(startYear, innerMap);
					while(i <= between){
						int year = startYearInt + i;
						Map<String,Date> innMap = new HashMap<String,Date>();
						innMap.put("start", sdff.parse(year+"0101000000"));
						if(endYearInt == year){
							innMap.put("stop", end);
						}else{
							innMap.put("stop", sdff.parse(year+"1231235959"));
						}
						result.put(String.valueOf(year), innMap);
						i++;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
 		}else{
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			String startYear = sdf.format(end);
			String endYear = sdf.format(start);
			int startYearInt = Integer.valueOf(startYear);
			int endYearInt = Integer.valueOf(endYear);
			int between = endYearInt - startYearInt;
			if(0 == between){
				Map<String,Date> innerMap = new HashMap<String,Date>();
				innerMap.put("start", end);
				innerMap.put("stop", start);
				result.put(startYear, innerMap);
			}
			else{
				try {
					int i = 1;
					SimpleDateFormat sdff = new SimpleDateFormat("yyyyMMddHHmmss");
					Map<String,Date> innerMap = new HashMap<String,Date>();
					innerMap.put("start", end);
					innerMap.put("stop", sdff.parse(startYear+"1231235959"));
					result.put(startYear, innerMap);
					while(i <= between){
						int year = startYearInt + i;
						Map<String,Date> innMap = new HashMap<String,Date>();
						innMap.put("start", sdff.parse(year+"0101000000"));
						if(endYearInt == year){
							innMap.put("stop", end);
						}else{
							innMap.put("stop", sdff.parse(year+"1231235959"));
						}
						result.put(String.valueOf(year), innMap);
						i++;
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
 		}
		return result;
		
	}

	
	
    public static Date getDate(){
    	Calendar now = Calendar.getInstance();   		
    	return now.getTime();    
    } 
}
