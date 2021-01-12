package com.str.spring_jdbc;

import com.str.AppConfig;
import com.str.service.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class UserService_jdbcTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService_jdbc us = context.getBean(UserService_jdbc.class);

    @Before
    public void setUp() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(us.register("cjm@163.com", "123", "cjm"));
        users.add(us.register("Bob@hotmail.com", "1234", "Bob"));
        users.add(us.register("Jerry@gmail.com", "12345", "Jerry"));
        users.add(us.register("Tom@Yahoo.com", "123", "Tom"));
    }

    @Test
    public void register() {
        System.out.println("register completed.");
    }

    @Test
    public void getUserById() {
        System.out.println(us.getUserById(2).getName());
    }

    @Test
    public void getUserByName() {
        System.out.println(us.getUserByName("Tom").getEmail());
    }

    @Test
    public void getUserByEmail() {
    }

    @Test
    public void getUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(us.getUsers(1));
        System.out.println(users.get(1));
    }

    @Test
    public void updateUser() {
    }

}