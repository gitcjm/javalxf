package com.str.service;

import com.str.entity.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class UserService {
    @Autowired
    HibernateTemplate hibernateTemplate;

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

    public void updateUser(Long id, String name) {
        User user = hibernateTemplate.load(User.class, id);
        user.setName(name);
        hibernateTemplate.update(user);
    }

    public User login(String email, String password) {
        User example = new User();
        example.setEmail(email);
        example.setPassword(password);
        List<User> list = hibernateTemplate.findByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    public User qryByCriteria(String email, String password) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password));
        List<User> list = (List<User>) hibernateTemplate.findByCriteria(criteria);
        return list.isEmpty() ? null : list.get(0);
    }

    public User qryByHQL(String email, String password) {
        List<User> list = (List<User>) hibernateTemplate.find(
                "FROM User WHERE email=?0 AND password=?1", email, password);
        return list.isEmpty() ? null : list.get(0);
    }
}
