package com.jk.microservices.product.service;

import com.jk.microservices.product.dto.ProductRequest;
import com.jk.microservices.product.dto.ProductResponse;
import com.jk.microservices.product.model.Product;
import com.jk.microservices.product.respository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .skuCode(productRequest.skuCode())
                .build();

        log.info("product created successfully");
        productRepository.save(product);
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(), productRequest.skuCode(), product.getPrice());


    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().peek(System.out::println).map(product -> new ProductResponse(product.getId(),product.getName(),product.getDescription(), product.getSkuCode(), product.getPrice())).toList();
    }
}
