package com.ecom;

import com.ecom.controller.ProductController;
import com.ecom.dto.ProductDto;
import com.ecom.enums.SortDirection;
import com.ecom.model.Product;

import java.util.List;

public class EcomApp {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        //List<Product> list = productController.getProductsWithCategoryAndSellerInfo();
        List<ProductDto> list = productController.getProductsWithCategoryAndSellerInfoWithDto();
        System.out.println("----------------Product Info with Category & Seller with Dto-------------");
        list.forEach(System.out :: println);

        System.out.println("-----------Sorted List by Price: ASC>>>>>>>>");
        List<ProductDto> sortedListPriceAsc = productController.getProductsSortedByPrice(SortDirection.ASC);
        sortedListPriceAsc.forEach(System.out :: println);
        System.out.println("-----------Sorted List by Price: DESC>>>>>>>>");
        List<ProductDto> sortedListPriceDesc = productController.getProductsSortedByPrice(SortDirection.DESC);
        sortedListPriceDesc.forEach(System.out :: println);
    }
}
