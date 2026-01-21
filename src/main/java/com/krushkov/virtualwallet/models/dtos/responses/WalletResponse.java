package com.krushkov.virtualwallet.models.dtos.responses;

import com.krushkov.virtualwallet.models.enums.CurrencyCode;

import java.math.BigDecimal;

public record WalletResponse(
        Long id,
        BigDecimal balance,
        CurrencyCode currencyCode,
        Long userId
) {}
