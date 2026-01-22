package com.krushkov.virtualwallet.controllers;

import com.krushkov.virtualwallet.helpers.mappers.WalletMapper;
import com.krushkov.virtualwallet.models.dtos.filters.WalletFilterOptions;
import com.krushkov.virtualwallet.models.dtos.requests.TopUpRequest;
import com.krushkov.virtualwallet.models.dtos.responses.WalletResponse;
import com.krushkov.virtualwallet.models.enums.CurrencyCode;
import com.krushkov.virtualwallet.services.contacts.TransactionService;
import com.krushkov.virtualwallet.services.contacts.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;
    private final TransactionService transactionService;

    @GetMapping
    public Page<WalletResponse> search(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) CurrencyCode currencyCode,
            @RequestParam(required = false) BigDecimal minBalance,
            @RequestParam(required = false) BigDecimal maxBalance,
            Pageable pageable
    ) {
        WalletFilterOptions filters = new WalletFilterOptions(
                userId,
                currencyCode,
                minBalance, maxBalance
        );

        return walletService.search(filters, pageable)
                .map(walletMapper::toResponse);
    }

    @GetMapping("/{walletId}")
    public WalletResponse getById(@PathVariable Long walletId) {
        return walletMapper.toResponse(walletService.getById(walletId));
    }

    @PostMapping("/{walletId}/top-up")
    public void topUp(
            @PathVariable Long walletId,
            @Valid @RequestBody TopUpRequest request
    ) {
        transactionService.topUp(walletId, request.amount(), request.merchantReference());
    }
}
