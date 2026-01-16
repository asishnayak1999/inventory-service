package com.korber.inventoryservice.factory;

import java.util.List;

import com.korber.inventoryservice.model.InventoryBatch;

public interface InventoryHandler {
	List<Long> updateStock(List<InventoryBatch> batches,int qty);
}
