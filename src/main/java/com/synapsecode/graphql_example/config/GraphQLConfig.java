package com.synapsecode.graphql_example.config;

import com.example.demo.model.Product;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLConfig implements GraphQLQueryResolver, GraphQLMutationResolver {

    @Autowired
    private ProductResolver productResolver;

    public List<Product> products() {
        return productResolver.getProducts();
    }

    public Product product(Long id) {
        return productResolver.getProduct(id);
    }

    public Product createProduct(String name, String description, double price) {
        return productResolver.createProduct(name, description, price);
    }

    public Product updateProduct(Long id, String name, String description, double price) {
        return productResolver.updateProduct(id, name, description, price);
    }

    public boolean deleteProduct(Long id) {
        return productResolver.deleteProduct(id);
    }
}