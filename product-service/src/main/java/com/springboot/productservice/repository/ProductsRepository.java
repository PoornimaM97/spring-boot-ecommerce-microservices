package com.springboot.productservice.repository;

import com.springboot.productservice.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products, Long> {

    List<Products> findByCategory(String category);

    List<Products> findByNameContaining(String keyword);

    List<Products> findByCategoryAndNameContaining(String categoty, String keyword);
}
