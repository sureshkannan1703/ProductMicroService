package com.products.productmicroservice.dtos;

import com.products.productmicroservice.models.Category;
import lombok.Data;

import java.io.Serializable;

@Data
public class ThirdPartyProductDto implements Serializable {

    private long id;

    private String title;

    private String description;

    private double price;

    private String category;   //Purpose of this class we can't directly receive Category object to Product class.

}
