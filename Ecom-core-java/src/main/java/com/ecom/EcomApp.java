package com.ecom;

import com.ecom.controller.CustomerController;
import com.ecom.controller.ProductController;
import com.ecom.dto.CustomerDto;
import com.ecom.dto.ProductDto;
import com.ecom.enums.SortDirection;
import com.ecom.model.Product;

import java.util.List;

public class EcomApp {
    public static void main(String[] args) {
       /*
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

        CustomerController customerController = new CustomerController();
        int productId = 1;
        List<CustomerDto> list = customerController.fetchCustomerDetailsByProductWithCatAndSellerInfoWithDto(productId);
        list.forEach(System.out :: println);
        System.out.println("-------------Sorted as per date DESC order-------");
        List<CustomerDto> sortedList = customerController.sortCustomerListByPurchaseDate(SortDirection.DESC, productId);
        sortedList.forEach(System.out :: println);
        */
        ProductController productController = new ProductController();
        List<Product> list = productController.getProductsWithCategoryAndSellerInfo();

        System.out.println("---------Display Product Titles---------");
        List<String> productTitleList = productController.getProductTitles(list);
        productTitleList.forEach(System.out:: println);

        System.out.println("---------Display Categories---------");
        List<String> categoryNameList = productController.getCategoryNames(list);
        categoryNameList.forEach(System.out:: println);

        System.out.println("---------Display Seller ---------");
        List<String> sellerNameList = productController.getSellerNames(list);
        sellerNameList.forEach(System.out:: println);
    }
}
