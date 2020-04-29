package com.str.designpatterns.observer;

import org.junit.Test;

public class StoreTest {
    // 货架
    Store store = new Store();
    // 观察者
    ProductObserver obs_duo = new ProductObserver(Observer.create("小多", Observer.CUSTOMER));
    ProductObserver obs_bei = new ProductObserver(Observer.create("小贝", Observer.CUSTOMER));
    ProductObserver obs_dan = new ProductObserver(Observer.create("蛋蛋", Observer.ADMIN));

    @Test
    public void handleProduct() {
        store.addObserver(obs_duo);
        store.addNewProduct("apple", 5.00);
        store.addObserver(obs_dan);
        store.addNewProduct("orange", 9.00);
        store.updateProductPrice("apple", 5.15);
        store.removeObserver(obs_dan);
        store.addObserver(obs_bei);
        store.updateProductPrice("orange", 8.50);
        store.addNewProduct("banana", 3.50);
    }

}