package com.leonardo.productreviewer.security.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
public record StandardErrorResponseDTO(
        Integer status,
        Long timestamp,
        String message,
        String path
) {
}
