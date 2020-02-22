package com.str;

public class ToBi {
    public static void main(String[] args) {
        String str = toBinary("新型冠状病毒");
        System.out.println(str);
    }

    private static String toBinary(String str) {
        char[] strChar = str.toCharArray();
        StringBuffer result = new StringBuffer();
        for (char c : strChar) {
            result.append(Integer.toBinaryString(c));
        }
        return result.toString();
    }
}
