package com.nishant.orderservice.service;

import com.nishant.orderservice.dto.OrderRequest;

public interface OrderService {
	public void placeOrder(OrderRequest orderRequest);
}
