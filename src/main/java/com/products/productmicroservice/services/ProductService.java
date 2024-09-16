package com.products.productmicroservice.services;

import com.products.productmicroservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    public Page<Product> getAllProducts(int pageNo, int pageSize, String sort);

    public Product getProductById(long id);

    public Product createProduct(String title,String description,Double price,String image,String category);
}
