package com.str.sort;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;

public class SortUtilTest {

    int len = 9;
    int[] arr = new int[len];

    @Before
    public void setUp() throws Exception {
        // 有种的随机函数，每次生成的随机数都一样；没有种子的，每次都不一样。
        Random random = new Random(500);
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(50)+10;
        }
        System.out.println("原始数组：" + Arrays.toString(arr));
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("排序：" + Arrays.toString(arr));
    }

    @Test
    public void mergeSort() {
        SortUtil.mergeSort(arr, 0, arr.length-1);
    }

    @Test
    public void quickSort() {
        SortUtil.quickSort(arr, 0, arr.length-1);
    }
}