package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.ProductReqDto;
import com.springboot.ecom.dto.response.OrderDto;
import com.springboot.ecom.dto.response.ProductResDto;
import com.springboot.ecom.dto.response.ProductResStatDto;
import com.springboot.ecom.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add/{sellerId}/{categoryId}")
    public void insert(@PathVariable long sellerId,
                       @PathVariable long categoryId,
                       @Valid @RequestBody ProductReqDto productReqDto){

        productService.insert(sellerId, categoryId, productReqDto);
    }

    /*
    Task: Get all products by category
    Pagination : optional
    param: categoryId
     */
    @GetMapping("/by-category/{categoryId}")
     public List<ProductResDto> getByCategoryId(@PathVariable long categoryId,
                                                @RequestParam(required = false, defaultValue = "0") int page,
                                                @RequestParam(required = false, defaultValue = "50") int size){
        return productService.getByCategoryId(categoryId,page,size);
    }

    @GetMapping("/count/for-each-seller")
    public List<ProductResStatDto> getProductForEachSeller(){
        return productService.getProductForEachSeller();
    }

    @GetMapping("/purchase/by-customer")
    public List<OrderDto> getProductsPurchasedByCustomerUsername(@RequestParam String customerUsername,
                                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                                 @RequestParam(required = false, defaultValue = "5") int size){
        return productService.getProductsPurchasedByCustomerUsername(customerUsername, page,size);
    }
}
