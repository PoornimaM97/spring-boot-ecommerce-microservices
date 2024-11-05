package com.springboot.productservice.service.impl;

import com.springboot.productservice.dto.ProductsDto;
import com.springboot.productservice.entity.Products;
import com.springboot.productservice.exception.ResourceNotFoundException;
import com.springboot.productservice.mapper.ProductsMapper;
import com.springboot.productservice.repository.ProductsRepository;
import com.springboot.productservice.service.ProductsService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.lang.module.ResolutionException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductsServiceImpl.class);

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

    //@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultProducts")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultProducts")
    @Override
    public ProductsDto getProductsById(Long id) {
        LOGGER.info("Inside getProductsById() method");
        Products products = productsRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("The products does not exists with id: "+ id));
        return ProductsMapper.mapToProductsDto(products);
    }

    public ProductsDto getDefaultProducts(Long id, Exception exception) {
        LOGGER.info("Inside getDefaultProducts() method");
        Products products = productsRepository.findById(id).get();
        Products product = new Products();
        product.setName("Item");
        product.setDescription("Items");
        product.setCategory("Tech");
        product.setQuantity(2);
        product.setPrice(2000.0);
        return ProductsMapper.mapToProductsDto(product);
    }
}
