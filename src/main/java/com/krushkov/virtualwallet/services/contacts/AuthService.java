package com.krushkov.virtualwallet.services.contacts;

import com.krushkov.virtualwallet.models.dtos.requests.LoginRequest;
import com.krushkov.virtualwallet.models.dtos.requests.RegisterRequest;
import com.krushkov.virtualwallet.models.dtos.responses.UserPrincipalResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void register(RegisterRequest request, HttpServletResponse response);

    UserPrincipalResponse login(LoginRequest request, HttpServletResponse response);

    void logout(HttpServletResponse response);
}
