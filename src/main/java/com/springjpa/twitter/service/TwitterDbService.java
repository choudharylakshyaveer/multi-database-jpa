package com.springjpa.twitter.service;

import com.springjpa.inventory.config.DatasourceConfigInventory;
import com.springjpa.twitter.config.DatasourceConfigTwitter;
import com.springjpa.twitter.entitiy.TwitterIds;
import com.springjpa.twitter.repo.TwitterRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@EnableTransactionManagement
@Transactional
public class TwitterDbService {

    SessionFactory sessionFactory;

    @Autowired
    DatasourceConfigTwitter hibernateConfig;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    TwitterRepo twitterRepo;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
        // sf.close();
    }

    @Transactional(value = "twitterTransactionManager")
    public List<TwitterIds> getAllTwitterEntities(){
        return twitterRepo.findAll();
     }
}
