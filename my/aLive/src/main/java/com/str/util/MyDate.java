package com.str.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期相关的工具
 * */
public class MyDate {

    // 将字符串格式的日期转换为long
    public static long strDate2Long(String strDate, String pattern) {
        ParsePosition position = new ParsePosition(0);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(strDate, position);
        return date.getTime();
    }

}
