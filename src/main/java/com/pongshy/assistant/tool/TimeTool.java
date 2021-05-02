package com.pongshy.assistant.tool;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @ClassName: TimeTool
 * @Description:
 * @Author: pongshy
 * @Date: 2021/4/26 16:34
 **/
public class TimeTool {


    /**
     * @Description: date转换为最小为分的格式
     * @Param: [date]
     * @Return: java.lang.String
     * @Author: SoCMo
     * @Date: 2019/12/6
     */
    public static String timeToMinute(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        //设置时区为Asia/Shanghai
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(date);
    }

    /**
     * @Description: date转换为最小为秒的格式
     * @Param: [date]
     * @Return: java.lang.String
     * @Author: SoCMo
     * @Date: 2019/12/6
     */
    public static String timeToSecond(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        //设置时区为Asia/Shanghai
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(date);
    }

    /**
     * @Description: date转换为最小为秒的格式，微信解析使用
     * @Param: [date]
     * @Return: java.lang.String
     * @Author: SoCMo
     * @Date: 2019/12/6
     */
    public static String timeToSecondWX(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //设置时区为Asia/Shanghai
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(date);
    }

    /**
     * @Description: date转换为最小为天的格式
     * @Param: [date]
     * @Return: java.lang.String
     * @Author: SoCMo
     * @Date: 2019/12/6
     */
    public static String timeToDay(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        //设置时区为Asia/Shanghai
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(date);
    }

    /**
     * @Description: date转换为最小为天的格式2
     * @Param: [date]
     * @Return: java.lang.String
     * @Author: SoCMo
     * @Date: 2019/12/11
     */
    public static String timeToDay2(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //设置时区为Asia/Shanghai
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return simpleDateFormat.format(date);
    }

    /**
     * @Description: 字符串转化为Date类型
     * @Param: [time]
     * @Return: java.util.Date
     * @Author: SoCMo
     * @Date: 2019/12/11
     */
    public static Date stringToDay(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @Description: 字符串转化为Date类型，最小单位为秒
     * @Param: [time]
     * @return: java.util.Date
     * @Author: SoCMo
     * @Date: 2020/7/16
     */
    public static Date stringToDate(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String DateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return simpleDateFormat.format(date);
    }


    /**
     * @Description: 返回今日日期, 不包含小时、分钟、秒
     * @Param: []
     * @return: java.util.Calendar
     * @Author: SoCMo
     * @Date: 2020/3/26
     */
    public static Calendar todayCreate() {
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.set(calendarNow.get(Calendar.YEAR), calendarNow.get(Calendar.MONTH), calendarNow.get(Calendar.DATE), 0, 0, 0);
        return calendarNow;
    }

    /**
     * @Description: 只获取日期，不包含小时、分钟、秒
     * @Method: [date]
     * @Return: java.lang.String
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/26 21:23
     */
    public static String dateWithoutHMS(Date date) {
        String str = DateToString(date);
        str = str.substring(0, str.indexOf(" "));
        return str;
    }

    /**
     * @Description: 获取结束时间到现在还剩多少天
     * @Method: [date]
     * @Return: java.lang.Integer
     * @Version: 1.0
     * @Author: pongshy
     * @Date: 2021/4/27 21:18
     */
    public static Integer getDeadline(Date date) {
        Date now = new Date(System.currentTimeMillis());
        if (date.before(now)) {
            return 0;
        }
        Integer deadline = 0;
        deadline = (int)((date.getTime() - now.getTime()) / (1000* 3600 * 24));
        return deadline;
    }

    /**
     * @Description: 时单位转时分单位格式
     * @Param: [hour]
     * @return: java.lang.String
     * @Author: SoCMo
     * @Date: 2020/3/29
     */
    public static String timeSlotToString(double hour) {
        return "" + (int) hour +
                "时" +
                (int) ((hour - (int) hour) * 60) +
                "分";
    }

    public static String getNowStrTimeOnlyYMD() {
        return DateToString(todayCreate().getTime());
    }
}
