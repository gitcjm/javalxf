package com.str.service;

import com.str.AppConfig;
import com.str.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;
import java.time.ZoneId;

public class UserServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);

    @Test
    public void getUserById() {
        User user = userService.getUserById(2);
        userService.print(user);
    }

    @Test
    public void getAllUsers() {
    }

    @Test
    public void register() {
        User user = userService.register("xiaoyan@dp.com", "12345", "晓燕");
        userService.print(user);
    }

    @Test
    public void deleteUser() {
        userService.deleteUser(13);
    }
}