package com.springboot.ecom.mapper;

import com.springboot.ecom.dto.request.ProductReqDto;
import com.springboot.ecom.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

   public static Product convertDtoToEntity(final ProductReqDto productReqDto){
        Product product = new Product();
        product.setTitle(productReqDto.title());
        product.setDescription(productReqDto.description());
        product.setPrice(productReqDto.price());
        return product;
    }
}
