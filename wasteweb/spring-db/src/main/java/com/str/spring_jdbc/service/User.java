package com.str.spring_jdbc.service;

public class User {
    private long id;
    private String email;
    private String password;
    private String name;

    // 调用getUsers()方法时，spring弹出“No default constructor found”异常，故，添加一个默认构造方法
    public User() {}

    public User(long id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

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

    public String toString() {
        return id + "\t" + name + "\t" + password + "\t\t" + email;
    }

}
