package com.krushkov.virtualwallet.controllers;

import com.krushkov.virtualwallet.helpers.mappers.TransactionMapper;
import com.krushkov.virtualwallet.models.dtos.filters.TransactionFilterOptions;
import com.krushkov.virtualwallet.models.dtos.requests.PaymentRequest;
import com.krushkov.virtualwallet.models.dtos.requests.TransferRequest;
import com.krushkov.virtualwallet.models.dtos.responses.TransactionResponse;
import com.krushkov.virtualwallet.models.enums.TransactionStatus;
import com.krushkov.virtualwallet.models.enums.TransactionType;
import com.krushkov.virtualwallet.services.contacts.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping
    public Page<TransactionResponse> search(
            @RequestParam(required = false) Long senderId,
            @RequestParam(required = false) Long recipientId,
            @RequestParam(required = false) Long senderWalletId,
            @RequestParam(required = false) Long recipientWalletId,
            @RequestParam(required = false) TransactionType type,
            @RequestParam(required = false) TransactionStatus status,
            @RequestParam(required = false) LocalDateTime createdFrom,
            @RequestParam(required = false) LocalDateTime createdTo,
            @RequestParam(required = false) BigDecimal minAmount,
            @RequestParam(required = false) BigDecimal maxAmount,
            Pageable pageable
    ) {
        TransactionFilterOptions filters = new TransactionFilterOptions(
                senderId, recipientId,
                senderWalletId, recipientWalletId,
                type, status,
                createdFrom, createdTo,
                minAmount, maxAmount
        );

        return transactionService.search(filters, pageable)
                .map(transactionMapper::toResponse);
    }

    @GetMapping("/{transactionId}")
    public TransactionResponse getById(@PathVariable Long transactionId) {
        return transactionMapper.toResponse(transactionService.getById(transactionId));
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

    @PostMapping("/pay")
    public TransactionResponse pay(
            @RequestParam Long senderWalletId,
            @Valid @RequestBody PaymentRequest request) {
        return transactionMapper.toResponse(
                transactionService.pay(senderWalletId, request.amount(), request.merchantReference())
        );
    }
}
