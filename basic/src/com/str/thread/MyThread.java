package com.str.thread;

import com.str.algorithm.Algorithm;

import java.time.LocalTime;
import java.util.Arrays;

public class MyThread {

    // 方法一：从Thread派生一个类，覆盖run()方法
    class ThreadA extends Thread {
        private int n;

        public ThreadA(int n) {
            this.n = n;
        }

        @Override
        public void run() {
            System.out.println(Arrays.toString(Algorithm.fibonacci(n)));
        }
    }

    public void startThreadA() throws InterruptedException {
        int[] a = {5,8,15,22};

        for (int i = 0; i < a.length; i++) {
            Thread t = new ThreadA(a[i]);
            t.start();
            t.join();
        }
    }

    // 方法二：实例化Runnable接口
    class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("start new thread use Runnable");
        }
    }

    public void startMyRunnable () throws InterruptedException {
        Thread t = new Thread(new MyRunnable());
        t.start();
        t.join();
    }

    // 中断线程
    class InterruptThread extends Thread {

        @Override
        public void run() {
            int n = 0;
            while (! isInterrupted()) {
                n++;
                System.out.print(n + ", ");
            }
        }
    }

    public void startInterruptThread() throws InterruptedException {
        Thread t = new InterruptThread();
        t.start();
        Thread.sleep(1);    // 暂停1毫秒
        t.interrupt();  // 中断t线程
        t.join();   // 等待t线程结束
    }

    // 守护线程
    class TimerThead extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println(LocalTime.now());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    class DaemonThread extends Thread {
        @Override
        public void run() {
            Thread t = new TimerThead();
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startTimerThread() {
        DaemonThread dt = new DaemonThread();
        dt.setDaemon(true);
        dt.start();
        try {
            dt.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // 睡眠排序
    class SortThread extends Thread {

        private int ms = 0;

        public SortThread(int ms) {
            this.ms = ms;
        }

        @Override
        public void run() {
            try {
                sleep(ms * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(ms + ", ");
        }
    }

    public void startSleepSort() {
        int[] a = {4,2,6,5,3,1};

        SortThread[] st = new SortThread[a.length];
        for (int i = 0; i < a.length; i++) {
            st[i] = new SortThread(a[i]);
            st[i].start();
        }

        // 等待两秒，让上面的几个线程结束
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
