package com.springboot.ecom.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerDto(
        @NotBlank(message = "Name cannot be blank")
        @Size(min = 3, message = "Name should be minimum 3 chars")
        @Pattern(regexp = "[a-zA-Z ]+" , message = "Only chars and space are allowed in name")
        String name,
        @NotBlank(message = "City is mandatory")
        String city,
        String username,
        String password
) {
}
