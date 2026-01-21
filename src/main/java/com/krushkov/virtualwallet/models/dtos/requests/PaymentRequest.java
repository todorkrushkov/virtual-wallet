package com.krushkov.virtualwallet.models.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentRequest(

        @Positive(message = "Amount must be greater that zero.")
        @NotNull(message = "Amount must be not null.")
        BigDecimal amount,

        @NotBlank(message = "Merchant reference is required.")
        String merchantReference
) {}
