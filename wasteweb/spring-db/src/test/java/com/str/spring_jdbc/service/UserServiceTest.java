package com.str.spring_jdbc.service;

import com.str.spring_jdbc.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class UserServiceTest {

    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    //service userService = context.getBean(service.class);
    UserServiceReduce userService = context.getBean(UserServiceReduce.class);

    @Before
    public void setUp() throws Exception {
        userService.register("cjm@163.com", "123", "cjm");
        userService.register("Bob@hotmail.com", "1234", "Bob");
        userService.register("Jerry@gmail.com", "12345", "Jerry");
        userService.register("Tom@Yahoo.com", "123", "Tom");
        userService.register("George@gmail.com", "345", "George");
    }

    @Test
    public void insertUser() {
        User user = userService.insertUser("PigPage@pig.com", "87", "PigPage");
        System.out.println(user.toString());
    }

    @Test
    public void getUserById() {
        System.out.println(userService.getUserById(2).toString());
    }

    @Test
    public void getUserByName() {
        System.out.println(userService.getUserByName("cjm").toString());
    }

    @Test
    public void getUsers() {
        List<User> users = userService.getUsers(2);
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void updateUser() {
        String name = "Tom";
        userService.updateUser("2227850", name);
        System.out.println(userService.getUserByName(name).toString());
    }

}