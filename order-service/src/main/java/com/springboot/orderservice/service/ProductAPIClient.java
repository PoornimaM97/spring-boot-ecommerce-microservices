package com.springboot.orderservice.service;

import com.springboot.orderservice.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductAPIClient {

    @GetMapping("api/products/{id}")
    ProductDto getProductsById(@PathVariable Long id);

}
