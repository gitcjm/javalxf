package com.str.algorithm;

public class Algorithm {
    // 在一个数组中，除一个数字只出现了1次，其他都出现了2次，请找出这一个数字
    public static int findOne(int[] a) {
        int lost = 0;
        for (int i = 0; i < a.length; i++) {
            lost ^= a[i];
        }
        return lost;
    }

    // 在一个数组中，有两个数字只出现了1次，其他都出现了2次，请找出这两个数字
    public static void findTwo(int[] a) {
        int temp = 0;

        // 计算这两个数的异或结果
        for (int i = 0; i < a.length; i++) {
            temp ^= a[i];
        }

        // 找出第一个为1的位
        for (int j = 0; j < Integer.SIZE; j++) {
            if (((temp >> j) & 1) == 1)
                break;
        }


    }
}
