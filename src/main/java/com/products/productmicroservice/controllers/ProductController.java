package com.products.productmicroservice.controllers;

import com.products.productmicroservice.dtos.CreateProductRequestDto;
import com.products.productmicroservice.models.Product;
import com.products.productmicroservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequestMapping("/products")
public class ProductController {

    ProductService productService;
    WebClient webClient;

    @Autowired
    public ProductController(@Qualifier("ThirdPartyService")ProductService productService, WebClient webClient) {
        this.productService = productService;
        this.webClient = webClient;
    }

    @GetMapping("/hello")
    public String hello(){
       return "Welcome to products microservice";
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") long productId){

        Product product = productService.getProductById(productId);
        return ResponseEntity.ok().body(product);
    }

    //Requestparam values send via url by user
    @GetMapping("/all")
    public ResponseEntity<Page> getAllProducts(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
                                               @RequestParam(value="pageSize",defaultValue = "5") int pageSize, String sortBy){
        int noOfItems = Math.min(pageSize,10);
        Page productsPage = productService.getAllProducts(pageNum,noOfItems,sortBy);
        return ResponseEntity.ok().body(productsPage);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDto requestDto){
        Product product = productService.createProduct(requestDto.getTitle(),requestDto.getDescription(),
                requestDto.getPrice(),requestDto.getImage(),requestDto.getCategoryName());

        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }
}
