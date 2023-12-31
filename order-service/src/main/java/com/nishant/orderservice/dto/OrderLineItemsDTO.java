package com.nishant.orderservice.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderLineItemsDTO {
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
}
