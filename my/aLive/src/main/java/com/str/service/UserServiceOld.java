package com.str.service;

import java.util.List;

import com.str.entity.User;
import com.str.util.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserServiceOld extends AbstractService<User> {

    // 对于Create操作,传入的永远是 非持久化对象
    public User register(String email, String password,  String name, String mobile, String address) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setMobile(mobile);
        user.setAddress(address);
        hibernateTemplate.save(user);
        return user;
    }

    public User getById(Long id) {
        User user = hibernateTemplate.get(User.class, id);
        if (user == null) {
            throw new RuntimeException("Found failed.");
        }
        return user;
    }

    public User signin(String email, String password) {
        User example = new User();
        example.setEmail(email);
        example.setPassword(password);
        List<User> list = hibernateTemplate.findByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<User> getUserList(int pageIndex, int pageSize) {
        Page page = new Page(pageIndex, pageSize);
        User example = new User();
        return hibernateTemplate.findByExample(example, page.getFirstResult(), pageSize);
    }

    // 对于Delete操作, 传入的必须是 持久化对象
    public void deleteUser(Long id) {
        User user = hibernateTemplate.get(User.class, id);
        if (user == null) {
            throw new RuntimeException("Delete failed.");
        }
        hibernateTemplate.delete(user);
    }

    // 对于Update操作, 传入的也必须是 持久化对象
    public void updateUser(User user) {
        User user1 = hibernateTemplate.get(User.class, user.getId());
        if (user1 == null) {
            throw new RuntimeException("Update failed.");
        }
        hibernateTemplate.update(user);
    }

}
