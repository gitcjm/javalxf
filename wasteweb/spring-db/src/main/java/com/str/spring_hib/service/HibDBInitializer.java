package com.str.spring_hib.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HibDBInitializer {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hib_user ("
                + "id BIGINT IDENTITY NOT NULL PRIMARY KEY, "
                + "email VARCHAR(100) NOT NULL, "
                + "password VARCHAR(100) NOT NULL, "
                + "name VARCHAR(100) NOT NULL, "
                + "createAt BIGINT NOT NULL, "
                + "UNIQUE (email))");

        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS hib_book ("
                + "id BIGINT IDENTITY NOT NULL PRIMARY KEY, "
                + "title VARCHAR(100) NOT NULL, "
                + "createAt BIGINT NOT NULL, "
                + "UNIQUE (title))");
    }

}
