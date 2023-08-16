package com.nishant.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nishant.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
