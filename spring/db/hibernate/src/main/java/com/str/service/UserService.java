package com.str.service;

import com.str.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class UserService {
    @Autowired
    HibernateTemplate hibernateTemplate;

    public void print(User user) {
        System.out.println(user.getId() + "\t" + user.getEmail() + "\t" + user.getPassword() + "\t" + user.getName());
    }

    public User register(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        // 我靠，插入数据库就这么简单！
        hibernateTemplate.save(user);
        // 现在已经自动获得了id
        System.out.println(user.getId());
        return user;
    }

    public boolean deleteUser(Long id) {
        User user = hibernateTemplate.get(User.class, id);
        if (user != null) {
            hibernateTemplate.delete(user);
            return true;
        }
        return false;
    }

    public boolean updateUs
}
