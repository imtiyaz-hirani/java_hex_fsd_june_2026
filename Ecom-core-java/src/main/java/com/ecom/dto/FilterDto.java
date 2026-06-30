package com.ecom.dto;

import java.util.List;

public record FilterDto(
        List<String> listCategoryNames,
        List<String> listSellerNames,
        double startPrice,
        double endPrice
    ) {
}
