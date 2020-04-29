package com.str.designpatterns.observer;

public class Product {
    String name;
    double price;

    public Product (String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
