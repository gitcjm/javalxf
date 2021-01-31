package com.str.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Component
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public User getUserById(long id) {
        String sql = "SELECT * FROM user WHERE id = ?";

        return jdbcTemplate.execute((Connection conn) -> {
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
        String sql = "SELECT * FROM user WHERE name = ?";

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
                throw new RuntimeException("user not found by id.");
            }
        });
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";

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
        int limit = 3;
        int offset = limit * (pageIndex - 1);

        String sql = "select * from user limit ? offset ?";
        return jdbcTemplate.query(sql, new Object[] { limit, offset },
                new BeanPropertyRowMapper<>(User.class));
    }

}
