package com.str.rafactoring.beginning;

public class Movie {
    // movie price code
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDRENS = 2;

    private  String title;
    private Price price;

    public Movie(String title, int priceCode) {
        this.title = title;
        setPriceCode(priceCode);
    }

    public String getTitle() {
        return title;
    }

    public int getPriceCode() {
        return price.getPriceCode();
    }

    // 设置影片类型
    private void setPriceCode(int arg) {
        switch (arg) {
            case REGULAR:
                this.price = new RegularPrice();
                break;
            case NEW_RELEASE:
                this.price = new NewReleasePrice();
                break;
            case CHILDRENS:
                this.price = new ChildrensPrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    // 影片租金
    public double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

    // 影片积分
    public int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }
}

