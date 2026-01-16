package com.korber.inventoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korber.inventoryservice.model.InventoryBatch;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryBatch, Long> {
	List<InventoryBatch> findByProductIdOrderByExpiryDateAsc(Long productId);
}
