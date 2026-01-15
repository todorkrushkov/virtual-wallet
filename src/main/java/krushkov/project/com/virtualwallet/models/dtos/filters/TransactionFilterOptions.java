package krushkov.project.com.virtualwallet.models.dtos.filters;

import krushkov.project.com.virtualwallet.models.enums.TransactionStatus;
import krushkov.project.com.virtualwallet.models.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionFilterOptions(
        Long senderId,
        Long recipientId,

        Long senderWalletId,
        Long recipientWalletId,

        TransactionType type,
        TransactionStatus status,

        LocalDateTime createdFrom,
        LocalDateTime createdTo,

        BigDecimal minAmount,
        BigDecimal maxAmount
) {}
