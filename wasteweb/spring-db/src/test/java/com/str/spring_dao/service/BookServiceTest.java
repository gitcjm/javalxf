package com.str.spring_dao.service;

import com.str.spring_dao.AppConfig;
import com.str.spring_dao.entity.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class BookServiceTest {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    BookService bookService = context.getBean(BookService.class);

    @Test
    public void getBookById() {
        System.out.println(bookService.getBookById(1).toString());
    }

    @Test
    public void insertBook() {
        //bookService.insertBook("古炉", "贾平凹");
        bookService.insertBook("动物庄园","乔治.奥维尔");
    }

    @Test
    public void updateBook() {
        bookService.updateBook("斯塔夫里阿诺斯", 4);
    }

    @Test
    public void getBooks() {
        List<Book> books = bookService.getBooks(1);
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    @Test
    public void deleteBook() {
        bookService.deleteBookById(2);
    }
}