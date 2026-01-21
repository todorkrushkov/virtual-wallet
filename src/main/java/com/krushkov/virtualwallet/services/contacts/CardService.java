package com.krushkov.virtualwallet.services.contacts;

import com.krushkov.virtualwallet.models.Card;

public interface CardService {

    Card getById(Long cardId);

    Card getByUserId(Long userId);

    Card addCard(Long userId, Card card);

    void removeCard(Long cardId);
}
