package com.korber.inventoryservice.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.korber.inventoryservice.dto.BatchDTO;
import com.korber.inventoryservice.dto.InventoryReserveResponse;
import com.korber.inventoryservice.dto.InventoryResponse;
import com.korber.inventoryservice.exception.InsufficientStockException;
import com.korber.inventoryservice.factory.InventoryHandlerFactory;
import com.korber.inventoryservice.model.InventoryBatch;
import com.korber.inventoryservice.repository.InventoryRepository;
import com.korber.inventoryservice.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryRepository repository;

	@Autowired
	private InventoryHandlerFactory inventoryFactory;

	@Override
	public InventoryResponse getInventory(Long productId) {
		List<InventoryBatch> list = repository.findByProductIdOrderByExpiryDateAsc(productId);
		if (list.isEmpty()) {
			throw new RuntimeException("No inventory found for productId=" + productId);
		}

		InventoryResponse r = new InventoryResponse();
		r.setProductId(productId);
		r.setProductName(list.get(0).getProductName());
		r.setBatches(list.stream().map(b -> new BatchDTO(b.getBatchId(), b.getQuantity(), b.getExpiryDate())).toList());
		return r;
	}

	@Override
	public InventoryReserveResponse reserve(Long productId, int quantity) {

		List<InventoryBatch> list = repository.findByProductIdOrderByExpiryDateAsc(productId);
		if (list.isEmpty()) {
			throw new InsufficientStockException(
		            "No inventory found for productId = " + productId);
		}
		List<Long> usedBatchIds = inventoryFactory.getHandler().updateStock(list, quantity);
		repository.saveAll(list);

		return new InventoryReserveResponse(productId, list.get(0).getProductName(), quantity, "RESERVED",
				usedBatchIds);
	}
}
