package com.products.productmicroservice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    private double price;

    private String image;

    @ManyToOne
    private Category category;
}


//#1
//Without Cardinality Mapping it'll be not created.

//#2
//Only mention OneMany and ManytoOne without using MappedBy - creates mapping table and categoryid at product table.

//#3
//ManyToOne do not allow mappedBy. Because that mean -> The table value need to mapped with multiple products.
