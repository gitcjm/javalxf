package com.str.service;

import com.str.entity.Comment;
import com.str.util.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * 抽象类不能实例化，只能用于继承。现在就正好需要这种特性！
 * */
@Component
@Transactional
public abstract class AbstractService<T> {
    @PersistenceContext
    EntityManager em;

    protected T getById(long id) {
        T t = em.find(getEntityClass(), id);
        if (t == null) {
            throw new RuntimeException("Not found by id: " + id);
        }
        return t;
    }

    /**
     * 获取参数化类型的实际类型,比如User, Book, Order等
     * */
    private Class<T> getEntityClass() {
        // 获取实例化对象的类型，如: userService对象的UserService，bookService的BookService
        Class<T> clazz = (Class<T>) this.getClass();
        // 获取该类型的泛型父类类型，比如：对UserService来说就是AbstractService<User>
        Type t = clazz.getGenericSuperclass();
        // 需要这个判断吗？我怎么觉得有点画蛇添足！
        if (t instanceof ParameterizedType) {
            ParameterizedType pt = (ParameterizedType) t;
            return (Class<T>) pt.getActualTypeArguments()[0];
        }
        throw new RuntimeException("参数化类型获取失败.");
    }

    // 传入非持久化对象
    protected T create(T t) {
        em.persist(t);
        return t;
    }

    // 必须是根据ID查询的持久化对象
    protected boolean delete(long id) {
        T t = getById(id);
        if (t != null) {
            em.remove(t);
            return true;
        }
        return false;
    }

    // 此处传入的是持久化对象
    protected void update(T t) {
        em.clear();
        em.merge(t);
    }

    /**
     * 参考廖雪峰的Spring2.0核心技术与最佳实践
     * 采用JPA实现
     * */
    // 执行一次查询, 并返回一个唯一结果，通用版
    protected Object queryForObject(final String select, final Object[] values) {
        Query query = em.createQuery(select);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.getSingleResult();
    }

    // 执行一次查询，返回一个唯一实体
    protected T queryForEntity(final String select, final Object[] values) {
        TypedQuery<T> query = em.createQuery(select, getEntityClass());
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query.getSingleResult();
    }

    // 分页查询
    protected List<T> queryForList(final String select, final Object[] values, final Page page) {
        TypedQuery<T> query = em.createQuery(select, getEntityClass());
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getPageSize());
        return (List<T>) query.getResultList();
    }

    // 分页查询：计算了总页数
    protected List<T> queryForListHasPages(final String selectCount, final String select,
                                           final Object[] values, final Page page) {

        Long count = (Long) queryForObject(selectCount, values);
        page.setTotalCount(count.intValue());
        if (page.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return queryForList(select, values, page);
    }
}
