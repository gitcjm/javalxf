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
        User user = (User) session.getAttribute(KEY_USER);
        if (user != null) {
            return new ModelAndView("redirect:/user/profile");
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
        return new ModelAndView("redirect:/user/profile");
    }

    /**
     * 修改密码
     * 经过一些挫折，我似乎理解了 redirect 的含义：
     * 当还没有创建某一页面时（即还没有new ModelAndView()），是不能redirect的！
     * 也就是说，没有view可供redirect !
     * */
    @GetMapping("/password")
    public ModelAndView password(HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        if (user != null) {
            return new ModelAndView("password.html");
        }
        return new ModelAndView("signin.html");
    }

    @PostMapping("/password")
    public ModelAndView changePassword(
            @RequestParam("newPassword") String newPassword,
            HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        userService.updateUser(user, newPassword);
        User user1 = userService.getUserByEmail(user.getEmail());
        session.setAttribute(KEY_USER, user1);
        return new ModelAndView("redirect:/user/profile");
    }

    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        if (user == null) {
            return new ModelAndView("redirect:/user/signin");
        }
        return new ModelAndView("profile.html", Map.of("user", user));
    }

    @GetMapping("/signout")
    public String signout(HttpSession session) {
        session.removeAttribute(KEY_USER);
        return "redirect:/user/signin";
    }

    /**
     * 页面创建都需要通过@GetMapping？
     * */
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

        return new ModelAndView("redirect:/user/signin");
    }

    @GetMapping("/users")
    public ModelAndView allUsers() {
        List<User> users = userService.getUsers(1, 10);
        return new ModelAndView("users.html", "users", users);
    }

    @PostMapping(value = "/rest",
            consumes = "application/json;charset=UTF-8",
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String rest(@RequestBody User user) {
        return "{\"restSupport\":true}";
    }

}
