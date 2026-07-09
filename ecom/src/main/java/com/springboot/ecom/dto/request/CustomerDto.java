package com.springboot.ecom.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerDto(
        @NotBlank(message = "Name is mandatory")
        @Pattern(regexp = "[a-zA-Z ]+", message = "Please use lower case and space")
        @Size(min = 3, message = "We need minimum 3 chars in name")
        String name,
        @NotBlank(message = "city field is mandatory")
        String city
) {
}
