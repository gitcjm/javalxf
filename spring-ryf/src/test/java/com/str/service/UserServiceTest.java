package com.str.service;

import com.str.AppConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {
    /**
     * 如果没有spring, 你就得这样:
     * MailService mailService = new MailService();
     * UserService userService = new UserService();
     * userService.setMailService(mailService);
     * 如果有多个类用到 MailService 的话，就得有多个 mailService 实例
     * */
    /*// 自从有了spring ：）
    ApplicationContext context = new ClassPathXmlApplicationContext("application-drop.xml");
    // 获取Bean
    UserService userService = context.getBean(UserService.class);*/

    UserService userService = AppConfig.getUserService();

    @org.junit.Test
    public void login() {
        User user = userService.login("361335019@qq.com", "123");
    }

    @org.junit.Test
    public void getUser() {
        User user = userService.getUser(3);
        System.out.println(user.getName());
    }

    @org.junit.Test
    public void register() {
        User user = userService.register("mail-cjm@163.com", "123", "崔军明");
        System.out.println(user.getId());

    }

    @Test
    public void logout() throws InterruptedException {
        User user = userService.login("163mail_nql@163.com", "123");
        Thread.sleep(1000);
        userService.logout(user);
    }

}