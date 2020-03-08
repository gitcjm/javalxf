package com.str.algorithm;

import com.sun.org.apache.bcel.internal.generic.ALOAD;

import java.util.Arrays;

public class AlgorithmTestMy {
    public static void main(String[] args) {
        // 在成对整数数组中，查找一个落单的数
        /*int[] arr = {2, 3, 5, 3, 9, 2, 5};
        System.out.println("原数字对数组：" + Arrays.toString(arr));
        System.out.println("数字对数组中落单的数字是：" +
                Algorithm.findOneAlone(arr));*/

        // 在成对整数数组中，查找2个落单的数
        /*int[] arr2 = {1, 1, 2, 2, 8, 16, 3, 5, 3, 5};
        System.out.println("两个落单者：" + Arrays.toString(Algorithm.findTwoAlone(arr2)));*/

        // 在数组A中，除x只出现了一次外，其他数字都出现了3次，请找出x
        int[] arr3 = {5, 2, 12, 5, 2, 5, 2};
        System.out.println("那个只出现了一次的数字是：" + Algorithm.findAloneInTriplets(arr3));
        // 看看x的二进制码
        //System.out.println(Arrays.toString(Algorithm.findAloneInTriplets(arr3)));
    }
}
