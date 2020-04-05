package com.str;

import com.str.service.User;
import com.str.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        UserService userService = context.getBean(UserService.class);
        User user = userService.login("361335019@qq.com", "123");
        System.out.println(user.getName());
    }
}
