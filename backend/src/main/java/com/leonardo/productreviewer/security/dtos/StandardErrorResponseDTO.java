package com.leonardo.productreviewer.security.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
public record StandardErrorResponseDTO(
        Integer status,
        Long timestamp,
        Object error,
        String message,
        String path
) {
}
