package com.ecom.controller;

import com.ecom.dto.ProductDto;
import com.ecom.model.Product;
import com.ecom.service.ProductService;

import java.util.List;

public class ProductController {

    // Reaching out to ProductService class
    ProductService productService = new ProductService();

    public List<Product> getProductsWithCategoryAndSellerInfo(){
        return productService.getProductsWithCategoryAndSellerInfo();
    }

    public List<ProductDto> getProductsWithCategoryAndSellerInfoWithDto(){
        return productService.getProductsWithCategoryAndSellerInfoWithDto();
    }
}
