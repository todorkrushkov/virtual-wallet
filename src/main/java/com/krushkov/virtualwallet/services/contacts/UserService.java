package com.krushkov.virtualwallet.services.contacts;

import com.krushkov.virtualwallet.models.User;
import com.krushkov.virtualwallet.models.dtos.filters.UserFilterOptions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> search(UserFilterOptions filters, Pageable pageable);

    User getById(Long id);

    User create(User user);

    User update(Long id, User user);

    void blockUser(Long id);

    void unblockUser(Long id);

    long count();
}
