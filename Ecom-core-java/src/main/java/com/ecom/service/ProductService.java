package com.ecom.service;

import com.ecom.dto.FilterDto;
import com.ecom.dto.ProductDto;
import com.ecom.enums.SortDirection;
import com.ecom.model.Product;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.impl.ProductRepositoryImpl;
import com.ecom.utility.ProductSortUtility;

import java.util.Collections;
import java.util.List;

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
}
