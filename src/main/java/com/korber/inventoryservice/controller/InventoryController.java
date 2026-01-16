package com.korber.inventoryservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.korber.inventoryservice.dto.InventoryReserveResponse;
import com.korber.inventoryservice.dto.InventoryResponse;
import com.korber.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryService service;

	@GetMapping("/{productId}")
	public InventoryResponse get(@PathVariable(name = "productId") Long productId) {
		return service.getInventory(productId);
	}

	@PostMapping("/update")
	public InventoryReserveResponse update(@RequestBody Map<String, Integer> request) {
		return service.reserve(request.get("productId").longValue(), request.get("quantity"));
	}
}
