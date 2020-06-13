package com.str;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * 一个嵌入式的Tomcat
 * 1、启动简单，无需下载Tomcat或安装任何IDE插件；
 * 2、调试方便，可在IDE中使用断点调试；
 * 3、使用Maven创建war包后，也可以正常部署到独立的Tomcat服务器中。
 * */
public class EmbedMain {

    public static void main(String[] args) throws LifecycleException {
        // 启动Tomcat
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.getInteger("port", 8080));
        tomcat.getConnector();
        // 创建webapp
        Context ctx = tomcat.addWebapp("",
                new File("src/main/webapp").getAbsolutePath());
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(
                new DirResourceSet(resources, "/WEB-INF/classes",
                        new File("target/classes").getAbsolutePath(),
                        "/")
        );
        ctx.setResources(resources);
        tomcat.start();
        tomcat.getServer().await();
    }
}
