package com.str.spring_jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {
    @Autowired
    JdbcTemplate jdbcTemplate;

    // @PostConstruct注解在构造方法之后执行，并且只执行一次
    @PostConstruct
    public void init() {
        // jdbcTemplate.update方法也可
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS users ("
                + "id BIGINT IDENTITY NOT NULL PRIMARY KEY, "
                + "email VARCHAR(100) NOT NULL, "
                + "password VARCHAR(100) NOT NULL, "
                + "name VARCHAR(100) NOT NULL, "
                + "UNIQUE (email))");
    }
}
