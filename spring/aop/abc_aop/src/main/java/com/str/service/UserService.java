package com.str.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    // 使用@Autowired注解就相当于把一个Bean注入到字段中
    // 和XML配置相比，@Autowired大幅简化了注入
    @Autowired
    MailService mailService;

    // 自己创建的@Bean，也是通过@Autowired注入字段中的
    @Autowired
    HikariDataSource dataSource;

    // 读取数据库中的用户列表
    public List<User> listUsers(int row_count) {
        List<User> list = new ArrayList<>();

        String sql = "SELECT * FROM user LIMIT ?";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, row_count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String name = rs.getString("name");

                User user = new User(id, email, password, name);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public User login(String email, String password) {
        for (User user : listUsers(10)) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                mailService.sendLoginMail(user);
                return user;
            }
        }
        throw new RuntimeException("login failed");
    }

    // 数据库版用户注册
    public void register(String email, String password, String name) {
        /*
        // 由Aspect来审查
        listUsers(10).forEach((user) -> {
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new RuntimeException("email exist.");
            }
        });*/
        String sql = "INSERT INTO user (email, password, name) VALUES (?,?,?)";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, email);
            ps.setObject(2, password);
            ps.setObject(3, name);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
