package com.str.entity;

import javax.persistence.*;

@Entity
@Table(name="t_comment")
public class Comment extends AbstractEntity {

    // 通过@JoinColumn注解, 将该属性持久化为外键
    private Book book;    // FK
    private User user;    // FK
    private Integer rating;     // 评分
    private String content;     // 评论内容

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Column
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
