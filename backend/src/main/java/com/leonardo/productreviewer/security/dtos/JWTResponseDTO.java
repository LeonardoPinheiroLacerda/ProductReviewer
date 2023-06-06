package com.leonardo.productreviewer.security.dtos;

import lombok.Builder;

@Builder
public record JWTResponseDTO(
        String type,
        String token
) {
}
