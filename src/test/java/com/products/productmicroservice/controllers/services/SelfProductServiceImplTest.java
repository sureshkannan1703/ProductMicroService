package com.products.productmicroservice.controllers.services;

import com.products.productmicroservice.models.Product;
import com.products.productmicroservice.repositories.ProductRepository;
import com.products.productmicroservice.services.ProductService;
import com.products.productmicroservice.services.SelfProductServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class SelfProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    SelfProductServiceImpl selfProductServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(SelfProductServiceImplTest.class);

    @BeforeAll
    public static void initiation() {
        String startTime = String.valueOf(System.currentTimeMillis());
        logger.info("Execution Time : {}",startTime);
    }

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductById_returnsCorrectProduct(){

        Product product = new Product();
        long productId = 1;
        product.setId(productId);
        product.setTitle("Bat");
        product.setDescription("Bat");
        product.setPrice(898.98);

        when(productRepository.findProductById(productId)).thenReturn(product);
        Product prd = selfProductServiceImpl.getProductById(productId);
        assertEquals(prd.getTitle(), product.getTitle());
    }

    public void testGetProductById_returnsNullProduct(){
        long productId = 1;
        when(productRepository.findProductById(productId)).thenReturn(null);
        Product prd = selfProductServiceImpl.getProductById(productId);

        assert.assertEquals(null, prd);
    }
}
