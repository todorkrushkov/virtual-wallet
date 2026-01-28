package com.krushkov.virtualwallet.models.dtos.responses;

public record UserDetailsResponse(
        Long id,
        String username,
        String role,
        Boolean isBlocked
) {}
