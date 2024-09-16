package com.products.productmicroservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "category")   //Product table will be have categoryid.
    @JsonIgnore                         //It helps to avoid recursive JSON object serialization.
    List<Product> products;
}
