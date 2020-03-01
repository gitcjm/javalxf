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
        System.out.println("堆排序：" + Arrays.toString(arr));*/

        /*// 冒泡排序
        SortUtil.bubbleSort(arr);
        System.out.println("冒泡排序：" + Arrays.toString(arr));*/

        /*// 测试合并两个有序数组
        int[] arr1 = {3, 5, 7, 8, 9, 12};
        int[] arr2 = {1, 2, 6, 8};
        int[] arrMerged =  new int[arr1.length + arr2.length];
        SortUtil.mergeArrayTest(arr1, arr1.length, arr2, arr2.length, arrMerged);
        System.out.println(Arrays.toString(arrMerged));*/

        // 归并排序
        SortUtil.mergeSort(arr, 0, arr.length);
        System.out.println("归并排序：" + Arrays.toString(arr));
    }
}
