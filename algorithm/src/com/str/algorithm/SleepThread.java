package com.str.algorithm;

public class SleepThread extends Thread {
    private int ms = 0;

    public SleepThread(int ms) {
        this.ms = ms;
    }

    public void run() {
        try {
            sleep(ms * 10 + 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(ms);
    }
}
