package com.str.entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class AbstractEntity {
    private Long id;
    private Long createdAt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, updatable = false)
    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createAt) {
        this.createdAt = createAt;
    }

    @Transient
    public ZonedDateTime getCreatedDateTime() {
        return Instant.ofEpochMilli(this.createdAt).atZone(ZoneId.systemDefault());
    }

    // @PrePersist表示：在我们将一个JavaBean持久化到数据库之前（即执行INSERT语句），
    // Hibernate会先执行该方法，这样我们就可以自动设置好createAt属性
    @PrePersist
    public void preInsert() {
        setCreatedAt(System.currentTimeMillis());
    }

}
