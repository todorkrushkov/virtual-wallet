package com.krushkov.virtualwallet.models.dtos.responses;

public record CardResponse(
        Long id,
        String cardSuffix,
        String cardHolder,
        Integer expirationMonth,
        Integer expirationYear
) {}
