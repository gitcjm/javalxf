package com.str;


import com.str.service.User;
import com.str.service.UserService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import java.util.List;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AppConfig {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        // 打印数据库中的用户列表
        List<User> users = userService.listUsers(10);
        for (User user : users) {
            System.out.println(user.getId() + "\t" + user.getEmail() + "\t" +
                    user.getPassword() + "\t" + user.getName());
        }
        // 用户登录
        User user = userService.login("alice@gmail.com", "1234");

    }

    // 如果一个Bean不在我们的package管理之内，就要自己编写一个方法创建它
    // spring会对标记为@Bean的方法进行调用（只调用一次，返回单例）
    @Bean
    HikariDataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost/lxfdb");
        config.setUsername("root");
        config.setPassword("Mysql80@deb");
        config.addDataSourceProperty("autoCommit","true");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(config);
    }
}
