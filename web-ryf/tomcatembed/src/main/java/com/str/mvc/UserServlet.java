package com.str.mvc;

import com.str.mvc.bean.School;
import com.str.mvc.bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 假装从数据库读取
        School school = new School("No.1 Middle School", "108 South Street");
        User user = new User(101, "Jerry", school);
        // 放入Request中
        req.setAttribute("user", user);
        // 分发给user.jsp, getRequestDispatcher()方法用于服务器上资源的转发
        req.getRequestDispatcher("/WEB-INF/jsp-mvc/user.jsp").forward(req, resp);
    }
}
