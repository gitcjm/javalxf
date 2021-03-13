package com.str;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.Properties;

@Configuration
@ComponentScan
@PropertySource("jdbc.properties")
@EnableTransactionManagement
public class AppConfig {
    @Value("${jdbc.url}")
    String jdbcUrl;

    @Value("${jdbc.username}")
    String jdbcUsername;

    @Value("${jdbc.password}")
    String jdbcPassword;

    @Bean
    DataSource createDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(jdbcUsername);
        config.setPassword(jdbcPassword);
        config.addDataSourceProperty("autoCommit", "true");
        config.addDataSourceProperty("connectionTimeout", "5");
        config.addDataSourceProperty("idleTimeout", "60");
        return new HikariDataSource(config);
    }

    @Bean
    LocalContainerEntityManagerFactoryBean createEntityManagerFactory(@Autowired DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        // 设置DataSource
        entityManagerFactoryBean.setDataSource(dataSource);
        // 扫描指定的package获取所有entity class
        entityManagerFactoryBean.setPackagesToScan("com.str.entity");
        // 指定JPA提供商Hibernate
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        // 设定提供商自己的配置
        Properties props = new Properties();
        // 教程中使用的是HSQLDB内存数据库，在程序初始化时自动创建表结构。我的mysql数据库表已建好，不需要！
        // props.setProperty("hibernate.hbm2ddl.auto", "update");
        // 我使用Mysql数据库，所以数据库方言是：MySQLDialect
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        // 让Hibernate打印执行的SQL
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        entityManagerFactoryBean.setJpaProperties(props);
        return entityManagerFactoryBean;
    }

    @Bean
    PlatformTransactionManager createTxManager(@Autowired EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
