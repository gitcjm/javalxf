package com.str;

import com.str.service.User;
import com.str.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        /**
         * 如果没有spring, 你得这样:
         * MailService mailService = new MailService();
         * UserService userService = new UserService();
         * userService.setMailService(mailService);
         * 如果有多个类用到 MailService 的话，就得有多个 mailService 实例
         * */
        // 自从有了spring
        ApplicationContext context = new ClassPathXmlApplicationContext("application-drop.xml");
        // 获取Bean
        UserService userService = context.getBean(UserService.class);

        User user = userService.login("361335019@qq.com", "123");
        System.out.println(user.getName());
    }
}
