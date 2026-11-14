
# Feature 07 – Spring Security and JWT Authentication

## Overview

This feature implements authentication and authorization using Spring Security and JSON Web Tokens (JWT).

Users can register and log in. Successful authentication produces a signed JWT that is required to access protected Patient APIs.

## Objectives

- Implement user registration and login.
- Encode passwords using BCrypt.
- Implement UserDetailsService.
- Configure AuthenticationManager and AuthenticationProvider.
- Generate and validate JWTs.
- Implement a JWT authentication filter.
- Configure stateless Spring Security.
- Protect Patient CRUD endpoints.
- Test authentication and authorization using Postman.

## 1. Authentication Endpoints

| Method | Endpoint | Access |
|---|---|---|
| POST | /api/auth/register | Public |
| POST | /api/auth/login | Public |

Registration assigns the USER role by default.

Public registration cannot assign ADMIN privileges.

## 2. Password Encoding

BCryptPasswordEncoder is used to hash passwords before saving users.

Plain-text passwords are never stored in the database.

During login, Spring Security compares the submitted password against the stored BCrypt hash.

## 3. UserDetailsService

AppUserDetailsService implements Spring Security's UserDetailsService.

It loads a user from UserRepository using the user's email address.

The resulting UserDetails contains:

- Username (email)
- Encoded password
- Granted authorities

## 4. JWT Generation

JwtService generates signed JWTs containing:

- Subject: user's email
- Role claim
- Issued-at timestamp
- Expiration timestamp

The token is signed using an HMAC signing key derived from the Base64-encoded secret.

The secret is supplied through an environment variable and is not committed to Git.

## 5. JWT Authentication Filter

JwtAuthenticationFilter extends OncePerRequestFilter.

It:

1. Reads the Authorization header.
2. Extracts the Bearer token.
3. Validates the token signature and expiration.
4. Loads the associated user.
5. Creates an Authentication object.
6. Stores authentication in SecurityContextHolder.

Invalid tokens do not establish authentication.

## 6. Security Configuration

SecurityConfig defines:

- BCryptPasswordEncoder
- DaoAuthenticationProvider
- AuthenticationManager
- SecurityFilterChain

The application uses stateless session management.

Registration and login are public. All other endpoints require authentication.

## 7. Authentication Flow

### Registration

Client → AuthController → AuthService → BCryptPasswordEncoder → UserRepository

### Login

Client → AuthController → AuthenticationManager → AuthenticationProvider → UserDetailsService → JwtService

### Protected API

Client → JwtAuthenticationFilter → SecurityContextHolder → PatientController

## 8. Environment Configuration

Required environment variables:

- JWT_SECRET
- JWT_EXPIRATION_MS

The JWT secret must be Base64 encoded and contain at least 32 random bytes for the configured HMAC algorithm.

The `.env` file must not be committed.

## 9. Postman Testing

Verified:

- Successful registration.
- Duplicate email registration.
- Registration validation.
- Successful login.
- Incorrect password rejection.
- Patient API access without a token.
- Patient API access with a valid token.
- Invalid JWT rejection.

## 10. Security Notes

- JWT payloads are encoded, not encrypted.
- Passwords are stored as BCrypt hashes.
- Public registration assigns USER privileges only.
- JWT secrets are externalized.
- No HTTP session is used for authentication.
- Patient APIs require a valid JWT.

## Status

Spring Security and JWT authentication implemented.
