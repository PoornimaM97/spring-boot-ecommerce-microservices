package com.springboot.orderservice.mapper;

import com.springboot.orderservice.dto.OrdersDto;
import com.springboot.orderservice.entity.Orders;

public class OrdersMapper {

    public static Orders mapToOrders(OrdersDto ordersDto){
        return new Orders(
                ordersDto.getId(),
                ordersDto.getUserId(),
                ordersDto.getProductId(),
                ordersDto.getQuantity(),
                ordersDto.getTotalPrice(),
                ordersDto.getStatus(),
                ordersDto.getCreatedAt()
        );
    }

    public static OrdersDto mapToOrdersDto(Orders orders){
        return new OrdersDto(
                orders.getId(),
                orders.getUserId(),
                orders.getProductId(),
                orders.getQuantity(),
                orders.getTotalPrice(),
                orders.getStatus(),
                orders.getCreatedAt()
        );
    }
}
