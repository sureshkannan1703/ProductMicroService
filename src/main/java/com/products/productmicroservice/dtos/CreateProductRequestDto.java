package com.products.productmicroservice.dtos;

import com.products.productmicroservice.models.Category;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class CreateProductRequestDto {

    private String title;

    private String description;

    private double price;

    private String categoryName;

    private String image;
}
