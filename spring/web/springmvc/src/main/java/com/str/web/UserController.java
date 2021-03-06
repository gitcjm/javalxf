package com.str.web;

import com.str.entity.User;
import com.str.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/user")
public class UserController {
    public static final String KEY_USER = "__user__";

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ModelAndView index(HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        Map<String, Object> model = new HashMap<>();
        if (user != null) {
            model.put("user", user);
        }
        return new ModelAndView("index.html", model);
    }

    @GetMapping("/signin")
    public ModelAndView signin(HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        if (user != null) {
            return new ModelAndView("redirect:/profile");
        }
        return new ModelAndView("signin.html");
    }

    @PostMapping("/signin")
    public ModelAndView doSignin(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session) {
        try {
            User user = userService.signin(email, password);
            session.setAttribute(KEY_USER, user);
        } catch (RuntimeException e) {
            return new ModelAndView("signin.html",
                    Map.of("email", email, "error", "Signin failed"));
        }
        return new ModelAndView("redirect:/profile");
    }

    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        if (user == null) {
            return new ModelAndView("redirect:/signin");
        }
        return new ModelAndView("profile.html", Map.of("user", user));
    }

    @GetMapping("/signout")
    public String signout(HttpSession session) {
        session.removeAttribute(KEY_USER);
        return "redirect:/signin";
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register.html");
    }

    @PostMapping("/register")
    public ModelAndView register(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("name") String name) {
        try {
            User user = userService.register(email, password, name);
        } catch (RuntimeException e) {
            return new ModelAndView("register.html",
                    Map.of("email", email, "error", "Register failed."));
        }

        return new ModelAndView("redirect:/signin");
    }

    @GetMapping("/list_users")
    public ModelAndView listUsers() {
        List<User> users = userService.getUsers(1, 3);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list_users.html");
        mv.addObject("users", users);
        return mv;
        // 现代写法：
        // return new ModelAndView("list_users.html", Map.of("users", users));
    }

}
