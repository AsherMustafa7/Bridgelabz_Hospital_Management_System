# Feature 08 – Security Testing

## Overview

This feature introduces integration testing for the Spring Security and JWT authentication implementation.

The objective is to verify authentication, authorization, password encoding, JWT validation, and access restrictions through HTTP requests.

## Technologies Used

- JUnit 5
- Spring Boot Test
- Mockito
- MockMvc
- Spring Security Test
- H2 Database

## Test Database

H2 is configured as an isolated in-memory database for integration testing.

This ensures that test execution does not modify the application's actual MySQL database.

The test configuration uses a separate JWT secret and expiration configuration.

## Integration Test Coverage

| Test Case | Expected Result |
|---|---|
| User registration | 201 Created |
| Correct login credentials | 200 OK |
| Incorrect login password | 401 Unauthorized |
| Protected endpoint without token | 401 Unauthorized |
| Protected endpoint with invalid token | 401 Unauthorized |
| Protected endpoint with valid token | 200 OK |
| Password BCrypt verification | Pass |
| Default registration role | USER |

## Password Security

Tests verify that:

- Passwords are not stored as plain text.
- Passwords are encoded using BCrypt.
- The encoded password can be verified using `PasswordEncoder.matches()`.

## JWT Security

Tests verify that:

- Successful registration returns a JWT.
- Successful login returns a JWT.
- Invalid tokens cannot access protected endpoints.
- Requests without authentication cannot access protected endpoints.

## Running Tests

Run all tests:

```powershell
mvn clean test
```

Build the application:

```powershell
mvn clean package
```

## Important Notes

- H2 is used only for testing.
- MySQL remains the application's development database.
- The test JWT secret is separate from the development secret.
- No real credentials or production secrets are stored in the test configuration.

## Outcome

The authentication and authorization implementation is verified through automated integration tests, providing confidence that protected APIs reject unauthenticated requests and accept valid JWT authentication.