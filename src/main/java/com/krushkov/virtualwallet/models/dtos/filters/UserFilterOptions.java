package com.krushkov.virtualwallet.models.dtos.filters;

import jakarta.validation.constraints.AssertTrue;

import java.time.LocalDateTime;

public record UserFilterOptions(
        String username,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,

        Boolean isBlocked,

        LocalDateTime createdFrom,
        LocalDateTime createdTo
) {
    @AssertTrue(message = "Created from must be before or equal to created to.")
    public boolean isValidCreateRange() {
        if (createdFrom == null || createdTo == null) {
            return true;
        }
        return !createdFrom.isAfter(createdTo);
    }
}
