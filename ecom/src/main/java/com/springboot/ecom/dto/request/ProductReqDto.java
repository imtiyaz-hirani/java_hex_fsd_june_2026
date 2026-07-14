package com.springboot.ecom.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import org.hibernate.annotations.NotFound;

public record ProductReqDto(
        @NotBlank(message = "Field is mandatory")
        String title,
        @NotBlank(message = "Field is mandatory")
        String description,
        @NotNull(message = "Field is mandatory")
        @Min(value = 100)
        Double price
) {
}
