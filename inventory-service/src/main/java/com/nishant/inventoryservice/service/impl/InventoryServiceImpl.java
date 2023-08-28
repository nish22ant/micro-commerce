package com.nishant.inventoryservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nishant.inventoryservice.dto.InventoryResponse;
import com.nishant.inventoryservice.repository.InventoryRepository;
import com.nishant.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

	private final InventoryRepository inventoryRepository;

	@Override
	public List<InventoryResponse> isInStock(List<String> skuCodes) {
		return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
				.map(inventory -> InventoryResponse.builder()
									.skuCode(inventory.getSkuCode())
									.inInStock(inventory.getQuantity() > 0)
									.build())
				.toList();
	}

}
