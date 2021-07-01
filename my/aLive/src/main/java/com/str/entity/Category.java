package com.str.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="t_category")
public class Category extends AbstractEntity {
    // ! 使用Hibernate时, 不要使用基本类型的属性, 总是使用包装类型, 如Long或Integer.

    public static final Integer ROOT_ID = 0x01000000;
    private static final Integer[] MASK = {
            0xFF000000,
            0xFFFF0000,
            0xFFFFFF00,
            0xFFFFFFFF
    };

    private String name;
    private Integer categoryOrder;

    // 除了 name和categoryOrder是直接映射到数据库表外，mask、level、parentId和children都是在初始化时设定的
    private Integer mask;
    private Integer level;
    private Integer parentId = 0;
    private List<Category> children = new ArrayList<Category>();


    @Column(nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public Integer getCategoryOrder() {
        return categoryOrder;
    }

    public void setCategoryOrder(Integer categoryOrder) {
        this.categoryOrder = categoryOrder;
    }
}
