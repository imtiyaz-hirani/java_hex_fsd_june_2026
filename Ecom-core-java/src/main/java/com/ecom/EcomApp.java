package com.ecom;

import com.ecom.controller.ProductController;
import com.ecom.model.Product;

import java.util.List;

public class EcomApp {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        List<Product> list = productController.getProductsWithCategoryAndSellerInfo();
        System.out.println("----------------Product Info with Category & Seller-------------");
        list.forEach(System.out :: println);
    }
}
