package com.springboot.ecom.dto.request;

import com.springboot.ecom.enums.JobTitle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ExecutiveReqDto(
        @NotBlank(message = "Name is mandatory")
        String name,
        JobTitle jobTitle,
        @NotBlank(message = "Username is mandatory")
        String username,
        @NotBlank(message = "password is mandatory")
        @Size(min = 5, max=15 , message = "Password should've min 5 and max 15 chars")
        String password
) {
}
