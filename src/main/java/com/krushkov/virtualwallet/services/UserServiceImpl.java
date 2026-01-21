package com.krushkov.virtualwallet.services;

import com.krushkov.virtualwallet.exceptions.AccessDeniedException;
import com.krushkov.virtualwallet.exceptions.EntityDuplicateException;
import com.krushkov.virtualwallet.exceptions.EntityNotFoundException;
import com.krushkov.virtualwallet.helpers.validations.UserValidationHelper;
import com.krushkov.virtualwallet.models.User;
import com.krushkov.virtualwallet.models.dtos.filters.UserFilterOptions;
import com.krushkov.virtualwallet.repositories.UserRepository;
import com.krushkov.virtualwallet.repositories.specifications.UserSpecifications;
import com.krushkov.virtualwallet.services.contacts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Page<User> search(UserFilterOptions filters, Pageable pageable) {
        return userRepository.findAll(UserSpecifications.withFilters(filters), pageable);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
    }

    @Override
    @Transactional
    public User create(User user) {
        UserValidationHelper.validateUsernameNotTaken(userRepository, user.getUsername());
        UserValidationHelper.validateEmailNotTaken(userRepository, user.getEmail());
        UserValidationHelper.validatePhoneNumberNotTaken(userRepository, user.getPhoneNumber());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User existing = getById(id);

        UserValidationHelper.validateUsernameNotTaken(userRepository, existing.getUsername());
        UserValidationHelper.validateEmailNotTaken(userRepository, existing.getEmail());
        UserValidationHelper.validatePhoneNumberNotTaken(userRepository, existing.getPhoneNumber());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void blockUser(Long id) {
        User user = getById(id);
        user.setIsBlocked(true);
    }

    @Override
    @Transactional
    public void unblockUser(Long id) {
        User user = getById(id);
        user.setIsBlocked(false);
    }
}
