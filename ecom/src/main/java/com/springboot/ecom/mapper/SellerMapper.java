package com.springboot.ecom.mapper;

import com.springboot.ecom.dto.request.SellerReqDto;
import com.springboot.ecom.model.Seller;
import org.springframework.stereotype.Component;

@Component
public class SellerMapper {

    public static Seller convertDtoToEntity(SellerReqDto sellerReqDto){
        Seller seller = new Seller();
        seller.setName(sellerReqDto.name());
        seller.setContact(sellerReqDto.contact());
        seller.setCity(sellerReqDto.city());
        return seller;
    }
}
