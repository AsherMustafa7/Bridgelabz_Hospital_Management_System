
# Feature 05 – Global Exception Handling and Validation

## Overview

This feature introduces centralized exception handling and validation error responses in the Hospital Management System.

It ensures that API errors are returned in a consistent and meaningful format.

## Objectives

- Create custom runtime exceptions.
- Implement centralized exception handling.
- Return meaningful HTTP status codes.
- Create standardized error response DTOs.
- Handle request validation failures.
- Handle malformed JSON requests.
- Handle database constraint violations.
- Avoid exposing internal exception details to API clients.

## Package Structure

```text
com.asher.hospitalmanagement/
│
├── dto/
│   └── response/
│       ├── ApiErrorResponse.java
│       └── ValidationErrorResponse.java
│
├── exception/
│   ├── ResourceNotFoundException.java
│   ├── DuplicateResourceException.java
│   └── GlobalExceptionHandler.java
│
└── service/
    └── implementations/
        └── PatientServiceImpl.java
```

## 1. Custom Exceptions

### ResourceNotFoundException

Extends RuntimeException.

Used when a requested resource does not exist.

Example: A patient cannot be found using the supplied ID.

### DuplicateResourceException

Extends RuntimeException.

Used when an attempt is made to create a resource that already exists.

Example: Registering an account with an existing email address.

## 2. API Error Response

ApiErrorResponse provides a consistent error response containing:

- timestamp
- status
- error
- message
- path

## 3. Validation Error Response

ValidationErrorResponse provides:

- timestamp
- status
- error
- fieldErrors
- path

It allows the API to return individual validation messages for invalid request fields.

## 4. Global Exception Handler

GlobalExceptionHandler uses @RestControllerAdvice to handle exceptions centrally.

Supported exceptions:

| Exception | HTTP Status |
|---|---|
| ResourceNotFoundException | 404 Not Found |
| DuplicateResourceException | 409 Conflict |
| MethodArgumentNotValidException | 400 Bad Request |
| ConstraintViolationException | 400 Bad Request |
| HttpMessageNotReadableException | 400 Bad Request |
| DataIntegrityViolationException | 409 Conflict |
| Unexpected Exception | 500 Internal Server Error |

## 5. Patient Service Update

PatientServiceImpl now throws ResourceNotFoundException when a requested patient does not exist.

HTTP-specific exception handling remains outside the service layer.

## 6. Validation

The global handler is prepared to process validation failures from:

- @Valid request DTOs
- Jakarta Bean Validation constraints
- Controller method parameter validation

Request DTOs and their validation annotations will be implemented with the API layer.

## 7. Logging and Security

SLF4J is used to log important errors.

Unexpected exception details are recorded internally but are not exposed in API responses.

Passwords and other sensitive information must not be logged.

## 8. Testing and Verification

Run:

`mvn clean package`

Start the application:

`mvn spring-boot:run`

Verify that the application starts successfully and that the global exception handler is registered.

HTTP-level exception and validation tests will be performed when the REST controllers are implemented.

## 9. Dependencies

No additional dependencies are required.

The project already includes Spring Boot Starter Validation and Spring Boot Starter Web.

## Important Note

@RestControllerAdvice handles exceptions during Spring MVC request processing.

Spring Security filter-chain exceptions are handled separately by Spring Security's authentication entry point and access-denied handler. Those will be configured during the JWT security feature.

## Status

Custom exceptions, standardized error response DTOs, and centralized exception handling implemented.
