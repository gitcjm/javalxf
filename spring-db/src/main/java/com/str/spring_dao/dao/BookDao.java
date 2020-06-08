package com.str.spring_dao.dao;

import com.str.spring_dao.entity.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class BookDao extends AbstractDao<Book> {

    public void insert(String title, String writer) {
        long createAt = System.currentTimeMillis();
        if (1 != super.getJdbcTemplate().update(
                "INSERT INTO book (title, writer, createAt) " +
                        "VALUES (?,?," + createAt + ")",
                title, writer)) {
            throw new RuntimeException("书籍添加失败");
        }
    }

    public void update(String writer, long id) {
        super.getJdbcTemplate().update(
                "UPDATE book SET writer = ? WHERE id = ?",
                writer, id
        );
    }
}
