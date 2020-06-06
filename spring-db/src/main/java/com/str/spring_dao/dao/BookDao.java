package com.str.spring_dao.dao;

import com.str.spring_dao.entity.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BookDao extends AbstractDao<Book> {
    /*public void insert(String title, String writer) {
        long createAt = System.currentTimeMillis();
        super.getJdbcTemplate().update(
                "INSERT INTO book (title, writer, createAt) " +
                        "VALUES (?,?," + createAt + ")",
                title, writer
        );
    }*/
}
