package com.str.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.str.cache.RedisService;
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

    public static final String KEY_USER_ID = "__userid__";
    public static final String KEY_USERS = "__users__";

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    RedisService redisService;

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleUnknowException(Exception ex) {
        return new ModelAndView("500.html",
                Map.of("error", ex.getClass().getSimpleName(), "message", ex.getMessage()));
    }

    @GetMapping("/")
    public ModelAndView index(HttpSession session) throws Exception {
        User user = getUserFromRedis(session);
        Map<String, Object> model = new HashMap<>();
        if (user != null) {
            model.put("user", user);
        }
        return new ModelAndView("index.html", model);
    }

    @GetMapping("/signin")
    public ModelAndView signin(HttpSession session) throws Exception {
        User user = getUserFromRedis(session);
        if (user != null) {
            return new ModelAndView("redirect:/user/profile");
        }
        return new ModelAndView("signin.html");
    }

    @PostMapping("/signin")
    public ModelAndView doSignin(@RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 HttpSession session) {
        try {
            User user = userService.signin(email, password);
            session.setAttribute(KEY_USER_ID, user.getId());
            putUserIntoRedis(user);
        } catch (Exception e) {
            return new ModelAndView("signin.html", Map.of("email", email, "error", "Signin failed."));
        }
        return new ModelAndView("redirect:/user/profile");
    }

    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) throws Exception {
        User user = getUserFromRedis(session);
        if (user == null) {
            return new ModelAndView("redirect:/user/signin");
        }
        return new ModelAndView("profile.html", Map.of("user", user));
    }

    @GetMapping("/signout")
    public ModelAndView signout(HttpSession session) {
        session.removeAttribute(KEY_USER_ID);
        return new ModelAndView("redirect:/user/signin");
    }

    @GetMapping("/user-list")
    public ModelAndView listUsers() {
        List<User>  users = userService.getUserList(3, 5);
        return new ModelAndView("user-list.html", Map.of("users", users));
    }

    @GetMapping("/user-detail/{id}")
    public ModelAndView userDetail(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return new ModelAndView("user-detail.html", Map.of("user", user));
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register.html");
    }

    @PostMapping("/register")
    public ModelAndView doRegister(@RequestParam("email") String email,
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

    /**
     * Redis读写
     * 使用JSON作为序列化方案，简单可靠
     * （使用ObjectMapper对象进行序列化和反序列化）
     * */

    private void putUserIntoRedis(User user) throws Exception {
        redisService.hset(KEY_USERS, user.getId().toString(),
                objectMapper.writeValueAsString(user));
    }

    private User getUserFromRedis(HttpSession session) throws Exception {
        Long id = (Long) session.getAttribute(KEY_USER_ID);
        if (id != null) {
            String s = redisService.hget(KEY_USERS, id.toString());
            if (s != null) {
                return objectMapper.readValue(s, User.class);
            }
        }
        return null;
    }

}
