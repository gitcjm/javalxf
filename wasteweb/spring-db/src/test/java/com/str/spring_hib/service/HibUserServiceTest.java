package com.str.spring_hib.service;

import com.str.spring_hib.HibAppConfig;
import com.str.spring_hib.entity.HibUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class HibUserServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(HibAppConfig.class);
    HibUserService userService = context.getBean(HibUserService.class);

    @Before
    public void setUp() throws Exception {
        userService.register("Bob@hotmail.com", "1234", "Bob");
        userService.register("Jerry@gmail.com", "12345", "Jerry");
        userService.register("Tom@Yahoo.com", "123", "Tom");
    }

    @Test
    public void register() {
        userService.register("George@gmail.com", "345", "George");
    }

    @Test
    public void login() {
        HibUser user = userService.login("Tom@Yahoo.com", "123");
        System.out.println(user.toString());
    }

    @Test
    public void getUsers() {
        userService.getUsers().forEach(user -> {
            System.out.println(user.toString());
        });
    }

}