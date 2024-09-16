package com.products.productmicroservice.services;

import com.products.productmicroservice.models.Category;
import com.products.productmicroservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(String categoryName) {
            Category category = new Category();
            category.setName(categoryName);
            return categoryRepository.save(category);
    }
}
