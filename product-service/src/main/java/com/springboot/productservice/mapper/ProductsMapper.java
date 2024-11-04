package com.springboot.productservice.mapper;

import com.springboot.productservice.dto.ProductsDto;
import com.springboot.productservice.entity.Products;

public class ProductsMapper {

    public static Products mapToProducts(ProductsDto productsDto){
        return new Products(
                productsDto.getId(),
                productsDto.getName(),
                productsDto.getDescription(),
                productsDto.getPrice(),
                productsDto.getCategory(),
                productsDto.getQuantity()
        );
    }

    public static ProductsDto mapToProductsDto(Products products){
        return new ProductsDto(
                products.getId(),
                products.getName(),
                products.getDescription(),
                products.getPrice(),
                products.getCategory(),
                products.getQuantity()
        );
    }
}
