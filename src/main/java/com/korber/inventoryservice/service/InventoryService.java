package com.korber.inventoryservice.service;

import com.korber.inventoryservice.dto.InventoryReserveResponse;
import com.korber.inventoryservice.dto.InventoryResponse;

public interface InventoryService {
	
	public InventoryResponse getInventory(Long productId);
	
	public InventoryReserveResponse reserve(Long productId,int quantity);
}
