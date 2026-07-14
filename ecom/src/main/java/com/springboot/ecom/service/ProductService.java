package com.springboot.ecom.service;

import com.springboot.ecom.dto.request.ProductReqDto;
import com.springboot.ecom.exception.ResourceNotFoundException;
import com.springboot.ecom.mapper.ProductMapper;
import com.springboot.ecom.model.Category;
import com.springboot.ecom.model.Product;
import com.springboot.ecom.model.Seller;
import com.springboot.ecom.repository.CategoryRepository;
import com.springboot.ecom.repository.ProductRepository;
import com.springboot.ecom.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public void insert(long sellerId,
                       long categoryId,
                       ProductReqDto productReqDto) {

        // Step 1: Fetch Seller from given sellerId
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow();

        // Step 2: Fetch Category from given categoryId
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category id invalid."));

        // Step 3: Map productReqDto to Product entity
        Product product = ProductMapper.convertDtoToEntity(productReqDto);

        // Step 4: Attach seller obj and category obj to product
        product.setSeller(seller);
        product.setCategory(category);

        // Step 5: Save product in DB
        productRepository.save(product);
    }
}
