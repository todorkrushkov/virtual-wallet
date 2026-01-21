package com.krushkov.virtualwallet.helpers.mappers;

import com.krushkov.virtualwallet.models.Card;
import com.krushkov.virtualwallet.models.User;
import com.krushkov.virtualwallet.models.dtos.requests.CardCreateRequest;
import com.krushkov.virtualwallet.models.dtos.responses.CardResponse;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardResponse toResponse(Card card) {
        return new CardResponse(
                card.getId(),
                card.getCardSuffix(),
                card.getCardHolder(),
                card.getExpirationMonth(),
                card.getExpirationYear()
        );
    }

    public Card toEntity(CardCreateRequest request, User user) {
        Card card = new Card();
        card.setUser(user);
        card.setCardSuffix(request.cardNumber().substring(request.cardNumber().length() - 4));
        card.setExpirationMonth(request.expirationMonth());
        card.setExpirationYear(request.expirationYear());
        card.setCardHolder(request.cardHolder());
        return card;
    }
}
