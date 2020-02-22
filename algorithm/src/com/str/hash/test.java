package com.str.hash;

import java.io.*;

public class test {
    /*public static void main(String[] args) throws UnsupportedEncodingException {
        // 计算字符串的MD5值
        String string = "崔军明";
        byte[] bytes = string.getBytes("UTF-8");
        for (byte b : bytes) {
            System.out.println(Integer.toHexString(b & 0xff));
        }
    }*/

    public static void main(String[] args) throws IOException {
        File file = new File("/home/cjm/Documents/java8ApiDocs/api/index.html");
        FileInputStream in = new FileInputStream(file);
        /*int i;
        while ((i=in.read()) != -1) {
            System.out.print((char)i);
        }
        in.close();
        */

        int len;
        byte[] b = new byte[512];
        while((len=in.read(b)) != -1) {
            System.out.println(new String(b, 0, len));
        }
        in.close();
    }
}
