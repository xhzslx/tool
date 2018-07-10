package com.example.jpm.librarydemo.tools.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class DateUtil {

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    private final static SimpleDateFormat SDF_YEAR = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat SDF_DAY = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat SDF_DAYS = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat SDF_TIMES = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final static SimpleDateFormat SDF_MONTH = new SimpleDateFormat("yyyy-MM");
    private final static SimpleDateFormat SN_TIMES = new SimpleDateFormat("yyyyMMddHHmmss");

    //这是sn生成的方法 具体到秒 加二位随机数
    public static Long createSn() {
        int i = new Random().nextInt(10000);
        DecimalFormat localDecimalFormat = new DecimalFormat("0000");
        return Long.parseLong(getSn() + "" + localDecimalFormat.format(i));
    }

    /**
     * 获取YYYY-MM-DD hh:mm:ss格式
     *
     * @return
     */
    public static String getSn() {
        return SN_TIMES.format(new Date());
    }

    //2017 06 02 18 26 50> yyyy-MM-dd HH:mm:ss
    public static String setStandardDate(String sn) {
        StringBuilder date = new StringBuilder(sn);
        date.insert(4, "-");
        date.insert(7, "-");
        date.insert(10, " ");
        date.insert(13, ":");
        date.insert(16, ":");
        return date.toString();
    }

    /**
     * 获取YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return SDF_YEAR.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return SDF_DAY.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     *
     * @return
     */
    public static String getDays() {
        return SDF_DAYS.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD hh:mm:ss格式
     *
     * @return
     */
    public static String getTimes() {
        return SDF_TIMES.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD hh:mm格式
     *
     * @return
     */
    public static String getTime() {
        return SDF_TIME.format(new Date());
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @throws
     * @Title: compareDate
     * @Description: 日期比较，如果s>=e 返回true 否则返回false
     * @author luguosui
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() >= fomatDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化时间
     *
     * @return
     */
    public static Date fomatTime(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    // 将时间戳转为字符串
    public static String getStrTime(String cc_time) {
        String re_StrTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lcc_time = Long.valueOf(cc_time);
        re_StrTime = sdf.format(new Date(lcc_time));
        return re_StrTime;
    }

    // 将字符串转为时间戳
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    // 将字符串转为时间戳
    public static String getDate(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime();
            String str = String.valueOf(l);
            re_time = str.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    /**
     * 获取时间戳
     *
     * @return 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long aa = 0;
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(
                    startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);

        return day;
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        // java.util包
        Calendar canlendar = Calendar.getInstance();
        // 日期减 如果不够减会将月变动
        canlendar.add(Calendar.DATE, daysInt);
        Date date = canlendar.getTime();

//        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        // java.util包
        Calendar canlendar = Calendar.getInstance();
        // 日期减 如果不够减会将月变动
        canlendar.add(Calendar.DATE, daysInt);
        Date date = canlendar.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 获取每月的天数
     *
     * @param month
     */
    public static int getMonthDays(String month) {

        // String month = "2015-05";
        try {
            Date date = SDF_MONTH.parse(month);
            // java.util包
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //默认返回最大值每月的最大值31
        return 31;
    }

    /**
     * 解析带时区的时间戳到日
     */
    public static String Format(String str) {
        if (str != null && !str.equals("")) {
            str = str.replace("/Date(", "").replace(")/", "");
            String time = str.substring(0, str.length() - 5);
            Date date = new Date(Long.parseLong(time));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(date);
        } else {
            return "";
        }
    }

    /**
     * 解析带时区的时间戳到分
     *
     * @param
     */
    public static String Formatsecond(String str) {
        if (str != null && !str.equals("")) {
            str = str.replace("/Date(", "").replace(")/", "");
            String time = str.substring(0, str.length() - 5);
            Date date = new Date(Long.parseLong(time));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.format(date);
        } else {
            return "";
        }
    }

    /**
     * 解析时间戳到秒
     *
     * @param
     */
    public static String Format(Long str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(str));
    }

    /**
     * 解析date时间戳到秒
     *
     * @param
     */
    public static String Format(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        return time;
    }

    /**
     * 解析date到年
     *
     * @param
     */
    public static String FormatDate(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String time = format.format(date);
        return time;
    }

    /**
     * 解析date到中文年
     *
     * @param
     */
    public static String FormatYear(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年");
        String time = format.format(date);
        return time;
    }

    /**
     * 解析时间戳成时分
     *
     * @param
     */
    public static String FormatTime(String str) {
        str = str.replace("/Date(", "").replace(")/", "");
        String time = str.substring(0, str.length() - 5);
        Date date = new Date(Long.parseLong(time));
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    /**
     * 得到现在的时间到秒
     *
     * @param
     */
    public static String getNowtime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return format.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 得到现在的时间到日
     *
     * @param
     */
    public static String getDay(Date string) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(string);
    }

    public static String getrqTime(Date date, int type, boolean flag) {//可根据需要自行截取数据显示
        SimpleDateFormat format = null;
        switch (type) {
            case 1:
                if (flag) {
                    format = new SimpleDateFormat("yyyy年MM月dd日日报");
                } else {
                    format = new SimpleDateFormat("yyyy年MM月dd日");
                }

                break;
            case 3:
                if (flag) {
                    format = new SimpleDateFormat("yyyy年MM月月报");
                } else {
                    format = new SimpleDateFormat("yyyy年MM月");
                }
                break;
            case 5:
                if (flag) {
                    format = new SimpleDateFormat("yyyy年年报");
                } else {
                    format = new SimpleDateFormat("yyyy年");
                }

                break;
        }

        return format.format(date);
    }
}
