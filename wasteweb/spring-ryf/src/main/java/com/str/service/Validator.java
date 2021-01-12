package com.str.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public interface Validator {
    void validate(String email, String password, String name);
}

@Component
class EmailValidator implements Validator {
    @Override
    public void validate(String email, String password, String name) {
        // \\@前面修改成"^\\w+\\-*\\w+" 就可以匹配我的邮箱"163mail-cjm@163.com"
        if (!email.matches("^\\w+\\-*\\w+\\@[a-z0-9]+\\.[a-z]{2,10}$")) {
            throw new IllegalArgumentException("Invalid email: " + email);
        }
    }
}

@Component
class PasswordValidator implements Validator {
    public void validate(String email, String password, String name) {
        if (!password.matches("^.{6,20}$")) {
            throw new IllegalArgumentException("Invalid password");
        }
    }
}

@Component
class NameValidator implements Validator {
    public void validate(String email, String password, String name) {
        if (name == null || name.isEmpty() || name.length() > 10) {
            throw new IllegalArgumentException("Invalid name: " + name);
        }
    }
}

@Component
class Validators {
    @Autowired
    List<Validator> validators;

    public void validate(String email, String password, String name) {
        for (Validator validator : this.validators) {
            validator.validate(email, password, name);
        }
    }
}