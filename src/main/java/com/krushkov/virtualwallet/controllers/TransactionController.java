package com.krushkov.virtualwallet.controllers;

import com.krushkov.virtualwallet.helpers.mappers.TransactionMapper;
import com.krushkov.virtualwallet.models.dtos.filters.TransactionFilterOptions;
import com.krushkov.virtualwallet.models.dtos.requests.TransferRequest;
import com.krushkov.virtualwallet.models.dtos.responses.TransactionResponse;
import com.krushkov.virtualwallet.services.contacts.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping
    public Page<TransactionResponse> search(
            @Valid TransactionFilterOptions filters,
            Pageable pageable
    ) {
        return transactionService.search(filters, pageable)
                .map(transactionMapper::toResponse);
    }

    @PostMapping("/transfer")
    public TransactionResponse transfer(
            @RequestParam Long senderWalletId,
            @Valid @RequestBody TransferRequest request
    ) {
        return transactionMapper.toResponse(
                transactionService.transfer(senderWalletId, request.recipientUserId(), request.amount())
        );
    }
}
