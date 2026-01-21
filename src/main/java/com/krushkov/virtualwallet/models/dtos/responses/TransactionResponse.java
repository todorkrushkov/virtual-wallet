package com.krushkov.virtualwallet.models.dtos.responses;

import com.krushkov.virtualwallet.models.enums.TransactionStatus;
import com.krushkov.virtualwallet.models.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        TransactionType type,
        TransactionStatus status,
        BigDecimal amount,
        LocalDateTime createdAt
) {}
