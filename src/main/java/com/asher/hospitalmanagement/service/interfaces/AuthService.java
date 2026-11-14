
package com.asher.hospitalmanagement.service.interfaces;

import com.asher.hospitalmanagement.dto.request.LoginRequest;
import com.asher.hospitalmanagement.dto.request.RegisterRequest;
import com.asher.hospitalmanagement.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
