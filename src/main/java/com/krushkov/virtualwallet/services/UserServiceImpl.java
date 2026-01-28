package com.krushkov.virtualwallet.services;

import com.krushkov.virtualwallet.exceptions.EntityNotFoundException;
import com.krushkov.virtualwallet.helpers.validations.UserValidationHelper;
import com.krushkov.virtualwallet.models.Role;
import com.krushkov.virtualwallet.models.User;
import com.krushkov.virtualwallet.models.dtos.filters.UserFilterOptions;
import com.krushkov.virtualwallet.models.enums.RoleType;
import com.krushkov.virtualwallet.repositories.RoleRepository;
import com.krushkov.virtualwallet.repositories.UserRepository;
import com.krushkov.virtualwallet.repositories.specifications.UserSpecifications;
import com.krushkov.virtualwallet.services.contacts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

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
        UserValidationHelper.validateUsernameNotTaken(userRepository, user.getUsername(), null);
        UserValidationHelper.validateEmailNotTaken(userRepository, user.getEmail(), null);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = roleRepository.findByName(RoleType.USER)
                .orElseThrow(() -> new EntityNotFoundException("Role", "name", "USER"));

        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long targetUserId, User updatedUser) {
        User existing = getById(targetUserId);

        UserValidationHelper.validateUsernameNotTaken(userRepository, existing.getUsername(), targetUserId);
        UserValidationHelper.validateEmailNotTaken(userRepository, existing.getEmail(), targetUserId);
        UserValidationHelper.validatePhoneNumberNotTaken(userRepository, existing.getPhoneNumber(), targetUserId);

        return userRepository.save(existing);
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

    @Override
    public long count() {
        return userRepository.count();
    }
}
