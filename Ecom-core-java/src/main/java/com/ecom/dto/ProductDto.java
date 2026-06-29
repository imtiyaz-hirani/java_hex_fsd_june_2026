package com.ecom.dto;

public record ProductDto (
        int id,
        String title,
        double price,
        String categoryName,
        String sellerName
) { }
