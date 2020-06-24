package com.str.mvcadvanced.framework;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.str.mvcadvanced.controller.IndexController;
import com.str.mvcadvanced.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@WebServlet(urlPatterns = "/")
public class DispatcherServlet extends HttpServlet {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, GetDispatcher> getMappings = new HashMap<>();
    private Map<String, PostDispatcher> postMappings = new HashMap<>();
    // TODO: 可指定package并自动扫描
    private List<Class<?>> controllers = List.of(IndexController.class, UserController.class);

    private ViewEngine viewEngine;

    private static final Set<Class<?>> supportedGetParameterTypes = Set.of(
            int.class, long.class, boolean.class, String.class,
            HttpServletRequest.class, HttpServletResponse.class, HttpSession.class
    );
    private static final Set<Class<?>> supportedPostParameterTypes = Set.of(
            HttpServletRequest.class, HttpServletResponse.class, HttpSession.class
    );

    /**
     * 当servlet容器创建当前servlet实例后，会自动调用init(ServletConfig)方法
     * */
    @Override
    public void init() {
        logger.info("init {}...", getClass().getSimpleName());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 依次处理每个Controller
        for (Class<?> controllerClass : controllers) {
            try {
                Object controllerInstance = controllerClass.getConstructor().newInstance();
                // 依次处理每个Method
                for (Method method : controllerClass.getMethods()) {
                    if (method.getAnnotation(GetMapping.class) != null) {
                        // 处理GetDispatcher
                        if (method.getReturnType() != ModelAndView.class && method.getReturnType() != void.class) {
                            throw new UnsupportedOperationException(
                                    "Unsupported return type: " + method.getReturnType() + " for method: " + method);
                        }
                        for (Class<?> parameterClass : method.getParameterTypes()) {
                            if (!supportedGetParameterTypes.contains(parameterClass)) {
                                throw new UnsupportedOperationException(
                                        "Unsupported parameter type: " + parameterClass + " for method: " + method);
                            }
                        }
                        // 使用Stream来获取所有参数名，以简化forEach操作。高！实在是高！
                        String[] parameterNames = Arrays.stream(method.getParameters())
                                .map(p -> p.getName()).toArray(String[]::new);
                        // 这儿就用到了GetMapping注解的值了
                        String path = method.getAnnotation(GetMapping.class).value();
                        logger.info("Found GET: {} => {}", path, method);
                        this.getMappings.put(path, new GetDispatcher(controllerInstance, method, parameterNames,
                                method.getParameterTypes()));
                    } else if (method.getAnnotation(PostMapping.class) != null) {
                        // 处理PostDispatcher
                        if (method.getReturnType() != ModelAndView.class && method.getReturnType() != void.class) {
                            throw new UnsupportedOperationException(
                                    "Unsupported return type: " + method.getReturnType() + " for method: " + method);
                        }
                        // TODO: requestBodyClass
                    }
                }
            } catch (ReflectiveOperationException e) {
                //throw new ServletException(e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        String path = req.getRequestURI().substring(req.getContextPath().length());
        // 根据路径查找GetDispatcher
        GetDispatcher dispatcher = this.getMappings.get(path);
        if (dispatcher == null) {
            // 未找到返回404
            resp.sendError(404);
            return;
        }
        // 调用Controller方法获得返回值
        ModelAndView mv = dispatcher.invoke(req, resp);
        // 允许返回null
        if (mv == null) {
            return;
        }
        // 允许返回'redirect:'开头的view表示重定向
        if (mv.view.startsWith("redirect:")) {
            resp.sendRedirect(mv.view.substring(9));
            return;
        }
        // 最后，将模板引擎渲染的内容写入响应
        PrintWriter pw = resp.getWriter();
        this.viewEngine.render(mv, pw);
        pw.flush();
    }

}

abstract class AbstractDispatcher {
    public abstract ModelAndView invoke(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ReflectiveOperationException;
}

class GetDispatcher extends AbstractDispatcher {
    final Object instance;    // Controller实例
    final Method method;      // Controller方法
    final String[] parameterNames;        // 方法参数名称
    final Class<?>[] parameterClasses;    // 方法参数类型

    public GetDispatcher(Object instance, Method method, String[] parameterNames, Class<?>[] parameterClasses) {
        super();
        this.instance = instance;
        this.method = method;
        this.parameterNames = parameterNames;
        this.parameterClasses = parameterClasses;
    }

    @Override
    public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ReflectiveOperationException {
        Object[] arguments = new Object[parameterClasses.length];
        for (int i = 0; i < parameterClasses.length; i++) {
            String parameterName = parameterNames[i];
            Class<?> parameterClass = parameterClasses[i];
            if (parameterClass == HttpServletRequest.class) {
                arguments[i] = request;
            } else if (parameterClass == HttpServletResponse.class) {
                arguments[i] = response;
            } else if (parameterClass == HttpSession.class) {
                arguments[i] = request.getSession();
            } else if (parameterClass == int.class) {
                arguments[i] = Integer.valueOf(getOrDefault(
                        request, parameterName, "0"));
            } else if (parameterClass == long.class) {
                arguments[i] = Long.valueOf(getOrDefault(
                        request, parameterName, "0"));
            } else if (parameterClass == boolean.class) {
                arguments[i] = Boolean.valueOf(getOrDefault(
                        request, parameterName, "false"));
            } else if (parameterClass == String.class) {
                arguments[i] = getOrDefault(request, parameterName, "");
            } else {
                throw new RuntimeException("Missing handler for type: " + parameterName);
            }
        }
        return (ModelAndView) this.method.invoke(this.instance, arguments);
    }

    private String getOrDefault(HttpServletRequest request,
                                String name, String defaultValue) {
        String s = request.getParameter(name);
        return s == null ? defaultValue : s;
    }
}

class PostDispatcher extends AbstractDispatcher {
    final Object instance;    // Controller实例
    final Method method;      // Controller方法
    final Class<?>[] parameterClasses;    // 方法参数类型
    final ObjectMapper objectMapper;

    public PostDispatcher(Object instance, Method method, Class<?>[] parameterClasses, ObjectMapper objectMapper) {
        this.instance = instance;
        this.method = method;
        this.parameterClasses = parameterClasses;
        this.objectMapper = objectMapper;
    }

    @Override
    public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ReflectiveOperationException {
        Object[] arguments = new Object[parameterClasses.length];
        for (int i = 0; i < parameterClasses.length; i++) {
            Class<?> parameterClass = parameterClasses[i];
            if (parameterClass == HttpServletRequest.class) {
                arguments[i] = request;
            } else if (parameterClass == HttpServletResponse.class) {
                arguments[i] = response;
            } else if (parameterClass == HttpSession.class) {
                arguments[i] = request.getSession();
            } else {
                // 读取JSON并解析为JavaBean
                BufferedReader reader = request.getReader();
                arguments[i] = this.objectMapper.readValue(reader, parameterClass);
            }
        }
        return (ModelAndView) this.method.invoke(instance, arguments);
    }
}
