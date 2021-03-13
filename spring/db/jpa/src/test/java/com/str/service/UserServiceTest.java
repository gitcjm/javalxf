package com.str.service;

import com.str.AppConfig;
import com.str.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class UserServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);

    @Test
    public void getUserById() {
        User user = userService.getUserById(Long.valueOf(10));
        if (user != null) {
            userService.print(user);
        }
    }

    @Test
    public void fetchUserByEmail() {
        User user = userService.fetchUserByEmail("bob@example.com");
        if (user == null) {
            throw new RuntimeException("User not found by email");
        }
        userService.print(user);
    }

    @Test
    public void getUserByName() {
        User user = userService.getUserByName("Jerry");
        if (user == null) {
            throw new RuntimeException("User not found by name");
        }
        userService.print(user);
    }

    @Test
    public void login() {
        User user = userService.login("alice@gmail.com", "1234");
        if (user == null) {
            throw new RuntimeException("User not exist");
        }
        userService.print(user);
    }

    @Test
    public void register() {
        User user = userService.register("marry@hotmail.com", "12345", "Marry");
        userService.print(user);
    }
}