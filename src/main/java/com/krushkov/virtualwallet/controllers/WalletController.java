package com.krushkov.virtualwallet.controllers;

import com.krushkov.virtualwallet.helpers.mappers.WalletMapper;
import com.krushkov.virtualwallet.models.dtos.filters.WalletFilterOptions;
import com.krushkov.virtualwallet.models.dtos.requests.TopUpRequest;
import com.krushkov.virtualwallet.models.dtos.responses.WalletResponse;
import com.krushkov.virtualwallet.services.contacts.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;

    @GetMapping
    public Page<WalletResponse> search(
            @Valid WalletFilterOptions filters,
            Pageable pageable
    ) {
        return walletService.search(filters, pageable)
                .map(walletMapper::toResponse);
    }

    @PostMapping("/{walletId}/top-up")
    public void topUp(
            @PathVariable Long walletId,
            @Valid @RequestBody TopUpRequest request
    ) {
        walletService.credit(walletId, request.amount());
    }
}
