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

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public Page<UserResponse> search(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) Boolean isBlocked,
            @RequestParam(required = false) LocalDateTime createdFrom,
            @RequestParam(required = false) LocalDateTime createdTo,
            Pageable pageable
    ) {
        UserFilterOptions filters = new UserFilterOptions(
                username, firstName, lastName,
                email, phoneNumber, isBlocked,
                createdFrom, createdTo
        );

        return userService.search(filters, pageable)
                .map(userMapper::toResponse);
    }

    @GetMapping("/{userId}")
    public UserResponse getById(@PathVariable Long userId) {
        return userMapper.toResponse(userService.getById(userId));
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
