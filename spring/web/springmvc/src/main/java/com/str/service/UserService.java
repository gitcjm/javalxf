package com.str.service;

import com.str.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Statement;

@Component
public class UserService {
    final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);

    public User getUserById(String id) {
        String sql = "select * from user where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, userRowMapper);
    }

    public User getUserByEmail(String email) {
        String sql = "select * from user where email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { email }, userRowMapper);
    }

    public User signin(String email, String password) {
        logger.info("try login by {}...", email);
        User user = getUserByEmail(email);
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("login failed.");
    }

    public User register(String email, String password, String name) {
        logger.info("try register by {}...", email);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setCreatedAt(System.currentTimeMillis());

        // 将用户信息持久化
        String sql = "Insert Into user(email, password, name, createdAt) Value (?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        if (1 != jdbcTemplate.update((conn) -> {
            var ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, user.getEmail());
            ps.setObject(2, user.getPassword());
            ps.setObject(3, user.getName());
            ps.setObject(4, user.getCreatedAt());
            return ps;
        }, holder)) {
            throw new RuntimeException("Insert failed.");
        }

        user.setId(holder.getKey().longValue());
        return user;
    }
}
