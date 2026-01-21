package com.krushkov.virtualwallet.services.contacts;

import com.krushkov.virtualwallet.models.Transaction;
import com.krushkov.virtualwallet.models.dtos.filters.TransactionFilterOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface TransactionService {

    Page<Transaction> search(TransactionFilterOptions filters, Pageable pageable);

    Transaction getById(Long txId);

    Transaction transfer(Long senderWalletId, Long recipientWalletId, BigDecimal amount);

    Transaction topUp(Long walletId, BigDecimal amount, String externalReference);

    Transaction pay(Long senderWalletId, BigDecimal amount, String externalReference);
}
