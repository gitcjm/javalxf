package com.str.entity;

public class Book extends AbstractEntity {
    private String title;
    private String author;
    private String intro;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }



    @Override
    public String toString() {
        return String.format("Book[id=%s, title=%s, author=%s, intro=%s, createdAt=%s, createdDateTime=%s]",
                getId(), getTitle(), getAuthor(), getIntro(), getCreatedAt(), getCreatedDateTime());
    }
}
