package com.str.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {

    @Autowired
    private MailService mailService;

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    // java 8 中没有 List.of() 方法
    /*private List<User> users = new ArrayList<User>(List.of(
            new User(1, "361335019@qq.com", "123", "cjmqq"),
            new User(2, "163mail-cjm@163.com", "123", "cjm163"),
            new User(3, "163mai_nql@163.com", "123", "nql163")));*/

    // 初始化时，添加几个用户
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(1, "361335019@qq.com", "123", "cjmqq"));
        users.add(new User(2, "163mail-cjm@163.com", "123", "cjm163"));
        users.add(new User(3, "163mail_nql@163.com", "123", "nql163"));
    }

    // 监控login()方法性能
    @MetricTime("login")
    public User login(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                mailService.sendLoginMail(user);
                return user;
            }
        }
        throw new RuntimeException("login failed.");
    }

    public User getUser(long id) {
        // lambda 表达式写法，现在还不习惯！
        /*return this.users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow();*/
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        throw new RuntimeException("user not exist.");
    }

    // 监控register()方法性能
    @MetricTime("register")
    public User register(String email, String password, String name) {
        /*users.forEach(
                (user) -> {
                    if (user.getEmail().equalsIgnoreCase(email)) {
                        throw new RuntimeException("email exist.");
                    }
                });*/
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                throw new RuntimeException("email exist.");
            }
        }

        /*User user = new User(users.stream().mapToLong(
                u -> u.getId()).max().getAsLong(), email, password, name);*/
        User user = new User(users.get(users.size()-1).getId() + 1,
                email, password, name);
        users.add(user);
        mailService.sendRegistrationMail(user);

        return user;
    }

    public void logout(User user) {
        mailService.sendLogoutMail(user);
    }

}
