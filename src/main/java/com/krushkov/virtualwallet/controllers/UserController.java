package com.krushkov.virtualwallet.controllers;

import com.krushkov.virtualwallet.helpers.mappers.UserMapper;
import com.krushkov.virtualwallet.models.dtos.filters.UserFilterOptions;
import com.krushkov.virtualwallet.models.dtos.responses.UserResponse;
import com.krushkov.virtualwallet.services.contacts.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public Page<UserResponse> search(
            @Valid UserFilterOptions filters,
            Pageable pageable
    ) {
        return userService.search(filters, pageable)
                .map(userMapper::toResponse);
    }

    @PatchMapping("/{id}/block")
    public void block(@PathVariable Long id) {
        userService.blockUser(id);
    }

    @PatchMapping("/{id}/unblock")
    public void unblock(@PathVariable Long id) {
        userService.unblockUser(id);
    }
}
