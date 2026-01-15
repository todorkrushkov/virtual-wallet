package krushkov.project.com.virtualwallet.models.dtos.filters;

import krushkov.project.com.virtualwallet.models.enums.CurrencyCode;

import java.math.BigDecimal;

public record WalletFilterOptions(
        Long userId,

        CurrencyCode currencyCode,

        BigDecimal minBalance,
        BigDecimal maxBalance
) {}
