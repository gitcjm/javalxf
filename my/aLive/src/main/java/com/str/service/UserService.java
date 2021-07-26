package com.str.service;

import com.str.entity.User;
import com.str.util.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserService extends AbstractService<User> {

    public User getUserById(long id) {
        return getById(id);
    }

    public User register(String email, String password,  String name, String mobile, String address) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setMobile(mobile);
        user.setAddress(address);
        return create(user);
    }

    public void deleteUser(long id) {
        delete(id);
    }

    // 传入非持久化对象，然后将其属性复制给持久化对象，最后更新持久化对象
    public void updateUser(User user) {
        // u为数据库中的持久化对象
        User u = getById(user.getId());
        if (u == null) {
            throw new RuntimeException("Update failed.");
        }
        // 将非持久化对象user的属性, 复制到持久化对象u中
        u.setPassword(user.getPassword());
        u.setName(user.getName());
        u.setMobile(user.getMobile());
        u.setAddress(user.getAddress());
        // 更新数据库中的持久化对象
        update(u);
    }

    // 登录
    public User signin(String email, String password) {
        String select = "select u from User u where u.email = ?0 and password = ?1";
        String[] parameters = {email, password};
        User user = queryForEntity(select, parameters);
        if (user == null) {
            throw new RuntimeException("Signin failed.");
        }
        return user;
    }

    // 某一时间后创建的用户列表
    @Deprecated
    public List<User> getUserList(long createdAt, Page page) {
        String select = "select u from User u where u.createdAt > ?0";
        Long[] parameters = { createdAt };
        return queryForList(select, parameters, page);
    }

    // 计算了总页数的用户列表
    public List<User> getUserListHasPages(long createdAt, Page page) {
        String selectCount = "select count(*) from User u where u.createdAt > ?0";
        String select = "select u from User u where u.createdAt > ?0";
        Long[] values = { createdAt };
        return queryForListHasPages(selectCount, select, values, page);
    }

}
