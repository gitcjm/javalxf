package com.str.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 从HttpSession获取当前用户名
        String user = (String) req.getSession().getAttribute("user");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setHeader("X-Powered-By", "JavaEE Servlet");
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Welcome, " + (user != null ? user : "Guest") + "</h1>");
        if (user == null) {
            // 未登录，显示登录链接
            pw.write("<p><a href=\"/signin\">Sing In</a></p>");
        } else {
            // 已登录
            pw.write("<p><a href=\"/signout\">Sign Out</a></p>");
        }
        // 根据Cookie值，设置网页语言
        String lang = parseLanguageFromCookie(req);
        pw.write(LanguageServlet.LANGSMAP.get(lang));

        pw.flush();
    }

    private String parseLanguageFromCookie(HttpServletRequest req) {
        // 获取请求附带的所有Cookie
        Cookie[] cookies = req.getCookies();
        // 如果获取到Cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                // 获取Cookie名称为lang的值
                if (cookie.getName().equals("lang")) {
                    return cookie.getValue();
                }
            }
        }
        // 返回默认值
        return "en";
    }
}
