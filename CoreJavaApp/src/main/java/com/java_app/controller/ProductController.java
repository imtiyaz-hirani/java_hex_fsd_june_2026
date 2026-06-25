package com.java_app.controller;

import com.java_app.model.Product;
import com.java_app.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductController {
    public static void main(String[] args) {
        // take input -- give output
        System.out.println("-------------All Products-------------");
        // Reach out to Service : Create an Object
        ProductService productService = new ProductService();

        try {
            List<Product> list =  productService.getAllProducts();
            list.forEach(System.out :: println);
        } catch (SQLException e) {
            System.out.println("Operation Failed " + e.getMessage());
        }
        System.out.println("-----------------------------------------");
        System.out.println("------ Products By Category-------------");
        String category = "";
        try {
            List<Product> list = productService.getAllProductsByCategory(category);
            list.forEach(System.out :: println);
        } catch (SQLException e) {
            System.out.println("Operation Failed " + e.getMessage());
        }
        System.out.println("-----------------------------------------");

    }
}
