package com.java_app.service;

import com.java_app.model.Product;
import com.java_app.repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    ProductRepository productRepository = new ProductRepository();

    public List<Product> getAllProducts() throws SQLException {
        /*
          what do i do?? -- i need to fetch records from DB
          but this is job of repository
        */
        List<Product> list = productRepository.getAllProducts();
        return list;
    }


    public List<Product> getAllProductsByCategory(String category) throws SQLException {
        return productRepository.getAllProductsByCategory(category);
    }
}
