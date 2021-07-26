package com.str.util;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;

import static org.junit.Assert.*;

public class MyDateTest {


    @Test
    public void strDate2Long() {
        long ld = MyDate.strDate2Long("2021-5-2", "yyyy-M-d");
        System.out.println(ld);
        System.out.println(Instant.ofEpochMilli(ld).atZone(ZoneId.systemDefault()));

    }
}