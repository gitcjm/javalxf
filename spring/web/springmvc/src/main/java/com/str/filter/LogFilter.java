package com.str.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
* 暂停一下Filter，再试试AOP。
 * 暂时失败，可能是Tomcat无法创建ASPECT对象的原因
 * 还是Filter简单，可如何定制？
* */
@WebFilter(urlPatterns = "/*")
public class LogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("LogFilter: process " + ((HttpServletRequest) request).getRequestURI());
        chain.doFilter(request, response);
    }
}
