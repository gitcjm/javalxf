package com.str.designpatterns.adapter;

import java.util.concurrent.Callable;

public class RunnableAdapter implements Runnable {
    // 引用待转换的接口
    private Callable<?> callable;

    public RunnableAdapter(Callable<?> callable) {
        this.callable = callable;
    }

    // 实现指定接口
    @Override
    public void run() {
        // 将指定接口调用，委托给转换接口调用
        try {
            callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
