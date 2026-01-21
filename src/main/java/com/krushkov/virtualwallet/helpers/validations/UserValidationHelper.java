package com.krushkov.virtualwallet.helpers.validations;

import com.krushkov.virtualwallet.exceptions.EntityDuplicateException;
import com.krushkov.virtualwallet.exceptions.InvalidOperationException;
import com.krushkov.virtualwallet.models.User;
import com.krushkov.virtualwallet.repositories.UserRepository;

public final class UserValidationHelper {

    private UserValidationHelper() {}

    public static void validateUsernameNotTaken(UserRepository repository, String username) {
        if (repository.existsByUsername(username)) {
            throw new EntityDuplicateException("User", "username", username);
        }
    }

    public static void validateEmailNotTaken(UserRepository repository, String email) {
        if (repository.existsByEmail(email)) {
            throw new EntityDuplicateException("User", "email", email);
        }
    }

    public static void validatePhoneNumberNotTaken(UserRepository repository, String phoneNumber) {
        if (repository.existsByPhoneNumber(phoneNumber)) {
            throw new EntityDuplicateException("User", "phone number", phoneNumber);
        }
    }

    public static void validateNotBlocked(User user) {
        if (user.getIsBlocked()) {
            throw new InvalidOperationException("User is blocked.");
        }
    }
}
