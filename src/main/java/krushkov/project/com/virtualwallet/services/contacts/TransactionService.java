package krushkov.project.com.virtualwallet.services.contacts;

import krushkov.project.com.virtualwallet.models.Transaction;
import krushkov.project.com.virtualwallet.models.Wallet;
import krushkov.project.com.virtualwallet.models.dtos.filters.TransactionFilterOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface TransactionService {

    Page<Transaction> search(TransactionFilterOptions filters, Pageable pageable);

    Transaction getById(Long txId);

    Transaction transfer(Wallet senderWallet, Wallet recipientWallet, BigDecimal amount);

    Transaction topUp(Wallet wallet, BigDecimal amount);

    Transaction pay(Wallet senderWallet, Wallet externalWallet, BigDecimal amount);
}
