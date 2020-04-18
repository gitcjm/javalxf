package com.str.rafactoring.beginning;

/**
 * 专门把计费方法抽出来
 */

abstract class Price {
    abstract int getPriceCode();
    abstract double getCharge(int daysRented);
    // 影片积分
    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}

// 普通影片
class RegularPrice extends Price {

    int getPriceCode() {
        return Movie.REGULAR;
    }

    public double getCharge(int daysRented) {
        double result = 2;
        if (daysRented > 2)
            result += (daysRented - 2) *1.5;
        return result;
    }
}

// 新影片
class NewReleasePrice extends Price {

    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    public double getCharge(int daysRented) {
       return daysRented * 3;
    }

    public int getFrequentRenterPoints(int daysRented) {
        return (daysRented > 1) ? 2 : 1;
    }
}

// 儿童影片
class ChildrensPrice extends Price {
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    public double getCharge(int daysRented) {
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }
}
