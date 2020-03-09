package com.str.algorithm;

import java.util.Random;

import static java.lang.Math.abs;

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

        // 第j位为1,说明这两个数字在第j位上是不相同的
        // 由此分组即可。直接异或出结果，而不需要分开的两个数组
        int[] r = new int[2];
        for (i = 0; i < a.length; i++) {
            if (((a[i] >> j) & 1) == 1) {
                r[0] ^= a[i];
            } else {
                r[1] ^= a[i];
            }
        }

        return r;

        // 犯了以下的错误，说明我对位运算还是不熟悉啊！
        // 倒是可以把分开的数组打出来看看！
        /*
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
        /*
        // 分别在两个数组中找出落单的数
        int[] r = new int[2];
        r[0] = findOneAlone(p);
        r[1] = findOneAlone(q);*/
    }

    // 在数组A中，除了x出现了一次，其他都出现了三次，请快速找出这个x
    public static int findAloneInTriplets(int[] a) {
        // bits为x的二进制码
        int[] bits = new int[Integer.SIZE];
        int c = 0;

        // 如果没有x的话，数组中的每个数都出现3次，在二进制上，每位上的1的个数都能被3整除
        // 那么，有了x后，每位上1的个数除以3，余数就是x的二进制位值
        for (int i = 0; i < Integer.SIZE; i++) {
            for (int j = 0; j < a.length; j++) {
                c += (a[j] >> i) & 1;
            }
            bits[i] = c % 3 ;
            c = 0;
        }

        // 据二进制码数组，生成x的二进制码
        int x = 0;
        for (int i = Integer.SIZE-1; i >= 0; i--) {
            x = (x << 1) | bits[i] ;
        }

        return x;
        //return bits; // 看看x的二进制码
    }

    // 数组A，相邻元素差的绝对值为1。现给定一个数x，请找出x在数组A中的位置
    public static int findPosInArray(int[] a, int x) {
        int len = a.length;
        int dif = Math.abs(x - a[0]);

        while (dif < len) {
            if (a[dif] == x)
                return dif;
            dif += Math.abs(x - a[dif]);
        }

        return -1;
    }

    // 数组A，长度为n，其元素取值[0..n-1]（可以重复）
    // 问题：查找第一个出现重复的数
    public static int findRepeatedElement(int[] a) {
        int len = a.length;
        int t = 0;

        for (int i = 0; i < len; i++) {
            while (a[i] != i) {
                if (a[i] == a[a[i]]) return a[i];
                // 交换
                t = a[a[i]];
                a[a[i]] = a[i];
                a[i] = t;
            }
        }

        return -1;
    }

    // 寻找一个数组中的逆序数对
    public static int findReverseNumPair(int[] a) {
        int count = 0;
        int len = a.length;

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (a[i] > a[j]) count++;
            }
        }

        return count;
    }

    // 寻找一个数组中的逆序数对（合并数列法）

    // 随机生成和为S的N个自然数（
    // 原为N个正整数，但0不好避免，再者，当N大于S时，必然有0，故改为自然数）
    public static int[] genRandomArray(int S, int N) {
        // 生成小于S的N个随机正整数数组
        int[] randArray = new int[N];
        Random random = new Random();
        for (int i = 0; i < N-1; i++) {
            randArray[i] = random.nextInt(S - 1) + 1;
        }
        randArray[N-1] = S;

        // 如果要求返回正整数数组，就得对 randArray 排序
        com.str.sort.SortUtil.quickSort(randArray,0, N-1);

        // 生成N个差值数组，其和为S
        int[] difArray = new int[N];
        for (int i = 0; i < N-1; i++) {
            difArray[i+1] = randArray[i+1] - randArray[i];
        }
        difArray[0] = randArray[0];

        return difArray;
    }
}
