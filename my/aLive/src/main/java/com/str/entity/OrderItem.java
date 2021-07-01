package com.str.entity;

import javax.persistence.*;

@Entity
@Table(name="t_orderitem")
public class OrderItem extends AbstractEntity {
    // 不用担心Order,Book会被持久化为该表的字段,
    // 因为使用了@JoinColumn注解,它会将该字段持久化为外键(默认字段名为"order_id, book_id")
    private Order order;    // FK
    private Book book;      // FK
    private Integer number;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
