package com.str;

import com.str.service.User;
import com.str.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 创建一个Spring的IoC容器实例，让Spring容器为我们创建并装配（配置文件中）指定的Bean
        // Spring容器是通过读取XML文件，使用反射完成创建和装配的。
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        UserService userService = context.getBean(UserService.class);
        // 列出前10个用户
        List<User> listUser = userService.listUsers(10);
        for (User user : listUser) {
            System.out.println(user.getId() + "\t" + user.getEmail() + "\t" + user.getPassword() + "\t" + user.getName());
        }
        User user = userService.login("bob@example.com", "123");
        System.out.println(user.getName());
        User user2 = userService.getUser(3);
        System.out.println(user2.getId() + "\t" + user2.getEmail() + "\t" + user2.getName());
        //userService.registerDb("cjm@str.com", "12345", "CJM");
    }
}
