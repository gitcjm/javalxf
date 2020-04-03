package com.str.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class UdpServer {
    public static void main(String[] args) throws IOException {
        // 监听指定端口
        DatagramSocket ds = new DatagramSocket(6667);
        System.out.println("server is running...");

        for (;;) {
            // 为接收或发送数据定义一个数据包 packet
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            // 接收数据
            ds.receive(packet);
            // 将packet中的数据（存储在buffer中），按UTF-8编码转换为String
            String s = new String(packet.getData(), packet.getOffset(),
                    packet.getLength(), StandardCharsets.UTF_8);
            System.out.println(s);

            // 发送数据
            String ackString = "[server ack] " + s;
            byte[] data = ackString.getBytes(StandardCharsets.UTF_8);
            packet.setData(data);
            ds.send(packet);
        }
    }

}
