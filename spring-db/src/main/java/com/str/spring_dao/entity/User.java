package com.str.spring_dao.entity;

import javax.persistence.Transient;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class User {
    private long id;
    private String email;
    private String password;
    private String name;
    private long createAt;

    public User() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return id + "\t" + name + "\t" + password + "\t\t" + email + "\t" + getCreateDataTime();
    }
}
