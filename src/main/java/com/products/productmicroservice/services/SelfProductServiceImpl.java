package com.products.productmicroservice.services;

import com.products.productmicroservice.models.Category;
import com.products.productmicroservice.models.Product;
import com.products.productmicroservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("SelfService")
public class SelfProductServiceImpl implements ProductService{

    ProductRepository productRepository;

    CategoryService categoryService;

    @Autowired
    public SelfProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Page<Product> getAllProducts(int pageNum, int pageSize, String sortby) {

        Sort sort = Sort.by(sortby);
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findProductById(id);
    }

    @Override
    public Product createProduct(String title, String description, Double price, String image, String categoryName) {

        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImage(image);
        Category category = categoryService.addCategory(categoryName);
        product.setCategory(category);
        return productRepository.save(product);
    }
}
