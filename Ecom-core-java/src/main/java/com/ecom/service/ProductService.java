package com.ecom.service;

import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.impl.ProductRepositoryImpl;

import java.util.List;

public class ProductService {
    // Reach out to Repository using polymorphic object
    // Super-interface ref = Sub-class object

    ProductRepository productRepository = new ProductRepositoryImpl();

    public List<Product> getProductsWithCategoryAndSellerInfo() {
        return productRepository.getProductsWithCategoryAndSellerInfo();
    }
}
