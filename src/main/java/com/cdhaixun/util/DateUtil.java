package com.cdhaixun.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    /**
     * 日期短格式yyyy.MM.dd
     */
    private static final String DATE_SHORT = "yyyy.MM.dd";

    /**
     * 日期ISO格式;yyyy-MM-dd;
     */
    private static final String DATE_SHORT_ISO = "yyyy-MM-dd";
    
    /**
     * 日期格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String LONG_DATE_FORMAT_STR = "yyyy-MM-dd HH:mm:ss";

    /**
     * 24小时格式
     */
    private static final String DATE_MINUTES = "yyyy.MM.dd HH:mm:ss";

    /**
     * 12小时格式
     */
    private static final String DATE_MINUTES_12 = "yyyy.MM.dd hh-mm-ss";

    /**
	 * 使用预设Format格式化Date成字符串
	 *日期格式 yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String format(Date date) {
		return date == null ? "" : format(date, LONG_DATE_FORMAT_STR);
	}
	
	/**
	 * 使用参数Format格式化Date成字符串
	 *
	 * @return String
	 */
	public static String format(Date date, String pattern) {
		return date == null ? "" : new SimpleDateFormat(pattern).format(date);
	}
	
    /**
     * @param dateString -日期字符串 <br>
     *                   Stirng pattern -格式
     * @return String
     * @description -将日期字符按pattern串格式化
     * @author HuangCheng
     */
    public static String format(String dateString, String pattern) {
        String result = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        try {
            result = dateFormat.format(dateFormat.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param dateString
     * @return String
     * @description -将日期字符按DATE_SHORT串格式化
     * @author HuangCheng
     */
    public static String format(String dateString) {
        return format(dateString, DATE_SHORT);
    }

    /**
     * @param dateString
     * @return String
     * @description -将日期字符按DATE_SHORT串格式化
     * @author HuangCheng
     */
    public static String format2(String dateString) {
        return format(dateString, DATE_SHORT);
    }

    /**
     * Date类型日期格式化为，短格式的日期对象 (yyyy.MM.dd)
     *
     * @param date
     * @return
     */
    public static Date formatDate(Date date) {
        String dateString = dateToString(date);
        return stringToDate(dateString);
    }

    public static Date formatDate(Date date, String pattern) {
        String dateString = dateToString(date, pattern);
        return stringToDate(dateString, pattern);
    }

    /**
     * @param dateStr -日期字符串 <br>
     *                int len -移动天数;eg: +1(明天),-1(昨天)
     * @return String
     * @description -将日期字符串 按天 前后移动 (yyyy.MM.dd)
     * @author HuangCheng
     */
    public String dayMove(String dateStr, int len) {
        return dateMove(dateStr, len, Calendar.DATE, DATE_SHORT);
    }
	
    /**
     * @param dateStr -日期字符串 <br>
     *                int len -移动的月数;eg:+1(下个月),-1(上个月)
     * @return String
     * @description -将日期字符串 按月 前后移动 (yyyy.MM.dd)
     * @author HuangCheng
     */
    public String mouthMove(String dateStr, int len) {
        return dateMove(dateStr, len, Calendar.MONTH, DATE_SHORT);
    }

    /**
     * @param dateStr -日期字符串 <br>
     *                int len -移动的年数;eg:+1(明年),-1(去年)
     * @return String
     * @description -将日期字符串 按年 前后移动 (yyyy.MM.dd)
     * @author HuangCheng
     */
    public String yearMove(String dateStr, int len) {
        return dateMove(dateStr, len, Calendar.YEAR, DATE_SHORT);
    }

    /**
     * @param dateStr -需要处理的字符串 <br>
     *                int len -移动天数 <br>
     *                Calendar.DATE field -按(天/月/年)移动
     * @return String 返回日期字符串
     * @description -返回日期字符串dateStr按移动字段field,移动天数len移动后的字符串
     * @author HuangCheng
     */
    public String dateMove(String dateStr, int len, int field) {
        return dateMove(dateStr, len, field, DATE_SHORT);
    }

    /**
     * @param dateStr -需要处理的字符串 <br>
     *                int len -移动天数 <br>
     *                Calendar.DATE field -按(天/月/年)移动 <br>
     *                String pattern -格式
     * @return String
     * @description -返回日期字符串按移动字段field，移动天数len, 格式pattern
     * @author HuangCheng
     */
    public String dateMove(String dateStr, int len, int field, String pattern) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(stringToDate(dateStr, pattern));
        cal.add(field, len);
        return dateToString(cal.getTime(), pattern);
    }

    /**
     * @return String
     * @description 返回系统时间的字符串 (yyyy.MM.dd hh-mm-ss),12小时格式
     * @author HuangCheng
     */
    public static String getTime() {
        return dateToString(new Date(), DATE_MINUTES_12);
    }

    /**
     * @return String
     * @description 返回系统时间的字符串24小时格式 (yyyy.MM.dd HH:mm:ss)
     * @author HuangCheng
     */
    public static String getTime24() {
        return getTime24(new Date());
    }

    /**
     * @param date 传入日期
     * @return
     * @description 返回系统时间的字符串24小时格式 (yyyy.MM.dd HH:mm:ss)
     */
    public static String getTime24(Date date) {
        return dateToString(date, DATE_MINUTES);
    }

    /**
     * @return String
     * @description 返回系统日期的字符串 (yyyy.MM.dd)
     * @author HuangCheng
     */
    public static String getDate() {
        return dateToString(new Date(), DATE_SHORT);
    }

    /**
     * 将时间减少几分钟
     *
     * @param date   日期
     * @param minute 分钟
     * @return
     */
    public static Date getDateReducingTime(Date date, int minute) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -minute);
        return calendar.getTime();
    }

    /**
     * 将天数增加
     *
     * @param date 日期
     * @param day  增加的天数
     * @return
     */
    public static Date getDateAddDay(Date date, int day) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }
    /**
     * 将月份增加，负数为减少
     * @param date
     * @param
     * @return
     */
    public static Date getDateAddMonth(Date date, int month) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTime();
    }
    /**
     * 将天数减少
     *
     * @param date 日期
     * @param day  增加的天数
     * @return
     */
    public static Date getDateReducingDay(Date date, int day) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -day);
        return calendar.getTime();
    }

    /**
     * 将当前时间减少几分钟
     *
     * @param minute
     * @return
     */
    public static Date getCurrentDateReducingTime(int minute) {
        Calendar calendar = new GregorianCalendar();
        return getDateReducingTime(calendar.getTime(), minute);
    }

    /**
     * @return String
     * @description -返回系统现在时间的毫秒数
     * @author HuangCheng
     */
    public static String getTimeMilliseconds() {
        return String.valueOf(new Date().getTime());
    }

    /**
     * @param pattern -格式 <br>
     *                Date date -日期对象
     * @return String -日期字符串
     * @description 将日期对象date转化成格式pattern的日期字符串
     * @author HuangCheng
     */
    public static String dateToString(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * @param date
     * @return String
     * @description 返回指定时间的字符串 (yyyy.MM.dd hh-mm-ss),12小时格式
     * @author HuangCheng
     */
    public static String timeToString(Date date) {
        if (date != null) {
            return dateToString(date, DATE_MINUTES_12);
        } else {
            return null;
        }
    }

    /**
     * 24小时格式 "yyyy.MM.dd HH:mm:ss"
     *
     * @param date
     * @return
     */
    public static String time24ToString(Date date) {
        if (date != null) {
            return dateToString(date, DATE_MINUTES);
        } else {
            return null;
        }
    }

    /**
     * @param
     * @return String 日期的字符串
     * @description 返回指定日期的字符串 (yyyy.MM.dd)
     * @author HuangCheng
     */
    public static String dateToString(Date date) {
        if (date != null) {
            return dateToString(date, DATE_SHORT);
        } else {
            return null;
        }
    }
    /**
     * 日期ISO格式;yyyy-MM-dd;
     * @param date
     * @return
     */
    public static String dateToISOString(Date date) {
        if (date != null) {
            return dateToString(date, DATE_SHORT_ISO);
        } else {
            return null;
        }
    }

    /**
     * @param
     * @return String 日期的字符串
     * @description 返回指定日期的字符串 (yyyy.MM.dd)
     * @author HuangCheng
     */
    public static String date2StringShort(Date date) {
        if (date != null) {
            return dateToString(date, DATE_SHORT);
        } else {
            return null;
        }
    }

    /**
     * @param dateStr -日期字符串 <br>
     *                String pattern -转化格式
     * @return Date -转化成功返回该格式的日期对象,失败返回null
     * @description -按格式pattern将字符串dateStr转化为日期
     * @author HuangCheng
     */
    public static Date stringToDate(String dateStr, String pattern) {
        Date date = null;
        if (!StringUtil.isBlank(dateStr)) {
            try {
                date = new SimpleDateFormat(pattern).parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * @param timeStr -日期字符串
     * @return Date
     * @description -将日期字符串timeStr转化为日期对象 (yyyy.MM.dd hh-mm-ss),12小时格式
     * @author HuangCheng
     */
    public static Date stringToTime(String timeStr) {
        return stringToDate(timeStr, DATE_MINUTES_12);
    }

    /**
     * @param dateStr -日期字符串
     * @return Date
     * @description -将日期字符串dateStr转化为日期对象 (yyyy.MM.dd)
     * @author HuangCheng
     */
    public static Date stringToDate(String dateStr) {
        return stringToDate(dateStr, DATE_SHORT);
    }
    /**
     * 格式为yyyy-MM-dd
     * @param dateStr
     * @return
     */
    public static Date stringToISODate(String dateStr) {
        return stringToDate(dateStr, DATE_SHORT_ISO);
    }
    /**
     * 格式为yyyy-MM-dd HH:mm:ss
     * @param dateStr
     * @return
     */
    public static Date stringToMiuDate(String dateStr) {
        return stringToDate(dateStr, LONG_DATE_FORMAT_STR);
    }
    /**
     * 日期ISO格式;yyyy-MM-dd
     * @param date
     * @return
     */
    public static String dateISOToString(Date date) {
    	   if (date != null) {
               return dateToString(date, DATE_SHORT_ISO);
           } else {
               return null;
           }
    }

    /**
     * 判断当前日期 > 传入的日期 返回true。 判断当前日期 <= 传入的日期 返回false。
     *
     * @param vDate
     * @return
     */
    public static boolean isAfterDate(Date vDate) {
        Calendar nowDate = GregorianCalendar.getInstance();
        return nowDate.after(vDate);
    }

    /**
     * 判断日期的相隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getDistDates(Date startDate, Date endDate) {
        startDate = formatDate(startDate);
        endDate = formatDate(endDate);
        long totalDate = 0;
        long timestart = startDate.getTime();
        long timeend = endDate.getTime();
        totalDate = Math.abs((timeend - timestart)) / (1000 * 60 * 60 * 24);
        return Long.valueOf(totalDate).intValue();
    }

    /**
     * 判断传入的日期与服务器当前日期的相隔天数
     *
     * @param vDate
     * @return
     */
    public static int getDistCurrentDates(Date vDate) {
        return getDistDates(new Date(), vDate);
    }

    /**
     * 判断日期相隔的时、分、秒
     */
    public static Long[] getDistTime(Date startDate, Date endDate) {
        long hour = 0;
        long min = 0;
        long sec = 0;
        if (startDate != null && endDate != null) {
            long time1 = startDate.getTime();
            long time2 = endDate.getTime();
            long diff;
            if (time1 < time2) {
                hour = 0L;
                min = 0L;
                sec = 0L;
            } else {
                diff = time1 - time2;
                hour = diff / (60 * 60 * 1000);
                min = ((diff / (60 * 1000)) - hour * 60);
                sec = (diff / 1000 - hour * 60 * 60 - min * 60);
            }
        }
        Long[] times = {hour, min, sec};
        return times;
    }

    /**
     * 判断日期相隔的天、时、分
     */
    public static Long[] getDistMinute(Date startDate, Date endDate) {
        long day = 0;
        long hour = 0;
        long min = 0;
        if (startDate != null && endDate != null) {
            long time1 = startDate.getTime();
            long time2 = endDate.getTime();
            long diff;
            if (time1 < time2) {
                day = 0L;
                hour = 0L;
                min = 0L;
            } else {
                diff = time1 - time2;
                day = diff / (1000 * 60 * 60 * 24);
                hour = (diff / (60 * 60 * 1000) - day * 24);
                min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            }
        }
        Long[] times = {day, hour, min};
        return times;
    }

    /**
     * 判断传入日期与服务器当期日期相隔的时、分、秒
     */
    public static Long[] getDistCurrentTime(Date vDate) {
        return getDistTime(vDate, new Date());
    }

    /**
     * 判断传入日期与服务器当期日期相隔的天、时、分
     */
    public static Long[] getDistCurrentMinute(Date vDate) {
        return getDistMinute(vDate, new Date());
    }

    /**
     * 返回当前日期的星期数 星期天为1，星期一为：2星期二为3，以此类推星期六为7
     *
     * @return
     */
    public static int getDayOfWeek() {
        Calendar nowDate = GregorianCalendar.getInstance();
        return nowDate.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取传入日期的星期
     *
     * @param date
     * @return
     */
    public static String getDayWeek(Date date) {
        String[] weeks = {"sunday", "monday", "tuesday", "wednesday",
                "thursday", "friday", "saturday"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayinweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weeks[dayinweek];
    }

    /**
     * 获取传入日期的星期
     *
     * @param date
     * @return
     */
    public static String getChinaDayWeek(Date date) {
        String[] weeks = {"星期天", "星期一", "星期二", "星期三",
                "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayinweek = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weeks[dayinweek];
    }

    public static int  getWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return  cal.get(Calendar.DAY_OF_WEEK) - 1;
    }
    /**
     * 判断当前时间是否在营业日期和时间范围内
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static boolean isBusinessHours(Date startDate, Date endDate, String startTime, String endTime) {
        return getDistTime(startTime, endTime) && isBetween(startDate, endDate);
    }

    /**
     * 判断当前时间是否在营业时间范围内( 时间比较 )
     * 两种情况
     * 1:    正常情况 09:00 -  17:00
     * 2:    跨凌晨    21:00  -  03:00
     */
    public static boolean getDistTime(String startTime, String endTime) {
        Calendar nowTime = Calendar.getInstance();
        int nowHour = nowTime.get(Calendar.HOUR_OF_DAY);

        String[] sa = startTime.split(":");
        String[] ea = endTime.split(":");
        if (sa.length > 1 && ea.length > 1) {
            int startHour = Integer.valueOf(sa[0]).intValue();
            int startMin = Integer.valueOf(sa[1]).intValue();
            int endHour = Integer.valueOf(ea[0]).intValue();
            int endMin = Integer.valueOf(ea[1]).intValue();
            Long nowMillis = nowTime.getTimeInMillis();
            nowTime.set(Calendar.HOUR_OF_DAY, startHour);
            nowTime.set(Calendar.MINUTE, startMin);
            Long startMillis = nowTime.getTimeInMillis();
            nowTime.set(Calendar.HOUR_OF_DAY, endHour);
            nowTime.set(Calendar.MINUTE, endMin);
            Long endMillis = nowTime.getTimeInMillis();
            //正常时间
            if (startHour <= endHour) {
                if (nowMillis.compareTo(startMillis) >= 0 && nowMillis.compareTo(endMillis) <= 0) {
                    return true;
                } else {
                    return false;
                }
                //跨凌晨营业
            } else {
                //将这里形式的日期划分为两段21:00 - 06:00
                //21:00 - 23:59修改结束时间
                if (nowHour >= startHour && nowHour <= 23) {
                    nowTime.set(Calendar.HOUR_OF_DAY, startHour);
                    nowTime.set(Calendar.MINUTE, startMin);
                    startMillis = nowTime.getTimeInMillis();
                    nowTime.set(Calendar.HOUR_OF_DAY, 23);
                    nowTime.set(Calendar.MINUTE, 59);
                    endMillis = nowTime.getTimeInMillis();
                    if (nowMillis.compareTo(startMillis) >= 0 && nowMillis.compareTo(endMillis) <= 0) {
                        return true;
                    }
                }

                //00:00 - 06:00修改开始和结束时间
                if (nowHour >= 0 && nowHour <= endHour) {
                    nowTime.set(Calendar.HOUR_OF_DAY, 0);
                    nowTime.set(Calendar.MINUTE, 0);
                    startMillis = nowTime.getTimeInMillis();
                    nowTime.set(Calendar.HOUR_OF_DAY, endHour);
                    nowTime.set(Calendar.MINUTE, endMin);
                    endMillis = nowTime.getTimeInMillis();
                    if (nowMillis.compareTo(startMillis) >= 0 && nowMillis.compareTo(endMillis) <= 0) {
                        return true;
                    }
                }

                //在时间之外返回false
                return false;
            }
        }
        return true;
    }

    public static long compareDate(String date, String compareDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        long time1 = 0;
        long time2 = 0;
        try {
            time1 = sdf.parse(date).getTime();
            time2 = sdf.parse(compareDate).getTime();
        } catch (ParseException e) {
            // nothing to do
        }
        return time1 - time2;
    }

    /**
     * 根据出生日期计算年龄
     */
    public static int getAge(Date birthDay) {
        if (birthDay == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            return 0;
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age;
    }

    /**
     * 根据出生日期计算年龄
     */
    public static int getAge(String strBirthDay, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Date birthDay = null;
        try {
            birthDay = df.parse(strBirthDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getAge(birthDay);
    }

    /**
     * 构造日期时间
     *
     * @param date
     * @param timeStr
     * @return
     */
    public static Date parseEndDate(Date date, String timeStr) {
        String[] sa = timeStr.split(":");
        Calendar gc = GregorianCalendar.getInstance();
        if (sa.length > 1) {
            int hour = Integer.parseInt(sa[0]);
            int minute = Integer.parseInt(sa[1]);
            gc.setTime(date);
            gc.set(Calendar.HOUR_OF_DAY, hour);
            gc.set(Calendar.MINUTE, minute);
        }
        return gc.getTime();
    }
    /**
     * 判断当前时间在时间范围的位置
     * -1为之前，未开始;0 为当中，1为已过
     * @param startDate
     * @param endDate
     * @return
     */
    public static int beforeInAfter(Date startDate, Date endDate){
        long nowTime = new Date().getTime();
        if(nowTime > endDate.getTime()){
            return 1;
        }else if(nowTime >= startDate.getTime() &&  nowTime<= endDate.getTime()){
            return 0;
        }else{
            return -1;
        }
    }
    /**
     * 检测当前日期是否在传入的日期范围内
     * 在范围内返回true;反之false
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isBetween(Date startDate, Date endDate) {
        long nowTime = new Date().getTime();
        System.out.println("nowTime:"+nowTime+"startDate:"+startDate.getTime()+"endDate:"+endDate.getTime());
        if (nowTime >= startDate.getTime() &&  nowTime<= endDate.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean inRange(Date startDate,String startTime,Date endDate,String  endTime){
        String[] sa = startTime.split(":");
        String[] ea = endTime.split(":");
        GregorianCalendar start =  new GregorianCalendar();
        GregorianCalendar end = new GregorianCalendar();
        start.setTime(startDate);
        end.setTime(endDate);
        int startHour = 0 ;
        int startMin=0;
        int endHour = 0;
        int endMin = 0;
        if (sa.length > 1 && ea.length > 1) {
             startHour = Integer.valueOf(sa[0]).intValue();
             startMin = Integer.valueOf(sa[1]).intValue();
             endHour = Integer.valueOf(ea[0]).intValue();
             endMin = Integer.valueOf(ea[1]).intValue();
        }
        start.set(Calendar.HOUR_OF_DAY,startHour);
        start.set(Calendar.MINUTE,startMin);
        start.set(Calendar.SECOND,0);
        start.set(Calendar.MILLISECOND,0);
        end.set(Calendar.HOUR_OF_DAY,endHour);
        end.set(Calendar.MINUTE,endMin);
        end.set(Calendar.SECOND,0);
        end.set(Calendar.MILLISECOND,0);
        Date now = new Date();
        return start.getTime().getTime()<=now.getTime() && end.getTime().getTime()>=now.getTime();
    }
    /**
     * 把当前日期转换为当日的第一秒
     *
     * @param date 原始日期
     * @return 转换后的日期, xxxx-xx-xx 00:00:00
     */
    public static Date getBeginOfDay(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        return gc.getTime();
    }

    /**
     * 把当前日期转换为当日的最后一秒
     *
     * @param date 原始日期
     * @return 转换后的日期, xxxx-xx-xx 23:59:59
     */
    public static Date getEndOfDay(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.set(Calendar.HOUR_OF_DAY, 23);
        gc.set(Calendar.MINUTE, 59);
        gc.set(Calendar.SECOND, 59);
        return gc.getTime();
    }
    /**
     * 构建日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date buildDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    /**
     * 构建日期
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date buildDate(int year, int month, int day, int hour,
                                 int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year );
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        return c.getTime();
    }

    /**
     * 获取指定日期内的星期一
     *
     * @param date
     * @return
     */
    public static Date getChineseMonday(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        if (gc.get(Calendar.DAY_OF_WEEK) == 1) {
            gc.add(Calendar.DAY_OF_MONTH, -1);
        }
        gc.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return gc.getTime();
    }

    /**
     * 获取指定日期内的星期日
     *
     * @param date
     * @return
     */
    public static Date getChineseSunday(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        if (gc.get(Calendar.DAY_OF_WEEK) == 1) {
            return date;
        }
        gc.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        gc.add(Calendar.DAY_OF_MONTH, 1);
        return gc.getTime();
    }
    /**
     * 获取指定月的月首
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.set(Calendar.DAY_OF_MONTH, 1);
        return gc.getTime();
    }
    /**
     * 获取指定月的月尾
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.set(Calendar.DAY_OF_MONTH, getDaysOfMonth(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH) + 1));
        return gc.getTime();
    }

    /**
     * 判断日期是否是闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0 || year % 400 == 0);
    }

    /**
     * 获取当月天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        int result = 0;
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
                return isLeapYear(year) ? 29 : 28;
        }
        return result;
    }

    /**
     * 获取当年的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfYear(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.set(Calendar.DAY_OF_YEAR, 1);
        return gc.getTime();
    }

    /**
     * 获取一年最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.set(Calendar.DAY_OF_YEAR, isLeapYear(gc.get(Calendar.YEAR)) ? 366 : 365);
        return gc.getTime();
    }

    public enum TimeScale {
        YEAR {
            @Override
            public int compare(Date d1, Date d2) {
                return compare(d1, d2, 1);
            }
        }, MONTH {
            @Override
            public int compare(Date d1, Date d2) {
                return compare(d1, d2, 2);
            }
        }, DAY {
            @Override
            public int compare(Date d1, Date d2) {
                return compare(d1, d2, 3);
            }
        }, HOUR {
            @Override
            public int compare(Date d1, Date d2) {
                return compare(d1, d2, 4);
            }
        }, MINUTE {
            @Override
            public int compare(Date d1, Date d2) {
                return compare(d1, d2, 5);
            }
        }, SECOND {
            @Override
            public int compare(Date d1, Date d2) {
                return compare(d1, d2, 6);
            }
        }, FULL {
            @Override
            public int compare(Date d1, Date d2) {
                return compare(d1, d2, 7);
            }
        };

        public abstract int compare(Date d1, Date d2);

        protected static int compare(Date d1, Date d2, int n) {
            GregorianCalendar c = new GregorianCalendar();
            c.setTime(d1);
            int[] date1 = new int[]{c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH),
                    c.get(Calendar.HOUR_OF_DAY),
                    c.get(Calendar.SECOND),
                    c.get(Calendar.MINUTE),
                    c.get(Calendar.MILLISECOND)
            };
            GregorianCalendar c1 = new GregorianCalendar();
            c1.setTime(d2);
            int[] date2 = new int[]{c1.get(Calendar.YEAR),
                    c1.get(Calendar.MONTH),
                    c1.get(Calendar.DAY_OF_MONTH),
                    c1.get(Calendar.HOUR_OF_DAY),
                    c1.get(Calendar.SECOND),
                    c1.get(Calendar.MINUTE),
                    c1.get(Calendar.MILLISECOND)
            };
            for (int i = 0; i < n; i++) {
                if (date1[i] > date2[i]) {
                    return 1;
                } else if (date1[i] < date2[i]) {
                    return -1;
                }
            }
            return 0;
        }
    }
    /**
     * 增加或减少小时数
     * 
     * @param date
     * @param hour
     * @return
     */
    public static Date getDateAddHour(Date date, int hour) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }
    public static void main(String[] args) {
        Date nowDate = new Date();
        System.out.println(isOverdueHour(nowDate, 0));
    }
    /**
     * 判断时间是否过期 传人的时间 + 小时 > 当前时间 ;返回 true反之false
     * 
     * @param
     * @return
     */
    public static boolean isOverdueHour(Date dbDate, int hour) {
        Date nowDate = new Date();
        return getDateAddHour(dbDate, hour).after(nowDate);
    }
    /**
     * 是否为同一天
     * @param date1
     * @param date2
     * @return true是同一天，反正不是
     */
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }
    
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
}