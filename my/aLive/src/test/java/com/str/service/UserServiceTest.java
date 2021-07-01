package com.str.service;

import com.str.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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
                "xiaoyan@dp.com", "123",
                "晓燕", "1390359",
                "苏州盛泽工业区");
        System.out.println(user);
    }
}