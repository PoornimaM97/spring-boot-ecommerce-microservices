package com.springboot.productservice.controller;

import com.springboot.productservice.dto.ProductsDto;
import com.springboot.productservice.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductsController {

    private ProductsService productsService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductsDto> addProduct(@RequestBody ProductsDto productsDto){
        System.out.println("1");
        ProductsDto products = productsService.addProduct(productsDto);
        System.out.println("2");
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductsDto>> getAllProducts(@RequestParam(value = "category", required = false) String category,
                                                            @RequestParam(value = "keyword", required = false) String keyword){
        List<ProductsDto> productList = productsService.getAllProducts(category, keyword);
        return ResponseEntity.ok(productList);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductsDto> getProductsById(@PathVariable Long id){
        ProductsDto products = productsService.getProductsById(id);
        return ResponseEntity.ok(products);
    }

}
