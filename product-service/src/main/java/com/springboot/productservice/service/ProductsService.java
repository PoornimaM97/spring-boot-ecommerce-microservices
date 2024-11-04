package com.springboot.productservice.service;

import com.springboot.productservice.dto.ProductsDto;

import java.util.List;

public interface ProductsService {

    ProductsDto addProduct(ProductsDto productsDto);

    List<ProductsDto> getAllProducts(String category, String keyword);

    ProductsDto getProductsById(Long id);
}
