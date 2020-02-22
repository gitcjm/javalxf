package com.str;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Searching {
    public static void main(String[] args) {
        String[] strings = {"0","1","2","3","4","5"};

        List<Integer> integers = new ArrayList<Integer>();
        for (String s : strings) {
            integers.add(Integer.valueOf(s));
        }

        System.out.println(Collections.binarySearch(integers, 2, cmp));
    }

    static Comparator<Integer> cmp = new Comparator<Integer>() {
        public int compare(Integer i, Integer j) {
            return i<j ? -1 : (i==j ? 0 : 1);
        }
    };
}
