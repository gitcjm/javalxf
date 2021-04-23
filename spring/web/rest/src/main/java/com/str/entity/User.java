package com.str.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User extends AbstractEntity {
    private String email;
    private String password;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(this.email.trim().toLowerCase().getBytes(StandardCharsets.UTF_8));
            return "https://www.gravatar.com/avatar/" + String.format("%032x", new BigInteger(1, hash));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 这个toString()函数真是奇妙，把数据库中没有的属性也加上了，如：createdDateTime
     * 这样，那些额外属性也就可以在页面（view）中使用了。
    */
    @Override
    public String toString() {
        return String.format("User[id=%s, email=%s, name=%s, password=%s, createdAt=%s, createdDateTime=%s]",
                getId(), getEmail(), getName(), getPassword(), getCreatedAt(), getCreatedDateTime());
    }
}
