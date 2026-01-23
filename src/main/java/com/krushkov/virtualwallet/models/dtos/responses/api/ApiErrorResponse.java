package com.krushkov.virtualwallet.models.dtos.responses.api;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiErrorResponse(
        boolean success,
        int status,
        String path,
        String message,
        Map<String, String>errors,
        LocalDateTime timestamp
) {
    public static ApiErrorResponse error(
            int status,
            String path,
            String message
    ) {
        return new ApiErrorResponse(
                false,
                status,
                path,
                message,
                null,
                LocalDateTime.now()
        );
    }

    public static ApiErrorResponse validationError(
            int status,
            String path,
            String message,
            Map<String, String> errors
    ) {
        return new ApiErrorResponse(
                false,
                status,
                path,
                message,
                errors,
                LocalDateTime.now()
        );
    }
}
