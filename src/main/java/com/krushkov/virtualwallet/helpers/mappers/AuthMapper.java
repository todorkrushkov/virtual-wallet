package com.krushkov.virtualwallet.helpers.mappers;

import com.krushkov.virtualwallet.models.User;
import com.krushkov.virtualwallet.models.dtos.requests.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {
    public User toObject(RegisterRequest request) {
        User user = new User();

        user.setUsername(request.username());
        user.setPassword(request.password());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());

        return user;
    }
}
