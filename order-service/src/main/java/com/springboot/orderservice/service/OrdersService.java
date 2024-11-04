package com.springboot.orderservice.service;

import com.springboot.orderservice.dto.OrdersDto;

import java.util.List;

public interface OrdersService {

    OrdersDto addOrders(OrdersDto ordersDto, String jwtToken);

    OrdersDto getOrderById(Long id);

    List<OrdersDto> getOrdersByUserId(Long userId);
}
