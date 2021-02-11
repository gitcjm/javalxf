package com.str.service;

import com.str.AppConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);

    @org.junit.Test
    public void getUserById() {
        userService.printUser(userService.getUserById(2));
    }

    @org.junit.Test
    public void getUserByName() {
        userService.printUser(userService.getUserByName("cjm"));
    }

    @org.junit.Test
    public void getUserByEmail() {
    }

    @org.junit.Test
    public void getUsers() {
        for (User user : userService.getUsers(2)) {
            userService.printUser(user);
        }
    }

    @Test
    public void register() {
        //User user = userService.register("wh@whitehouse.com", "123", "白宫");
        User user = userService.register("weini@xiong.com", "456", "维尼");
        userService.printUser(user);
    }
}