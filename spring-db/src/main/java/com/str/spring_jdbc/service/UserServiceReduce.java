package com.str.spring_jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class UserServiceReduce {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional
    public void register(String email, String password, String name) {
        String sql = "INSERT INTO users(email, password, name) VALUES(?,?,?)";
        if (1 != jdbcTemplate.update(sql, email, password, name)) {
            throw new RuntimeException("Insert failed.");
        }
    }

    // 对寥雪峰的register()方法进行去lambda化
    @Transactional
    public User insertUser(String email, String password, String name) {
        String sql = "INSERT INTO users(email, password, name) VALUES(?,?,?)";

        // 通过prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)获取自增的ID
        KeyHolder holder = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setObject(1, email);
                ps.setObject(2, password);
                ps.setObject(3, name);
                return ps;
            }
        };
        if (1 != jdbcTemplate.update(psc, holder)) {
            throw new RuntimeException("Insert failed.");
        }
        return new User(holder.getKey().longValue(), email, password, name);
    }

    public User getUserById(long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(User.class), id);
    }

    public User getUserByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";
        return jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(User.class), name);
    }

    public List<User> getUsers(int pageIndex) {
        int limit = 3;
        int offset = limit * (pageIndex - 1);
        String sql = "SELECT * FROM users LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(User.class), limit, offset);
    }

    public void updateUser(String password, String name) {
        String sql = "UPDATE users SET password=? WHERE name=?";
        if (1 != jdbcTemplate.update(sql, password, name)) {
            throw new RuntimeException("user not found by name.");
        }
    }
}
