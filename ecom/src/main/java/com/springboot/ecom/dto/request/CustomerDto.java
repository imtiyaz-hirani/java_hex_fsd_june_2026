package com.springboot.ecom.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CustomerDto(
        @NotBlank
        String name,
        @NotBlank
        String city
) {
}
