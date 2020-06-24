package com.str.mvcadvanced.controller;

import com.str.mvcadvanced.bean.User;
import com.str.mvcadvanced.framework.GetMapping;
import com.str.mvcadvanced.framework.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserController {
    // 模拟一个数据库
    private Map<String, User> userDatabase = new HashMap<>() {
        {   // static block
            List<User> users = List.of(
                    new User("bob@163.com", "bob123", "Bob", "This is bob."),
                    new User("tom@hotmail.com", "tom123", "Tom", "This is Tom.")
            );
            users.forEach(user -> {
                put(user.email, user);
            });
        }
    };

    @GetMapping("/signin")
    public ModelAndView signin() {
        return new ModelAndView("/signin.html");
    }

    @GetMapping("/user/profile")
    public ModelAndView profile(HttpServletResponse response, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            // 未登录，跳转到登录页
            return new ModelAndView("redirect:/signin");
        }
        if (!user.isManager()) {
            // 权限不够，返回403
            response.sendError(403);
            return null;
        }
        return new ModelAndView("/profile.html", Map.of("user", user));
     }
}
