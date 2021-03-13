package com.str.entity;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public abstract class AbstractEntity {
    private Long id;
    private Long createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createAt) {
        this.createdAt = createAt;
    }

    // @Transient, @PrePersist, @id, @Column等是JPA注解. mybatis不使用它们，也就是说，mybatis不是符合JPA规范的ORM框架
    // @Transient
    public ZonedDateTime getCreatedDateTime() {
        return Instant.ofEpochMilli(this.createdAt).atZone(ZoneId.systemDefault());
    }

    /*// @PrePersist表示：在我们将一个JavaBean持久化到数据库之前（即执行INSERT语句），
    // Hibernate会先执行该方法，这样我们就可以自动设置好createAt属性
    @PrePersist
    public void preInsert() {
        setCreatedAt(System.currentTimeMillis());
    }*/

}
