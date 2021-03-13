package com.str.service;

import com.str.entity.User;
import com.str.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Component
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper;

    public void print(User user) {
        System.out.println(user.getId() + "\t" + user.getEmail() + "\t" + user.getPassword() +
                "\t" + user.getName() + "\t" + user.getCreatedDateTime());
    }

    public User getUserById(long id) {
        User user = userMapper.getById(id);
        if (user == null) {
            throw new RuntimeException("User not found by id.");
        }
        return user;
    }

    public List<User> getAllUsers(int offset, int maxResults) {
        return userMapper.getAll(offset, maxResults);
    }

    public User register(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setCreatedAt(Instant.now().toEpochMilli());
        //user.setCreatedAt(System.currentTimeMillis());      // 廖雪峰老师的代码更易读、简洁！
        userMapper.insert(user);
        System.out.println(user.getId());
        return user;
    }

    public void deleteUser(long id) {
        userMapper.deleteById(id);

    }
}
