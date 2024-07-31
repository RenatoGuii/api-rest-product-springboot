package com.example.springboot.services;

import com.example.springboot.controllers.ProductController;
import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.exceptions.EntityNotFoundException;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(ProductModel p) {
        productRepository.save(p);
    }

    public List<ProductModel> getAllProducts() {
        List<ProductModel> listProducts = productRepository.findAll();
        if (listProducts.isEmpty()) {
            throw new EntityNotFoundException("There is no registered product");
        }
        for (ProductModel p : listProducts) {
            UUID id = p.getId();
            p.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
        }
        return listProducts;
    }

    public ProductModel getOneProduct(UUID id) {
        Optional<ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("Product Not Found Record");
        }
        return product.get();
    }

    public ProductModel updateProduct(UUID id, ProductRecordDto productRecordDto) {
        Optional <ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("There is no product with this identification");
        }
        var productModel = product.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return productModel;
    }

    public boolean deleteProduct(UUID id) {
        Optional <ProductModel> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("There is no product with this identification");
        }
        productRepository.delete(product.get());
        return true;
    }

}
