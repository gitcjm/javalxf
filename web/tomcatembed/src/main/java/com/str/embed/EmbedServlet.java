package com.str.embed;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/embed")
public class EmbedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        String name = req.getParameter("name");
        if (name == null) {
            name = "world";
        }
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Hello, " + name + "!</h1>");
        // 测试路径
        pw.write("<p>URI: " + req.getRequestURI() + "</p>");
        pw.write("<p>req.ContextPath: " + req.getContextPath() + "</p>");
        ServletContext ctx = req.getServletContext();
        pw.write("<p>ctx.ContextPath: " + ctx.getContextPath() + "</p>");

        pw.flush();
    }
}
