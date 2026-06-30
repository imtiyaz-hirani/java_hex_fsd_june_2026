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
        // Without streams - traditional way
        List<String> listTitles = new ArrayList<>();

        for(Product p  : list){
            if(listTitles.contains(p.getTitle()))
                continue;

            listTitles.add(p.getTitle());
        }
        return listTitles;
    }

    public List<String> getProductTitles(List<Product> list) {
        // Step 1: Convert list to Stream
        // Step 2: Perform op : map
        // Step 3: convert back to list

        return list.stream()
                .map(Product::getTitle)
                .toList();

    }

    public List<String> getCategoryNames(List<Product> list) { //[p1,p2,p3,p4]
        return list.stream()
                .map(p -> p.getCategory().getName())
                .distinct() // gets rid of repetition
                .toList();
    }

    public List<String> getSellerNames(List<Product> list) {
        return list.parallelStream()
                .map(p->p.getSeller().getName())
                .distinct()
                .toList();
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
