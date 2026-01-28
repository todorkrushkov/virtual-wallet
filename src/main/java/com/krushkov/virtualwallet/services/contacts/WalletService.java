package com.krushkov.virtualwallet.services.contacts;

import com.krushkov.virtualwallet.models.Wallet;
import com.krushkov.virtualwallet.models.dtos.filters.WalletFilterOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface WalletService {

    Page<Wallet> search(WalletFilterOptions filters, Pageable pageable);

    Wallet getById(Long walletId);

    Wallet getByUserId(Long userId);

    Wallet create(Wallet wallet);

    void credit(Long walletId, BigDecimal amount);

    void debit(Long walletId, BigDecimal amount);
}
