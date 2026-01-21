package com.krushkov.virtualwallet.models.dtos.requests;

import jakarta.validation.constraints.*;

import java.time.YearMonth;

public record CardCreateRequest(

        @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits.")
        @NotBlank(message = "Card number is required.")
        String cardNumber,

        @Min(1) @Max(12)
        @NotNull(message = "Expiration month is required.")
        Integer expirationMonth,

        @Min(2026)
        @NotNull(message = "Expiration year is required.")
        Integer expirationYear,

        @Size(min = 6, max = 100, message = "Card holder must be between 6 and 100 characters.")
        @NotBlank(message = "Card holder is required.")
        String cardHolder
) {
    @AssertTrue(message = "Card is expired.")
    public boolean isExpirationDateValid() {
        if (expirationMonth == null || expirationYear == null) {
            return true;
        }

        YearMonth expiration = YearMonth.of(expirationYear, expirationMonth);
        return !expiration.isBefore(YearMonth.now());
    }
}
