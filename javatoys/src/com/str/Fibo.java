package com.str;

public class Fibo {
    public static void main(String[] args) {
        fibo(1000);
    }

    private static void fibo(int n) {
        int a = 1, b = 1, t;
        while (a < n) {
            System.out.print(a + " ");
            t = a;
            a = b;
            b = t + b;
        }
    }
}
