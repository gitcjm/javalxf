package com.str.service;

import com.str.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.List;

@Component
public class UserService implements EntityService<User> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<User> userRowMapper = new BeanPropertyRowMapper<>(User.class);

    @Override
    public User getEntityById(long id) {
        String sql = "select * from user where id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, userRowMapper);
    }

    @Override
    public List<User> getEntityList(int pageIndex, int pageSize) {
        int offset = pageSize * (pageIndex - 1);
        String sql = "select * from user limit ? offset ?";
        return jdbcTemplate.query(sql, new Object[] { pageSize, offset }, userRowMapper);
    }

    public User getUserByEmail(String email) {
        String sql = "select * from user where email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { email }, userRowMapper);
    }

    public User signin(String email, String password) {
        String sql = "select * from user where email = ? and password = ?";
        User user = getUserByEmail(email);
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new RuntimeException("login failed");
    }

    public User register(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setCreatedAt(System.currentTimeMillis());

        String sql = "Insert into user (email, password, name, createdAt) value (?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        if (1 != jdbcTemplate.update((conn) -> {
            var ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
