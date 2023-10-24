package com.springjpa.inventory.service;

import com.springjpa.inventory.config.DatasourceConfigInventory;
import com.springjpa.inventory.entity.Product;
import com.springjpa.inventory.repo.ProductInventoryRepo;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    ProductInventoryRepo productInventoryRepo;

    @Autowired
    DatasourceConfigInventory datasourceConfigInventory;

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    public void initializeProducts(){
        Product p1 = new Product(1L, "Coconut Water" , 101L);
        Product p2 = new Product(2L, "Soda Water" , 102L);
        Product p3 = new Product(3L, "Pepsi" , 103L);
        Product p4 = new Product(4L, "Coco Cola" , 104L);
        Product p5 = new Product(5L, "String" , 105L);
        Product p6 = new Product(6L, "Nimbuz water" , 106L);
        List<Product> productList = List.of(p1, p2, p3, p4, p5, p6);
        productInventoryRepo.saveAll(productList);
    }

    @Transactional("inventoryTransactionManager")
    public List<Product> getAllProducts(){
        return productInventoryRepo.findAll();
    }

}
