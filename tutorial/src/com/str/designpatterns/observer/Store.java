package com.str.designpatterns.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
    private List<ProductObserver> observers = new ArrayList<>();
    private Map<String, Product> products = new HashMap<>();

    // 注册观察者
    public void addObserver(ProductObserver observer) {
        this.observers.add(observer);
    }

    // 注销观察者
    public void removeObserver(ProductObserver observer) {
        this.observers.remove(observer);
    }

    // 添加产品
    public void addNewProduct(String name, double price) {
        Product product = new Product(name, price);
        products.put(product.getName(), product);
        // 通知观察者
        for (ProductObserver observer : observers) {
            observer.onPublished(product);
        }
    }

    // 更新价格
    public void updateProductPrice(String name, double price) {
        Product product = products.get(name);
        product.setPrice(price);
        // 通知观察者
        for (ProductObserver observer : observers) {
            observer.onPriceChanged(product);
        }
    }
}
