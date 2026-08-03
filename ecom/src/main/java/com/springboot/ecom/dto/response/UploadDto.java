package com.springboot.ecom.dto.response;

public record UploadDto(
        long productId,
        String path,
        String fileName,
        String message
) {
}
