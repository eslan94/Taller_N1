package com.desarrollo.videojuegos.repository;

import com.desarrollo.videojuegos.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
