package krushkov.project.com.virtualwallet.repositories.specifications;

import jakarta.persistence.criteria.Predicate;
import krushkov.project.com.virtualwallet.models.Transaction;
import krushkov.project.com.virtualwallet.models.dtos.filters.TransactionFilterOptions;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransactionSpecifications {
    public static Specification<Transaction> withFIlters(TransactionFilterOptions filters) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filters.senderId() != null) {
                predicates.add(cb.equal(root.get("sender").get("id"), filters.senderId()));
            }

            if (filters.recipientId() != null) {
                predicates.add(cb.equal(root.get("recipient").get("id"), filters.recipientId()));
            }

            if (filters.senderWalletId() != null) {
                predicates.add(cb.equal(root.get("senderWallet").get("id"), filters.senderWalletId()));
            }

            if (filters.recipientWalletId() != null) {
                predicates.add(cb.equal(root.get("recipientWallet").get("id"), filters.recipientWalletId()));
            }

            if (filters.type() != null) {
                predicates.add(cb.equal(root.get("type"), filters.type()));
            }

            if (filters.status() != null) {
                predicates.add(cb.equal(root.get("status"), filters.status()));
            }

            if (filters.createdFrom() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), filters.createdFrom()));
            }

            if (filters.createdTo() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), filters.createdTo()));
            }

            if (filters.minAmount() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("amount"), filters.minAmount()));
            }

            if (filters.maxAmount() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("amount"), filters.maxAmount()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
