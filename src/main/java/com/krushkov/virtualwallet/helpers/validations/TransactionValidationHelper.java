package com.krushkov.virtualwallet.helpers.validations;

import com.krushkov.virtualwallet.exceptions.InvalidOperationException;
import com.krushkov.virtualwallet.models.Wallet;

import java.math.BigDecimal;

public final class TransactionValidationHelper {

    private TransactionValidationHelper() {}

    public static void validateAmount(BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new InvalidOperationException("Amount must be positive.");
        }
    }

    public static void validateSameCurrency(Wallet senderWallet, Wallet recipientWallet) {
        if (!senderWallet.getCurrency().equals(recipientWallet.getCurrency())) {
            throw new InvalidOperationException("Currency mismatch.");
        }
    }

    public static void validateSufficientFunds(Wallet wallet, BigDecimal amount) {
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InvalidOperationException("Insufficient funds.");
        }
    }
}
