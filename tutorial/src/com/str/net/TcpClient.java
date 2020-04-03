package com.str.net;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        // 连接指定服务器和端口
        Socket sock = new Socket("localhost", 6666);

        try (InputStream input = sock.getInputStream()) {
            try (OutputStream output = sock.getOutputStream()) {
                // 处理对话
                handle(input, output);
            }
        }
        sock.close();
        System.out.println("disconnected.");
    }

    private static void handle(InputStream input, OutputStream output) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(output, StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(input, StandardCharsets.UTF_8));

        Scanner sc = new Scanner(System.in);
        System.out.println("[server] " + reader.readLine());

        for (;;) {
            // 打印提示
            System.out.println(">>> ");
            // 读取一行输入，发送给服务器
            String s = sc.nextLine();
            writer.write(s);
            writer.newLine();
            writer.flush();
            // 读取服务器的应答
            String resp = reader.readLine();
            System.out.println("<<< " + resp);
            // 结束对话
            if (resp.equals("bye")) {
                break;
            }
        }
    }
}
