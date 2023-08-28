package com.nishant.productservice.service.impl;



import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nishant.productservice.dto.ProductRequest;
import com.nishant.productservice.dto.ProductResponse;
import com.nishant.productservice.model.Product;
import com.nishant.productservice.repository.ProductRepository;
import com.nishant.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;

	@Override
	public void createProduct(ProductRequest productRequest) {
		
		Product product = Product.builder()
			.name(productRequest.getName())
			.description(productRequest.getDescription())
			.price(productRequest.getPrice())
			.build();
		
		productRepository.save(product);
		log.info("Product {} is saved", product.getId());

	}

	@Override
	public List<ProductResponse> getAllProducts() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(product -> 
			ProductResponse.builder()
				.id(product.getId())
				.name(product.getName())
				.description(product.getDescription())
				.price(product.getPrice())
				.build()
		).toList();
	}

}
