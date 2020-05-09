package com.str;

import com.str.service.User;
import com.str.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AppConfig {


    /*// 测试一下Annotation配置
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        User user = userService.login("361335019@qq.com", "123");
        System.out.println(user.getName());

    }

    // 为了使用JUnit测试，我只好在此创建userService // 奇怪，没必要呀！
    public static UserService getUserService() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        return userService;
    }*/

    // 要这个AppConfig类有什么用？
    // 通过这个类来扫描装配Bean
    // 如果把UserServiceTest测试放在com.str包的顶层，然后加上这两个注解，应该也可以
    // 就把这个类作为桥梁吧！这样挺好
}
