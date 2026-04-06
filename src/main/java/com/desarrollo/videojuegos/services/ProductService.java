package com.desarrollo.videojuegos.services;

import com.desarrollo.videojuegos.models.Product;
import com.desarrollo.videojuegos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product saveProduct(Product product){
        return this.productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return this.productRepository.findById(id);
    }

    public void deleteProduct(Long id){
        this.productRepository.deleteById(id);
    }
}
