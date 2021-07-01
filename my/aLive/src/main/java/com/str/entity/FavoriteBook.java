package com.str.entity;

import javax.persistence.*;

@Entity
@Table(name="t_favoritebook")
public class FavoriteBook extends AbstractEntity {

    private User user;    // FK
    private Book book;    // FK

    // @JoinColumn专门用来持久化为外键,默认字段名为"表名_主键"(如: account_id)
    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
