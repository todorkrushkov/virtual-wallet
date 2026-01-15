package krushkov.project.com.virtualwallet.models.dtos.requests;

import java.math.BigDecimal;

public record TransferRequest(
        Long recipientUserId,
        BigDecimal amount
) {}
