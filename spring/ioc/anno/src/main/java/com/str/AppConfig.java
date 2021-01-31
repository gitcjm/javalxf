package com.str;

import com.str.service.User;
import com.str.service.UserService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * */
@Configuration  // 表示这是一个配置类，用于创建IoC容器context
@ComponentScan  // 此注解告诉容器，自动搜索当前类所在的包及其子包，所以要注意包的层次结构
public class AppConfig {
    public static void main(String[] args) {
        // 创建context时，必须传入一个标注了@Configuration的类
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        // 容器为我们创建一个Bean
        UserService userService = context.getBean(UserService.class);
        // UserService中初始化的用户
        /*User user = userService.login("jerry@126.com", "pwd789");
        System.out.println(user.getName())*/;

        // 数据库中的用户列表
        List<User> users = userService.listUsers(10);
        for (User user : users) {
            System.out.println(user.getId() + "\t" + user.getEmail() + "\t" +
                    user.getPassword() + "\t" + user.getName());
        }

        User user = userService.login("alice@gmail.com", "1234");
        System.out.println(user.getName());
    }

    // 如果一个Bean不在我们的package管理之内，就要自己编写一个方法创建它
    // spring会对标记为@Bean的方法进行调用（只调用一次，返回单例）
    @Bean
    HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost/lxfdb");
        config.setUsername("root");
        config.setPassword("Mysql80@deb");
        config.addDataSourceProperty("autoCommit", "true");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(config);
    }
}
