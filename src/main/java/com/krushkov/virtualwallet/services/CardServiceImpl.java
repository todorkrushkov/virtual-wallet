package com.krushkov.virtualwallet.services;

import com.krushkov.virtualwallet.exceptions.EntityNotFoundException;
import com.krushkov.virtualwallet.models.Card;
import com.krushkov.virtualwallet.repositories.CardRepository;
import com.krushkov.virtualwallet.services.contacts.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public Card getById(Long cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card", cardId));
    }

    @Override
    public Card getByUserId(Long userId) {
        return cardRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", userId));
    }

    @Override
    @Transactional
    public Card addCard(Long userId, Card card) {
        return cardRepository.save(card);
    }

    @Override
    @Transactional
    public void removeCard(Long cardId) {
        Card card = getById(cardId);
        cardRepository.delete(card);
    }
}
