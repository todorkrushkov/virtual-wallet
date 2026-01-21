package com.krushkov.virtualwallet.helpers.mappers;

import com.krushkov.virtualwallet.models.Transaction;
import com.krushkov.virtualwallet.models.dtos.responses.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionResponse toResponse(Transaction tx) {
        return new TransactionResponse(
                tx.getId(),
                tx.getType(),
                tx.getStatus(),
                tx.getAmount(),
                tx.getCreatedAt()
        );
    }
}
