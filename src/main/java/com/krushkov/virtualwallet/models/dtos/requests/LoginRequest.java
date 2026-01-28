package com.krushkov.virtualwallet.models.dtos.requests;

public record LoginRequest(
        String identifier,
        String password
) {}
