package com.krushkov.virtualwallet.helpers;

import com.krushkov.virtualwallet.exceptions.AuthenticationFailureException;
import com.krushkov.virtualwallet.models.CustomUserDetails;
import com.krushkov.virtualwallet.models.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextHelper {

    public static Long getCurrentUserId() {
        CustomUserDetails userDetails = extractCustomUserDetails();
        return userDetails.getId();
    }

    public static String getCurrentUserUsername() {
        CustomUserDetails userDetails = extractCustomUserDetails();
        return userDetails.getUsername();
    }

    public static String getCurrentUserEmail() {
        CustomUserDetails userDetails = extractCustomUserDetails();
        return userDetails.getEmail();
    }

    public static Role getCurrentUserRole() {
        CustomUserDetails userDetails = extractCustomUserDetails();
        return userDetails.getRole();
    }

    private static CustomUserDetails extractCustomUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null
                || !auth.isAuthenticated()
                || auth.getPrincipal() == null
                || auth.getPrincipal().equals("anonymousUser")
                || !(auth.getPrincipal() instanceof CustomUserDetails principal)
        ) {
            throw new AuthenticationFailureException("Authentication required.");
        }

        return principal;
    }
}
