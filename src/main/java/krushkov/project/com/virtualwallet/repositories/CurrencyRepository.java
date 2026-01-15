package krushkov.project.com.virtualwallet.repositories;

import krushkov.project.com.virtualwallet.models.Currency;
import krushkov.project.com.virtualwallet.models.enums.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, CurrencyCode> {

    List<Currency> findByIsActiveTrue();

}
