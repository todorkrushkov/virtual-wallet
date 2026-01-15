package krushkov.project.com.virtualwallet.repositories.specifications;

import jakarta.persistence.criteria.Predicate;
import krushkov.project.com.virtualwallet.models.Wallet;
import krushkov.project.com.virtualwallet.models.dtos.filters.WalletFilterOptions;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class WalletSpecifications {
    public static Specification<Wallet> withFilters(WalletFilterOptions filters) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filters.userId() != null) {
                predicates.add(cb.equal(root.get("user").get("id"), filters.userId()));
            }

            if (filters.currencyCode() != null) {
                predicates.add(cb.equal(root.get("currency").get("code"), filters.currencyCode().name()));
            }

            if (filters.minBalance() != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("balance"), filters.minBalance()));
            }

            if (filters.maxBalance() != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("balance"), filters.maxBalance()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
