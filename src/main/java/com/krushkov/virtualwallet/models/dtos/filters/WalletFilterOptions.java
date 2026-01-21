package com.krushkov.virtualwallet.models.dtos.filters;

import com.krushkov.virtualwallet.models.enums.CurrencyCode;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record WalletFilterOptions(

        @Positive(message = "User Id must be positive")
        Long userId,

        CurrencyCode currencyCode,

        @PositiveOrZero(message = "Minimal balance must be positive or zero.")
        BigDecimal minBalance,

        @PositiveOrZero(message = "Maximal balance must be positive or zero.")
        BigDecimal maxBalance
) {
    @AssertTrue(message = "Min balance must be less than or equal to max balance.")
    public boolean isValidBalanceRange() {
        if (minBalance == null || maxBalance == null) {
            return true;
        }
        return minBalance.compareTo(maxBalance) <= 0;
    }
}
