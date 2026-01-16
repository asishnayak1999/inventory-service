package com.korber.inventoryservice.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BatchDTO {
	private Long batchId;
	private Integer quantity;
	private Date expiryDate;
}
