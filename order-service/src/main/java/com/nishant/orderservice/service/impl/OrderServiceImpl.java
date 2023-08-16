package com.nishant.orderservice.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nishant.orderservice.dto.OrderRequest;
import com.nishant.orderservice.model.Order;
import com.nishant.orderservice.model.OrderLineItems;
import com.nishant.orderservice.repository.OrderRepository;
import com.nishant.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	
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
		
		orderRepository.save(order);
		
	}
}
