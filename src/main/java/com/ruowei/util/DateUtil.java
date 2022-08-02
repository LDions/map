package com.ruowei.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateUtil extends org.apache.commons.lang3.time.DateUtils {

    /**
     * 获取某月有几天
     *
     * @param year 年份
     * @param month 月分
     * @return 天数
     */
    public static int getMonthHasDays(String year, String month) {
        String day31 = ",01,03,05,07,08,10,12,";
        String day30 = "04,06,09,11";
        int day = 0;
        if (day31.contains(month)) {
            day = 31;
        } else if (day30.contains(month)) {
            day = 30;
        } else {
            int y = Integer.parseInt(year);
            if ((y % 4 == 0 && (y % 100 != 0)) || y % 400 == 0) {
                day = 29;
            } else {
                day = 28;
            }
        }
        return day;
    }

    /**
     * 将String时间格式为: [yyyy-MM-dd HH:mm:ss]转为Instant
     * @param str
     * @return
     * @author czz
     */
    public static Instant stringToInstant(String str) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(str,df);
        ZoneId zone = ZoneId.systemDefault();
        return localDateTime.atZone(zone).toInstant();
    }

    public static List<String> getEachYearAndMonth(String startTime, String endTime) {
        List<String> dateList = new ArrayList<>();
        dateList.add(startTime);
        startTime = startTime.concat("-01 00:00:00");
        endTime = endTime.concat("-01 00:00:00");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat easySdf = new SimpleDateFormat("yyyy-MM");
        try {
            calendar.setTime(sdf.parse(startTime));
            while (true) {
                calendar.add(Calendar.MONTH, 1);
                if (sdf.parse(endTime).after(calendar.getTime())) {
                    dateList.add(easySdf.format(calendar.getTime()));
                }else {
                    break;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (!startTime.equals(endTime)) {
            dateList.add(endTime.substring(0, 7));
        }
        return dateList;
    }
}
