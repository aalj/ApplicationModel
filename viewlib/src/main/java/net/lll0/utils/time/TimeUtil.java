package net.lll0.utils.time;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import net.lll0.utils.time.bean.OverTimeMonthBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@SuppressLint("SimpleDateFormat")
public class TimeUtil {

    public static final String DATE_FORMAT_TYPE_01 = "yyyy";

    public static final String DATE_FORMAT_TYPE_02 = "yyyy-MM";

    public static final String DATE_FORMAT_TYPE_03 = "yyyy-MM-dd";

    public static final String DATE_FORMAT_TYPE_04 = "HH:mm:ss";

    public static final String DATE_FORMAT_TYPE_05 = "MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_TYPE_06 = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_TYPE_07 = "ahh:mm";

    public static final String DATE_FORMAT_TYPE_08 = "yyyy/MM/dd";

    public static final String DATE_FORMAT_TYPE_09 = "yyyy.MM.dd";

    public static final String DATE_FORMAT_TYPE_10 = "MM月dd日";

    public static final String DATE_FORMAT_TYPE_11 = "yyyy-MM";
    public static final String DATE_FORMAT_TYPE_12 = "yyyy年MM月";

    public static String getDateStringByFormat(Date date, String format) {
        SimpleDateFormat dateFormat02 = new SimpleDateFormat(format);
        return dateFormat02.format(date);
    }

    public static Date getDateByStringFormat(String time, String format) {
        try {
            SimpleDateFormat dateFormat02 = new SimpleDateFormat(format);
            return dateFormat02.parse(time);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 通过年月日返回日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getDataByYMD(int year, int month, int day) {
        Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
        //修改日历控件的年，月，日
        dateAndTime.set(Calendar.YEAR, year);
        dateAndTime.set(Calendar.MONTH, month);
        dateAndTime.set(Calendar.DAY_OF_MONTH, day);

        SimpleDateFormat dateFormat02 = new SimpleDateFormat(DATE_FORMAT_TYPE_02);
        return dateFormat02.format(dateAndTime.getTime());
    }

    /**
     * 根据时间错返回日期字符串 yyyy-MM	 *
     *
     * @param millis
     * @return
     */
    public static String getYM(String millis) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(new Long(millis));

            SimpleDateFormat dateFormat02 = new SimpleDateFormat(DATE_FORMAT_TYPE_02);
            return dateFormat02.format(calendar.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 根据时间错返回日期字符串 yyyy-MM
     * 如果yyyy为9999则返回至今
     *
     * @param millis
     * @return
     */
    public static String getWorkExperienceTime(String millis) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(new Long(millis));

            if (calendar.get(calendar.YEAR) == 9999) {
                return "至今";
            }

            SimpleDateFormat dateFormat02 = new SimpleDateFormat(DATE_FORMAT_TYPE_02);
            return dateFormat02.format(calendar.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 通过年月日返回时间戳
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static long getDataMillisByYMD(int year, int month, int day) {
        Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
        //修改日历控件的年，月，日
        dateAndTime.set(Calendar.YEAR, year);
        dateAndTime.set(Calendar.MONTH, month);
        dateAndTime.set(Calendar.DAY_OF_MONTH, day);

        return dateAndTime.getTimeInMillis();
    }

    /**
     * 通过年月日返回时间戳
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static long getDataMillisByYMD(String year, String month, String day) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, Integer.parseInt(month));
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

            return cal.getTimeInMillis();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 通过毫秒数返回日期
     * yyyy-MM-dd相同时返回 HH:mm:ss
     * yyyy-MM相同时返回 MM-dd HH:mm:ss
     * 其余返回yyyy-MM-dd HH:mm:ss
     *
     * @param millis
     * @return
     */
    public static String getTimeOrderTypeByMil(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return getTimeOrderTypeByDate(calendar.getTime());
    }

    /**
     * 通过date返回日期
     * yyyy-MM-dd相同时返回 HH:mm:ss
     * yyyy-MM相同时返回 MM-dd HH:mm:ss
     * 其余返回yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getTimeOrderTypeByDate(Date date) {
        SimpleDateFormat dateFormat02 = new SimpleDateFormat(DATE_FORMAT_TYPE_02);
        SimpleDateFormat dateFormat03 = new SimpleDateFormat(DATE_FORMAT_TYPE_03);
        SimpleDateFormat dateFormat04 = new SimpleDateFormat(DATE_FORMAT_TYPE_04);
        SimpleDateFormat dateFormat05 = new SimpleDateFormat(DATE_FORMAT_TYPE_05);
        SimpleDateFormat dateFormat06 = new SimpleDateFormat(DATE_FORMAT_TYPE_06);

        if (dateFormat03.format(new Date()).equals(dateFormat03.format(date))) {
            return dateFormat04.format(date);
        } else if (dateFormat02.format(new Date()).equals(dateFormat02.format(date))) {
            return dateFormat05.format(date);
        } else {
            return dateFormat06.format(date);
        }
    }

    /**
     * 根据时间错返回日期字符串 yyyy-MM-dd
     *
     * @param millis
     * @return
     */
    public static String getYMD(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        SimpleDateFormat dateFormat03 = new SimpleDateFormat(DATE_FORMAT_TYPE_03);
        return dateFormat03.format(calendar.getTime());
    }

    /**
     * 根据时间错返回日期字符串 yyyy-MM-dd
     *
     * @param millis
     * @return
     */
    public static String getYMD(String millis) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(new Long(millis));

            SimpleDateFormat dateFormat03 = new SimpleDateFormat(DATE_FORMAT_TYPE_03);
            return dateFormat03.format(calendar.getTime());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 根据时间戳返回日期字符串 yyyy-MM
     *
     * @param millis
     * @return
     */
    public static String getYMD02(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        SimpleDateFormat dateFormat02 = new SimpleDateFormat(DATE_FORMAT_TYPE_02);
        return dateFormat02.format(calendar.getTime());
    }

    /**
     * 根据时间戳返回日期字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param millis
     * @return
     */
    public static String getYMD06(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        SimpleDateFormat dateFormat06 = new SimpleDateFormat(DATE_FORMAT_TYPE_06);
        return dateFormat06.format(calendar.getTime());
    }

    /**
     * 根据时间戳返回日期字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param millis
     * @return
     */
    public static String getYMD06(String millis) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(new Long(millis));

            SimpleDateFormat dateFormat06 = new SimpleDateFormat(DATE_FORMAT_TYPE_06);
            return dateFormat06.format(calendar.getTime());
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 获得一个月中的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthAllDay(int year, int month) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, (month - 1));

        int count = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return count;
    }

    /**
     * 获得每月1号是星期几
     *
     * @param year
     * @param month
     * @return
     */
    public static int getWeekDay(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, (month - 1));
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int i = calendar.get(Calendar.DAY_OF_WEEK);
        if (i == 1) {
            return 7;
        } else if (i == 2) {
            return 1;
        } else if (i == 3) {
            return 2;
        } else if (i == 4) {
            return 3;
        } else if (i == 5) {
            return 4;
        } else if (i == 6) {
            return 5;
        }
        return 6;
    }

    /**
     * 获取当前月
     *
     * @return
     */
    public static int getCurrentMonth() {
        int month = Calendar.getInstance().get(Calendar.MONTH);
        return month + 1;
    }

    /**
     * 获取当前年
     *
     * @return
     */
    public static int getCurrentYear() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return year;
    }

    /**
     * 根据年与月返回下月
     *
     * @param year
     * @param month
     * @return
     */
    public static int getNextMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, (month - 1));
        cal.set(Calendar.DAY_OF_MONTH, 1);

        cal.add(Calendar.MONTH, -1);
        int nextMonth = cal.get(Calendar.MONTH);
        return nextMonth + 1;
    }

    /**
     * 根据年与月返回下月的年
     *
     * @param year
     * @param month
     * @return
     */
    public static int getNextYear(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, (month - 1));
        cal.set(Calendar.DAY_OF_MONTH, 1);

        cal.add(Calendar.MONTH, -1);
        int nextYear = Calendar.getInstance().get(Calendar.YEAR);
        return nextYear;
    }

    /**
     * 将时间戳转换成时分秒
     *
     * @param time
     * @return
     */
    public static String formatTime(int time) {
        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
        return formatter.format(new Date(time));
    }

    public static String getListDateFormat(String time) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_TYPE_03);// yyy-MM-dd

            Date nowDate = new Date();//当前时间
            String nowDateStr = dateFormat.format(nowDate);//当前时间字符串

            if (TextUtils.isEmpty(time)) {
                return "";
            }
            Date targetDate = new Date(Long.valueOf(time));//目标时间
            String targetDateStr = dateFormat.format(targetDate);//目标时间字符串

            if (targetDateStr.equals(nowDateStr)) {//当天
                SimpleDateFormat tempFormat = new SimpleDateFormat(DATE_FORMAT_TYPE_07);//ahh:mm
                return tempFormat.format(targetDate);
            } else if (targetDateStr.equals(dateFormat.format(getDataByDateAdd(Calendar.DATE, -1)))) {//昨天
                return "昨天";
            } else if (targetDateStr.compareTo(dateFormat.format(getDataByDateAdd(Calendar.DATE, -6))) > 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(targetDate);
                int i = calendar.get(Calendar.DAY_OF_WEEK);
                if (i == 1) {
                    return "星期日";
                } else if (i == 2) {
                    return "星期一";
                } else if (i == 3) {
                    return "星期二";
                } else if (i == 4) {
                    return "星期三";
                } else if (i == 5) {
                    return "星期四";
                } else if (i == 6) {
                    return "星期五";
                } else {
                    return "星期六";
                }
            } else {
                SimpleDateFormat tempFormat = new SimpleDateFormat(DATE_FORMAT_TYPE_08);//yyyy/MM/dd
                return tempFormat.format(targetDate);
            }
        } catch (Exception e) {
        }
        return "";
    }

    public static Date getDataByDateAdd(int type, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(type, num);
        return calendar.getTime();
    }

    public static List<String> getYearFStartTCurrent(String startYear) {
        List<String> yearDatas = new ArrayList<>();
        if (!TextUtils.isEmpty(startYear)) {
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT_TYPE_01);
            for (; ; ) {
                String endYear = formatter.format(calendar.getTime());
                if (startYear.compareTo(endYear) < 0) {
                    yearDatas.add(endYear);
                } else {
                    break;
                }
                calendar.add(Calendar.YEAR, -1);
            }
        }
        yearDatas.add(startYear);
        return yearDatas;
    }

    public static List<OverTimeMonthBean> getMonthFrontNum(int num) {
        List<OverTimeMonthBean> monthDatas = new ArrayList<>();
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        for (int i = 0; i < num; i++) {
            OverTimeMonthBean monthBean = new OverTimeMonthBean();
            monthBean.year = calendar.get(Calendar.YEAR) + "";
            monthBean.month = calendar.get(Calendar.MONTH) + 1 + "";

            monthDatas.add(monthBean);
            calendar.add(Calendar.MONTH, -1);
        }
        return monthDatas;
    }

    /**
     * 当前年
     *
     * @return
     */
    public static int getYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    /**
     * 年
     *
     * @return
     */
    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * 当前月
     *
     * @return
     */
    public static int getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 月
     *
     * @return
     */
    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 当前日
     *
     * @return
     */
    public static int getDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DATE);
    }

    /**
     * 日
     *
     * @return
     */
    public static int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);
    }

    /**
     * 当前星期几
     *
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        boolean isFirstSunday = (c.getFirstDayOfWeek() == Calendar.SUNDAY);
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        if (isFirstSunday) {
            weekDay = weekDay - 1;
            if (weekDay == 0) {
                weekDay = 7;
            }
        }
        return weekDay;
    }


    public static String MM_DD_(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat06 = new SimpleDateFormat(DATE_FORMAT_TYPE_10);
        return dateFormat06.format(date);
    }


    /**
     * 获取当前月的总共天数
     *
     * @return
     */
    public static int getDayNumInMonth() {
        Calendar c = Calendar.getInstance();
        return c.getActualMaximum(Calendar.DATE);
    }

    public static String getWeek(String year, String month, String day) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, (Integer.parseInt(month) - 1));
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

            int i = cal.get(Calendar.DAY_OF_WEEK);
            if (i == 1) {
                return "周日";
            } else if (i == 2) {
                return "周一";
            } else if (i == 3) {
                return "周二";
            } else if (i == 4) {
                return "周三";
            } else if (i == 5) {
                return "周四";
            } else if (i == 6) {
                return "周五";
            } else {
                return "周六";
            }
        } catch (Exception e) {
            return "";
        }
    }

    public static Calendar getDate(String year, String month, String day) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, (Integer.parseInt(month) - 1));
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

            return cal;
        } catch (Exception e) {
            return null;
        }
    }

    public static long getTimeStampBy00(String year, String month, String day) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, (Integer.parseInt(month) - 1));
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

            String timeStr = getYMD(cal.getTimeInMillis());
            SimpleDateFormat dateFormat02 = new SimpleDateFormat(DATE_FORMAT_TYPE_03);
            return dateFormat02.parse(timeStr).getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    public static long getTimeStampBy59(String year, String month, String day) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, Integer.parseInt(year));
            cal.set(Calendar.MONTH, (Integer.parseInt(month) - 1));
            cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);

            return cal.getTimeInMillis();
        } catch (Exception e) {
            return 0;
        }
    }


    public static String weekFormat(int week) {
        switch (week) {
            case 0: {
                return DateConstants.SUNDAY;
            }
            case 1: {
                return DateConstants.MONDAY;
            }
            case 2: {
                return DateConstants.TUESDAY;
            }
            case 3: {
                return DateConstants.WEDNESDAY;
            }
            case 4: {
                return DateConstants.THURSDAY;
            }
            case 5: {
                return DateConstants.FRIDAY;
            }
            case 6: {
                return DateConstants.SATURDAY;
            }
        }
        return null;
    }

    public static String weekFormatString(int week) {
        switch (week) {
            case 7: {
                return DateConstants.SUNDAY_S;
            }
            case 1: {
                return DateConstants.MONDAY_S;
            }
            case 2: {
                return DateConstants.TUESDAY_S;
            }
            case 3: {
                return DateConstants.WEDNESDAY_S;
            }
            case 4: {
                return DateConstants.THURSDAY_S;
            }
            case 5: {
                return DateConstants.FRIDAY_S;
            }
            case 6: {
                return DateConstants.SATURDAY_S;
            }
        }
        return null;
    }


    /**
     * 把按照timestamp格式的时间戳转换成YYYY/MM/DD字符串
     *
     * @param timeStamp
     * @return
     */
    public static String getDateStringFormTimestamp(long timeStamp) {
        try {
            return new SimpleDateFormat(DATE_FORMAT_TYPE_08).format(timeStamp);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把按照timestamp格式的时间戳转换成YYYY/MM/DD字符串
     *
     * @param timeStamp
     * @return
     */
    public static String getDateStringFormTimestamp(long timeStamp, String format) {
        try {
            return new SimpleDateFormat(format).format(timeStamp);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把时间转换成小时  2.5h
     *
     * @param time
     * @return
     */
    public static String minsToHour(int time) {

        float v = time / 60F;
        int i = (int) v;
        if (v - i > 0) {
            return String.format("%.1fh", v);
        } else {
            return String.format("%sh", i);
        }
    }

    /**
     * 把时间转换成小时  2.5h
     *
     * @param time
     * @return
     */
    public static String minsToHourv2(int time) {

        float v = time / 60F;
        int i = (int) v;
        if (v - i > 0) {
            return String.format("%.1f", v);
        } else {
            return String.format("%s", i);
        }
    }

    /**
     * 把时间转换成小时  2.5h
     *
     * @param time
     * @return
     */
    public static String minsToHourv3(float time) {

        int v = (int) time / 60;
        int v1 = (int) time % 60;
        return String.format("%s小时%s分钟", v, v1);
    }
}
