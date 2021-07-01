package com.str.service;


import com.str.entity.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BookService extends AbstractService {

    public Book getBookById(Long id) {
        return hibernateTemplate.get(Book.class, id);
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        if (book == null) {
            throw new RuntimeException("Delete failed.");
        }
        hibernateTemplate.delete(book);
    }

    public void updateBook(Book book) {
        Book book1 = getBookById(book.getId());
        if (book1 == null) {
            throw new RuntimeException("Update failed.");
        }
        hibernateTemplate.update(book);
    }
}
