package com.str;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource("jdbc.properties")
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

    // 为了启用Hibernate，我们需要创建一个LocalSessionFactoryBean
    @Bean
    LocalSessionFactoryBean createSessionFactory(@Autowired DataSource dataSource) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        // 设置DataSource
        sessionFactoryBean.setDataSource(dataSource);
        // 扫描指定的package获取所以entity class
        sessionFactoryBean.setPackagesToScan("com.str.entity");
        // 设置Hibernate配置
        Properties props = new Properties();
        // 廖雪峰在教程中用的是HSQLDB内存数据库，在程序初始化时才创建，所以需要下面这个配置。我的MYSQL数据库就不需要了
        // 这是hibernate自动创建数据库的设置, 不管是hsqldb还是mysql数据库.
        // hbm2ddl(hibernate model to DDL)就是hibernate根据java bean自动创建数据库的功能
        /**
         * 该属性可以设置为none, validate, create, create-drop, update
         * none:        不配置
         * validate:    加载 Hibernate 时，验证创建数据库表结构
         * create:      每次加载 Hibernate，重新创建数据库表结构
         * create-drop: 加载 Hibernate 时创建，退出是删除表结构
         * update:      加载 Hibernate 时自动更新数据库结构
         */
        props.setProperty("hibernate.hbm2ddl.auto", "update");  // 生产环境不需要
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        props.setProperty("hibernate.show_sql", "ture");
        sessionFactoryBean.setHibernateProperties(props);
        return sessionFactoryBean;
    }

    @Bean
    HibernateTemplate createHibernateTemplate(@Autowired SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }

    @Bean
    PlatformTransactionManager createTxManager(@Autowired SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
