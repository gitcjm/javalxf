package com.str.javatime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class JavaTime {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);
        System.out.println(Instant.now().atZone(ZoneId.systemDefault()));
    }
}
