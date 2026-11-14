
package com.asher.hospitalmanagement.service.implementations;

import com.asher.hospitalmanagement.dto.request.LoginRequest;
import com.asher.hospitalmanagement.dto.request.RegisterRequest;
import com.asher.hospitalmanagement.dto.response.AuthResponse;

import com.asher.hospitalmanagement.entity.Role;
import com.asher.hospitalmanagement.entity.User;

import com.asher.hospitalmanagement.exception.DuplicateResourceException;

import com.asher.hospitalmanagement.mapper.UserMapper;

import com.asher.hospitalmanagement.security.JwtService;

import com.asher.hospitalmanagement.service.interfaces.AuthService;
import com.asher.hospitalmanagement.service.interfaces.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger =
            LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UserService userService,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /**
     * Registers a new user and returns a JWT.
     */
    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userService.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException(
                    "An account with this email already exists"
            );
        }

        User user = userMapper.toEntity(request);

        // Never save a plain-text password.
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // Public registration always receives USER role.
        user.setRole(Role.USER);

        User savedUser = userService.saveUser(user);

        String token = jwtService.generateToken(savedUser);

        logger.info("New user registered with ID: {}", savedUser.getId());

        return new AuthResponse(
                token,
                "Bearer",
                jwtService.getExpiration()
        );
    }

    /**
     * Authenticates credentials and returns a JWT.
     */
    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Authenticated user could not be loaded"
                        )
                );

        String token = jwtService.generateToken(user);

        logger.info("User logged in successfully: {}", user.getEmail());

        return new AuthResponse(
                token,
                "Bearer",
                jwtService.getExpiration()
        );
    }
}
