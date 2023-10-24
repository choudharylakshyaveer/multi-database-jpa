package com.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@EntityScan(basePackages = {"com.springjpa.twitter.entity", "com.springjpa.inventory.entity"})
@SpringBootApplication(exclude= HibernateJpaAutoConfiguration.class)
public class SpringJpaTutorialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaTutorialsApplication.class, args);
    }

}
