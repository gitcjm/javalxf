package com.str.spring_hib.entity;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@MappedSuperclass
public abstract class AbstractEntity {
    private Long id;
    private Long createAt;

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
    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    // “虚拟”的属性
    @Transient
    public ZonedDateTime getCreateDataTime() {
        return Instant.ofEpochMilli(this.createAt).atZone(ZoneId.systemDefault());
    }

    // 表示将一个JavaBean持久化到数据库之前（即执行INSERT语句之前），
    // Hibernate会先执行该方法，这样，我们就可以自动设置好createAt属性
    @PrePersist
    public void preInsert() {
        setCreateAt(System.currentTimeMillis());
    }
}
