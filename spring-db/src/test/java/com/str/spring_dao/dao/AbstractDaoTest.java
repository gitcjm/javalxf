package com.str.spring_dao.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractDaoTest {

    @Test
    public void getEntityClass() {
        UserDao userDao = new UserDao();
        // 将getEntityClass()方法修改为protected后再测试
        System.out.println(userDao.getEntityClass());

        BookDao bookDao = new BookDao();
        System.out.println(bookDao.getEntityClass());
    }
}