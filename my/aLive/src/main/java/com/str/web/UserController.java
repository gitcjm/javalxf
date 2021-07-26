package com.str.web;

import com.str.entity.User;
import com.str.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String KEY_USER = "__user__";

    @Autowired
    UserService userService;

    @GetMapping("/update")
    public ModelAndView update() {
        return new ModelAndView("updateUser.html");
    }

    @PostMapping("/update")
    public ModelAndView doUpdate(@RequestParam("id") Long id,
                                 @RequestParam("password") String password,
                                 @RequestParam("name") String name,
                                 @RequestParam("mobile") String mobile,
                                 @RequestParam("address") String address,
                                 HttpSession session) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setName(name);
        user.setMobile(mobile);
        user.setAddress(address);
        try {
            userService.updateUser(user);
        } catch (RuntimeException e) {
            return new ModelAndView("update.html", Map.of("error", "Update failed."));
        }
        return new ModelAndView("redirect:/user/signin");
    }

    @GetMapping("/profile")
    public ModelAndView profile(HttpSession session) {
        User user = (User) session.getAttribute(KEY_USER);
        if (user == null) {
            return new ModelAndView("redirect:/user/signin");
        }
        return new ModelAndView("profile.html", Map.of("user", user));
    }
}
