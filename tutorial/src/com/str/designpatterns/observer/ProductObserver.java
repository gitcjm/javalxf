package com.str.designpatterns.observer;

public class ProductObserver {

    private Observer observer;

    public ProductObserver(Observer observer) {
        this.observer = observer;
    }

    public void onPublished(Product product) {
        System.out.println("[" + observer.getName() + "] product published: " +
                product.getName());
    }

    public void onPriceChanged(Product product) {
        System.out.println("[" + observer.getName() + "] price changed: " +
                product.getName());
    }

}
