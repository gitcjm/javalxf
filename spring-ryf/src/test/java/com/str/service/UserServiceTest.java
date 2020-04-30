package com.str.service;

import com.str.AppConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {
    /**
     * 如果没有spring, 你就得这样:
     * MailService mailService = new MailService();
     * UserService userService = new UserService();
     * userService.setMailService(mailService);
     * 如果有多个类用到 MailService 的话，就得有多个mailService实例
     * */
    // 自从有了spring ：）
    // 通过xml配置文件（放在resources下），把Bean的依赖关系描述出来，然后让容器来创建并装配Bean
    //ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

    /**
     * 使用Annotation配置，更进一步，可以完全不需要XML，让Spring自动扫描Bean并组装它们
     */
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    // 从容器中获取Bean
    UserService userService = context.getBean(UserService.class);

    @org.junit.Test
    public void login() {
        User user = userService.login("361335019@qq.com", "123");
    }

    @org.junit.Test
    public void getUser() {
        User user = userService.getUser(2);
        System.out.println(user.getName());
    }

    @org.junit.Test
    public void register() {
        User user = userService.register("mail-cjm@163.com", "123", "崔军明");
        System.out.println(user.getId());

    }

    @Test
    public void logout() throws InterruptedException {
        User user = userService.login("163mail_nql@163.com", "123");
        Thread.sleep(1000);
        userService.logout(user);
    }

    Validators validators = context.getBean(Validators.class);

    @Test
    public void validate() {
        validators.validate("163mail+cjm@163.com", "12345", "cjm");
    }

}