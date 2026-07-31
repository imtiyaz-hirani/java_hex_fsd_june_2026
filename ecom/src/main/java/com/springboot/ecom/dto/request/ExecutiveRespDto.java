package com.springboot.ecom.dto.request;

import com.springboot.ecom.enums.JobTitle;

public record ExecutiveRespDto(
        long id,
        String name,
        JobTitle jobTitle,
        String username
) {
}
