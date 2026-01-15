package krushkov.project.com.virtualwallet.services.contacts;

import krushkov.project.com.virtualwallet.models.User;
import krushkov.project.com.virtualwallet.models.dtos.filters.UserFilterOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> search(UserFilterOptions filters, Pageable pageable);

    User getById(Long id);

    User getByUsername(String username);

    User getByEmail(String email);

    User create(User user);

    User update(Long id, User user);

    void blockUser(Long id);

    void unblockUser(Long id);
}
