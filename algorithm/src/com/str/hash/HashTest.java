package com.str.hash;

import java.io.File;

public class HashTest {
    public static void main(String[] args) {
        String string = "123456";
        System.out.println(MD5Util.md5(string));

        File file = new File("/home/cjm/IdeaProjects/algorithm/test.file");
        System.out.println(MD5Util.md5(file));

        System.out.println(HashUtil.hash(string, "MD5"));
        System.out.println(HashUtil.hash(file, "MD5"));

        System.out.println(HashUtil.hash(string, "SHA1"));
        System.out.println(HashUtil.hash(file, "SHA1"));

        System.out.println(HashUtil.hash(string, "SHA-256"));
        System.out.println(HashUtil.hash(file, "SHA-256"));
    }
}
