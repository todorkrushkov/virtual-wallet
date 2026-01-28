package com.krushkov.virtualwallet.services;

import com.krushkov.virtualwallet.exceptions.EntityNotFoundException;
import com.krushkov.virtualwallet.models.Transaction;
import com.krushkov.virtualwallet.models.Wallet;
import com.krushkov.virtualwallet.models.dtos.filters.TransactionFilterOptions;
import com.krushkov.virtualwallet.repositories.TransactionRepository;
import com.krushkov.virtualwallet.repositories.specifications.TransactionSpecifications;
import com.krushkov.virtualwallet.security.auth.SecurityContextUtil;
import com.krushkov.virtualwallet.services.contacts.TransactionService;
import com.krushkov.virtualwallet.services.contacts.WalletService;
import com.krushkov.virtualwallet.services.processors.PaymentTransactionProcessor;
import com.krushkov.virtualwallet.services.processors.TopUpTransactionProcessor;
import com.krushkov.virtualwallet.services.processors.TransferTransactionProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletService walletService;

    private final TransferTransactionProcessor transferProcessor;
    private final TopUpTransactionProcessor topUpProcessor;
    private final PaymentTransactionProcessor paymentProcessor;

    @Override
    public Page<Transaction> search(TransactionFilterOptions filters, Pageable pageable) {
        return transactionRepository.findAll(TransactionSpecifications.withFIlters(filters), pageable);
    }

    @Override
    public Transaction getById(Long txId) {
        return transactionRepository.findById(txId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction", txId));
    }

    @Override
    @Transactional
    public Transaction transfer(Long recipientWalletId, BigDecimal amount) {
        Long senderId = SecurityContextUtil.getUserDetailsId();

        Wallet senderWallet = walletService.getByUserId(senderId);
        Wallet recipientWallet = walletService.getById(recipientWalletId);

        Transaction tx = transferProcessor.process(senderWallet, recipientWallet, amount);
        return transactionRepository.save(tx);
    }

    @Override
    @Transactional
    public Transaction topUp(BigDecimal amount, String externalReference) {
        Long senderId = SecurityContextUtil.getUserDetailsId();

        Wallet wallet = walletService.getByUserId(senderId);

        Transaction tx = topUpProcessor.process(wallet, amount, externalReference);
        return transactionRepository.save(tx);
    }

    @Override
    @Transactional
    public Transaction pay(BigDecimal amount, String externalReference) {
        Long senderId = SecurityContextUtil.getUserDetailsId();

        Wallet senderWallet = walletService.getByUserId(senderId);

        Transaction tx = paymentProcessor.process(senderWallet, amount, externalReference);
        return transactionRepository.save(tx);
    }
}
