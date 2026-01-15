package krushkov.project.com.virtualwallet.models.dtos.responses;

import krushkov.project.com.virtualwallet.models.enums.CurrencyCode;

import java.math.BigDecimal;

public record WalletResponse(
        Long id,
        BigDecimal balance,
        CurrencyCode currencyCode,
        Long userId
) {}
