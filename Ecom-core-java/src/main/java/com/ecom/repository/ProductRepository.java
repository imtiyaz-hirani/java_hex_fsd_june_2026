package com.ecom.repository;

import com.ecom.dto.ProductDto;
import com.ecom.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getProductsWithCategoryAndSellerInfo();

    List<ProductDto> getProductsWithCategoryAndSellerInfoWithDto();
}
