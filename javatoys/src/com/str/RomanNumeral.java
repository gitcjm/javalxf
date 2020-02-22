package com.str;

import java.util.LinkedHashMap;
import java.util.Map;

public enum RomanNumeral {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
    private static Map<Integer, RomanNumeral> map = new LinkedHashMap<Integer,RomanNumeral>();
    public final int val;

    RomanNumeral(int val) {
        this.val = val;
        storeInMap();
    }

    private void storeInMap() {
        map.put(val, this);
    }

    public static RomanNumeral fromInt(int val) {
        return map.get(val);
    }

    public static void main(String[] args) {
        int sum = 0;
        for (int i=0; i<1000; i++) {
            if (fromInt(i) != null) {
                sum += i;
            }
        }
        System.out.println(sum);
    }
}
