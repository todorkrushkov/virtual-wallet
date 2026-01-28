package com.krushkov.virtualwallet.security.auth;

import com.krushkov.virtualwallet.exceptions.AuthenticationFailureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityContextUtil {

    public static Long getUserDetailsId() {
        UserPrincipal userDetails = extractCustomUserDetails();
        return userDetails.getId();
    }

    public static String getUserDetailsUsername() {
        UserPrincipal userDetails = extractCustomUserDetails();
        return userDetails.getUsername();
    }

    public static Boolean getUserDetailsIsBlocked() {
        UserPrincipal userDetails = extractCustomUserDetails();
        return userDetails.getIsBlocked();
    }

    private static UserPrincipal extractCustomUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null
                || !auth.isAuthenticated()
                || auth.getPrincipal() == null
                || auth.getPrincipal().equals("anonymousUser")
                || !(auth.getPrincipal() instanceof UserPrincipal principal)
        ) {
            throw new AuthenticationFailureException("Authentication required.");
        }

        return principal;
    }
}
