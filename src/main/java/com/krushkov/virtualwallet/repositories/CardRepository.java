package com.krushkov.virtualwallet.repositories;

import com.krushkov.virtualwallet.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findById(Long cardId);

    Optional<Card> findByUserId(Long userId);

    boolean existsByUserId(Long userId);

}
