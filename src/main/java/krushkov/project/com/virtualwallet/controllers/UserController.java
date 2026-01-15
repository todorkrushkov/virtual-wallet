package krushkov.project.com.virtualwallet.controllers;

import krushkov.project.com.virtualwallet.models.dtos.filters.UserFilterOptions;
import krushkov.project.com.virtualwallet.models.dtos.responses.UserResponse;
import krushkov.project.com.virtualwallet.services.contacts.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public Page<UserResponse> search(
            UserFilterOptions filters,
            Pageable pageable
    ) {
        return userService.search(filters, pageable)
                .map(u -> new UserResponse(
                        u.getId(),
                        u.getUsername(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getEmail(),
                        u.getPhoneNumber(),
                        u.getIsBlocked(),
                        u.getCreatedAt()
                ));
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
