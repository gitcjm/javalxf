package com.str.algorithm;

import java.util.Arrays;

public class AlgorithmTest {
    public static void main(String[] args) {
        // 在成对整数数组中，查找一个落单的数
        /*int[] arr = {2, 3, 5, 3, 9, 2, 5};
        System.out.println("原数字对数组：" + Arrays.toString(arr));
        System.out.println("数字对数组中落单的数字是：" +
                Algorithm.findOneAlone(arr));*/

        // 在成对整数数组中，查找2个落单的数
        int[] arr2 = {1, 1, 2, 2, 32, 16, 3, 5, 3, 5};
        System.out.println("两个落单者：" + Arrays.toString(Algorithm.findTwoAlone(arr2)));
    }
}
