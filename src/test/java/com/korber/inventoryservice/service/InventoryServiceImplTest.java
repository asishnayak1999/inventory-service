package com.korber.inventoryservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.korber.inventoryservice.dto.InventoryReserveResponse;
import com.korber.inventoryservice.exception.InsufficientStockException;
import com.korber.inventoryservice.factory.InventoryHandler;
import com.korber.inventoryservice.factory.InventoryHandlerFactory;
import com.korber.inventoryservice.model.InventoryBatch;
import com.korber.inventoryservice.repository.InventoryRepository;
import com.korber.inventoryservice.serviceImpl.InventoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceImplTest {

	@InjectMocks
	private InventoryServiceImpl serviceImpl;

	@Mock
	private InventoryRepository repository;

	@Mock
	private InventoryHandlerFactory factory;

	@Mock
	private InventoryHandler handler;

	private List<InventoryBatch> batches;

	@BeforeEach
	void setup() {

		batches = List.of(new InventoryBatch(1L, 1005L, "Smartwatch", 5, new java.sql.Date(System.currentTimeMillis())),

				new InventoryBatch(2L, 1005L, "Smartwatch", 7, new java.sql.Date(System.currentTimeMillis())),

				new InventoryBatch(3L, 1005L, "Smartwatch", 40, new java.sql.Date(System.currentTimeMillis())));
	}

	@Test
	void testReserveSuccess() {

		when(repository.findByProductIdOrderByExpiryDateAsc(1005L)).thenReturn(batches);

		when(factory.getHandler()).thenReturn(handler);

		when(handler.updateStock(anyList(), eq(10))).thenReturn(List.of(1L, 2L));

		InventoryReserveResponse resp = serviceImpl.reserve(1005L, 10);

		assertNotNull(resp);
		assertEquals(1005L, resp.getProductId());
		assertEquals("Smartwatch", resp.getProductName());
		assertEquals(10, resp.getQuantity());
		assertEquals("RESERVED", resp.getStatus());
		assertEquals(2, resp.getUsedBatchIds().size());

		verify(repository).saveAll(anyList());
	}

	@Test
	void testOutOfStock() {

		when(repository.findByProductIdOrderByExpiryDateAsc(1005L)).thenReturn(batches);

		when(factory.getHandler()).thenReturn(handler);

		when(handler.updateStock(anyList(), eq(100))).thenThrow(new InsufficientStockException("Insufficient stock"));

		assertThrows(InsufficientStockException.class, () -> serviceImpl.reserve(1005L, 100));

		verify(repository, never()).saveAll(any());
	}

}
