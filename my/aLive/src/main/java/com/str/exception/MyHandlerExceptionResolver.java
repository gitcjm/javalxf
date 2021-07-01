package com.str.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一处理日志和错误
 * */
@Controller
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    // TODO: 添加日志功能

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o, Exception ex) {

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex.getClass().getSimpleName());
        model.put("message", ex.getMessage());

        // 根据不同错误, 转向不同页面
        if (ex instanceof NeedLoginException) {
            return new ModelAndView("redirect:/user/signin", model);
        } else if (ex instanceof SQLException) {
            return new ModelAndView("sql-error.html", model);
        } else if (ex instanceof OperationException) {
            return new ModelAndView("orperation-error.html", model);
        } else {
            return new ModelAndView("error.html", model);
        }
    }
}
