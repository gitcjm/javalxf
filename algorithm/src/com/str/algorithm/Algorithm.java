package com.str.algorithm;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Algorithm {
    // 在一个数组中，除一个数字只出现了1次，其他都出现了2次，请找出这一个数字
    public static int findOneAlone(int[] a) {
        int lost = 0;
        for (int i = 0; i < a.length; i++) {
            lost ^= a[i];
        }
        return lost;
    }

    // 在一个整数数组中，有两个数只出现了1次，其他都出现了2次，请找出这两个数
    public static int[] findTwoAlone(int[] a) {
        int temp = 0;
        int i = 0, j = 0;

        // 计算这两个数的异或结果
        for (i = 0; i < a.length; i++) {
            temp ^= a[i];
        }

        // 在异或结果中，找出第一个为1的位置（由低向高）
        for (j = 0; j < Integer.SIZE; j++) {
            if (((temp >> j) & 1) == 1)
                break;
        }

        // 据j位的0/1值，将数组 a 分成两个数组。
        // 如此，这两个落单数，必然会被分在不同的数组中。
        // 这两个数组的长度不一定相等
        int[] p = new int[a.length];    // java的int数组初始化为0，所以，异或时不影响结果
        int[] q = new int[a.length];    // 数组长一点就长一点吧，没有方便的动态数组，只好如此
        int m = 0, n = 0;
        for (i = 0; i < a.length; i++) {
            if (((a[i] >> j) & 1) == 1) {
                p[m++] = a[i];
            } else {
                q[n++] = a[i];
            }
        }

        // 分别在两个数组中找出落单的数
        int[] r = new int[2];
        r[0] = findOneAlone(p);
        r[1] = findOneAlone(q);

        return r;
    }
}
