package com.krushkov.virtualwallet.helpers.mappers;

import com.krushkov.virtualwallet.models.User;
import com.krushkov.virtualwallet.models.dtos.responses.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getPhoneNumber(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getIsBlocked(),
                user.getCreatedAt()
        );
    }
}
