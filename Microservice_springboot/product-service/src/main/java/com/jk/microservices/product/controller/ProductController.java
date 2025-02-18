package com.jk.microservices.product.controller;

import com.jk.microservices.product.dto.ProductRequest;
import com.jk.microservices.product.dto.ProductResponse;
import com.jk.microservices.product.model.Product;
import com.jk.microservices.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/product")
@RestController
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody  ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }


}
