package com.str;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication()
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // mvc configuration
    @Bean
    WebMvcConfigurer createWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                // 映射"/static/"路径到classpath:/static/目录
                registry.addResourceHandler("/static/**")
                        .addResourceLocations("classpath:/static/");
            }
        };
    }

    /**
     * 为了启用JPA, 我们需要创建一个LocalContainerEntityManagerFactoryBean（Spring封装的EntityManagerFactory），
     * 并让它自动创建一个EntityManagerFactory
     * hbm2ddl.auto属性可以设置为: none, validate, create, create-drop, update
     * none:        不配置
     * validate:    加载 Hibernate 时，验证创建数据库表结构
     * create:      每次加载 Hibernate，重新创建数据库表结构
     * create-drop: 加载 Hibernate 时创建，退出是删除表结构
     * update:      加载 Hibernate 时自动更新数据库结构
     */
    @Bean
    LocalContainerEntityManagerFactoryBean createEntityManagerFactory(@Autowired DataSource dataSource) {
        var entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        // 设置DataSource
        entityManagerFactoryBean.setDataSource(dataSource);
        // 扫描指定的package, 获取所有entity class
        entityManagerFactoryBean.setPackagesToScan("com.str.entity");
        // 指定JPA提供商是Hibernate
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

        /* 设定特定提供商自己的配置 */
        var props = new Properties();
        // 生产环境使用"none"
        props.setProperty("hibernate.hbm2ddl.auto", "update");
        // mysql 8.0使用MySQL8Dialect方言
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        entityManagerFactoryBean.setJpaProperties(props);

        return entityManagerFactoryBean;
    }

    /**
     * 声明式事物
     * */
    @Bean
    PlatformTransactionManager createTxManager(@Autowired EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
