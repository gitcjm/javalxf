package com.str.spring_hib.entity;

import javax.persistence.*;

@Entity
@Table(name="hib_book")
public class HibBook extends AbstractEntity {

    private String title;

    @Column(nullable = false, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
