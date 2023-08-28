package com.nishant.inventoryservice.service;

import java.util.List;

import com.nishant.inventoryservice.dto.InventoryResponse;

public interface InventoryService {
	public List<InventoryResponse>  isInStock(List<String> skuCode);
}
