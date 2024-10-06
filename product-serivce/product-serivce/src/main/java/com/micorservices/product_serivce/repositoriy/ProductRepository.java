package com.micorservices.product_serivce.repositoriy;

import com.micorservices.product_serivce.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
