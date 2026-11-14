package com.asher.hospitalmanagement.service.implementations;

import com.asher.hospitalmanagement.dto.request.LoginRequest;
import com.asher.hospitalmanagement.dto.request.RegisterRequest;
import com.asher.hospitalmanagement.dto.response.AuthResponse;

import com.asher.hospitalmanagement.entity.Role;
import com.asher.hospitalmanagement.entity.User;

import com.asher.hospitalmanagement.exception.DuplicateResourceException;

import com.asher.hospitalmanagement.mapper.UserMapper;
import com.asher.hospitalmanagement.security.JwtService;
import com.asher.hospitalmanagement.service.interfaces.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImpl authService;

    private User user;

    @BeforeEach
    void setUp() {

        user = new User();

        user.setId(1L);
        user.setName("Asher");
        user.setEmail("asher@example.com");
        user.setPassword("encoded-password");
        user.setRole(Role.USER);
    }

    @Test
    void registerShouldSaveEncodedPasswordAndReturnToken() {

        RegisterRequest request = new RegisterRequest(
                "Asher",
                "asher@example.com",
                "Password@123"
        );

        when(userService.existsByEmail(request.getEmail()))
                .thenReturn(false);

        when(userMapper.toEntity(request)).thenReturn(user);

        when(passwordEncoder.encode(request.getPassword()))
                .thenReturn("encoded-password");

        when(userService.saveUser(user)).thenReturn(user);

        when(jwtService.generateToken(user)).thenReturn("test-jwt");

        when(jwtService.getExpiration()).thenReturn(86400000L);

        AuthResponse response = authService.register(request);

        assertEquals("test-jwt", response.getToken());
        assertEquals("Bearer", response.getTokenType());
        assertEquals(86400000L, response.getExpiresIn());

        assertEquals("encoded-password", user.getPassword());
        assertEquals(Role.USER, user.getRole());

        verify(userService).saveUser(user);
        verify(passwordEncoder).encode("Password@123");
    }

    @Test
    void registerShouldRejectDuplicateEmail() {

        RegisterRequest request = new RegisterRequest(
                "Asher",
                "asher@example.com",
                "Password@123"
        );

        when(userService.existsByEmail(request.getEmail()))
                .thenReturn(true);

        assertThrows(
                DuplicateResourceException.class,
                () -> authService.register(request)
        );

        verify(userService, never()).saveUser(any());
        verify(passwordEncoder, never()).encode(anyString());
    }

    @Test
    void loginShouldAuthenticateAndReturnToken() {

        LoginRequest request = new LoginRequest(
                "asher@example.com",
                "Password@123"
        );

        when(userService.findByEmail(request.getEmail()))
                .thenReturn(Optional.of(user));

        when(jwtService.generateToken(user)).thenReturn("test-jwt");

        when(jwtService.getExpiration()).thenReturn(86400000L);

        AuthResponse response = authService.login(request);

        assertEquals("test-jwt", response.getToken());
        assertEquals("Bearer", response.getTokenType());

        verify(authenticationManager).authenticate(
                any(UsernamePasswordAuthenticationToken.class)
        );

        verify(jwtService).generateToken(user);
    }
}
