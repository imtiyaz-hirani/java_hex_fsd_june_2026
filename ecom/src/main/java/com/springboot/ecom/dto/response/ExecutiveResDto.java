package com.springboot.ecom.dto.response;

import com.springboot.ecom.enums.JobTitle;

public record ExecutiveResDto(
        long id,
        String name,
        JobTitle jobTitle
) {
}
