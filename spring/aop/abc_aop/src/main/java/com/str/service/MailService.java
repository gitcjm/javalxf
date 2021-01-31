package com.str.service;

import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 在用户登录或注册成功后发送邮件通知
 * */
// 这个@Component注解就相当于定义了一个Bean
@Component
public class MailService {
    private ZoneId zoneId = ZoneId.systemDefault();

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public String getTime() {
        return ZonedDateTime.now(this.zoneId).format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    public void sendLoginMail(User user) {
        System.out.println(String.format("Hi, %s! You are logged in at %s",
                user.getName(), getTime()));
    }

    public void sendRegistrationMail(User user) {
        System.out.println(String.format("Welcome, %s", user.getName()));
    }
}
