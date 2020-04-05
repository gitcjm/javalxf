package com.str.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserServiceTest {

    ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
    UserService userService = context.getBean(UserService.class);

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

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
        User user = userService.register("cjm@test.com", "123", "崔军明");
    }
}