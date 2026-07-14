package com.springboot.ecom.dto.response;

public record ProductResStatDto(
        String sellerName,
        long numberOfProductsOwned
) {
}
