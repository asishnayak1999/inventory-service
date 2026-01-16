package com.korber.inventoryservice.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryHandlerFactory {
	@Autowired
	private InventoryHandler handler;

	public InventoryHandler getHandler() {
		return handler;
	}
}
