package com.str.spring_dao.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

public abstract class AbstractDao<T> extends JdbcDaoSupport {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        super.setJdbcTemplate(jdbcTemplate);
    }

    private String table;
    private Class<T> entityClass;
    private RowMapper<T> rowMapper;

    public AbstractDao() {
        // 获取当前类型的泛型类型
        this.entityClass = getEntityClass();
        this.table = this.entityClass.getSimpleName().toLowerCase();
        this.rowMapper = new BeanPropertyRowMapper<>(entityClass);
    }

    private Class<T> getEntityClass() {
        Method method = null;
        try {
            method = AbstractDao.class.getMethod("AbstractDao");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Parameter[] parameters = method.getParameters();
        return (Class<T>) parameters[0].getParameterizedType();
    }

    public T getById(long id) {
        return super.getJdbcTemplate().queryForObject(
                "SELECT * FROM " + table + " WHERE id = ?",
                this.rowMapper,
                id
        );
    }

    public List<T> getAll(int pageIndex) {
        int limit = 5;
        int offset = limit * (pageIndex - 1);
        return super.getJdbcTemplate().query(
                "SELECT * FROM " + table + " LIMIT ? OFFSET ?",
                this.rowMapper,
                limit, offset
        );
    }

    public void deleteById(long id) {
        super.getJdbcTemplate().update(
                "DELETE FROM " + table + " WHERE id = ?",
                id
        );
    }

}
