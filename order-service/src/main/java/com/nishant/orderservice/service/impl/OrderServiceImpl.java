package com.nishant.orderservice.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nishant.orderservice.dto.InventoryResponse;
import com.nishant.orderservice.dto.OrderRequest;
import com.nishant.orderservice.model.Order;
import com.nishant.orderservice.model.OrderLineItems;
import com.nishant.orderservice.repository.OrderRepository;
import com.nishant.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;
	
	public void placeOrder(OrderRequest orderRequest) {
		
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDTO()
			.stream()
			.map(orderLineItemsDTO -> 
			OrderLineItems.builder()
				.price(orderLineItemsDTO.getPrice())
				.quantity(orderLineItemsDTO.getQuantity())
				.skuCode(orderLineItemsDTO.getSkuCode())
				.build()
		).toList();
		
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		order.setOrderLineItems(orderLineItems);
		
		List<String> skuCodes = order.getOrderLineItems().stream().map(orderLineItem -> orderLineItem.getSkuCode())
											.toList();
		
		// Call inventory service and place order if product is inStock
		InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
					.uri("http://INVENTORY-SERVICE/api/inventory", 
							uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
					.retrieve()
						.bodyToMono(InventoryResponse[].class)
					.block();
	
		boolean allProductInStock = Arrays.stream(inventoryResponses)
										.allMatch(inventoryResponse -> inventoryResponse.isInInStock());
		if(allProductInStock) {
			orderRepository.save(order);
		} else {
			throw new IllegalArgumentException("Products are not in stock!! Please try again later.");
		}
		
		
	}
}
