package com.str.sort;

import java.util.Arrays;

public class TestSort {
    public static void main(String[] args) {
        int[] arr = {2, 7, 0, 9, 8, 4, 1, 3, 5, 6, 34, 25, 56, 15};
        System.out.println("原始数组：" + Arrays.toString(arr));

        /*SortUtil.insertSort(arr);
        System.out.println("插入排序：" + Arrays.toString(arr));*/

        /*SortUtil.sheelSort(arr);
        System.out.println("希尔排序：" + Arrays.toString(arr));*/

        /*// 简单选择排序
        SortUtil.selectSort(arr);
        System.out.println("简单排序：" + Arrays.toString(arr));*/

        /*// 堆排序
        SortUtil.heapSort(arr);
        System.out.println("堆排序：" + Arrays.toString(arr))*/;

        // 快速排序
        SortUtil.quickSort(arr, 0, arr.length-1);
        System.out.println("快速排序：" + Arrays.toString(arr));
    }

    private static int[] genRandArray() {
        int[] result;


        return result;
    }
}
