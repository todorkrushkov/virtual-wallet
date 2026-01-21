package com.krushkov.virtualwallet.controllers;

import com.krushkov.virtualwallet.helpers.mappers.CardMapper;
import com.krushkov.virtualwallet.models.Card;
import com.krushkov.virtualwallet.models.User;
import com.krushkov.virtualwallet.models.dtos.requests.CardCreateRequest;
import com.krushkov.virtualwallet.models.dtos.responses.CardResponse;
import com.krushkov.virtualwallet.services.contacts.CardService;
import com.krushkov.virtualwallet.services.contacts.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final UserService userService;

    private final CardMapper cardMapper;

    @PostMapping("/users/{userId}")
    public CardResponse addCard(
            @PathVariable Long userId,
            @Valid @RequestBody CardCreateRequest request
    ) {
        User user = userService.getById(userId);
        Card card = cardMapper.toEntity(request, user);
        return cardMapper.toResponse(cardService.addCard(userId, card));
    }

    @DeleteMapping("/{cardId}")
    public void remove(@PathVariable Long cardId) {
        cardService.removeCard(cardId);
    }
}
