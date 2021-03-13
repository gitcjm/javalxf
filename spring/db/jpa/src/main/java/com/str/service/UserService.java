package com.str.service;

import com.str.entity.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class UserService {
    @PersistenceContext
    EntityManager em;

    public void print(User user) {
        System.out.println(user.getId() + "\t" + user.getEmail() + "\t" + user.getPassword() + "\t" + user.getName());
    }

    public User getUserById(Long id) {
        User user = this.em.find(User.class, id);
        if (user == null) {
            throw new RuntimeException("User not found by id: " + id);
        }
        return user;
    }

    // 一个简单的查询就这么复杂，太恐怖了！那么，这种语法存在的意义是什么呢？
    public User fetchUserByEmail(String email) {
        // CriteriaBuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> q = cb.createQuery(User.class);
        Root<User> r = q.from(User.class);
        q.where(cb.equal(r.get("email"), cb.parameter(String.class, "e")));
        TypedQuery<User> query = em.createQuery(q);
        // 绑定参数
        query.setParameter("e", email);
        // 执行查询
        List<User> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    // 正常人还是建议写JPQL查询，它的语法和HQL差不多
    public User getUserByName(String name) {
        String jpql = "SELECT u FROM User u WHERE u.name = :n";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("n", name);
        List<User> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    // 像hibernate一样，JPA也支持NamedQuery，即，先给查询起个名字，再按名字创建查询
    // NamedQuery需要在实体类上标注注解，这样也是为了把查询语句和业务逻辑分离吧
    public User login(String email, String password) {
        TypedQuery<User> query = em.createNamedQuery("login", User.class);
        query.setParameter("e", email);
        query.setParameter("p", password);
        List<User> list = query.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }

    // 添加一条记录倒是简单！
    public User register(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        this.em.persist(user);
        // 这时，user已经写入数据库了
        System.out.println(user.getId());
        return user;
    }
}
