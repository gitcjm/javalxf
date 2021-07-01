package com.str.auth;

import com.str.entity.User;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocalCookieAuthenticator implements Authenticator {

    private static final String KEY_USER = "__user__";

    @Override
    public User authenticate(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String cookie = getCookieFromRequest(request, KEY_USER);
        if (cookie == null) {
            return null;
        }
        return getUserByCookie(cookie);
    }
}
