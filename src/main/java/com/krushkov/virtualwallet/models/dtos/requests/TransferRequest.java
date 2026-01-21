package com.krushkov.virtualwallet.models.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferRequest(

        @NotNull(message = "Recipient must be not null.")
        Long recipientUserId,

        @Positive(message = "Amount must be not negative.")
        @NotNull(message = "Amount must be not null.")
        BigDecimal amount
) {}
