package com.products.productmicroservice.services;

import com.products.productmicroservice.dtos.ThirdPartyProductDto;
import com.products.productmicroservice.models.Category;
import com.products.productmicroservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.*;
//import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service("ThirdPartyService")
public class ThirdPartyProductServiceImpl implements ProductService{


    WebClient webClient;

    RedisTemplate redisTemplate;

    @Autowired
    ThirdPartyProductServiceImpl(WebClient webClient){
        this.webClient = webClient;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Page<Product> getAllProducts(int pageNum, int pageSize, String sortBy) {

        List<ThirdPartyProductDto> thirdPartyProductDtos = webClient.get()
                .uri("https://fakestoreapi.com/products/")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ThirdPartyProductDto>>() {})
                .block();
        List<Product> products = new ArrayList<>();
        Product product;
        for (ThirdPartyProductDto thirdPartyProductDto : thirdPartyProductDtos) {
            product = convertThirdPartyProductDtoToProduct(thirdPartyProductDto);
            products.add(product);
        }

        Pageable pageable = PageRequest.of(pageNum,pageSize, Sort.by(sortBy));
        return new PageImpl<>(products,pageable,products.size());
    }

    @Override
    public Product getProductById(long id) {

        ThirdPartyProductDto cachedDtoProduct = (ThirdPartyProductDto) redisTemplate.opsForHash().get("PRODUCT", String.valueOf("product_"+id));
        if(cachedDtoProduct != null){
            return convertThirdPartyProductDtoToProduct(cachedDtoProduct);
        }
        ThirdPartyProductDto thirdPartyProductDto = webClient.get().uri("https://fakestoreapi.com/products/"+id).retrieve().bodyToMono(ThirdPartyProductDto.class).block();
        redisTemplate.opsForHash().put("PRODUCT", String.valueOf("product_"+id), thirdPartyProductDto);
        return convertThirdPartyProductDtoToProduct(thirdPartyProductDto);
    }

    @Override
    public Product createProduct(String title, String description, Double price, String image, String category) {
        return null;
    }

    private Product convertThirdPartyProductDtoToProduct(ThirdPartyProductDto thirdPartyProductDto) {
        Product product = new Product();
        product.setId(thirdPartyProductDto.getId());
        product.setTitle(thirdPartyProductDto.getTitle());
        product.setDescription(thirdPartyProductDto.getDescription());
        product.setPrice(thirdPartyProductDto.getPrice());
        Category category = new Category();
        category.setName(thirdPartyProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
