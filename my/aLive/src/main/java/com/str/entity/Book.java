package com.str.entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name="t_book")
public class Book extends AbstractEntity {
    // ! 使用Hibernate时, 不要使用基本类型的属性, 总是使用包装类型, 如Long或Integer.
    public static final int STATE_SELLING = 0;
    public static final int STATE_COMING_SOON = 1;
    public static final int STATE_SHUTDOWN = 2;
    public static final String[] STATES = {
            "正常销售", "即将上市", "停售"
    };

    private String name;
    private String originalName;
    private Integer categoryCode = Category.ROOT_CODE;

    private String author;
    private String publisher;
    private String language;

    private Float price;
    private Integer discount; // 1-100, default 100(no discount)
    private String isbn;
    private Long pubDate;
    private String description;

    private Integer state;  // 状态
    private Integer stock;  // 库存
    private Integer sold;   // 已出售

    private Integer rating; // 用户对书籍评价的总分
    private Integer ratingCount;    // 用户对书籍评价的人次

    /*private List<Comment> comments;  // 本书的所有评论
    private List<OrderItem> orderItems;*/

    @Column(nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 100)
    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    @Column(nullable = false, columnDefinition = "Integer default 0x01000000")
    public Integer getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Column(nullable = false, length = 50)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(length = 100)
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Column(length = 100)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(nullable = false)
    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Column(nullable = false, columnDefinition = "Integer default 100")
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Column(nullable = false, unique = true)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column
    public Long getPubDate() {
        return pubDate;
    }

    public void setPubDate(Long pubDate) {
        this.pubDate = pubDate;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Column
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Column
    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    @Column
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Column
    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    /*// @OneToMany注解不会将该属性持久化, @ManyToOne注解会持久化
    @OneToMany(targetEntity = Comment.class, mappedBy = "book")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(targetEntity = OrderItem.class, mappedBy = "book")
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }*/

    @Transient
    public String getPubDateTime() {
        return Instant.ofEpochMilli(this.pubDate).atZone(ZoneId.systemDefault())
                .format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Transient
    public int getRatingLevel() {
        if (ratingCount == 0) {
            return 0;
        }
        return (int) (10*((float)rating/ratingCount+0.05f)*2)/10;
    }

    @Transient
    public String getStateAsString() {
        return STATES[state];
    }

    @Transient
    public String getImage() {
        int hash = this.getId().hashCode();
        int dir1 = (hash & 0xF0) >>> 4;
        int dir2 = hash & 0xF;
        return dir1 + "/" + dir2 + "/" + this.getId() + ".jpg";
    }



}
