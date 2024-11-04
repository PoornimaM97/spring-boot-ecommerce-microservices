package com.springboot.productservice.service.impl;

import com.springboot.productservice.dto.ProductsDto;
import com.springboot.productservice.entity.Products;
import com.springboot.productservice.exception.ResourceNotFoundException;
import com.springboot.productservice.mapper.ProductsMapper;
import com.springboot.productservice.repository.ProductsRepository;
import com.springboot.productservice.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private ProductsRepository productsRepository;

    @Override
    public ProductsDto addProduct(ProductsDto productsDto) {
        System.out.println("3");
        Products products = ProductsMapper.mapToProducts(productsDto);
        System.out.println("4");
        Products savedProduct = productsRepository.save(products);
        System.out.println("5");
        return ProductsMapper.mapToProductsDto(savedProduct);
    }

    @Override
    public List<ProductsDto> getAllProducts(String category, String keyword) {
        List<Products> productsList = new ArrayList<>();
        if(category != null && keyword !=null){
            productsList = productsRepository.findByCategoryAndNameContaining(category, keyword);
        } else if(category != null){
            productsList = productsRepository.findByCategory(category);
        } else if(keyword != null){
            productsList = productsRepository.findByNameContaining(keyword);
        } else {
            productsList = productsRepository.findAll();
        }

        if(productsList.isEmpty()){
            throw new ResourceNotFoundException("The products does not exists");
        } else {
            return productsList.stream().map((product) ->
                    ProductsMapper.mapToProductsDto(product)).collect(Collectors.toList());
        }
    }

    @Override
    public ProductsDto getProductsById(Long id) {
        Products products = productsRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("The products does not exists with id: "+ id));
        return ProductsMapper.mapToProductsDto(products);
    }
}
