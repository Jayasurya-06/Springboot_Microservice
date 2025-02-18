package com.jk.microservices.product.service;

import com.jk.microservices.product.dto.ProductRequest;
import com.jk.microservices.product.dto.ProductResponse;
import com.jk.microservices.product.model.Product;
import com.jk.microservices.product.respository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
//                .skuCode(productRequest.skuCode())
                .price(productRequest.price())
                .build();

        log.info("product created successfully");
        productRepository.save(product);
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());


    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(product -> new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice())).toList();
    }
}
