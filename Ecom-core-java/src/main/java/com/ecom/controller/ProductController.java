package com.ecom.controller;

import com.ecom.dto.FilterDto;
import com.ecom.dto.ProductDto;
import com.ecom.enums.SortDirection;
import com.ecom.model.Product;
import com.ecom.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductController {

    // Reaching out to ProductService class
    ProductService productService = new ProductService();

    public List<Product> getProductsWithCategoryAndSellerInfo(){
        return productService.getProductsWithCategoryAndSellerInfo();
    }

    public List<ProductDto> getProductsWithCategoryAndSellerInfoWithDto(){
        return productService.getProductsWithCategoryAndSellerInfoWithDto();
    }


    public List<ProductDto> getProductsSortedByPrice(SortDirection sortDirection) {
        List<ProductDto> list = productService.getProductsWithCategoryAndSellerInfoWithDto();
        return productService.getProductsSortedByPrice(list, sortDirection);
    }

    public List<String> getProductTitlesWithoutStreams(List<Product> list) {
       return productService.getProductTitlesWithoutStreams(list);

    }

    public List<String> getProductTitles(List<Product> list) {
        return productService.getProductTitles(list);
     }

    public List<String> getCategoryNames(List<Product> list) { //[p1,p2,p3,p4]
        return productService.getCategoryNames(list);
    }

    public List<String> getSellerNames(List<Product> list) {
        return productService.getSellerNames(list);

    }

    public List<Product> filterByCriteria(List<Product> list, FilterDto filterDto) {
        return productService.filterByCriteria(list,filterDto);
    }


    public Map<String, Integer> getProductsForEachSeller(List<Product> list) {
        return productService.getProductsForEachSeller(list);
    }

    public Map<String, Integer> getProductsByCategory(List<Product> list) {
        return productService.getProductsByCategory(list);
    }
}
