package com.ecom.dto;

import java.time.LocalDate;

public record CustomerDto(
        int id,
        String customerName,
        String email,
        LocalDate purchaseDate,
        int quantity,
        String title,
        double price,
        String categoryName,
        String sellerName
) {
}
// constructor, accessors , toString, equals and Hashcode
