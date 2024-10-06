package com.micorservices.product_serivce;

import com.micorservices.product_serivce.Service.ProductService;
import com.micorservices.product_serivce.dto.ProductRequest;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.math.BigDecimal;

@SpringBootApplication
@AllArgsConstructor
@EnableDiscoveryClient
public class ProductSerivceApplication implements CommandLineRunner {

	private final ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(ProductSerivceApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		// Créer un nouveau produit
		ProductRequest productRequest = new ProductRequest("Produit Test2", "Ceci est une description de test", BigDecimal.valueOf(29.99));
		/*
		productService.createProduct(productRequest);
		 */

	}
}
