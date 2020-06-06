package com.str.spring_dao.service;

import com.str.spring_dao.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class UserServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getUserById() {
        userService.getUserById(21);

    }

    @Test
    public void getUsers() {
    }
}