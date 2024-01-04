package com.bijanghanei.productservice.repository;

import com.bijanghanei.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
