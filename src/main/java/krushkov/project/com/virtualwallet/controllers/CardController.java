package krushkov.project.com.virtualwallet.controllers;

import krushkov.project.com.virtualwallet.models.Card;
import krushkov.project.com.virtualwallet.models.User;
import krushkov.project.com.virtualwallet.models.dtos.requests.CardCreateRequest;
import krushkov.project.com.virtualwallet.models.dtos.responses.CardResponse;
import krushkov.project.com.virtualwallet.services.contacts.CardService;
import krushkov.project.com.virtualwallet.services.contacts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final UserService userService;

    @PostMapping("/users/{userId}")
    public CardResponse addCard(
            @PathVariable Long userId,
            @RequestBody CardCreateRequest request
    ) {
        User user = userService.getById(userId);

        Card card = new Card();

        card.setUser(user);
        card.setCardNumber(request.cardNumber());
        card.setExpirationMonth(request.expirationMonth());
        card.setExpirationYear(request.expirationYear());
        card.setCardHolder(request.cardHolder());
        card.setCcv(request.ccv());

        Card saved = cardService.addCard(userId, card);

        return new CardResponse(
                saved.getId(),
                saved.getCardNumber(),
                saved.getCardHolder(),
                saved.getExpirationMonth(),
                saved.getExpirationYear()
        );
    }

    @DeleteMapping("/{cardId}")
    public void remove(@PathVariable Long cardId) {
        cardService.removeCard(cardId);
    }
}
