package com.str.web;

import com.str.entity.User;
import com.str.service.UserServiceOld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final String KEY_USER = "__user__";

    @Autowired
    UserServiceOld userService;

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
}
