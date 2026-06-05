package com.ecuti.auth_service.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(
        String accessToken,
        String refreshToken,
        String tokenType,
        long expiresIn
) {}
