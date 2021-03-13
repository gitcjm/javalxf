package com.str.entity;

import javax.persistence.*;

@NamedQueries(
        @NamedQuery(
              name = "login",
              query = "select u from User u where u.email=:e and u.password=:p"
        )
)
@Entity
@Table(name="user")
public class User extends AbstractEntity {
    private String email;
    private String password;
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
