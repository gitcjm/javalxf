package com.str.spring_batis.service;

import com.str.spring_batis.BaAppConfig;
import com.str.spring_batis.entity.BaUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(BaAppConfig.class);
    UserService userService = context.getBean(UserService.class);

    @Before
    public void setUp() throws Exception {
        /*userService.register("Bob@hotmail.com", "1234", "Bob");
        userService.register("Jerry@gmail.com", "12345", "Jerry");
        userService.register("Tom@Yahoo.com", "123", "Tom");
        userService.register("George@gmail.com", "345", "George");*/
    }

    @Test
    public void getUserById() {
        BaUser user = userService.getUserById(19);
        System.out.println(user.toString());
    }

    @Test
    public void getUsers() {
        List<BaUser> users = userService.getUsers(0, 4);
        for (BaUser user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void updateUser() {
        long id = 19;
        userService.updateUser(id, "Rosee", System.currentTimeMillis());
        System.out.println(userService.getUserById(id));
    }

    @Test
    public void deleteUser() {
        userService.deleteUser(20);
    }

    @Test
    public void register() {
        userService.register("cjm@Yahoo.com", "123", "cjm");
    }
}