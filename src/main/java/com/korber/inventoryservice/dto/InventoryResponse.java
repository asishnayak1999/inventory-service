package com.korber.inventoryservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class InventoryResponse {
	private Long productId;
	private String productName;
	private List<BatchDTO> batches;
}
