package com.springboot.ecom.controller;

import com.springboot.ecom.dto.request.ProductReqDto;
import com.springboot.ecom.dto.response.OrderDto;
import com.springboot.ecom.dto.response.ProductResDto;
import com.springboot.ecom.dto.response.ProductResStatDto;
import com.springboot.ecom.dto.response.UploadDto;
import com.springboot.ecom.enums.ProductPriceFilter;
import com.springboot.ecom.model.Product;
import com.springboot.ecom.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:5173")
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
                                                @RequestParam(required = false, defaultValue = "50") int size,
                                                @RequestParam(required = false, defaultValue = "NO_SORT_PRICE") ProductPriceFilter priceFilter){
        return productService.getByCategoryId(categoryId,page,size, priceFilter);
    }

    @GetMapping("/count/for-each-seller")
    public List<ProductResStatDto> getProductForEachSeller(){
        return productService.getProductForEachSeller();
    }

    @GetMapping("/purchase/by-customer")
    public List<OrderDto> getProductsPurchasedByCustomerUsername(Principal principal,
                                                                 @RequestParam(required = false, defaultValue = "0") int page,
                                                                 @RequestParam(required = false, defaultValue = "5") int size){
        // get the username of logged in user
        String customerUsername = principal.getName();
        return productService.getProductsPurchasedByCustomerUsername(customerUsername, page,size);
    }

    @PostMapping("/image/upload/{productId}")
    public UploadDto uploadImage(@PathVariable long productId,
                                 @RequestParam("pImage") MultipartFile imageFile) throws IOException {
        return productService.uploadImage(productId, imageFile);
    }

    @GetMapping("/by-seller")
    public List<ProductResDto> getProductsBySeller(Principal principal){
        String loggedInSellerUsername = principal.getName();
        return productService.getProductsBySeller(loggedInSellerUsername);
    }
}
