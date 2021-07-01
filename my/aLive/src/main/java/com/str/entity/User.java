package com.str.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="t_user")
public class User extends AbstractEntity {
    // ! 使用Hibernate时, 不要使用基本类型的属性, 总是使用包装类型, 如Long或Integer.
    private String email;
    private String password;
    private String name;
    private String mobile;
    private String address;

    // 该属性不会被持久化
    private Set<Order> orders;
    private Set<FavoriteBook> favoriteBooks;

    @OneToMany(targetEntity = Order.class, mappedBy = "user")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @OneToMany(targetEntity = FavoriteBook.class, mappedBy = "user")
    public Set<FavoriteBook> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(Set<FavoriteBook> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

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

    @Column(nullable = false, length = 100)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(nullable = true)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, email=%s, name=%s, password=%s, mobile=%s," +
                             " address=%s, createdAt=%s, createdDateTime=%s]",
                getId(), getEmail(), getName(), getPassword(), getMobile(),
                getAddress(), getCreatedAt(), getCreatedDateTime());
    }

}
