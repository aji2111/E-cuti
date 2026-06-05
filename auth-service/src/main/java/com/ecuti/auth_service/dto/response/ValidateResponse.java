package com.ecuti.auth_service.dto.response;

import lombok.Builder;

@Builder
public record ValidateResponse(
        boolean valid,
        String username,
        String role
) {}
