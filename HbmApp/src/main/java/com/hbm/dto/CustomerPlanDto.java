package com.hbm.dto;

import java.time.LocalDate;

public record CustomerPlanDto(
        int customerId,
        String customerName,
        String customerEmail,
        String planName,
        LocalDate startDate,
        LocalDate endDate
) {
}
