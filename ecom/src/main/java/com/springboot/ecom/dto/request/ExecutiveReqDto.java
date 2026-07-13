package com.springboot.ecom.dto.request;

import com.springboot.ecom.enums.JobTitle;

public record ExecutiveReqDto(
        String name,
        JobTitle jobTitle,
        String username,
        String password
) {
}
