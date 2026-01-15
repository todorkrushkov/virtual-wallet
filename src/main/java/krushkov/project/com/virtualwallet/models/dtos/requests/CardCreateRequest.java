package krushkov.project.com.virtualwallet.models.dtos.requests;

public record CardCreateRequest(
        String cardNumber,
        Integer expirationMonth,
        Integer expirationYear,
        String cardHolder,
        String ccv
) {}
