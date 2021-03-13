package com.str.service;

import com.str.AppConfig;
import com.str.entity.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class BookServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    BookService bookService = context.getBean(BookService.class);

    @Test
    public void addBook() {
        Book book = bookService.addBook("东西方哲学基础宗论", "王东岳");
        bookService.print(book);
    }

    @Test
    public void getBookById() {
        Book book = bookService.getBookById(Long.valueOf(1));
        if (book != null) {
            bookService.print(book);
        }
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(Long.valueOf(3));
    }
}