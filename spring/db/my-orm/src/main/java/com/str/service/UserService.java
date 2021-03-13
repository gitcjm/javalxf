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

    public void print(User user) {
        System.out.println(user.getId() + "\t" + user.getEmail() + "\t" + user.getPassword()
                + "\t" + user.getName() + "\t" + user.getCreatedDateTime());
    }
}
