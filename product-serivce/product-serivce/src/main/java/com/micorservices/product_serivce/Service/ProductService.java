package com.micorservices.product_serivce.Service;


import com.micorservices.product_serivce.dto.ProductRequest;
import com.micorservices.product_serivce.dto.ProductResponse;
import com.micorservices.product_serivce.model.Product;
import com.micorservices.product_serivce.repositoriy.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    public final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepository.save(product);

        log.info("Product {} is created" ,product.getId());
    }


    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }


    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(product.getId() ,product.getName(),product.getDescription(),product.getPrice());
    }
}
