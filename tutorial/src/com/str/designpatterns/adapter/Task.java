package com.str.designpatterns.adapter;

import java.util.concurrent.Callable;

public class Task implements Callable<Long> {
    private long num;

    public Task(long num) {
        this.num = num;
    }

    @Override
    public Long call() throws Exception {
        long result = 0;
        for (long n = 1; n <= this.num; n++) {
            result += n;
        }
        System.out.println(result);
        return result;
    }

}
