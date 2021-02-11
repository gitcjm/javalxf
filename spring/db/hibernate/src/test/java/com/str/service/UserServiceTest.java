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
    public void register() {
        User user = userService.register("hiber@hibernate.com", "123", "Hiber");
        userService.print(user);
    }

    @Test
    public void deleteUser() {
        Long userId = Long.valueOf(7);
        if ( userService.deleteUser(userId)) {
            System.out.println("User " + userId + " deleted success.");
            return;
        }
        System.out.println("User " + userId + " deleted failed.");
    }
}