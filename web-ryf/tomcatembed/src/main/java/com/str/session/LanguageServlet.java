package com.str.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet(urlPatterns = "/pref")
public class LanguageServlet extends HttpServlet {

    private static final Set<String> LANGUAGES = Set.of("en", "zh");

    static final Map<String, String> LANGSMAP = Map.of(
            "en", "English", "zh", "中文");

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String lang = req.getParameter("lang");
        if (LANGUAGES.contains(lang)) {
            // 创建一个新的Cookie
            Cookie cookie = new Cookie("lang", lang);
            // 该Cookie生效的路径范围
            cookie.setPath("/");
            // 有效期
            cookie.setMaxAge(8640000);  // 8640000秒=100天
            // 将该Cookie添加到相应
            resp.addCookie(cookie);
        }
        resp.sendRedirect("/");
    }

}
