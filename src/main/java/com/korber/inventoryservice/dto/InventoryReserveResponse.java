package com.korber.inventoryservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryReserveResponse {
	private Long productId;
	private String productName;
	private Integer quantity;
	private String status;
	private List<Long> usedBatchIds;
}
