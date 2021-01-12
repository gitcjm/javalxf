package com.str.spring_jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUserById(long id) {
        return jdbcTemplate.execute((Connection conn) -> {
            String sql = "SELECT * FROM users WHERE id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setObject(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new User(
                                rs.getLong("id"),
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("name")
                        );
                    }
                    throw new RuntimeException("user not found by id.");
                }
            }
        });
    }

    public User getUserByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";
        return jdbcTemplate.execute(sql, (PreparedStatement ps) -> {
            ps.setObject(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getLong("id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("name")
                    );
                }
                throw new RuntimeException("user not found by name.");
            }
        });
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { email },
                (ResultSet rs, int rowNum) -> {
                    return new User(
                            rs.getLong("id"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("name")
                    );
                });
    }

    public List<User> getUsers(int pageIndex) {
        int limit = 10;
        int offset = limit * (pageIndex - 1);
        String sql = "SELECT * FROM users LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[] {limit, offset},
                new BeanPropertyRowMapper<>(User.class));
    }

    public void updateUser(String name, long id) {
        String sql = "UPDATE users SET name=? WHERE id=?";
        if (1 != jdbcTemplate.update(sql, name, id)) {
            throw new RuntimeException("user not found by id.");
        }
    }

    public User register(String email, String password, String name) {
        String sql = "INSERT INTO users(email, password, name) VALUES(?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        if (1 != jdbcTemplate.update(
                (Connection conn) -> {
                    PreparedStatement ps = conn.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);
                    ps.setObject(1, email);
                    ps.setObject(2, password);
                    ps.setObject(3, name);
                    return ps;
                },
                holder)
        ) {
            throw new RuntimeException("Insert failed.");
        }
        return new User(holder.getKey().longValue(), email, password, name);
    }

}
