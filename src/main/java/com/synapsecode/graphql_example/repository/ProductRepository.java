package com.synapsecode.graphql_example.repository;

import com.synapsecode.graphql_example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}