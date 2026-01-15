package krushkov.project.com.virtualwallet.services;

import krushkov.project.com.virtualwallet.exceptions.EntityNotFoundException;
import krushkov.project.com.virtualwallet.models.Transaction;
import krushkov.project.com.virtualwallet.models.Wallet;
import krushkov.project.com.virtualwallet.models.dtos.filters.TransactionFilterOptions;
import krushkov.project.com.virtualwallet.models.enums.TransactionStatus;
import krushkov.project.com.virtualwallet.models.enums.TransactionType;
import krushkov.project.com.virtualwallet.repositories.TransactionRepository;
import krushkov.project.com.virtualwallet.repositories.specifications.TransactionSpecifications;
import krushkov.project.com.virtualwallet.services.contacts.TransactionService;
import krushkov.project.com.virtualwallet.services.contacts.WalletService;
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
    public Transaction transfer(Wallet senderWallet, Wallet recipientWallet, BigDecimal amount) {
        walletService.debit(senderWallet.getId(), amount);
        walletService.credit(recipientWallet.getId(), amount);

        Transaction tx = new Transaction();
        tx.setType(TransactionType.TRANSFER);
        tx.setStatus(TransactionStatus.CONFIRMED);
        tx.setAmount(amount);
        tx.setCurrency(senderWallet.getCurrency());

        tx.setSenderWallet(senderWallet);
        tx.setRecipientWallet(recipientWallet);

        tx.setSender(senderWallet.getUser());
        tx.setRecipient(recipientWallet.getUser());

        return transactionRepository.save(tx);
    }

    @Override
    @Transactional
    public Transaction topUp(Wallet wallet, BigDecimal amount) {
        walletService.credit(wallet.getId(), amount);

        Transaction tx = new Transaction();

        tx.setType(TransactionType.TOP_UP);
        tx.setStatus(TransactionStatus.CONFIRMED);
        tx.setAmount(amount);
        tx.setCurrency(wallet.getCurrency());

        tx.setRecipientWallet(wallet);
        tx.setRecipient(wallet.getUser());

        return transactionRepository.save(tx);
    }

    @Override
    @Transactional
    public Transaction pay(Wallet senderWallet, Wallet externalWallet, BigDecimal amount) {
        walletService.debit(senderWallet.getId(), amount);
        walletService.credit(externalWallet.getId(), amount);

        Transaction tx = new Transaction();
        tx.setType(TransactionType.PAYMENT);
        tx.setStatus(TransactionStatus.CONFIRMED);
        tx.setAmount(amount);
        tx.setCurrency(senderWallet.getCurrency());

        tx.setSenderWallet(senderWallet);
        tx.setRecipientWallet(externalWallet);

        tx.setSender(senderWallet.getUser());
        tx.setRecipient(externalWallet.getUser());

        return transactionRepository.save(tx);
    }
}
