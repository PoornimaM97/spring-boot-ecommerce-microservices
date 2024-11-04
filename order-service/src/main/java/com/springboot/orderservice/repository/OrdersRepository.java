package com.springboot.orderservice.repository;

import com.springboot.orderservice.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    List<Orders> findByUserId(Long usedId);
}
