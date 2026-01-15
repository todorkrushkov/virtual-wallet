package krushkov.project.com.virtualwallet.controllers;

import krushkov.project.com.virtualwallet.models.Wallet;
import krushkov.project.com.virtualwallet.models.dtos.filters.TransactionFilterOptions;
import krushkov.project.com.virtualwallet.models.dtos.requests.TransferRequest;
import krushkov.project.com.virtualwallet.models.dtos.responses.TransactionResponse;
import krushkov.project.com.virtualwallet.services.contacts.TransactionService;
import krushkov.project.com.virtualwallet.services.contacts.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final WalletService walletService;

    @GetMapping
    public Page<TransactionResponse> search(
            TransactionFilterOptions filters,
            Pageable pageable
    ) {
        return transactionService.search(filters, pageable)
                .map(t -> new TransactionResponse(
                        t.getId(),
                        t.getType(),
                        t.getStatus(),
                        t.getAmount(),
                        t.getCreatedAt()
                ));
    }

    @PostMapping("/transfer")
    public TransactionResponse transfer(
            @RequestParam Long senderWalletId,
            @RequestBody TransferRequest request
    ) {
        Wallet sender = walletService.getById(senderWalletId);
        Wallet recipient = walletService.getByUserId(request.recipientUserId());

        var tx = transactionService.transfer(sender, recipient, request.amount());

        return new TransactionResponse(
                tx.getId(),
                tx.getType(),
                tx.getStatus(),
                tx.getAmount(),
                tx.getCreatedAt()
        );
    }
}
