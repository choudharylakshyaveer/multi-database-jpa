package com.springjpa.twitter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "twitterDbSessionFactory",
        basePackages = {"com.springjpa.twitter.repo"},
        transactionManagerRef = "twitterTransactionManager")
public class DatasourceConfigTwitter {
    @Autowired
    Environment env;
    //datasource

    @Bean(name = "twitterDatasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("app.datasource.twitter.url"));
        dataSource.setDriverClassName(env.getProperty("app.datasource.twitter.driverClassName"));
        dataSource.setUsername(env.getProperty("app.datasource.twitter.username"));
        dataSource.setPassword(env.getProperty("app.datasource.twitter.password"));
        return dataSource;
    }

    //entityManager
    @Bean(name="twitterDbSessionFactory")
    @Primary
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.springjpa.twitter");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", env.getProperty("app.datasource.twitter.dialect"));
        hibernateProperties.put("hibernate.show_sql", env.getProperty("app.datasource.twitter.show-sql"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", env.getProperty("app.datasource.twitter.hbm2ddl.auto"));
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    //platformTransactionManager
    @Bean(name = "twitterTransactionManager")
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
