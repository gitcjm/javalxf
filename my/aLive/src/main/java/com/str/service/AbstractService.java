package com.str.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * AbstractService是UserService, BookService, OrderService等的父类
 * 该类由Hibernate实现, 也可以由Spring JdbcTemplate, MyBatis等实现
 * 这样就把底层的实现方式封装了,不管底层使用什么框架实现数据库查询, 都不影响上层的代码
 * DAO层也就是干这些工作的,只是因为逻辑简单,没有再抽取DAO层而已
 *
 * @author cjm
 * */

@Component
@Transactional
public abstract class AbstractService<T> {

    @Autowired
    HibernateTemplate hibernateTemplate;

    public T getById(long id) {
        T entity = (T) hibernateTemplate.get(getEntityClass(), id);
        if (entity == null) {
            throw new RuntimeException("Found failed.");
        }
        return entity;
    }

    public T create(T t) {
        hibernateTemplate.save(t);
        return t;
    }

    public void deleteById(long id) {
        T entity = getById(id);
        if (entity == null) {
            throw new RuntimeException("Delete failed.");
        }
        hibernateTemplate.delete(entity);
    }

    public void updateById(long id) {
        T entity = getById(id);
        if (entity == null) {
            throw new RuntimeException("Update failed.");
        }
        hibernateTemplate.update(entity);
    }

    /* 查询实体列表的默认方法
     * 这个方法分页查询全部列表, 用处不大, 后代可以覆盖此方法, 根据更多的条件查询*/
    public List<T> getList(DetachedCriteria criteria, int pageIndex, int pageSize) {
        return (List<T>) hibernateTemplate.findByCriteria(criteria, pageIndex, pageSize);
    }

    // 获取参数化类型的实际实体类型,比如User, Book, Order等
    private Class<T> getEntityClass() {
        Class<T> clazz = (Class<T>) this.getClass();
        Type t = clazz.getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        return (Class<T>) pt.getActualTypeArguments()[0];
    }
}
