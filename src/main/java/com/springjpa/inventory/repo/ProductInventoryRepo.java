package com.springjpa.inventory.repo;

import com.springjpa.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInventoryRepo extends JpaRepository<Product, Long> {
}
