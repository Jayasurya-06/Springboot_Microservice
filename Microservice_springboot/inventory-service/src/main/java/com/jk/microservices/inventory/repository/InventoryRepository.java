package com.jk.microservices.inventory.repository;

import com.jk.microservices.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    boolean existsBySkuCodeAndQuantityEqual(String skuCode, Integer quantity);
}
