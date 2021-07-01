package com.str.service;

import com.str.entity.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
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
        deleteById(id);
    }

    public void updateUser(long id) {
        updateById(id);
    }

    // 根据日期查询用户,这只是一个演示
    public List<User> getUserList(Date date, int pageIndex, int pageSize) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.ge("createdAt", date.toInstant()));
        return getList(criteria,pageIndex, pageSize);
    }

}
