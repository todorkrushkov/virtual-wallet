package com.krushkov.virtualwallet.models.dtos.filters;

import com.krushkov.virtualwallet.models.enums.TransactionStatus;
import com.krushkov.virtualwallet.models.enums.TransactionType;
import jakarta.validation.constraints.AssertTrue;

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
) {
    @AssertTrue(message = "Created from must be before or equal to created to.")
    public boolean isValidCreateRange() {
        if (createdFrom == null || createdTo == null) {
            return true;
        }
        return !createdFrom.isAfter(createdTo);
    }

    @AssertTrue(message = "Min amount must be less than or equal to max amount.")
    public boolean isValidAmountRange() {
        if (minAmount == null || maxAmount == null) {
            return true;
        }
        return minAmount.compareTo(maxAmount) <= 0;
    }
}
