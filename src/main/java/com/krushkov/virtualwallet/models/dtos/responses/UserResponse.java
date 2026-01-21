package com.krushkov.virtualwallet.models.dtos.responses;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String username,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Boolean isBlocked,
        LocalDateTime createdAt
) {}
