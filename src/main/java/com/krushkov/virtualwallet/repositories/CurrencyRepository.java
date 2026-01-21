package com.krushkov.virtualwallet.repositories;

import com.krushkov.virtualwallet.models.Currency;
import com.krushkov.virtualwallet.models.enums.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, CurrencyCode> {

    List<Currency> findByIsActiveTrue();

}
