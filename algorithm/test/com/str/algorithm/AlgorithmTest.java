package com.str.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AlgorithmTest {
    @Test
    public void findOneAlone() {
        int[] arr = {2, 3, 5, 3, 9, 2, 5};
        System.out.println("数字对数组中落单的数字是：" + Algorithm.findOneAlone(arr));
    }

    @Test
    public void findTwoAlone() {
        int[] arr = {1, 1, 2, 2, 8, 16, 3, 5, 3, 5};
        System.out.println("两个落单者：" + Arrays.toString(Algorithm.findTwoAlone(arr)));
        /*int[] expected = {8, 16};
        int[] actual = Algorithm.findTwoAlone(arr);
        Assert.assertArrayEquals("不是这两个落单者", expected, actual);*/
    }

    @Test
    public void findAloneInTriplets() {
        int[] arr = {5, 2, 12, 5, 2, 5, 2};
        System.out.println("那个只出现了一次的数字是：" + Algorithm.findAloneInTriplets(arr));
        /*int expected = 12;
        int actual = Algorithm.findAloneInTriplets(arr);
        Assert.assertEquals(expected, actual);*/
    }
}