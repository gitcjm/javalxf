package com.str.spring_batis.service;

import com.str.spring_batis.entity.BaUser;
import com.str.spring_batis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper;

    public BaUser getUserById(long id) {
        BaUser user = userMapper.getById(id);
        if (user == null) {
            throw new RuntimeException("User not found by id");
        }
        return user;
    }

    public List<BaUser> getUsers(int offset, int maxResults) {
        return userMapper.getAll(offset, maxResults);
    }

    public void register(String email, String password, String name) {
        BaUser user = new BaUser(email, password, name);
        user.setCreateAt(System.currentTimeMillis());
        userMapper.insert(user);
    }

    // 走过弯路！
    public void updateUser(long id, String name, long createAt) {
        BaUser user = getUserById(id);
        user.setName(name);
        user.setCreateAt(createAt);
        userMapper.update(user);
    }

    public void deleteUser(long id) {
        userMapper.deleteById(id);
    }

}
