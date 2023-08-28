package com.nishant.inventoryservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.inventoryservice.dto.InventoryResponse;
import com.nishant.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
	
	private final InventoryService inventoryService;
	
	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<InventoryResponse>  inStock(@RequestParam List<String> skuCodes) {
		return inventoryService.isInStock(skuCodes);
	}
	
}
