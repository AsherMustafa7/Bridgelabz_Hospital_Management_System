
package com.asher.hospitalmanagement.security;

import com.asher.hospitalmanagement.dto.request.LoginRequest;
import com.asher.hospitalmanagement.dto.request.RegisterRequest;
import com.asher.hospitalmanagement.repository.UserRepository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String email;

    private final String password = "Password@123";

    @BeforeEach
    void setUp() {
        email = "test-" + UUID.randomUUID() + "@example.com";
    }

    private String registerAndGetToken() throws Exception {

        RegisterRequest request = new RegisterRequest();

        request.setName("Test User");
        request.setEmail(email);
        request.setPassword(password);

        MvcResult result = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andReturn();

        JsonNode response = objectMapper.readTree(
                result.getResponse().getContentAsString()
        );

        return response.get("token").asText();
    }

    @Test
    void registerUserShouldReturnJwtToken() throws Exception {

        String token = registerAndGetToken();

        assertNotNull(token);
        assertFalse(token.isBlank());

        assertTrue(userRepository.existsByEmail(email));
    }

    @Test
    void registeredPasswordShouldBeStoredAsBCryptHash() throws Exception {

        registerAndGetToken();

        var user = userRepository.findByEmail(email)
                .orElseThrow();

        assertNotEquals(password, user.getPassword());

        assertTrue(
                passwordEncoder.matches(password, user.getPassword())
        );

        assertEquals("USER", user.getRole().name());
    }

    @Test
    void loginWithCorrectCredentialsShouldReturnToken() throws Exception {

        registerAndGetToken();

        LoginRequest request = new LoginRequest();

        request.setEmail(email);
        request.setPassword(password);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty())
                .andExpect(jsonPath("$.tokenType").value("Bearer"));
    }

    @Test
    void loginWithIncorrectPasswordShouldReturn401() throws Exception {

        registerAndGetToken();

        LoginRequest request = new LoginRequest();

        request.setEmail(email);
        request.setPassword("WrongPassword123");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void accessingPatientsWithoutTokenShouldReturn401() throws Exception {

        mockMvc.perform(get("/api/patients"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void accessingPatientsWithInvalidTokenShouldReturn401() throws Exception {

        mockMvc.perform(get("/api/patients")
                        .header(
                                "Authorization",
                                "Bearer invalid.jwt.token"
                        ))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void accessingPatientsWithValidTokenShouldSucceed() throws Exception {

        String token = registerAndGetToken();

        mockMvc.perform(get("/api/patients")
                        .header(
                                "Authorization",
                                "Bearer " + token
                        ))
                .andExpect(status().isOk());
    }
}
