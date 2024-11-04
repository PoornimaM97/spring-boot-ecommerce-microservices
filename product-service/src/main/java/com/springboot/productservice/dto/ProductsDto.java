package com.springboot.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductsDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private int quantity;
}
