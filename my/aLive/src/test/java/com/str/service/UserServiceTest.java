package com.str.service;

import com.str.entity.User;
import com.str.util.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * If you are using JUnit 4, don’t forget to also add @RunWith(SpringRunner.class) to your test. (see spring boot doc)
 * */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void getUserById() {
        User user = userService.getUserById(1);
        System.out.println(user);
    }

    @Test
    public void register() {
        User user = userService.register(
                "jerry@disney.com", "12345",
                "杰瑞", "1390359",
                "Disney co. in America");
        System.out.println(user);
    }

    @Test
    public void signin() {
        User user = userService.signin("cjm@163.com", "123");
        System.out.println(user);
    }

    @Test
    public void getUserListHasPages() {
        Page page = new Page(1, 2);
        List<User> userList = userService.getUserListHasPages(2L, page);
        for (User user : userList) {
            System.out.println(user);
        }
        System.out.println("总记录：" + page.getTotalCount());
        System.out.println("总页数：" + page.getPageCount());
    }
}