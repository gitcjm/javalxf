package com.str.service;

import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MailService {
    //private ZoneId zoneId = ZoneId.systemDefault();
    private ZoneId zoneId;

    public MailService() {
        setZoneId(ZoneId.systemDefault());
    }

    public void setZoneId(ZoneId zoneId) {
        this.zoneId = zoneId;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public String getTime() {
        return ZonedDateTime.now(getZoneId()).format(
                DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    // 登录成功邮件
    public void sendLoginMail(User user) {
        System.out.println(String.format(
                "Hi, %s! You are logged in at %s", user.getName(), getTime()));
    }

    // 注册成功邮件
    public void sendRegistrationMail(User user) {
        // 此处使用err打印，而非out打印，就是为了让输出打印的文本为红色！无他！
        System.out.println(String.format("Welcome, %s", user.getName()));
    }

    // 退出登录邮件
    public void sendLogoutMail(User user) {
        System.out.println(String.format("Bye %s! You are logged out at %s",
                user.getName(), getTime()));
    }
}
