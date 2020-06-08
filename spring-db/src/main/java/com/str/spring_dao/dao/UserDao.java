package com.str.spring_dao.dao;

import com.str.spring_dao.entity.User;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@Transactional
public class UserDao extends AbstractDao<User> {

    public void update(String name, long id) {
        super.getJdbcTemplate().update(
                "UPDATE user SET name = ? WHERE id = ?",
                name, id
        );
    }

    // 简单的insert
    public void insert(String email, String password, String name) {
        long createAt = System.currentTimeMillis();
        int suc = super.getJdbcTemplate().update(
                "INSERT INTO user (email, password, name, createAt)" +
                        " VALUES (?,?,?," + createAt + ")",
                new Object[] {email, password, name}
        );
        if (1 != suc) {
            throw new RuntimeException("Insert failed.");
        }

    }

    // 获取id的insert
    public User insertUser(String email, String password, String name) {
        long createAt = System.currentTimeMillis();
        String sql = "INSERT INTO user (email, password, name, createAt)" +
                " VALUES (?,?,?," + createAt + ")";
        // 装配sql语句
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
        // 执行查询
        if (1 != super.getJdbcTemplate().update(psc, holder)) {
            throw new RuntimeException("Insert failed.");
        }
        return new User(holder.getKey().longValue(), email, password, name, createAt);
    }
}
