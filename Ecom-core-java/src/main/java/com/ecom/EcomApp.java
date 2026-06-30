package com.ecom;

import com.ecom.controller.CustomerController;
import com.ecom.controller.ProductController;
import com.ecom.dto.CustomerDto;
import com.ecom.dto.FilterDto;
import com.ecom.dto.ProductDto;
import com.ecom.enums.SortDirection;
import com.ecom.model.Category;
import com.ecom.model.Product;

import java.util.List;
import java.util.Map;

public class EcomApp {
    public static void main(String[] args) {
        ProductController productController = new ProductController();
        List<Product> list = productController.getProductsWithCategoryAndSellerInfo();
       /*
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

        list.forEach(System.out::println);

        /*
        In a given dataset :
        we want to offer filter options to the end user:
        1. Filter by one of more category
        2. Filter by one or more sellers
        3. Filter by particular price(1000) / less than / greater than
        * */
//        List<String> listCategoryNames = List.of("Electronics", "Books");  //first filter
//        List<String> listSellerNames = List.of("Tech World", "Fashion Hub");
//
//        double startPrice = 1000;
//        double endPrice = 2000;
        /*
        FilterDto filterDto = new FilterDto(
                List.of("Electronics", "Books"),
                List.of("Tech World", "Fashion Hub"),
                500,
                2000
        );
        System.out.println("-------------------Filtered List-----------------------");
        List<Product> filteredList =  productController.filterByCriteria(list, filterDto);
        filteredList.forEach(System.out::println);
        */

        System.out.println("-----------Number of Products for each seller-------");
        Map<String,Integer> mapProductsBySeller =  productController.getProductsForEachSeller(list);
        mapProductsBySeller.forEach((key,value)->
                System.out.println(key + "        " + value));

        System.out.println("-----------Number of Products for each category-------");
        Map<String,Integer>  mapProductByCategory =  productController.getProductsByCategory(list);
        mapProductByCategory.forEach((key,value)-> System.out.println(key + "  " + value));
    }
}
