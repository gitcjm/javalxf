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

    @Test
    public void findPosInArray() {
        int[] arr = {3, 4, 5, 4, 5, 6, 7, 8, 7, 6, 7, 8, 9, 10};
        int expected = 2;
        int actual = Algorithm.findPosInArray(arr, 5);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findRepeatedElement() {
        int[] arr = {0, 5, 2, 0, 3, 4};
        int expected = 0;
        int actual = Algorithm.findRepeatedElement(arr);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findReserveNumPair() {
        int[] array = {8,7,9,10,6,5};
        int expected = 5;
        int actual = Algorithm.findReverseNumPair(array);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void generateSumArray() {
        int[] array = Algorithm.genRandomArray(20, 5);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void factorial() {
        int expected = 120;
        int actual = Algorithm.factorial(10);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void fiboCount() {
        int expected = 5;
        int actual = Algorithm.fiboCount(21);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void fibonacci() {
        /*int[] expected = {1,1,2,3,5,8};
        int[] actual = Algorithm.fibonacci(10);
        Assert.assertArrayEquals(expected, actual);*/
        System.out.println(Arrays.toString(Algorithm.fibonacci(1000)));
    }

    @Test
    public void fiboNumber() {
        // 第n个斐波那契数是什么
        long expected = 21;
        long actual = Algorithm.fiboNumber(8);
        //Assert.assertEquals(expected, actual);
        System.out.println(actual);
    }

    @Test
    public void swap() {
        int[] arr = new int[2];
        arr[0] = 2;
        arr[1] = 5;
        Algorithm.swap(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void KnuthShuffle() {
        int[] arr = {1, 2, 3, 4, 5};
        Algorithm.KnuthShuffle(arr);
        System.out.println(Arrays.toString(arr));
    }



}