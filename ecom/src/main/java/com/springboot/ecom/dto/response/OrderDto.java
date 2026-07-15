package com.springboot.ecom.dto.response;

import java.time.Instant;
import java.time.LocalDate;

public record OrderDto(
        long productId,
        String productTitle,
        double actualPrice,
        double discount,
        int quantity,
        Instant dateOfPurchase,
        String sellerName,
        double paidPrice,
        boolean isReviewGiven,
        int rating,
        String reviewText,
        boolean isReturnAllowed,
        Instant deliveredDate
) {
}
