package krushkov.project.com.virtualwallet.services.contacts;

import krushkov.project.com.virtualwallet.models.Card;

public interface CardService {

    Card getById(Long cardId);

    Card getByUserId(Long userId);

    Card addCard(Long userId, Card card);

    void removeCard(Long cardId);
}
