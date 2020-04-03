package com.str.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        // 创建一个固定大小的线程池
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 6; i++) {
            pool.submit(new Task("任务 " + i));
        }
        // 关闭线程池
        pool.shutdown();
    }
}

class Task implements Runnable {
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("start task " + name);

        // 一个 handler
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }

        System.out.println("end task " + name);
    }
}

