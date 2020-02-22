package com.str.hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    /**
     * 计算字符串的MD5直
     * @param string
     * @param algorithm 算法名称
     * @return 字符串的MD5值
     * */
    public static String hash(String string, String algorithm) {
        if (string.isEmpty()) {
            return "";
        }

        String result = "";
        MessageDigest digest = null;

        try {
            digest = MessageDigest.getInstance(algorithm);
            byte[] bytes = digest.digest(string.getBytes("UTF-8"));
            result = byte2HexString(bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 计算文件的MD5值
     * @param file
     * @param algorithm 算法名称
     * @return 文件的MD5值
     * */
    public static String hash(File file, String algorithm) {
        if (file == null || !file.isFile() || !file.exists()) {
            return "";
        }

        FileInputStream in = null;
        String result = "";
        byte buffer[] = new byte[1024];
        int len;

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            in = new FileInputStream(file);
            while ((len=in.read(buffer)) != -1) {
                digest.update(buffer, 0, len);
            }
            byte[] bytes = digest.digest();

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
