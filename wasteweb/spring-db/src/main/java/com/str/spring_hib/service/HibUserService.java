package com.str.spring_hib.service;

import com.str.spring_hib.entity.HibUser;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class HibUserService {

    @Autowired
    HibernateTemplate hibernateTemplate;

    // 要持久化一个User实例，只需要调用save()方法
    public HibUser register(String email, String password, String name) {
        // 创建一个User对象
        HibUser user = new HibUser();
        // 设置好各个属性
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        //不要设置id，因为使用了自增主键
        // 保存到数据库
        hibernateTemplate.save(user);
        // 现在已经自动获得了id
        //System.out.println(user.getId());
        return user;
    }

    // Hibernate总是用id来删除记录
    public boolean deleteUser(Long id) {
        // 先根据主键加载记录，再删除
        HibUser user = hibernateTemplate.get(HibUser.class, id);
        if (user != null) {
            hibernateTemplate.delete(user);
            return true;
        }
        return false;
    }

    public void updateUser(Long id, String password) {
        HibUser user = hibernateTemplate.load(HibUser.class, id);
        user.setPassword(password);
        hibernateTemplate.update(user);
    }

    /*// 使用example查询
    public HibUser login(String email, String password) {
        HibUser example = new HibUser();
        example.setEmail(email);
        example.setPassword(password);
        List<HibUser> list = hibernateTemplate.findByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<HibUser> getUsers() {
        HibUser example = new HibUser();
        return hibernateTemplate.findByExample(example);
    }*/

    // 使用criteria查询
    public HibUser login(String email, String password) {
        DetachedCriteria criteria = DetachedCriteria.forClass(HibUser.class);
        criteria.add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password));
        List<HibUser> list = (List<HibUser>) hibernateTemplate.findByCriteria(criteria);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<HibUser> getUsers() {
        DetachedCriteria criteria = DetachedCriteria.forClass(HibUser.class);
        return (List<HibUser>) hibernateTemplate.findByCriteria(criteria);
    }

}
