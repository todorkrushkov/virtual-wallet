package com.krushkov.virtualwallet.services.processors;

import com.krushkov.virtualwallet.helpers.validations.TransactionValidationHelper;
import com.krushkov.virtualwallet.models.Transaction;
import com.krushkov.virtualwallet.models.Wallet;
import com.krushkov.virtualwallet.services.contacts.WalletService;
import com.krushkov.virtualwallet.helpers.TransactionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class TopUpTransactionProcessor {

    private final WalletService walletService;
    private final TransactionFactory transactionFactory;

    public Transaction process(Wallet wallet, BigDecimal amount, String externalReference) {
        TransactionValidationHelper.validateAmount(amount);

        walletService.credit(wallet.getId(), amount);

        return transactionFactory.createTopUp(wallet, amount, externalReference);
    }
}
