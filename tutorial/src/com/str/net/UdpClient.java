package com.str.net;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UdpClient {
    public static void main(String[] args) throws IOException {
        // talk();
        talkEx();
    }

    static void talk() throws IOException {
        // 连接指定服务器和端口
        DatagramSocket ds = new DatagramSocket();
        ds.setSoTimeout(1000);
        // "localhost"也可以写为本机地址"10.14.209.232"，这也叫ByName!
        ds.connect(InetAddress.getByName("localhost"), 6667);

        // 发送数据
        String s = "崔军明";
        byte[] data = s.getBytes();
        DatagramPacket packet = new DatagramPacket(data, data.length);
        ds.send(packet);

        // 接受数据
        byte[] buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        ds.receive(packet);
        String resp = new String(packet.getData(), packet.getOffset(),
                packet.getLength(), StandardCharsets.UTF_8);
        System.out.println(resp);

        // 断开连接（假）
        ds.disconnect();
    }

    static void talkEx() throws IOException {
        // 连接指定服务器和端口
        DatagramSocket ds = new DatagramSocket();
        ds.setSoTimeout(1000);
        // "localhost"也可以写为本机地址"10.14.209.232"，这也叫ByName!
        ds.connect(InetAddress.getByName("localhost"), 6667);

        Scanner sc = new Scanner(System.in);
        for (;;) {
            // 读取一行输入，发送给服务器
            System.out.println(">>> ");
            String s = sc.nextLine();
            byte[] data = s.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length);
            ds.send(packet);

            // 接受数据
            byte[] buffer = new byte[1024];
            packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            String resp = new String(packet.getData(), packet.getOffset(),
                    packet.getLength(), StandardCharsets.UTF_8);
            System.out.println("<<< " + resp);

            // 断开连接
            if (s.equals("bye")) {
                ds.disconnect();
                break;
            }
        }
    }
}
