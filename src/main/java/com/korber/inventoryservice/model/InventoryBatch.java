package com.korber.inventoryservice.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "inventory_batch")
@AllArgsConstructor
@NoArgsConstructor
public class InventoryBatch {
	@Id
	private Long batchId;
	private Long productId;
	private String productName;
	private Integer quantity;
	private Date expiryDate;
}
