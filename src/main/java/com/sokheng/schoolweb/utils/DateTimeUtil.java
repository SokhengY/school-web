package com.sokheng.schoolweb.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeUtil {

    public static String PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static Timestamp stringToTimestamp(String time){

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(PATTERN);
            return new Timestamp(formatter.parse(time).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
