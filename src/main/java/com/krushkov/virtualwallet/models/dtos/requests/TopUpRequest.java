package com.krushkov.virtualwallet.models.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TopUpRequest(

        @Positive(message = "Amount must be greater that zero.")
        @NotNull(message = "Amount must be not null.")
        BigDecimal amount
) {}
