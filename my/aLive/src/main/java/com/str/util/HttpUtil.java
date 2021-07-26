package com.str.util;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;

public class HttpUtil {

    public static int getInt(HttpServletRequest request, String paramName) {
        String s = request.getParameter(paramName);
        return Integer.parseInt(s);
    }

    public static float getLong(HttpServletRequest request, String paramName) {
        String s = request.getParameter(paramName);
        return Float.parseFloat(s);
    }

    public static String getString(HttpServletRequest request, String paramName) {
        String s = request.getParameter(paramName);
        if (s == null || s.equals("")) {
            throw new NullPointerException("Null parameter: " + paramName);
        }
        return s;
    }

    /**
     * 从表单获取实体属性，然后创建该实体
     * 用于替换Service和Controller中具体而琐碎的参数获取过程
     * */
    public static Object createFormBean(HttpServletRequest request, Class clazz) {
        Object bean;
        try {
            Constructor constructor = clazz.getConstructor();
            bean = constructor.newInstance();
        } catch (Exception e) {
            return new Object();
        }



        return null;
    }

    private static String htmlEncode(String text) {
        if(text==null || "".equals(text))
            return "";
        text = text.replace("<", "&lt;");
        text = text.replace(">", "&gt;");
        text = text.replace(" ", "&nbsp;");
        text = text.replace("\"", "&quot;");
        text = text.replace("\'", "&apos;");
        return text.replace("\n", "<br/>");
    }
}
