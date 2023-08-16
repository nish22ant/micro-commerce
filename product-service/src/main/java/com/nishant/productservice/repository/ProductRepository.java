package com.nishant.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nishant.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
