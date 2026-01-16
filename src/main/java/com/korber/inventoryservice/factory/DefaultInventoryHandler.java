package com.korber.inventoryservice.factory;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.korber.inventoryservice.exception.InsufficientStockException;
import com.korber.inventoryservice.model.InventoryBatch;

@Component
public class DefaultInventoryHandler implements InventoryHandler {

	@Override
	public List<Long> updateStock(List<InventoryBatch> batches, int qty) {
		int remaining = qty;
		List<Long> usedBatchIds = new ArrayList<>();
		for (InventoryBatch batch : batches) {
			if (remaining <= 0)
				break;
			int available = batch.getQuantity();
			if (available > 0) {
				int used = Math.min(available, remaining);
				batch.setQuantity(available - used);
				remaining -= used;
				usedBatchIds.add(batch.getBatchId());
			}
		}
		if (remaining > 0) {
			throw new InsufficientStockException("Insufficient stock for " + remaining);
		}
		return usedBatchIds;
	}
}
