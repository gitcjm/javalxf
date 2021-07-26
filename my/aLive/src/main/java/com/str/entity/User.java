package com.str.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="t_user")
public class User extends AbstractEntity {
    // ! 使用Hibernate时,不要使用基本类型的属性,总是使用包装类型,如Long或Integer.
    private String email;
    private String password;
    private String name;
    private String mobile;
    private String address;
    // 该属性不会被持久化
    /*private List<Order> orders;
    private List<FavoriteBook> favoriteBooks;
    private List<Comment> comments;*/

    @Column(updatable = false, nullable = false, unique = true, length = 100)
    @Email(message = "无效的Email地址（示例：abc@example.com）")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false, length = 32)
    @Pattern(regexp = "[0-9a-f]{32}", message = "无效的口令")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false, length = 20)
    @Length(max = 10, message = "名字长度限制在10个字以内")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, length = 100)
    @Pattern(regexp = "[0-9]{11,17}", message = "手机号码有误")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(nullable = true, length = 200)
    @Length(max=100, message = "地址长度限制在100字以内")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*@OneToMany(targetEntity = Order.class, mappedBy = "user")
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @OneToMany(targetEntity = FavoriteBook.class, mappedBy = "user")
    public List<FavoriteBook> getFavoriteBooks() {
        return favoriteBooks;
    }

    public void setFavoriteBooks(List<FavoriteBook> favoriteBooks) {
        this.favoriteBooks = favoriteBooks;
    }

    @OneToMany(targetEntity = Comment.class, mappedBy = "user")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
*/
    @Override
    public String toString() {
        return String.format("User[id=%s, email=%s, name=%s, password=%s, mobile=%s," +
                             " address=%s, createdAt=%s, createdDateTime=%s]",
                getId(), getEmail(), getName(), getPassword(), getMobile(),
                getAddress(), getCreatedAt(), getCreatedDateTime());
    }

}
