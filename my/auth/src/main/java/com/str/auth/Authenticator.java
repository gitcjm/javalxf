package com.str.auth;

import com.str.entity.User;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Authenticator {
    // 认证成功返回User, 认证失败抛出异常, 无认证信息返回null
    User authenticate(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException;
}
