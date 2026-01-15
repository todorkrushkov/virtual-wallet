package krushkov.project.com.virtualwallet.controllers;

import krushkov.project.com.virtualwallet.models.dtos.filters.WalletFilterOptions;
import krushkov.project.com.virtualwallet.models.dtos.requests.TopUpRequest;
import krushkov.project.com.virtualwallet.models.dtos.responses.WalletResponse;
import krushkov.project.com.virtualwallet.services.contacts.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @GetMapping
    public Page<WalletResponse> search(
            WalletFilterOptions filters,
            Pageable pageable
    ) {
        return walletService.search(filters, pageable)
                .map(w -> new WalletResponse(
                        w.getId(),
                        w.getBalance(),
                        w.getCurrency().getCode(),
                        w.getUser().getId()
                ));
    }

    @PostMapping("/{walletId}/top-up")
    public void topUp(
            @PathVariable Long walletId,
            @RequestBody TopUpRequest request
    ) {
        walletService.credit(walletId, request.amount());
    }
}
