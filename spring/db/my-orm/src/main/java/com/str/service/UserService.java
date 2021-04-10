package com.str.service;

import com.str.entity.User;
import com.str.orm.DbTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserService {
    @Autowired
    DbTemplate db;

    public User getUserById(long id) {
        return db.get(User.class, id);
    }

    /**
     * 这是廖雪峰的ORM的典型应用：链式语句
     * 非常棒！ Very good!
     * */
    public String getNameByEmail(String email) {
        User user = db.select("name")
                .from(User.class)
                .where("email=?", email)
                .unique();
        return user.getName();
    }

    public void print(User user) {
        System.out.println(user.getId() + "\t" + user.getEmail() + "\t" + user.getPassword()
                + "\t" + user.getName() + "\t" + user.getCreatedDateTime());
    }
}
