package com.springboot.orderservice.controller;

import com.springboot.orderservice.dto.OrdersDto;
import com.springboot.orderservice.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrdersController {

    private OrdersService ordersService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrdersDto> addOrders(@RequestBody OrdersDto ordersDto,
                                               @RequestHeader("Authorization") String jwtToken){
        System.out.println("1");
        System.out.println(ordersDto.getUserId());
        OrdersDto orders = ordersService.addOrders(ordersDto, jwtToken);
        System.out.println("2");
        return new ResponseEntity<>(orders, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<OrdersDto> getOrderById(@PathVariable Long id) {
        OrdersDto order = ordersService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<OrdersDto>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrdersDto> orders = ordersService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

}
