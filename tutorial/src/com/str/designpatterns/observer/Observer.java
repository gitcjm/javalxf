package com.str.designpatterns.observer;

/**
 * Replace Constructor with Factory Method
 * 以工厂方法取代构造函数
 */
public class Observer {
    private String name;

    private int type;
    static final int CUSTOMER = 0;
    static final int ADMIN = 1;

    private Observer(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public static Observer create(String name, int type) {
        return new Observer(name, type);
    }

    public String getName() {
        return name;
    }

}

