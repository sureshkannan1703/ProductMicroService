package com.products.productmicroservice.repositories;

import com.products.productmicroservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product findProductById(long id);

    public Page findAll(Pageable pageable);


}
