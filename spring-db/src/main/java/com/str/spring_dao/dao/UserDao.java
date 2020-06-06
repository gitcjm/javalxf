package com.str.spring_dao.dao;

import com.str.spring_dao.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserDao extends AbstractDao<User> {

    public void update(String name, long id) {
        super.getJdbcTemplate().update(
                "UPDATE user SET name = " + name + " WHERE id = ?",
                id
        );
    }

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
}
