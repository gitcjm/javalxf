package com.str.spring_dao.service;

import com.str.spring_dao.dao.UserDao;
import com.str.spring_dao.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserService {
    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUserById(long id) {
        return userDao.getById(id);
    }

    public List<User> getUsers() {
        return userDao.getAll(1);
    }
}
