package com.krushkov.virtualwallet.services;

import com.krushkov.virtualwallet.exceptions.EntityNotFoundException;
import com.krushkov.virtualwallet.exceptions.InvalidOperationException;
import com.krushkov.virtualwallet.models.Wallet;
import com.krushkov.virtualwallet.models.dtos.filters.WalletFilterOptions;
import com.krushkov.virtualwallet.repositories.WalletRepository;
import com.krushkov.virtualwallet.repositories.specifications.WalletSpecifications;
import com.krushkov.virtualwallet.services.contacts.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Override
    public Page<Wallet> search(WalletFilterOptions filters, Pageable pageable) {
        return walletRepository.findAll(WalletSpecifications.withFilters(filters), pageable);
    }

    @Override
    public Wallet getById(Long walletId) {
        return walletRepository.findById(walletId).orElseThrow(() -> new EntityNotFoundException("Wallet", walletId));
    }

    @Override
    public Wallet getByUserId(Long userId) {
        return walletRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Wallet create(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public void credit(Long walletId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByIdForUpdate(walletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet", walletId));

        wallet.setBalance(wallet.getBalance().add(amount));
    }

    @Override
    @Transactional
    public void debit(Long walletId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByIdForUpdate(walletId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet", walletId));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InvalidOperationException("Insufficient funds.");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
    }
}
