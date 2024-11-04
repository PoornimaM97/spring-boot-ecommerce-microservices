package com.springboot.orderservice.service.impl;

import com.springboot.orderservice.dto.OrdersDto;
import com.springboot.orderservice.dto.ProductDto;
import com.springboot.orderservice.dto.UserDto;
import com.springboot.orderservice.entity.OrderStatus;
import com.springboot.orderservice.entity.Orders;
import com.springboot.orderservice.exception.ResourceNotFoundException;
import com.springboot.orderservice.mapper.OrdersMapper;
import com.springboot.orderservice.repository.OrdersRepository;
import com.springboot.orderservice.service.*;
import com.springboot.orderservice.service.UserAPIClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private OrdersRepository ordersRepository;

    //private RestTemplate restTemplate;

    //private WebClient webClient;

    private UserAPIClient userAPIClient;

    private ProductAPIClient productAPIClient;

    private AuthService authService;

    private static final String USER_SERVICE_URL = "http://localhost:8080/api/user/";

    private static final String PRODUCT_SERVICE_URL = "http://localhost:8083/api/products";

    @Override
    public OrdersDto addOrders(OrdersDto ordersDto, String jwtToken) {
        // Fetch user from User Service
        System.out.println("3");
        System.out.println("user id:"+ ordersDto.getUserId());
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + jwtToken.substring(7));
//        System.out.println("Token: "+ jwtToken);
//        System.out.println("Headers: "+ headers.toString());
//
//        HttpEntity<UserDto> entity = new HttpEntity<>(headers);
//        ResponseEntity<UserDto> user = restTemplate.exchange(
//                USER_SERVICE_URL + ordersDto.getUserId(),
//                HttpMethod.GET,
//                entity,
//                UserDto.class
//        );

//        UserDto user = webClient.get().uri(USER_SERVICE_URL + ordersDto.getUserId())
//                .header("Authorization", "Bearer " + jwtToken.substring(7))
//                .header("Content-Type", "application/json")
//                .retrieve().bodyToMono(UserDto.class)
//                .block();

        authService.setJwtToken(jwtToken.substring(7));

        UserDto user = userAPIClient.getUserByUserId(ordersDto.getUserId());

        if (user == null) {
            throw new IllegalArgumentException("Invalid user ID.");
        }
        System.out.println("4");
        // Fetch product from Product Service
        //ProductDto product = restTemplate.getForObject(PRODUCT_SERVICE_URL + "/" + ordersDto.getProductId(), ProductDto.class);
//        ProductDto product = webClient.get().uri(PRODUCT_SERVICE_URL + "/" + ordersDto.getProductId())
//                .retrieve()
//                .bodyToMono(ProductDto.class)
//                .block();

        ProductDto product = productAPIClient.getProductsById(ordersDto.getProductId());

        if (product == null) {
            throw new IllegalArgumentException("Invalid product ID.");
        }
        // Calculate the total price based on product price and quantity
        double totalPrice = product.getPrice() * ordersDto.getQuantity();
        System.out.println("5");
        // Create a new order entity
        Orders order = new Orders();
        order.setUserId(ordersDto.getUserId());
        order.setProductId(ordersDto.getProductId());
        order.setQuantity(ordersDto.getQuantity());
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        // Save the order to the database
        Orders savedOrder = ordersRepository.save(order);
        System.out.println("6");
        // Convert the saved order to DTO and return
        return OrdersMapper.mapToOrdersDto(savedOrder);
    }

    @Override
    public OrdersDto getOrderById(Long id) {
        Orders orders = ordersRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("order does not exists with id: "+ id));
        return OrdersMapper.mapToOrdersDto(orders);
    }

    @Override
    public List<OrdersDto> getOrdersByUserId(Long userId) {
        List<Orders> orders = ordersRepository.findByUserId(userId);
        return orders.stream().map((order)
                 -> OrdersMapper.mapToOrdersDto(order)).collect(Collectors.toList());
    }
}
