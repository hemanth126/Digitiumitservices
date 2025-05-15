package com.dis.bulkorderinsertion.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dis.bulkorderinsertion.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}