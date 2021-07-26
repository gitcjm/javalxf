package com.str.entity;

import javax.persistence.*;

/**
 * 先用廖雪峰的代码实现一次，然后用最新版的Hibernate简化代码
 * 比如：
 * （1）把OrderItem域模型改造为Item，加上@Embeddable注解，然后去掉order属性
 * （2）在Order模型中，将List<OrderItem> orderItems修改为相应的List<Item> items后，加上@ElementCollection注解
 * （3）新版的Hibernate框架会自动创建一个order_items表
 * 注意：
 * 以上全错了，也不能说错了。
 * 使用单向包时，可以据上简化；使用双向包时，OrderItems域中还是需要order属性的！
 * */
@Entity
@Table(name="t_orderitem")
public class OrderItem extends AbstractEntity {
    // 不用担心Order,Book会被持久化为该表的字段,
    // 因为使用了@JoinColumn注解,它会将该字段持久化为外键(默认字段名为"order_id, book_id")
    private Order order;    // FK
    private Book book;      // FK
    private Integer number; // How many books

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, updatable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Column(nullable = false)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
