package com.springboot.ecom.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PassengerReqDto(
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "Contact is mandatory")
        String contact
) {
}
