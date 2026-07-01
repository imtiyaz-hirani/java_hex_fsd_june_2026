package com.ecom.service;

import com.ecom.dto.FilterDto;
import com.ecom.dto.ProductDto;
import com.ecom.enums.SortDirection;
import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.impl.ProductRepositoryImpl;
import com.ecom.utility.ProductSortUtility;

import java.util.*;
import java.util.stream.Collectors;

public class ProductService {
    // Reach out to Repository using polymorphic object
    // Super-interface ref = Sub-class object

    ProductRepository productRepository = new ProductRepositoryImpl();

    public List<Product> getProductsWithCategoryAndSellerInfo() {
        return productRepository.getProductsWithCategoryAndSellerInfo();
    }

    public List<ProductDto> getProductsWithCategoryAndSellerInfoWithDto() {
        return productRepository.getProductsWithCategoryAndSellerInfoWithDto();
    }

    public List<ProductDto> getProductsSortedByPrice(List<ProductDto> list, SortDirection sortDirection) {
        // Collections.sort(list, new ProductSortUtility(sortDirection));
        list.sort(new ProductSortUtility(sortDirection)); //[dto1,dto2,dto3]
        return list;
    }

    public List<Product> filterByCriteria(List<Product> list, FilterDto filterDto) { //[p1,p2,p3,p4,p5]
        return
        list.stream()
                .filter(p-> filterDto.listCategoryNames().contains(p.getCategory().getName()))
                .filter(p->filterDto.listSellerNames().contains(p.getSeller().getName()))
                .filter(p-> (p.getPrice() <= filterDto.endPrice() && p.getPrice() >= filterDto.startPrice()))
                .toList();
    }

    public Map<String, Integer> getProductsForEachSeller(List<Product> list) { //[p1,p2,p3,p4]
        Map<String, Integer> outMap = new HashMap<>();

        list.stream()
                .collect(Collectors.groupingBy(p -> p.getSeller().getName()))
                .forEach((key, value) -> outMap.put(key, value.size()));

        return outMap;
    }

    public Map<String, Integer> getProductsByCategory(List<Product> list) {
        Map<String, Integer> outMap = new HashMap<>();

        list.stream()
                .collect(Collectors.groupingBy(p-> p.getCategory().getName()))
                .forEach((key, value) -> outMap.put(key,value.size() ));

        return outMap;
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

    public List<String> getCategoryNames(List<Product> list) {
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
}
