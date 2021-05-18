package com.str.service;

import com.str.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import java.sql.Statement;
import java.util.List;

@Component
public class BookService implements EntityService<Book> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<Book> bookRowMapper = new BeanPropertyRowMapper<>(Book.class);

    @Override
    public Book getEntityById(long id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { id }, bookRowMapper);
    }

    @Override
    public List<Book> getEntityList(int pageIndex, int pageSize) {
        int offset = pageSize * (pageIndex - 1);
        String sql = "SELECT * FROM book LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new Object[] { pageSize, offset }, bookRowMapper);
    }

    public Book getBookByTitle(String title) {
        String sql = "SELECT * FROM book WHERE title = ?";
        return jdbcTemplate.queryForObject(sql, new Object[] { title }, bookRowMapper);
    }

    public Book addBook(String title, String author, String intro) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIntro(intro);
        book.setCreatedAt(System.currentTimeMillis());

        String sql = "INSERT INTO book (title, author, intro, createdAt) VALUE (?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        if (1 != jdbcTemplate.update(connection -> {
            var ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, book.getTitle());
            ps.setObject(2, book.getAuthor());
            ps.setObject(3, book.getIntro());
            ps.setObject(4, book.getCreatedAt());
            return ps;
        }, holder)) {
            throw new RuntimeException("Insert failed.");
        }
        book.setId(holder.getKey().longValue());
        return book;
    }

}
