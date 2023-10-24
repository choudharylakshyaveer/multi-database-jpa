package com.springjpa.inventory.controller;

import com.springjpa.inventory.entity.Product;
import com.springjpa.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping("/getInventory")
    public List<Product> getAllInventory(){
        return inventoryService.getAllProducts();
    }
}
