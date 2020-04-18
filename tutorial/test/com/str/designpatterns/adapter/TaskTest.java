package com.str.designpatterns.adapter;

import org.junit.Test;

import java.util.concurrent.Callable;

import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void callTest() {
        Callable<Long> callable = new Task(10);
        Thread thread = new Thread(new RunnableAdapter(callable));
        thread.start();
    }
}