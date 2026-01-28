package com.krushkov.virtualwallet.security.auth;

import com.krushkov.virtualwallet.models.Role;
import com.krushkov.virtualwallet.models.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class UserPrincipal implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final Role role;
    private final Boolean isBlocked;

    public UserPrincipal(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.isBlocked = user.getIsBlocked();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + role.getName().name());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isBlocked;
    }
}
