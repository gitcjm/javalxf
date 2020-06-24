package com.str.mvcadvanced.framework;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.loader.ServletLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class ViewEngine {
    private final PebbleEngine engine;

    public ViewEngine(ServletContext servletContext) {
        // 定义一个ServletLoader用于加载模板
        ServletLoader loader = new ServletLoader(servletContext);
        // 模板编码
        loader.setCharset("UTF-8");
        // 模板前缀，这里默认模板前缀必须放在'/WEB-INF/templates'目录
        loader.setPrefix("/WEB-INF/templates");
        // 模板后缀
        loader.setSuffix("");
        // 创建Pebble实例
        this.engine = new PebbleEngine.Builder()
                .autoEscaping(true)     // 默认打开HTML转义，防止XSS攻击
                .cacheActive(false)     // 禁用缓存，使得每次修改模板可以立刻看到效果
                .loader(loader).build();
    }

    public void render(ModelAndView mv, Writer writer) throws IOException {
        // 根据view找到模板文件
        PebbleTemplate template = this.engine.getTemplate(mv.view);
        // 渲染并写入Writer
        template.evaluate(writer, mv.model);
    }
}
