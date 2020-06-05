package com.str.spring_hib.entity;

import com.str.spring_hib.entity.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name="hib_user")
public class HibUser extends AbstractEntity {

    private String email;
    private String password;
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    public String getEmail() {
        return email;
    }

    @Column(nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    @Column(nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return super.getId() + "\t" + name + "\t" + password + "\t\t" + email; // + "\t" + super.getCreateDataTime();
    }
}
