package com.str.spring_dao.service;

import com.str.spring_dao.dao.UserDao;
import com.str.spring_dao.entity.User;

import java.util.List;

public class UserService extends UserDao {

    public User getUserById(long id) {
        return super.getById(id);
    }

    public List<User> getUsers() {
        return super.getAll(1);
    }
}
