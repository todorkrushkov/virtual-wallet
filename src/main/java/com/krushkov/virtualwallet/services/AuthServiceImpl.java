package com.krushkov.virtualwallet.services;

import com.krushkov.virtualwallet.exceptions.InvalidOperationException;
import com.krushkov.virtualwallet.helpers.mappers.AuthMapper;
import com.krushkov.virtualwallet.jwt.JwtCookieUtil;
import com.krushkov.virtualwallet.jwt.JwtUtil;
import com.krushkov.virtualwallet.models.CustomUserDetails;
import com.krushkov.virtualwallet.models.User;
import com.krushkov.virtualwallet.models.dtos.requests.LoginRequest;
import com.krushkov.virtualwallet.models.dtos.requests.RegisterRequest;
import com.krushkov.virtualwallet.models.dtos.responses.UserResponse;
import com.krushkov.virtualwallet.services.contacts.AuthService;
import com.krushkov.virtualwallet.services.contacts.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final JwtCookieUtil jwtCookieUtil;
    private final UserService userService;
    private final AuthMapper authMapper;

    @Override
    public void register(RegisterRequest request, HttpServletResponse response) {
        User user = authMapper.toObject(request);
        userService.create(user);
        //ToDo: Email verification
    }

    @Override
    public UserResponse login(LoginRequest request, HttpServletResponse response) {
        if (request.identifier() == null || request.identifier().isBlank()
                || request.password() == null || request.password().isBlank()) {
            throw new InvalidOperationException("Username/Email and password are required.");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.identifier(),
                        request.password()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String jwt = jwtUtil.generateToken(userDetails);
        jwtCookieUtil.addTokenCookie(response, jwt);

        return UserResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getEmail(),
                userDetails.getRole()
        )
    }

    @Override
    public void logout(HttpServletResponse response) {

    }
}
