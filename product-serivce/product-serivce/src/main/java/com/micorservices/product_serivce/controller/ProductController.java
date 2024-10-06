package com.micorservices.product_serivce.controller;



import com.micorservices.product_serivce.Service.ProductService;
import com.micorservices.product_serivce.dto.ProductRequest;
import com.micorservices.product_serivce.dto.ProductResponse;
import com.micorservices.product_serivce.repositoriy.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService  productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
     productService.createProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
