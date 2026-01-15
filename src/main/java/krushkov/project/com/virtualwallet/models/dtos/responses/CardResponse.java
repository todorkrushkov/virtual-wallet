package krushkov.project.com.virtualwallet.models.dtos.responses;

public record CardResponse(
        Long id,
        String cardNumber,
        String cardHolder,
        Integer expirationMonth,
        Integer expirationYear
) {}
