package com.example.springboot.services;

import com.example.springboot.controllers.ProductController;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(ProductModel p) {
        productRepository.save(p);
    }

}
