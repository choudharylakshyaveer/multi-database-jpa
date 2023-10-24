package com.springjpa.inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(entityManagerFactoryRef = "inventorySessionFactory",
        basePackages = {"com.springjpa.inventory.repo"},
        transactionManagerRef = "inventoryTransactionManager")
public class DatasourceConfigInventory {
    @Autowired
    Environment env;
    //datasource

    @Bean(name = "inventoryDatasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getProperty("app.datasource.inventory.url"));
        dataSource.setDriverClassName(env.getProperty("app.datasource.inventory.driverClassName"));
        dataSource.setUsername(env.getProperty("app.datasource.inventory.username"));
        dataSource.setPassword(env.getProperty("app.datasource.inventory.password"));
        return dataSource;
    }

    //entityManager
    @Bean(name="inventorySessionFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.springjpa.inventory");
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", env.getProperty("app.datasource.inventory.dialect"));
        hibernateProperties.put("hibernate.show_sql", env.getProperty("app.datasource.inventory.show-sql"));
        hibernateProperties.put("hibernate.hbm2ddl.auto", env.getProperty("app.datasource.inventory.hbm2ddl.auto"));
        sessionFactory.setHibernateProperties(hibernateProperties);

        return sessionFactory;
    }

    //platformTransactionManager
    @Bean(name = "inventoryTransactionManager")
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
