package com.str.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
    // 分组匹配
    void groupMatch(String phoneNum) {
        // 电话号码
        Pattern p = Pattern.compile("(\\d{3,4})\\-(\\d{7,8})");
        Matcher m = p.matcher(phoneNum);
        if (m.matches()) {
            String g1 = m.group(1);
            String g2 = m.group(2);
            //String g0 = m.group(0);
            System.out.println(g1);
            System.out.println(g2);
            //System.out.println(g0);
        } else {
            System.out.println("匹配失败");
        }
    }

    // 非贪婪匹配
    void notGreedyMatch(String num) {
        // 数字末尾0的个数
        Pattern pattern = Pattern.compile("(\\d+?)(0*)");
        Matcher matcher = pattern.matcher(num);
        if (matcher.matches()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            // group()或group(0)是整个字符串num
            //System.out.println(matcher.group());
        }
    }

    // 判断小数后面末尾0的个数
    void getDecimalTailZero(String decimal) {
        Pattern pattern = Pattern.compile("(\\d+)\\.(\\d+)(0*)");
        Matcher matcher = pattern.matcher(decimal);
        if (matcher.matches()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3));
        } else {
            System.out.println("没有匹配");
        }
    }
}
