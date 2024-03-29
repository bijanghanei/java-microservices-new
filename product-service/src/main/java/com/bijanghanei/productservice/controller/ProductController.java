package com.bijanghanei.productservice.controller;

import com.bijanghanei.productservice.dto.ProductRequest;
import com.bijanghanei.productservice.dto.ProductResponse;
import com.bijanghanei.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private  final ProductService productService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
