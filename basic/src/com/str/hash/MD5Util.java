package com.str.hash;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * 计算字符串的MD5直
     * @param string
     * @return 字符串的MD5值
     * */
    public static String md5(String string) {
        if (string.isEmpty()) {
            return "";
        }

        String result = "";
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes("UTF-8"));
            result = byte2HexString(bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 计算文件的MD5值
     * @param file
     * @return 文件的MD5值
     * */
    public static String md5(File file) {
        if (file == null || !file.isFile() || !file.exists()) {
            return "";
        }

        FileInputStream in = null;
        String result = "";
        byte buffer[] = new byte[1024];
        int len;

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len=in.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            byte[] bytes = md5.digest();

            result = byte2HexString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    private static String byte2HexString(byte[] bytes) {
        String result = "";

        for (byte b : bytes) {
            String temp = Integer.toHexString(b & 0xff);
            if (temp.length() == 1) {
                temp = "0" + temp;
            }
            result += temp;
        }

        return result;
    }
}
