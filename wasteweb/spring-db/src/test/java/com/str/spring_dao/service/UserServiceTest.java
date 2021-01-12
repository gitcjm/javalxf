package com.str.spring_dao.service;

import com.str.spring_dao.AppConfig;
import com.str.spring_dao.dao.UserDao;
import com.str.spring_dao.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class UserServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    //UserService userService = context.getBean(UserService.class);
    // 也可以不用UserService
    UserDao userDao = context.getBean(UserDao.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getUserById() {
        //User user = userService.getUserById(21);
        User user = userDao.getById(21);
        System.out.println(user.toString());
    }

    @Test
    public void getUsers() {
        List<User> users = userDao.getAll(1);
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void insertUser() {
        userDao.insert("cjm@163.com", "1234", "cjm");
    }

    @Test
    public void deleteUser() {
        userDao.deleteById(23);
    }

    @Test
    public void insertUserX() {
        User user = userDao.insertUser("Alice@gmail.com", "123", "Alice");
        System.out.println(user.toString());
    }
}