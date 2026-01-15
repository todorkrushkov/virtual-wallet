package krushkov.project.com.virtualwallet.services;

import krushkov.project.com.virtualwallet.exceptions.AccessDeniedException;
import krushkov.project.com.virtualwallet.exceptions.EntityDuplicateException;
import krushkov.project.com.virtualwallet.exceptions.EntityNotFoundException;
import krushkov.project.com.virtualwallet.models.User;
import krushkov.project.com.virtualwallet.models.dtos.filters.UserFilterOptions;
import krushkov.project.com.virtualwallet.repositories.UserRepository;
import krushkov.project.com.virtualwallet.repositories.specifications.UserSpecifications;
import krushkov.project.com.virtualwallet.services.contacts.UserService;
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
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User", "username", username));
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User", "email", email));
    }

    @Override
    @Transactional
    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new EntityDuplicateException("User", "username", user.getUsername());
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityDuplicateException("User", "email", user.getEmail());
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new EntityDuplicateException("User", "phone number", user.getPhoneNumber());
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        User existing = getById(id);

        if (!user.getId().equals(existing.getId())) {
            throw new AccessDeniedException("edit", "user");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new EntityDuplicateException("User", "username", user.getUsername());
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityDuplicateException("User", "email", user.getEmail());
        }

        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new EntityDuplicateException("User", "phone number", user.getPhoneNumber());
        }

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
