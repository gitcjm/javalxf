package com.str.web;

import com.str.entity.User;
import com.str.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
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
        User user = (User)session.getAttribute(KEY_USER);
        if (user == null) {
            return new ModelAndView("signin.html");
        }
        return new ModelAndView("redirect:/user/profile");
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
        return new ModelAndView("redirect:/user/profile");
    }

    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        if (user != null) {
            return new ModelAndView("profile.html", Map.of("user", user));
        }
        return new ModelAndView("redirect:/user/signin");
    }

    @GetMapping("/signout")
    public ModelAndView signout(HttpSession session) {
        session.removeAttribute(KEY_USER);
        return new ModelAndView("redirect:/user/signin");
    }

    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @PostMapping("/register")
    public ModelAndView doRegister(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("name") String name) {
        try {
            User user = userService.register(email, password, name);
        } catch (RuntimeException e) {
            return new ModelAndView("register.html",
                    Map.of("email", email, "error", "Register failed."));
        }
        return new ModelAndView("redirect:/user/signin");
    }

    @GetMapping("/users")
    public ModelAndView users() {
        List<User> users = userService.getEntityList(1, 4);
        return new ModelAndView("users.html", "users", users);
    }

    // 处理异常
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleUnknowException(Exception ex) {
        return new ModelAndView("500.html",
                Map.of("error", ex.getClass().getSimpleName(), "message", ex.getMessage()));
    }
}
