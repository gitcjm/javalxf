package com.str.spring_dao.entity;

import javax.persistence.Transient;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Book {
    private long id;
    private String title;
    private String writer;
    private long createAt;

    public Book() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    // “虚拟”的属性
    @Transient
    public ZonedDateTime getCreateDataTime() {
        return Instant.ofEpochMilli(getCreateAt()).atZone(ZoneId.systemDefault());
    }

    public String toString() {
        return id + "\t" + title + "\t" + writer + "\t" + getCreateDataTime();
    }
}
