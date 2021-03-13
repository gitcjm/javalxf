package com.str.service;

import com.str.AppConfig;
import com.str.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class UserServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    UserService userService = context.getBean(UserService.class);

    @Test
    public void getUserById() {
        User user = userService.getUserById(5);
        userService.print(user);
    }

    // 在生产环境，最后要关掉容器context
    //((ConfigurableApplicationContext) context).close();
}