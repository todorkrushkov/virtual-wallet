package com.krushkov.virtualwallet.helpers.validations;

import com.krushkov.virtualwallet.exceptions.EntityDuplicateException;
import com.krushkov.virtualwallet.exceptions.InvalidOperationException;
import com.krushkov.virtualwallet.models.User;
import com.krushkov.virtualwallet.repositories.UserRepository;

public final class UserValidationHelper {

    private UserValidationHelper() {
    }

    public static void validateUsernameNotTaken(UserRepository repository, String newUsername, Long targetUserId) {
        repository.findByUsername(newUsername)
                .filter(u -> !u.getId().equals(targetUserId))
                .ifPresent(u -> {
                    throw new EntityDuplicateException("User", "username", newUsername);
                });
    }

    public static void validateEmailNotTaken(UserRepository repository, String newEmail, Long targetUserId) {
        repository.findByUsername(newEmail)
                .filter(u -> !u.getId().equals(targetUserId))
                .ifPresent(u -> {
                    throw new EntityDuplicateException("User", "email", newEmail);
                });
    }

    public static void validatePhoneNumberNotTaken(UserRepository repository, String newPhoneNumber, Long targetUserId) {
        repository.findByUsername(newPhoneNumber)
                .filter(u -> !u.getId().equals(targetUserId))
                .ifPresent(u -> {
                    throw new EntityDuplicateException("User", "phone number", newPhoneNumber);
                });
    }

    public static void validateNotBlocked(User user) {
        if (user.getIsBlocked()) {
            throw new InvalidOperationException("User is blocked.");
        }
    }
}
