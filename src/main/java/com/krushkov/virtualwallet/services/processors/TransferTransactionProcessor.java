package com.krushkov.virtualwallet.services.processors;

import com.krushkov.virtualwallet.helpers.validations.TransactionValidationHelper;
import com.krushkov.virtualwallet.helpers.validations.UserValidationHelper;
import com.krushkov.virtualwallet.models.Transaction;
import com.krushkov.virtualwallet.models.Wallet;
import com.krushkov.virtualwallet.services.contacts.WalletService;
import com.krushkov.virtualwallet.helpers.TransactionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class TransferTransactionProcessor {

    private final WalletService walletService;
    private final TransactionFactory transactionFactory;

    public Transaction process(
            Wallet senderWallet,
            Wallet recipientWallet,
            BigDecimal amount
    ) {
        TransactionValidationHelper.validateAmount(amount);
        TransactionValidationHelper.validateSameCurrency(senderWallet, recipientWallet);
        TransactionValidationHelper.validateSufficientFunds(senderWallet, amount);

        UserValidationHelper.validateNotBlocked(senderWallet.getUser());
        UserValidationHelper.validateNotBlocked(recipientWallet.getUser());

        walletService.debit(senderWallet.getId(), amount);
        walletService.credit(recipientWallet.getId(), amount);

        return transactionFactory.createTransfer(senderWallet, recipientWallet, amount);
    }
}
