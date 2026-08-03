package com.springboot.ecom.dto.response;

public record ProductResDto(
        String title,
        long id,
        double price,
        String sellerName,
        String imageUrl
) {
}
