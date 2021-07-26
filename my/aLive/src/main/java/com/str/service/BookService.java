package com.str.service;


import com.str.entity.Book;
import com.str.util.MyDate;
import com.str.util.Page;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class BookService extends AbstractService<Book> {

    public Book getBookById(long id) {
        return getById(id);
    }

    public Book newBook(String name, String originalName, int categoryCode, String author,
                        String publisher, String language, float price, int discount,
                        String isbn, String pubDate, int state, int stock, int sold,
                        int rating, int ratingCount, String description) {
        Book book = new Book();
        Book book1 = setAttributes(book, name, originalName, categoryCode, author, publisher, language,
                price, discount, isbn, pubDate, state, stock, sold, rating, ratingCount, description);
        return create(book1);
    }

    // 从表单获取实体属性
    private Book setAttributes(Book book, String name, String originalName, int categoryCode, String author,
                               String publisher, String language, float price, int discount,
                               String isbn, String pubDate, int state, int stock, int sold,
                               int rating, int ratingCount, String description) {
        book.setName(name);
        book.setOriginalName(originalName);
        book.setCategoryCode(categoryCode);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setLanguage(language);
        book.setPrice(price);
        book.setDiscount(discount);
        book.setIsbn(isbn);
        book.setPubDate(MyDate.strDate2Long(pubDate, "yyyy-M-d"));
        book.setState(state);
        book.setStock(stock);
        book.setSold(sold);
        book.setRating(rating);
        book.setRatingCount(ratingCount);
        book.setDescription(description);
        return book;
    }

    public List<Book> getBookList(long createdAt, Page page) {
        String selectCount = "select count(*) from Book b where b.createdAt > ?0";
        String select = "select b from Book b where b.createdAt > ?0 order by b.createdAt DESC";
        Long[] values = { createdAt };
        return queryForListHasPages(selectCount, select, values, page);
    }

    // 传入非持久化对象
    public void updateBook(Book book, String name, String originalName, int categoryCode, String author,
                           String publisher, String language, float price, int discount,
                           String isbn, String pubDate, int state, int stock, int sold,
                           int rating, int ratingCount, String description) {
        Book book1 = setAttributes(book, name, originalName, categoryCode, author, publisher, language,
                price, discount, isbn, pubDate, state, stock, sold, rating, ratingCount, description);
        update(book1);
    }

    public void deleteBook(long id) {
        delete(id);
    }

}
