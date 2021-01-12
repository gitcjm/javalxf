package com.str.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
* 浏览器中输入http://localhost:8080/hello/，即可看到HelloServlet的输出。
* 为啥路径是/hello/而不是/？因为一个Web服务器允许同时运行多个Web App，
* 而我们的Web App叫hello，因此，第一级目录/hello表示Web App的名字，
* 后面的/才是我们在HelloServlet中映射的路径。
* */
@WebServlet(urlPatterns = "/")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 设置响应类型
        resp.setContentType("text/html");
        // 获取输出流
        PrintWriter pw = resp.getWriter();
        // 写入响应数据
        pw.write("<h1>Hello cjm, welcome to servlet world! </h1>");
        // 最后不要忘了flush（因为使用的是BufferWriter）
        pw.flush();
    }
}
