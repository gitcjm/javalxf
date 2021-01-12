package com.str.spring_dao.service;

import com.str.spring_dao.dao.BookDao;
import com.str.spring_dao.entity.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class BookService {
    private BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public Book getBookById(long id) {
        return bookDao.getById(id);
    }

    public List<Book> getBooks(int pageIndex) {
        return bookDao.getAll(pageIndex);
    }

    public void insertBook(String title, String writer) {
        bookDao.insert(title, writer);
    }

    public void updateBook(String writer, long id) {
        bookDao.update(writer, id);
    }

    public void deleteBookById(long id) {
        bookDao.deleteById(id);
    }

}
