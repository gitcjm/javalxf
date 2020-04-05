package com.str.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MailService {
    private ZoneId zoneId = ZoneId.systemDefault();

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public String getTime() {
        return ZonedDateTime.now(this.zoneId).format(
                DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    // 登录成功邮件
    public void sendLoginMail(User user) {
        System.err.println(String.format(
                "Hi, %s! You are logged in at %s", user.getName(), getTime()));
    }

    // 注册成功邮件
    public void sendRegistrationMail(User user) {
        System.err.println(String.format("Welcome, %s", user.getName()));
    }
}
