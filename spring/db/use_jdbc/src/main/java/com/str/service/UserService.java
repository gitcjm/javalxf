package com.str.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
                throw new RuntimeException("user not found by name.");
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
        int limit = 5;
        int offset = limit * (pageIndex - 1);

        String sql = "select * from user limit ? offset ?";
        return jdbcTemplate.query(sql, new Object[] { limit, offset },
                new BeanPropertyRowMapper<>(User.class));
    }

    public void printUser(User user) {
        System.out.println(user.getId() + "\t" + user.getEmail() + "\t" + user.getPassword() + "\t" + user.getName());
    }

    public void updateUser(User user) {
        String sql = "UPDATE user SET name = ? WHERE id = ?";
        if (1 != jdbcTemplate.update(sql, user.getName(), user.getId())) {
            throw new RuntimeException("User not found by id.");
        }
    }

    // Insert操作时，如果有自增主键，则JdbcTemplate提供了一个KeyHolder来简化操作
    public User register(String email, String password, String name) {
        String sql = "Insert Into user (email, password, name) Values (?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        if (1 != jdbcTemplate.update(
                (Connection conn) -> {
                    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setObject(1, email);
                    ps.setObject(2, password);
                    ps.setObject(3, name);
                    return ps;
                },
                holder)) {
            throw new RuntimeException("Insert failed.");
        }
        return new User(holder.getKey().longValue(), email, password, name);
    }
}
