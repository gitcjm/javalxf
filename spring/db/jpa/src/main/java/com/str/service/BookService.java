package com.str.service;

import com.str.entity.Book;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Component
@Transactional
public class BookService {
    @PersistenceContext
    EntityManager em;

    public void print(Book book) {
        System.out.println(book.getId() + "\t" + book.getTitle() + "\t" + book.getAuthor() + "\t" + book.getCreatedAt());
    }

    public Book addBook(String title, String author) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        em.persist(book);
        System.out.println(book.getId());
        return book;
    }

    public Book getBookById(Long id) {
        Book book = this.em.find(Book.class, id);
        if (book == null) {
            throw new RuntimeException("Book not found by id: " + id);
        }
        return book;
    }

    public void deleteBookById(Long id) {
        Book book = this.em.find(Book.class, id);
        if (book == null) {
            throw new RuntimeException("Book not found by id: " + id);
        }
        this.em.remove(book);
    }
}
