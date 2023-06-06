package com.leonardo.productreviewer.security.dtos;

import lombok.Builder;

@Builder
public record CredentialsRequestDTO(
        String username,
        String password
) {
}
