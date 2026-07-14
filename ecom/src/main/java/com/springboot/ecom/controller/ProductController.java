package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.ProductReqDto;
import com.springboot.ecom.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
