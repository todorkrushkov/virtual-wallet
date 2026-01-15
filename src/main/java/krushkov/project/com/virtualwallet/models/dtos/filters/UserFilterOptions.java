package krushkov.project.com.virtualwallet.models.dtos.filters;

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
) {}
