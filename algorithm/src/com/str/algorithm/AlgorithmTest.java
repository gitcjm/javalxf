package com.str.algorithm;

import java.util.Arrays;

public class AlgorithmTest {
    public static void main(String[] args) {
        int[] arr = {2, 3, 5, 3, 9, 2, 5};

        System.out.println("原数字对数组：" + Arrays.toString(arr));
        System.out.println("数字对数组中落单的数字是：" +
                Algorithm.findOne(arr));
    }
}
