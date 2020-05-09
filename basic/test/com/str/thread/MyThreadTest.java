package com.str.thread;

import org.junit.Before;
import org.junit.Test;

public class MyThreadTest {

    private MyThread mt;

    @Before
    public void setUp() throws Exception {
        mt = new MyThread();
    }

    @Test
    public void startThreadA() throws InterruptedException {
        mt.startThreadA();
    }

    @Test
    public void startMyRunnable() throws InterruptedException {
        System.out.println("main start");
        mt.startMyRunnable();
        System.out.println("main end");
    }

    @Test
    public void startInterruptThread() throws InterruptedException {
        mt.startInterruptThread();
        System.out.println("main end.");
    }

    @Test
    public void startTimerThread() {
        mt.startTimerThread();
        System.out.println("main end.");
    }

    @Test
    public void sleepSort() {
        mt.startSleepSort();
    }

}