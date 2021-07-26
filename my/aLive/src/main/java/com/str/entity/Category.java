package com.str.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个分类挺有意思，还需好好研究
 * */
@Entity
@Table(name="t_category")
public class Category extends AbstractEntity {
    // ! 使用Hibernate时, 不要使用基本类型的属性, 总是使用包装类型, 如Long或Integer.

    public static final Integer ROOT_CODE = 0x01000000;
    private static final Integer[] MASK = {
            0xFF000000,
            0xFFFF0000,
            0xFFFFFF00,
            0xFFFFFFFF
    };

    private Integer code;
    private String name;
    private Integer categoryOrder;

    // 除了 name和categoryOrder是直接映射到数据库外，mask、level、parentId和children都是在初始化时设定的
    private Integer mask;
    private Integer level;
    private Integer parentCode = 0;

    private List<Category> children = new ArrayList<Category>();

    // 书籍分类编码
    public Integer getCode() {
        return code;
    }

    // set object's code also set mask & parent's code
    public void setCode(Integer code) {
        this.code = code;
        for (int i = 0; i < MASK.length; i++) {
            if ((MASK[i] & code.intValue()) == code.intValue()) {
                this.mask = MASK[i];
                this.level = i;
                if (i > 0) {
                    this.parentCode = code.intValue() & MASK[i-1];
                }
                break;
            }
        }
    }

    @Column(nullable = false, length = 20)
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

    @Transient
    public int getParentCode() {
        return parentCode;
    }

    @Transient
    public int getLevel() {
        return level;
    }

    @Transient
    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    @Transient
    public int getMask() {
        return mask;
    }

    @Transient
    public boolean isRoot() {
        return level == 0;
    }

    @Transient
    public boolean isLeaf() {
        return level == 3;
    }

    // 递归法添加子类型
    public synchronized boolean add(Category child) {
        if (this.level >= child.level) {
            return false;
        }
        if ((child.level - this.level) == 1) {
            if ((child.code.intValue() & mask) == this.code.intValue()) {
                // TODO: logger.info
                this.children.add(child);
                return true;
            }
            return false;
        }
        for (Category c : children) {
            if (c.add(child))
                return true;
        }
        return false;
    }

    // 递归法删除子类型
    public synchronized boolean remove(Category child) {
        for (Category c : children) {
            if (c.getCode().intValue() == child.getCode().intValue()) {
                children.remove(c);
                return true;
            }
            if (c.remove(child)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "[" + Integer.toHexString(code.intValue()) + ", " + level + "] " + name;
    }

    public void debug() {
        String s = "";
        for (int i = 0; i < this.getLevel(); i++) {
            s = s + " ";
        }
        // TODO: Logger.info
        for (Category c : children) {
            c.debug();
        }
    }
}
