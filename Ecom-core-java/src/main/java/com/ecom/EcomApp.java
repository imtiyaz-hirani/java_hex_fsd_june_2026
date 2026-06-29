package com.ecom;

import com.ecom.controller.ProductController;
import com.ecom.dto.ProductDto;
import com.ecom.model.Product;

import java.util.List;

public class EcomApp {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        //List<Product> list = productController.getProductsWithCategoryAndSellerInfo();
        List<ProductDto> list = productController.getProductsWithCategoryAndSellerInfoWithDto();
        System.out.println("----------------Product Info with Category & Seller with Dto-------------");
        list.forEach(System.out :: println);
    }
}
