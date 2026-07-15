package com.springboot.ecom.mapper;

import com.springboot.ecom.dto.response.OrderDto;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;

@Component
public class OrderMapper{

    public static OrderDto processOrder(OrderDto dto){
        return new OrderDto(
                dto.productId(),
                dto.productTitle(),
                dto.actualPrice(),
                dto.discount(),
                dto.quantity(),
                dto.dateOfPurchase(),
                dto.sellerName(),
                dto.paidPrice(),
                dto.rating() != 0 || dto.reviewText() != null,
                dto.rating(),
                dto.reviewText(),
                Instant.now().minus(4, ChronoUnit.DAYS).isBefore(dto.deliveredDate()),
                dto.deliveredDate()
        );
    }
}
