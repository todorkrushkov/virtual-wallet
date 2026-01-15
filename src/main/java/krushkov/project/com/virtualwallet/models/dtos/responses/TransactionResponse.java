package krushkov.project.com.virtualwallet.models.dtos.responses;

import krushkov.project.com.virtualwallet.models.enums.TransactionStatus;
import krushkov.project.com.virtualwallet.models.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        TransactionType type,
        TransactionStatus status,
        BigDecimal amount,
        LocalDateTime createdAt
) {}
