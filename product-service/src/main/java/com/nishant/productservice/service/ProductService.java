package com.nishant.productservice.service;

import java.util.List;

import com.nishant.productservice.dto.ProductRequest;
import com.nishant.productservice.dto.ProductResponse;

public interface ProductService {
	void createProduct(ProductRequest productRequest);

	List<ProductResponse> getAllProducts();
}
